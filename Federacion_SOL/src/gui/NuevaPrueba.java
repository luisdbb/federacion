package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class NuevaPrueba extends JFrame {

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
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(39, 30, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(102, 27, 285, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setBounds(39, 63, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1651788000000L), new Date(1651788000000L), null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(112, 58, 61, 20);
		contentPane.add(spinner);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo de Prueba:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 128, 210, 52);
		contentPane.add(panel);
		
		JRadioButton rbIndividual = new JRadioButton("Individual");
		buttonGroupTipo.add(rbIndividual);
		panel.add(rbIndividual);
		
		JRadioButton rbEquipos = new JRadioButton("Por Equipos");
		buttonGroupTipo.add(rbEquipos);
		panel.add(rbEquipos);
		
		JLabel lblNewLabel_2 = new JLabel("Lugar:");
		lblNewLabel_2.setBounds(39, 103, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(102, 99, 169, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Patrocinador:");
		lblNewLabel_3.setBounds(39, 187, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(113, 183, 274, 22);
		contentPane.add(comboBox_1);
		
		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.setBounds(193, 227, 89, 23);
		contentPane.add(buttonAceptar);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(298, 227, 89, 23);
		contentPane.add(buttonCancelar);
	}
}
