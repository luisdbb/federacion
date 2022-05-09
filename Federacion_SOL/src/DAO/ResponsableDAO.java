package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Responsable;
import utils.ConexBD;
import utils.Datos;

public class ResponsableDAO implements operacionesCRUD<Responsable>{

	Connection conex;

	public ResponsableDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}
	/// Examen 10 Ejercicio 9
		@Override
		public boolean insertarConID(Responsable r) {
			boolean ret = false;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "insert into responsables(id, idpersona, telefonoprof, horaini, horafin) values (?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

				pstmt.setLong(1, r.getId());
				pstmt.setLong(2, r.getPersona().getId());
				pstmt.setString(3, r.getTelefonoProf());
				pstmt.setTime(4, java.sql.Time.valueOf(r.getHorarioIni()));
				pstmt.setTime(5, java.sql.Time.valueOf(r.getHorarioFin()));
				int resultadoInsercion = pstmt.executeUpdate();
				ret = (resultadoInsercion == 1);

			} catch (SQLException e) {
				System.out.println("Se ha producido una SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			return ret;
		}

		@Override
		public long insertarSinID(Responsable r) {
			long ret = -1;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "insert into responsables(idpersona, telefonoprof, horaini, horafin) values (?,?,?,?)";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

				pstmt.setLong(1, r.getPersona().getId());
				pstmt.setString(2, r.getTelefonoProf());
				pstmt.setTime(3, java.sql.Time.valueOf(r.getHorarioIni()));
				pstmt.setTime(4, java.sql.Time.valueOf(r.getHorarioFin()));
				int resultadoInsercion = pstmt.executeUpdate();
				if (resultadoInsercion == 1) {
					String consultaSelect = "SELECT id FROM responsables WHERE (idpersona=? AND telefonoprof=? "
							+ "AND horaini=? AND horafin=?)";
					PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
					pstmt2.setLong(1, r.getPersona().getId());
					pstmt2.setString(2, r.getTelefonoProf());
					pstmt2.setTime(3, java.sql.Time.valueOf(r.getHorarioIni()));
					pstmt2.setTime(4, java.sql.Time.valueOf(r.getHorarioFin()));
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
		public Responsable buscarPorID(long id) {
			Responsable ret = null;
			Connection conex = ConexBD.establecerConexion();
			String consultaInsertStr = "select * FROM responsables WHERE id=?";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
				pstmt.setLong(1, id);
				ResultSet result = pstmt.executeQuery();
				while (result.next()) {
					long idBD = result.getLong("id");
					long idPersona = result.getLong("idpersona");
					String telefonoprof = result.getString("telefonoprof");
					java.time.LocalTime horaini = result.getTime("horaini").toLocalTime();
					java.time.LocalTime horafin = result.getTime("horafin").toLocalTime();
					ret = new Responsable();
					ret.setId(idBD);
					ret.setHorarioFin(horafin);
					ret.setHorarioIni(horaini);
					ret.setTelefonoProf(telefonoprof);
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
	public Collection<Responsable> buscarTodos() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public boolean modificar(Responsable elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Responsable elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
