package entidades;

import java.time.LocalDate;

//Examen 1 Ejercicio 5, parte A
public abstract class Metal {
	public abstract float maximaPurezaAlcanzada();
	public abstract float[] cotasPurezaEfectiva();
	public abstract float getPureza(); ////Examen 8 Ejercicio 2
	
	public LocalDate fecha;
	public boolean asignada = false;
	
	public Metal() {
	}
	
	public Metal(LocalDate f, boolean b) {
		this.fecha = f;
		this.asignada = b;
	}
	
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public boolean isAsignada() {
		return asignada;
	}
	public void setAsignada(boolean asignada) {
		this.asignada = asignada;
	}
	
}
