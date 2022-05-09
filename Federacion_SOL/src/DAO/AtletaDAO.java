package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Atleta;
import entidades.DatosPersona;
import utils.ConexBD;
import utils.Datos;

public class AtletaDAO implements operacionesCRUD<Atleta> {
	Connection conex;

	public AtletaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Atleta a) {
		boolean ret = false;

		String consultaInsertStr1 = "insert into personas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		String consultaInsertStr3 = "insert into atletas(id, altura, peso, idequipo, idpersona) values (?,?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setLong(1, a.getPersona().getId());
			pstmt.setString(2, a.getPersona().getNombre());
			pstmt.setString(3, a.getPersona().getTelefono());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(a.getPersona().getFechaNac());
			pstmt.setDate(4, fechaSQL);
			pstmt.setString(5, a.getPersona().getNifnie().mostrar());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				PreparedStatement pstmt2 = conex.prepareStatement(consultaInsertStr3);
				pstmt2.setLong(1, a.getId());
				pstmt2.setFloat(2, a.getAltura());
				pstmt2.setFloat(3, a.getPeso());
				pstmt2.setNull(4, java.sql.Types.INTEGER);
				pstmt2.setLong(5, a.getPersona().getId());
				int resultadoInsercion2 = pstmt2.executeUpdate();
				if (resultadoInsercion2 == 1) {
					System.out.println("Se ha insertado correctamente el nuevo Atleta.");
					ret = true;
				}
				if (conex != null)
					conex.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return ret;
	}

	@Override
	public long insertarSinID(Atleta a) {
		long ret = -1;
		
		String consultaInsertStr1 = "insert into personas(nombre, telefono, fechanac, nifnie) values (?,?,?,?)";
		String consultaInsertStr3 = "insert into atletas(altura, peso, idequipo, idpersona) values (?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setString(1, a.getPersona().getNombre());
			pstmt.setString(2, a.getPersona().getTelefono());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(a.getPersona().getFechaNac());
			pstmt.setDate(3, fechaSQL);
			pstmt.setString(4, a.getPersona().getNifnie().mostrar());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM personas WHERE (nombre=? AND telefono=? " + "AND nifnie=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, a.getPersona().getNombre());
				pstmt2.setString(2, a.getPersona().getTelefono());
				pstmt2.setString(3, a.getPersona().getNifnie().mostrar());
				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long idpersona = result.getLong("id");
					if (idpersona != -1) {
						PreparedStatement pstmt21 = conex.prepareStatement(consultaInsertStr3);
						pstmt21.setFloat(1, a.getAltura());
						pstmt21.setFloat(2, a.getPeso());
						pstmt21.setNull(3, java.sql.Types.INTEGER);
						pstmt21.setLong(4, idpersona);
						int resultadoInsercion2 = pstmt21.executeUpdate();
						if (resultadoInsercion2 == 1) {
							String consultaSelect2 = "SELECT id FROM atletas WHERE (altura=? AND peso=? "
									+ "AND idpersona=?)";
							PreparedStatement pstmt3 = conex.prepareStatement(consultaSelect2);
							pstmt3.setFloat(1, a.getAltura());
							pstmt3.setFloat(2, a.getPeso());
							pstmt3.setLong(3, a.getPersona().getId());
							ResultSet result3 = pstmt3.executeQuery();
							while (result3.next()) {
								long idatleta = result3.getLong("id");
								if (idatleta != -1) {
									System.out.println(
											"Se ha insertado correctamente el nuevo Atleta de id: " + idatleta);
									if (conex != null)
										conex.close();
									return idatleta;
								}
							}

						}
					}

				}
				result.close();
				pstmt2.close();
				if (conex != null)
					conex.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

	/// Examen 10 ejercicio 10
	@Override
	public Atleta buscarPorID(long id) {
		Atleta ret = null;
		String consultaInsertStr = "select * FROM atletas WHERE id=?";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				long idPersona = result.getLong("idpersona");
				long idEquipo = result.getLong("idequipo"); //// puede ser null
				float altura = result.getFloat("altura");
				float peso = result.getFloat("peso");
				ret = new Atleta();
				ret.setIdAtleta(idBD);
				ret.setAltura(altura);
				ret.setPeso(peso);
				DatosPersona dp = Datos.buscarPersonaPorId(idPersona);
				ret.setPersona(dp);
				/// TO-DO: Habrá que arreglar esta parte cuando se incluya la información del
				/// equipo
			}
			if (conex != null)
				conex.close();
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
	public Collection<Atleta> buscarTodos() {
		List<Atleta> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM atletas";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Atleta atleta;
				long idBD = result.getLong("id");
				long idPersona = result.getLong("idpersona");
				long idEquipo = result.getLong("idequipo"); //// puede ser null
				float altura = result.getFloat("altura");
				float peso = result.getFloat("peso");
				atleta = new Atleta();
				atleta.setIdAtleta(idBD);
				atleta.setAltura(altura);
				atleta.setPeso(peso);
				DatosPersona dp = Datos.buscarPersonaPorId(idPersona);
				atleta.setPersona(dp);
				/// TODO: Habrá que arreglar esta parte cuando se incluya la información del
				/// equipo
				todos.add(atleta);
			}
			if (conex != null)
				conex.close();
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public boolean modificar(Atleta elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Atleta elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
