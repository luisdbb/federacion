package utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class UtilsDNI {

	// Atributos
	private int numeroDNI;
	private char letraDNI;
	private String DNIcompleto;

	// Constantes
	private char letrasNIF[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
			'V', 'H', 'L', 'C', 'K', 'E' };

	private static char letrasNIE[] = { 'X', 'Y', 'Z' };

	private static char letrasCIF[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'U', 'V', 'W' };
	private static boolean NUMERO_O_LETRAS[] = { true, true, false, true, true, true, true, true, true, false, false,
			false, false, false, false, false, false, true, true, false }; // True = numero, false = letra
	private static char letrasCIFFINAL[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };

	private final int DIVISOR = 23;

	public UtilsDNI() {
		this.generarDNIAleatorio();
	}

	public UtilsDNI(int numeroDNI) {
		if (cuentaCifras(numeroDNI) >= 7 && cuentaCifras(numeroDNI) <= 8) {
			this.numeroDNI = numeroDNI;
			letraNIF();
			completarDNI();
		} else {
			numeroDNI = 0;
			letraDNI = ' ';
			DNIcompleto = "";
		}
	}

	public UtilsDNI(String DNIcompleto) {
		if (comprobarDNI(DNIcompleto)) {
			numeroDNI = Integer.parseInt(DNIcompleto.substring(0, DNIcompleto.length() - 1));
			letraDNI = DNIcompleto.charAt(DNIcompleto.length() - 1);
			completarDNI();
		} else {
			numeroDNI = 0;
			letraDNI = ' ';
			DNIcompleto = "";
		}
	}

	private void generarDNIAleatorio() {
		numeroDNI = generaNumeroAleatorio(1000000, 100000000);
		letraNIF();
		completarDNI();
	}

	private void completarDNI() {
		DNIcompleto = String.valueOf(numeroDNI) + letraDNI;
	}

	private void letraNIF() {
		if (cuentaCifras(numeroDNI) >= 7 && cuentaCifras(numeroDNI) <= 8) {
			int res = numeroDNI - (numeroDNI / DIVISOR * DIVISOR);
			letraDNI = letrasNIF[res];
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void letraNIF(String DNI) {
		if (DNI.length() >= 7 && DNI.length() <= 8) {
			try {
				int dni = Integer.parseInt(DNI);
				int res = dni - (dni / DIVISOR * DIVISOR);
				letraDNI = letrasNIF[res];
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean comprobarDNI(String DNI) {
		if (DNI.length() >= 8 && DNI.length() <= 9) {
			try {
				boolean correcto = false;
				int dni = Integer.parseInt(DNI.substring(0, DNI.length() - 1));
				int res = dni - (dni / DIVISOR * DIVISOR);
				String DNICalculado = String.valueOf(dni) + letrasNIF[res];
				if (DNICalculado.replace('0', ' ').trim().equals(DNI.replace('0', ' ').trim())) {
					correcto = true;
				}
				return correcto;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 8 u 9 cifras", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Genera un numero aleatorio entre dos numeros. NOTA: se incluye el minimo,
	 * pero no el maximo
	 * 
	 * @param minimo
	 * @param maximo
	 * @return numero entre minimo y maximo
	 */
	public static int generaNumeroAleatorio(int minimo, int maximo) {
		int num = (int) Math.floor(Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
		return num;
	}

	public int getNumeroDNI() {
		return numeroDNI;
	}

	public char getLetraDNI() {
		return letraDNI;
	}

	public String getDNIcompleto() {
		return DNIcompleto;
	}

	// metodos estaticos

	public static char letraNIF(int DNI) {
		if (cuentaCifras(DNI) >= 7 && cuentaCifras(DNI) <= 8) {
			final int divisor = 23;
			int res = DNI - (DNI / divisor * divisor);
			char letrasNIF[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
					'V', 'H', 'L', 'C', 'K', 'E' };
			return letrasNIF[res];
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	// Metodos estaticos
	public static int cuentaCifras(int num) {
		int contador = 0;
		if (num == 0) {
			return 1;
		} else if (num > 0) {
			// bucle que cuenta las cifras
			while (num > 0) {
				num = (int) Math.floor(num / 10);
				contador += 1;
			}
			return contador;
		} else {
			while (num < 0) {
				num = (int) Math.floor(num / 10);
				contador += 1;
			}
			return contador;
		}
	}

	public static char devolverLetraNIF(String DNI) {
		if (DNI.length() >= 7 && DNI.length() <= 8) {
			try {
				final int divisor = 23;
				int dni = Integer.parseInt(DNI);
				int res = dni - (dni / divisor * divisor);
				char letrasNIF[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
						'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
				return letrasNIF[res];
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				return ' ';
			}
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
			return ' ';
		}

	}

	public static boolean validarDNI(String DNI) {
		if (DNI.length() >= 8 && DNI.length() <= 9) {
			try {
				boolean correcto = false;
				final int divisor = 23;
				int dni = Integer.parseInt(DNI.substring(0, DNI.length() - 1));
				int res = dni - (dni / divisor * divisor);
				char letrasNIF[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
						'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
				String DNICalculado = String.valueOf(dni) + letrasNIF[res];
				if (DNICalculado.replace('0', ' ').trim().equals(DNI.replace('0', ' ').trim())) {
					correcto = true;
				}
				return correcto;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "El UtilsDNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	public static String generaDNIAleatorio() {
		int numero = generaNumeroAleatorio(1000000, 100000000);
		char letra = letraNIF(numero);
		if (cuentaCifras(numero) == 7) {
			return String.valueOf("0" + numero) + letra;
		} else {
			return String.valueOf(numero) + letra;
		}
	}

	public static String[] generaDNIsAletorios(int numeroDNIs) {
		String DNIs[] = new String[numeroDNIs];
		String dni;
		for (int i = 0; i < DNIs.length; i++) {
			do {
				dni = generaDNIAleatorio();
			} while (existeDocumento(DNIs, dni));
			DNIs[i] = dni;
		}
		return DNIs;
	}

	private static boolean existeDocumento(String[] documentos, String valor) {
		boolean encontrado = false;
		for (int i = 0; i < documentos.length && !encontrado; i++) {
			if (documentos[i] != null) {
				if (documentos[i].equals(valor)) {
					encontrado = true;
				}
			}
		}
		return encontrado;

	}

	public static String generarNIEAleatorio() {
		int numero = generaNumeroAleatorio(1000000, 9999999);
		int indiceLetraNIE = generaNumeroAleatorio(0, 2);
		char letraInicial = letrasNIE[indiceLetraNIE];
		char letra = devolverLetraNIF(String.valueOf(indiceLetraNIE) + String.valueOf(numero));
		return letraInicial + String.valueOf(numero) + letra;
	}

	public static String[] generaNIEsAletorios(int numeroNIEs) {
		String NIEs[] = new String[numeroNIEs];
		String nie;
		for (int i = 0; i < NIEs.length; i++) {
			do {
				nie = generarNIEAleatorio();
			} while (existeDocumento(NIEs, nie));
			NIEs[i] = nie;
		}
		return NIEs;
	}

	public static String generarCIFAleatorio() {
		int indiceLetraInicial = generaNumeroAleatorio(0, letrasCIF.length);
		char letraInicial = letrasCIF[indiceLetraInicial];
		String numerosProvincias[] = new String[89];
		for (int i = 0, valorActual = 1; i < numerosProvincias.length; i++, valorActual++) {
			if ((i + 1) < 10) {
				numerosProvincias[i] = "0" + valorActual;
			} else {
				if ((valorActual == 65)) {
					valorActual = 70;
				}
				if (valorActual == 86) {
					valorActual = 91;
				}
				numerosProvincias[i] = String.valueOf(valorActual);
			}
		}
		String digitoProvincia = numerosProvincias[generaNumeroAleatorio(0, numerosProvincias.length)];
		String restoNumeros = String.valueOf(generaNumeroAleatorio(10000, 99999));
		String numeroCompleto = digitoProvincia + restoNumeros;
		int A = 0;
		int B = 0;
		int digitoActual;
		for (int i = 0; i < numeroCompleto.length(); i++) {
			digitoActual = Integer.parseInt(String.valueOf(numeroCompleto.charAt(i)));
			if ((i + 1) % 2 == 0) {
				A += digitoActual;
			} else {
				B += sumaArray(devuelveDigitos(digitoActual * 2));
			}
		}

		int C = A + B;
		int D = Integer.parseInt(String.valueOf(String.valueOf(C).charAt(String.valueOf(C).length() - 1)));
		int resultado = 10 - D;
		String digitoControl;
		if (NUMERO_O_LETRAS[indiceLetraInicial]) {
			if (resultado == 10) {
				digitoControl = String.valueOf(0);
			} else {
				digitoControl = String.valueOf(resultado);
			}
		} else {
			digitoControl = String.valueOf(letrasCIFFINAL[resultado - 1]);
		}
		return letraInicial + numeroCompleto + digitoControl;
	}

	public static String[] generaCIFsAletorios(int numeroCIFs) {
		String CIFs[] = new String[numeroCIFs];
		String cif;
		for (int i = 0; i < CIFs.length; i++) {
			do {
				cif = generarCIFAleatorio();
			} while (existeDocumento(CIFs, cif));
			CIFs[i] = cif;
		}
		return CIFs;
	}

	private static int[] devuelveDigitos(int numeroInicial) {
		int numero = numeroInicial;
		int digitos[] = new int[cuentaCifras(numeroInicial)];
		int numero_solo;

		for (int i = 0; numeroInicial > 0; i++) {
			numero /= 10;
			numero_solo = numeroInicial - (numero * 10);
			digitos[i] = numero_solo;
			numeroInicial = numero;
		}
		return invertirArray(digitos);
	}

	private static int sumaArray(int array[]) {
		int suma = 0;
		for (int i = 0; i < array.length; i++) {
			suma += array[i];
		}
		return suma;
	}

	private static int[] invertirArray(int array[]) {
		int temp[] = new int[array.length];
		for (int i = temp.length - 1, j = 0; i >= 0; i--, j++) {
			temp[i] = array[j];
		}
		return temp;
	}

}
