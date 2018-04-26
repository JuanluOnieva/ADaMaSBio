package org.uma.BDB2018.Interfaz;

import java.awt.GridLayout;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class CenterPanel extends JPanel{
	
	private String name;
	
	private JTextPane txtpnQueryResult;
	private JTextPane txtpnQueryResult2;

	private JPanel centerRightPanel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblQueryStats;
	private JLabel lblExecutionTime;
	private JTextField txts;
	//private Time time;
	
	public CenterPanel(String db){
		name = db;
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		//txtpnQueryResult = new JTextPane();
		//txtpnQueryResult.setEditable(false);
		//txtpnQueryResult.setText("Query result");
		//this.add(txtpnQueryResult);

		//txtpnQueryResult2 = new JTextPane();
		//txtpnQueryResult2.setEditable(false);
		//txtpnQueryResult2.setText("por aqui");
		//this.add(txtpnQueryResult2);
		
		centerRightPanel = new JPanel();
		this.add(centerRightPanel);
		centerRightPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_1 = new JPanel();
		centerRightPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		//lblQueryStats = new JLabel("Query stats");
		//lblQueryStats.setHorizontalAlignment(SwingConstants.CENTER);
		//panel_1.add(lblQueryStats);
		
		panel_2 = new JPanel();
		centerRightPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblExecutionTime = new JLabel(name);
		lblExecutionTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblExecutionTime);
		
		txts = new JTextField();
		txts.setEditable(false);
		txts.setHorizontalAlignment(SwingConstants.CENTER);
		//txts.setText(""+time.getSegundos());
		panel_2.add(txts);
		txts.setColumns(10);
		
	}
	
	/*
	public void showResult(String result){
		txtpnQueryResult.setText(result);
	}
	*/
	
	public void setSQLTime(String t) {
		txts.setText(""+t+" ms");
	}
	
	public String getName(){
		return name;
	}


}
