package entidades;

import java.time.format.DateTimeFormatter;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}
	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}
	public int getAnioinscripcion() {
		return anioinscripcion;
	}
	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Atleta[] getAtletas() {
		return atletas;
	}
	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	//Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret+= "EQ"+idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion()+") " + atletas.length + " componentes en el equipo:\n";
		for(Atleta a: atletas) {
			ret += a.getId()+". " + a.getPersona().getNombre() + "(" + a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ ") "
					+ " Datos físicos:\t"+ a.getPeso()+ "Kgs.\t" + a.getAltura()+"m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}


}
