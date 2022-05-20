package gui;

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

import DAO.AtletaDAO;
import DAO.MetalDAO;
import DAO.PatrocinadorDAO;
import DAO.PruebaDAO;
import DAO.ResultadoDAO;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import entidades.*;
import utils.ConexBD;
import validaciones.Validaciones;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class CerrarPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private final ButtonGroup buttonGroupTipo = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int idprueba = 1;
					CerrarPrueba frame = new CerrarPrueba(idprueba);
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
	public CerrarPrueba(int idprueba) {
		PruebaDAO pDAO = new PruebaDAO(ConexBD.getCon());
		Prueba prueba = pDAO.buscarPorID(idprueba);
		if (prueba != null) {
			setTitle("Cerrar Prueba" + idprueba);
		} else
			setTitle("Cerrar Prueba INDETERMINADA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Datos de la prueba", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 0, 424, 199);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIdPrueba = new JLabel("IdPrueba:");
		lblIdPrueba.setBounds(22, 21, 77, 14);
		panel_1.add(lblIdPrueba);

		textField = new JTextField("" + prueba.getId());
		textField.setBounds(86, 14, 86, 20);
		panel_1.add(textField);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 48, 77, 14);
		panel_1.add(lblNombre);

		textFieldNombre = new JTextField(prueba.getNombre());
		textFieldNombre.setBounds(86, 41, 261, 20);
		panel_1.add(textFieldNombre);
		textFieldNombre.setEnabled(false);
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(22, 73, 46, 14);
		panel_1.add(lblFecha);

		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(200, 73, 46, 14);
		panel_1.add(lblLugar);

		JComboBox comboBoxLugar = new JComboBox();
		comboBoxLugar.setBounds(245, 69, 169, 22);
		panel_1.add(comboBoxLugar);
		comboBoxLugar.setEnabled(false);
		comboBoxLugar.setModel(new DefaultComboBoxModel(Lugar.values()));

		JPanel panel = new JPanel();
		panel.setBounds(22, 98, 210, 52);
		panel_1.add(panel);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tipo de Prueba:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JRadioButton rbIndividual = new JRadioButton("Individual");

		rbIndividual.setEnabled(false);
		buttonGroupTipo.add(rbIndividual);
		panel.add(rbIndividual);

		JRadioButton rbEquipos = new JRadioButton("Por Equipos");
		rbEquipos.setEnabled(false);
		buttonGroupTipo.add(rbEquipos);
		panel.add(rbEquipos);
		if (prueba.isIndividual())
			rbIndividual.setSelected(true);
		else
			rbEquipos.setSelected(true);

		JLabel lblPatrocinador = new JLabel("Patrocinador:");
		lblPatrocinador.setBounds(22, 161, 87, 14);
		panel_1.add(lblPatrocinador);

		JLabel lblprimerpuesto = new JLabel("Primer puesto *:");
		lblprimerpuesto.setBounds(10, 203, 109, 14);
		contentPane.add(lblprimerpuesto);

		JLabel lblsegundopuesto = new JLabel("Segundo puesto *:");
		lblsegundopuesto.setBounds(10, 292, 98, 14);
		contentPane.add(lblsegundopuesto);

		JLabel lbltercerpuesto = new JLabel("Tercer puesto *:");
		lbltercerpuesto.setBounds(10, 381, 109, 14);
		contentPane.add(lbltercerpuesto);

		DefaultComboBoxModel<Atleta> atletasModel = new DefaultComboBoxModel<Atleta>();
		AtletaDAO aDAO = new AtletaDAO(ConexBD.getCon());
		ArrayList<Atleta> atletassList = (ArrayList<Atleta>) aDAO.buscarTodos();
		/// Pero el modelo de comboBox básico requiere Strings
		String[] atletasStr = new String[atletassList.size()];

		for (int k = 0; k < atletassList.size(); k++)
			atletasStr[k] = atletassList.get(k).getPersona().data();

		JComboBox<Atleta> comboBoxPuesto1 = new JComboBox<Atleta>();
		comboBoxPuesto1.setModel(new DefaultComboBoxModel(atletasStr));
		lblprimerpuesto.setLabelFor(comboBoxPuesto1);
		comboBoxPuesto1.setBounds(111, 199, 287, 22);
		contentPane.add(comboBoxPuesto1);

		JComboBox<Atleta> comboBoxPuesto2 = new JComboBox<Atleta>();
		comboBoxPuesto2.setModel(new DefaultComboBoxModel(atletasStr));
		lblsegundopuesto.setLabelFor(comboBoxPuesto2);
		comboBoxPuesto2.setBounds(111, 288, 287, 22);
		contentPane.add(comboBoxPuesto2);

		JComboBox<Atleta> comboBoxPuesto3 = new JComboBox<Atleta>();
		comboBoxPuesto3.setModel(new DefaultComboBoxModel(atletasStr));
		lbltercerpuesto.setLabelFor(comboBoxPuesto3);
		comboBoxPuesto3.setBounds(111, 377, 287, 22);
		contentPane.add(comboBoxPuesto3);

		LocalDate hoyMas1MesLD = LocalDate.now().plusMonths(1);
		java.util.Date hoyMas1Mes = new Date(hoyMas1MesLD.getYear() - 1900, hoyMas1MesLD.getMonthValue() - 1,
				hoyMas1MesLD.getDayOfMonth());

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
		comboBoxPatrocinador.setBounds(96, 157, 261, 22);
		panel_1.add(comboBoxPatrocinador);
		comboBoxPatrocinador.setEnabled(false);

		JSpinner spinnerFecha = new JSpinner();
		spinnerFecha.setBounds(86, 69, 86, 20);
		panel_1.add(spinnerFecha);
		spinnerFecha.setEnabled(false);
		spinnerFecha.setModel(new SpinnerDateModel(hoyMas1Mes, hoyMas1Mes, null, Calendar.DAY_OF_YEAR));

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
		buttonCancelar.setBounds(345, 510, 89, 23);
		contentPane.add(buttonCancelar);

		JLabel lblNewLabel = new JLabel("Establecer como DEFINITIVO:");
		lblNewLabel.setBounds(10, 473, 183, 14);
		contentPane.add(lblNewLabel);

		JCheckBox chckbxDefinitivo = new JCheckBox("");
		chckbxDefinitivo.setBounds(180, 468, 31, 23);
		contentPane.add(chckbxDefinitivo);

		JSpinner spinnerHoras_3 = new JSpinner();
		spinnerHoras_3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerHoras_3.setBounds(82, 403, 31, 20);
		contentPane.add(spinnerHoras_3);

		JSpinner spinnerMinutos_3 = new JSpinner();
		spinnerMinutos_3.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerMinutos_3.setBounds(164, 403, 39, 20);
		contentPane.add(spinnerMinutos_3);

		JSpinner spinnerSegundos_3 = new JSpinner();
		spinnerSegundos_3.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerSegundos_3.setBounds(264, 403, 39, 20);
		contentPane.add(spinnerSegundos_3);

		JSpinner spinnerCentesimas_3 = new JSpinner();
		spinnerCentesimas_3.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerCentesimas_3.setBounds(372, 403, 39, 20);
		contentPane.add(spinnerCentesimas_3);

		JLabel lblNewLabelHoras = new JLabel("Horas:");
		lblNewLabelHoras.setBounds(45, 406, 32, 14);
		contentPane.add(lblNewLabelHoras);

		JLabel lblNewLabelMinutos = new JLabel("Minutos:");
		lblNewLabelMinutos.setBounds(118, 406, 41, 14);
		contentPane.add(lblNewLabelMinutos);

		JLabel lblNewLabelSegundos = new JLabel("Segundos:");
		lblNewLabelSegundos.setBounds(208, 406, 51, 14);
		contentPane.add(lblNewLabelSegundos);

		JLabel lblNewLabelCentesimas = new JLabel("Centésimas:");
		lblNewLabelCentesimas.setBounds(308, 406, 59, 14);
		contentPane.add(lblNewLabelCentesimas);

		JSpinner spinnerHoras_1 = new JSpinner();
		spinnerHoras_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerHoras_1.setBounds(98, 228, 31, 20);
		contentPane.add(spinnerHoras_1);

		JSpinner spinnerMinutos_1 = new JSpinner();
		spinnerMinutos_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerMinutos_1.setBounds(180, 228, 39, 20);
		contentPane.add(spinnerMinutos_1);

		JSpinner spinnerSegundos_1 = new JSpinner();
		spinnerSegundos_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerSegundos_1.setBounds(280, 228, 39, 20);
		contentPane.add(spinnerSegundos_1);

		JSpinner spinnerCentesimas_1 = new JSpinner();
		spinnerCentesimas_1.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerCentesimas_1.setBounds(388, 228, 39, 20);
		contentPane.add(spinnerCentesimas_1);

		JLabel lblNewLabelHoras_1 = new JLabel("Horas:");
		lblNewLabelHoras_1.setBounds(61, 231, 32, 14);
		contentPane.add(lblNewLabelHoras_1);

		JLabel lblNewLabelMinutos_1 = new JLabel("Minutos:");
		lblNewLabelMinutos_1.setBounds(134, 231, 41, 14);
		contentPane.add(lblNewLabelMinutos_1);

		JLabel lblNewLabelSegundos_1 = new JLabel("Segundos:");
		lblNewLabelSegundos_1.setBounds(224, 231, 51, 14);
		contentPane.add(lblNewLabelSegundos_1);

		JLabel lblNewLabelCentesimas_1 = new JLabel("Centésimas:");
		lblNewLabelCentesimas_1.setBounds(324, 231, 59, 14);
		contentPane.add(lblNewLabelCentesimas_1);

		JComboBox comboBoxOros = new JComboBox();
		MetalDAO mDAO = new MetalDAO(ConexBD.getCon());
		ArrayList<Oro> orossList = (ArrayList<Oro>) mDAO.buscarTodosOros(true);
		String[] orosStr = new String[orossList.size()];
		for (int k = 0; k < orossList.size(); k++)
			orosStr[k] = orossList.get(k).toString();
		comboBoxOros.setModel(new DefaultComboBoxModel(orosStr));
		comboBoxOros.setBounds(117, 256, 158, 22);
		contentPane.add(comboBoxOros);

		JLabel lblOros = new JLabel("Id Oro *:");
		lblOros.setBounds(62, 254, 46, 14);
		contentPane.add(lblOros);

		JLabel lblNewLabelHoras_2 = new JLabel("Horas:");
		lblNewLabelHoras_2.setBounds(61, 320, 32, 14);
		contentPane.add(lblNewLabelHoras_2);

		JLabel lblNewLabelMinutos_2 = new JLabel("Minutos:");
		lblNewLabelMinutos_2.setBounds(134, 320, 41, 14);
		contentPane.add(lblNewLabelMinutos_2);

		JSpinner spinnerHoras_2 = new JSpinner();
		spinnerHoras_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerHoras_2.setBounds(98, 317, 31, 20);
		contentPane.add(spinnerHoras_2);

		JSpinner spinnerMinutos_2 = new JSpinner();
		spinnerMinutos_2.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerMinutos_2.setBounds(180, 317, 39, 20);
		contentPane.add(spinnerMinutos_2);

		JLabel lblNewLabelSegundos_2 = new JLabel("Segundos:");
		lblNewLabelSegundos_2.setBounds(224, 320, 51, 14);
		contentPane.add(lblNewLabelSegundos_2);

		JSpinner spinnerSegundos_2 = new JSpinner();
		spinnerSegundos_2.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinnerSegundos_2.setBounds(280, 317, 39, 20);
		contentPane.add(spinnerSegundos_2);

		JLabel lblNewLabelCentesimas_2 = new JLabel("Centésimas:");
		lblNewLabelCentesimas_2.setBounds(324, 320, 59, 14);
		contentPane.add(lblNewLabelCentesimas_2);

		JSpinner spinnerCentesimas_2 = new JSpinner();
		spinnerCentesimas_2.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerCentesimas_2.setBounds(388, 317, 39, 20);
		contentPane.add(spinnerCentesimas_2);

		JLabel lblplata = new JLabel("Id Plata *:");
		lblplata.setBounds(61, 345, 63, 14);
		contentPane.add(lblplata);

		JComboBox comboBoxPlatas = new JComboBox();
		ArrayList<Plata> platasList = (ArrayList<Plata>) mDAO.buscarTodosPlatas(true);
		String[] platasStr = new String[platasList.size()];
		for (int k = 0; k < platasList.size(); k++)
			platasStr[k] = platasList.get(k).toString();
		comboBoxPlatas.setModel(new DefaultComboBoxModel(platasStr));
		comboBoxPlatas.setBounds(118, 345, 157, 22);
		contentPane.add(comboBoxPlatas);

		JLabel lblbronce = new JLabel("Id Bronce *:");
		lblbronce.setBounds(55, 434, 63, 14);
		contentPane.add(lblbronce);

		JComboBox comboBoxBronces = new JComboBox();
		ArrayList<Bronce> broncesList = (ArrayList<Bronce>) mDAO.buscarTodosBronces(true);
		String[] broncesStr = new String[broncesList.size()];
		for (int k = 0; k < broncesList.size(); k++)
			broncesStr[k] = broncesList.get(k).toString();
		comboBoxBronces.setModel(new DefaultComboBoxModel(broncesStr));
		comboBoxBronces.setBounds(128, 434, 147, 22);
		contentPane.add(comboBoxBronces);

		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resultado nuevo = new Resultado();
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				Atleta[] podio = new Atleta[3];
				Atleta atleta1 = null, atleta2 = null, atleta3 = null;
				/// Tomar cada campo y validarlo. Si alguno no es correcto, avisar al usuario
				// atleta1
				valido = false;
				valido = (comboBoxPuesto1.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el primer puesto del resultado de la prueba.\n";
					lblprimerpuesto.setForeground(Color.RED);
				} else {
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxPuesto1.getSelectedItem();
					String[] aux = seleccionado.split("\\|");
					long idAt1 = 0;
					try {
						idAt1 = Long.valueOf(aux[0]);
					} catch (Exception ex) {

					}
					atleta1 = atDAO.buscarPorID(idAt1);
					ConexBD.cerrarConexion();
					if (atleta1 == null)
						valido = false;
					else
						podio[0] = atleta1;
				}
				// oro1
				valido = false;
				valido = (comboBoxOros.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el ORO para el primer puesto del resultado de la prueba.\n";
					lblOros.setForeground(Color.RED);
				} else {
					MetalDAO metDAO = new MetalDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxOros.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idoro = 0;
					try {
						idoro = Long.valueOf(aux[0]);
					} catch (Exception ex) {
					}
					Oro o = (Oro) metDAO.buscarPorID(idoro);
					ConexBD.cerrarConexion();
					if (o == null)
						valido = false;
					else
						nuevo.setPrimero(o);
				}
				// tiempo1
				Tiempo t1 = new Tiempo();
				t1.setHoras(Integer.valueOf(spinnerHoras_1.getValue().toString()));
				t1.setMinutos(Integer.valueOf(spinnerMinutos_1.getValue().toString()));
				t1.setSegundos(Integer.valueOf(spinnerSegundos_1.getValue().toString()));
				t1.setCentesimas(Integer.valueOf(spinnerCentesimas_1.getValue().toString()));
				valido = Validaciones.validarTiempo(t1);
				if (!valido) {
					errores += "El tiempo para el primer puesto NO es válido.\n";
				}

				// atleta2
				valido = false;
				valido = (comboBoxPuesto2.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el segundo puesto del resultado de la prueba.\n";
					lblsegundopuesto.setForeground(Color.RED);
				} else {
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxPuesto2.getSelectedItem();
					String[] aux = seleccionado.split("\\|");
					long idAt2 = 0;
					try {
						idAt2 = Long.valueOf(aux[0]);
					} catch (Exception ex) {
					}
					atleta2 = atDAO.buscarPorID(idAt2);
					ConexBD.cerrarConexion();
					if (atleta2 == null)
						valido = false;
					else
						podio[1] = atleta2;
				}
				// plata
				valido = false;
				valido = (comboBoxPlatas.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar la PLATA para el segundo puesto del resultado de la prueba.\n";
					lblplata.setForeground(Color.RED);
				} else {
					MetalDAO metDAO = new MetalDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxOros.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idplata = 0;
					try {
						idplata = Long.valueOf(aux[0]);
					} catch (Exception ex) {
					}
					Plata p = (Plata) metDAO.buscarPorID(idplata);
					ConexBD.cerrarConexion();
					if (p == null)
						valido = false;
					else
						nuevo.setSegundo(p);
				}
				// tiempo2
				Tiempo t2 = new Tiempo();
				t2.setHoras(Integer.valueOf(spinnerHoras_2.getValue().toString()));
				t2.setMinutos(Integer.valueOf(spinnerMinutos_2.getValue().toString()));
				t2.setSegundos(Integer.valueOf(spinnerSegundos_2.getValue().toString()));
				t2.setCentesimas(Integer.valueOf(spinnerCentesimas_2.getValue().toString()));
				valido = Validaciones.validarTiempo(t2);
				if (!valido) {
					errores += "El tiempo para el segundo puesto NO es válido.\n";
				}

				// atleta3
				valido = false;
				valido = (comboBoxPuesto3.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el tercer puesto del resultado de la prueba.\n";
					lbltercerpuesto.setForeground(Color.RED);
				} else {
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxPuesto3.getSelectedItem();
					String[] aux = seleccionado.split("\\|");
					long idAt3 = 0;
					try {
						idAt3 = Long.valueOf(aux[0]);
					} catch (Exception ex) {
					}
					atleta3 = atDAO.buscarPorID(idAt3);
					ConexBD.cerrarConexion();
					if (atleta3 == null)
						valido = false;
					else
						podio[2] = atleta3;
				}
				// bronce
				valido = false;
				valido = (comboBoxBronces.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el BRONCE para el terceer puesto del resultado de la prueba.\n";
					lblbronce.setForeground(Color.RED);
				} else {
					MetalDAO metDAO = new MetalDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxOros.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idbronce = 0;
					try {
						Long.valueOf(aux[0]);
					} catch (Exception ex) {
					}
					Bronce b = (Bronce) metDAO.buscarPorID(idbronce);
					ConexBD.cerrarConexion();
					if (b == null)
						valido = false;
					else
						nuevo.setTercero(b);
				}
				// tiempo3
				Tiempo t3 = new Tiempo();
				t3.setHoras(Integer.valueOf(spinnerHoras_3.getValue().toString()));
				t3.setMinutos(Integer.valueOf(spinnerMinutos_3.getValue().toString()));
				t3.setSegundos(Integer.valueOf(spinnerSegundos_3.getValue().toString()));
				t3.setCentesimas(Integer.valueOf(spinnerCentesimas_3.getValue().toString()));
				valido = Validaciones.validarTiempo(t3);
				if (!valido) {
					errores += "El tiempo para el tercer puesto NO es válido.\n";
				}
				/// validar que no esté el mismo atleta en varios puestos del podio
				valido = false;
				valido = podio[0].equals(podio[1]);
				if (!valido) {
					errores += "Se tiene el mismo atleta para el primer y el segundo puesto.\n";
					lblprimerpuesto.setForeground(Color.RED);
					lblsegundopuesto.setForeground(Color.RED);
				}
				valido = podio[0].equals(podio[2]);
				if (!valido) {
					errores += "Se tiene el mismo atleta para el primer y el tercer puesto.\n";
					lblprimerpuesto.setForeground(Color.RED);
					lbltercerpuesto.setForeground(Color.RED);
				}
				valido = podio[2].equals(podio[1]);
				if (!valido) {
					errores += "Se tiene el mismo atleta para el segundo y el tercer puesto.\n";
					lbltercerpuesto.setForeground(Color.RED);
					lblsegundopuesto.setForeground(Color.RED);
				}
				/// mirar que los tiempos son válidos (t1 <= t2 <= t3)
				valido = false;
				if (t1.compareTo(t2) <= 0)
					valido = t2.compareTo(t3) <= 0;
				if (!valido) {
					errores += "Revise los tiempos de manera que sean válidos (tiempo1 <= tiempo2 <= tiempo3).\n";
				}
				/// chckbxDefinitivo
				valido = false;
				valido = chckbxDefinitivo.isSelected();
				if (!valido) {
					errores += "Debe marcar el checkbox DEFINITIVO para cerar la prueba.\n";
				}

				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				} else
					nuevo.setPodio(podio);
				// establecemos finalmente la fecha de cierre del resultado validado
				nuevo.setDefinitivo(true);
				nuevo.setFechahora(LocalDateTime.now());

				/// Si todos los datos son correctos, llamar a ResultadoDAO, MetalDAO y
				/// PruebaDAO para insertar/modificar en la BD
				ResultadoDAO resDAO = new ResultadoDAO(ConexBD.establecerConexion());
				MetalDAO metalDAO = new MetalDAO(ConexBD.getCon());
				PruebaDAO pruebadao = new PruebaDAO(ConexBD.getCon());
				boolean correcto = false;
				long idRes = resDAO.insertarSinID(nuevo);
				if (correcto = idRes > 0) {
					nuevo.setId(idRes);
					prueba.setResultado(nuevo);
					correcto = pruebadao.modificar(prueba);
					if (correcto) {
						Oro o = nuevo.getPrimero();
						o.setAsignada(true);
						o.setFecha(LocalDate.now());
						Plata p = nuevo.getSegundo();
						p.setAsignada(true);
						p.setFecha(LocalDate.now());
						Bronce b = nuevo.getTercero();
						b.setAsignada(true);
						b.setFecha(LocalDate.now());
						correcto = metalDAO.modificar(o) && metalDAO.modificar(p) && metalDAO.modificar(b);
					}
				}
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (!correcto) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al cerrar la Prueba en la BD";
					msj = "Hubo un error y NO se ha cerrado la prueba en la BD correctamente.\nNOTA: Revise los datos.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					titulo = "Prueba " + prueba.getId() + " cerrada en la BD";
					msj = "Se ha cerrado correctamente la  prueba:\n" + prueba.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);

				}

				// Exportar a fichero de texto
				String nombreFich = "resultado_prueba_" + idprueba + ".txt";
				PruebaDAO pruebaDAO = new PruebaDAO(ConexBD.getCon());
				Prueba prueba = pruebaDAO.buscarPorID(idprueba);
				if (prueba != null) {
					File f = new File(nombreFich);
					FileWriter fw;
					try {
						String mensaje = "Resultado de la prueba " + idprueba + "\\“" + prueba.getNombre()
								+ "\\” celebrada el pasado "
								+ prueba.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " en "
								+ prueba.getLugar().getNombre();
						mensaje += "\n";
						mensaje += "Primer puesto para " + atleta1.getPersona().getNombre() + " ("
								+ atleta1.getPersona().getNifnie().mostrar() + ") " + "+, con un tiempo de "
								+ t1.toString() + ". Se le otorga el oro " + nuevo.getPrimero().getId() + " de pureza "
								+ nuevo.getPrimero().getPureza() + "%.\n";
						mensaje += "Segundo puesto para " + atleta2.getPersona().getNombre() + " ("
								+ atleta2.getPersona().getNifnie().mostrar() + ") " + "+, con un tiempo de "
								+ t2.toString() + ". Se le otorga la plata " + nuevo.getSegundo().getId()
								+ " de pureza " + nuevo.getSegundo().getPureza() + "%.\n";
						mensaje += "Tercer puesto para " + atleta3.getPersona().getNombre() + " ("
								+ atleta3.getPersona().getNifnie().mostrar() + ") " + "+, con un tiempo de "
								+ t3.toString() + ". Se le otorga el bronce " + nuevo.getTercero().getId()
								+ " de pureza " + nuevo.getTercero().getPureza() + "%.\n";
						mensaje += "Resultado " + nuevo.getId() + " cerrado a las " + nuevo.mostrarFechaHoraCierre()
								+ ".";

						fw = new FileWriter(f);
						fw.write("\n" + mensaje + "\n");
						fw.write("-----------------------------\n");
						fw.flush();
						if (fw != null)
							fw.close();

						titulo = "Resultado de la Prueba " + prueba.getId() + " exportado";
						msj = "Se ha exportado correctamente el informe del cierre de la  prueba:\n"
								+ prueba.toString();
						msj += "\nal fichero " + nombreFich + ".";
						JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception exc) {
						System.out.println(
								"Se ha producido una Exception al exportar al fichero de texto:" + exc.getMessage());
						exc.printStackTrace();
					} finally {

					}
				} else {
					titulo = "ERROR al recuprar la Prueba desde la BD";
					msj = "Hubo un error y NO se ha recuperado la información de la prueba desde la BD correctamente.\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				}
				/// Aqui se redirigiría al usuario hacia la pantalla principal
				/// TODO
			}
		});
		buttonAceptar.setBounds(230, 510, 89, 23);
		contentPane.add(buttonAceptar);

	}
}
