package entidades;

import java.time.LocalDateTime;

public class Resultado {
	private long id;
	private Oro oro; // idOro
	private Plata plata; // idPlata
	private Bronce bronce; // idBronce

	// datos para el cierre del resultado
	private boolean definitivo = false;
	private LocalDateTime fechahora;
	private Participante[] podio = new Participante[3];
	// podio[0] -> primer puesto
	// podio[1] -> segundo puesto
	// podio[2] -> tercer puesto

	public Resultado(long id, Oro oro, Plata plata, Bronce bronce) {
		super();
		this.id = id;
		this.oro = oro;
		this.plata = plata;
		this.bronce = bronce;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Oro getPrimero() {
		return oro;
	}

	public void setPrimero(Oro primero) {
		this.oro = primero;
	}

	public Plata getSegundo() {
		return plata;
	}

	public void setSegundo(Plata segundo) {
		this.plata = segundo;
	}

	public Bronce getTercero() {
		return bronce;
	}

	public void setTercero(Bronce tercero) {
		this.bronce = tercero;
	}

	public boolean isDefinitivo() {
		return definitivo;
	}

	private void setDefinitivo(boolean definitivo) {
		this.definitivo = definitivo;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	private void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public Participante[] getPodio() {
		return podio;
	}

	public void setPodio(Participante[] podio) {
		this.podio = podio;
	}

	/**
	 * Funcion que establece el resultado como definitivo y la fecha/hora a la
	 * actual junto con el podio obtenido
	 */
	public void cerrarResultado(Participante[] podioobtenido, Oro o, Plata p, Bronce b) {
		this.podio = podioobtenido;
		this.oro = o;
		this.plata = p;
		this.bronce = b;
		this.setDefinitivo(true);
		this.setFechahora(LocalDateTime.now());
	}

	public void cerrarResultado(Participante[] podioobtenido) {
		this.podio = podioobtenido;
		this.setDefinitivo(true);
		this.setFechahora(LocalDateTime.now());
	}

	public void cerrarResultado(Participante primero, Participante segundo, Participante tercero) {
		Participante[] podioobtenido = { primero, segundo, tercero };
		this.podio = podioobtenido;
		this.setDefinitivo(true);
		this.setFechahora(LocalDateTime.now());
	}

	public void cerrarResultado(Participante primero, Participante segundo, Participante tercero,
			LocalDateTime fechahora) {
		Participante[] podioobtenido = { primero, segundo, tercero };
		this.podio = podioobtenido;
		this.setDefinitivo(true);
		this.setFechahora(fechahora);
	}

}
