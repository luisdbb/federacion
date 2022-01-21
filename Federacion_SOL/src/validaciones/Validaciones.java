package validaciones;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aeat.valida.Validador;

import entidades.NIE;
import entidades.NIF;

//Examen 5 Ejercicio 3
public class Validaciones {

	/**
	 * Valida que una cadena de caracteres contiene dígitos únicamente
	 * 
	 * @param tfn cadena con el telefono a validar
	 * @return true si es un telefono válido o false en caso contrario
	 */
	public static boolean validarTelefono(String tfn) {
		return tfn.trim().chars().allMatch(Character::isDigit);
	}

	/**
	 * Valida que una cadena de caracteres contiene letras o espacios únicamente,
	 * longitud entre 3 y 50 caractreres
	 * 
	 * @param nombre cadena con el nombre a validar
	 * @return true si es un nombre válido o false en caso contrario
	 */
	public static boolean validarNombre(String nombre) {
		// regEx general para cadena de caracteres con longitud entre 1 y 50 caracteres,
		// aceptando dígitos, letras MAYUS y minúsculas, con tildes, diréresis y
		// diferentes símbolos especiales
		// Pattern patron = Pattern.compile("[
		// 0-9A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ¡!¿?@#$%()=+-€/.,]{1,50}");
		Pattern patron = Pattern.compile("[ A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ-]{3,50}");
		Matcher comprobacion = patron.matcher(nombre);
		return comprobacion.matches();//
	}

	/**
	 * 
	 * Funcion que valida si una cadena de caracteres que se pasa como parámetro
	 * tiene un NIF válido.
	 * 
	 * @param nif cadena con el NIF a validar
	 * @return true si la cadena nif es un NIF válido o false en caso contrario
	 */
	public static boolean validarNIF(String nif) {
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

	/**
	 * Función que valida un objeto NIF
	 * 
	 * @param nif objeto NIF que se va a validar
	 * @return true si el NIF pasado como parámetro es válido o false en caso
	 *         contrario
	 */
	public static boolean validarNIF(NIF nif) {
		boolean ret = false;
		if (nif.getNumero().length() != 8)
			ret = false;
		if (!Character.isLetter(nif.getLetraFinal()))
			ret = false;
		// Usamos validador de AEAT --> valnif.jar
		Validador val = new Validador();
		ret = (val.checkNif(nif.mostrar()) > 0 ? true : false);
		return ret;
	}

	/**
	 * Funcion que valida si una cadena de caracteres que se pasa como parámetro
	 * tiene un NIE válido.
	 * 
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

	/**
	 * Función que valida un objeto NIe
	 * 
	 * @param nie objeto NIE que se va a validar
	 * @return true si el NIE pasado como parámetro es válido o false en caso
	 *         contrario
	 */
	public static boolean validarNIE(NIE nie) {
		return validarNIE(nie.mostrar());
	}

	public static boolean validarAltura(float altura) {
		return (altura <= 0.0F);
	}
	
	public static boolean validarPeso(float peso) {
		return (peso <= 0.0F);
	}

	public static boolean validarDireccion(String direccion) {
		return (direccion.length() > 3);
	}

	public static boolean validarPureza(float pureza) {
		return (pureza >= 0.0F && pureza <= 100.0F);
	}

	public static boolean validarFecha(LocalDate fechaMin) {
		// TODO Esbozo de método generado automáticamente
		return true;
	}
}