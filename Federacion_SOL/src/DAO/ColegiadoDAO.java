package DAO;

import java.sql.Connection;
import java.util.Collection;

import entidades.Colegiado;

public class ColegiadoDAO implements operacionesCRUD<Colegiado> {
	Connection conex;

	public ColegiadoDAO(Connection co) {
		if (this.conex == null)
			this.conex = co;
	}

	@Override
	public boolean insertarConID(Colegiado elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public long insertarSinID(Colegiado elemento) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public Colegiado buscarPorID(long id) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Collection<Colegiado> buscarTodos() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public boolean modificar(Colegiado elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public boolean eliminar(Colegiado elemento) {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
