package es.uva.inf.poo;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Representa un terminal de punto de venta. Proporciona funcionalidades para
 * cobrar, anular o cerrar una comanda, asi como para consultar informacion de
 * las comandas de un dia.
 * 
 * @author mignune
 * @author rafgome
 *
 */

public class TPV {

	private static final String ERROR_COMANDA_NULL = "La comanda actual no puede ser null";

	private String codigoTPV;
	private int codigoComanda;
	private Comanda comandaActual;
	private List<Comanda> comandasPagadas;
	private List<Comanda> comandasCerradas;
	private List<Comanda> comandasAnuladas;

	/**
	 * Inicializa un TPV con codigo
	 * 
	 * @param codigo Codigo identificador del TPV. No puede ser null
	 * @throws java.lang.IllegalArgumentException Si el codigo es null
	 */
	public TPV(String codigo) {
		if (codigo == null)
			throw new IllegalArgumentException("El codigo del tpv no puede ser null");

		this.codigoTPV = codigo;
		this.codigoComanda = 0;
		comandasAnuladas = new ArrayList<>();
		comandasPagadas = new ArrayList<>();
		comandasCerradas = new ArrayList<>();
	}

	/**
	 * Crea una nueva comanda y la establece como actual. No puede existir otra
	 * comanda abierta o pagada actualmente.
	 * 
	 * @throws java.lang.IllegalStateException Si existe otra comanda abierta.
	 * @throws java.lang.IllegalStateException Si existe otra comanda pagada.
	 * @see #generarCodigoComanda()
	 */
	public void newComanda() {
		if (comandaActual != null && comandaActual.getEstado() == Comanda.Estado.ABIERTO)
			throw new IllegalStateException("La comanda actual no puede estar abierta");

		if (comandaActual != null && comandaActual.getEstado() == Comanda.Estado.PAGADO)
			throw new IllegalStateException("La comanda actual no puede estar pagada");

		Comanda c = new Comanda(generarCodigoComanda());
		comandaActual = c;
	}

	/**
	 * Cobra el importe de la comanda actual a una tarjeta monedero aportada por el
	 * codigo cliente. Cambia el estado de la comanda a pagado y la añade a la lista
	 * de comandas pagadas. Actualiza el stock del almacen de sus productos. La
	 * comanda actual debe de estar abierta.
	 * 
	 * @param t          Tarjeta monedero del cliente. Debe de tener saldo
	 *                   suficiente para pagar el importe de la comanda. Se requiere
	 *                   una credencial valida para poder realizar operaciones.
	 * @param credencial Aportada por el código cliente por razones de seguridad
	 *                   para obtener el permiso necesario para descontar saldo
	 * @throws java.lang.IllegalStateException Si la comanda actual es null
	 * @throws java.lang.IllegalStateException Si la comanda actual no esta abierta
	 * @throws java.lang.IllegalArgumentException Si la tarjeta monedero es null
	 * @throws java.lang.IllegalStateException Si la credencial es null
	 * @see fabricante.externo.tarjetas.TarjetaMonedero#descontarDelSaldo(String,
	 *      double)
	 * @see #actualizarAlmacen()
	 */
	public void cobrarComandaActual(TarjetaMonedero t, String credencial) {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.ABIERTO)
			throw new IllegalStateException("La comanda actual debe estar abierta");

		if (t == null)
			throw new IllegalArgumentException("La tarjeta no puede ser null");

		if (credencial == null)
			throw new IllegalArgumentException("La credencial no puede ser null");

