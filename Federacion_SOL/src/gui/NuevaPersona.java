package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class NuevaPersona extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtNIFNIE;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JRadioButton rbNIE = new JRadioButton("NIE");
	private JRadioButton rbNIF = new JRadioButton("NIF");
	private JSpinner spinnerFechaNac = new JSpinner();

	/**
	 * Create the panel.
	 */
	public NuevaPersona() {
		setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel lblNombre = new JLabel("Nombre *:");
		lblNombre.setBounds(10, 25, 81, 14);
		add(lblNombre);

		setTxtNombre(new JTextField());
		getTxtNombre().setBounds(74, 22, 354, 20);
		add(getTxtNombre());
		getTxtNombre().setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(10, 146, 65, 14);
		add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(74, 143, 115, 20);
		add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblFechaNac = new JLabel("Fecha Nacimiento *:");
		lblFechaNac.setBounds(209, 146, 106, 14);
		add(lblFechaNac);

		spinnerFechaNac.setModel(new SpinnerDateModel(new Date(-315622800000L), null, null, Calendar.DAY_OF_YEAR));
		spinnerFechaNac.setBounds(313, 143, 115, 20);
		add(spinnerFechaNac);

		rbNIE.setBounds(137, 46, 49, 23);
		add(rbNIE);
		buttonGroup.add(rbNIE);

		rbNIF.setBounds(137, 73, 49, 23);
		add(rbNIF);
		buttonGroup.add(rbNIF);

		setTxtNIFNIE(new JTextField());
		getTxtNIFNIE().setColumns(10);

		JLabel lblNifnie = new JLabel("<html>Seleccione la opción e<br>introduzca el valor</html>");
		lblNifnie.setBounds(20, 50, 111, 43);
		add(lblNifnie);

	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtNIFNIE() {
		return txtNIFNIE;
	}

	public void setTxtNIFNIE(JTextField txtNIFNIE) {
		this.txtNIFNIE = txtNIFNIE;
		txtNIFNIE.setBounds(193, 53, 103, 20);
		add(txtNIFNIE);
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JRadioButton getRbNIE() {
		return rbNIE;
	}

	public void setRbNIE(JRadioButton rbNIE) {
		this.rbNIE = rbNIE;
	}

	public JRadioButton getRbNIF() {
		return rbNIF;
	}

	public void setRbNIF(JRadioButton rbNIF) {
		this.rbNIF = rbNIF;
	}

	public JSpinner getSpinnerFechaNac() {
		return spinnerFechaNac;
	}

	public void setSpinnerFechaNac(JSpinner spinnerFechaNac) {
		this.spinnerFechaNac = spinnerFechaNac;
	}

}
