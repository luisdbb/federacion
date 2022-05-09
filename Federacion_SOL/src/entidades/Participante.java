package entidades;

import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

public class Participante implements Comparable<Participante> {
	protected long id;
	protected int dorsal; // valor entre 001 y 150
	protected char calle;

	/// Examen 11
	Tiempo tiempo = new Tiempo();
	boolean penalizacion = false;
	String otros = "";

	public Participante(long id, int dorsal, char calle) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.calle = calle;
	}

	public Participante(long id, int dorsal, char calle, Tiempo tiempo, boolean penalizacion, String otros) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.calle = calle;
		this.tiempo = tiempo;
		this.penalizacion = penalizacion;
		this.otros = otros;
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

	public Tiempo getTiempo() {
		return tiempo;
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}

	public boolean isPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(boolean penalizacion) {
		if (penalizacion)
			System.out.println("AVISO: Deberá introducir el motivo de la pensalización!");
		this.penalizacion = penalizacion;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	@Override
	public String toString() {
		return "id=" + id + ", dorsal=" + dorsal + ", calle=" + calle
				+ (Validaciones.validarTiempo(this.getTiempo()) ? ", tiempo=" + tiempo : "") + ", ¿penalizado? "
				+ (penalizacion ? "SÍ: " + otros : "NO");
	}

	/// EXAM11 EJERCICIO2 EVAL1
	public static Participante nuevoParticipante() {
		Participante ret = new Participante();
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		long id;
		int dorsal = -1;
		char calle = '-';
		Tiempo tiempo;
		boolean penalizacion;
		String otros = "";
		System.out.println("Introduzca los datos del nuevo Participante:");
		do {
			System.out.println("Introduzca el id del nuevo participante:");
			id = in.nextLong();
			valido = Validaciones.validarId(id);
			if (!valido) {
				System.out.println("Valor introducido para el id del nuevo participante inválido,");
			}
		} while (!valido);
		ret.setId(id);
		valido = false;

		do {
			System.out.println("Introduzca el dorsal (1-150) del participante:");
			dorsal = in.nextInt();
			valido = Validaciones.validarDorsal(dorsal);
			if (!valido) {
				System.out.println("Valor introducido para el dorsal inválido. Ha de ser un entero entre 1 y 150.");
			}
		} while (!valido);
		ret.setDorsal(dorsal);
		valido = false;

		do {
			System.out.println("Introduzca la calle por la que participa:");
			calle = in.nextLine().charAt(0);
			valido = Validaciones.validarCalle(calle);
			if (!valido) {
				System.out.println("Valor introducido para la calle inválido. Ha de ser una letra.");
			}
		} while (!valido);
		ret.setCalle(calle);
		valido = false;

		System.out.println("¿Desea establecer el tiempo del participante y su penalizacion ahora?");
		boolean respuesta = Utilidades.leerBoolean();
		if (respuesta) {
			valido = false;
			do {
				tiempo = Tiempo.nuevoTiempo();
				valido = Validaciones.validarTiempo(tiempo);
				if (!valido) {
					System.out.println(
							"Valor introducido para el tiempo del participante inválido. Vuelva a introducir el tiempo.");
				}
			} while (!valido);
			ret.setTiempo(tiempo);
			System.out.println("¿El participante ha sido penalizado: ?");
			penalizacion = Utilidades.leerBoolean();
			ret.setPenalizacion(penalizacion);
			if (penalizacion) {
				valido = false;
				do {
					System.out.println("Introduzca el motivo de la penalización: (hasta 500 caracteres máximo)");
					otros = in.nextLine();
					valido = Validaciones.validarMotivoPenalizacion(otros);
					if (!valido)
						System.out.println(
								"El valor introducido para el motivo de la penalización no es válido (máx 500 caracteres).");
				} while (!valido);
				ret.setOtros(otros);

			}
		}
		
		return ret;
	}

	/// EXAM11 EJERCICIO2 EVAL2
	/**
	 * Implementar la interfaz Comparable para la clase Participante.java, de forma
	 * que se ordenen según su tiempo en orden creciente y desempatar en función de
	 * la calle en la que participó (de menor a mayor) y, por último, si sigue
	 * habiendo empate, deshacerlo por el valor creciente del campo idParticipante .
	 */
	@Override
	public int compareTo(Participante o) {
		int ret = this.getTiempo().compareTo(o.getTiempo());
		if (ret == 0) {
			ret = Character.compare(this.getCalle(),o.getCalle());
			if(ret==0)
				ret = Long.compare(this.getId(), o.getId());
		}
		return ret;
	}

}
