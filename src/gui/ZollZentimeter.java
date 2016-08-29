package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TexturePaint;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.awt.event.ActionEvent;

public class ZollZentimeter extends JFrame {

	private JPanel contentPane;
	private JTextField tfInputUnit;
	private JLabel lblInputUnit;
	private JLabel lblOutputUnit;
	private String operatingMode = "zollZuZentimeter";
	private DecimalFormat f = new DecimalFormat("#0.00");	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					ZollZentimeter frame = new ZollZentimeter();
					frame.setVisible(true);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ZollZentimeter() {
		setTitle("Zoll zu Zentimeter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600,300,350,200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.tfInputUnit = new JTextField();
		tfInputUnit.setToolTipText("Geben Sie einen Wert in Zoll ein");
		tfInputUnit.setBounds(20 , 50, 100, 20);
		tfInputUnit.setText("");
		tfInputUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					 if (tfInputUnit.getText().equals("") || tfInputUnit.getText().equals("Fehler: kein Wert")) {
							tfInputUnit.setForeground(Color.red);
							tfInputUnit.setText("Fehler: kein Wert");
						} else {
							
							if (operatingMode.equals("zollZuZentimeter")) {
								umrechnenZollZentimeter();
							}
							if (operatingMode.equals("zentimeterZuZoll")) {
								umrechnenZentimeterZoll();
						}
					}
			 	} else if (tfInputUnit.getText().equals("")) {
			 		tfInputUnit.setForeground(Color.black);
				}
			}
		});
		contentPane.add(tfInputUnit);
		
		this.lblInputUnit = new JLabel("Zoll");
		lblInputUnit.setBounds(20, 30, 55, 14);
		contentPane.add(lblInputUnit);
		
		this.lblOutputUnit = new JLabel("");
		lblOutputUnit.setBounds(206, 53, 89, 14);
		contentPane.add(lblOutputUnit);
		
		JButton btnUmrechnen = new JButton("Umrechnen");
		btnUmrechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfInputUnit.getText().equals("") || tfInputUnit.getText().equals("Fehler: kein Wert")) {
					tfInputUnit.setForeground(Color.red);
					tfInputUnit.setText("Fehler: kein Wert");
				} else {
					
					if (operatingMode.equals("zollZuZentimeter")) {
						umrechnenZollZentimeter();
					}
					if (operatingMode.equals("zentimeterZuZoll")) {
						umrechnenZentimeterZoll();
					}
				}
			}
		});
		btnUmrechnen.setBounds(20, 92, 100, 23);
		contentPane.add(btnUmrechnen);
		
		JButton btnSwitch = new JButton("Tauschen");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if tfInoutUnit is empty no calculation is supposed to be done
				if ( operatingMode.equals("zollZuZentimeter") & (tfInputUnit.getText().equals("")) ) {
					operatingMode = "zentimeterZuZoll";
					setTitle("Zentimeter zu Zoll");
					lblInputUnit.setText("Zentimeter");
				} else if ( operatingMode.equals("zentimeterZuZoll") & (tfInputUnit.getText().equals("")) ) {
					operatingMode = "zollZuZentimeter";
					setTitle("Zoll zu Zentimeter");
					lblInputUnit.setText("Zoll");
				}
				
				if (tfInputUnit.getText().equals("Fehler: kein Wert")) {
					tfInputUnit.setText("");
					lblOutputUnit.setText("");
					tfInputUnit.requestFocus();
					
					if (operatingMode.equals("zollZuZentimeter") ) {
						operatingMode = "zentimeterZuZoll";
						setTitle("Zentimeter zu Zoll");
						lblInputUnit.setText("Zentimeter");
					} else {
						operatingMode = "zollZuZentimeter";
						setTitle("Zoll zu Zentimeter");
						lblInputUnit.setText("Zoll");
					}
					
				}
				
				if (operatingMode.equals("zollZuZentimeter") & !tfInputUnit.getText().equals("")) {
					operatingMode = "zentimeterZuZoll";
					setTitle("Zentimeter zu Zoll");
					lblInputUnit.setText("Zentimeter");
					umrechnenZentimeterZoll();
				} else if (operatingMode.equals("zentimeterZuZoll") & !tfInputUnit.getText().equals("")) {
					operatingMode = "zollZuZentimeter";
					setTitle("Zoll zu Zentimeter");
					lblInputUnit.setText("Zoll");
					umrechnenZollZentimeter();
				}
			}
		});
		btnSwitch.setBounds(206, 92, 89, 23);
		contentPane.add(btnSwitch);
	}

	public String getOperatingMode() {
		return operatingMode;
	}
	
	protected void umrechnenZollZentimeter() {
		double input = Double.parseDouble(tfInputUnit.getText());
		double output = input * 2.54;
		lblOutputUnit.setText(f.format(output).concat(" cm"));
		tfInputUnit.requestFocus();
		tfInputUnit.selectAll();
	}
	
	protected void umrechnenZentimeterZoll() {
		double input = Double.parseDouble(tfInputUnit.getText());
		double output = input / 2.54;
		lblOutputUnit.setText(f.format(output).concat(" Zoll"));
		tfInputUnit.requestFocus();
		tfInputUnit.selectAll();
	}
	
}
