package entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import java.util.Iterator;

import utils.Datos;

///Examen 8 Ejercicio 2
public class ComparadorMedallas implements Comparator<Metal> {

	@Override
	public int compare(Metal o1, Metal o2) {
		return Integer.parseUnsignedInt(String.valueOf(o1.getPureza() - o2.getPureza()));
	}

	/**
	 * Funcion que muestra por la salida estándar la lista de medallas almacenadas
	 * en la clase Datos.java en los arrays OROS, PLATAS y BRONCES ordenadas en
	 * función de la pureza (a través de este comparador)
	 */
	public void medallasOrdenadas() {
		LinkedList<Metal> ret = new LinkedList<Metal>();
		for (Metal m : Datos.OROS) {
			ret.add(m);
		}
		for (Metal m : Datos.PLATAS) {
			ret.add(m);
		}
		for (Metal m : Datos.BRONCES) {
			ret.add(m);
		}
		Collections.sort(ret, new ComparadorMedallas());
		System.out.println("La lista ordenada de todas las medallas es:");
		Iterator<Metal> it = ret.iterator();
		int i = 1; // Marcador de posicion de la medalla (orden)
		while (it.hasNext()) {
			System.out.println(i + ": " + ((Metal)it.next()).toString() + " ");
			i++;
		}
	}

}
