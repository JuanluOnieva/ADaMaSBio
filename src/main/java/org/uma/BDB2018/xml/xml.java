package org.uma.BDB2018.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.uma.BDB2018.Interfaz.archivo;

public class xml {

	private ResultSet result;
	private String idprev;
	private FileWriter pw;
	private String id;
	
	public xml (ResultSet result) throws SQLException, IOException {
		this.result = result;
		idprev = "nada";
    	File fichero = new File("prueba.txt");
       	pw = new FileWriter(fichero, true);
		createXML();
	}
	
	public void createXML() throws SQLException, IOException {
		int cnt = 2;
		String label = null;
		String key = null;
		int num = result.getMetaData().getColumnCount();
		pw.write(startLabel("table") + "\n");
		while (result.next()) {
			id = result.getString("id");
			if(!result.wasNull() && !idprev.equals("nada") && !idprev.equals(id))
				pw.write(endLabel("id") + "\n");
			if(!result.wasNull() && !id.equals(idprev))
				pw.write(startLabel("id") + id + "\n");
			List<String> rowsXML = new LinkedList<>();
			while (cnt <= num) {
				label = result.getMetaData().getColumnLabel(cnt);
				key = result.getString(cnt);
				if(result.wasNull())
					key = "NULL";
				else if(id.equals(idprev)) {
					key = result.getString("secondary_id");
					rowsXML.add(startLabel("secondary_id") + key + endLabel("secondary_id"));
					break;
				}
				rowsXML.add(startLabel(label) + key + endLabel(label));
				cnt++;
			}
			System.out.println(rowsXML.toString());
			escribir(rowsXML);
			rowsXML.clear();
			cnt = 2;
			idprev = result.getString("id");
		}
		pw.write(endLabel("id") + "\n");
		pw.write(endLabel("table") + "\n");
		
		if (null != pw)
			pw.close();
	}
	
	public String startLabel(String id) {
		return "<" + id + ">";
	}
	
	public String endLabel(String id) {
		return "</" + id + ">";
	}
	
	public void escribir(List<String> list) throws IOException {
		for(String l : list)
			pw.write(l+"\n");
		
	}

}
