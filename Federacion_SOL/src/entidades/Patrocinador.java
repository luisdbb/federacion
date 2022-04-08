package entidades;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.*;
import validaciones.Validaciones;

///Examen 10 ejercicio 0
public class Patrocinador implements Comparable<Patrocinador>, Serializable, operacionesCRUD<Patrocinador> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6508415087938400684L;
	private long id;
	/*
	 * nombre: cadena de caracteres de entre 3 y 150 caracteres, siendo válidos los
	 * alfabéticos (letras) o numéricos (dígitos) solamente
	 */
	private String nombre;
	/*
	 * web: URL con la cadena de caracteres de la web del patrocinador (si la hay).
	 */
	private String web;
	/*
	 * dotacion: valor de la dotación en euros que realiza para patrocinar la/s
	 * prueba/s. Es obligatorio
	 */
	private double dotacion;
	private Responsable responsable;

	public Patrocinador() {
	}

	public Patrocinador(Patrocinador p) {
		super();
		this.id = p.id;
		this.nombre = p.nombre;
		this.web = p.web;
		this.dotacion = p.dotacion;
		this.responsable = p.responsable;
	}

	public Patrocinador(long id, String nombre, String web, double dotacion, Responsable r) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.web = web;
		this.dotacion = dotacion;
		this.responsable = r;
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

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public double getDotacion() {
		return dotacion;
	}

	public void setDotacion(double dotacion) {
		this.dotacion = dotacion;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	/// Examen 10 ejercicio 2
	public static Patrocinador nuevoPatrocinador() {
		Patrocinador ret = null;
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		long id = 0;
		String nombre = "";
		String web = "";
		double dotacion = 0.0;

		do {
			System.out.println(
					"Introduzca el nombre del nuevo patrocinador (entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
			nombre = in.nextLine();
			valido = Validaciones.validarNombrePatrocinador(nombre);
			if (!valido) {
				System.out.println(
						"El valor introducido para el nombre del patrocinador (debe ser  entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
				continue;
			} else
				valido = true;
		} while (!valido);
		valido = false;

		System.out.println("¿Desea introducir la url de la web del nuevo patrocinador?");
		boolean confirmacion = Utilidades.leerBoolean();
		if (confirmacion) {
			do {
				System.out.println(
						"Introduzca la URL de la web del nuevo patrocinador (entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
				web = in.nextLine();
				valido = Validaciones.validarWebPatrocinador(web);
				if (!valido) {
					System.out.println("El valor introducido para la web del patrocinador es inválido.");
					continue;
				} else
					valido = true;
			} while (!valido);
		}
		valido = false;
		do {
			System.out.println("Introduzca la dotacion en euros del nuevo patrocinador:");
			dotacion = Utilidades.leerDouble();
			valido = Validaciones.validarDotacion(dotacion);
			if (!valido) {
				System.out.println("El valor introducido para la dotacion no es correcta (debe ser mayor que 100):");
				continue;
			} else
				valido = true;
		} while (!valido);

		System.out.println("Introduzca los datos del responsable del nuevo patrocinador:");
		Responsable responsable = Responsable.nuevoResponsable();
		ret = new Patrocinador(id, nombre, web, dotacion, responsable);
		return ret;
	}

	/// Examen 10 ejercicio 3C
	/***
	 * Funcion que devuelve una cadena de caracteres con los datos básicos de un
	 * patrocinador: idPatrocinador + nombre + web (si la hay).
	 * 
	 * @return la cadena formateada
	 */
	public String mostrarBasico() {
		String ret = "";
		ret += this.id + ". " + this.nombre + (!this.web.equals("") ? " " + web : " ");
		return ret;
	}

	/// Examen 10 ejercicio 3C
	/**
	 * Funcion que devuelve una cadena de caracteres con los datos de un
	 * patrocinador al completo: idPatrocinador + nombre + web (si la hay) +
	 * dotación en euros (xx.xx euros) + los datos del responsable
	 */
	public String mostrarCompleto() {
		String ret = mostrarBasico();
		ret += Utilidades.mostrarDouble2Decimales(dotacion) + "euros aportados\t";
		ret += "Responsable: " + this.responsable.toString();
		return ret;
	}

	/// Examen 10 Ejercicio 5
	@Override
	public int compareTo(Patrocinador o) {
		/// El primer criterio de ordenación es la cantidad datada por el Patrocinador
		int comparar1 = Double.compare(this.dotacion, o.dotacion);
		if (comparar1 == 0) {
			/// Si hay un primer empate (misma dotacion), se desempata por el rango horario
			long rangoThis = ChronoUnit.MINUTES.between(this.getResponsable().getHorarioFin(),
					this.getResponsable().getHorarioIni());
			long rangoO = ChronoUnit.MINUTES.between(o.getResponsable().getHorarioFin(),
					o.getResponsable().getHorarioIni());
			int comparar2 = Long.compare(rangoThis, rangoO);
			if (comparar2 == 0) {
				/// Si se tiene el mismo rango horario para los 2 Patrocinadores, se desempata
				/// por su IDPatrocinador
				return Long.compare(this.getId(), o.getId());
			} else
				return comparar2;
		} else
			return comparar1;
	}

	/// Examen 10 Ejercicio 8A
	/***
	 * funcion que devuelve una cadena de caracteres de la forma siguiente:
	 * <idPatrocinador> | <idRepresentante> | <nombre> | <dotacion> | <web>
	 * 
	 * @return
	 */
	public String data() {
		String ret = "";
		ret += "" + this.getId() + "|" + this.getResponsable().getId() + "|" + this.getNombre() + "|"
				+ this.getDotacion() + "|" + this.getWeb();
		return ret;
	}

	/// Examen 10 ejercicio 9
	@Override
	public boolean insertarConID(Patrocinador p) {
		/// Lo primero sería insertar los datos del responsable ANTES de la inserción en
		/// la tabla patrocinadores
		Responsable aux = new Responsable();
		long idresp = p.getResponsable().getId();
		if (idresp < 1)
			idresp = aux.insertarSinID(p.getResponsable());
		else if (aux.insertarConID(p.getResponsable()))
			idresp = p.getResponsable().getId();
		if (idresp != -1) {
			p.getResponsable().setId(idresp);
		} else {
			System.out.println("Hubo un error al insertar el responsable del patrocinador. Operación abortada.");
			return false;
		}
		boolean ret = false;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into patrocinadores(id, idresponsable, nombre, web, dotacion) values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

			pstmt.setLong(1, p.getId());
			pstmt.setLong(2, p.getResponsable().getId());
			pstmt.setString(3, p.getNombre());
			pstmt.setString(4, p.getWeb());
			pstmt.setDouble(5, p.getDotacion());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public long insertarSinID(Patrocinador p) {
		/// Lo primero sería insertar los datos del responsable ANTES de la inserción en
		/// la tabla patrocinadores
		Responsable aux = new Responsable();
		long idresp = p.getResponsable().getId();
		if (idresp < 1)
			idresp = aux.insertarSinID(p.getResponsable());
		else if (aux.insertarConID(p.getResponsable()))
			idresp = p.getResponsable().getId();
		if (idresp != -1) {
			p.getResponsable().setId(idresp);
		} else {
			System.out.println("Hubo un error al insertar el responsable del patrocinador. Operación abortada.");
			return -1;
		}
		long ret = -1;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into patrocinadores(idresponsable, nombre, web, dotacion) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

			pstmt.setLong(1, p.getResponsable().getId());
			pstmt.setString(2, p.getNombre());
			pstmt.setString(3, p.getWeb());
			pstmt.setDouble(4, p.getDotacion());

			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM patrocinadores WHERE (idresponsable=? AND nombre=? "
						+ "AND web=? AND dotacion=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setLong(1, p.getResponsable().getId());
				pstmt2.setString(2, p.getNombre());
				pstmt2.setString(3, p.getWeb());
				pstmt2.setDouble(4, p.getDotacion());
				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long id = result.getLong("id");
					if (id != -1)
						ret = id;
				}
				result.close();
				pstmt2.close();
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

	/// Examen 10 ejercicio 10
	@Override
	public Patrocinador buscarPorID(long id) {
		Patrocinador ret = null;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "select * FROM patrocinadores WHERE id=?";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				long idResponsable = result.getLong("idresponsable");
				String nombre = result.getString("nombre");
				String web = result.getString("web");
				double dotacion = result.getDouble("dotacion");
				ret = new Patrocinador();
				ret.setId(idBD);
				ret.setNombre(nombre);
				ret.setWeb(web);
				ret.setDotacion(dotacion);
				Responsable aux = new Responsable();
				ret.setResponsable(aux.buscarPorID(idResponsable));
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public static List<Patrocinador> buscarTodos() {
		List<Patrocinador> ret = new ArrayList<Patrocinador>();
		Connection conex = ConexBD.establecerConexion();
		String consultaSelect = "select * FROM patrocinadores";
		try {
			Statement pstmt = conex.createStatement();
			ResultSet result = pstmt.executeQuery(consultaSelect);
			while (result.next()) {
				long idBD = result.getLong("id");
				long idResponsable = result.getLong("idresponsable");
				String nombre = result.getString("nombre");
				String web = result.getString("web");
				double dotacion = result.getDouble("dotacion");
				Patrocinador p = new Patrocinador();
				p.setId(idBD);
				p.setNombre(nombre);
				p.setWeb(web);
				p.setDotacion(dotacion);
				Responsable aux = new Responsable();
				p.setResponsable(aux.buscarPorID(idResponsable));
				ret.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public static Patrocinador seleccionarUnoYaExistente() {
		Patrocinador ret = null;
		List<Patrocinador> todos = Patrocinador.buscarTodos();
		if (todos.size() == 0) {
			System.out.println(
					"Actualmente no hay datos de patrocinadores en la BD. Introduzca los datos de un nuevo patrocinador:");
			ret = Patrocinador.nuevoPatrocinador();
			return ret;
		}
		boolean correcto = false;
		Scanner in;
		do {
			in = new Scanner(System.in);
			System.out.println("Seleccione el id del Patrocinador de entre los siguientes:");
			for (Patrocinador p : todos) {
				System.out.println(p.mostrarBasico());
			}
			int seleccion = -1;
			try {
				seleccion = in.nextInt();
			} catch (Exception e) {
				System.out.println(
						"Se produjo una excepcion al introducir el idPatrocinador seleccionado." + e.getMessage());
				e.printStackTrace();
			}
			ret = ret.buscarPorID(seleccion);
			correcto = (ret != null);
			if (!correcto)
				System.out.println("ERROR: el valor seleccionado para el patrocinador es inválido.");
			else
				correcto = true;
		} while (!correcto);

		return ret;
	}
}
