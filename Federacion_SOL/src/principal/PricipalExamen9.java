package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entidades.DatosPersona;
import entidades.NIE;
import entidades.NIF;
import utils.ConexBD;

public class PricipalExamen9 {

	public static void main(String[] args) {
		System.out.println("INICIO");
		/// Probar Ejercicio1
		/// Borrar antes el fichero atletas_alfabetico.txt
		DatosPersona.exportarAtletasAlfabetico();
		/// Probar ejercicio 2
		/// Borrar antes todas las filas de la tabla personas en la BD
		DatosPersona.insertarPersonas();

		/// Ejercicio 3
		/// Borrar antes el fichero atletas_alfabetico2.txt
		Connection conex = null;
		Statement consulta = null;
		ResultSet resultado = null;
		File f = new File("atletas_alfabetico2.txt");
		FileWriter fo = null;
		try {
			fo = new FileWriter(f);
			conex = ConexBD.establecerConexion();
			String consultaStr = "SELECT * FROM personas ORDER BY nombre ASC";
			if (conex == null)
				conex = ConexBD.getCon();
			consulta = conex.createStatement();
			resultado = consulta.executeQuery(consultaStr);
			while (resultado.next()) {
				int id = resultado.getInt(1);
				String nombre = resultado.getString(2);
				String telefono = resultado.getString(3);
				java.sql.Date fecha = resultado.getDate(4);
				/// java.sql.Time hora = resultado.getTime(4);
				java.time.LocalDate fechadate = fecha.toLocalDate();
				/// java.time.LocalTime horatime = hora.toLocalTime();
				/// java.time.LocalDateTime fechahora = LocalDateTime.of(fechadate, horatime);

				/// OJO:disponemos del String con el NIF/NIE pero NO el objeto Documentacion
				String nifnie = resultado.getString(5);
				DatosPersona dp = new DatosPersona(id, nombre, telefono, fechadate);
				if (Character.isLetter(nifnie.charAt(0))) /// si comienza por una letra es un NIE
					dp.setNifnie(new NIE(nifnie));
				else
					dp.setNifnie(new NIF(nifnie));
				fo.write(dp.data() + "\n");
				fo.flush();
			}

			fo.close();
			System.out.println("Se han exportado correctamente los datos de las personas.");
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
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

}
