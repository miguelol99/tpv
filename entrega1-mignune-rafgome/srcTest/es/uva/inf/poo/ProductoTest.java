package es.uva.inf.poo; 

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductoTest {  

	public static final double ERROR_ADMISIBLE = 0.001;
	 
	@Test
	public void testInicializarValido() {
		Producto p = new Producto("a","b",0.01,0,"c");		
		assertNotNull(p);
	} 

	@SuppressWarnings("unused") 
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarNombreNoValido() {
		Producto p = new Producto(null,"b",0.01,0,"c");
	} 
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarDescripcionNoValida() {
		Producto p = new Producto("a",null,0.01,0,"c");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarPrecioNoValido() {
		Producto p = new Producto("a","b",0,0,"c");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarStockNoValido() {
		Producto p = new Producto("a","b",0.01,-1,"c");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		Producto p = new Producto("a","b",0.01,0,null); 
	}
	
	@Test
	public void testGetNombre() {
		Producto p = new Producto("a","b",0.01,0,"c");
		assertEquals("a", p.getNombre());
	}
	
	@Test
	public void testGetDescripcion() {
		Producto p = new Producto("a","b",0.01,0,"c");
		assertEquals("b", p.getDescripcion());
	}
	
	@Test
	public void testGetPrecio() {
		Producto p = new Producto("a","b",0.01,0,"c");
		assertEquals(0.01, p.getPrecio(), ERROR_ADMISIBLE);
	}
	
	@Test
	public void testSetPrecioValido() {
		Producto p = new Producto("a","b",0.01,0,"c");
		p.setPrecio(0.02);
		assertEquals(0.02, p.getPrecio(), ERROR_ADMISIBLE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPrecioIgualACero() {
		Producto p = new Producto("a","b",0.01,0,"c");
		p.setPrecio(0);
	}
	
	@Test
	public void testGetStock() {
		Producto p = new Producto("a","b",0.01,0,"c");
		assertEquals(0, p.getStock());
	}
	
	@Test
	public void testSetStockValido() {
		Producto p = new Producto("a","b",0.01,0,"c");
		p.setStock(1);
		assertEquals(1, p.getStock());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockNoValido() {
		Producto p = new Producto("a","b",0.01,0,"c");
		p.setStock(-1);
	}
	
	@Test
	public void testGetCodigo() {
		Producto p = new Producto("a","b",0.01,1,"c");
		assertEquals("c", p.getCodigo());
	} 

}
