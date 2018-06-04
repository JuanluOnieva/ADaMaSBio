package org.uma.BDB2018.Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import org.uma.BDB2018.Interfaz.*;

import org.uma.BDB2018.Driver.*;
import org.xmldb.api.base.XMLDBException;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<String> historial;
	private List<WindowPanel> panel;
	private archivo arch = new archivo("historial.txt");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws XMLDBException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Main() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		
		if(arch.leer()==null)
			historial = new LinkedList<String>();
		else
			historial = arch.leer();
		
		panel = new LinkedList<WindowPanel>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 420);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpt = new JMenu("File");
		menuBar.add(mnOpt);
		
		JMenu mnAbout = new JMenu("About");
		mnOpt.add(mnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		MariaDBConnection mariadb = new MariaDBConnection();
		PostgreSqlConnection psql = new PostgreSqlConnection();
		MySQLConnection mysql = new MySQLConnection();
		XQueryDriver existDB = new XQueryDriver();
		CassandraConnection cassandra = new CassandraConnection();
		MongoDBConnection mongodb = new MongoDBConnection();
		
		ViewMainPanelSQL mainPanel = new ViewMainPanelSQL("MySQL", "CONNECTED", historial, mysql);
		panel.add(mainPanel);
		
		ViewMainPanelSQL mainPanel2 = new ViewMainPanelSQL("MariaDB", "CONNECTED", historial, mariadb);
		panel.add(mainPanel2);
		
		ViewMainPanelSQL mainPanel3 = new ViewMainPanelSQL("PostgrSQL", "CONNECTED", historial, psql);
		panel.add(mainPanel3);
		
		ViewMainPanelXQuery mainPanel4 = new ViewMainPanelXQuery("ExistDB", "CONNECTED", historial, existDB);
		panel.add(mainPanel4);
		
		ViewMainPanelNoSQL mainPanel5 = new ViewMainPanelNoSQL("Cassandra", "CONNECTED", historial, cassandra);
		panel.add(mainPanel5);
		
		ViewMainPanelNoSQL mainPanel6 = new ViewMainPanelNoSQL("mongodb", "CONNECTED", historial, mongodb);
		panel.add(mainPanel6);
		
		tabbedPane.addTab("MySQL", null, mainPanel, null);
		tabbedPane.addTab("MariaDB", null, mainPanel2, null);
		tabbedPane.addTab("PostgreSQL", null, mainPanel3, null);
		tabbedPane.addTab("ExistDB", null, mainPanel4, null);
		tabbedPane.addTab("Cassandra", null, mainPanel5, null);
		tabbedPane.addTab("MongoDB", null, mainPanel6, null);


		ActionListener bt = new Control(mainPanel, panel, arch);
		ActionListener bt2 = new Control(mainPanel2, panel, arch);
		ActionListener bt3 = new Control(mainPanel3, panel, arch);
		ActionListener bt4 = new Control(mainPanel4, panel, arch);
		ActionListener bt5 = new Control(mainPanel5, panel, arch);
		ActionListener bt6 = new Control(mainPanel6, panel, arch);
		mainPanel.controller(bt);
		mainPanel2.controller(bt2);
		mainPanel3.controller(bt3);
		mainPanel4.controller(bt4);
		mainPanel5.controller(bt5);
		mainPanel6.controller(bt6);
		this.pack();
	}

}
