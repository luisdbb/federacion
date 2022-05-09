package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Lugar;
import utils.ConexBD;

public class LugarDAO implements operacionesCRUD<Lugar> {
	Connection conex;

	public LugarDAO(Connection c) {
		if (this.conex == null)
			conex = c;
	}

	@Override
	public boolean insertarConID(Lugar l) {
		boolean ret = false;

		String consultaInsertStr = "insert into lugares(id, nombre, ubicacion, airelibre) values (?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

			pstmt.setInt(1, l.getId());
			pstmt.setString(2, l.getNombre());
			pstmt.setString(3, l.getUbicacion());
			pstmt.setBoolean(4, l.isAirelibre());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public long insertarSinID(Lugar l) {
		long ret = -1;

		String consultaInsertStr = "insert into lugares(nombre, ubicacion, airelibre) values (?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setString(1, l.getNombre());
			pstmt.setString(2, l.getUbicacion());
			pstmt.setBoolean(3, l.isAirelibre());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM lugares WHERE (nombre=? AND ubicacion=? " + "AND airelibre=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, l.getNombre());
				pstmt2.setString(2, l.getUbicacion());
				pstmt2.setInt(3, (l.isAirelibre() ? 1 : 0));

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
	public Lugar buscarPorID(long id) {
		Lugar ret = null;

		String consultaInsertStr = "select * FROM lugares WHERE id=?";
		try {
			if (this.conex == null || this.conex.isClosed())
				conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				for (Lugar l : Lugar.values())
					if (l.getId() == idBD)
						return l;
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
	public Collection<Lugar> buscarTodos() {
		Collection<Lugar> lugares = new ArrayList<Lugar>();
		String consultaInsertStr = "select * FROM lugares";
		try {
			if (this.conex == null || this.conex.isClosed())
				conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				for (Lugar l : Lugar.values())
					if (l.getId() == idBD)
						lugares.add(l);
			}
			if (this.conex == null)
				conex.close();
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return lugares;
	}

	@Override
	public boolean modificar(Lugar elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Lugar elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
