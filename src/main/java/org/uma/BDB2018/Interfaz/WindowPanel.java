package org.uma.BDB2018.Interfaz;
import java . awt . event . ActionListener ;
import java.util.List;

import javax.swing.JComboBox;

public interface WindowPanel{
	public static String EXECUTE = "Execute query" ;
	public void controller(ActionListener ctr) ;
	public void changeText(String s) ;
	public JComboBox getComboBox();
	public List<String> getHistorial();
	public void writeHistorial(String s);
}

