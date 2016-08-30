package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Notenbilanz extends JFrame {

	private JPanel contentPane;
	private DecimalFormat f = new DecimalFormat("#0.00");
	
	private JTextField tfMarkAmount;
	private JTextField tfInputMark;
	
	private JLabel lblAmountMarks;
	private JLabel lblMarkCount;
	private JLabel lblAmountEnteredMarks;
	private JLabel lblMarkAverage;
	private JLabel lblBestMark;
	private JLabel lblWorstMark;
	private String markCountLabelTxt;
	
	private JButton btnSaveMark;
	private JButton btnSaveMarkAmount;
	private JButton btnNew;

	private int markAmount = 0;
	private int markCount = 1;
	private Map<String, Integer> marks = new HashMap<>();
	
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
					Notenbilanz frame = new Notenbilanz();
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
	public Notenbilanz() {
		setTitle("Notenbilanz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 350, 395, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Textfields
		tfMarkAmount = new JTextField();
		tfMarkAmount.setBounds(10, 30, 86, 20);
		tfMarkAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { //watch for key strokes
	            if(tfMarkAmount.getText().length() == 0)
	                btnSaveMarkAmount.setEnabled(false);
	            else
	            {
	                btnSaveMarkAmount.setEnabled(true);
	            }
	        }
		});
		contentPane.add(tfMarkAmount);
		tfMarkAmount.setColumns(10);
		
		tfInputMark = new JTextField();
		tfInputMark.setVisible(false);
		tfInputMark.setBounds(10, 90, 86, 20);
		tfInputMark.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { //watch for key strokes
	            if(tfInputMark.getText().length() == 0)
	                btnSaveMark.setEnabled(false);
	            else
	            {
	                btnSaveMark.setEnabled(true);
	            }
	        }
		});
		contentPane.add(tfInputMark);
		tfInputMark.setColumns(10);
		
		//Labels
		lblAmountMarks = new JLabel("Anzahl Noten");
		lblAmountMarks.setBounds(10, 15, 87, 13);
		contentPane.add(lblAmountMarks);
				
		lblMarkCount = new JLabel(". Note");
		lblMarkCount.setVisible(false);
		lblMarkCount.setBounds(10, 75, 46, 15);
		contentPane.add(lblMarkCount);
		
		lblAmountEnteredMarks = new JLabel("Anzahl eingegebene Noten: ");
		lblAmountEnteredMarks.setVisible(false);
		lblAmountEnteredMarks.setBounds(10, 130, 150, 14);
		contentPane.add(lblAmountEnteredMarks);
		
		lblMarkAverage = new JLabel("Notenschnitt: ");
		lblMarkAverage.setVisible(false);
		lblMarkAverage.setBounds(10, 155, 75, 14);
		contentPane.add(lblMarkAverage);
		
		lblBestMark = new JLabel("Beste Note: ");
		lblBestMark.setVisible(false);
		lblBestMark.setBounds(10, 180, 75, 14);
		contentPane.add(lblBestMark);
		
		lblWorstMark = new JLabel("schlechteste Note: ");
		lblWorstMark.setVisible(false);
		lblWorstMark.setBounds(10, 205, 100, 14);
		contentPane.add(lblWorstMark);
		
		//Buttons

		btnSaveMarkAmount = new JButton("\u00DCbernehmen");
		btnSaveMarkAmount.setBounds(118, 29, 107, 23);
		btnSaveMarkAmount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add "save mark amount" functionality
				markAmount = Integer.parseInt(tfMarkAmount.getText());
				tfMarkAmount.setEditable(false);
				btnSaveMarkAmount.setVisible(false);
				String labeltext = lblMarkCount.getText(); 
				lblMarkCount.setText(markCount + labeltext);
				lblMarkCount.setVisible(true);
				tfInputMark.setVisible(true);
				btnSaveMark.setVisible(true);
				
			}
		});
		btnSaveMarkAmount.setEnabled(false);
		contentPane.add(btnSaveMarkAmount);	
		
		
		btnNew = new JButton("Neue Berechnung");
		btnNew.setVisible(false);
		btnNew.setBounds(240, 29, 127, 23);
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				
			}
		});
		contentPane.add(btnNew);
		
		
		btnSaveMark = new JButton("\u00DCbernehmen");
		btnSaveMark.setVisible(false);
		btnSaveMark.setBounds(118, 89, 107, 23);
		btnSaveMark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO add "save mark" functionality
				
				
				if (markCount < markAmount) {
				        marks.put("mark" + markAmount, Integer.parseInt(tfInputMark.getText()));
				} else {
					// TODO: output statistic
				}
				markCount++;
			}
		});
		btnSaveMark.setEnabled(false);
		contentPane.add(btnSaveMark);
	}
	
	private void reset() {
		tfMarkAmount.setText("");
		tfMarkAmount.setEditable(true);
		btnSaveMarkAmount.setVisible(true);
		
		//TODO define global Strings for initiale label texts
		lblMarkCount.setText(markCountLabelTxt);
		lblBestMark.setText(bestMarkLabelTxt);
		lblWorstMark.setText(worstMarkLabelTxt);
		
		marks.clear();
	}
	
}
