package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import entidades.*;
import utils.*;
import validaciones.Validaciones;

public class Principal4 {

	public static void main(String[] args) {
		Datos.cerrarResultados();
		System.out.println("INICIO");

		Scanner in;
		int elecc = -1;
		Rol rol; // Examen 4 Ejercicio 3A
		boolean correcto = false;
		while (true) {
			System.out.println("Bienvenido al programa de gestión de la FEDERACIÓN DEPORTIVA:");
			do {
				System.out.println("Seleccione el id del tipo de usuario o pulse 0 para SALIR:");
				int i = 1;
				for (Rol r : Rol.values()) {
					System.out.println(i + " " + r.getNombre());
					i++;
				}
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc == 0) {
					System.out.println("¿Está seguro de que desea SALIR?");
					if (Utilidades.leerBoolean()) {
						System.out.println("¡ADIOS!");
						return;
					}
				}
				if (elecc >= 1 && elecc <= Rol.values().length)
					correcto = true;
				else
					System.out.println("¡Valor invalido para el ROL seleccionado!");
			} while (!correcto);
			rol = Rol.values()[elecc - 1];

			Credenciales cred; // Examen 4 Ejericicio 3B
			boolean login = false;
			switch (rol.ordinal()) {
			case 0: // Rol.DIRECTIVA;
			case 1: // Rol.MANAGER
			case 2: // Rol.ATLETA;
			case 3: // Rol.COLEGIADO;
			case 4: // Rol.ADMIN;
				do {
					System.out.println("Introduzca sus credenciales de acceso.");
					System.out.println("Introduzca su nombre de usuario:");
					String usuario, password;
					usuario = in.next();
					System.out.println("Introduzca su contraseña:");
					password = in.next();
					cred = new Credenciales(usuario, password);
					login = login(cred);
					if (!login)
						System.out.println("ERROR: Usuario o password incorrectos.");
					else
						System.out.println("Login correcto con rol " + rol.getNombre());
				} while (!login);
				break;
			case 5: // Rol.INVITADO;
				System.out.println("Ha ingresado con el rol " + rol.getNombre());
			}

			mostrarMenu(rol);
		}

	} // Final del main

	// Examen 3 Ejercicio 2 - Examen 4 Ejercicio 3C
	private static void mostrarMenu(Rol rol) {
		int elecc = -1;
		Scanner in = new Scanner(System.in);
		boolean valido = false;

		switch (rol.ordinal()) {
		case 0: // Rol.DIRECTIVA;
			do {
				mostrarMenuDirectiva();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuDirectiva(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 1: // Rol.MANAGER;
			do {
				mostrarMenuManager();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuManager(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER");
			break;
		case 2: // Rol.ATLETA
			do {
				mostrarMenuAtleta();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuAtleta(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 3: // Rol.COLEGIADO;
			do {
				mostrarMenuColegiado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuColegiado(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 4: // Rol.ADMIN;
			do {
				mostrarMenuAdmin();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 4) {
					valido = true;
					mostrarSubmenuAdmin(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 5: // Rol.INVITADO;
			do {
				mostrarMenuInvitado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuInvitado(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		default:
		}

	}

	private static void mostrarSubmenuDirectiva(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenús de la DIRECTIVA.");
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado 1.1 GESTIÓN de MEDALLAS.");
			do {
				mostrarMenuGestionMedallas();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				subelecc = in.nextInt();
				if (subelecc >= 0 && subelecc <= 2) {
					valido = true;
					mostrarSubmenuGestionMedallas(subelecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || subelecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 2:
			System.out.println("Ha seleccionado 1.2 GESTIÓN de COMPETICIONES y de PRUEBAS.");
			break;
		default:
		}

		System.out.println("Volviendo al menú de la DIRECTIVA...");
	}

	private static void mostrarMenuGestionMedallas() {
		System.out.println("Menú de GESTIÓN de MEDALLAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Nueva Medalla\n" + "2. Ver Medallas\n" + "0. Volver");
	}

	private static void mostrarSubmenuGestionMedallas(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("\nGESTIÓN de MEDALLAS.");
		switch (elecc) {
		case 1: // opción 1.1.1
			System.out.println("Ha seleccionado 1.1.1 Nueva MEDALLA.");
			do {
				System.out.println("Seleccione 1 para ORO, 2 para PLATA o 3 para BRONCE.");
				subelecc = in.nextInt();
				if (subelecc != 1 && subelecc != 2 && subelecc != 3)
					System.out.println("¡Valor seleccionado no válido!\n");
				else
					valido = true;
			} while (!valido);
			Metal nuevo;
			if (subelecc == 1) {
				System.out.println("Ha seleccionado Nuevo ORO");
				nuevo = Oro.nuevoOro();
			} else if (subelecc == 2) {
				System.out.println("Ha seleccionado Nueva PLATA");
				nuevo = (Plata) Plata.nuevoPlata();
			} else {
				System.out.println("Ha seleccionado Nuevo BRONCE");
				nuevo = (Bronce) Bronce.nuevoBronce();
			}
			System.out.println("Se ha introducido una nueva medalla correctamente.");
			System.out.println(nuevo);
			break;
		// Examen 5 Ejercicio 6
		case 2: // opción 1.1.2
			System.out.println("Ha seleccionado 1.1.2 Ver Medallas");
			boolean validar = false;
			boolean orosSi = false;
			boolean platasSi = false;
			boolean broncesSi = false;
			// Se pregunta al usuario por sus criterios de búsqueda
			do {
				System.out.println("Seleccione qué tipo de medallas filtrar:");
				System.out.println("¿Desea buscar Oros?");
				in = new Scanner(System.in);
				orosSi = Utilidades.leerBoolean();
				System.out.println("¿Desea buscar Platas?");
				in = new Scanner(System.in);
				platasSi = Utilidades.leerBoolean();
				System.out.println("¿Desea buscar Bronces?");
				in = new Scanner(System.in);
				broncesSi = Utilidades.leerBoolean();
				validar = orosSi || platasSi || broncesSi;
				if (!validar) {
					System.out.println("ERROR: Debe seleccionar al menos algún tipo de medallas.");
					continue;
				}
				System.out.print("Ha seleccionado buscar sobre " + (orosSi ? "oros " : " ")
						+ (platasSi ? "platas " : " ") + (broncesSi ? "bronces" : " "));
				System.out.println("\n¿Es correcto?");
				in = new Scanner(System.in);
				validar = Utilidades.leerBoolean();
			} while (!validar);

			System.out.println("¿Desea buscar por un rango de pureza?");
			boolean purezaSi = Utilidades.leerBoolean();
			float purezaMin = 0.0F;
			float purezaMax = 0.0F;
			if (purezaSi) {
				boolean purezaVal = false;
				do {
					System.out.println("Introduzca el valor para la pureza mínima:");
					purezaMin = Utilidades.leerFloat();
					purezaVal = Validaciones.validarPureza(purezaMin);
					if (!purezaVal)
						System.out.println("ERROR: Valor introducido para la pureza mínima no válido.");
				} while (!purezaVal);
				purezaVal = false;
				do {
					System.out.println("Introduzca el valor para la pureza máxima:");
					purezaMax = Utilidades.leerFloat();
					purezaVal = Validaciones.validarPureza(purezaMax);
					if (!purezaVal)
						System.out.println("ERROR: Valor introducido para la pureza máxima no válido.");
					else if (purezaMax < purezaMin) {
						System.out.println("ERROR: La pureza máxima debe ser mayor que la mínima.");
						purezaVal = false;
					}
				} while (!purezaVal);
			}

			System.out.println("¿Desea buscar por un rango de fechas?");
			boolean fechasSi = Utilidades.leerBoolean();
			LocalDate fechaMin = null;
			LocalDate fechaMax = null;
			if (fechasSi) {
				boolean fechaVal = false;
				do {
					System.out.println("Introduzca el valor para la fecha mínima:");
					fechaMin = Utilidades.leerFecha();
					fechaVal = Validaciones.validarFecha(fechaMin);
					if (!fechaVal)
						System.out.println("ERROR: Valor introducido para la fecha mínima no válido.");
				} while (!fechaVal);
				fechaVal = false;
				do {
					System.out.println("Introduzca el valor para la fecha máxima:");
					fechaMax = Utilidades.leerFecha();
					fechaVal = Validaciones.validarFecha(fechaMax);
					if (!fechaVal)
						System.out.println("ERROR: Valor introducido para la fecha máxima no válido.");
					else if (fechaMax.isBefore(fechaMin)) {
						System.out.println("ERROR: La fecha máxima debe ser posterior a la mínima.");
						fechaVal = false;
					}
				} while (!fechaVal);
			}

			System.out.println("¿Desea buscar solo entre medallas ya asignadas?");
			boolean asignadasOnly = Utilidades.leerBoolean();

			// Se prepara un array medallasFiltradas que se inicializa con todas las
			// medallas del sistema (primero Oros, luego Platas y por último Bronces
			int totalMedallas = Datos.OROS.length + Datos.PLATAS.length + Datos.BRONCES.length;
			Metal[] medallasFiltradas = new Metal[totalMedallas];
			for (int i = 0; i < totalMedallas;) {
				for (int j = 0; j < Datos.OROS.length; j++, i++)
					medallasFiltradas[i] = Datos.OROS[j];
				for (int j = 0; j < Datos.PLATAS.length; j++, i++)
					medallasFiltradas[i] = Datos.PLATAS[j];
				for (int j = 0; j < Datos.BRONCES.length; j++, i++)
					medallasFiltradas[i] = Datos.BRONCES[j];
			}
			// se filtra cada elemento de medallasFiltradas en función de los criterios de
			// búsqueda seleccionados
			int index = 0;
			if (orosSi) {
				for (int i = 0; i < Datos.OROS.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Oro) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = index; i < Datos.OROS.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}
			if (platasSi) {
				for (int i = 0; i < Datos.PLATAS.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Plata) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = 0; i < Datos.PLATAS.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}

			if (broncesSi) {
				for (int i = 0; i < Datos.BRONCES.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Bronce) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = 0; i < Datos.BRONCES.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}

			// mostrar las medallas que cumplen los criterios de búsqueda
			int numMedallasFiltradas = 0;
			for (int i = 0; i < medallasFiltradas.length; i++)
				if (medallasFiltradas[i] != null)
					numMedallasFiltradas++;
			System.out.println("Hay " + numMedallasFiltradas + " medallas que cumplen sun criterios de búsqueda.");
			if (numMedallasFiltradas > 0) {
				System.out.println("Las siguientes medallas cumplen sus criterios de búsqueda:");
				for (int i = 0; i < medallasFiltradas.length; i++)
					if (medallasFiltradas[i] != null)
						System.out.println("medalla: " + medallasFiltradas[i].toString());
			} else
				System.out.println("No hay medallas que cumplan sus criterios de búsqueda.");

			break;
		default:
		}
		System.out.println("Volviendo al menú principal de gestión de medallas...");

	}

	private static void mostrarSubmenuManager(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: // opción 2.1
			System.out.println("Ha seleccionado 2.1 CONFORMAR EQUIPO.");
			Equipo nuevo = Equipo.nuevoEquipo();
			System.out.println("Se ha creado el nuevo equipo:" + nuevo);
			break;
		case 2: // opción 2.2
			/// Examen 8 Ejercicio 1 partesBC
			System.out.println("Ha seleccionado 2.2 INSCRIPCIÓN de EQUIPO en PRUEBA.");
			inscripcionEquipoPrueba(in);
			break;
		default:
		}
		System.out.println("Volviendo al menú de MÁNAGERS...\n\n");
	}

	private static void mostrarSubmenuAtleta(int elecc) {
		Scanner in = new Scanner(System.in);
		Atleta nuevo;
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opción 3.1
			System.out.println("Ha seleccionado 3.1 FEDERARSE (Nuevo ATLETA).");
			nuevo = Atleta.nuevoAtleta();
			System.out.println("El nuevo atleta introducido es: " + nuevo);
			break;
		case 2: // opción 3.2
			inscripcionAtletaPrueba(in);

			break;
		default:
		}
		System.out.println("Volviendo al menú de ATLETAS...\n\n");
	}

	private static void mostrarSubmenuColegiado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opción 4.1
			System.out.println("Ha seleccionado 4.1 Nuevo COLEGIADO.");
			Colegiado nuevo = Colegiado.nuevoColegiado();
			System.out.println("Se ha creado correctamente el nuevo colegiado:" + nuevo);
			break;
		case 2: //// opción 4.2
			System.out.println("Ha seleccionado 4.2 INTRODUCIR RESULTADOS de PRUEBA..");
			break;
		default:
		}
		System.out.println("Volviendo al menú de COLEGIADOS...\n\n");
	}

	private static void mostrarSubmenuAdmin(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado Gestión de medallas, de competiciones y de pruebas.");
			break;
		case 2:
			System.out.println("Ha seleccionado Gestión de equipos.");
			break;
		case 3:
			System.out.println("Ha seleccionado Gestión de atletas.");
			break;
		case 4:
			System.out.println("Ha seleccionado Gestión de arbitrajes y resultados.");
			break;
		default:
		}
		System.out.println("Volviendo al menú de ADMINISTRADORES...\n\n");
	}

	private static void mostrarSubmenuInvitado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenús para INVITADOS.");
		System.out.println("Volviendo al menú principal...\n\n");
	}

	private static void mostrarMenuDirectiva() {
		System.out.println("Menú de la DIRECTIVA.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1.1 Gestión de medallas\n" + "1.2 Gestión de competiciones y pruebas.\n" + "0. Volver");
	}

	private static void mostrarMenuManager() {
		System.out.println("Menú para los MÁNAGERS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("2.1 Conformar equipo\n" + "2.2 Inscripción de equipo en prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAtleta() {
		System.out.println("Menú para los ATLETAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("3.1 Federarse (nuevo Atleta)\n" + "3.2 Inscripción de atleta en prueba.\n" + "0. Volver");

	}

	private static void mostrarMenuColegiado() {
		System.out.println("Menú para los COLEGIADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("4.1 Nuevo Colegiado\n" + "4.2 Introducir resultados de prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAdmin() {
		System.out.println("Menú para los ADMINISTRADORES.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Gestión de medallas, de competiciones y de pruebas\n" + "2. Gestión de equipos.\n"
				+ "3. Gestión de atletas.\n" + "4. Gestión de arbitrajes y resultados.\n" + "0. Volver");
	}

	private static void mostrarMenuInvitado() {
		System.out.println("Menú para los INVITADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("" + "0. Volver");
	}

	/// Examen 6 Ejercicio 1
	/**
	 * Función para el login de un usuario mediante sus credenciales de acceso
	 * 
	 * @param cred credenciales de acceso (2 cadenas de caracteres para usuario y
	 *             passsword)
	 * @return true si las credenciales coinciden con alguna de las contenidas en el
	 *         fichero de caracteres credenciales.txt o false en caso contrario
	 */
	private static boolean login(Credenciales cred) {
		boolean ret = false;
		File fichero = new File("credenciales.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					String user = campos[0];
					String pass = campos[1];
					String rol = campos[2];
					if (user.equals(cred.getUsuario()))
						if (pass.equals(cred.getPassword()))
							return true;
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		return ret;
	}

	/// Examen 6 Ejercicio 2
	/***
	 * (Máx 2ptos.) Implementar una función para recorrer todos los elementos del
	 * array ATLETAS de la clase Datos.java, y exportar a un fichero binario de
	 * nombre juniors.dat sólo aquellos atletas considerados como Junior (es decir,
	 * cuya fechaNac posterior al 01/01/2000).
	 */
	public static void exportarAtletasJunior() {
		String path = "juniors.dat";
		try {
			FileOutputStream fichero = new FileOutputStream(path, false); // el 2º argumento a true para que concatene
																			// al final del fichero, en lugar de
																			// sobreescribir
			ObjectOutputStream escritor = new ObjectOutputStream(fichero);
			for (Atleta a : Datos.ATLETAS) {
				if (a.getPersona().getFechaNac().isAfter(LocalDate.of(2000, 1, 1))) {
					escritor.writeObject((Atleta) a);
					escritor.flush();
				}
			}
			escritor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

	/// Examen 7 Ejercicio 2
	/***
	 * (Máx 1,5ptos.) Implementar una función para recorrer todos los elementos del
	 * array COLEGIADOS de la clase Datos.java, y exportar a distintos ficheros
	 * binarios de nombres colegiadosjunior.dat, colegiadossenior.dat
	 * ycolegiadosespecial.dat los datos completos de cada uno de los colegiados en
	 * función de la categoría a la que pertenecen. Documentar adecuadamente la
	 * función implementada.
	 */
	public static void exportarColegiadosPorCategorias() {
		String pathJunior = "colegiadosjunior.dat";
		String pathSenior = "colegiadossenior.dat";
		String pathEspecial = "colegiadosespecial.dat";
		try {
			FileOutputStream ficheroJunior = new FileOutputStream(pathJunior);
			ObjectOutputStream escritorJunior = new ObjectOutputStream(ficheroJunior);
			FileOutputStream ficheroSenior = new FileOutputStream(pathSenior);
			ObjectOutputStream escritorSenior = new ObjectOutputStream(ficheroSenior);
			FileOutputStream ficheroEspecial = new FileOutputStream(pathEspecial);
			ObjectOutputStream escritorEspecial = new ObjectOutputStream(ficheroEspecial);

			for (Colegiado c : Datos.COLEGIADOS) {
				Categoria cat = c.getCategoria();
				switch (cat) {
				case JUNIOR:
					escritorJunior.writeObject((Colegiado) c);
					escritorJunior.flush();
					break;
				case SENIOR:
					escritorSenior.writeObject((Colegiado) c);
					escritorSenior.flush();
					break;
				case ESPECIAL:
					escritorEspecial.writeObject((Colegiado) c);
					escritorEspecial.flush();
					break;

				}
			}
			escritorJunior.close();
			escritorSenior.close();
			escritorEspecial.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

	/// Examen 7 Ejercicio 3
	private static void mostrarManagerEquipo() {
		File fichero = new File("managers.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					/// <DatosPersona.id>|<DatosPersona.nombre>|<DatosPersona.documentacion>|<DatosPersona.fechaNac>|<DatosPersona.telefono>
					/// |<Manager.id>|<Manager.telefono>|<Manager.direccion>
					String DatosPersona_id = campos[0];
					String DatosPersona_nombre = campos[1];
					String DatosPersona_documentacion = campos[2];
					String DatosPersona_fechaNac = campos[3];
					String datosPersona_telefono = campos[4];

					String Manager_id = campos[5];
					String Manager_telefono = campos[6];
					String Manager_direccion = campos[7];
					boolean representaEquipo = false;
					Equipo equipoRepresentado = null;
					for (Equipo e : Datos.EQUIPOS) {
						if (e.getManager().getId() == Long.valueOf(Manager_id)) {
							representaEquipo = true;
							equipoRepresentado = e;
							break;
						}
					}
					if (!representaEquipo)
						System.out.println("El manager " + DatosPersona_nombre + " de id " + Manager_id
								+ " no representa a ningún equipo.");
					else {
						String cad = "";
						cad += "D./Dña. " + DatosPersona_nombre + " con NIF:NIE " + DatosPersona_documentacion
								+ " nacido el " + DatosPersona_fechaNac + " representa al equipo "
								+ equipoRepresentado.getNombre() + " de id " + equipoRepresentado.getId()
								+ " durante el año " + equipoRepresentado.getAnioinscripcion()
								+ ", el cual está conformado por lossiguientes atletas:";
						for (Atleta a : equipoRepresentado.getAtletas())
							cad += "\t" + a.toString() + "\n";
						System.out.println(cad);
					}
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

	/// Examen 8 Ejercicio 3
	private static void mapearManagersEquipo() {
		File fichero = new File("managers.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		TreeMap<Documentacion, Equipo> mapeosAux = new TreeMap<Documentacion, Equipo>(); ///Si lo hacemos así deberia tomar el Comprable de Documentacion
		///Se crea la variable diccionario que usa el ComparadorDocumentacion, que seguro funciona
		TreeMap<Documentacion, Equipo> mapeos = new TreeMap<Documentacion, Equipo>(new ComparadorDocumentacion());
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					/// <DatosPersona.id>|<DatosPersona.nombre>|<DatosPersona.documentacion>|<DatosPersona.fechaNac>|<DatosPersona.telefono>
					/// |<Manager.id>|<Manager.telefono>|<Manager.direccion>
					String DatosPersona_id = campos[0];
					String DatosPersona_nombre = campos[1];
					String DatosPersona_documentacion = campos[2];
					String DatosPersona_fechaNac = campos[3];
					String datosPersona_telefono = campos[4];

					String Manager_id = campos[5];
					String Manager_telefono = campos[6];
					String Manager_direccion = campos[7];
					boolean representaEquipo = false;
					Equipo equipoRepresentado = null;  ///Si no representa a nadie, se toma null para ese Mánager
					
					for (Equipo e : Datos.EQUIPOS) {
						if (e.getManager().getId() == Long.valueOf(Manager_id)) {
							representaEquipo = true;
							equipoRepresentado = e;
							break;
						}
					}
					///Se construye el objeto Documentacion a partir del String DatosPersona_documentacion
					Documentacion NIFNIEManager;
					NIFNIEManager = new NIE(DatosPersona_documentacion);
					///Si la primera letra de la Documentacion del manager no es una letra, entonces es que es un NIF
					if(!Character.isLetter(((NIE)NIFNIEManager).getLetraInicial()))
						NIFNIEManager = new NIF(DatosPersona_documentacion);
					///se añade la nueva entrada al diccionario
					mapeos.put(NIFNIEManager, equipoRepresentado);
//					mapeosAux.put(NIFNIEManager, equipoRepresentado);
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		Set<Entry<Documentacion, Equipo>> managers = mapeos.entrySet();
//		Set<Entry<Documentacion, Equipo>> managers = mapeosAux.entrySet();
		Iterator<Entry<Documentacion, Equipo>> it = managers.iterator();
		while(it.hasNext()) {
			Entry<Documentacion, Equipo> NIFNIE_Manager = it.next(); 
			String cad = "El manager de NIF/NIE "+NIFNIE_Manager.getKey().mostrar();
			Equipo representado = mapeos.get(NIFNIE_Manager.getValue());
			if(representado!= null)
				cad += " representa al equipo " + representado.toString();
			else
				cad += " no representa a ningún equipo.";
			System.out.println(cad);
		}
	}

	/// Examen 7 Ejercicio 4
	/***
	 * Funcion que solicita los datos para la inscripcion de un nuevo atleta en una
	 * prueba individual cuyos datos se obtienen desde el fichero de caracteres
	 * pruebas.txt Se pide confirmacion y se crea un fichero binario con los datos
	 * de la inscripcion de nombre inscrip_<idPrueba>_<NIF/NIE>.dat Luego se
	 * verifica la validez e integridad del fichero creado, leyendo su contenido y
	 * mostrando por la salida estándar los datos de la inscripcion del atleta en la
	 * prueba individual
	 * 
	 * @param in objeto de entrada estándar
	 */
	private static void inscripcionAtletaPrueba(Scanner in) {
		Atleta nuevo;
		int subelecc;
		boolean valido;
		System.out.println("Ha seleccionado 3.2 INSCRIPCIÓN de ATLETA en PRUEBA..");
		/// Examen 7 Ejercicio 1
		/// Se piden los datos del atleta que se va a inscribir
		do {
			System.out.println("Introduzca los datos del Atleta:");
			nuevo = Atleta.nuevoAtleta();
			System.out.println("¿Son correctos los datos del atleta introducido?" + nuevo);
			if (valido = Utilidades.leerBoolean()) {
				valido = true;
			}
		} while (!valido);
		/// Se muestran las pruebas individuales importadas desde el fichero de
		/// caracteres pruebas.txt
		valido = false;
		Prueba[] individuales = new Prueba[256];
		File fichero = new File("pruebas.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		int i = 0; /// contador de pruebas individuales
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					long idPrueba = Long.valueOf(campos[0]);
					String nombrePrueba = campos[1];
					LocalDate fecha = LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/YYYY"));
					String lugarString = campos[3];
					/// Hay que convertir el String con el lugar a su correspondiente valor de la
					/// enum Lugar
					Lugar lugar = null;
					for (Lugar l : Lugar.values()) {
						if (l.name().equalsIgnoreCase(lugarString)) {
							lugar = l;
						}
					}
					boolean individual = Boolean.valueOf(campos[4]);
					Prueba p = new Prueba(idPrueba, nombrePrueba, fecha, lugar, individual);
					/// Solo se muestran al usuario las pruebas individuales, que se van guardando
					/// en el array individuales
					if (p.isIndividual()) {
						System.out.println("" + p);
						individuales[i] = p;
						i++;
					}
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		/// Se pide al usuario que elija una de las pruebas y se comprueba que es un
		/// valor correcto
		Prueba pruebaSelecc = null;
		do {
			System.out.println("Introduzca el id de la prueba en que desea inscribirse:");
			subelecc = in.nextInt();
			for (int j = 0; j < i; j++) {
				if (((Prueba) individuales[j]).getId() == subelecc) {
					/// El valor introducido es alguno de los idPrueba individuales
					pruebaSelecc = individuales[j];
					valido = true;
					break;
				}
			}
			if (!valido) {
				System.out.println(
						"El valor " + subelecc + " no es válido. Se le mostrarán de nuevo las pruebas individuales:");
				for (Prueba aux : individuales) {
					if (aux != null) {
						System.out.println("" + aux);
					}
				}
			} else {
				System.out.println("Se ha elegido la prueba de id:" + subelecc + ". ¿Es correcto?");
				if (valido = Utilidades.leerBoolean()) {
					break; /// confirmacion de idPrueba seleccionado correcto
				} else {
					System.out.println("Se le mostrarán de nuevo las pruebas individuales:");
					for (Prueba aux : individuales) {
						if (aux != null) {
							System.out.println("" + aux);
						}
					}
				}
			}
		} while (!valido);
		/// Ahora se crea el fichero con la inscripcion
		valido = false;
		String path = "inscrip_" + pruebaSelecc.getId() + "_" + nuevo.getPersona().getNifnie().mostrar() + ".dat";
		try {
			FileOutputStream ficheroInscrip = new FileOutputStream(path, false);
			ObjectOutputStream escritor = new ObjectOutputStream(ficheroInscrip);
			escritor.writeObject((Atleta) nuevo);
			escritor.writeObject((Long) pruebaSelecc.getId());
			LocalDateTime ahora = LocalDateTime.now();
			escritor.writeObject((LocalDateTime) ahora);
			escritor.flush();
			valido = true;
			escritor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		/// Si el fichero se creó exitosamente, se lee su contenido y se muestra el
		/// mensaje
		if (!valido) {
			System.out.println("ERROR: No se creó el fichero con la inscripcion.");
		} else {
			try {
				File ficheroLeido = new File(path);
				FileInputStream ficheroInscrip = new FileInputStream(ficheroLeido);
				ObjectInputStream lectorFichInsc = new ObjectInputStream(ficheroInscrip);
				Atleta atletaLeido = (Atleta) lectorFichInsc.readObject();
				Long idPruebaLeido = (Long) lectorFichInsc.readObject();
				LocalDateTime fechahoraLeida = (LocalDateTime) lectorFichInsc.readObject();
				System.out.println("Se ha creado el fichero " + path + " a "
						+ fechahoraLeida.format(DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss"))
						+ ", en el que el atleta " + atletaLeido.getId() + " de nombre "
						+ atletaLeido.getPersona().getNombre() + " y NIF/NIE "
						+ atletaLeido.getPersona().getNifnie().mostrar() + " queda" + "inscrito en la prueba "
						+ idPruebaLeido + " de nombre " + pruebaSelecc.getNombre() + " a celebrar en "
						+ pruebaSelecc.getLugar().getNombre() + " el día "
						+ pruebaSelecc.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ".");
				valido = true;
				lectorFichInsc.close();
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
		}
	}

	/// Examen 8 Ejercicio 1 parte B
	/***
	 * Funcion que solicita los datos para la inscripcion de un nuevo equipo en una
	 * prueba colectiva cuyos datos se obtienen desde el fichero de caracteres
	 * pruebas.txt Se pide confirmacion y se crea un fichero binario con los datos
	 * de la inscripcion de nombre inscrip_<idPrueba>_<NIF/NIE_Manager>.dat Luego se
	 * verifica la validez e integridad del fichero creado, leyendo su contenido y
	 * mostrando por la salida estándar los datos de la inscripcion del equipo en la
	 * prueba por equipos
	 * 
	 * @param in objeto de entrada estándar
	 */
	private static void inscripcionEquipoPrueba(Scanner in) {
		Equipo nuevo;
		int subelecc;
		boolean valido;
		System.out.println("Ha seleccionado 2.2 INSCRIPCIÓN de EQUIPO en PRUEBA.");
		/// Examen 8 Ejercicio 1 parte B
		/// Se piden los datos del equipo que se va a inscribir
		do {
			System.out.println("Introduzca los datos del Equipo:");
			nuevo = Equipo.nuevoEquipo();
			System.out.println("¿Son correctos los datos del equipo introducido?" + nuevo);
			if (valido = Utilidades.leerBoolean()) {
				valido = true;
			}
		} while (!valido);
		/// Se muestran las pruebas colectivas importadas desde el fichero de
		/// caracteres pruebas.txt
		valido = false;
		Prueba[] colectivas = new Prueba[256];
		File fichero = new File("pruebas.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		int i = 0; /// contador de pruebas colectivas
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					long idPrueba = Long.valueOf(campos[0]);
					String nombrePrueba = campos[1];
					LocalDate fecha = LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/YYYY"));
					String lugarString = campos[3];
					/// Hay que convertir el String con el lugar a su correspondiente valor de la
					/// enum Lugar
					Lugar lugar = null;
					for (Lugar l : Lugar.values()) {
						if (l.name().equalsIgnoreCase(lugarString)) {
							lugar = l;
						}
					}
					boolean individual = Boolean.valueOf(campos[4]);
					Prueba p = new Prueba(idPrueba, nombrePrueba, fecha, lugar, individual);
					/// Solo se muestran al usuario las pruebas colectivas (no individuales), que se
					/// van guardando
					/// en el array colectivas
					if (!p.isIndividual()) {
						System.out.println("" + p);
						colectivas[i] = p;
						i++;
					}
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		/// Se pide al usuario que elija una de las pruebas y se comprueba que es un
		/// valor correcto
		Prueba pruebaSelecc = null;
		do {
			System.out.println("Introduzca el id de la prueba en que desea inscribirse:");
			subelecc = in.nextInt();
			for (int j = 0; j < i; j++) {
				if (((Prueba) colectivas[j]).getId() == subelecc) {
					/// El valor introducido es alguno de los idPrueba colectivas
					pruebaSelecc = colectivas[j];
					valido = true;
					break;
				}
			}
			if (!valido) {
				System.out.println(
						"El valor " + subelecc + " no es válido. Se le mostrarán de nuevo las pruebas por equipos:");
				for (Prueba aux : colectivas) {
					if (aux != null) {
						System.out.println("" + aux);
					}
				}
			} else {
				System.out.println("Se ha elegido la prueba de id:" + subelecc + ". ¿Es correcto?");
				if (valido = Utilidades.leerBoolean()) {
					break; /// confirmacion de idPrueba seleccionado correcto
				} else {
					System.out.println("Se le mostrarán de nuevo las pruebas por equipos:");
					for (Prueba aux : colectivas) {
						if (aux != null) {
							System.out.println("" + aux);
						}
					}
				}
			}
		} while (!valido);
		/// Ahora se crea el fichero con la inscripcion
		valido = false;
		String path = "inscrip_" + pruebaSelecc.getId() + "_" + nuevo.getManager().getPersona().getNifnie().mostrar()
				+ ".dat";
		try {
			FileOutputStream ficheroInscrip = new FileOutputStream(path, false);
			ObjectOutputStream escritor = new ObjectOutputStream(ficheroInscrip);
			escritor.writeObject((Equipo) nuevo);
			escritor.writeObject((Long) pruebaSelecc.getId());
			LocalDateTime ahora = LocalDateTime.now();
			escritor.writeObject((LocalDateTime) ahora);
			escritor.flush();
			valido = true;
			escritor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		/// Si el fichero se creó exitosamente, se lee su contenido y se muestra el
		/// mensaje
		if (!valido) {
			System.out.println("ERROR: No se creó el fichero con la inscripcion.");
		} else {
			try {
				File ficheroLeido = new File(path);
				FileInputStream ficheroInscrip = new FileInputStream(ficheroLeido);
				ObjectInputStream lectorFichInsc = new ObjectInputStream(ficheroInscrip);
				Equipo equipoLeido = (Equipo) lectorFichInsc.readObject();
				Long idPruebaLeido = (Long) lectorFichInsc.readObject();
				LocalDateTime fechahoraLeida = (LocalDateTime) lectorFichInsc.readObject();
				System.out.println("Se ha creado el fichero " + path + " a "
						+ fechahoraLeida.format(DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss"))
						+ ", en el que el equipo " + equipoLeido.getId() + " de nombre " + equipoLeido.getNombre()
						+ " representado por " + equipoLeido.getManager().getPersona().getNombre() + " ("
						+ equipoLeido.getManager().getPersona().getNifnie().mostrar() + ") queda"
						+ "inscrito en la prueba " + idPruebaLeido + " de nombre " + pruebaSelecc.getNombre()
						+ " a celebrar en " + pruebaSelecc.getLugar().getNombre() + " el día "
						+ pruebaSelecc.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ".");
				valido = true;
				lectorFichInsc.close();
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
		}
	}

}
