package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.activation.ActivationDataFlavor;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Einkauf extends JFrame {

	private JPanel contentPane;
	private JTextField tfStueckzahl;
	private JTextField tfPreis;
	private JTextField tfRabatt;
	private JLabel lblOutput;
	private DecimalFormat f = new DecimalFormat("#0.00");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Einkauf frame = new Einkauf();
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
	public Einkauf() {
		setTitle("Einkauf");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfStueckzahl = new JTextField();
		tfStueckzahl.setBounds(10, 32, 86, 20);
		tfStueckzahl.setText("");
		tfStueckzahl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPreis.grabFocus();
				}
			}
		});
		contentPane.add(tfStueckzahl);
		tfStueckzahl.setColumns(10);
		
		tfPreis = new JTextField();
		tfPreis.setBounds(120, 32, 86, 20);
		tfPreis.setText("");
		tfPreis.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfRabatt.grabFocus();
				}
			}
		});
		contentPane.add(tfPreis);
		tfPreis.setColumns(10);
		
		tfRabatt = new JTextField();
		tfRabatt.setBounds(226, 32, 86, 20);
		tfRabatt.setText("");
		tfRabatt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					berechnen();
				}
			}
		});
		contentPane.add(tfRabatt);
		tfRabatt.setColumns(10);
		
		JButton btnBerechnen = new JButton("Berechnen");
		btnBerechnen.setBounds(335, 31, 89, 23);
		btnBerechnen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				berechnen();
			}
		});
		contentPane.add(btnBerechnen);
		
		JLabel lblStueckzahl = new JLabel("St\u00FCckzahl");
		lblStueckzahl.setBounds(10, 15, 46, 14);
		contentPane.add(lblStueckzahl);
		
		JLabel lblStckpreis = new JLabel("St\u00FCckpreis");
		lblStckpreis.setBounds(120, 15, 66, 14);
		contentPane.add(lblStckpreis);
		
		JLabel lblRabatt = new JLabel("Rabatt");
		lblRabatt.setBounds(226, 15, 46, 14);
		contentPane.add(lblRabatt);
		
		this.lblOutput = new JLabel("");
		lblOutput.setBounds(10, 73, 300, 14);
		contentPane.add(lblOutput);
	}
	public void berechnen(){
		if (tfStueckzahl.getText().equals("") || tfPreis.getText().equals("") || tfRabatt.getText().equals("")) {
			lblOutput.setForeground(Color.red);
			lblOutput.setText("Die Eingaben sind unvollständig!");
		} else {
			int stueckzahl = Integer.parseInt(tfStueckzahl.getText());
			// usability for german users: allow decimals to be entered with ,
			double d_stckpreis = Double.parseDouble(this.toDecimal(tfPreis.getText()));
			double rabatt = Double.parseDouble(this.toDecimal(tfRabatt.getText()));
		  	double ergebnis = (stueckzahl * d_stckpreis) * (1 -(rabatt/100));
			
			lblOutput.setForeground(Color.black);
			lblOutput.setText(f.format(ergebnis));
			tfStueckzahl.requestFocus();
			tfStueckzahl.selectAll();
		}
	}
	private String toDecimal(String input){
		StringBuilder sbDecimal = new StringBuilder();
		String vDecimal="";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ',') {
				sbDecimal.append(".");
			} else {
				sbDecimal.append(input.charAt(i));
			}
		}
		vDecimal = sbDecimal.toString();
		return vDecimal;
	}
}

