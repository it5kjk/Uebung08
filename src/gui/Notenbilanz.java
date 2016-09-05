
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

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
	
	private final String markCountLabelTxt = ". Note";
	private final String amountEnteredMarksLabelTxt = "Anzahl eingegebener Noten: ";
	private final String markAverageLabelTxt = "Notenschnitt: "; 		
	private final String bestMarkLabelTxt = "Beste Note: ";
	private final String worstMarkLabelTxt = "schlechteste Note: ";
	
	private JButton btnSaveMark;
	private JButton btnSaveMarkAmount;
	private JButton btnNew;

	private int markAmount = 0;
	private int markCount = 0;
	private TreeMap<String, Integer> marks = new TreeMap<>();
	
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
		setBounds(650, 350, 395, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Textfields
		tfMarkAmount = new JTextField();
		tfMarkAmount.setBounds(10, 30, 86, 20);
		tfMarkAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { //watch for key strokes
	            if(tfMarkAmount.getText().length() == 0) {
	                btnSaveMarkAmount.setEnabled(false);
	            } else {
	            	tfMarkAmount.setBackground(Color.white);
	                btnSaveMarkAmount.setEnabled(true);
	            }
	        }
		});
		tfMarkAmount.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent b) {
				if (b.getKeyCode() == KeyEvent.VK_ENTER) {
					saveMarkAmount();
	            	tfInputMark.grabFocus();
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
	            else {
	                btnSaveMark.setEnabled(true);
	            }
	        }
		});
		tfInputMark.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent b) {
				if (b.getKeyCode() == KeyEvent.VK_ENTER) {
					saveMark();
				}
			}
		});
		/*btnSaveMark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveMark();
			}
		}); */
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
		lblAmountEnteredMarks.setBounds(10, 130, 250, 14);
		contentPane.add(lblAmountEnteredMarks);
		
		lblMarkAverage = new JLabel("Notenschnitt: ");
		lblMarkAverage.setVisible(false);
		lblMarkAverage.setBounds(10, 155, 150, 14);
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
				saveMarkAmount();
				tfInputMark.requestFocus();
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
				saveMark();
			}
		});
		btnSaveMark.setEnabled(false);
		contentPane.add(btnSaveMark);
	}
	
	private void reset() {
		/*
		this.dispose();
		main(null); */
		
		// disable all 2nd level features and enable first level
		// resets all labels and assigns default texts
		
		btnSaveMark.setVisible(false);
		tfInputMark.setVisible(false);
		tfInputMark.setText("");
		tfInputMark.setEditable(true);
		tfInputMark.setFocusable(true);
		
		lblAmountEnteredMarks.setVisible(false);
		lblAmountEnteredMarks.setText(amountEnteredMarksLabelTxt);
		lblMarkAverage.setVisible(false);
		lblMarkAverage.setText(markAverageLabelTxt);
		lblMarkCount.setVisible(false);
		lblMarkCount.setText(markCountLabelTxt);
		lblBestMark.setVisible(false);
		lblBestMark.setText(bestMarkLabelTxt);
		lblWorstMark.setVisible(false);
		lblWorstMark.setText(worstMarkLabelTxt);
		
		tfMarkAmount.setText("");
		tfMarkAmount.setEditable(true);
		btnSaveMarkAmount.setVisible(true);
		
		// clear the hashMap and reset
		markCount = 0;
		marks.clear();
		
		tfMarkAmount.requestFocus();
	}
	private void saveMarkAmount() {
		lblAmountEnteredMarks.setText("");
		lblAmountEnteredMarks.setForeground(Color.black);
		try {
			Integer.parseInt(tfMarkAmount.getText());
			markAmount = Integer.parseInt(tfMarkAmount.getText());
			tfMarkAmount.setEditable(false);
			btnSaveMarkAmount.setVisible(false);
			String labeltext = lblMarkCount.getText(); 
			lblMarkCount.setText(markCount+ 1 + labeltext);
			lblMarkCount.setVisible(true);
			tfInputMark.setVisible(true);
			tfInputMark.setFocusable(true);
			System.out.println();
			tfInputMark.setEditable(true);
			System.out.println("editierbar? "+tfInputMark.isEditable());
			btnSaveMark.setVisible(true);
		} catch (NumberFormatException e) {
			lblAmountEnteredMarks.setForeground(Color.red);
			lblAmountEnteredMarks.setText("Bitte geben Sie eine gültige Nummer ein!");
			lblAmountEnteredMarks.setVisible(true);
			tfMarkAmount.requestFocus();
			tfMarkAmount.selectAll();
		}
	}
	
	private void saveMark(){
		tfInputMark.setBackground(Color.white);
		String[] allowedInputSet = {"1","2","3","4","5","6"};
		System.out.println(Arrays.asList(allowedInputSet).contains(tfInputMark.getText())); 
		if (!tfInputMark.getText().isEmpty() && 
    			Arrays.asList(allowedInputSet).contains(tfInputMark.getText())
    		) 
    	{
			if (markCount < markAmount) {
		        marks.put("mark" + markCount, Integer.parseInt(tfInputMark.getText()));
		        markCount++;
		        System.out.println(markCount);
		        tfInputMark.setText("");
		        lblMarkCount.setText(""); // clear the label
		        if (markCount == markAmount) {
		        	lblMarkCount.setText(Integer.toString(markCount)+ markCountLabelTxt);
		        } else {
		        lblMarkCount.setText(Integer.toString(markCount +1)+ markCountLabelTxt);
		        }
		        
		        tfInputMark.requestFocus();
		        
		        if (marks.size() == markAmount) {
		        	tfInputMark.setText("");
		        	//tfInputMark.setFocusable(false);
					tfInputMark.setEditable(false);
					btnSaveMark.setEnabled(false);
					btnNew.setVisible(true);
					printStatistic();
					btnNew.requestFocus();
				}
			}
    	} else {
    		tfInputMark.setBackground(Color.red);
    		tfInputMark.requestFocus();
    		tfInputMark.selectAll();
		}	
	}
	
	private void printStatistic() {
		//compute values for statistic from the treemap
		
		Entry<String, Integer> bestMark = null;
		for (Entry<String, Integer> entry : marks.entrySet()) {
		    if (bestMark == null || bestMark.getValue() > entry.getValue()) {
		        bestMark = entry;
		    }
		}
		
		Entry<String, Integer> worstMark = null;
		for (Entry<String, Integer> entry : marks.entrySet()) {
		    if (worstMark == null || worstMark.getValue() < entry.getValue()) {
		        worstMark = entry;
		    }
		}
		
		int summe = 0;
		for (Entry<String, Integer> entry : marks.entrySet()) {
			if (entry != null) {
				summe = summe + entry.getValue();
			}
		}
		
		Double average =  ((double) summe / (double) markAmount);
		double average_rounded = average + 0.005;
		
		//write labels
		lblAmountEnteredMarks.setText(amountEnteredMarksLabelTxt.concat(Integer.toString(markAmount)));
		lblBestMark.setText(lblBestMark.getText().concat(bestMark.getValue().toString()));
		lblMarkAverage.setText(lblMarkAverage.getText().concat(f.format(average_rounded)));
		lblWorstMark.setText(lblWorstMark.getText().concat(worstMark.getValue().toString()));
		
		lblAmountEnteredMarks.setVisible(true);
		lblMarkAverage.setVisible(true);
		lblBestMark.setVisible(true);
		lblWorstMark.setVisible(true);
	}
}