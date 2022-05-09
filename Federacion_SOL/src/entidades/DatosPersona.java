package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import comparadores.ComparadorAlfabetico;
import utils.*;
import validaciones.Validaciones;

public class DatosPersona implements Comparable<DatosPersona>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String telefono;
	private LocalDate fechaNac;

	private Documentacion nifnie; // Examen 2 Ejercicio 3.2

	public DatosPersona(long id, String nombre, String telefono, LocalDate fechaNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
	}

	// Examen 2 Ejercicio 3.2
	public DatosPersona(long id, String nombre, String telefono, LocalDate fechaNac, Documentacion nifnie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.nifnie = nifnie;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Documentacion getNifnie() {
		return nifnie;
	}

	public void setNifnie(Documentacion nifnie) {
		this.nifnie = nifnie;
	}

	@Override
	public String toString() {
		return nombre + " NIF/NIE: " + nifnie.mostrar() + " Tfn:" + telefono + " ("
				+ fechaNac.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
	}

	// Examen 2 Ejercicio 3.3
	// Examen 5 Ejercicio 3
	public static DatosPersona nuevaPersona() {
		DatosPersona ret = null;
		Scanner in;
		long id = -1;
		String nombre = "";
		String tfn = "";
		boolean valido = false;
		do {
			System.out.println("Introduzca el id de la nueva persona:");
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
			System.out.println("Introduzca el nombre de la nueva persona:");
			in = new Scanner(System.in);
			nombre = in.nextLine();
			valido = Validaciones.validarNombre(nombre);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el nombre no es válido. ");
		} while (!valido);
		do {
			System.out.println("Introduzca el teléfono de la nueva persona:");
			in = new Scanner(System.in);
			tfn = in.nextLine();
			valido = Validaciones.validarTelefono(tfn);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teléfono no es válido. ");
		} while (!valido);
		System.out.println("Introduzca la fecha de nacimiento de la nueva persona");
		LocalDate fecha = Utilidades.leerFecha();
		System.out.println("¿Va a introducir un NIF? (pulse 'S' par SÍ o 'N' para NIE)");
		boolean esnif = Utilidades.leerBoolean();
		Documentacion doc;
		valido = false;
		do {
			if (esnif)
				doc = NIF.nuevoNIF();
			else
				doc = NIE.nuevoNIE();
			valido = doc.validar();
			if (!valido)
				System.out.println("ERROR: El documento introducido no es válido.");
		} while (!valido);
		ret = new DatosPersona(id, nombre, tfn, fecha, doc);
		return ret;
	}

	/// Examen 9 Ejercicio 1-A
	public String data() {
		String ret = "";
		ret += "" + this.getId() + "|" + this.getNombre() + "|" + this.getTelefono() + "|"
				+ this.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "|"
				+ this.getNifnie().mostrar();
		return ret;
	}

	/// Examen 9 Ejercicio 1-C
	public static void exportarAtletasAlfabetico() {
		File f = new File("atletas_alfabetico.txt");
		FileWriter fo = null;
		/// Utilizamos una lista para tomar primeramente los datos desde la clase
		/// Datos.java
		List<DatosPersona> personas = new LinkedList<DatosPersona>();
		for (DatosPersona dp : Datos.PERSONAS) {
			personas.add(dp);
		}
		/// Se ordena la lista según el ComparadorAlfabetico
		Collections.sort(personas, new ComparadorAlfabetico());
		try {
			fo = new FileWriter(f);
			/// Se recorre cada elemento de la lista ya ordenada y se exporta hacia el
			/// fichero de caracteres, una persona en cada línea y a través del método
			/// DatosPersona.data
			for (DatosPersona dp : personas) {
				fo.write(dp.data() + "\n");
				fo.flush();
			}
			fo.close();
			System.out.println("Se han exportado correctamente los datos de las personas.");
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/// Examen 9 ejercicio 2-A
	@Override
	public int compareTo(DatosPersona o2) {
		// si la fecha_nac de this es posterior a la de o2, entonces this es menor (en
		// edad) que o2
		if (this.getFechaNac().isAfter(o2.getFechaNac()))
			return -1;
		else
		// si la fecha_nac de this es posterior a la de o2, entonces this es menor (en
		// edad) que o2
		if (this.getFechaNac().isBefore(o2.getFechaNac()))
			return 1;
		else {
			// si la fecha_nac de this la misma de o2, entonces se desempata en funcion del
			// campo Documentacion
			return this.getNifnie().compareTo(o2.getNifnie());
		}
		/// Otra forma más sencilla sería esta:
		//return this.getFechaNac().compareTo(o2.getFechaNac());
	}

	/// Examen 9 ejercicio 2-B
	public static boolean insertarPersonas() {
		boolean ret = false;
		String consultaInsertStr1 = "insert into personas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		try {
			Connection conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);

			List<DatosPersona> personas = new LinkedList<>();
			for (DatosPersona dp : Datos.PERSONAS) {
				personas.add(dp);
			}
			Collections.sort(personas);
			Iterator<DatosPersona> it = personas.iterator();
			while (it.hasNext()) {
				DatosPersona dp = (DatosPersona) it.next();
				pstmt.setLong(1, dp.getId());
				pstmt.setString(2, dp.getNombre());
				pstmt.setString(3, dp.getTelefono());
				java.sql.Date fechaSQL = java.sql.Date.valueOf(dp.getFechaNac());
				pstmt.setDate(4, fechaSQL);
				pstmt.setString(5, dp.getNifnie().mostrar());
				int resultadoInsercion = pstmt.executeUpdate();
				ret = (resultadoInsercion != 0);
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			ret = false;
		}

		return ret;
	}

}
