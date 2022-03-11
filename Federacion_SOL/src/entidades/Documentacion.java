package entidades;

//Examen 2 Ejercicio 3.1
public abstract class Documentacion implements Comparable<Documentacion>{
	public abstract String mostrar();
	public abstract boolean validar();
	
	///Examen 8 Ejercicio 3
	public int compareTo(Documentacion d) {
		return this.mostrar().compareTo(d.mostrar());
	}
	
}
