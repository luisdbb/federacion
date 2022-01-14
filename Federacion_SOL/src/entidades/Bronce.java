package entidades;

import java.time.LocalDate;
import java.util.Scanner;

import utils.Utilidades;

public class Bronce extends Metal {
	private long id;
	private float pureza; // % pureza
	
	//Ejercicio 5, parte B
	private static float maxPureza;
	static private long idmaxpureza;

	public Bronce(long id, float pureza) {
		super();
		this.id = id;
		this.pureza = pureza;
		if (pureza > maxPureza) {
			maxPureza = pureza;
			idmaxpureza = id;
		}
	}

	public Bronce(int id, float pureza, LocalDate fecha) {
		super();
		this.id = id;
		this.pureza = pureza;
		if (pureza > maxPureza) {
			maxPureza = pureza;
			idmaxpureza = id;
		}
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPureza() {
		return pureza;
	}

	public void setPureza(float pureza) {
		this.pureza = pureza;
	}

	@Override
	public String toString() {
		return "B" + id + "(" + pureza + "%) de fecha:"+ this.getFecha()+" ¿asignado?="+this.isAsignada();
	}

	//Ejercicio 5, parte B
	@Override
	public float maximaPurezaAlcanzada() {
		return Bronce.maxPureza;
	}

	@Override
	public float[] cotasPurezaEfectiva() {
		float[] cotas = new float[2];
		cotas[0] = (float) (pureza - 0.35);
		cotas[1] = (float) (pureza + 0.35);
		return cotas;
	}

	public static long getIdMaxPureza() {
		return idmaxpureza;
	}
	
	public static Bronce nuevoBronce() {
		Bronce ret = null;
		Scanner in = new Scanner(System.in);
		int id = 0;
		do {
			System.out.println("Introduzca el id:");
			id = in.nextInt();
			if(id <= 0) 
				System.out.println("Valor incorrecto para el identificador.");
		}while(id <= 0);
		System.out.println("Introduzca la pureza:");
		float pureza = Float.parseFloat(String.valueOf(Utilidades.leerDouble()));
		System.out.println("Introduzca la fecha:");
		LocalDate fecha = Utilidades.leerFecha();
		ret = new Bronce(id, pureza, fecha);
		return ret;
	}
}
