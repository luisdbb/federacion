package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexBD {
	// Datos de la conexion a la BD
	static final String DB_URL = "jdbc:mysql://localhost:3306/bdfederacion";
	static final String USER = "root";
	static final String PASS = "";

	static Connection conexion = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	@SuppressWarnings("finally")
	public static Connection establecerConexion() {
		try {
			System.out.println("Conectando a la Base de Datos...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException ex) {
			System.out.println("Se ha producido una SQLException:" + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Se ha producido una ClassNotFoundException:" + ex.getMessage());
		} finally {
			return conexion;
		}
	}

	public static Connection getCon() {
		try {
			if (conexion == null) {
				Properties properties = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				fis = new FileInputStream("src/resources/db.properties");
				// cargamos la información del fichero properties
				properties.load(fis);
				// asignamos al origen de datos las propiedades leidas del fichero properties
				m.setUrl(properties.getProperty("url"));
				m.setPassword(properties.getProperty("password"));
				m.setUser(properties.getProperty("usuario")); // obtememos la conexion
				fis.close();
				conexion = m.getConnection();
			}
			return conexion;
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return conexion;
	}
	
	public static void cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
