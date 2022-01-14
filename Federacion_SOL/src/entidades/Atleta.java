package entidades;

import utils.Datos;

public class Atleta extends Participante {
	private long idAtleta;
	private float altura;
	private float peso;

	private DatosPersona persona;

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso, DatosPersona dp) {
		super(idAtleta, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idAtleta, float altura, float peso, DatosPersona dp) {
		super();
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idParticipante, Atleta a, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idAtleta = a.idAtleta;
		this.altura = a.altura;
		this.peso = a.peso;
		this.persona = Datos.buscarPersonaPorId(a.idAtleta);
	}

	@Override
	public long getId() {
		return idAtleta;
	}

	@Override
	public void setId(long id) {
		this.idAtleta = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

}
