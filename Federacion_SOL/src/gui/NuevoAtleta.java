package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import DAO.AtletaDAO;
import DAO.EquipoDAO;
import DAO.PruebaDAO;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import entidades.Atleta;
import entidades.DatosPersona;
import entidades.Documentacion;
import entidades.Equipo;
import entidades.NIE;
import entidades.NIF;
import entidades.Patrocinador;
import utils.ConexBD;
import validaciones.Validaciones;

public class NuevoAtleta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPeso;
	private JTextField textFieldAltura;
	private JComboBox comboBoxEquipo = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				try {
					////API JTatoo para GUI en java
					////https://malalanayake.wordpress.com/2012/10/16/java-themes-with-jtattoo/
					///OFICIAL: http://www.jtattoo.net/index.html
		            UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//		            UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//		            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
//		            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

		            
		            
		            
		            NuevoAtleta frame = new NuevoAtleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoAtleta() {
		setTitle("Nuevo Atleta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Cerrar ventana";
				String msj = "¿Realmente desea cerrar la ventana?";
				int opcion = JOptionPane.showConfirmDialog(null, msj, titulo, JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					/// Aqui se redirigiría al usuario hacia la pantalla principal o se saldria
					System.exit(0); /// SALIR directamente
				}
			}
		});
		buttonCancelar.setBounds(364, 327, 93, 23);
		contentPane.add(buttonCancelar);

		JPanel paneldatospersonales = new NuevaPersona();
		paneldatospersonales.setBounds(10, 11, 447, 181);
		contentPane.add(paneldatospersonales);

		JPanel paneldatosfisicos = new JPanel();
		paneldatosfisicos.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Datos F\u00EDsicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		paneldatosfisicos.setBounds(10, 203, 447, 74);
		contentPane.add(paneldatosfisicos);
		paneldatosfisicos.setLayout(null);

		JLabel lblAltura = new JLabel("Altura *:");
		lblAltura.setBounds(10, 23, 68, 14);
		paneldatosfisicos.add(lblAltura);

		JLabel lblPeso = new JLabel("Peso *:");
		lblPeso.setBounds(10, 48, 44, 14);
		paneldatosfisicos.add(lblPeso);

		textFieldPeso = new JTextField();
		textFieldPeso.setBounds(83, 45, 57, 20);
		paneldatosfisicos.add(textFieldPeso);
		textFieldPeso.setColumns(10);

		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(83, 20, 57, 20);
		paneldatosfisicos.add(textFieldAltura);

		JLabel lblNewLabel = new JLabel("m. (en formato xx.xx)");
		lblNewLabel.setBounds(150, 23, 265, 14);
		paneldatosfisicos.add(lblNewLabel);

		JLabel lblKgs = new JLabel("Kg. (en formato xx.x)");
		lblKgs.setBounds(150, 48, 265, 14);
		paneldatosfisicos.add(lblKgs);

		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(20, 293, 46, 14);
		contentPane.add(lblEquipo);

		EquipoDAO eqDAO = new EquipoDAO(ConexBD.getCon());
		ArrayList<Equipo> equiposList = (ArrayList<Equipo>) eqDAO.buscarTodos();

		String[] equiposStr = new String[equiposList.size() + 1];
		equiposStr[0] = "NINGUNO";
		for (int i = 0; i < equiposList.size(); i++)
			equiposStr[i + 1] = equiposList.get(i).toString();
		comboBoxEquipo.setModel(new DefaultComboBoxModel(equiposStr));
		comboBoxEquipo.setBounds(94, 288, 299, 22);
		contentPane.add(comboBoxEquipo);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				Atleta nuevo = new Atleta();
				boolean valido = false;
				String titulo;
				String msj;
				String errores = "";

				String nombre = ((NuevaPersona) paneldatospersonales).getTxtNombre().getText();
				valido = Validaciones.validarNombre(nombre);
				if (!valido)
					errores += "\nEl nombre no es válido. Ha de estar entre 3 y 50 caracteres sin números.";

				valido = false;
				Documentacion doc;
				boolean esnif = ((NuevaPersona) paneldatospersonales).getRbNIF().isSelected();
				if (esnif) {
					doc = new NIF(((NuevaPersona) paneldatospersonales).getTxtNIFNIE().getText());
					valido = Validaciones.validarNIF((NIF) doc);
				} else {
					doc = new NIE(((NuevaPersona) paneldatospersonales).getTxtNIFNIE().getText());
					valido = Validaciones.validarNIE((NIE) doc);
				}
				if (!valido)
					errores += "\nEl NIF/NIE introducido no es válido.";

				valido = false;
				String telefono = ((NuevaPersona) paneldatospersonales).getTxtTelefono().getText();
				valido = Validaciones.validarTelefono(telefono);
				if (!valido)
					errores += "\nEl telefono introducido no es válido. Solo acepta digitos. ";

				valido = false;
				java.util.Date fecha = (java.util.Date) ((NuevaPersona) paneldatospersonales).getSpinnerFechaNac()
						.getValue();
				valido = Validaciones.validarFechaNuevoAtleta(fecha);
				LocalDate fechaLD = null;
				if (!valido)
					errores += "\nLa fecha ha de ser posteriore a 1/1/1960.";
				else
					fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());
				valido = false;
				float altura = 0.0f;
				try {
					altura = Float.valueOf(getTextFieldAltura().getText());
				} catch (Exception ex) {
					System.out.println("El formato para el peso es inválido.");
				}
				valido = Validaciones.validarAltura(altura);
				if (!valido)
					errores += "\nLa altura no es válida. Formato XX.XX expresado en metros.";

				valido = false;
				float peso = 0.0f;
				try {
					peso = Float.valueOf(getTextFieldPeso().getText());
				} catch (Exception ex) {
					System.out.println("El formato para el peso es inválido.");
				}
				valido = Validaciones.validarPeso(peso);
				if (!valido)
					errores += "\nEl peso no es válido. Formato XX.X expresado en Kilogramos.";

				valido = false;
				// Recuperar El equipo desde el combobox
				boolean perteneceAEquipo = false;
				long idequipo = 0;
				valido = getComboBoxEquipo().getSelectedIndex() != -1;
				if (valido) {
					String equipoStr = getComboBoxEquipo().getSelectedItem().toString();
					if (!equipoStr.equals("NINGUNO")) {
						perteneceAEquipo = true;
						String[] aux = equipoStr.split("\\.");
						idequipo = Long.valueOf(aux[0]);
					}
				} else
					errores += "\nHay que se selecionar un valor del comboBox.";

				valido = errores.isEmpty();
				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos del Nuevo Atleta NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (nuevo.getPersona() == null) {
					nuevo.setPersona(new DatosPersona(0, nombre, telefono, fechaLD, doc));
				}
				nuevo.getPersona().setNombre(nombre);
				nuevo.getPersona().setNifnie(doc);
				nuevo.getPersona().setTelefono(telefono);
				nuevo.getPersona().setFechaNac(fechaLD);
				nuevo.setAltura(altura);
				nuevo.setPeso(peso);
				nuevo.setIdEquipo(idequipo);

				int opcion = JOptionPane.showConfirmDialog(null,
						"¿Son correctos los datos del nuevo atleta?" + nuevo.toString(),
						"Confirmar datos del nuevo atleta", JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.NO_OPTION) {
					return;
				}

				boolean exportadoOK = false;
				/// Si todos los datos son correctos, llamar a atletaDAO para insertar en la BD
				AtletaDAO adao = new AtletaDAO(ConexBD.establecerConexion());
				long idatletanuevo = adao.insertarSinID(nuevo);
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (idatletanuevo <= 0) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al insertar el Nuevo Atleta en la BD";
					msj = "Hubo un error y NO se ha insertado el nuevo atleta en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					nuevo.setId(idatletanuevo);
					titulo = "Nuevo Atleta insertado en la BD";
					msj = "Se ha insertado correctamente el nuevo atleta:\n" + nuevo.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);

					/*
					 * 1. El idatleta resultante tras la inserción en la tabla atletas (Long) 
					 * 2. El nombre del atleta (String) 
					 * 3. El NIF o NIE del atleta (Documentacion) 
					 * 4. La fecha de nacimiento del atleta (java.time.LocalDate) 
					 * 5. La altura del atleta (Float) 
					 * 6. El peso del atleta (Float) 
					 * 7. El teléfono del atleta (String) 
					 * 8. El idpersona resultante tras la inserción en la tabla personas (Long)
					 */
					File f = new File(""+nuevo.getPersona().getNifnie().mostrar()+".dat");
					FileOutputStream fo;
					try {
							fo = new FileOutputStream(f);
							ObjectOutputStream oos = new ObjectOutputStream(fo);
							oos.writeLong(idatletanuevo);
							oos.writeUTF(nuevo.getPersona().getNombre());
							oos.writeObject((Documentacion)nuevo.getPersona().getNifnie());
							oos.writeObject((LocalDate)nuevo.getPersona().getFechaNac());
							oos.writeFloat(nuevo.getAltura());
							oos.writeFloat(nuevo.getPeso());
							oos.writeUTF(telefono);
							oos.writeLong(nuevo.getPersona().getId());
							oos.flush();
							oos.close();
							fo.close();
							titulo = "Nuevo Atleta exportado a fichero";
							msj = "Se ha exportado correctamente el nuevo atleta al fichero " + f.getName();
							JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
							/// Aqui se redirigiría al usuario hacia la pantalla principal
							/// TODO
							System.out.println("Se han exportado los datos del nuevo Atleta al fichero "+f.getName());
					} catch (FileNotFoundException exc) {
						System.out.println("Se ha producido una FileNotFoundException:" + exc.getMessage());
						exc.printStackTrace();
					} catch (IOException ex) {
						System.out.println("Se ha producido una IOException:" + ex.getMessage());
						ex.printStackTrace();
					}

				}
				ConexBD.cerrarConexion();
				/// Aqui se redirigiría al usuario hacia la pantalla principal
				/// TODO
			}
		});
		btnAceptar.setBounds(270, 327, 89, 23);
		contentPane.add(btnAceptar);
	}

	public JTextField getTextFieldPeso() {
		return textFieldPeso;
	}

	public void setTextFieldPeso(JTextField textFieldPeso) {
		this.textFieldPeso = textFieldPeso;
	}

	public JTextField getTextFieldAltura() {
		return textFieldAltura;
	}

	public void setTextFieldAltura(JTextField textFieldAltura) {
		this.textFieldAltura = textFieldAltura;
	}

	public JComboBox getComboBoxEquipo() {
		return comboBoxEquipo;
	}

	public void setComboBoxEquipo(JComboBox comboBoxEquipo) {
		this.comboBoxEquipo = comboBoxEquipo;
	}

}
