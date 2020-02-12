package es.uva.inf.poo;
 
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;


public class ComandaTest { 

	public static final double ERROR_ADMISIBLE = 0.001;
	 
	@Test
	public void testInicializarValido() {
		Comanda c = new Comanda("a");		
		assertNotNull(c); 
	}
	 
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarCodigoNull() {
		Comanda c = new Comanda(null); 
	} 
	
	@Test
	public void testAddProductoValido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a", "b", 0.01, 1, "c");
		c.addProducto(p, 1);
		
		assertEquals(p, c.getProductos().get(0));
		assertEquals(1, c.getCantidadDeUnProducto(p));
	}   
	
	@Test(expected=IllegalStateException.class)
	public void testAddProductoEstadoPagado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.setEstado(Comanda.Estado.PAGADO);
		c.addProducto(p, 1);
	} 
	
	@Test(expected=IllegalStateException.class)
	public void testAddProductoEstadoCerrado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.setEstado(Comanda.Estado.CERRADO);
		c.addProducto(p, 1);
	}

	@Test(expected=IllegalStateException.class)
	public void testAddProductoEstadoAnulado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.setEstado(Comanda.Estado.ANULADO);
		c.addProducto(p, 1);
	} 
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddProductoProductoNull() {
		Comanda c = new Comanda("a"); 
		c.addProducto(null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddProductoUnidadesIgualACero() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddProductoUnidadesMayorQueStock() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 2);
	}
	
	@Test
	public void testRemoveProductoValido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.removeProducto(p);		 
		assertTrue(c.getProductos().isEmpty());
	}
	 
	@Test(expected=IllegalStateException.class)
	public void testRemoveProductoEstadoPagado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.PAGADO);
		c.removeProducto(p);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testRemoveProductoEstadoCerrado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.CERRADO);
		c.removeProducto(p);
	}

	@Test(expected=IllegalStateException.class)
	public void testRemoveProductoEstadoAnulado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.ANULADO);
		c.removeProducto(p);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveProductoProductoNull() {
		Comanda c = new Comanda("a"); 
		c.removeProducto(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveProductoProductoNoContenido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.removeProducto(p);
	}
	 
	@Test
	public void testGetCantidadDeUnProductoValido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		assertEquals(1, c.getCantidadDeUnProducto(p));
	} 
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCantidadDeUnProductoProductoNull() {
		Comanda c = new Comanda("a"); 
		c.getCantidadDeUnProducto(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCantidadDeUnProductoProductoNoContenido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.getCantidadDeUnProducto(p);
	}
	
	@Test
	public void testSetCantidadDeUnProductoValido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setCantidadDeUnProducto(p, 2);
		assertEquals(2, c.getCantidadDeUnProducto(p));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetCantidadDeUnProductoEstadoPagado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.PAGADO);
		c.setCantidadDeUnProducto(p, 2);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetCantidadDeUnProductoEstadoCerrado() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.CERRADO);
		c.setCantidadDeUnProducto(p, 2);
	}

	@Test(expected=IllegalStateException.class)
	public void testSetCantidadDeUnProductoEstadoAnulada() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.ANULADO);
		c.setCantidadDeUnProducto(p, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCantidadDeUnProductoProductoNull() {
		Comanda c = new Comanda("a"); 
		c.setCantidadDeUnProducto(null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCantidadDeUnProductoProductoNoContenido() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,2,"c");
		c.setCantidadDeUnProducto(p, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCantidadDeUnProductoUnidadesIgualACero() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setCantidadDeUnProducto(p, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCantidadDeUnProductoUnidadesMayorQueStock() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setCantidadDeUnProducto(p, 2);
	}
	
	@Test
	public void testGetCodigoValido() {
		Comanda c = new Comanda("a");
		assertEquals("a", c.getCodigo());		
	}

	@Test
	public void testGetEstadoValido() {
		Comanda c = new Comanda("a");
		assertEquals(Comanda.Estado.ABIERTO, c.getEstado());		
	}

	@Test
	public void testSetEstadoDeAbiertoAPagadoValido() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.PAGADO);   
		assertEquals(Comanda.Estado.PAGADO,c.getEstado());
	}
	
	@Test
	public void testSetEstadoDeAbiertoAAnuladoValido() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.ANULADO);   
		assertEquals(Comanda.Estado.ANULADO,c.getEstado());
	}
	
	@Test
	public void testSetEstadoDePagadoACerradoValido() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.PAGADO);   
		c.setEstado(Comanda.Estado.CERRADO);
		assertEquals(Comanda.Estado.CERRADO,c.getEstado());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetEstadoConEstadoNull() {
		Comanda c = new Comanda("a");
		c.setEstado(null); 
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetEstadoDesdeAnulado() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.ANULADO);
		c.setEstado(Comanda.Estado.ABIERTO);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetEstadoDesdeCerrado() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.CERRADO);
		c.setEstado(Comanda.Estado.ABIERTO);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetEstadoDesdePagadoAAbierto() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.PAGADO);
		c.setEstado(Comanda.Estado.ABIERTO);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetEstadoDesdePagadoAAnulado() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.PAGADO);
		c.setEstado(Comanda.Estado.ANULADO);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSetEstadoDesdeAbiertoACerrado() {
		Comanda c = new Comanda("a");
		c.setEstado(Comanda.Estado.CERRADO);
	}

	@Test
	public void testGetProductosVacio() {
		Comanda c = new Comanda("a");
		assertTrue(c.getProductos().isEmpty());
	}
	
	@Test
	public void testGetProductosNoVacio() {
		Comanda c = new Comanda("a"); 
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		assertEquals(p, c.getProductos().get(0));
		assertEquals(1, c.getProductos().size());
	}
	
	@Test
	public void testGetCantidadVacio() {
		Comanda c = new Comanda("a");
		assertTrue(c.getCantidad().isEmpty());
	}
	
	@Test
	public void testGetCantidadNoVacia() {
		Comanda c = new Comanda("a");
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		assertEquals(1, c.getCantidadDeUnProducto(p));
		assertEquals(1,c.getCantidad().size());
	}
	
	@Test
	public void testGetImporteTotalEstadoAbierto() {
		Comanda c = new Comanda("a");
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		assertEquals(0.01, c.getImporte(), ERROR_ADMISIBLE);
	} 
	
	@Test
	public void testGetImporteTotalEstadoAnulado() {
		Comanda c = new Comanda("a");
		Producto p = new Producto("a","b",0.01,1,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.ANULADO);
		assertEquals(0, c.getImporte(), ERROR_ADMISIBLE);
	}
	
	@Test
	public void testGetImporteTotalEstadoPagado() {
		Comanda c = new Comanda("a");
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.PAGADO);
		p.setPrecio(0.02);
		p.setStock(2); 
		assertEquals(0.01, c.getImporte(),ERROR_ADMISIBLE);
	}
	
	@Test
	public void testGetImporteTotalEstadoCerrado() {
		Comanda c = new Comanda("a");
		Producto p = new Producto("a","b",0.01,2,"c");
		c.addProducto(p, 1);
		c.setEstado(Comanda.Estado.PAGADO);
		c.setEstado(Comanda.Estado.CERRADO);
		p.setPrecio(0.02);
		p.setStock(2);
		assertEquals(0.01, c.getImporte(),ERROR_ADMISIBLE);
	}
	
	@Test
	public void testGetFechaValido() {
		Comanda c = new Comanda("a");
		LocalDate lc = LocalDate.now();
		
		assertEquals(lc, c.getFecha());
	}
	
	@Test
	public void testGetHoraValido() {
		Comanda c = new Comanda("a");		
		assertEquals(LocalTime.now(), c.getHora());
	}
	 
}
