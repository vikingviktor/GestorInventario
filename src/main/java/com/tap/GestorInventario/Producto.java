package com.tap.GestorInventario;

public class Producto {

	private String name;
	private String valueEur;
	private double valueDol;
	private int number = 0;
	
	public Producto(String name, String number1) {
		super();
		this.name = name;
		this.valueEur = number1;
		this.valueDol = 1.2*Double.parseDouble(number1);
	}
	
	
	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValueEur() {
		return valueEur;
	}

	public void setValueEur(String valueEur) {
		this.valueEur = valueEur;
	}

	public double getValueDol() {
		return valueDol;
	}
	
	
	public void eliminarProducto(Producto prod) {
		prod.setNumber(prod.getNumber()-1);
	}
	
	public void aniadirProducto(Producto prod) {
		prod.setNumber(prod.getNumber()+1);
	}
	
}