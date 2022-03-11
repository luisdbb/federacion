package entidades;

///Examen 7 Ejercicio 4
public class Palmares<T extends Metal, S extends Participante> {
	private long id;
	private T medalla;
	private S participante;
	private Prueba prueba;
	private String observaciones;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getMedalla() {
		return medalla;
	}

	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}

	public S getParticipante() {
		return participante;
	}

	public void setParticipante(S participante) {
		this.participante = participante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Palmares() {
	}

	public Palmares(long id, T medalla, S participante, Prueba prueba, String observaciones) {
		super();
		this.id = id;
		this.medalla = medalla;
		this.participante = participante;
		this.prueba = prueba;
		this.observaciones = observaciones;
	}

	/**
	 * Funcion que muestra por la salida estandar los datos de un Palmares, en este
	 * orden:  el id del propio objeto Palmarés,  seguido los datos de la medalla
	 * a partir de su método toString(),  seguido del nombre de la prueba y su
	 * fecha y lugar de celebración,  seguido del dorsal y calle con que participó
	 *  y, en función de si el participante es Atleta o Equipo, mostrar los datos
	 * de la/s persona/s que ganaron esa medalla en dicha prueba, a partir del
	 * método toString() del campo persona asociado/s, según el caso.
	 */
	public void mostrar() {
		String cad = "";
		cad += "idPalmares: "+this.getId() + "\n";
		cad += "Medalla: "+ this.getMedalla() +"\n"; 
		cad += "en la Prueba: "+this.getPrueba().getNombre() + ", a fecha " + this.getPrueba().getFecha() + ", celebrada en"
				+ this.getPrueba().getLugar().getNombre() + "\n";
		cad += "participó con el dorsal: "+this.getParticipante().getDorsal() + ", calle: " + this.getParticipante().getCalle() + "\n";
		if(this.getParticipante().getClass().equals(Atleta.class)) {
			cad += "el atleta: "+(Atleta)this.getParticipante() +".";
		}
		else {
			Equipo equipo = (Equipo) this.getParticipante();
			cad +="el equipo formado por los atletas: ";
			for(Atleta a: equipo.getAtletas()) {
				cad +="\t* " + a+"\n";
			}
			cad+=".\n";
		}
		System.out.println(cad);
	}

}
