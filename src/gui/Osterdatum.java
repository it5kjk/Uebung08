package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Osterdatum extends JFrame {

	private JPanel contentPane;
	private JTextField tfInputJahr;
	private JLabel lblOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					Osterdatum frame = new Osterdatum();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Osterdatum() {
		setTitle("Osterdatum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 350, 325, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfInputJahr = new JTextField();
		tfInputJahr.setBounds(10, 25, 86, 20);
		tfInputJahr.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					calculate();
				}
			}
		});
		contentPane.add(tfInputJahr);
		tfInputJahr.setColumns(10);
		
		JButton btnCalcDate = new JButton("Datum berechnen");
		btnCalcDate.setBounds(141, 24, 124, 23);
		btnCalcDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		contentPane.add(btnCalcDate);
		
		JLabel lblJahr = new JLabel("Jahr");
		lblJahr.setBounds(10, 11, 46, 14);
		contentPane.add(lblJahr);
		
		JLabel lblDatum = new JLabel("Datum f\u00FCr Ostersonntag:");
		lblDatum.setBounds(10, 56, 134, 14);
		contentPane.add(lblDatum);
		
		lblOutput = new JLabel("");
		lblOutput.setBounds(10, 82, 200, 14);
		contentPane.add(lblOutput);
	}
	
	private void printErr(String message) {
		lblOutput.setForeground(Color.red);
		lblOutput.setText(message);
		tfInputJahr.grabFocus();
		tfInputJahr.selectAll();
	}

	private void calculate() {
		
		if (tfInputJahr.getText().isEmpty() == true) {
			this.printErr("Es wurde keine Jahreszahl eingegeben!");
		} else {
			int y=0;
			if (y <= 1583) {
				this.printErr("Berechnung nur für Jahreszahl > 1583");
			} else {
				y = Integer.parseInt(tfInputJahr.getText());
				int g = y % 19;
			    int c = y / 100;
			    int h = (c - c / 4 - (8 * c + 13) / 25 + 19 * g + 15) % 30;
			    int i = h - (h / 28) * (1 - (29 / (h + 1)) * ((21 - g) / 11));
			    int j = (y + y / 4 + i + 2 - c + c / 4) % 7;
			    int l = i - j;
			    int m = 3 + (l + 40) / 44;
			    int d = l + 28 - 31 * (m / 4);
			    lblOutput.setForeground(Color.black);
			    lblOutput.setText(d + "." + m + "." + y);
			    tfInputJahr.grabFocus();
				tfInputJahr.selectAll();
			}
		}
	}
}
