package org.uma.BDB2018.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
		//createXML1();
		createXML2();
		//createXML3();

	}
	
	public void createXML1() throws SQLException, IOException {
		int cnt = 2;
		String label = null;
		String key = null;
		int num = result.getMetaData().getColumnCount();
		HashMap<String, String> entrada = new HashMap<>();
		entrada.put("name", "compounds");
		pw.write(startLabel("table",entrada));
		entrada.clear();
		while (result.next()) {
			id = result.getString("id");
			if(!result.wasNull() && !idprev.equals("nada") && !idprev.equals(id))
				pw.write(endLabel("id") + "\n");
			if(!result.wasNull() && !id.equals(idprev)) {
				entrada.put("value", id);
				pw.write(startLabel("id", entrada));
				entrada.clear();
			}
			List<String> rowsXML = new LinkedList<>();
			while (cnt <= num) {
				label = result.getMetaData().getColumnLabel(cnt);
				key = result.getString(cnt);
				if(!result.wasNull())
					key  = key.replaceAll("<->", "-");
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
	
	
	//select compound_id, type, name from names where (type like 'SYNONYM' or type like 'IUPAC NAME') 
	//AND compound_id=15377;
	public void createXML2() throws SQLException, IOException {
		int cnt = 2;
		String label = null;
		String key = null;
		int num = result.getMetaData().getColumnCount();
		HashMap<String, String> entrada = new HashMap<>();
		entrada.put("name", "names");
		pw.write(startLabel("table", entrada));
		entrada.clear();
		while (result.next()) {
			id = result.getString("compound_id");
			if(!result.wasNull() && !idprev.equals("nada") && !idprev.equals(id))
				pw.write(endLabel("id") + "\n");
			if(!result.wasNull() && !id.equals(idprev)) {
				entrada.put("value", id);
				pw.write(startLabel("id", entrada));
				entrada.clear();
			}
			List<String> rowsXML = new LinkedList<>();
			while (cnt <= num) {
				label = result.getMetaData().getColumnLabel(cnt);
				key = result.getString(cnt);
				if(!result.wasNull()) {
					key = key.replaceAll(">", "-");
					key = key.replaceAll("<", "-");
				}
				if(result.wasNull())
					key = "NULL";
				else if(id.equals(idprev)) {
					key = result.getString("name");
					if(!result.wasNull()) {
						key = key.replaceAll(">", "-");
						key = key.replaceAll("<", "-");
					}
					label = result.getString("type").replaceAll(" ", "_");
					rowsXML.add(startLabel(label) + key + endLabel(label));
					break;
				}
				if(label.equals("type")) {
					cnt++;
					label = key.replaceAll(" ", "_");
					key = result.getString(cnt);
					key = key.replaceAll("<", "-");
					key = key.replaceAll(">", "-");
				}
				rowsXML.add(startLabel(label) + key + endLabel(label));
				cnt++;
			}
			escribir(rowsXML);
			rowsXML.clear();
			cnt = 2;
			idprev = result.getString("compound_id");
		}
		pw.write(endLabel("id") + "\n");
		pw.write(endLabel("table") + "\n");
		
		if (null != pw)
			pw.close();
	}
	
	public void createXML3() throws SQLException, IOException {
		int cnt = 2;
		String label = null;
		String key = null;
		int num = result.getMetaData().getColumnCount();
		HashMap<String, String> entrada = new HashMap<>();
		entrada.put("name", "database_registry");
		pw.write(startLabel("table", entrada));
		entrada.clear();
		while (result.next()) {
			id = result.getString("compound_id");
			if(!result.wasNull() && !idprev.equals("nada") && !idprev.equals(id))
				pw.write(endLabel("id") + "\n");
			if(!result.wasNull() && !id.equals(idprev)) {
				entrada.put("value", id);
				pw.write(startLabel("id", entrada));
				entrada.clear();
			}
			List<String> rowsXML = new LinkedList<>();
			while (cnt <= num) {
				label = result.getMetaData().getColumnLabel(cnt);
				key = result.getString(cnt);
				if(result.wasNull())
					key = "NULL";
				else if(id.equals(idprev)) {
					entrada.clear();
					key = result.getString("accession_number");
					label = result.getString("type");
					entrada.put("source", label.replace(" ", "_"));
					rowsXML.add(startLabel("registry_number", entrada) + key + endLabel("registry_number"));
					entrada.clear();
					break;
				}
				if(label.equals("type")) {
					cnt++;
					entrada.clear();
					key = result.getString("accession_number");
					label = result.getString("type");
					entrada.put("source", label.replace(" ", "_"));
					rowsXML.add(startLabel("registry_number", entrada) + key + endLabel("registry_number"));
					entrada.clear();
				}
				//rowsXML.add(startLabel(label) + key + endLabel(label));
				cnt++;
			}
			escribir(rowsXML);
			rowsXML.clear();
			cnt = 2;
			idprev = result.getString("compound_id");
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
	
	public String startLabel(String id, HashMap<String,String> Attributes) {
		//Creates a label with attributes
		StringBuilder tag = new StringBuilder("<" + id);
		for (String key : Attributes.keySet()){
			tag.append(' ').append(key).append("=\"").append(Attributes.get(key)).append('\"');
		}
		tag.append(">\n");
		return tag.toString();
	}
}
