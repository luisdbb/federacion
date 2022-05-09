package gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

///Examen 11 Ejercicio 1-EVAL3
public class PanelInsercionTiempo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6573019921331671704L;

	/**
	 * Create the panel.
	 */
	public PanelInsercionTiempo() {
		setFont(new Font("Wide Latin", Font.BOLD, 11));
		setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Introduzca el Tiempo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Introduzca el Tiempo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabelHoras = new JLabel("Horas:");
		add(lblNewLabelHoras);
		
		JSpinner spinnerHoras = new JSpinner();
		lblNewLabelHoras.setLabelFor(spinnerHoras);
		spinnerHoras.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(spinnerHoras);
		
		JLabel lblNewLabelMinutos = new JLabel("Minutos:");
		add(lblNewLabelMinutos);
		
		JSpinner spinnerMinutos = new JSpinner();
		lblNewLabelMinutos.setLabelFor(spinnerMinutos);
		spinnerMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		add(spinnerMinutos);
		
		JLabel lblNewLabelSegundos = new JLabel("Segundos:");
		add(lblNewLabelSegundos);
		
		JSpinner spinnerSegundos = new JSpinner();
		lblNewLabelSegundos.setLabelFor(spinnerSegundos);
		spinnerSegundos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		add(spinnerSegundos);
		
		JLabel lblNewLabelCentesimas = new JLabel("Centésimas:");
		add(lblNewLabelCentesimas);
		
		JSpinner spinnerCentesimas = new JSpinner();
		lblNewLabelCentesimas.setLabelFor(spinnerCentesimas);
		spinnerCentesimas.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		add(spinnerCentesimas);

	}

}
