package org.uma.BDB2018.Interfaz;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

public abstract class NorthPanel extends JPanel{

	protected JComboBox comboBox;
	private JButton button;
	
	public NorthPanel(String status, List<String> hist){
		
		comboBox = new JComboBox();
		fillCombo(hist);
		comboBox.setEditable(true);
		this.add(comboBox);
		button = new JButton("Execute query");
		
		connected(status);
		this.add(button);
		

		
	}
	
	//si esta conectada deja hacer consultas
	private void connected(String s){
		if(s=="CONNECTED")
			button.setEnabled(true);
		else 
			button.setEnabled(false);
	}
	
	public JComboBox getComboBox(){
		return comboBox;
	}
	
	public JButton getButton(){
		return button;
	}

	public void fillCombo(List<String> hist){
		for(String h : hist){
			comboBox.addItem(h);
		}
	}
	
	 public abstract void notify(String s);
}
