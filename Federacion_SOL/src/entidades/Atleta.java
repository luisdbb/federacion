package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utils.ConexBD;
import utils.Datos;
import utils.Utilidades;
import validaciones.Validaciones;

public class Atleta extends Participante implements operacionesCRUD<Atleta> {
	private long idAtleta;
	private float altura;
	private float peso;

	private DatosPersona persona;

	public Atleta() {
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso, DatosPersona dp) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idAtleta, float altura, float peso, DatosPersona dp) {
		super();
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idParticipante, Atleta a, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idAtleta = a.idAtleta;
		this.altura = a.altura;
		this.peso = a.peso;
		this.persona = Datos.buscarPersonaPorId(a.idAtleta);
	}

	@Override
	public long getId() {
		return idAtleta;
	}

	@Override
	public void setId(long id) {
		this.idAtleta = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	// Examen 5 Ejercicio 5
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Atleta, los valida y si son correctos devuelve un objeto Atleta completo
	 * 
	 * @return un objeto Atleta completo válido o null si hubo algún error
	 */
	public static Atleta nuevoAtleta() {
		Atleta ret = null;
		long id = -1;
		float altura = 0.0F;
		float peso = 0.0F;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo atleta:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca el peso del nuevo atleta (xx,xx)Kgs:");
			peso = Utilidades.leerFloat();
			valido = Validaciones.validarPeso(peso);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el peso no es válido.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la altura del nuevo atleta (xx,xx)m:");
			altura = Utilidades.leerFloat();
			valido = Validaciones.validarAltura(altura);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la altura no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Atleta(id, altura, peso, dp);
		return ret;
	}

	/***
	 * Función que devuelve una cadena de caracteres con los datos del atleta con el
	 * siguiente formato: <nombre> “ (” <documentacion> ”) del año
	 * ”<fechaNac.año>’\t’<peso>”Kgs. ”<altura>”m.“
	 */
	@Override
	public String toString() {
		return "" + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + "\t" + peso + "Kgs. " + altura + "m.";
	}

	@Override
	public boolean insertarConID(Atleta a) {
		boolean ret = false;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 = "insert into personas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		String consultaInsertStr3 = "insert into atletas(id, altura, peso, idequipo, idpersona) values (?,?,?,?,?)";
		try {

			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setLong(1, a.getPersona().getId());
			pstmt.setString(2, a.getPersona().getNombre());
			pstmt.setString(3, a.getPersona().getTelefono());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(a.getPersona().getFechaNac());
			pstmt.setDate(4, fechaSQL);
			pstmt.setString(5, a.getPersona().getNifnie().mostrar());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				PreparedStatement pstmt2 = conex.prepareStatement(consultaInsertStr3);
				pstmt2.setLong(1, a.getId());
				pstmt2.setFloat(2, a.getAltura());
				pstmt2.setFloat(3, a.getPeso());
				pstmt2.setNull(4, java.sql.Types.INTEGER);
				pstmt2.setLong(5, a.getPersona().getId());
				int resultadoInsercion2 = pstmt2.executeUpdate();
				if (resultadoInsercion2 == 1) {
					System.out.println("Se ha insertado correctamente el nuevo Atleta.");
					ret = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return ret;
	}

	@Override
	public long insertarSinID(Atleta a) {
		long ret = -1;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 = "insert into personas(nombre, telefono, fechanac, nifnie) values (?,?,?,?)";
		String consultaInsertStr3 = "insert into atletas(altura, peso, idequipo, idpersona) values (?,?,?,?)";
		try {

			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setString(1, a.getPersona().getNombre());
			pstmt.setString(2, a.getPersona().getTelefono());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(a.getPersona().getFechaNac());
			pstmt.setDate(3, fechaSQL);
			pstmt.setString(4, a.getPersona().getNifnie().mostrar());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM personas WHERE (nombre=? AND telefono=? " + "AND nifnie=?)";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, a.getPersona().getNombre());
				pstmt2.setString(2, a.getPersona().getTelefono());
				pstmt2.setString(3, a.getPersona().getNifnie().mostrar());
				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long idpersona = result.getLong("id");
					if (idpersona != -1) {
						PreparedStatement pstmt21 = conex.prepareStatement(consultaInsertStr3);
						pstmt21.setFloat(1, a.getAltura());
						pstmt21.setFloat(2, a.getPeso());
						pstmt21.setNull(3, java.sql.Types.INTEGER);
						pstmt21.setLong(4, idpersona);
						int resultadoInsercion2 = pstmt21.executeUpdate();
						if (resultadoInsercion2 == 1) {
							String consultaSelect2 = "SELECT id FROM atletas WHERE (altura=? AND peso=? "
									+ "AND idpersona=?)";
							PreparedStatement pstmt3 = conex.prepareStatement(consultaSelect2);
							pstmt3.setFloat(1, a.getAltura());
							pstmt3.setFloat(2, a.getPeso());
							pstmt3.setLong(3, a.getPersona().getId());
							ResultSet result3 = pstmt3.executeQuery();
							while (result3.next()) {
								long idatleta = result3.getLong("id");
								if (idatleta != -1) {
									System.out.println(
											"Se ha insertado correctamente el nuevo Atleta de id: " + idatleta);
									return idatleta;
								}
							}

						}
					}

				}
				result.close();
				pstmt2.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

}
