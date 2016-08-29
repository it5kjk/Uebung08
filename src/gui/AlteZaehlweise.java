package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AlteZaehlweise extends JFrame {

	private JPanel contentPane;
	private JTextField tfStueckzahl;
	private JLabel lblValueGros;
	private JLabel lblValueSchock;
	private JLabel lblValueDutzend;
	private JLabel lblValueStueck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Throwable e) {
					e.printStackTrace();
				}
				try {
					AlteZaehlweise frame = new AlteZaehlweise();
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
	public AlteZaehlweise() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 315, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfStueckzahl = new JTextField();
		tfStueckzahl.setBounds(10, 32, 86, 20);
		tfStueckzahl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//TODO add Keylistener
					System.out.println(130%60);
				}
			}
		});
		contentPane.add(tfStueckzahl);
		tfStueckzahl.setColumns(10);
		
		JLabel lblStueckzahl = new JLabel("St\u00FCckzahl");
		lblStueckzahl.setBounds(10, 15, 46, 14);
		contentPane.add(lblStueckzahl);
		
		JLabel lblGros = new JLabel("Gros");
		lblGros.setBounds(20, 85, 35, 14);
		contentPane.add(lblGros);
		
		JLabel lblSchock = new JLabel("Schock");
		lblSchock.setBounds(75, 85, 35, 14);
		contentPane.add(lblSchock);
		
		JLabel lblDutzend = new JLabel("Dutzend");
		lblDutzend.setBounds(145, 85, 40, 14);
		contentPane.add(lblDutzend);
		
		JLabel lblStck = new JLabel("St\u00FCck");
		lblStck.setBounds(230, 85, 46, 14);
		contentPane.add(lblStck);
		
		JButton btnUmrechnen = new JButton("Umrechnen");
		btnUmrechnen.setBounds(150, 31, 89, 23);
		btnUmrechnen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				umrechnen();
			}
		});
		contentPane.add(btnUmrechnen);
		
		this.lblValueGros = new JLabel("");
		lblValueGros.setBounds(10, 85, 46, 14);
		contentPane.add(lblValueGros);
		
		this.lblValueSchock = new JLabel("");
		lblValueSchock.setBounds(65, 85, 21, 14);
		contentPane.add(lblValueSchock);
		
		this.lblValueDutzend = new JLabel("");
		lblValueDutzend.setBounds(135, 85, 15, 14);
		contentPane.add(lblValueDutzend);
		
		this.lblValueStueck = new JLabel("");
		lblValueStueck.setBounds(215, 85, 15, 14);
		contentPane.add(lblValueStueck);
	}
	
	private void umrechnen() {
		int valGros = 0;
		int valSchock = 0;
		int valDutzend = 0;
		int valStueck = 0;
		int inputStueck = Integer.parseInt(tfStueckzahl.getText());
		
		if (inputStueck > 144) {
			valGros = inputStueck/144;
			int r = inputStueck%144;
			for (int i = 0; i < r; i++) {
				valStueck++;
				if (valStueck > 11) {
					valDutzend++;
					valStueck = 0;
				}
				if (valDutzend > 4) {
					valSchock++;
					valDutzend = 0;
				}
			}
		} else {
			for (int i = 0; i < inputStueck; i++) {
				valStueck++;
				if (valStueck > 11) {
					valDutzend++;
					valStueck = 0;
				}
				if (valDutzend > 4) {
					valSchock++;
					valDutzend = 0;
				}
			}
		}
		lblValueGros.setText(Integer.toString(valGros));
		lblValueSchock.setText(Integer.toString(valSchock));
		lblValueDutzend.setText(Integer.toString(valDutzend));
		lblValueStueck.setText(Integer.toString(valStueck));
	}
}
