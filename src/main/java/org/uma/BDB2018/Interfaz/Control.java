package org.uma.BDB2018.Interfaz;

import java . awt . event.ActionEvent;
import java . awt . event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Control implements ActionListener {

	WindowPanel win ;
	List<WindowPanel> panel;
	archivo arch;
	String[] databases = {"chebi_basic", "ChebiIndex", "ChebiStorage", "ChebiOptimize"};
	
	public Control(WindowPanel w, List<WindowPanel> p, archivo a) {
		win = w ;
		panel =p;
		arch = a;
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand( );
		String s = (String) win.getComboBox().getSelectedItem();
		if (command == "Execute query"){
			if(s!=null && !win.getHistorial().contains(s)){
				win.changeText(s);
				win.writeHistorial(s);
				arch.escribir(win.getHistorial());
				try {
					win.getConn().executeQuery(win.getComboBox().toString());
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(WindowPanel v : panel)
					if(v!=win)
						v.notify(s);
			}
			if(win.getConn().hasMoreDB()) {
			for (String db : win.getConn().availableDbs()){
				try {
					win.getConn().connect(db);
					win.getConn().executeQuery(s);
					win.getCenterP(win.getConn().dbAdapt(db)).setSQLTime(win.getConn().getTime()+" ms");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getErrorCode());
					System.out.println(e1.getSQLState());
					System.out.println(e1.getCause());
					win.getCenterP(win.getConn().dbAdapt(db)).setErrorTime("DB not found");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//win.showResult(win.getConn().getRows(), db);
			}
			}else {
				try {
					win.getConn().executeQuery(s);
					win.getCenterP(0).setSQLTime(win.getConn().getTime()+" ms");
					win.getCenterP(1).setSQLTime(win.getConn().getRows());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getErrorCode());
					System.out.println(e1.getSQLState());
					System.out.println(e1.getCause());
					win.getCenterP(0).setErrorTime("DB not found");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}
