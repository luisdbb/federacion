package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

import entidades.Resultado;
import entidades.Atleta;
import entidades.Bronce;
import entidades.Oro;
import entidades.Participante;
import entidades.Plata;
import utils.ConexBD;

public class ResultadoDAO implements operacionesCRUD<Resultado> {
	Connection conex;

	public ResultadoDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Resultado elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Resultado r) {
		long ret = -1;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into resultados(definitivo, fechahora, idoro, idplata, idbronce, idpuesto1, idpuesto2, idpuesto3) values (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setBoolean(1, r.isDefinitivo());
			java.sql.Timestamp fechahora = java.sql.Timestamp.valueOf(r.getFechahora());
			pstmt.setTimestamp(2, fechahora);
			pstmt.setLong(3, r.getPrimero().getId());
			pstmt.setLong(4, r.getSegundo().getId());
			pstmt.setLong(5, r.getTercero().getId());
			pstmt.setLong(6, ((Atleta) r.getPodio()[0]).getIdAtleta());
			pstmt.setLong(7, ((Atleta) r.getPodio()[1]).getIdAtleta());
			pstmt.setLong(8, ((Atleta) r.getPodio()[2]).getIdAtleta());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM resultados WHERE (idoro=? AND idplata=? AND idbronce=? "
						+ "AND puesto1=? AND puesto2=? AND idpuesto3=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setLong(1, r.getPrimero().getId());
				pstmt2.setLong(2, r.getSegundo().getId());
				pstmt2.setLong(3, r.getTercero().getId());

				pstmt2.setLong(4, ((Atleta) r.getPodio()[0]).getIdAtleta());
				pstmt2.setLong(5, ((Atleta) r.getPodio()[1]).getIdAtleta());
				pstmt2.setLong(6, ((Atleta) r.getPodio()[2]).getIdAtleta());

				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long id = result.getLong("id");
					if (id != -1)
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
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

	@Override
	public Resultado buscarPorID(long id) {
		Resultado ret = null;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "select * FROM resultados WHERE id=?";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				long idBD = result.getLong("id");
				boolean definitivo = result.getBoolean("definitvo");
				Timestamp fechahora = result.getTimestamp("fechahora");
				LocalDateTime fechahoraLDT = fechahora.toLocalDateTime();
				Long idoro = result.getLong("idoro");
				Long idplata = result.getLong("idplata");
				Long idbronce = result.getLong("idbronce");
				Long idpuesto1 = result.getLong("idpuesto1");
				Long idpuesto2 = result.getLong("idpuesto2");
				Long idpuesto3 = result.getLong("idpuesto3");

				ret = new Resultado();
				ret.setId(idBD);
				ret.setDefinitivo(definitivo);
				ret.setFechahora(fechahoraLDT);
				MetalDAO metDAO = new MetalDAO(this.conex);
				Oro oro = (Oro) metDAO.buscarPorID(idoro);
				if (oro != null) {
					ret.setPrimero(oro);
				}
				Plata plata = (Plata) metDAO.buscarPorID(idplata);
				if (plata != null) {
					ret.setSegundo(plata);
				}
				Bronce bronce = (Bronce) metDAO.buscarPorID(idbronce);
				if (bronce != null) {
					ret.setTercero(bronce);
				}
				Participante[] podio = new Participante[3];
				AtletaDAO atDAO = new AtletaDAO(this.conex);
				Atleta puesto1 = atDAO.buscarPorID(idpuesto1);
				if (puesto1 != null) {
					podio[0] = puesto1;
				}
				Atleta puesto2 = atDAO.buscarPorID(idpuesto2);
				if (puesto2 != null) {
					podio[1] = puesto2;
				}
				Atleta puesto3 = atDAO.buscarPorID(idpuesto3);
				if (puesto3 != null) {
					podio[2] = puesto3;
				}
				ret.setPodio(podio);
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
	public Collection<Resultado> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Resultado elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Resultado elemento) {
		// TODO Auto-generated method stub
		return false;
	}
}
