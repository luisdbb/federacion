package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Manager;
import utils.ConexBD;
import utils.Datos;

public class ManagerDAO implements operacionesCRUD<Manager>{

	/// Examen 10 ejercicio 11
		@Override
		public boolean insertarConID(Manager m) {
			boolean ret = false;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "insert into managers(id, idpersona, telefono, direccion) values (?,?,?,?)";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

				pstmt.setLong(1, m.getId());
				pstmt.setLong(2, m.getPersona().getId());
				pstmt.setString(3, m.getTelefono());
				pstmt.setString(4, m.getDireccion());
				int resultadoInsercion = pstmt.executeUpdate();
				ret = (resultadoInsercion == 1);
			} catch (SQLException e) {
				System.out.println("Se ha producido una SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			return ret;
		}

		/// Examen 10 ejercicio 11
		@Override
		public long insertarSinID(Manager m) {
			long ret = -1;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "insert into managers(idpersona, telefonoprof, direccion) values (?,?,?)";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
				pstmt.setLong(1, m.getPersona().getId());
				pstmt.setString(2, m.getTelefono());
				pstmt.setString(3, m.getDireccion());
				int resultadoInsercion = pstmt.executeUpdate();
				if (resultadoInsercion == 1) {
					String consultaSelect = "SELECT id FROM managers WHERE (idpersona=? AND telefono=? "
							+ "AND direccion=?)";
					PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
					pstmt2.setLong(1, m.getPersona().getId());
					pstmt2.setString(2, m.getTelefono());
					pstmt2.setString(3, m.getDireccion());
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

		/// Examen 10 ejercicio 11
		@Override
		public Manager buscarPorID(long id) {
			Manager ret = null;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "select * FROM managers WHERE id=?";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
				pstmt.setLong(1, id);
				ResultSet result = pstmt.executeQuery();
				while (result.next()) {
					long idBD = result.getLong("id");
					long idPersona = result.getLong("idpersona");
					String telefono = result.getString("telefono");
					String direccion = result.getString("direccion");
					ret = new Manager();
					ret.setId(idBD);
					ret.setTelefono(telefono);
					ret.setDireccion(direccion);
					ret.setPersona(Datos.buscarPersonaPorId(idPersona));
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
	public Collection<Manager> buscarTodos() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public boolean modificar(Manager elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Manager elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
