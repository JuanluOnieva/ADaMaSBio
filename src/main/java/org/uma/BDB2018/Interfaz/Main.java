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

import org.uma.BDB2018.Driver.*;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<String> historial;
	private List<ViewMainPanel> panel;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Main() throws SQLException {
		
		if(arch.leer()==null)
			historial = new LinkedList<String>();
		else
			historial = arch.leer();
		
		panel = new LinkedList<ViewMainPanel>();
		
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
		//MySQLConnection mysql = new MySQLConnection();
		
		ViewMainPanel mainPanel = new ViewMainPanel("MySQL", "CONNECTED", historial, mariadb);
		panel.add(mainPanel);
		ViewMainPanel mainPanel2 = new ViewMainPanel("MariaDB", "CONNECTED", historial, mariadb);
		panel.add(mainPanel2);
		
		tabbedPane.addTab("Mysql DB", null, mainPanel, null);
		
		tabbedPane.addTab("MariaDB", null, mainPanel2, null);
		

		ActionListener bt = new Control(mainPanel, panel, arch);
		ActionListener bt2 = new Control(mainPanel2, panel, arch);
		mainPanel.controller(bt);
		mainPanel2.controller(bt2);
		this.pack();
	}

}
