package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import entidades.Patrocinador;
import entidades.Responsable;
import utils.ConexBD;

public class PatrocinadorDAO implements operacionesCRUD<Patrocinador> {

	Connection conex;

	public PatrocinadorDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	/// Examen 10 ejercicio 9
	@Override
	public boolean insertarConID(Patrocinador p) {
		/// Lo primero sería insertar los datos del responsable ANTES de la inserción en
		/// la tabla patrocinadores
		Responsable aux = new Responsable();
		ResponsableDAO rDAO = new ResponsableDAO(this.conex);
		long idresp = p.getResponsable().getId();
		if (idresp < 1)
			idresp = rDAO.insertarSinID(p.getResponsable());
		else if (rDAO.insertarConID(p.getResponsable()))
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
		ResponsableDAO rDAO = new ResponsableDAO(this.conex);
		long idresp = p.getResponsable().getId();
		if (idresp < 1)
			idresp = rDAO.insertarSinID(p.getResponsable());
		else if (rDAO.insertarConID(p.getResponsable()))
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
				ResponsableDAO rDAO = new ResponsableDAO(conex);
				ret.setResponsable(rDAO.buscarPorID(idResponsable));
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

	@Override
	public Collection<Patrocinador> buscarTodos() {
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
				ResponsableDAO rDAO = new ResponsableDAO(conex);
				p.setResponsable(rDAO.buscarPorID(idResponsable));
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

	@Override
	public boolean modificar(Patrocinador elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Patrocinador elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}
	
	/***
	 * Muestra todos los Patrocinadores desde la BD y pide al usuario que elija uno
	 * de ellos, que es el que se devuelve
	 * 
	 * @return
	 */
	public Patrocinador seleccionarUnoYaExistente() {
		Patrocinador ret = null;
		List<Patrocinador> todos = (List<Patrocinador>) buscarTodos();
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
			ret = buscarPorID(seleccion);
			correcto = (ret != null);
			if (!correcto)
				System.out.println("ERROR: el valor seleccionado para el patrocinador es inválido.");
			else
				correcto = true;
		} while (!correcto);

		return ret;
	}
}
