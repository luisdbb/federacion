package utils;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import entidades.Tiempo;
import validaciones.Validaciones;

/**
 *
 * @author luis
 */
public class Utilidades extends Validaciones {

	/**
	 * Función que pide al usuario que introduzca 's' o 'S' para Sí o 'n' o 'N' para
	 * No y devuelve true o false en cada caso. Si el usuario no introduce ni 's' ni
	 * 'S' ni 'n' ni 'N' entonces avisa al usuario y le vuelve a pedir a que lo
	 * introduzca de nuevo.
	 *
	 * @return true si el usuario introduce 's' o 'S'; false si el usuario introduce
	 *         'n' o 'N'
	 */
	public static boolean leerBoolean() {
		boolean ret;
		Scanner in;
		char resp;
		do {
			System.out.println("Pulse s para Sí o n para No");
			in = new Scanner(System.in, "ISO-8859-1");
			in.reset();
			resp = in.nextLine().charAt(0);
			if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
				System.out.println("Valor introducido incorrecto.");
			}
		} while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N');
		if (resp == 's' || resp == 'S') {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}

	/**
	 * Función que pide al usuario que introduzca un valor decimal por la entrada
	 * estándar. Si el formato introducido no es correcto, avisa al usuario y le
	 * vuelve a pedir que lo introduzca de nuevo.
	 *
	 * @return el valor double introducido por el usuario
	 */
	public static double leerDouble() {
		double ret = 0.0;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor decimal (xx,xx)");
			in = new Scanner(System.in, "ISO-8859-1");
			try {
				ret = in.nextDouble();
				correcto = true;
			} catch (InputMismatchException ime) {
				System.out.println("Formato introducido incorrecto.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente. Si los valores introducidos
	 * no producen una fecha correcta, avisa al usuario y le pide que los introduzca
	 * de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha de la clase java.time.LocalDate o null si hay error
	 */
	public static java.time.LocalDate leerFecha() {
		LocalDate ret = null;
		int dia, mes, anio;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();

			try {
				ret = LocalDate.of(anio, mes, dia);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	// Examen 3 Ejercicio 1
	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente Y una hora a partir de ptrps
	 * 3 valores para la hora, minutos y segundos. Si los valores introducidos no
	 * producen una fecha u hora correctas, avisa al usuario y le pide que los
	 * introduzca de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha-hora de la clase java.time.LocalDateTime o null si hay
	 *         error
	 */
	public static java.time.LocalDateTime leerFechaHora() {
		LocalDateTime ret = null;
		int dia, mes, anio;
		int hora, min, seg;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();
			System.out.println("Introduzca un valor para la hora del día (0...23)");
			in = new Scanner(System.in, "ISO-8859-1");
			hora = in.nextInt();
			System.out.println("Introduzca un valor para los minutos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			min = in.nextInt();
			System.out.println("Introduzca un valor para los segundos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			seg = in.nextInt();

			try {
				ret = LocalDateTime.of(anio, mes, dia, hora, min, seg);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha-hora introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/// Examen 10 Ejercicio 1
	/**
	 * Función que pide al usuario que introduce un valor para una hora a partir de
	 * 3 enteros para la hora del dica, minuto y segundo respectivamente. Si los
	 * valores introducidos no producen una hora correcta, avisa al usuario y le
	 * pide que los introduzca de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una hora de la clase java.time.LocalTime o null si hay error
	 */
	public static java.time.LocalTime leerHora() {
		java.time.LocalTime ret = null;
		int hora, minuto, segundo;
		boolean correcto = false;
		Scanner in;
		do {
			try {
				System.out.println("Introduzca un valor para la hora del dia (0...23)");
				in = new Scanner(System.in, "ISO-8859-1");
				hora = in.nextInt();
				System.out.println("Introduzca un valor para el minuto (0...59)");
				in = new Scanner(System.in, "ISO-8859-1");
				minuto = in.nextInt();
				System.out.println("Introduzca un valor para el segundo");
				in = new Scanner(System.in, "ISO-8859-1");
				segundo = in.nextInt();

				ret = LocalTime.of(hora, minuto, segundo);
				correcto = true;
			} catch (Exception e) {
				System.out.println(
						"Hora introducida incorrecta. Primero ingrese la hora, luego el minuto y finalmente el segundo.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	// Examen 4 Ejercicio 1
	/**
	 * Función que quita los espacios en blanco del comienzo y del final de una
	 * cadena de caracteres que se pasa como parámetro y, además, sustituye todas
	 * las vocales que tengan tilde por la correspondiente sin tilde, devolviendo la
	 * cadena resultante
	 * 
	 * @param s cadena original
	 * @return cadena original sin espacios en blanco al comienzo y final de la
	 *         cadena y sin vocales con tildes
	 */
	public static String quitarEspaciosTildes(String s) {
		String ret = s.trim();
		return ret.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u')
				.replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U');
	}

	public static String removeDiacriticalMarks(String string) {
		// Form.NFC acepta ñ y distingue las tildes en español
		return Normalizer.normalize(string, Form.NFC).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	// Examen 5 Ejercicio 1
	/**
	 * Función que pide al usuario que introduzca un valor decimal por la entrada
	 * estándar. Si el formato introducido no es correcto o fuera de rango, o si se
	 * produce cualquier otra excepción, avisa al usuario y le vuelve a pedir que lo
	 * introduzca de nuevo un valor.
	 *
	 * @return el valor float introducido por el usuario
	 */
	public static float leerFloat() {
		double ret = 0.0;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor decimal en formato xx,xx ");
			in = new Scanner(System.in, "ISO-8859-1");
			try {
				ret = in.nextDouble();
				correcto = true;
			} catch (InputMismatchException e1) {
				System.out.println("Formato introducido incorrecto o valor fuera de rango." + e1.getMessage());
				e1.printStackTrace();
				correcto = false;
			} catch (NoSuchElementException e2) {
				System.out.println("ERROR: the input is exhausted: " + e2.getMessage());
				e2.printStackTrace();
				correcto = false;
			} catch (IllegalStateException e3) {
				System.out.println("ERROR: this scanner is closed:" + e3.getMessage());
				e3.printStackTrace();
				correcto = false;
			} catch (Exception e) {
				System.out.println("ERROR: Se ha producido una excepción: " + e.getMessage());
				e.printStackTrace();
				correcto = false;
			}
		} while (!correcto);
		return Float.parseFloat("" + ret);
	}

	/***
	 * Funcion que se le pasa un valor double y devuelve su cadena de caracteres
	 * asociada con 2 decimales únicamente
	 * 
	 * @param valor
	 * @return
	 */
	public static String mostrarDouble2Decimales(double valor) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(valor);
	}

	/***
	 * Funcion que devuelve un valor real(double o float) con el nº de decimales que
	 * se quiera, pasando ambos adatos como parámetros a la función
	 * 
	 * @param numero          el valor real con decimales
	 * @param numeroDecimales el nº de decimales que deseamos
	 * @return el valor del primer argumento redondeado al nº de decimales que
	 *         marque el segundo argumento
	 */
	public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}



	public static int convertirAEntero(String aux) {
		int ret = -1;
		try {
			ret = Integer.valueOf(aux);
		} catch (Exception e) {
			System.out.println("ERROR: Problema al tomar el valor del entero.");
		}

		return ret;
	}

	/**
	 * Funcion que redondea un Tiempo de forma que un valor >50 en las centésimas
	 * incremente el valor de los segundos en 1 unidad (y luego siempre establecer a
	 * 0 las centésimas).
	 * 
	 * @param t
	 * @return
	 */
	public static Tiempo redondearCentesimas(Tiempo t) {
		Tiempo ret = new Tiempo();
		if (t.getCentesimas() > 50) {
			ret.setCentesimas(0);
			ret.setSegundos(t.getSegundos() + 1);
			if (ret.getSegundos() == 60) {
				ret.setSegundos(0);
				ret.setMinutos(t.getMinutos() + 1);
			} else
				ret.setMinutos(t.getMinutos());
			if (ret.getMinutos() == 60) {
				ret.setMinutos(0);
				ret.setHoras(t.getHoras() + 1);
			} else
				ret.setHoras(t.getHoras());
		} else {
			ret.setHoras(t.getHoras());
			ret.setMinutos(t.getMinutos());
			ret.setSegundos(t.getSegundos());
			ret.setCentesimas(0);
		}

		return ret;
	}


}
