package validaciones;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aeat.valida.Validador;

import entidades.NIE;
import entidades.NIF;
import entidades.Tiempo;

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
	 * Función que valida un objeto NIE
	 * 
	 * @param nie objeto NIE que se va a validar
	 * @return true si el NIE pasado como parámetro es válido o false en caso
	 *         contrario
	 */
	public static boolean validarNIE(NIE nie) {
		return validarNIE(nie.mostrar());
	}

	public static boolean validarAltura(float altura) {
		return (altura > 0.0F);
	}

	public static boolean validarPeso(float peso) {
		return (peso > 0.0F);
	}

	public static boolean validarDireccion(String direccion) {
		return (direccion.length() > 3);
	}

	/**
	 * Función que valida un valor flotante para la pureza (en %)
	 * 
	 * @param pureza valor flotante de la pureza para validar
	 * @return true si es valor válido (entre 0,0 y 100,0) o false en caso contrario
	 */
	public static boolean validarPureza(float pureza) {
		return (pureza >= 0.0F && pureza <= 100.0F);
	}

	public static boolean validarFecha(LocalDate fechaMin) {
		// TODO Esbozo de método generado automáticamente
		return true;
	}

	public static boolean validarId(long id) {
		return (id > 0);
	}

	public static boolean validarAnio(int anio) {
		return (anio > 0);
	}

	public static boolean validarNombreEquipo(String nombre) {
		return true; // TODO
	}

	/// Examen 10 ejercicio 2
	/***
	 * Funcion que valida el teléfono profesional de un responsable de un
	 * patrocinador, que es una cadena de caracteres de 9 o 10 dígitos.
	 * 
	 * @param tfnProf la cadena con el telefono profesional a validar
	 * @return true si la cadena que se pasa como parametro es válida o false en
	 *         caso contrario
	 */
	public static boolean validarTelefonoProf(String tfnProf) {
		boolean ret = false;
		ret = tfnProf.trim().chars().allMatch(Character::isDigit) && (tfnProf.length() == 9 || tfnProf.length() == 10);
		return ret;
	}

	/***
	 * Funcion que valida la franja horaria de un responsable de un patrocinador
	 * 
	 * @param horaini hora de inicio de la franja horaria a validar
	 * @param horafin hora de fin de la franja horaria a validar
	 * @return true si la franja horaria se pasa como parametro es válida o false en
	 *         caso contrario
	 */
	public static boolean validarRangoHorario(LocalTime horaini, LocalTime horafin) {
		return horaini.isBefore(horafin);
	}

	/***
	 * Funcion que valida un nombre para un patrocinador: ha de ser único (que no se
	 * puede repetir), y conformado por una cadena de caracteres de entre 3 y 150
	 * caracteres, siendo válidos los alfabéticos (letras) o numéricos (dígitos)
	 * solamente
	 * 
	 * @param nombre con el nombre del patrocinador a validar
	 * @return true si el nombre que se pasa como parametro es válido para un
	 *         patrocinador o false en caso contrario
	 */
	public static boolean validarNombrePatrocinador(String nombre) {
		Pattern patron = Pattern.compile("[ A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ0123456789-]{3,150}");
		Matcher comprobacion = patron.matcher(nombre);
		return comprobacion.matches();
	}

	/***
	 * Funcion que valida la dotacion aportada por un patrocinador: ha de ser mayor
	 * de 100 euros y con 2 decimales como mucho (céntimos)
	 * 
	 * @param dotacion valor double con la dotacion a validar
	 * @return true si la dotacion que se pasa como parametro es válido para un
	 *         patrocinador o false en caso contrario
	 */
	public static boolean validarDotacion(double dotacion) {
		boolean aux = (dotacion * 1000) % 10 > 0;
		if (aux)
			return false;
		return (dotacion >= 100.00);
	}

	public static boolean validarWebPatrocinador(String web) {
		if (web.length() < 3 || web.length() > 150)
			return false;
		else
			return true;
	}

	public static boolean validarHoras(int h) {
		return (h >= 0);
	}

	public static boolean validarMinutos(int m) {
		return (m >= 0 && m <= 59);
	}

	public static boolean validarSegundos(int s) {
		return (s >= 0 && s <= 59);
	}

	public static boolean validarCentesimas(int c) {
		return (c >= 0 && c <= 99);
	}

	/***
	 * funcion que valida si un Tiempo es mayor que 00h 00m 00,00s
	 * 
	 * @param t
	 * @return
	 */
	public static boolean validarTiempo(Tiempo t) {
		boolean ret = false;
		if (!validarHoras(t.getHoras()))
			return false;
		if (!validarMinutos(t.getMinutos()))
			return false;
		if (!validarSegundos(t.getSegundos()))
			return false;
		if (!validarCentesimas(t.getCentesimas()))
			return false;

		if (t.getHoras() == 0)
			if (t.getMinutos() == 0)
				if (t.getSegundos() == 0)
					if (t.getCentesimas() == 0)
						ret = false;

		return true;
	}

	public static boolean validarMotivoPenalizacion(String otros) {
		return (!otros.equals("") && otros.length() <= 500);
	}

	public static boolean validarDorsal(int dorsal) {
		return (dorsal >= 1 && dorsal <= 150);
	}

	public static boolean validarCalle(char calle) {
		try {
			return Character.isLetter(calle);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validarNombrePrueba(String nombre) {
		Pattern patron = Pattern.compile("[ A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ0123456789-]{5,150}");
		Matcher comprobacion = patron.matcher(nombre);
		return comprobacion.matches();
	}

	/***
	 * Valida que la fecha que se pasa como argumento sea posterior al dia actual + 1 mes
	 * @param fecha
	 * @return true si la fecha es posterior a hoy +1 mes o false en caso contrario
	 */
	public static boolean validarFechaNuevaPrueba(Date fecha) {
		LocalDate hoyMas1MesLD = LocalDate.now().plusMonths(1);
		java.util.Date hoyMas1Mes = new Date(hoyMas1MesLD.getYear() - 1900, hoyMas1MesLD.getMonthValue() - 1, hoyMas1MesLD.getDayOfMonth());
		return fecha.after(hoyMas1Mes);
		
	}

	public static boolean validarFechaNuevoAtleta(Date fecha) {
		Date min = new Date(1960-1900, 1, 1);
		return fecha.after(min);
	}

}