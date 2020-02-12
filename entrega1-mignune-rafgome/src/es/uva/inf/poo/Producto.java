package es.uva.inf.poo;

/**
 * Tipo de dato abstracto que representa un producto a partir de su nombre,
 * descripcion, precio, stock y codigo. Aporta funcionalidad para modificar su
 * precio y stock.
 * 
 * @author mignune
 * @author rafgome
 */
public class Producto {

	private String codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;

	/**
	 * Inicializa un producto con nombre, descripcion, precio, stock y codigo
	 * especificado
	 * 
	 * @param nombre      El nombre asociado al producto. No puede ser null.
	 * @param descripcion Descripcion asociada al producto. No puede ser null.
	 * @param precio      El precio del producto. Debe de ser mayor que cero.
	 * @param stock       El Stock del producto. Debe de ser mayor o igual que cero.
	 * @param codigo      El codigo del producto. No puede ser null.
	 * @throws java.lang.IllegalArgumentException Si el nombre es null.
	 * @throws java.lang.IllegalArgumentException Si la descripcion es null.
	 * @throws java.lang.IllegalArgumentException Si el precio es menor o igual a 0.
	 * @throws java.lang.IllegalArgumentException Si el stock menor que 0.
	 * @throws java.lang.IllegalArgumentException Si el codigo es null.
	 */
	public Producto(String nombre, String descripcion, double precio, int stock, String codigo) {
		if (nombre == null)
			throw new IllegalArgumentException("El nombre es null");

		if (descripcion == null)
			throw new IllegalArgumentException("La descripcion es null");

		if (precio <= 0)
			throw new IllegalArgumentException("El precio es menor o igual a 0");

		if (stock < 0) {
			throw new IllegalArgumentException("El stock es menor que 0");
		}

		if (codigo == null) {
			throw new IllegalArgumentException("El codigo es null");
		}

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.codigo = codigo;
	}

	/**
	 * Devuelve el codigo del producto
	 * 
	 * @return codigo El codigo del producto
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Devuelve el nombre del producto
	 * 
	 * @return El nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve la descripcion del producto
	 * 
	 * @return La descripcion del producto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Devuelve el precio del producto
	 * 
	 * @return El precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Cambia el precio del producto
	 * 
	 * @param precio Precio que se quiere establecer. No pude ser menor o igual a
	 *               cero.
	 * @throws java.lang.IllegalArgumentException Si el precio es igual o menor que
	 *         cero.
	 */
	public void setPrecio(double precio) {
		if (precio <= 0)
			throw new IllegalArgumentException();

		this.precio = precio;
	}

	/**
	 * Devuelve el número de unidades disponibles.
	 * 
	 * @return El stock del producto.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Cambia el stock del producto.
	 * 
	 * @param stock El stock que se quiere establecer. No puede ser menor que 0;
	 */
	public void setStock(int stock) {
		if (stock < 0)
			throw new IllegalArgumentException();

		this.stock = stock;
	}
}
