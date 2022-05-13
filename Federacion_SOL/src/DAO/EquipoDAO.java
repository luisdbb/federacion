package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Equipo;
import entidades.Manager;
import utils.ConexBD;


public class EquipoDAO implements operacionesCRUD<Equipo> {
	Connection conex;

	public EquipoDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Equipo elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Equipo buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Equipo> buscarTodos() {
		List<Equipo> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM equipos";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Equipo eq;
				long idBD = result.getLong("id");
				String nombre = result.getString("nombre");
				int anio = result.getInt("anioinscripcion");
				long idManager = result.getLong("idmanager");
				eq = new Equipo();
				eq.setId(idBD);
				eq.setNombre(nombre);
				eq.setAnioinscripcion(anio);
				ManagerDAO manDAO = new ManagerDAO();
				Manager man = manDAO.buscarPorID(idManager);
				eq.setManager(man);
				/// TODO: Habrá que arreglar esta parte cuando se incluya la información de los
				/// atletas del equipo
				todos.add(eq);
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
	public boolean modificar(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}
}
