package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import comparadores.ComparadorTiempos;
import entidades.*;
import utils.Utilidades;

public class PrincipalExamen11 {

	public static void main(String[] args) {
		System.out.println("INICIO EXAM 11");

		/// Examen 11 Ejercicio 1-EVAL1
		/*
		 * Crear después en un programa principal (PrincipalExamen11.java) un array de
		 * 10 objetos Tiempo tomados a partir de sucesivas llamadas a la función
		 * nuevoTiempo(). Luego, recorrer ese array y mostrar por la salida estándar
		 * sólo los tiempos introducidos que sean menores de 1h. Al mismo tiempo que se
		 * recorre el array, almacenar sobre otras variables el mejor y el peor de todos
		 * ellos. Mostrar los datos del mejor y del peor Tiempo tras recorrer el array.
		 * 
		 */
		Tiempo[] tiempos = new Tiempo[10];
		System.out.println("Introduzca 10 nuevos tiempos:");
		for (int i = 0; i < tiempos.length; i++) {
			tiempos[i] = Tiempo.nuevoTiempo();
		}

		Tiempo mejor = new Tiempo();
		Tiempo peor = new Tiempo();
		for (Tiempo t : tiempos) {
			// mirar si el t actual es mejor que el mejor hasta el momento
			if (t.getHoras() > mejor.getHoras())
				mejor = t;
			else {
				if (t.getHoras() == mejor.getHoras()) {
					if (t.getMinutos() > mejor.getMinutos())
						mejor = t;
					else {
						if (t.getMinutos() == mejor.getMinutos()) {
							if (t.getSegundos() > mejor.getSegundos())
								mejor = t;
							else {
								if (t.getSegundos() == mejor.getSegundos())
									if (t.getCentesimas() > mejor.getCentesimas())
										mejor = t;
							}
						}
					}
				}
			}
			// mirar si el t actual es peor que el peor hasta el momento
			if (t.getHoras() < peor.getHoras())
				peor = t;
			else {
				if (t.getHoras() == peor.getHoras()) {
					if (t.getMinutos() < peor.getMinutos())
						peor = t;
					else {
						if (t.getMinutos() == mejor.getMinutos()) {
							if (t.getSegundos() < peor.getSegundos())
								peor = t;
							else {

								if (t.getSegundos() == peor.getSegundos())
									if (t.getCentesimas() > peor.getCentesimas())
										peor = t;
							}
						}
					}
				}
			}

			// mostrar sólo los tiempos introducidos que sean menores 1h
			if (!(t.getHoras() >= 1)) {
				System.out.println("Tiempo: " + t);
			}
		}
		System.out.println("Mejor Tiempo: " + mejor);
		System.out.println("Peor Tiempo: " + peor);
		/*
		 * Sobre el array anterior, volver a recorrerlo desde el comienzo y, en aquellas
		 * posiciones en las que haya un Tiempo < 1h, preguntar al usuario si desea
		 * modificar el valor. En caso afirmativo, realizar una llamada al método
		 * nuevoTiempo() para obtener y actualizar esa posición del array.
		 */
		for (int i = 0; i < tiempos.length; i++) {
			if (tiempos[i].getHoras() < 1) {
				System.out.println("El tiempo en la posicion " + i + " del array es < 1h: " + tiempos[i]);
				System.out.println("¿Desea modificarlo?");
				boolean respuesta = Utilidades.leerBoolean();
				if (respuesta) {
					System.out.println("Introduzca el nuevo valor para el tiempo en la posicion " + i + " del array:");
					tiempos[i] = Tiempo.nuevoTiempo();
				}
			}
		}
		importarTiempos("tiempos.dat");
		List<Prueba> pruebas = new ArrayList<Prueba>();
		recorrerPruebas(pruebas);

		System.out.println("FIN EXAM11");
	}

	/// Examen 11 Ejercicio 1-EVAL2
	/***
	 * Implementar una función que tome como parámetro la ubicación de un fichero
	 * binario que contiene distintos objetos Tiempo. Recorrer ese fichero para
	 * obtener la colección de los tiempos almacenados en el mismo y almacenarlos en
	 * una lista.
	 * 
	 * @param path
	 */
	public static void importarTiempos(String path) {
		LinkedList<Tiempo> lista = new LinkedList<Tiempo>();

		File f = new File(path);
		FileInputStream fi;
		try {
			fi = new FileInputStream(f);
			ObjectInputStream is = new ObjectInputStream(fi);
			Tiempo aux;
			while ((aux = (Tiempo) is.readObject()) != null) {
				lista.add(aux);
			}
			is.close();
			fi.close();
			System.out.println("Se han importado correctamente los tiempos desde el fichero " + path);
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}

		/*
		 * Posteriormente, ordenar la lista de acuerdo a los comparadores anteriores y
		 * recorrerla mediante un iterador. Aquellos tiempos que sean mayores de 1h
		 * eliminarlos de la lista. Para el resto, redondear el Tiempo de forma que un
		 * valor >50 en las centésimas incremente el valor de los segundos en 1 unidad
		 * (y luego siempre establecer a 0 las centésimas).
		 */
		Collections.sort(lista);
		Collections.sort(lista, new ComparadorTiempos());
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			Tiempo aux = (Tiempo) it.next();
			if (aux.getHoras() >= 1)
				lista.remove(aux);
			else
				Utilidades.redondearCentesimas(aux);

		}

	}

	/**
	 * Implementar una función que tome como parámetro una Lista de pruebas,
	 * ordenarla según el criterio anterior y recorrer todos sus elementos. Con
	 * ellos, se construirá un diccionario<Lugar, Set< idPruebas>> en el que se
	 * incluirán sólo los identificadores de aquellas pruebas que sean individuales,
	 * estableciendo una entrada para cada Lugar con los identificadores de las
	 * pruebas que se celebran en dicho lugar.
	 */
	public static void recorrerPruebas(List<Prueba> pruebas) {
		Collections.sort(pruebas);
		HashMap<Lugar, Set<Integer>> diccionario = new HashMap<Lugar, Set<Integer>>();
		Iterator it = pruebas.iterator();
		while (it.hasNext()) {
			Prueba p = (Prueba) it.next();
			if (p.isIndividual()) {
				Lugar l = p.getLugar();
				if (diccionario.containsKey(l)) {
					diccionario.get(l).add(Integer.valueOf("" + p.getId()));
				} else {
					diccionario.put(l, new java.util.HashSet<Integer>(Integer.valueOf("" + p.getId())));
				}
				System.out
						.println("La prueba de id " + p.getId() + " se ha añadido al diccionario por ser individual.");
			} else
				System.out.println("La prueba de id " + p.getId() + " no es individual.");
		}

	}
}
