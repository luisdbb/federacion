package principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConexBD;

public class PrincipalBD {

	public static void main(String[] args) {
		System.out.println("INICIO");
		Connection conex = null;
		Statement consulta = null;
		ResultSet resultado = null;
		try {
//			conex = ConexBD.establecerConexion();
			String consultaStr = "SELECT * FROM personas";
			if(conex==null)
				conex = ConexBD.getCon();
			consulta = conex.createStatement();
			resultado = consulta.executeQuery(consultaStr);
			while (resultado.next()) {
				int id = resultado.getInt(1);
				String nombre = resultado.getString("nombre");
				String telefono = resultado.getString("telefono");
				System.out.println("id=" + id + "\tNombre:" + nombre + "\tTfn:" + telefono);
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una Excepcion:"+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				System.out.println("Cerrando recursos...");
				if(resultado!=null)
					resultado.close();
				if(consulta!=null)
					consulta.close();
				if(conex!= null)
					conex.close();
			} catch (SQLException e) {
				System.out.println("Se ha producido una Excepcion:"+ e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("FIN");
	}

}
