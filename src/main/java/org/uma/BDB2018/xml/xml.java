package org.uma.BDB2018.xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.uma.BDB2018.Interfaz.archivo;

public class xml {

	private ResultSet result;
	private String id;
	private archivo arch = new archivo("prueba.txt");
	
	public xml (ResultSet result) throws SQLException {
		this.result = result;
		createXML();
	}
	
	public void createXML() throws SQLException {
		int cnt = 1;
		String label = null;
		String key = null;
		int num = result.getMetaData().getColumnCount();
		while (result.next()) {
			List<String> rowsXML = new LinkedList<>();
			while (cnt <= num) {
				label = result.getMetaData().getColumnLabel(cnt);
				key = result.getString(cnt);
				if(result.wasNull())
					key = "NULL";
				rowsXML.add(startLabel(label) + key + endLabel(label));
				cnt++;
			}
			System.out.println(rowsXML.toString());
			//arch.escribir(rowsXML);
			rowsXML.clear();
			cnt = 1;
		}
	}
	
	public String startLabel(String id) {
		return "<" + id + ">";
	}
	
	public String endLabel(String id) {
		return "</" + id + ">";
	}

}
