package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entidades.Atleta;
import entidades.Bronce;
import entidades.Categoria;
import entidades.Colegiado;
import entidades.Competicion;
import entidades.DatosPersona;
import entidades.Equipo;
import entidades.Lugar;
import entidades.Manager;
import entidades.Oro;
import entidades.Participante;
import entidades.Plata;
import entidades.Prueba;
import entidades.Resultado;

public class Datos {

	public static Oro[] OROS= {
			new Oro(1,87.8F), new Oro(2,90.8F), new Oro(3,91.7F), new Oro(4,88.5F), new Oro(5,79.9F), new Oro(6,89.9F), new Oro(7,90.1F), new Oro(8,90.1F), new Oro(9,89.2F), new Oro(10,82.1F),
			new Oro(11,83.2F), new Oro(12,89.9F), new Oro(13,91.7F), new Oro(14,88.3F), new Oro(15,89.5F), new Oro(16,85.3F),new Oro(17,85.3F),new Oro(18,81.8F),new Oro(19,86.4F),new Oro(20,89.9F),
			new Oro(21,83.2F), new Oro(22,89.6F), new Oro(23,91.0F), new Oro(24,88.8F), new Oro(25,90.5F), new Oro(26,91.0F),new Oro(27,85.3F),new Oro(28,89.5F),new Oro(29,79.9F),new Oro(30,89.0F),
			new Oro(31,88.1F), new Oro(32,79.9F), new Oro(33,90.1F), new Oro(34,89.8F), new Oro(35,90.0F), new Oro(36,92.3F),new Oro(37,92.9F),new Oro(38,88.8F),new Oro(39,88.4F),new Oro(40,91.9F),
			new Oro(41,82.2F), new Oro(42,89.9F), new Oro(43,81.1F), new Oro(44,88.6F), new Oro(45,89.0F), new Oro(46,90.1F),new Oro(47,85.3F),new Oro(48,87.0F),new Oro(49,89.4F),new Oro(50,92.9F)
	};

	public static Plata[] PLATAS= {
			new Plata(1,89.8F), new Plata(2,90.8F), new Plata(3,92.7F), new Plata(4,89.5F), new Plata(5,79.9F), new Plata(6,90.9F), new Plata(7,90.1F), new Plata(8,90.1F), new Plata(9,90.2F), new Plata(10,82.1F),
			new Plata(11,93.2F), new Plata(12,80.9F), new Plata(13,90.7F), new Plata(14,90.3F), new Plata(15,89.6F), new Plata(16,89.3F),new Plata(17,85.3F),new Plata(18,91.8F),new Plata(19,83.4F),new Plata(20,80.9F),
			new Plata(21,84.2F), new Plata(22,89.7F), new Plata(23,90.0F), new Plata(24,85.8F), new Plata(25,92.5F), new Plata(26,92.0F),new Plata(27,85.3F),new Plata(28,79.9F),new Plata(29,91.9F),new Plata(30,84.0F),
			new Plata(31,80.1F), new Plata(32,79.8F), new Plata(33,90.1F), new Plata(34,81.8F), new Plata(35,90.1F), new Plata(36,90.3F),new Plata(37,92.9F),new Plata(38,94.3F),new Plata(39,83.4F),new Plata(40,91.9F),
			new Plata(41,81.2F), new Plata(42,78.9F), new Plata(43,91.1F), new Plata(44,88.0F), new Plata(45,89.0F), new Plata(46,89.1F),new Plata(47,85.3F),new Plata(48,88.0F),new Plata(49,89.4F),new Plata(50,82.9F)
	};

	public static Bronce[] BRONCES= {
			new Bronce(1,92.8F), new Bronce(2,93.0F), new Bronce(3,90.7F), new Bronce(4,87.5F), new Bronce(5,89.9F), new Bronce(6,92.2F), new Bronce(7,90.1F), new Bronce(8,90.1F), new Bronce(9,89.2F), new Bronce(10,88.1F),
			new Bronce(11,93.1F), new Bronce(12,89.9F), new Bronce(13,91.7F), new Bronce(14,88.3F), new Bronce(15,89.5F), new Bronce(16,85.2F),new Bronce(17,85.3F),new Bronce(18,81.7F),new Bronce(19,86.4F),new Bronce(20,84.9F),
			new Bronce(21,83.0F), new Bronce(22,89.6F), new Bronce(23,91.0F), new Bronce(24,88.8F), new Bronce(25,90.5F), new Bronce(26,92.0F),new Bronce(27,84.3F),new Bronce(28,89.2F),new Bronce(29,78.9F),new Bronce(30,89.0F),
			new Bronce(31,88.9F), new Bronce(32,90.9F), new Bronce(33,90.1F), new Bronce(34,89.8F), new Bronce(35,90.0F), new Bronce(36,90.3F),new Bronce(37,91.9F),new Bronce(38,89.8F),new Bronce(39,81.4F),new Bronce(40,91.9F),
			new Bronce(41,82.7F), new Bronce(42,89.9F), new Bronce(43,81.1F), new Bronce(44,88.6F), new Bronce(45,89.0F), new Bronce(46,88.1F),new Bronce(47,85.4F),new Bronce(48,80.0F),new Bronce(49,89.4F),new Bronce(50,90.0F)
	};

