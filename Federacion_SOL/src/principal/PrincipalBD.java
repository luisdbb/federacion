package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import entidades.DatosPersona;
import entidades.Lugar;
import utils.ConexBD;
import utils.Datos;

public class PrincipalBD {

	public static void main(String[] args) {
		System.out.println("INICIO");
		Connection conex = null;
		Statement consulta = null;
		ResultSet resultado = null;
		try {

//			insertarLugares();

			conex = ConexBD.establecerConexion();
			String consultaStr = "SELECT * FROM lugares";
			if (conex == null)
				conex = ConexBD.getCon();
			consulta = conex.createStatement();
			resultado = consulta.executeQuery(consultaStr);
			while (resultado.next()) {
				int id = resultado.getInt(1);
				String nombre = resultado.getString(2);
				String ubicacion = resultado.getString(3);
				boolean airelibre = resultado.getBoolean(4);
				
//				java.sql.Date fecha = resultado.getDate(4);
//				java.sql.Time hora = resultado.getTime(4);
//				LocalDate fechadate = fecha.toLocalDate();
//				LocalTime horatime = hora.toLocalTime();
//				java.time.LocalDateTime fechahora = LocalDateTime.of(fechadate, horatime);
//				System.out.println("id=" + id + "\tptos:" + puntuacionMedia + "\tfecha:"
//						+ fechahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una Excepcion:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Cerrando recursos...");
				if (resultado != null)
					resultado.close();
				if (consulta != null)
					consulta.close();
				if (conex != null)
					conex.close();
			} catch (SQLException e) {
				System.out.println("Se ha producido una Excepcion:" + e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("FIN");
	}

	public static void insertarAtletas() {
		
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 ="insert into datospersonas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		String consultaInsertStr2="insert into particpantes(id, dorsal, calle) values (?,?,?)";
		String consultaInsertStr3 = "insert into atletas(idatleta, altura, peso, idparticipante, idpersona) values (?,?,?,?,?,?)";
		
		
		try {
			
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			for ( DatosPersona dp: Datos.PERSONAS) {
				pstmt.setLong(1, dp.getId());
				pstmt.setString(2, dp.getNombre());
				pstmt.setString(3, dp.getTelefono());
				java.sql.Date fechaSQL = new java.sql.Date(0); //.getFechaNac();
				pstmt.setDate(4, fechaSQL);
				pstmt.setString(5, "");
				int resultadoInsercion = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	public static void insertarLugares() {
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into lugares(id, nombre, ubicacion, airelibre) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			for (Lugar l : Lugar.values()) {
				pstmt.setInt(1, l.getId());
				pstmt.setString(2, l.getNombre());
				pstmt.setString(3, l.getUbicacion());
				pstmt.setBoolean(4, l.isAirelibre());
				int resultadoInsercion = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

	}

}
