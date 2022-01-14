package entidades;

public class Participante {
	protected long id;
	protected int dorsal; // valor entre 001 y 150
	protected char calle;

	public Participante(long id, int dorsal, char calle) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.calle = calle;
	}

	public Participante() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public char getCalle() {
		return calle;
	}

	public void setCalle(char calle) {
		this.calle = calle;
	}

}
