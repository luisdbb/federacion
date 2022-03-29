package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.ConexBD;

//Examen 1 Ejercicio 1
public enum Lugar {
	GIJONMESTAS(1, "Las Mestas", "Gijon", true), 
	GIJONCENTRO(2, "Centro ciudad", "Gijon", true),
	OVIEDOSANFRANCISCO(3, "Parque San Francisco", "Oviedo", true), 
	AVILESPUERTO(4, "Puerto", "Aviles", true),
	AVILESPABELLON(5, "Pabellon deportivo Aviles", "Aviles", false), 
	OVIEDOCENTRO(6, "Centro ciudad", "Oviedo", true);

	private int id;
	private String nombre;
	private String ubicacion;
	private boolean airelibre;

	private Lugar(int id, String nombre, String ubicacion, boolean airelibre) {
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.airelibre = airelibre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isAirelibre() {
		return airelibre;
	}


}
