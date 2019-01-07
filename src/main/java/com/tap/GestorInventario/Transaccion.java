package com.tap.GestorInventario;

public class Transaccion {
	
	String codTrans;
	String fecha;
	double coste;
	double beneficioAct;
	
	
	public double getBeneficioAct() {
		return beneficioAct;
	}
	public void setBeneficioAct(double beneficioAct) {
		this.beneficioAct = beneficioAct;
	}
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
