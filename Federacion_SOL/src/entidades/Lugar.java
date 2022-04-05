package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConexBD;

//Examen 1 Ejercicio 1
public enum Lugar implements operacionesCRUD<Lugar> {
	GIJONMESTAS(1, "Las Mestas", "Gijon", true), GIJONCENTRO(2, "Centro ciudad", "Gijon", true),
	OVIEDOSANFRANCISCO(3, "Parque San Francisco", "Oviedo", true), AVILESPUERTO(4, "Puerto", "Aviles", true),
	AVILESPABELLON(5, "Pabellon deportivo Aviles", "Aviles", false), OVIEDOCENTRO(6, "Centro ciudad", "Oviedo", true);

	private int id;
	private String nombre;
	private String ubicacion;
	private boolean airelibre;

	private Lugar(int id, String nombre, String ubicacion, boolean airelibre) {
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.airelibre = airelibre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isAirelibre() {
		return airelibre;
	}

	@Override
	public boolean insertarConID(Lugar l) {
		boolean ret = false;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into lugares(id, nombre, ubicacion, airelibre) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

			pstmt.setInt(1, l.getId());
			pstmt.setString(2, l.getNombre());
			pstmt.setString(3, l.getUbicacion());
			pstmt.setBoolean(4, l.isAirelibre());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public long insertarSinID(Lugar l) {
		long ret = -1;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into lugares(nombre, ubicacion, airelibre) values (?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setString(1, l.getNombre());
			pstmt.setString(2, l.getUbicacion());
			pstmt.setBoolean(3, l.isAirelibre());
			int resultadoInsercion = pstmt.executeUpdate();
			if(resultadoInsercion==1) {
				String consultaSelect = "SELECT id FROM lugares WHERE (nombre=? AND ubicacion=? "
						+ "AND airelibre=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaInsertStr);
				pstmt2.setString(1, l.getNombre());
				pstmt2.setString(2, l.getUbicacion());
				pstmt2.setInt(3, (l.isAirelibre()?1:0));
			
				ResultSet result = pstmt2.executeQuery();
				while(result.next()) {
					long id = result.getLong("id");
					if(id!=-1)
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
		}
		catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	
		return ret;
	}

}
