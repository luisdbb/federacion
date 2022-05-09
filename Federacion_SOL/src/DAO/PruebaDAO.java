package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Prueba;
import utils.ConexBD;

public class PruebaDAO implements operacionesCRUD<Prueba> {
	Connection conex;

	public PruebaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Prueba elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Prueba p) {
		long ret = -1;

		String consultaInsertStr = "insert into pruebas(nombre, fecha, idlugar, individual, idpatrocinador) values (?,?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setString(1, p.getNombre());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(p.getFecha());
			pstmt.setDate(2, fechaSQL);
			pstmt.setLong(3, p.getLugar().getId());
			pstmt.setBoolean(4, p.isIndividual());
			pstmt.setLong(5, p.getPatrocinador().getId());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM pruebas WHERE (nombre=? AND idlugar=? " + "AND individual=? AND idpatrocinador=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, p.getNombre());
				pstmt2.setLong(2, p.getLugar().getId());
				pstmt2.setInt(3, (p.isIndividual()? 1 : 0));
				pstmt2.setLong(4, p.getPatrocinador().getId());
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

	@Override
	public Prueba buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Prueba> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Prueba elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Prueba elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
