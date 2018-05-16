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

import javax.xml.stream.events.Attribute;

public abstract class xml {

	ResultSet result;
	String idprev;
	FileWriter pw;
	String id;
	
	public xml (ResultSet result) throws SQLException, IOException {
		this.result = result;
		idprev = "nada";
    	File fichero = new File("prueba.txt");
       	pw = new FileWriter(fichero, true);
	}
	
public abstract void createXML() throws SQLException, IOException;
	
	String startLabel(String id) {
		return "<" + id + ">\n";
	}

	String startLabel(String id, HashMap<String,String> Attributes) {
		//Creates a label with attributes
		StringBuilder tag = new StringBuilder("<" + id);
		for (String key : Attributes.keySet()){
			tag.append(' ').append(key).append("=\"").append(Attributes.get(key)).append('\"');
		}
		tag.append(">\n");
		return tag.toString();
	}

	String endLabel(String id) {
		return "\n</" + id + ">\n";
	}
	
	void escribir(List<String> list) throws IOException {
		for(String l : list)
			pw.write(l+"\n");
		
	}

}
