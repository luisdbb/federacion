package entidades;

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
