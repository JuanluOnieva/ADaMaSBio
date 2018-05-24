package org.uma.BDB2018.Interfaz;
import java . awt . event . ActionListener ;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.uma.BDB2018.Driver.DBDriverInterface;

public abstract class WindowPanel extends JPanel{
	public static String EXECUTE = "Execute query" ;
	public abstract void controller(ActionListener ctr) ;
	public abstract void changeText(String s) ;
	public abstract  JComboBox getComboBox();
	public abstract List<String> getHistorial();
	public abstract void writeHistorial(String s);
	public abstract void notify(String s);
    public abstract CenterPanel getCenterP(int db);
	public abstract DBDriverInterface getConn();
}

