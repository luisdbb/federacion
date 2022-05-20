package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import entidades.Lugar;
import entidades.Patrocinador;
import entidades.Prueba;
import entidades.Resultado;
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
				String consultaSelect = "SELECT id FROM pruebas WHERE (nombre=? AND idlugar=? "
						+ "AND individual=? AND idpatrocinador=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, p.getNombre());
				pstmt2.setLong(2, p.getLugar().getId());
				pstmt2.setInt(3, (p.isIndividual() ? 1 : 0));
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
		Prueba ret = null;
		String consultaInsertStr = "select * FROM pruebas WHERE id=?";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				String nombre = result.getString("nombre");
				java.sql.Date fecha = result.getDate("fecha");
				long idPatrocinador = result.getLong("idpatrocinador");
				boolean individual = result.getBoolean("individual");
				long idLugar = result.getLong("idlugar");
				Long idResultado = result.getLong("idresultado");

				ret = new Prueba();
				ret.setId(idBD);
				ret.setNombre(nombre);
				LocalDate fechaLD = fecha.toLocalDate();
				ret.setFecha(fechaLD);
				PatrocinadorDAO pDAO = new PatrocinadorDAO(this.conex);
				Patrocinador patroc = pDAO.buscarPorID(idPatrocinador);
				if (patroc != null)
					ret.setPatrocinador(patroc);
				ret.setIndividual(individual);
				LugarDAO lDAO = new LugarDAO(this.conex);
				Lugar lugar = lDAO.buscarPorID(idLugar);
				ret.setLugar(lugar);
				ResultadoDAO resDAO = new ResultadoDAO(this.conex);
				Resultado resultado = resDAO.buscarPorID(idResultado);
				if (resultado != null) {
					ret.setResultado(resultado);
				}

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
	public Collection<Prueba> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Prueba p) {
		String consultaInsertStr = "update pruebas SET nombre=?, fecha=?, idlugar=?, individual=?, idpatrocinador=? WHERE id=?";
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
			pstmt.setLong(5, p.getId());
			int resultadomodificacion = pstmt.executeUpdate();
			if (resultadomodificacion == 1)
				return true;
			else
				return false;
		} catch (Exception exc) {
			System.out.println("Se ha producido una SQLException:" + exc.getMessage());
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(Prueba elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
