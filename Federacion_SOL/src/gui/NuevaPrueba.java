package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import DAO.PatrocinadorDAO;
import DAO.PruebaDAO;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import entidades.Lugar;
import entidades.Patrocinador;
import entidades.Prueba;
import utils.ConexBD;
import utils.Utilidades;
import validaciones.Validaciones;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class NuevaPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private final ButtonGroup buttonGroupTipo = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaPrueba frame = new NuevaPrueba();
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
	public NuevaPrueba() {
		setTitle("Nueva Prueba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre *:");
		lblNombre.setBounds(39, 30, 77, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(126, 27, 261, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha *:");
		lblFecha.setBounds(39, 63, 46, 14);
		contentPane.add(lblFecha);

		JSpinner spinnerFecha = new JSpinner();
		LocalDate hoyMas1MesLD = LocalDate.now().plusMonths(1);
		java.util.Date hoyMas1Mes = new Date(hoyMas1MesLD.getYear() - 1900, hoyMas1MesLD.getMonthValue() - 1,
				hoyMas1MesLD.getDayOfMonth());
		spinnerFecha.setModel(new SpinnerDateModel(hoyMas1Mes, hoyMas1Mes, null, Calendar.DAY_OF_YEAR));
		spinnerFecha.setBounds(102, 58, 123, 20);
		contentPane.add(spinnerFecha);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tipo de Prueba *:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(39, 128, 210, 52);
		contentPane.add(panel);

		JRadioButton rbIndividual = new JRadioButton("Individual");
		buttonGroupTipo.add(rbIndividual);
		panel.add(rbIndividual);

		JRadioButton rbEquipos = new JRadioButton("Por Equipos");
		buttonGroupTipo.add(rbEquipos);
		panel.add(rbEquipos);

		JLabel lblLugar = new JLabel("Lugar *:");
		lblLugar.setBounds(39, 103, 46, 14);
		contentPane.add(lblLugar);

		JComboBox comboBoxLugar = new JComboBox();
		comboBoxLugar.setModel(new DefaultComboBoxModel(Lugar.values()));
		comboBoxLugar.setBounds(102, 99, 169, 22);
		contentPane.add(comboBoxLugar);

		JLabel lblPatrocinador = new JLabel("Patrocinador *:");
		lblPatrocinador.setBounds(39, 187, 87, 14);
		contentPane.add(lblPatrocinador);

		/// Las siguientes lineas seria lo deseable: trabajar diectamente con objetos en
		/// el model del comboBox
		DefaultComboBoxModel<Patrocinador> patrocinadoresModel = new DefaultComboBoxModel<Patrocinador>();
		JComboBox<Patrocinador> comboBoxPatrocinador = new JComboBox<Patrocinador>(patrocinadoresModel);
		PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
		ArrayList<Patrocinador> patrocinadoresList = (ArrayList<Patrocinador>) patDAO.buscarTodos();
		for (Patrocinador pat : patrocinadoresList)
			comboBoxPatrocinador.addItem(pat);

		/// Pero el modelo de comboBox básico requiere Strings
		String[] patrocinadoresStr = new String[patrocinadoresList.size()];
		for (int i = 0; i < patrocinadoresList.size(); i++)
			patrocinadoresStr[i] = patrocinadoresList.get(i).mostrarBasico();
		comboBoxPatrocinador.setModel(new DefaultComboBoxModel(patrocinadoresStr));

		comboBoxPatrocinador.setBounds(136, 183, 251, 22);
		contentPane.add(comboBoxPatrocinador);

		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prueba nueva = new Prueba();
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				/// Tomar cada campo y validarlo. Si alguno no es correcto, avisar al usuario
				String nombre = textFieldNombre.getText();
				valido = Validaciones.validarNombrePrueba(nombre);
				if (!valido) {
					errores += "El nombre de la prueba no es válido (5-150 caracteres).\n";
					lblNombre.setForeground(Color.RED);
				} else
					nueva.setNombre(nombre);
				valido = false;

				java.util.Date fecha = (java.util.Date) spinnerFecha.getValue();
				valido = Validaciones.validarFechaNuevaPrueba(fecha);
				if (!valido) {
					errores += "La fecha de la prueba no es válido (posterior a 1 mes desde hoy).\n";
					lblFecha.setForeground(Color.RED);
				} else {
					LocalDate fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());
					nueva.setFecha(fechaLD);
				}
				valido = false;
				valido = (comboBoxLugar.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el lugar de celebración de la nueva prueba.\n";
					lblLugar.setForeground(Color.RED);
				} else {
					Lugar lugar = (Lugar) comboBoxLugar.getSelectedItem();
					nueva.setLugar(lugar);
				}
				valido = false;
				valido = !(!rbIndividual.isSelected() && !rbEquipos.isSelected())
						|| (rbIndividual.isSelected() && rbEquipos.isSelected());
				if (!valido) {
					errores += "Debe seleccionar si la nueva prueba es individual O por equipos.\n";
					rbIndividual.setForeground(Color.RED);
					rbEquipos.setForeground(Color.RED);
				} else {
					nueva.setIndividual(rbIndividual.isSelected() ? true : false);
				}
				valido = false;
				valido = (comboBoxPatrocinador.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el Patrocinador de la nueva prueba.\n";
					lblPatrocinador.setForeground(Color.RED);
				} else {
					PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxPatrocinador.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idPat = Long.valueOf(aux[0]);
					Patrocinador patrocinador = patDAO.buscarPorID(idPat);
					ConexBD.cerrarConexion();
					if (patrocinador == null)
						valido = false;
					else
						nueva.setPatrocinador(patrocinador);
				}
				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos de Nueva Prueba NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}

				/// Si todos los datos son correctos, llamar a PruebaDAO para insertar en la BD
				PruebaDAO pruebadao = new PruebaDAO(ConexBD.establecerConexion());
				long idpruebanuevo = pruebadao.insertarSinID(nueva);
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (idpruebanuevo <= 0) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al insertar la Nueva Prueba en la BD";
					msj = "Hubo un error y NO se ha insertado la nueva prueba en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					nueva.setId(idpruebanuevo);
					titulo = "Nueva Prueba insertada en la BD";
					msj = "Se ha insertado correctamente la nueva prueba:\n" + nueva.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
					///Aqui se redirigiría al usuario hacia la pantalla principal
					///TODO
				}
			}
		});
		buttonAceptar.setBounds(193, 227, 89, 23);
		contentPane.add(buttonAceptar);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(298, 227, 89, 23);
		contentPane.add(buttonCancelar);
	}
}
