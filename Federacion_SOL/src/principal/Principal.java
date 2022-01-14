package principal;

import java.time.LocalDate;

import entidades.*;
import utils.Datos;

public class Principal {

	public static void main(String[] args) {
		Datos.cerrarResultados();
		System.out.println("INICIO");
		
		//Ejercicio EXAMEN2
		System.out.println("\nEjercicio EXAMEN2:");
		int npruebasInd = 0;
		for(Prueba pr: Datos.PRUEBAS) {
			if(pr.isIndividual()) {
				npruebasInd++;
				if(pr.cerrada()) {
					System.out.println("\nPrueba individual de id"+pr.getId() +" "+pr.getNombre());
					System.out.println("Fecha: "+pr.getFecha()+"\tLugar: "+ pr.getLugar().getNombre());
					System.out.println("Podio obtenido:");
					Resultado res = pr.getResultado();
					Participante[] podio = pr.getParticipantes();
					Participante ganador =podio[0];
					Participante segundo =podio[1];
					Participante tercero =podio[2];
					System.out.println("Ganador : "+ ganador.getId()); //((Atleta)ganador).getPersona().getNombre();
					System.out.println("2ºpuesto: "+ segundo.getId()); //((Atleta)segundo).getPersona().getNombre();
					System.out.println("3 puesto: "+ tercero.getId()); //((Atleta)tercero).getPersona().getNombre();
				}
			}
		}
		System.out.println("\nHay "+ npruebasInd +" pruebas individuales registradas en el sistema actualmente.");
		
		System.out.println("\nUso de nuevaPersona():");
		DatosPersona dp = DatosPersona.nuevaPersona();
		System.out.println("Los datos de la persona introducida son:\nID:"+ dp.getId()+"\tNOMBRE:"+dp.getNombre()+"\tTFN:"+dp.getTelefono()+"\tNIF/NIE:"+dp.getNifnie().mostrar()+"\tFECHANAC:"+dp.getFechaNac());
		

		// Ejercicio 4
		System.out.println("\nEjercicio 4 Atletas con altura superior a la media y peso inferior a la media:");
		float mediapeso = 0.0F;
		float mediaaltura = 0.0F;
		for (Atleta a : Datos.ATLETAS) {
			mediapeso += a.getPeso();
			mediaaltura += a.getAltura();
		}
		mediapeso = mediapeso / Datos.ATLETAS.length;
		mediaaltura = mediaaltura / Datos.ATLETAS.length;
		System.out.println("La altura media de los atletas es de " + mediaaltura + "m.");
		System.out.println("El peso medio de los atletas es de " + mediapeso + "Kgs.");
		for (Atleta a : Datos.ATLETAS) {
			if (a.getAltura() >= mediaaltura && a.getPeso() <= mediapeso)
				System.out.println("Atleta:" + a.getPersona().getNombre() + " de id:" + a.getId() + " supera la altura media ("
								+ a.getAltura() + ") y peso inferior a la media (" + a.getPeso() + ").");
			// palmares(a);
		}

		// Ejercicio 6.1
		System.out.println("\nEjercicio 6.1 LUGARES:");
		for (Lugar l : Lugar.values()) {
			System.out.println(l.getNombre() + " ¿al aire libre?:" + l.isAirelibre());
		}

		// Ejercicio 6.2
		System.out.println("\nEjercicio 6.2 Nueva Competicion:");
		Competicion c = Datos.COMPETICIONES[2]; //Competicion.nuevaCompeticion();
		System.out.println("Competicion de id:" + c.getId());
		System.out.println("Nombre:" + c.getNombre());
		System.out.println("Año inscripcion:" + c.getAnio());
		System.out.println("Consta de las siguentes pruebas:");
		for (Prueba p : c.getPruebas()) {
			System.out.println("idPrueba: " + p.getId() + "\tNombre: " + p.getNombre() + "\tFecha: " + p.getFecha()
					+ "\tLugar: " + p.getLugar().getNombre() + " ¿Individual?:" + p.isIndividual());
		}

		// Ejercicio 6.3
		System.out.println("\nEjercicio 6.3 Equipos con atletas junior:");
		for (Equipo e : Datos.EQUIPOS) {
			Atleta[] componentes = e.getAtletas();
			boolean hayjunior = false;
			for (Atleta a : componentes) {
				if (a.getPersona().getFechaNac().isAfter(LocalDate.of(2000, 1, 1))) {
					hayjunior = true;
					break;
				}
			}
			if (hayjunior) {
				System.out.println(e + "\n");
			}
		}

		// Ejercicio 6.5
		System.out.println("\nEjercicio 6.4 Datos de metales de máximas purezas");
		Oro o = Datos.OROS[0];
		Plata p = Datos.PLATAS[0];
		Bronce b = Datos.BRONCES[0];
		float maxpurezaoro = o.maximaPurezaAlcanzada();
		float maxpurezaplata = p.maximaPurezaAlcanzada();
		float maxpurezabronce = b.maximaPurezaAlcanzada();
		long idmaxpurezaoro = Oro.getIdMaxPureza();
		long idmaxpurezaplata = Plata.getIdMaxPureza();
		long idmaxpurezabronce = Bronce.getIdMaxPureza();
		for (Oro oro : Datos.OROS) {
			if (idmaxpurezaoro == oro.getId()) {
				System.out.println("El oro de mayor pureza es el de id:" + idmaxpurezaoro);
				float[] cotas = oro.cotasPurezaEfectiva();
				System.out.println(maxpurezaoro + "% --> pureza_min: " + cotas[0] + "% pureza_max: " + cotas[1] + "%");
			}
		}
		for (Plata plata : Datos.PLATAS) {
			if (idmaxpurezaplata == plata.getId()) {
				System.out.println("La plata de mayor pureza es el de id:" + idmaxpurezaplata);
				float[] cotas = plata.cotasPurezaEfectiva();
				System.out
						.println(maxpurezaplata + "% --> pureza_min: " + cotas[0] + "% pureza_max: " + cotas[1] + "%");
			}
		}
		for (Bronce bronce : Datos.BRONCES) {
			if (idmaxpurezabronce == bronce.getId()) {
				System.out.println("El bronce de mayor pureza es el de id:" + idmaxpurezabronce);
				float[] cotas = bronce.cotasPurezaEfectiva();
				System.out
						.println(maxpurezabronce + "% --> pureza_min: " + cotas[0] + "% pureza_max: " + cotas[1] + "%");
			}
		}

		System.out.println("FIN");
	}

