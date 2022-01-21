package entidades;

import java.util.Scanner;
import com.aeat.valida.*;

import validaciones.Validaciones;

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
			valido = Validaciones.validarNIF(cadena);
		} while (!valido);

		ret = new NIF(cadena);
		return ret;
	}

	@Override
	public boolean validar() {
		return Validaciones.validarNIF(this);
	}

}
