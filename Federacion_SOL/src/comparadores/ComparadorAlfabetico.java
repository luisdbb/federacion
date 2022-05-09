package comparadores;

import java.util.Comparator;

import entidades.DatosPersona;

///Examen 9 ejercicio 1-B
public class ComparadorAlfabetico implements Comparator<DatosPersona>{

	@Override
	public int compare(DatosPersona o1, DatosPersona o2) {
		return o1.getNombre().compareTo(o2.getNombre());
	}
	
	

}