	public static void palmares(Atleta a) {
		Prueba[] pruebas = Datos.PRUEBAS;
		for (Prueba p : pruebas) {
			Participante[] ganadores = p.getResultado().getPodio();
			for (int i = 0; i < ganadores.length; i++) {
				Participante par = ganadores[i];
				if (par.getClass().equals(Atleta.class)) {
					if (a.equals(par)) {
						switch (i) {
						case 0:
							Oro o = p.getResultado().getPrimero();
							System.out.println("Oro" + o.getId() + " en " + p.getNombre() + " el " + p.getFecha()
									+ " en " + p.getLugar().getNombre());
							break;
						case 1:
							Plata pl = p.getResultado().getSegundo();
							System.out.println("Plata" + pl.getId() + " en " + p.getNombre() + " el " + p.getFecha()
									+ " en " + p.getLugar().getNombre());
							break;
						case 2:
							Bronce b = p.getResultado().getTercero();
							System.out.println("Bronce" + b.getId() + " en " + p.getNombre() + " el " + p.getFecha()
									+ " en " + p.getLugar().getNombre());
							break;
						}
						break;
					}
				} else {
					// Los ganadores son Equipos
					for (Atleta at : ((Equipo) par).getAtletas()) {
						if (a.equals(at)) {
							switch (i) {
							case 0:
								Oro o = p.getResultado().getPrimero();
								System.out.println("Oro" + o.getId() + " en " + p.getNombre() + " el " + p.getFecha()
										+ " en " + p.getLugar().getNombre());
								break;
							case 1:
								Plata pl = p.getResultado().getSegundo();
								System.out.println("Plata" + pl.getId() + " en " + p.getNombre() + " el " + p.getFecha()
										+ " en " + p.getLugar().getNombre());
								break;
							case 2:
								Bronce b = p.getResultado().getTercero();
								System.out.println("Bronce" + b.getId() + " en " + p.getNombre() + " el " + p.getFecha()
										+ " en " + p.getLugar().getNombre());
								break;
							}
							break;
						}
					}
				}
			}
		}

	}

}
