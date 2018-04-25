package org.uma.BDB2018.Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import javax.swing.GroupLayout.Alignment;

import org.uma.BDB2018.Driver.DummySqlConnection;

import javax.swing.JComboBox;

public class ViewMainPanel extends JPanel implements WindowPanel{

	private JLabel title;
	private JLabel state;
	private NorthPanel npanel;
	private GroupLayout g1_NorthPanel;
	private CenterPanel cpanel;
	public List<String> historial;
	private DummySqlConnection dbCon;
	
	public ViewMainPanel(String SGDB, String status, List<String> hist, DummySqlConnection sqlCon){
		dbCon = sqlCon;
		historial = hist;
		setLayout(new GridLayout(4, 1, 0, 0));
		title = new JLabel(SGDB+" query executer");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title);
		npanel = new NorthPanel(status, hist);
		
		g1_NorthPanel = new GroupLayout(npanel);
		g1_NorthPanel.setHorizontalGroup(
			g1_NorthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, g1_NorthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(npanel.getComboBox(), 0, 358, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(npanel.getButton())
					.addContainerGap())
		);
		g1_NorthPanel.setVerticalGroup(
			g1_NorthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(g1_NorthPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(g1_NorthPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(npanel.getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(npanel.getButton()))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		npanel.setLayout(g1_NorthPanel);
		cpanel = new CenterPanel();
		this.add(npanel);
		this.add(cpanel);
		state = new JLabel("Service status: " + status);
		state.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(state);
	}



	public void controller(ActionListener ctr) {
		// TODO Auto-generated method stub
		npanel.getButton().addActionListener(ctr);
		npanel.getButton().setActionCommand(EXECUTE);
	}


	public void changeText(String s) {
		// TODO Auto-generated method stub
		getComboBox().addItem(s);
		getComboBox().setSelectedItem(s);
	}
	
	public void showResult(String result){
		cpanel.showResult(result);
	}


	public JComboBox getComboBox() {
		// TODO Auto-generated method stub
		return npanel.getComboBox();
	}


	
	public List<String> getHistorial() {
		// TODO Auto-generated method stub
		return historial;
	}


	public void writeHistorial(String s) {
		// TODO Auto-generated method stub
		historial.add(s);
	}
	
	public void notify(String s){
		npanel.notify(s);
	}
	
	public CenterPanel getCenterP() {
		return cpanel;
	}
	
	public DummySqlConnection getConn() {
		return dbCon;
	}
	
	
}
