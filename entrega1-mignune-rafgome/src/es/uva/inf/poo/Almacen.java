package es.uva.inf.poo;

import java.util.List;
import java.util.ArrayList;

/**
 * Tipo abstracto de datos que representa un almacen de productos. Aporta
 * funcionalidades para añadir y eliminar productos, asi como para conocer y
 * modificar su stock.
 * 
 * @author mignune
 * @author rafgome
 * 
 */
public class Almacen {

	private static final String ERROR_PRODUCTO_NULL = "El producto no puede ser null";
	private static final String ERROR_NO_CONTENIDO = "El almacen no contiene el producto";

	private List<Producto> productos;

	/**
	 * Inicializa un almacen vacio
	 */
	public Almacen() {
		productos = new ArrayList<>();
	}

	/**
	 * Añade un producto al almacen.
	 * 
	 * @param p Producto que se quiere añadir. No puede estar contenido en el
	 *          almacen. No puede ser null
	 * @throws IllegalArgumentException Si el producto es null
	 * @throws IllegalArgumentException Si el producto ya esta contenido en el
	 *                                  almacen.
	 */
	public void addProducto(Producto p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (productos.contains(p))
			throw new IllegalArgumentException("El almacen ya contiene el producto");

		productos.add(p);
	}

	/**
	 * Elimina un producto del almacen
	 * 
	 * @param p Producto que se quiere eliminar. Debe estar contenido en el almacen.
	 *          No puede ser null.
	 * @throws IllegalArgumentException Si el producto es null
	 * @throws IllegalArgumentException Si el producto no esta contenido en el
	 *                                  almacen
	 */
	public void removeProducto(Producto p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (!productos.contains(p))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		productos.remove(p);
	}

	/**
	 * Devuelve true si el producto esta contenido en el almacen.
	 * 
	 * @param p Producto cuya presencia se quiere probar. No puede ser null
	 * @return true si el producto especificado esta contenido en el almacen
	 * @throws IllegalArgumentException Si el producto es null
	 */
	public boolean exist(Producto p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		return productos.contains(p);
	}

	/**
	 * Devuelve el stock del producto especificado
	 * 
	 * @param p Producto del que se quiere saber su stock. Debe de estar contenido
	 *          en el almacen. No puede ser null.
	 * @return el stock del producto especificado
	 * @throws IllegalArgumentException Si el producto es null
	 * @throws IllegalArgumentException Si el producto no esta contenido en el
	 *                                  almacen
	 */
	public int getStock(Producto p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (!productos.contains(p))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		return p.getStock();
	}

	/**
	 * Establece un nuevo stock para un producto del almacen.
	 * 
	 * @param p     Producto al que se le quiere cambiar el stock. Debe de estar
	 *              contenido en el almacen. No puede ser null
	 * @param stock Cantidad a la que se quiere establecer. No puede ser menor que
	 *              0.
	 * @throws IllegalArgumentException Si el producto es null
	 * @throws IllegalArgumentException Si el producto no esta contenido en el
	 *                                  almacen
	 * @throws IllegalArgumentException Si el Stock es menor que 0
	 * @see Producto#setStock(int)
	 */
	public void setStock(Producto p, int stock) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (!productos.contains(p))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		if (stock < 0)
			throw new IllegalArgumentException("El stock no puede ser negativo");

		p.setStock(stock);
	}
}
