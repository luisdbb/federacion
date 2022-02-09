package entidades;

import java.util.Scanner;

import validaciones.Validaciones;

public class Competicion {
	private long id;
	private String nombre;
	private int anio;
	private Prueba[] pruebas;

	public Competicion(long id, String nombre, int anio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
	}

	public Competicion(long id, String nombre, int anio, Prueba[] pruebas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.pruebas = pruebas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		String ret = "";
		ret += nombre + " (año " + anio + ")";
		for (Prueba p : this.pruebas) {
			ret += "* " + p + "\n";
		}
		return ret;
	}

	public Prueba[] getPruebas() {
		return pruebas;
	}

	public void setPruebas(Prueba[] pruebas) {
		this.pruebas = pruebas;
	}

	//Examen 1 Ejercicio 2, parte A
	public static Competicion nuevaCompeticion() {
		Competicion ret = null;
		Scanner in;
		int id = -1;
		int anio = -1;
		String nombre = "";
		boolean valido = false;
		do {
			System.out.println("Introduzca el id de la nueva competicion:");
			in = new Scanner(System.in);
			id = in.nextInt();
			valido = Validaciones.validarId(id);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el identificador.");
			else
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el nombre de la nueva competicion:");
			in = new Scanner(System.in);
			nombre = in.nextLine();
			valido = Validaciones.validarNombre(nombre);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el nombre.");
			else
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el anio de la nueva competicion:");
			in = new Scanner(System.in);
			anio = in.nextInt();
			valido = Validaciones.validarAnio(anio);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el año.");
			else
				valido = true;
		} while (!valido);
		valido = false;
		int npruebas = 0;
		do {
			System.out.println("Introduzca el nº de pruebas de la nueva competicion:");
			in = new Scanner(System.in);
			npruebas = in.nextInt();
			if (npruebas > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el nº de pruebas.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos de las " + npruebas + " pruebas de la nueva competición:");
		Prueba[] pruebas = new Prueba[npruebas];
		for (int i = 0; i < npruebas; i++) {
			System.out.println("Introduzca los datos de la " + (i + 1) + "ª prueba de la nueva competición.");
			pruebas[i] = Prueba.nuevaPrueba();
		}
		ret = new Competicion(id, nombre, anio, pruebas);
		return ret;
	}

}
