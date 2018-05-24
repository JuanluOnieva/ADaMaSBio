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

public class NorthPanelSQL extends NorthPanel{
	
	public NorthPanelSQL(String status, List<String> hist){
		super(status, hist);
		comboBox.setSelectedItem("SELECT * FROM exampleTable;");	
	}
	
	public void notify(String s){
		comboBox.addItem(s);
		comboBox.setSelectedItem("SELECT * FROM exampleTable;");
	}
}