		t.descontarDelSaldo(credencial, comandaActual.getImporte());
		comandaActual.setEstado(Comanda.Estado.PAGADO);
		addComandasPagadas();
		actualizarAlmacen();
	}

	/**
	 * Cierra la comanda actual y la añade a la lista de comandas cerradas. La
	 * comanda actual debe estar pagada.
	 * 
	 * @throws IllegalStateException Si la comanda es null
	 * @throws IllegalStateException Si la comanda no esta pagada
	 */
	public void cerrarComandaActual() {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.PAGADO)
			throw new IllegalStateException("La comanda actual debe estar pagada");

		comandaActual.setEstado(Comanda.Estado.CERRADO);
		addComandasCerradas();
	}

	/**
	 * Anula la comanda actual y la añade a la lista de comandas anuladas. La
	 * comanda actual debe estar abierta
	 * 
	 * @throws IllegalStateException Si la comanda es null
	 * @throws IllegalStateException Si la comanda no esta abierta
	 */
	public void anularComandaActual() {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.ABIERTO)
			throw new IllegalStateException("La comanda actual debe estar abierta");

		comandaActual.setEstado(Comanda.Estado.ANULADO);
		addComandasAnuladas();
	}

	/**
	 * Añade la comanda actual a la lista de comandas pagadas. La comanda actual
	 * debe estar pagada
	 * 
	 * @throws IllegalStateException Si la comanda es null
	 * @throws                       java.lang.IllegalStateException Si la comanda
	 *                               actual no esta pagada
	 */
	public void addComandasPagadas() {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.PAGADO)
			throw new IllegalStateException("La comanda actual debe estar pagada");

		comandasPagadas.add(comandaActual);
	}

	/**
	 * Añade la comanda actual a la lista de comandas cerradas. La comanda actual
	 * debe estar cerrada
	 * 
	 * @throws IllegalStateException Si la comanda es null
	 * @throws IllegalStateException Si la comanda no esta cerrada
	 */
	public void addComandasCerradas() {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.CERRADO)
			throw new IllegalStateException("La comanda actual debe estar cerrada");

		comandasCerradas.add(comandaActual);
	}

	/**
	 * Añade la comanda actual a la lista de comandas anuladas. La comanda actual
	 * debe de estar anulada.
	 * 
	 * @throws IllegalStateException Si la comanda es null
	 * @throws                       java.lang.IllegalStateException Si la comanda
	 *                               no esta anulada
	 */
	public void addComandasAnuladas() {
		if (comandaActual == null)
			throw new IllegalStateException(ERROR_COMANDA_NULL);

		if (comandaActual.getEstado() != Comanda.Estado.ANULADO)
			throw new IllegalStateException("La comanda actual debe estar anulada");

		comandasAnuladas.add(comandaActual);
	}

	/**
	 * Actualiza el almacen restando al stock de los productos su cantidad en la
	 * comanda actual. La comanda actual debe de estar pagada.
	 * 
	 * @throws java.lang.IllegalStateException si la comanda actual no esta pagada
	 */
	public void actualizarAlmacen() {
		if (comandaActual.getEstado() != Comanda.Estado.PAGADO)
			throw new IllegalStateException("La comanda actual debe de estar pagada");

		List<Producto> listaProductos = comandaActual.getProductos();
		List<Integer> listaCantidades = comandaActual.getCantidad();

		for (int i = 0; i < listaProductos.size(); i++) {
			Producto p = listaProductos.get(i);
			int cantidad = listaCantidades.get(i);

			p.setStock(p.getStock() - cantidad);
		}
	}

	/**
	 * Devuelve el importe de las comandas pagadas en un dia
	 * 
	 * @return el importe de las comandas pagadas en un dia
	 * @see Comanda#getImporte()
	 */
	public double getImporte() {
		double importe = 0;
		LocalDate hoy = LocalDate.now();

		for (Comanda c : comandasPagadas)
			if (c.getFecha().equals(hoy))
				importe = importe + c.getImporte();

		return importe;
	}

	/**
	 * Devuelve una lista de comandas con el mismo estado realizadas en un dia.
	 * Dicho estado no puede ser abierto.
	 * 
	 * @param estado Estado de las comandas que se quiere consultar. No puede ser
	 *               abierto. No puede ser null.
	 * @return la lista de comandas con el mismo estado realizadas en un dia.
	 * @throws java.lang.IllegalStateException si el estado es null
	 * @throws java.lang.IllegalStateException si el estado es abierto
	 */
	public List<Comanda> getComandasDeHoy(Comanda.Estado estado) {
		if (estado == null)
			throw new IllegalArgumentException("El estado no puede ser null");

		if (estado == Comanda.Estado.ABIERTO)
			throw new IllegalArgumentException("El estado no puede ser abierto");

		LocalDate hoy = LocalDate.now();
		List<Comanda> comandas = new ArrayList<>();
		List<Comanda> listaReturn = new ArrayList<>();

		if (estado == Comanda.Estado.PAGADO)
			comandas = comandasPagadas;

		else if (estado == Comanda.Estado.CERRADO)
			comandas = comandasCerradas;

		else if (estado == Comanda.Estado.ANULADO)
			comandas = comandasAnuladas;

		for (Comanda c : comandas)
			if (c.getFecha().equals(hoy))
				listaReturn.add(c);

		return listaReturn;
	}

	/**
	 * Devuelve el codigoTPV
	 * 
	 * @return el codigoTPV
	 */
	public String getCodigoTPV() {
		return codigoTPV;
	}

	/**
	 * Devuelve el codigoComanda
	 * 
	 * @return el codigoComanda
	 */
	public int getCodigoComanda() {
		return codigoComanda;
	}

	/**
	 * Incrementa el valor de codigoComanda en una unidad y devuelve su valor en
	 * forma de string.
	 * 
	 * @return el codigoComanda aumentado en una unidad en forma de string.
	 */
	public String generarCodigoComanda() {
		codigoComanda = codigoComanda + 1;
		return "" + codigoComanda + "";
	}

	/**
	 * Devuelve la comanda actual del TPV
	 * 
	 * @return la comanda actual del TPV
	 */
	public Comanda getComandaActual() {
		return comandaActual;
	}

	/**
	 * Devuelve una lista con las comandas pagadas en ese tpv
	 * 
	 * @return las comandas pagadas con ese tpv
	 */
	public List<Comanda> getComandasPagadas() {
		return comandasPagadas;
	}

	/**
	 * Devuelve una lista con las comandas cerradas en ese tpv
	 * 
	 * @return las comandas cerradas con ese tpv
	 */
	public List<Comanda> getComandasCerradas() {
		return comandasCerradas;
	}

	/**
	 * Devuelve una lista con las comandas anuladas en ese tpv
	 * 
	 * @return las comandas anuladas con ese tpv
	 */
	public List<Comanda> getComandasAnuladas() {
		return comandasAnuladas;
	}

}
