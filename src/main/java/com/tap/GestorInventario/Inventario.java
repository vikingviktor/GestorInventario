package com.tap.GestorInventario;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
	
	private List<Producto> productos;
	
	Producto palo = new Producto("Palo", "1");
	Producto pluma = new Producto("Pluma", "2.5");
	Producto piedra = new Producto("Piedra", "2");
	
	String a;
	int n;
	int presupuesto;

	public Inventario() {
		super();
		productos = new ArrayList<>();
		addProduct(palo);
		addProduct(pluma);
		addProduct(piedra);
	}
	
	
	public void addProduct(Producto p) {
		
		productos.add(p);
		
	}
	
	public void deleteProduct(Producto p)
	{
		productos.remove(p);
	}
	
	public List<Producto> getProducts() {
		return productos;
	}
	
	public void addTransaction () {
		
		
	}

}