package principal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import entidades.*;
import utils.*;

public class PrincipalBD {

	public static void main(String[] args) {
//		insertarAtletas();
		System.out.println("INICIO");
		Connection conex = null;
		Statement consulta = null;
		ResultSet resultado = null;
		try {
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
				System.out.println("IDLugar:" + id + ", nombre:" + nombre + ", en " + ubicacion
						+ (airelibre ? " al airelibre." : " a cubierto."));
			}

			/// Llamar a un procedimiento remoto de la BD
			/***
			 * DELIMITER $$
			 * 
			 * DROP PROCEDURE IF EXISTS `bdfederacion`.`obtenerNombrePersona` $$ CREATE
			 * PROCEDURE `bdfederacion`.`obtenerNombrePersona` (IN EMP_ID INT, OUT EMP_FIRST
			 * VARCHAR(255)) BEGIN SELECT nombre INTO EMP_FIRST FROM personas WHERE id =
			 * EMP_ID; END $$
			 * 
			 * DELIMITER ;
			 */
			final String QUERY = "{call obtenerNombrePersona (?, ?)}";
			CallableStatement stmt = conex.prepareCall(QUERY);
			Scanner in = new Scanner(System.in);
			System.out.println("\nDame el id de la persona y te digo su nombre:");
			int id = in.nextInt();
			// Enlazar los valores en los parametros del procedure
			stmt.setInt(1, id); // Esto establece el ID, que es de tipo INT en el procedure SQL
			// Como el 2º parametro es OUT hay que registrarlo junto a su tipo SQL, en este caso el nombre es String<->VARCHAR
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			// Se llama a execute para ejecutar el procedure en la BD.
			System.out.println("Executando procedure bdfederacion.obtenerNombrePersona de la BD...");
			stmt.execute();
			// Obtenemos el nombre del la persona con el método getXXX
			String nombrePersona = stmt.getString(2);
			System.out.println("Nombre de la persona con ID:"+id+" es: " + nombrePersona);
			in.close();
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
		String consultaInsertStr1 = "insert into personas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		//String consultaInsertStr2 = "insert into participantes(id, dorsal, calle) values (?,?,?)";
		String consultaInsertStr3 = "insert into atletas(id, altura, peso, idequipo, idpersona) values (?,?,?,?,?)";

		try {

			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			for (DatosPersona dp : Datos.PERSONAS) {
				pstmt.setLong(1, dp.getId());
				pstmt.setString(2, dp.getNombre());
				pstmt.setString(3, dp.getTelefono());
				java.sql.Date fechaSQL = java.sql.Date.valueOf(dp.getFechaNac());
				pstmt.setDate(4, fechaSQL);
				pstmt.setString(5, dp.getNifnie().mostrar());
				int resultadoInsercion = pstmt.executeUpdate();
			}
			PreparedStatement pstmt2 = conex.prepareStatement(consultaInsertStr3);
			for (Atleta a : Datos.ATLETAS) {
				pstmt2.setLong(1, a.getId());
				pstmt2.setFloat(2, a.getAltura());
				pstmt2.setFloat(3, a.getPeso());
				pstmt2.setNull(4, java.sql.Types.INTEGER);
				pstmt2.setLong(5, a.getPersona().getId());
				int resultadoInsercion = pstmt2.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

	}

//	public static void insertarLugares() {
//		Connection conex = ConexBD.establecerConexion();
//		String consultaInsertStr = "insert into lugares(id, nombre, ubicacion, airelibre) values (?,?,?,?)";
//		try {
//			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
//			for (Lugar l : Lugar.values()) {
//				pstmt.setInt(1, l.getId());
//				pstmt.setString(2, l.getNombre());
//				pstmt.setString(3, l.getUbicacion());
//				pstmt.setBoolean(4, l.isAirelibre());
//				int resultadoInsercion = pstmt.executeUpdate();
//			}
//
//		} catch (SQLException e) {
//			System.out.println("Se ha producido una SQLException:" + e.getMessage());
//			e.printStackTrace();
//		}
//
//	}

}
