package entidades;

//Examen 4 Ejercicio 2
public enum Rol {
	DIRECTIVA("Directiva de la Federación"), 
	MANAGER("Mánager"), 
	ATLETA("Atleta"), 
	COLEGIADO("Colegiado"),
	ADMIN("Administrador"), 
	INVITADO("Invitado");

	String nombre;

	Rol(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
}