	public static DatosPersona[] PERSONAS = {
			//Datos de las personas Atletas
			//Las personas de id terminado en -0 y en -5 son Junior (fechaNac posterior al 01/01/2000)
			//Federados hasta 2020
			new DatosPersona(1, "Jesus Perez Garcia", "697032897", LocalDate.parse("31/07/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2, "Carlos Fernandez Avia", "685432819", LocalDate.parse("14/11/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(3, "Luis Martinez Diaz", "695342871", LocalDate.parse("08/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(4, "Carmen Corrales Vega", "645532881", LocalDate.parse("16/06/1976", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(5, "Daniel Ruiz Blanco", "921342819", LocalDate.parse("13/02/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(6, "Pedro Gonzalez Puig", "690552712", LocalDate.parse("20/07/1977", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(7, "Martin Ibarra Encina", "683542893", LocalDate.parse("12/05/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(8, "Dinia Pino Novell", "685302577", LocalDate.parse("17/04/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(9, "Maria Frisuelos Ruiz", "605437884", LocalDate.parse("01/03/1979", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(10, "Hector Garcia Perez", "607332815", LocalDate.parse("11/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(11, "Carmen Perez Foz", "945707801", LocalDate.parse("29/04/1982", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(12, "Francisco Plaza Ayuso", "694432818", LocalDate.parse("10/01/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(13, "Maria Jesus Perez Salinas", "635714801", LocalDate.parse("09/12/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(14, "Laura Castro Llorente", "695432805", LocalDate.parse("21/05/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(15, "Gabriela del Rio Severo", "681232816", LocalDate.parse("28/02/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(16, "Carla Gallo Pedroso", "687342207", LocalDate.parse("30/05/1984", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			new DatosPersona(17, "Alberto Perez Lago", "695382349", LocalDate.parse("29/06/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(18, "Aser Linares Nobel", "685337834", LocalDate.parse("07/11/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(19, "Daniel Suarez Melano", "680734341", LocalDate.parse("30/06/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(20, "Raquel Gonzalez Banda", "985334823", LocalDate.parse("06/07/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(21, "Maria Calvo Egea", "930332814", LocalDate.parse("15/06/1986", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(22, "Hugo Blasco Rato", "684035810", LocalDate.parse("28/08/1987", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(23, "Andrés Martinez Real", "671332800", LocalDate.parse("05/07/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(24, "Raul Velazquez Garcia", "690352881", LocalDate.parse("09/06/1987", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(25, "Asuncion Garcia Perez", "603330818", LocalDate.parse("28/09/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(26, "Juan Jesus Ruiz Volantes", "620932819", LocalDate.parse("08/10/1986", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(27, "Cecilio Gallo Garcia", "684012713", LocalDate.parse("04/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(28, "Sandra Vega Cuena", "605384817", LocalDate.parse("22/02/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(29, "Sergio Poo Martinez", "608332113", LocalDate.parse("03/07/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(30, "Pedro Toca Gutierrez", "985432713", LocalDate.parse("02/10/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(31, "Borja Perez Suarez", "680332801", LocalDate.parse("27/12/1982", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(32, "Ander Santos Higuera", "682452807", LocalDate.parse("01/10/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(33, "Dominica Gonzalez Perez", "650237810", LocalDate.parse("23/01/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(34, "Laura Suarez Blasco", "680345912", LocalDate.parse("01/02/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(35, "Almudena Diaz Maltras", "685482881", LocalDate.parse("25/06/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(36, "Nicolasa Serrano Soto", "647002899", LocalDate.parse("31/05/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(37, "Maria Jesus Ibarra Montoya", "678305812", LocalDate.parse("30/06/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(38, "Yolanda Lopez Garcia", "684372315", LocalDate.parse("15/11/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(39, "Renata Prieto Fernandez", "605399813", LocalDate.parse("14/07/1975", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(40, "Antonia Garcia Herrera", "675332884", LocalDate.parse("24/12/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			new DatosPersona(41, "Diego Diez Murcia", "645572813", LocalDate.parse("08/04/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(42, "Agatha Fernandez Marron", "644382856", LocalDate.parse("08/04/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(43, "Luis Plaza Martinez", "607332436", LocalDate.parse("29/05/1977", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(44, "Alberto Corrales Suarez", "629332834", LocalDate.parse("13/06/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(45, "Pedro del Val Almendro", "678433858", LocalDate.parse("28/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(46, "Teresa Gonzalez Garcia", "615432888", LocalDate.parse("26/12/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(47, "Luisa Pina Soto", "605332720", LocalDate.parse("25/03/1969", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(48, "Jana Blanco Garcia", "635337841", LocalDate.parse("24/05/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(49, "Cristina Pedrosa Garcia", "605372412", LocalDate.parse("27/01/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(50, "Daniel Pares Mendez", "985357417", LocalDate.parse("25/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(51, "Martin Velazquez Melendez", "913232811", LocalDate.parse("26/10/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(52, "Candida Rato Linares", "605332872", LocalDate.parse("23/04/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(53, "Sebastian Castro Castro", "985372809", LocalDate.parse("25/07/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(54, "Carlos del Pino Inda", "685332817", LocalDate.parse("12/03/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(55, "Lucia Santos Fresneda", "949332872", LocalDate.parse("24/06/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(56, "Andrés Diaz Lago", "609332927", LocalDate.parse("08/12/1969", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(57, "Brigida Fernandez Fernandez", "675332819", LocalDate.parse("27/12/1987", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(58, "Carlos Melano Largo", "685932817", LocalDate.parse("11/10/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(59, "Maria Rox Suarez", "985538470", LocalDate.parse("23/01/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			//Final Masculina Memorial víctimas Coronavirus 2021
			new DatosPersona(60, "Francisco Calvo Real", "604832312", LocalDate.parse("08/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(61, "Diego Egea Gutierres", "987932838", LocalDate.parse("10/05/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(62, "Pablo Diez Vega", "67133591", LocalDate.parse("28/01/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(63, "Hector Suarez Gonzalez", "670358827", LocalDate.parse("21/08/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(64, "Andrés Gonzalez Res", "935332801", LocalDate.parse("29/12/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(65, "Juan Encina Panes", "609132923", LocalDate.parse("20/11/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(66, "Asier Mendez Blanco", "642782839", LocalDate.parse("02/04/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(67, "Luis Rajoy Garcia", "695282109", LocalDate.parse("19/06/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(68, "Rafael Pis Pedrosa", "685332913", LocalDate.parse("09/05/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(69, "Federico Ruiz Fresno", "685722391", LocalDate.parse("30/06/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			//Final Femenina Memorial víctimas Coronavirus 2021
			new DatosPersona(70, "Rosa Feroz Corbato", "645332823", LocalDate.parse("31/12/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(71, "Rai Jordan Riego Garcia", "918332819", LocalDate.parse("21/07/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(72, "Carmen Lago Par", "649332871", LocalDate.parse("20/03/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(73, "Asuncion Serrano Vega", "635839771", LocalDate.parse("10/06/1979", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(74, "Marina Castro Garcia", "659331801", LocalDate.parse("18/01/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(75, "Maria Ana de la Riva Suarez", "685932890", LocalDate.parse("10/02/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(76, "Maria Salas Fernandez", "675331829", LocalDate.parse("01/07/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(77, "Amaya Fernandez Muela", "97539218", LocalDate.parse("02/04/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(78, "Sandra Peres Robles", "929352801", LocalDate.parse("08/08/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(79, "Ursula Suarez Diaz", "645702851", LocalDate.parse("17/06/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			//Final Mixta Memorial víctimas Coronavirus 2021
			new DatosPersona(80, "Gabriel Julio Sol", "985372858", LocalDate.parse("16/02/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(81, "Carlos Culto Medina", "6230328710", LocalDate.parse("08/03/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(82, "Natasha Puig Garcia", "625159802", LocalDate.parse("15/12/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(83, "Dario Linares Gutierrez", "988032859", LocalDate.parse("03/04/1977", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(84, "Diego Bolo Garcia", "659373808", LocalDate.parse("04/11/1976", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(85, "Alberto Agujas Montoya", "930832821", LocalDate.parse("07/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(86, "Martin Garcia Garcia", "698501818", LocalDate.parse("04/05/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(87, "Jorge Ibarretxe Gallo", "680392814", LocalDate.parse("08/04/1975", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(88, "Daniel Muela Velazquez", "682312841", LocalDate.parse("05/02/1974", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(89, "Teresa Martinez Vivo", "680372845", LocalDate.parse("16/07/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			new DatosPersona(90, "Borja Lagos Perez", "699332873", LocalDate.parse("15/01/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			//Final Especial Memorial víctimas Coronavirus 2021
			new DatosPersona(91, "Andrés Gonzalez Galvez", "989300959", LocalDate.parse("08/12/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(92, "Maria Garcia de Vivar", "938332801", LocalDate.parse("14/08/1973", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(93, "Juan Robles Sello", "689337821", LocalDate.parse("05/06/1971", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(94, "Pablo Leon Garcia", "685409720", LocalDate.parse("06/01/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(95, "Amalia del Pino Gutierrez", "910832839", LocalDate.parse("30/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(96, "Raul Plaza Corbato", "694937805", LocalDate.parse("14/09/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(97, "Padro Santos Diaz", "637339880", LocalDate.parse("13/09/1972", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(98, "Juaquin Dalmata Serrano", "929330831", LocalDate.parse("08/09/1987", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(99, "Francisco Suarez Serrano", "970309833", LocalDate.parse("26/04/1986", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(100, "Emma Fernandez Corral", "941272809", LocalDate.parse("12/10/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			//Datos de las personas Colegiados (id > 1000 Y id <= 2000)
			new DatosPersona(1001, "Sonia Montoya Torna", "671250081", LocalDate.parse("12/07/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1002, "Natalia Prado Aguja", "680235891", LocalDate.parse("11/02/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1003, "Martin Puig Fernandez", "694382839", LocalDate.parse("07/08/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1004, "Juan Alvarez Roig", "685732413", LocalDate.parse("06/07/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1005, "Carlos Diez Cobo", "675312945", LocalDate.parse("08/09/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1006, "Carmen Martinez Tazones", "958232831", LocalDate.parse("21/10/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1007, "Hector Calvo Blanco", "688257823", LocalDate.parse("11/02/1984", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1008, "Pablo Linares Altamira", "697131831", LocalDate.parse("08/01/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1009, "Hugo Fuero Ruiz", "951832859", LocalDate.parse("10/10/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1010, "Luis Garcia Gonzalez", "681952317", LocalDate.parse("09/08/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1011, "Carmen Diaz Castro", "61734815", LocalDate.parse("07/10/1979", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1012, "Borja Vega Martinez", "688215870", LocalDate.parse("10/07/1979", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1013, "Carlos Garcia Perez", "650732808", LocalDate.parse("20/05/1982", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1014, "Maria Ruiz Fernandez", "652732818", LocalDate.parse("21/02/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(1015, "Emma del Rio Flores", "650732111", LocalDate.parse("02/01/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),

			//Datos de las personas Managers (id > 2000 Y id <= 3000)
			new DatosPersona(2001, "Rosario Prado Blanco", "627943650", LocalDate.parse("18/09/1979", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2002, "Laura Torices Fernandez", "685334056", LocalDate.parse("08/02/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2003, "Manuel Santiago Diez", "638135956", LocalDate.parse("31/10/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2004, "Alfredo Garcia Gallo", "608364550", LocalDate.parse("28/03/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2005, "Ramon Garcia Lazo", "694267193", LocalDate.parse("13/08/1976", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2006, "Rosa Diez Lucas", "628324853", LocalDate.parse("07/04/1981", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2007, "Ana Ruiz Arenas", "629304871", LocalDate.parse("04/11/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2008, "Alvaro Osorno Soto", "654768870", LocalDate.parse("23/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2009, "Bernabe Ruiz Garcia", "623804841", LocalDate.parse("14/11/1978", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2010, "Elisabeth Salamanca Blanco", "669359871", LocalDate.parse("07/04/1980", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2011, "Ana Ruiz Jurado", "609304772", LocalDate.parse("25/12/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2012, "Maria Cuena Velazquez", "672340851", LocalDate.parse("14/09/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2013, "Juana de la Riva Gutierrez", "629904871", LocalDate.parse("09/01/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2014, "Damian Ortiz Arenas", "681331773", LocalDate.parse("24/07/1987", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new DatosPersona(2015, "Cesar Gutierrez Fernandez", "690302861", LocalDate.parse("10/08/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
	};

	public static Manager[] MANAGERS = {
			new Manager(1, "985147240", "rosaprado@gmail.com", buscarPersonaPorId(2001)),
			new Manager(2, "675920137", "lauratorices@hotmail.com", buscarPersonaPorId(2002)),
			new Manager(3, "685893201", "mansandiez@gmail.com", buscarPersonaPorId(2003)),
			new Manager(4, "685730303", "alfregar@hotmail.es", buscarPersonaPorId(2004)),
			new Manager(5, "685623515", "ramongarcialazo@gmail.com", buscarPersonaPorId(2005)),
			new Manager(6, "685510928", "rosa10lucas@gmail.com", buscarPersonaPorId(2006)),
			new Manager(7, "985103229", "anaruarenas@hotmail.com", buscarPersonaPorId(2007)),
			new Manager(8, "945983022", "alvarosorno@hotmail.com", buscarPersonaPorId(2008)),
			new Manager(9, "965101229", "bernaruiz@gmail.com", buscarPersonaPorId(2009)),
			new Manager(10, "970703921", "elisalamanca@hotmail.es", buscarPersonaPorId(2010)),
			new Manager(11, "985123239", "anaruizjurado@outlook.com", buscarPersonaPorId(2011)),
			new Manager(12, "941603820", "maricuena_velazquez@hotmail.es", buscarPersonaPorId(2012)),
			new Manager(13, "982133248", "juanarivagutierrez@gmail.com", buscarPersonaPorId(2013)),
			new Manager(14, "935503727", "damortizarenas@hotmail.com", buscarPersonaPorId(2014)),
			new Manager(15, "982143656", "cesargutifdz@hotmail.es", buscarPersonaPorId(2015))
	};

	public static Atleta[] ATLETAS = {
			new Atleta(1, 1.70F, 89.9F, PERSONAS[0]),
			new Atleta(2, 1.98F, 72.8F, PERSONAS[1]),
			new Atleta(3, 1.69F, 65.7F, PERSONAS[2]),
			new Atleta(4, 1.90F, 89.4F, PERSONAS[3]),
			new Atleta(5, 1.87F, 68.0F, PERSONAS[4]),
			new Atleta(6, 1.81F, 67.5F, PERSONAS[5]),
			new Atleta(7, 1.79F, 89.9F, PERSONAS[6]),
			new Atleta(8, 1.86F, 65.8F, PERSONAS[7]),
			new Atleta(9, 1.90F, 72.0F, PERSONAS[8]),
			new Atleta(10, 1.81F, 84.8F, PERSONAS[9]),
			new Atleta(11, 1.76F, 89.5F, PERSONAS[10]),
			new Atleta(12, 1.99F, 67.7F, PERSONAS[11]),
			new Atleta(13, 1.60F, 65.6F, PERSONAS[12]),
			new Atleta(14, 1.86F, 79.2F, PERSONAS[13]),
			new Atleta(15, 1.79F, 84.4F, PERSONAS[14]),
			new Atleta(16, 1.81F, 68.9F, PERSONAS[15]),
			new Atleta(17, 1.99F, 69.3F, PERSONAS[16]),
			new Atleta(18, 1.80F, 65.0F, PERSONAS[17]),
			new Atleta(19, 1.69F, 68.8F, PERSONAS[18]),
			new Atleta(20, 1.79F, 84.6F, PERSONAS[19]),
			new Atleta(21, 1.86F, 69.2F, PERSONAS[20]),
			new Atleta(22, 1.89F, 69.0F, PERSONAS[21]),
			new Atleta(23, 1.99F, 79.4F, PERSONAS[22]),
			new Atleta(24, 1.82F, 69.7F, PERSONAS[23]),
			new Atleta(25, 1.79F, 87.8F, PERSONAS[24]),
			new Atleta(26, 1.80F, 69.3F, PERSONAS[25]),
			new Atleta(27, 1.89F, 65.0F, PERSONAS[26]),
			new Atleta(28, 1.99F, 68.2F, PERSONAS[27]),
			new Atleta(29, 1.85F, 89.8F, PERSONAS[28]),
			new Atleta(30, 1.89F, 79.8F, PERSONAS[29]),
			new Atleta(31, 1.76F, 64.5F, PERSONAS[30]),
			new Atleta(32, 1.81F, 69.0F, PERSONAS[31]),
			new Atleta(33, 1.99F, 88.1F, PERSONAS[32]),
			new Atleta(34, 1.60F, 69.5F, PERSONAS[33]),
			new Atleta(35, 1.86F, 67.7F, PERSONAS[34]),
			new Atleta(36, 1.89F, 64.9F, PERSONAS[35]),
			new Atleta(37, 1.71F, 89.8F, PERSONAS[36]),
			new Atleta(38, 1.89F, 68.6F, PERSONAS[37]),
			new Atleta(39, 1.89F, 79.6F, PERSONAS[38]),
			new Atleta(40, 1.65F, 69.4F, PERSONAS[39]),
			new Atleta(41, 1.89F, 65.0F, PERSONAS[40]),
			new Atleta(42, 1.70F, 89.5F, PERSONAS[41]),
			new Atleta(43, 1.79F, 69.1F, PERSONAS[42]),
			new Atleta(44, 1.81F, 78.8F, PERSONAS[43]),
			new Atleta(45, 1.99F, 89.0F, PERSONAS[44]),
			new Atleta(46, 1.86F, 69.7F, PERSONAS[45]),
			new Atleta(47, 1.69F, 89.1F, PERSONAS[46]),
			new Atleta(48, 1.79F, 69.5F, PERSONAS[47]),
			new Atleta(49, 1.70F, 79.2F, PERSONAS[48]),
			new Atleta(50, 1.86F, 69.9F, PERSONAS[49]),
			new Atleta(51, 1.81F, 88.0F, PERSONAS[50]),
			new Atleta(52, 1.99F, 64.1F, PERSONAS[51]),
			new Atleta(53, 1.69F, 69.2F, PERSONAS[52]),
			new Atleta(54, 1.86F, 85.5F, PERSONAS[53]),
			new Atleta(55, 1.80F, 69.1F, PERSONAS[54]),
			new Atleta(56, 1.89F, 89.5F, PERSONAS[55]),
			new Atleta(57, 1.71F, 68.0F, PERSONAS[56]),
			new Atleta(58, 1.79F, 65.8F, PERSONAS[57]),
			new Atleta(59, 1.66F, 89.9F, PERSONAS[58]),
			new Atleta(60, 1.69F, 69.4F, PERSONAS[59]),
			new Atleta(61, 1.80F, 89.0F, PERSONAS[60]),
			new Atleta(62, 1.80F, 69.3F, PERSONAS[61]),
			new Atleta(63, 1.89F, 69.1F, PERSONAS[62]),
			new Atleta(64, 1.89F, 68.0F, PERSONAS[63]),
			new Atleta(65, 1.79F, 89.0F, PERSONAS[64]),
			new Atleta(66, 1.89F, 79.1F, PERSONAS[65]),
			new Atleta(67, 1.89F, 69.0F, PERSONAS[66]),
			new Atleta(68, 1.69F, 69.7F, PERSONAS[67]),
			new Atleta(69, 1.80F, 69.8F, PERSONAS[68]),
			new Atleta(70, 1.89F, 88.4F, PERSONAS[69]),
			new Atleta(71, 1.79F, 64.3F, PERSONAS[70]),
			new Atleta(72, 1.89F, 67.0F, PERSONAS[71]),
			new Atleta(73, 1.90F, 65.1F, PERSONAS[72]),
			new Atleta(74, 1.81F, 69.0F, PERSONAS[73]),
			new Atleta(75, 1.80F, 89.0F, PERSONAS[74]),
			new Atleta(76, 1.69F, 68.9F, PERSONAS[75]),
			new Atleta(77, 1.89F, 64.3F, PERSONAS[76]),
			new Atleta(78, 1.79F, 69.2F, PERSONAS[77]),
			new Atleta(79, 1.79F, 67.7F, PERSONAS[78]),
			new Atleta(80, 1.80F, 87.8F, PERSONAS[79]),
			new Atleta(81, 1.81F, 69.6F, PERSONAS[80]),
			new Atleta(82, 1.69F, 79.4F, PERSONAS[81]),
			new Atleta(83, 1.86F, 69.5F, PERSONAS[82]),
			new Atleta(84, 1.70F, 88.9F, PERSONAS[83]),
			new Atleta(85, 1.89F, 67.2F, PERSONAS[84]),
			new Atleta(86, 1.69F, 65.0F, PERSONAS[85]),
			new Atleta(87, 1.79F, 69.0F, PERSONAS[86]),
			new Atleta(88, 1.87F, 85.5F, PERSONAS[87]),
			new Atleta(89, 1.89F, 69.3F, PERSONAS[88]),
			new Atleta(90, 1.62F, 67.2F, PERSONAS[89]),
			new Atleta(91, 1.89F, 68.7F, PERSONAS[90]),
			new Atleta(92, 1.99F, 65.8F, PERSONAS[91]),
			new Atleta(93, 1.69F, 89.5F, PERSONAS[92]),
			new Atleta(94, 1.89F, 69.8F, PERSONAS[93]),
			new Atleta(95, 1.89F, 67.0F, PERSONAS[94]),
			new Atleta(96, 1.79F, 64.7F, PERSONAS[95]),
			new Atleta(97, 1.62F, 69.8F, PERSONAS[96]),
			new Atleta(98, 1.89F, 79.0F, PERSONAS[97]),
			new Atleta(99, 1.86F, 77.6F, PERSONAS[98]),
			new Atleta(100, 1.82F, 69.8F, PERSONAS[99])
	};

	private static Atleta[] equipo1 = {ATLETAS[0], ATLETAS[4], ATLETAS[16], ATLETAS[23]};
	private static Atleta[] equipo2 = {ATLETAS[1], ATLETAS[5], ATLETAS[17], ATLETAS[25]};
	private static Atleta[] equipo3 = {ATLETAS[2], ATLETAS[9], ATLETAS[18], ATLETAS[26]};
	private static Atleta[] equipo4 = {ATLETAS[6], ATLETAS[21], ATLETAS[28], ATLETAS[30]};
	private static Atleta[] equipo5 = {ATLETAS[11], ATLETAS[22], ATLETAS[29], ATLETAS[31]};
	private static Atleta[] equipo6 = {ATLETAS[3], ATLETAS[13], ATLETAS[19], ATLETAS[35]};
	private static Atleta[] equipo7 = {ATLETAS[7], ATLETAS[20], ATLETAS[32], ATLETAS[36]};
	private static Atleta[] equipo8 = {ATLETAS[8], ATLETAS[14], ATLETAS[24], ATLETAS[37]};
	private static Atleta[] equipo9 = {ATLETAS[10], ATLETAS[27], ATLETAS[33], ATLETAS[38]};
	private static Atleta[] equipo10 = {ATLETAS[12], ATLETAS[15], ATLETAS[34], ATLETAS[93]};
	private static Atleta[] equipo11 = {ATLETAS[40], ATLETAS[44], ATLETAS[50], ATLETAS[55]};
	private static Atleta[] equipo12 = {ATLETAS[41], ATLETAS[45], ATLETAS[51], ATLETAS[56]};
	private static Atleta[] equipo13 = {ATLETAS[42], ATLETAS[46], ATLETAS[52], ATLETAS[57]};
	private static Atleta[] equipo14 = {ATLETAS[43], ATLETAS[47], ATLETAS[53], ATLETAS[58]};
	private static Atleta[] equipo15 = {ATLETAS[48], ATLETAS[49], ATLETAS[54]};


	public static Equipo[] EQUIPOS = {
			new Equipo(1, 2020, MANAGERS[0], equipo1),
			new Equipo(2, 2020, MANAGERS[1], equipo2),
			new Equipo(3, 2020, MANAGERS[2], equipo3),
			new Equipo(4, 2020, MANAGERS[3], equipo4),
			new Equipo(5, 2020, MANAGERS[4], equipo5),
			new Equipo(6, 2020, MANAGERS[5], equipo6),
			new Equipo(7, 2020, MANAGERS[6], equipo7),
			new Equipo(8, 2020, MANAGERS[7], equipo8),
			new Equipo(9, 2020, MANAGERS[8], equipo9),
			new Equipo(10, 2020, MANAGERS[9], equipo10),
			new Equipo(11, 2020, MANAGERS[10], equipo11),
			new Equipo(12, 2020, MANAGERS[11], equipo12),
			new Equipo(13, 2020, MANAGERS[12], equipo13),
			new Equipo(14, 2020, MANAGERS[13], equipo14),
			new Equipo(15, 2020, MANAGERS[14], equipo15)
	};

	private static Participante[] finalistasMascInd2020 = {
			new Atleta(1, ATLETAS[0], 121,'A'), //plata1
			new Atleta(2, ATLETAS[1], 15,'C'),
			new Atleta(3, ATLETAS[2], 133,'E'),
			new Atleta(4, ATLETAS[4], 88,'I'),
			new Atleta(5, ATLETAS[5], 72,'L'), //oro1
			new Atleta(6, ATLETAS[6], 45,'H'),
			new Atleta(7, ATLETAS[9], 50,'Q'), //bronce1
			new Atleta(8, ATLETAS[11], 27,'W')
	};

	private static Participante[] finalistasFemInd2020 = {
			new Atleta(9, ATLETAS[3], 7,'E'), //oro2
			new Atleta(10, ATLETAS[7], 11,'R'),
			new Atleta(11, ATLETAS[8], 69,'S'),
			new Atleta(12, ATLETAS[10], 40,'T'), //plata2
			new Atleta(13, ATLETAS[12], 52,'O'), //bronce2
			new Atleta(14, ATLETAS[13], 38,'K'),
			new Atleta(15, ATLETAS[14], 16,'A'),
			new Atleta(16, ATLETAS[15], 131,'C')
	};

	private static Participante[] finalistasMixInd2020 = {
			new Atleta(17, ATLETAS[1], 109,'M'), //plata3
			new Atleta(18, ATLETAS[2], 118,'O'),
			new Atleta(19, ATLETAS[7], 130,'L'), //oro3
			new Atleta(20, ATLETAS[8], 85,'F'),
			new Atleta(21, ATLETAS[9], 76,'E'),
			new Atleta(22, ATLETAS[10], 54,'Z'),
			new Atleta(23, ATLETAS[11], 43,'P'), //bronce3
			new Atleta(24, ATLETAS[12], 121,'I')
	};

	private static Participante[] finalistasMascEquipos2020 = {
			new Equipo(25, EQUIPOS[0], 101, 'A'), //oro4
			new Equipo(26, EQUIPOS[1], 95, 'E'),
			new Equipo(27, EQUIPOS[2], 2, 'I'), //plata4
			new Equipo(28, EQUIPOS[3], 77, 'O'),
			new Equipo(29, EQUIPOS[4], 48, 'U') //bronce4
	};

	private static Participante[] finalistasFemEquipos2020 = {
			new Equipo(30, EQUIPOS[5], 122, 'A'), //bronce8
			new Equipo(31, EQUIPOS[6], 18, 'E'),
			new Equipo(32, EQUIPOS[7], 45, 'I'),
			new Equipo(33, EQUIPOS[8], 89, 'O'), //plata8
			new Equipo(34, EQUIPOS[9], 77, 'U') //oro8
	};

	private static Participante[] finalistasMixEquipos2020 = {
			new Equipo(35, EQUIPOS[10], 42, 'A'), //plata12
			new Equipo(36, EQUIPOS[11], 1, 'E'),
			new Equipo(37, EQUIPOS[12], 15, 'I'), //bronce12
			new Equipo(38, EQUIPOS[13], 100, 'O'), //oro12
			new Equipo(39, EQUIPOS[14], 84, 'U')
	};

	private static Participante[] finalistasSanSilvestreMasc2020 = {
			new Atleta(40, ATLETAS[5], 97,'P'),
			new Atleta(41, ATLETAS[9], 81,'K'), //Oro16
			new Atleta(42, ATLETAS[16], 65,'J'),
			new Atleta(43, ATLETAS[29], 17,'E'), //Plata16
			new Atleta(44, ATLETAS[42], 55,'N'),
			new Atleta(45, ATLETAS[43], 121,'D'), //bronce16
			new Atleta(46, ATLETAS[44], 100,'V')
	};

	private static Participante[] finalistasSanSilvestreFem2020 = {
			new Atleta(47, ATLETAS[10], 50,'U'), //bronce17
			new Atleta(48, ATLETAS[41], 79,'L'), //plata17
			new Atleta(49, ATLETAS[15], 2,'S'),
			new Atleta(50, ATLETAS[39], 18,'O'),
			new Atleta(51, ATLETAS[45], 137,'W'), //oro17
			new Atleta(52, ATLETAS[46], 106,'K'),
			new Atleta(53, ATLETAS[48], 85,'A')
	};

	private static Participante[] finalistasSanSilvestreMix2020 = {
			new Atleta(54, ATLETAS[40], 148,'G'),
			new Atleta(55, ATLETAS[33], 14,'D'),
			new Atleta(56, ATLETAS[18], 51,'A'), //oro18
			new Atleta(57, ATLETAS[20], 49,'E'), //bronce18
			new Atleta(58, ATLETAS[30], 27,'P'),
			new Atleta(59, ATLETAS[34], 96,'W'),
			new Atleta(60, ATLETAS[47], 88,'Z') //plata18
	};

	private static Atleta[] finalistasRegMascInd2021 = {
			new Atleta(61, ATLETAS[16], 65,'A'), //oro19
			new Atleta(62, ATLETAS[11], 22,'L'),
			new Atleta(63, ATLETAS[18], 10,'Q'),
			new Atleta(64, ATLETAS[49], 123,'S'), //plata19
			new Atleta(65, ATLETAS[50], 31,'X'),
			new Atleta(66, ATLETAS[52], 88,'T'),
			new Atleta(67, ATLETAS[53], 93,'L'), //bronce19
			new Atleta(68, ATLETAS[55], 59,'P')
	};

	private static Participante[] finalistasRegFemInd2021 = {
			new Atleta(69, ATLETAS[10], 18,'I'), //oro20
			new Atleta(70, ATLETAS[15], 100,'T'),
			new Atleta(71, ATLETAS[19], 15,'G'),
			new Atleta(72, ATLETAS[33], 86,'S'),
			new Atleta(73, ATLETAS[39], 127,'X'), //bronce20
			new Atleta(74, ATLETAS[51], 94,'A'),
			new Atleta(75, ATLETAS[54], 71,'O'),
			new Atleta(76, ATLETAS[56], 23,'B') //plata20
	};

	private static Participante[] finalistasRegMixInd2021 = {
			new Atleta(77, ATLETAS[16], 22,'N'), //bronce21
			new Atleta(78, ATLETAS[19], 78,'F'),
			new Atleta(79, ATLETAS[33], 81,'O'), //oro21
			new Atleta(80, ATLETAS[49], 120,'R'),
			new Atleta(81, ATLETAS[51], 109,'A'), //plata21
			new Atleta(82, ATLETAS[55], 45,'I'),
			new Atleta(83, ATLETAS[57], 88,'L'),
			new Atleta(84, ATLETAS[58], 91,'X')
	};

	private static Participante[] finalistasCoronaMasc2021 = {
			new Atleta(85, ATLETAS[59], 62,'F'),
			new Atleta(86, ATLETAS[60], 61,'T'), //plata22
			new Atleta(87, ATLETAS[61], 59,'A'), //bronce22
			new Atleta(88, ATLETAS[62], 131,'D'),
			new Atleta(89, ATLETAS[63], 109,'Z'),
			new Atleta(90, ATLETAS[64], 15,'U'),
			new Atleta(91, ATLETAS[65], 67,'P'),
			new Atleta(92, ATLETAS[66], 83,'T'),
			new Atleta(93, ATLETAS[67], 39,'Q'),
			new Atleta(94, ATLETAS[68], 44,'L') //oro22
	};

	private static Participante[] finalistasCoronaFem2021 = {
			new Atleta(95, ATLETAS[69], 4,'U'), //plata23
			new Atleta(96, ATLETAS[70], 56,'C'), //bronce23
			new Atleta(97, ATLETAS[71], 11,'H'),
			new Atleta(98, ATLETAS[72], 145,'A'),
			new Atleta(9, ATLETAS[73], 68,'T'),
			new Atleta(100, ATLETAS[74], 92,'O'),
			new Atleta(101, ATLETAS[75], 37,'F'),
			new Atleta(102, ATLETAS[76], 61,'E'),
			new Atleta(103, ATLETAS[77], 19,'M'), //oro23
			new Atleta(104, ATLETAS[78], 144,'L')
	};

	private static Participante[] finalistasCoronaMix2021 = {
			new Atleta(105, ATLETAS[79], 88,'J'),
			new Atleta(106, ATLETAS[80], 52,'D'),
			new Atleta(107, ATLETAS[81], 28,'I'),
			new Atleta(108, ATLETAS[82], 40,'F'), //bronce24
			new Atleta(109, ATLETAS[83], 111,'R'), //plata24
			new Atleta(110, ATLETAS[84], 55,'E'), //oro24
			new Atleta(111, ATLETAS[85], 28,'O'),
			new Atleta(112, ATLETAS[86], 37,'W'),
			new Atleta(113, ATLETAS[87], 46,'K'),
			new Atleta(114, ATLETAS[88], 50,'T')
	};

	private static Participante[] finalistasCoronaEspecial2021 = {
			new Atleta(115, ATLETAS[90], 100,'P'),
			new Atleta(116, ATLETAS[91], 99,'H'),
			new Atleta(117, ATLETAS[92], 62,'O'), //oro25
			new Atleta(118, ATLETAS[93], 5,'S'),
			new Atleta(119, ATLETAS[94], 16,'A'),
			new Atleta(120, ATLETAS[95], 27,'Z'),
			new Atleta(121, ATLETAS[96], 38,'Q'), //plata25
			new Atleta(122, ATLETAS[97], 94,'K'),
			new Atleta(123, ATLETAS[98], 85,'B'), //bronce25
			new Atleta(124, ATLETAS[99], 76,'C')
	};


	public static Colegiado[] COLEGIADOS = {
			new Colegiado(1, Categoria.JUNIOR,  buscarPersonaPorId(1001)),
			new Colegiado(2, Categoria.SENIOR, buscarPersonaPorId(1002)),
			new Colegiado(3, Categoria.JUNIOR, buscarPersonaPorId(1003)),
			new Colegiado(4, Categoria.SENIOR, buscarPersonaPorId(1004)),
			new Colegiado(5, Categoria.ESPECIAL, buscarPersonaPorId(1005)),
			new Colegiado(6, Categoria.JUNIOR, buscarPersonaPorId(1006)),
			new Colegiado(7, Categoria.SENIOR, buscarPersonaPorId(1007)),
			new Colegiado(8, Categoria.SENIOR, buscarPersonaPorId(1008)),
			new Colegiado(9, Categoria.JUNIOR, buscarPersonaPorId(1009)),
			new Colegiado(10, Categoria.ESPECIAL, buscarPersonaPorId(1010)),
			new Colegiado(11, Categoria.SENIOR, buscarPersonaPorId(1011)),
			new Colegiado(12, Categoria.SENIOR, buscarPersonaPorId(1012)),
			new Colegiado(13, Categoria.SENIOR, buscarPersonaPorId(1013)),
			new Colegiado(14, Categoria.SENIOR, buscarPersonaPorId(1014)),
			new Colegiado(15, Categoria.ESPECIAL, buscarPersonaPorId(1015))
	};

	public static Colegiado[] arbitrosFinalMascInd2020 = {COLEGIADOS[0], COLEGIADOS[1], COLEGIADOS[13]};
	public static Colegiado[] arbitrosFinalFemInd2020 = {COLEGIADOS[2], COLEGIADOS[3], COLEGIADOS[12]};
	public static Colegiado[] arbitrosFinalMixInd2020 = {COLEGIADOS[0], COLEGIADOS[6], COLEGIADOS[7]};
	public static Colegiado[] arbitrosFinalMascEquipos2020 = {COLEGIADOS[5], COLEGIADOS[7], COLEGIADOS[10]};
	public static Colegiado[] arbitrosFinalFemEquipos2020 = {COLEGIADOS[2], COLEGIADOS[3], COLEGIADOS[11]};
	public static Colegiado[] arbitrosFinalMixEquipos2020 = {COLEGIADOS[8], COLEGIADOS[11], COLEGIADOS[12]};
	public static Colegiado[] arbitrosFinalSanSilvestreMasc2020 = {COLEGIADOS[5], COLEGIADOS[9], COLEGIADOS[13]};
	public static Colegiado[] arbitrosFinalSanSilvestreFem2020 = {COLEGIADOS[1], COLEGIADOS[6], COLEGIADOS[13]};
	public static Colegiado[] arbitrosFinalSanSilvestreMix2020 = {COLEGIADOS[2], COLEGIADOS[5], COLEGIADOS[11]};
	public static Colegiado[] arbitrosFinalRegMascInd2021 = {COLEGIADOS[2], COLEGIADOS[8], COLEGIADOS[7]};
	public static Colegiado[] arbitrosFinalRegFemInd2021 = {COLEGIADOS[6], COLEGIADOS[10], COLEGIADOS[13]};
	public static Colegiado[] arbitrosFinalRegMixInd2021 = {COLEGIADOS[4], COLEGIADOS[9], COLEGIADOS[12]};
	public static Colegiado[] arbitrosFinalCoronaMasc2021 = {COLEGIADOS[4], COLEGIADOS[7], COLEGIADOS[12]};
	public static Colegiado[] arbitrosFinalCoronaFem2021 = {COLEGIADOS[3], COLEGIADOS[7], COLEGIADOS[11]};
	public static Colegiado[] arbitrosFinalCoronaMix2021 = {COLEGIADOS[5], COLEGIADOS[8], COLEGIADOS[12]};
	public static Colegiado[] arbitrosFinalCoronaEspecial2021 = {COLEGIADOS[4], COLEGIADOS[9], COLEGIADOS[14]};

	public static Resultado resultadoFinalMascInd2020 = new Resultado(1, OROS[0], PLATAS[0], BRONCES[0]);
	public static Resultado resultadoFinalFemInd2020 = new Resultado(2, OROS[1], PLATAS[1], BRONCES[1]);
	public static Resultado resultadoFinalMixInd2020 = new Resultado(3, OROS[2], PLATAS[2], BRONCES[2]);
	public static Resultado resultadoFinalMascEquipos2020 = new Resultado(4, OROS[3], PLATAS[3], BRONCES[3]);
	public static Resultado resultadoFinalFemEquipos2020 = new Resultado(5, OROS[7], PLATAS[7], BRONCES[7]);
	public static Resultado resultadoFinalMixEquipos2020 = new Resultado(6, OROS[11], PLATAS[11], BRONCES[11]);

	public static Resultado resultadoFinalSanSilvestreMasc2020 = new Resultado(7, OROS[15], PLATAS[15], BRONCES[15]);
	public static Resultado resultadoFinalSanSilvestreFem2020 = new Resultado(8, OROS[16], PLATAS[16], BRONCES[16]);
	public static Resultado resultadoFinalSanSilvestreMix2020 = new Resultado(9, OROS[17], PLATAS[17], BRONCES[17]);

	public static Resultado resultadoFinalRegMascInd2021 = new Resultado(10, OROS[18], PLATAS[18], BRONCES[18]);
	public static Resultado resultadoFinalRegFemInd2021 = new Resultado(11, OROS[19], PLATAS[19], BRONCES[19]);
	public static Resultado resultadoFinalRegMixInd2021 = new Resultado(12, OROS[20], PLATAS[20], BRONCES[20]);

	public static Resultado resultadoFinalCoronaMasc2021 = new Resultado(13, OROS[21], PLATAS[21], BRONCES[21]);
	public static Resultado resultadoFinalCoronaFem2021 = new Resultado(14, OROS[22], PLATAS[22], BRONCES[22]);
	public static Resultado resultadoFinalCoronaMix2021 = new Resultado(15, OROS[23], PLATAS[23], BRONCES[23]);
	public static Resultado resultadoFinalCoronaEspecial2021 = new Resultado(16, OROS[24], PLATAS[24], BRONCES[24]);

	public static boolean cerrarResultados() {
		resultadoFinalMascInd2020.cerrarResultado(finalistasMascInd2020[4], finalistasMascInd2020[0], finalistasMascInd2020[6], LocalDateTime.parse("06/06/2020 14:34", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalFemInd2020.cerrarResultado(finalistasFemInd2020[0], finalistasFemInd2020[3], finalistasFemInd2020[4], LocalDateTime.parse("06/06/2020 14:54", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalMixInd2020.cerrarResultado(finalistasMixInd2020[2], finalistasMixInd2020[0], finalistasMixInd2020[6], LocalDateTime.parse("06/06/2020 15:04", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalMascEquipos2020.cerrarResultado(finalistasMascEquipos2020[0], finalistasMascEquipos2020[2], finalistasMascEquipos2020[4], LocalDateTime.parse("07/06/2020 12:34", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalFemEquipos2020.cerrarResultado(finalistasFemEquipos2020[4], finalistasFemEquipos2020[3], finalistasFemEquipos2020[0], LocalDateTime.parse("07/06/2020 12:55", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalMixEquipos2020.cerrarResultado(finalistasMixEquipos2020[3], finalistasMixEquipos2020[0], finalistasMixEquipos2020[2], LocalDateTime.parse("07/06/2020 13:13", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalSanSilvestreMasc2020.cerrarResultado(finalistasSanSilvestreMasc2020[1], finalistasSanSilvestreMasc2020[3], finalistasSanSilvestreMasc2020[5], LocalDateTime.parse("31/12/2020 19:30", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalSanSilvestreFem2020.cerrarResultado(finalistasSanSilvestreFem2020[4], finalistasSanSilvestreFem2020[1], finalistasSanSilvestreFem2020[0], LocalDateTime.parse("31/12/2020 19:34", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalSanSilvestreMix2020.cerrarResultado(finalistasSanSilvestreMix2020[2], finalistasSanSilvestreMix2020[6], finalistasSanSilvestreMix2020[3], LocalDateTime.parse("31/12/2020 19:35", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalRegMascInd2021.cerrarResultado(finalistasRegMascInd2021[0], finalistasRegMascInd2021[3], finalistasRegMascInd2021[6], LocalDateTime.parse("19/06/2021 15:04", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalRegFemInd2021.cerrarResultado(finalistasRegFemInd2021[0], finalistasRegFemInd2021[7], finalistasRegFemInd2021[4], LocalDateTime.parse("19/06/2021 15:18", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalRegMixInd2021.cerrarResultado(finalistasRegMixInd2021[2], finalistasRegMixInd2021[4], finalistasRegMixInd2021[0], LocalDateTime.parse("19/06/2021 15:24", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalCoronaMasc2021.cerrarResultado(finalistasCoronaMasc2021[9], finalistasCoronaMasc2021[1], finalistasCoronaMasc2021[2], LocalDateTime.parse("10/07/2021 12:27", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalCoronaFem2021.cerrarResultado(finalistasCoronaFem2021[8], finalistasCoronaFem2021[0], finalistasCoronaFem2021[1], LocalDateTime.parse("10/07/2021 12:32", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalCoronaMix2021.cerrarResultado(finalistasCoronaMix2021[5], finalistasCoronaMix2021[4], finalistasCoronaMix2021[3], LocalDateTime.parse("10/07/2021 12:46", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		resultadoFinalCoronaEspecial2021.cerrarResultado(finalistasCoronaEspecial2021[2], finalistasCoronaEspecial2021[6], finalistasCoronaEspecial2021[8], LocalDateTime.parse("10/07/2021 12:17", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		return true;
	}

	public static Prueba[] PRUEBAS = {
			//Campeonato individual 2020
			new Prueba(1, "Final Masculina individual", LocalDate.parse("06/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasMascInd2020, arbitrosFinalMascInd2020, resultadoFinalMascInd2020),
			new Prueba(2, "Final Femenina individual", LocalDate.parse("06/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasFemInd2020, arbitrosFinalFemInd2020, resultadoFinalFemInd2020),
			new Prueba(3, "Final Mixta individual", LocalDate.parse("06/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasMixInd2020,  arbitrosFinalMixInd2020, resultadoFinalMixInd2020),
			//Campeonato por equipos 2020
			new Prueba(4, "Final Masculina por equipos", LocalDate.parse("07/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, false, finalistasMascEquipos2020,  arbitrosFinalMascEquipos2020, resultadoFinalMascEquipos2020),
			new Prueba(5, "Final Femenina por equipos", LocalDate.parse("07/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, false, finalistasFemEquipos2020, arbitrosFinalFemEquipos2020, resultadoFinalFemEquipos2020),
			new Prueba(6, "Final Mixta por equipos", LocalDate.parse("07/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, false, finalistasMixEquipos2020, arbitrosFinalMixEquipos2020, resultadoFinalMixEquipos2020),
			//San Silvestre 2020
			new Prueba(7, "Final Masculina San Silvestre", LocalDate.parse("31/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.OVIEDOCENTRO, true, finalistasSanSilvestreMasc2020, arbitrosFinalSanSilvestreMasc2020, resultadoFinalSanSilvestreMasc2020),
			new Prueba(8, "Final Femenina San Silvestre", LocalDate.parse("31/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.OVIEDOCENTRO, true, finalistasSanSilvestreFem2020, arbitrosFinalSanSilvestreFem2020, resultadoFinalSanSilvestreFem2020),
			new Prueba(9, "Final Mixta San Silvestre", LocalDate.parse("31/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.OVIEDOCENTRO, true, finalistasSanSilvestreMix2020, arbitrosFinalSanSilvestreMix2020, resultadoFinalSanSilvestreMix2020),
			//Campeonato Regional Individual 2021
			new Prueba(10, "Final Masculina Regional individual", LocalDate.parse("19/06/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.AVILESPABELLON, true, finalistasRegMascInd2021, arbitrosFinalRegMascInd2021, resultadoFinalRegMascInd2021),
			new Prueba(11, "Final Femenina Regional individual", LocalDate.parse("19/06/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasRegFemInd2021, arbitrosFinalRegFemInd2021, resultadoFinalRegFemInd2021),
			new Prueba(12, "Final Mixta Regional individual", LocalDate.parse("19/06/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasRegMixInd2021, arbitrosFinalRegMixInd2021, resultadoFinalRegMixInd2021),
			//Memorial víctimas coronavirus
			new Prueba(13, "Final Masculina memorial coronavirus", LocalDate.parse("10/07/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasCoronaMasc2021,  arbitrosFinalCoronaMasc2021, resultadoFinalCoronaMasc2021),
			new Prueba(14, "Final Femenina memorial coronavirus", LocalDate.parse("10/07/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasCoronaFem2021, arbitrosFinalCoronaFem2021, resultadoFinalCoronaFem2021),
			new Prueba(15, "Final Mixta memorial coronavirus", LocalDate.parse("11/07/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasCoronaMix2021, arbitrosFinalCoronaMix2021, resultadoFinalCoronaMix2021),
			new Prueba(16, "Final Especial memorial coronavirus", LocalDate.parse("11/07/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Lugar.GIJONMESTAS, true, finalistasCoronaEspecial2021, arbitrosFinalCoronaEspecial2021, resultadoFinalCoronaEspecial2021)
	};

	public static Prueba[] pruebasCampeonatoInd2020 = {PRUEBAS[0], PRUEBAS[1], PRUEBAS[2]};
	public static Prueba[] pruebasCampeonatoEquipos2020 = {PRUEBAS[3], PRUEBAS[4], PRUEBAS[5]};
	public static Prueba[] pruebasSanSilvestre2020 = {PRUEBAS[6], PRUEBAS[7], PRUEBAS[8]};
	public static Prueba[] pruebasCampeonatoRegInd2021 = {PRUEBAS[9], PRUEBAS[10], PRUEBAS[11]};
	public static Prueba[] pruebasMemorialCoronavirus2021 = {PRUEBAS[12], PRUEBAS[13], PRUEBAS[14], PRUEBAS[15]};

	public static Competicion[] COMPETICIONES = {
			new Competicion(1, "Campeonato individual", 2020, pruebasCampeonatoInd2020),
			new Competicion(2, "Campeonato por Equipos", 2020, pruebasCampeonatoEquipos2020),
			new Competicion(3, "San Silvestre", 2020, pruebasSanSilvestre2020),
			new Competicion(4, "Campeonato Regional Individual", 2021, pruebasCampeonatoRegInd2021),
			new Competicion(5, "Memorial por víctimas del coronavirus", 2021, pruebasMemorialCoronavirus2021)
	};

	/***
	 * Funcion que se le pasa un idPersona y devuelve el objeto DatosPersona asociado o null si no existe
	 * @param id de la Persona a buscar
	 * @return objeto DatosPersona asociado al id que se pasa como parametro o null si no lo encuentra
	 */
	public static DatosPersona buscarPersonaPorId(long id) {
		DatosPersona ret = null;
		for(DatosPersona dp: Datos.PERSONAS) {
			if(dp.getId()==id)
				return ret = dp;
		}
		return ret;
	}
}
