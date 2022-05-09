package entidades;

import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

public class Tiempo implements Comparable<Tiempo> {
	private int horas;
	private int minutos;
	private int segundos;
	private int centesimas;

	public Tiempo() {
	}

	public Tiempo(int horas, int minutos, int segundos, int centesimas) {
		super();
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
		this.centesimas = centesimas;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public int getCentesimas() {
		return centesimas;
	}

	public void setCentesimas(int centesimas) {
		this.centesimas = centesimas;
	}

	@Override
	public String toString() {
		return "" + horas + ":" + minutos + ":" + segundos + "," + centesimas;
	}


	///Examen 11 Ejercicio 1-EVAL1
	public static Tiempo nuevoTiempo() {
		Tiempo ret = new Tiempo();
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		int h, m, s, c;
		do {
			System.out.println("Introduzca los valores para el Tiempo:");
			do {
				System.out.println("Introduzca el nº de horas:");
				String aux = in.nextLine();
			
				if (aux == "") {
					h = 0;
					valido = true;
				} else {
					h = Utilidades.convertirAEntero(aux);
					valido = Validaciones.validarHoras(h);
				}
				if (!valido)
					System.out.println("El valor introducido para las horas no es válido.");
			} while (!valido);
			ret.setHoras(h);
			valido = false;

			do {
				System.out.println("Introduzca el nº de minutos:");
				String aux = in.nextLine();
				if (aux == "") {
					m = 0;
					valido = true;
				} else {
					m = Utilidades.convertirAEntero(aux);
					valido = Validaciones.validarMinutos(m);
				}
				if (!valido)
					System.out.println("El valor introducido para los minutos no es válido.");
			} while (!valido);
			ret.setMinutos(m);
			valido = false;

			do {
				System.out.println("Introduzca el nº de segundos:");
				String aux = in.nextLine();
				if (aux == "") {
					s = 0;
					valido = true;
				} else {
					s = Utilidades.convertirAEntero(aux);
					valido = Validaciones.validarSegundos(s);
				}
				if (!valido)
					System.out.println("El valor introducido para los segundos no es válido.");
			} while (!valido);
			valido = false;
			ret.setSegundos(s);

			do {
				System.out.println("Introduzca el nº de centésimas de segundo:");
				String aux = in.nextLine();
				if (aux == "") {
					c = 0;
					valido = true;
				} else {
					c = Utilidades.convertirAEntero(aux);
					valido = Validaciones.validarCentesimas(c);
				}
				if (!valido)
					System.out.println("El valor introducido para las centésimas no es válido.");
			} while (!valido);
			valido = false;
			ret.setCentesimas(c);

			valido = Validaciones.validarTiempo(ret);
			if (!valido)
				System.out.println("El valor introducido para el Tiempo total no es válido.Repita la operación.");
		} while (!valido);
		System.out.println("El valor introducido para el tiempo es: " + ret);
		return ret;
	}


	///Examen 11 Ejercicio 1-EVAL2
	@Override
	public int compareTo(Tiempo o) {
		int ret = 0;
		ret = Integer.compare(this.getHoras(), o.getHoras());
		if (ret == 0) {
			ret = Integer.compare(this.getMinutos(), o.getMinutos());
			if (ret == 0) {
				ret = Integer.compare(this.getSegundos(), o.getSegundos());
				if (ret == 0) {
					ret = Integer.compare(this.getCentesimas(), o.getCentesimas());
//					return ret;
				}
//				else
//					return ret;
			}
//			else
//				return ret;
		}
//		else
//			return ret;

		return ret;
	}
}
