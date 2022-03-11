package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;
	private String nombre;

	public Equipo() {
		super();
	}

	public Equipo(long id, String nombre, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.nombre = nombre;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.nombre = e.nombre;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}

	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}

	public int getAnioinscripcion() {
		return anioinscripcion;
	}

	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Atleta[] getAtletas() {
		return atletas;
	}

	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Examen 1 Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret += "EQ" + idEquipo + " ("+nombre+") de " + manager.getPersona().getNombre() + " (" + manager.getDireccion() + ") "
				+ atletas.length + " componentes en el equipo:\n";
		for (Atleta a : atletas) {
			ret += a.getId() + ". " + a.getPersona().getNombre() + "("
					+ a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") "
					+ " Datos físicos:\t" + a.getPeso() + "Kgs.\t" + a.getAltura() + "m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}

	/*
	 * Equipo tiene 1 Mánager obligatoriamente y de 3 a 5 Atletas
	 */
	///Examen 8 Ejercicio 1 parte A
	public static Equipo nuevoEquipo() {
		Equipo ret = new Equipo();
		boolean valido = false;
		String nombre = "";
		long id = 0;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Introduzca el nombre del nuevo equipo:");
			nombre = in.nextLine();
			valido = Validaciones.validarNombreEquipo(nombre);
			if (!valido)
				System.out.println("El nombre " + nombre + " no es válido.");
			else {
				System.out.println("¿Es correcto el nombre elegido:" + nombre + "?");
				valido = Utilidades.leerBoolean();
			}
		} while (!valido);
		valido = false;
		int anio = LocalDate.now().getYear();
		Manager manager = Manager.nuevoManager();
		boolean resp = true;
		HashSet<Atleta> atletas = new HashSet<Atleta>();
		System.out.println("Introduzca los datos de los atletas del equipo (entre 3 y 5)");
		for(int i=1; resp; i++) {
			System.out.println("Introduzca datos del Atleta "+i+":");
			Atleta a = Atleta.nuevoAtleta();
			atletas.add(a);
			if(atletas.size()>=3) {
				System.out.println("Ya tendría un equipo válido.");
				if(atletas.size()<5) {
					System.out.println("¿Desea introducir otro atleta al equipo?");
					resp= Utilidades.leerBoolean();
				}
				else
					System.out.println("Ya ha completado el equipo. No puede añadir más atletas.");
				resp = false;
			}
		}
		Atleta[] atletasArray = new Atleta[atletas.size()];
		atletas.toArray(atletasArray);
		ret = new Equipo(id, nombre, anio, manager, atletasArray);
		return ret;
	}

}
