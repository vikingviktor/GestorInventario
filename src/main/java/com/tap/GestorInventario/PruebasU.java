package com.tap.GestorInventario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;


class PruebasU {

	@Test
	void crearproducto() {
		String nombre;
		String nombre2;
		Producto cemento = new Producto("cemento","3");
		System.out.println("se ha creado el producto: ");
		System.out.println("nombre: "+cemento.getName()+"valor eur: "+cemento.getValueEur()+" valor dol: "+cemento.getValueDol()+" cantidad: "+cemento.getNumber());
		nombre="cemento";
		nombre2=cemento.getName();
		assertEquals(nombre,nombre2);
	}
	
	@Test
	void aniadoproducto() {
		int cantidad;
		int valor;
		Producto cemento = new Producto("cemento","3");
		cemento.aniadirProducto(cemento);
		cantidad=cemento.getNumber();
		valor=1;
		assertEquals(cantidad,valor);
		
	}
	
	@Test
	void eliminarproducto() {
		int cantidad;
		int valor;
		Producto cemento = new Producto("cemento","3");
		cemento.aniadirProducto(cemento);
		cemento.eliminarProducto(cemento);
		cantidad=cemento.getNumber();
		valor=0;
		assertEquals(cantidad,valor);
		
	}
	
	@Test
	void crearInventario() {
		Inventario inventario = new Inventario();
		List<Producto> listaProd;
		listaProd=inventario.getProducts();
		assertNotNull(listaProd);
	}
	@Test
	void aniadirProdInv() {
		Inventario inventario = new Inventario();
		Producto cemento = new Producto("Cemento", "3");
		inventario.addProduct(cemento);
		List<Producto> listaProd;
		listaProd= inventario.getProducts();
		assertTrue(listaProd.contains(cemento));
	}
	
	@Test
	void eliminarProdInv() {
		Inventario inventario = new Inventario();
		Producto cemento = new Producto("Cemento", "3");
		inventario.addProduct(cemento);
		List<Producto> listaProd;
		listaProd= inventario.getProducts();
		inventario.deleteProduct(cemento);
		listaProd= inventario.getProducts();
		assertFalse(listaProd.contains(cemento));
	}
	
	
	@Test
	void aniadirUnidades() {
		int valor;
		int cantidad = 0;
		List<Producto> prods;
		List<Producto> prods2;
		Inventario inventario = new Inventario();
		prods= inventario.getProducts();
		inventario.actualizarProductos(inventario.addUnit("piedra", prods));
		prods2= inventario.getProducts();
	    for (Producto prod : prods2){
	        if (prod.getName().equals("piedra")) {
	        	cantidad=prod.getNumber();
	        }
	    }
	    
	   valor=1;
	   System.out.println("cantidad y valor"+cantidad+"Y"+valor);
	   assertEquals(cantidad,valor);
	}
	
	@Test
	void eliminarUnidades() {
		int valor;
		int cantidad = 0;
		List<Producto> prods;
		List<Producto> prods2;
		List<Producto> prods3;
		Inventario inventario = new Inventario();
		prods=inventario.getProducts();
		inventario.actualizarProductos(inventario.addUnit("piedra", prods));
		prods2=inventario.getProducts();
		inventario.actualizarProductos(inventario.substractUnit("piedra", prods2));
		prods3=inventario.getProducts();
	    for (Producto prod : prods3){
	        if (prod.getName().equals("piedra")) {
	        	cantidad=prod.getNumber();
	        }
	    }
	    
	   valor=0;
	   System.out.println("cantidad y valor"+cantidad+"Y"+valor);
	   assertEquals(cantidad,valor);
	}
	
}
