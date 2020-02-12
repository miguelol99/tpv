package es.uva.inf.poo;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlmacenTest { 

	@Test
	public void testInicializarValido() {
		Almacen a = new Almacen();
		assertNotNull(a);  
	}
	
	@Test
	public void testAddProductoValido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 0.01, 0, "c");
		a.addProducto(p);
		assertTrue(a.exist(p));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddProductoNull() {
		Almacen a = new Almacen();
		a.addProducto(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddProductoActualmenteContenido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		a.addProducto(p); 
	}
	
	@Test
	public void testRemoveProductoValido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		a.removeProducto(p);
		assertFalse(a.exist(p));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveProductoNull() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		a.removeProducto(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveProductoNoContenido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.removeProducto(p);
	}

	@Test
	public void testExistValidoTrue() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		assertTrue(a.exist(p));
	}

	@Test
	public void testExistValidoFalse() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		assertFalse(a.exist(p));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExistNull() {
		Almacen a = new Almacen();
		assertTrue(a.exist(null));
	}

	@Test
	public void testGetStockValido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		assertEquals(2, a.getStock(p));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetStockProductoNull() {
		Almacen a = new Almacen();
		a.getStock(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetStockProductoNoContenido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.getStock(p);
	}

	@Test
	public void testSetStockValido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		a.setStock(p, 1);
		assertEquals(1, a.getStock(p));
	}  
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockProductoNull() {
		Almacen a = new Almacen();
		a.setStock(null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockProductoNoContenido() {
		Almacen a = new Almacen();
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.setStock(p, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockStockNegativo() {
		Almacen a = new Almacen(); 
		Producto p = new Producto("a", "b", 1, 2, "c");
		a.addProducto(p);
		a.setStock(p, -1);
	}
}
