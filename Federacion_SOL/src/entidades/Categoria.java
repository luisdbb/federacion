package entidades;

//Examen 2 Ejercicio 1
public enum Categoria {
	JUNIOR(1, "Junior", 'J'), SENIOR(2, "Senior", 'S'), ESPECIAL(3, "Especial", 'E');

	private int id;
	String nombre;
	char abreviatura;

	Categoria(int id, String nombre, char abre) {
		this.id = id;
		this.nombre = nombre;
		this.abreviatura = abre;
	}

	public int getId() {
		return this.id;
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
