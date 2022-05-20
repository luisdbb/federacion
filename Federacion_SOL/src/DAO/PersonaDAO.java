package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import entidades.DatosPersona;
import entidades.Documentacion;
import entidades.NIE;
import entidades.NIF;
import entidades.Patrocinador;
import utils.ConexBD;

public class PersonaDAO implements operacionesCRUD<DatosPersona> {
	Connection conex;

	public PersonaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(DatosPersona elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(DatosPersona elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DatosPersona buscarPorID(long id) {
		DatosPersona ret = null;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "select * FROM personas WHERE id=?";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				String nombre = result.getString("nombre");
				String tfn = result.getString("telefono");
				java.sql.Date fecha = result.getDate("fechanac");
				LocalDate fechaLD = fecha.toLocalDate();
				String nifnieStr = result.getString("nifnie");
				Documentacion doc;
				if (Character.isLetter(nifnieStr.charAt(0)))
					doc = new NIE(nifnieStr);
				else
					doc = new NIF(nifnieStr);
				ret = new DatosPersona();
				ret.setId(idBD);
				ret.setNombre(nombre);
				ret.setTelefono(tfn);
				ret.setFechaNac(fechaLD);
				ret.setNifnie(doc);
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
	public Collection<DatosPersona> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(DatosPersona elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(DatosPersona elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
