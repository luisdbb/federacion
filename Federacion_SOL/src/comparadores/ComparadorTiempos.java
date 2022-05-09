package comparadores;

import java.util.Comparator;

import entidades.Tiempo;


///Examen 11 Ejercicio 1-EVAL2
public class ComparadorTiempos implements Comparator<Tiempo>{

	@Override
	public int compare(Tiempo o1, Tiempo o2) {
		int ret = 0;
		ret = Integer.compare(o1.getHoras(), o2.getHoras());
		if (ret == 0) {
			ret = Integer.compare(o1.getMinutos(), o2.getMinutos());
			if (ret == 0) {
				ret = Integer.compare(o1.getSegundos(), o2.getSegundos());
				if (ret == 0) {
					ret = Integer.compare(o1.getCentesimas(), o2.getCentesimas());
//					return ret;
				}
//				else
//					return ret;
			}
//			else
//				return ret;
		}
//		else
//			return ret;

		return ret;
	}

}
