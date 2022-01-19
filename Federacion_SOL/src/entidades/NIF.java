package entidades;

import java.util.Scanner;
import com.aeat.valida.*;

public class NIF extends Documentacion {
	String numero;
	char letraFinal;

	public NIF() {
	}

	public NIF(String numero, char letraF) {
		super();
		this.numero = numero;
		this.letraFinal = letraF;
	}

	public NIF(String cadena) {
		super();
		this.numero = cadena.substring(0, cadena.length());
		this.letraFinal = cadena.charAt(cadena.length() - 1);
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
		return numero + letraFinal;
	}

	public static NIF nuevoNIF() {
		NIF ret = null;
		Scanner in;
		boolean valido = false;
		String cadena;
		do {
			System.out.println("Introduzca el NIF completo:");
			in = new Scanner(System.in);
			cadena = in.nextLine();
			if (cadena.length() > 3)
				valido = true;
		} while (!valido);

		ret = new NIF(cadena);
		return ret;
	}

	@Override
	public boolean validar() {
		return NIF.validarNIF(this);
	}

	/**
	 * 
	 * Funcion que valida si una cadena de caracteres que se pasa como parámetro
	 * tiene un NIF válido.
	 * 
	 * @param nif cadena con el NIF a validar
	 * @return true si la cadena nif es un NIF válido o false en caso contrario
	 */
	private static boolean validarNIF(String nif) {
		boolean ret = false;
		if (nif.length() != 9)
			ret = false;
		if (!Character.isLetter(nif.charAt(nif.length() - 1)))
			ret = false;
		// Usamos validador de AEAT --> valnif.jar
		Validador val = new Validador();
		ret = (val.checkNif(nif) > 0 ? true : false);
		return ret;
	}

	private static boolean validarNIF(NIF nif) {
		boolean ret = false;
		if (nif.numero.length() != 8)
			ret = false;
		if (!Character.isLetter(nif.letraFinal))
			ret = false;
		// Usamos validador de AEAT --> valnif.jar
		Validador val = new Validador();
		ret = (val.checkNif(nif.mostrar()) > 0 ? true : false);
		return ret;
	}

}
