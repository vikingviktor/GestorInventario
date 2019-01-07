package com.tap.GestorInventario;

public class Transaccion {
	
	String codTrans;
	String fecha;
	double coste;
	
	
	public double getCoste() {
		return coste;
	}
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public String getCodTrans() {
		return codTrans;
	}
	public void setCodTrans(String codTrans) {
		this.codTrans = codTrans;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

}
