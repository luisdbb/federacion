package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;

import DAO.PatrocinadorDAO;
import entidades.*;
import utils.*;

public class PrincipalExamen10 {

	public static void main(String[] args) {
		///Examen 10 ejercicio 8B
		/**
		 *  1 ALSA 		  500.00 www.alsa.es 		1 	902422202 00:00 23:59 1011
			2 Ayto. Gijón 250.00 www.gijon.es 		2 	985181105 09:00 18:00 1012
			3 Universidad 350.50 www.uniovi.es 		3 	985103000 08:30 20:00 1013
			4 CIFP La	  255.99 www.cifplalaboral.es 4 985185503 08:30 18:00 1014
		 * */
		//Responsable(String telefonoProf, LocalTime horarioIni, LocalTime horarioFin, DatosPersona persona)
		Responsable r1 = new Responsable(1,"902422202", LocalTime.of(0, 0), LocalTime.of(23, 59), Datos.buscarPersonaPorId(1011)) ;
		Responsable r2 = new Responsable(2,"985181105", LocalTime.of(9, 0), LocalTime.of(18, 0), Datos.buscarPersonaPorId(1012));
		Responsable r3 = new Responsable(3,"985103000", LocalTime.of(8, 30), LocalTime.of(20, 0), Datos.buscarPersonaPorId(1013));
		Responsable r4 = new Responsable(4,"985185503", LocalTime.of(8, 30), LocalTime.of(10, 0), Datos.buscarPersonaPorId(1014));
		//Patrocinador(long id, String nombre, String web, double dotacion, Responsable r) {
		Patrocinador p1 = new Patrocinador(1,"ALSA", "www.alsa.es", 500.00, r1);
		Patrocinador p2 = new Patrocinador(2,"Ayto. Gijón", "www.gijon.es", 250.00, r2);
		Patrocinador p3 = new Patrocinador(3, "Universidad de Oviedo","www.uniovi.es", 350.50, r3);
		Patrocinador p4 = new Patrocinador(4, "CIFP LaLaboral", "www.cifplalaboral.es", 255.99, r4);
		Patrocinador[] array = new Patrocinador[]{p1, p2, p3, p4};
		System.out.println("Se van a exportar los patrocinadores al fichero patrocinadores.dat.");
		exportarPatrocinadores(array);///probar ejercicio 6
		System.out.println("Terminó la exportación hacia fichero binario.\n");
		
		///probar ejercicio1
		System.out.println("Prueba del método Utilidades.leerHora(). Introduzca una nueva hora:");
		java.time.LocalTime hora = Utilidades.leerHora();
		System.out.println("La hora introducida es: "+ hora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		System.out.println("La hora introducida (sin formato) es: "+ hora);
		System.out.println("-----------------\n");
		
		System.out.println("Prueba del método Patrocinador.nuevoPatrocinador(). Introduzca los datos para un nuevo patrocinador:");
		Patrocinador nuevo = Patrocinador.nuevoPatrocinador();
		System.out.println("método Responsable.toString(): "+ nuevo.getResponsable());
		System.out.println("método Responsable.data(): "+ nuevo.getResponsable().data());
		System.out.println("método Patrocinador.mostrarBasico(): "+nuevo.mostrarBasico());
		System.out.println("método Patrocinador.mostrarCompleto(): "+nuevo.mostrarCompleto());
		System.out.println("método Patrocinador.data(): "+ nuevo.data());
		System.out.println("-----------------\n");
		
		System.out.println("Prueba del método Prueba.nuevaPrueba(). Introduzca los datos para una nueva prueba:");
		Prueba nuevaprueba = Prueba.nuevaPrueba();
		System.out.println("Los datos introducidos para la nueva prueba son:"+nuevaprueba);
		System.out.println("-----------------\n");
		
		System.out.println("Se van a insertar en la BD los patrocinadores:");
		PatrocinadorDAO aux = new PatrocinadorDAO(ConexBD.getCon());
		if(!aux.insertarConID(p1)) System.out.println("Error al insertar el patrocinador: "+p1);
		if(!aux.insertarConID(p2)) System.out.println("Error al insertar el patrocinador: "+p2);
		if(!aux.insertarConID(p3)) System.out.println("Error al insertar el patrocinador: "+p3);
		if(!aux.insertarConID(p4)) System.out.println("Error al insertar el patrocinador: "+p4);
		System.out.println("Se han insertado correctamente los datos de los patrocinadores en la BD.\n");
		ConexBD.cerrarConexion();
		System.out.println("-----------------\n");
		
		System.out.println("Se van a importar responsables desde el fichero de caracteres responsables.txt");
		Responsable.importarResponsables();
		System.out.println("-----------------\n");
		
		System.out.println("FIN");
	}

	///Examen 10 ejercicio 6
	/***
	 * Funcion que se le pasa como argumento un array con una colección de objetos
	 * Patrocinador. La función recorrerá toda la colección y exportará hacia un
	 * fichero binario de nombre patrocinadores.dat los datos de todas los
	 * patrocinadores, ordenados según el ejercicio anterior, de uno en uno.
	 */
	public static void exportarPatrocinadores(Patrocinador[] patrocinadores) {
		String path = "patrocinadores.dat";
		LinkedList<Patrocinador> lista = new LinkedList<Patrocinador>();
		for (Patrocinador p : patrocinadores) {
			lista.add(p);
		}
		Collections.sort(lista); ///se ordena la lista mediante el comparador de Patrocinador
		File f = new File(path);
		FileOutputStream fo;
		try {
			fo = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fo);
			for (Patrocinador p : lista) {
				oos.writeObject((Patrocinador) p);
				oos.flush();
			}
			oos.close();
			fo.close();
			System.out.println("Se han exportado correctamente los patrocinadores del array.");
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException:" + e.getMessage());
			e.printStackTrace();
		}

	}

}
