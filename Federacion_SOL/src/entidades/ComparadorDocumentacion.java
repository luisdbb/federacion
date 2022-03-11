package entidades;

import java.util.Comparator;

public class ComparadorDocumentacion implements Comparator<Documentacion> {
	@Override
	public int compare(Documentacion o1, Documentacion o2) {
		return o1.mostrar().compareToIgnoreCase(o2.mostrar());
	}

}
