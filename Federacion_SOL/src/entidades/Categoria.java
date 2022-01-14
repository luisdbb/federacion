package entidades;

public enum Categoria {
	JUNIOR("Junior", 'J'), SENIOR("Senior", 'S'), ESPECIAL("Especial", 'E');

	String nombre;
	char abreviatura;

	Categoria(String nombre, char abre) {
		this.nombre = nombre;
		this.abreviatura = abre;
	}

	public String getNombre() {
		return nombre;
	}

	public char getAbreviatura() {
		return abreviatura;
	}

	public static void mostrarTodos() {
		int i = 1;
		for (Categoria c : Categoria.values()) {
			System.out.println(i + " " + c.getAbreviatura() + "-" + c.getNombre());
			i++;
		}
	}

	public void mostrar() {
		System.out.println("(" + abreviatura + ")" + nombre);
	}

}
