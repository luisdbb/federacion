package entidades;

//Ejercicio 1
public enum Lugar {
	GIJONMESTAS("Las Mestas", "Gijon", true),
	GIJONCENTRO("Centro ciudad", "Gijon", true),
	OVIEDOSANFRANCISCO("Parque San Francisco", "Oviedo", true),
	AVILESPUERTO("Puerto", "Aviles", true),
	AVILESPABELLON("Pabellon deportivo Aviles", "Aviles", false),
	OVIEDOCENTRO("Centro ciudad", "Oviedo", true);

	private String nombre;
	private String ubicacion;
	private boolean airelibre;

	private Lugar(String nombre, String ubicacion, boolean airelibre) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.airelibre = airelibre;
	}

	public String getNombre() {
		return nombre + " " + ubicacion;
	}

	public boolean isAirelibre() {
		return airelibre;
	}


}
