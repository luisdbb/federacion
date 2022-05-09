package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import utils.*;
import validaciones.Validaciones;

///Examen 10 Ejercicio 0
public class Responsable implements Serializable, Comparable<Responsable> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5370897726947229725L;
	private long id;
	/*
	 * telefonoProf: teléfono profesional, que es una cadena de caracteres de 9 o 10
	 * dígitos. Es obligatorio
	 */
	private String telefonoProf;
	/*
	 * horarioIni-horarioFin: La franja horaria del día en la que puede atender
	 * llamadas, con su hora de inicio y de fin
	 */
	private java.time.LocalTime horarioIni;
	private java.time.LocalTime horarioFin;
	private DatosPersona persona;

	public Responsable() {
	}

	public Responsable(Responsable r) {
		this.id = r.id;
		this.telefonoProf = r.telefonoProf;
		this.horarioFin = r.horarioFin;
		this.horarioIni = r.horarioIni;
		this.persona = r.persona;
	}

	public Responsable(String telefonoProf, LocalTime horarioIni, LocalTime horarioFin, DatosPersona persona) {
		super();
		this.telefonoProf = telefonoProf;
		this.horarioIni = horarioIni;
		this.horarioFin = horarioFin;
		this.persona = persona;
	}

	public Responsable(long id, String telefonoProf, LocalTime horarioIni, LocalTime horarioFin, DatosPersona persona) {
		super();
		this.id = id;
		this.telefonoProf = telefonoProf;
		this.horarioIni = horarioIni;
		this.horarioFin = horarioFin;
		this.persona = persona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefonoProf() {
		return telefonoProf;
	}

	public void setTelefonoProf(String telefonoProf) {
		this.telefonoProf = telefonoProf;
	}

	public java.time.LocalTime getHorarioIni() {
		return horarioIni;
	}

	public void setHorarioIni(java.time.LocalTime horarioIni) {
		this.horarioIni = horarioIni;
	}

	public java.time.LocalTime getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(java.time.LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}

	public DatosPersona getPersona() {
		return persona;
	}

	public void setPersona(DatosPersona persona) {
		this.persona = persona;
	}

	/// Examen 10 Ejercicio 2
	public static Responsable nuevoResponsable() {
		Responsable ret = null;
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		long id = 0;
		String tfnProf = "";
		java.time.LocalTime horaini;
		java.time.LocalTime horafin;
		do {
			System.out.println("Introduzca el telefono profesional del nuevo responsable (9 o 10 dígitos):");
			tfnProf = in.nextLine();
			valido = Validaciones.validarTelefonoProf(tfnProf);
			if (!valido) {
				System.out.println(
						"El valor introducido para el telefono profesional no es correcto (debe ser de 9 o 10 dígitos solamente):");
				continue;
			} else
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca la hora de inicio de la franja de atención para el nuevo responsable:");
			horaini = Utilidades.leerHora();
			System.out.println("Introduzca la hora de fin de la franja de atención para el nuevo responsable:");
			horafin = Utilidades.leerHora();
			valido = Validaciones.validarRangoHorario(horaini, horafin);
			if (!valido) {
				System.out.println("El valor introducido para la franja horaria de atención no es correcta.");
				continue;
			} else
				valido = true;
		} while (!valido);
		System.out.println("Introduzca ahora los datos personales del nuevo representante:");
		DatosPersona dp = DatosPersona.nuevaPersona();
		ret = new Responsable(id, tfnProf, horaini, horafin, dp);
		return ret;
	}

	/// Examen 10 ejercicio 3B
	/*
	 * <idResponsable> + ’.’ + <nombre> + ’(’ + <NIFNIE> + ’)’ + “horario de: ” +
	 * <horaIni(HH:mm)> + “ a ” + <horaFin(HH:mm)> + ” tfno: ” + <telefonoProf>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + "(" + persona.getNifnie().mostrar() + ") , horario de "
				+ horarioIni.format(DateTimeFormatter.ofPattern("HH:mm")) + " a "
				+ horarioFin.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	/// Examen 10 Ejercicio 4
	/***
	 * funcion que devuelve una cadena de caracteres de la forma siguiente:
	 * <idResponsable> | <idPersona> | <telefonoProf> | <horaIni(HH:mm)> |
	 * <horaFin(HH:mm)>
	 * 
	 * @return
	 */
	public String data() {
		String ret = "";
		ret += "" + this.getId() + "|" + this.getPersona().getId() + "|" + this.getTelefonoProf() + "|"
				+ this.getHorarioIni().format(DateTimeFormatter.ofPattern("HH:mm")) + "|"
				+ this.getHorarioFin().format(DateTimeFormatter.ofPattern("HH:mm"));
		return ret;
	}

	/// Examen 10 ejercicio 7
	/***
	 * Funcion que importa desde el fichero de caracteres responsables.txt una serie
	 * de datos de Responsables, con los que se conforma un conjunto que se ordena
	 * por valor creciente de idResponsable. Luego itera sobre cada elemento del
	 * conjunto y muestra los datos por la salida estándar
	 * 
	 */
	public static void importarResponsables() {
		String path = "responsables.txt";
		FileReader lector = null;
		File fichero = new File(path);
		BufferedReader buffer = null;
		TreeSet<Responsable> responsables = new TreeSet<Responsable>();
		try {
			lector = new FileReader(fichero);
			buffer = new BufferedReader(lector);
			String linea;
			while ((linea = buffer.readLine()) != null) {
				String[] campos = linea.split("\\|");
				/// El método Responsable.data() devuelve la cadena así:
				/// <idResponsable> | <idPersona> | <telefonoProf> | <horaIni(HH:mm)> |
				/// <horaFin(HH:mm)>
				long idResp = Long.valueOf(campos[0]);
				long idPersona = Long.valueOf(campos[1]);
				String tfnProf = campos[2];
				
				String horainiStr = campos[3];
				String[] horaAux = horainiStr.split("\\:");
				java.time.LocalTime horaini = LocalTime.of(Integer.valueOf(horaAux[0]), Integer.valueOf(horaAux[1]));
				
				String horafinStr = campos[4];
				horaAux = horafinStr.split("\\:");
				java.time.LocalTime horafin = LocalTime.of(Integer.valueOf(horaAux[0]), Integer.valueOf(horaAux[1]));
				DatosPersona dp = Datos.buscarPersonaPorId(idPersona);
				Responsable r = new Responsable(idResp, tfnProf, horaini, horafin, dp);
				responsables.add(r); /// Se autoordena en cada inserción
			}
			buffer.close();
			lector.close();

			System.out.println(
					"Estos son los responsables importados desde el fichero y ordenados por su idResponsable:");
			Iterator<Responsable> it = responsables.iterator();
			while (it.hasNext()) {
				Responsable r = (Responsable) it.next();
				System.out.println(r);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}

	}

	@Override
	public int compareTo(Responsable o) {
		return Long.compare(this.id, o.id);
	}



}
