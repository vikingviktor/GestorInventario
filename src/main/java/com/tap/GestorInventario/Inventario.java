package com.tap.GestorInventario;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
	
	private List<Producto> productos;
	
	private List<Transaccion> transacciones;
	
	Producto palo = new Producto("Palo", "1");
	Producto pluma = new Producto("Pluma", "2.5");
	Producto piedra = new Producto("Piedra", "2");
	Producto hierro = new Producto("Hierro", "3");
	Producto cuerda = new Producto("Cuerda", "1.5");
	Producto flecha = new Producto("Flecha", "10");
	Producto espada = new Producto("Espada", "20");
	Producto arco = new Producto("Arco", "15");
	
	String a;
	int n;
	double presupuesto;

	public double getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}


	public Inventario() {
		super();
		productos = new ArrayList<>();
		transacciones = new ArrayList<>();
		addProduct(palo);
		addProduct(pluma);
		addProduct(piedra);
		addProduct(hierro);
		addProduct(cuerda);
		addProduct(flecha);
		addProduct(espada);
		addProduct(arco);
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
	
	public void addTransaction(Transaccion t) {
		
		transacciones.add(t);
		
	}
	
	public List<Transaccion> getTransactions() {
		return transacciones;
	}
	
	public void addTransaction () {
		
		
	}
	
	
	public List<Producto> addUnit(
			  String name, List<Producto> prods) {
			 
			    for (Producto prod : prods) {
			        if (prod.getName().equals(name)) {
			        	prod.setNumber(prod.getNumber()+1);
			            return prods;
			        }
			    }
			    return null;
	}
	
	public List<Producto> substractUnit(
			  String name, List<Producto> prods) {
			 
			    for (Producto prod : prods) {
			        if (prod.getName().equals(name)) {
			        	prod.setNumber(prod.getNumber()-1);
			            return prods;
			        }
			    }
			    return null;
	}
	
	public Producto getUnit(
			  String name, List<Producto> prods) {
			 
			    for (Producto prod : prods) {
			        if (prod.getName().equals(name)) {
			            return prod;
			        }
			    }
			    return null;
	}
	
	public void actualizarProductos(List<Producto> prods) {
		
		this.productos = prods;
	}

}