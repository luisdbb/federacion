package entidades;

import java.util.Scanner;

public class NIE extends Documentacion {
	char letraInicial;
	String numero;
	char letraFinal;

	public NIE() {
	}

	public NIE(char letraI, String numero, char letraF) {
		super();
		this.letraInicial = letraI;
		this.numero = numero;
		this.letraFinal = letraF;
	}

	public NIE(String cadena) {
		super();
		this.letraInicial = cadena.charAt(0);
		this.numero = cadena.substring(1, cadena.length());
		this.letraFinal = cadena.charAt(cadena.length() - 1);
	}

	public char getLetraInicial() {
		return letraInicial;
	}

	public void setLetraInicial(char letraInicial) {
		this.letraInicial = letraInicial;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public char getLetraFinal() {
		return letraFinal;
	}

	public void setLetraFinal(char letraFinal) {
		this.letraFinal = letraFinal;
	}

	@Override
	public String mostrar() {
		return letraInicial + numero + letraFinal;
	}
	
	/**
	 * Funcion que valida si una cadena de caracteres que se pasa como parámetro tiene un NIE válido.
	 * @param nie cadena con el NIE a validar
	 * @return true si la cadena nie es un NIE válido o false en caso contrario
	 */
	public static boolean validarNIE(String nie) {
		boolean esValido = false;
		int i = 1;
		int caracterASCII = 0;
		char letra = ' ';
		int miNIE = 0;
		int resto = 0;
		char[] asignacionLetra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		if (nie.length() == 9 && Character.isLetter(nie.charAt(8)) && nie.substring(0, 1).toUpperCase().equals("X")
				|| nie.substring(0, 1).toUpperCase().equals("Y") || nie.substring(0, 1).toUpperCase().equals("Z")) {

			do {
				caracterASCII = nie.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			} while (i < nie.length() - 1 && esValido);
		}

		if (esValido && nie.substring(0, 1).toUpperCase().equals("X")) {
			nie = "0" + nie.substring(1, 9);
		} else if (esValido && nie.substring(0, 1).toUpperCase().equals("Y")) {
			nie = "1" + nie.substring(1, 9);
		} else if (esValido && nie.substring(0, 1).toUpperCase().equals("Z")) {
			nie = "2" + nie.substring(1, 9);
		}

		if (esValido) {
			letra = Character.toUpperCase(nie.charAt(8));
			miNIE = Integer.parseInt(nie.substring(0, 8));
			resto = miNIE % 23;
			esValido = (letra == asignacionLetra[resto]);
		}

		return esValido;
	}

	public static NIE nuevoNIE() {
		NIE ret = null;
		Scanner in;
		boolean valido = false;
		String cadena;
		do {
			System.out.println("Introduzca el NIE completo:");
			in = new Scanner(System.in);
			cadena = in.nextLine();
			if (cadena.length() > 3)
				valido = true;
		} while (!valido);

		ret = new NIE(cadena);
		return ret;
	}

	@Override
	public boolean validar() {
		return NIE.validarNIE(this.mostrar());
	}
	
	public static boolean validarNIE(NIE nie) {
		return validarNIE(nie.mostrar());
	}
}
