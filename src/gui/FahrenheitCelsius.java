package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FahrenheitCelsius extends JFrame {

	private JPanel contentPane;
	private JTextField tfFahrenheit;
	private JTextField tfCelsius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FahrenheitCelsius frame = new FahrenheitCelsius();
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
	public FahrenheitCelsius() {
		setTitle("Umrechnung Fahrenheit in Celsius");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFahrenheit = new JLabel("Grad Fahrenheit");
		lblFahrenheit.setBounds( 10, 20, 200, 17);
		contentPane.add(lblFahrenheit);
		
		this.tfFahrenheit = new JTextField();
		tfFahrenheit.addKeyListener(new Umrechner() {
		});
		tfFahrenheit.setBounds(10 , 40, 145, 20);
		contentPane.add(tfFahrenheit);
		
		JLabel lblCelsius = new JLabel("Grad Celsius");
		lblCelsius.setBounds(10, 70, 200, 17);
		contentPane.add(lblCelsius);
		
		this.tfCelsius = new JTextField();
		tfCelsius.setBounds(10, 90, 145, 18);
		tfCelsius.setEditable(false);
		contentPane.add(tfCelsius);
		
		JButton btUmrechnen = new JButton("Umrechnen");
		btUmrechnen.addActionListener(new Umrechner());
		btUmrechnen.setBounds(230, 37, 100, 20);
		contentPane.add(btUmrechnen);
		
		JButton btEnde = new JButton("Ende");
		btEnde.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btEnde.setBounds(230, 90, 100, 20);
		contentPane.add(btEnde);
	}
	
	public String getFahrenheit() {
		return tfFahrenheit.getText();
	}
	
	private class Umrechner implements ActionListener, KeyListener {
		private void umrechnen() {
			double celsius;
			double fahrenheit;
			DecimalFormat f = new DecimalFormat("#0.00");
			String x = getFahrenheit();
			fahrenheit = Double.parseDouble(x);
			celsius = (fahrenheit - 32) * 5 / 9;
			tfCelsius.setText(f.format(celsius));
			tfFahrenheit.requestFocus();
			tfFahrenheit.selectAll();
			
		}
		

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				umrechnen();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			umrechnen();
			
		}
	}
}

