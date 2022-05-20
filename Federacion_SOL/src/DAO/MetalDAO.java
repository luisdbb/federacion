package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Metal;
import entidades.Oro;
import entidades.Plata;

import entidades.Bronce;

import utils.ConexBD;

public class MetalDAO implements operacionesCRUD<Metal> {

	Connection conex;

	public MetalDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Metal elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Metal m) {
		long ret = -1;
		String consultaInsertStr1 = "insert into metales(pureza, asignada, idoro, idplata, idbronce ) values (?,?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setFloat(1, m.getPureza());
			pstmt.setBoolean(2, m.isAsignada());
			if (m.getClass().equals(Oro.class)) {
				m = (Oro) m;
				pstmt.setLong(3, ((Oro) m).getId());
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else if (m.getClass().equals(Plata.class)) {
				m = (Plata) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setLong(4, ((Plata) m).getId());
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else {
				m = (Bronce) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setLong(5, ((Bronce) m).getId());
			}

			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM metales WHERE ";
				if (m.getClass().equals(Oro.class)) {
					consultaSelect += " idoro=?";
				} else if (m.getClass().equals(Plata.class)) {
					consultaSelect += " idplata=?";
				} else {
					consultaSelect += " idbronce=?";
				}
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				if (m.getClass().equals(Oro.class)) {
					pstmt2.setLong(1, ((Oro) m).getId());
				} else if (m.getClass().equals(Plata.class)) {
					pstmt2.setLong(1, ((Plata) m).getId());
				} else {
					pstmt2.setLong(1, ((Bronce) m).getId());
				}

				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long idmetal = result.getLong("id");
					if (idmetal != -1)
						ret = idmetal;
				}
				result.close();
				pstmt2.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

	//// Examen 13
	@Override
	public Metal buscarPorID(long id) {
		Metal ret = null;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "select * FROM metales WHERE id=?";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				Long idoro = Long.valueOf(result.getLong("idoro"));
				Long idplata = Long.valueOf(result.getLong("idplata"));
				Long idbronce = Long.valueOf(result.getLong("idbronce"));
				boolean asignada = result.getBoolean("asignada");
				float pureza = result.getFloat("pureza");
				java.sql.Date fecha = result.getDate("fecha");
				LocalDate fechaLD = fecha.toLocalDate();
				if (idoro != null) {
					ret = new Oro();
					((Oro) ret).setId(idoro);
					((Oro) ret).setPureza(pureza);
				} else if (idplata != null) {
					ret = new Plata();
					((Plata) ret).setId(idplata);
					((Plata) ret).setPureza(pureza);
				} else {
					ret = new Bronce();
					((Bronce) ret).setId(idbronce);
					((Bronce) ret).setPureza(pureza);
				}
				ret.setAsignada(asignada);
				ret.setFecha(fechaLD);
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
	public Collection<Metal> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Metal m) {
		String consultaInsertStr1 = "update metales SET pureza=? , asignada=? WHERE idoro=? AND idplata=? AND idbronce = ? ";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setFloat(1, m.getPureza());
			pstmt.setBoolean(2, m.isAsignada());
			if (m.getClass().equals(Oro.class)) {
				m = (Oro) m;
				pstmt.setLong(3, ((Oro) m).getId());
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else if (m.getClass().equals(Plata.class)) {
				m = (Plata) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setLong(4, ((Plata) m).getId());
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else {
				m = (Bronce) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setLong(5, ((Bronce) m).getId());
			}

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
	public boolean eliminar(Metal elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<Oro> buscarTodosOros(boolean solosinasignar) {
		List<Oro> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM metales WHERE idoro IS NOT NULL";
		if (solosinasignar) {
			consultaInsertStr += " AND asignada=0";
		}

		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Oro oro;
				long idBD = result.getLong("id");
				long idOro = result.getLong("idoro");
				float pureza = result.getFloat("pureza");
				boolean asignada = result.getBoolean("asignada");
				java.sql.Date fecha = result.getDate("fecha");

				oro = new Oro();
				oro.setId(idOro);
				oro.setPureza(pureza);
				oro.setAsignada(asignada);
				if (fecha != null) {
					LocalDate fechaLD = fecha.toLocalDate();
					oro.setFecha(fechaLD);
				}
				todos.add(oro);
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

	public Collection<Plata> buscarTodosPlatas(boolean solosinasignar) {
		List<Plata> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM metales WHERE idplata IS NOT NULL";
		if (solosinasignar) {
			consultaInsertStr += " AND asignada=0";
		}

		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Plata plata;
				long idBD = result.getLong("id");
				long idPlata = result.getLong("idplata");
				float pureza = result.getFloat("pureza");
				boolean asignada = result.getBoolean("asignada");
				java.sql.Date fecha = result.getDate("fecha");

				plata = new Plata();
				plata.setId(idPlata);
				plata.setPureza(pureza);
				plata.setAsignada(asignada);
				if (fecha != null) {
					LocalDate fechaLD = fecha.toLocalDate();
					plata.setFecha(fechaLD);
				}
				todos.add(plata);
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

	public Collection<Bronce> buscarTodosBronces(boolean solosinasignar) {
		List<Bronce> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM metales WHERE idbronce IS NOT NULL";
		if (solosinasignar) {
			consultaInsertStr += " AND asignada=0";
		}

		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Bronce bronce;
				long idBD = result.getLong("id");
				long idBronce = result.getLong("idbronce");
				float pureza = result.getFloat("pureza");
				boolean asignada = result.getBoolean("asignada");
				java.sql.Date fecha = result.getDate("fecha");

				bronce = new Bronce();
				bronce.setId(idBronce);
				bronce.setPureza(pureza);
				bronce.setAsignada(asignada);
				if (fecha != null) {
					LocalDate fechaLD = fecha.toLocalDate();
					bronce.setFecha(fechaLD);
				}
				todos.add(bronce);
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

}
