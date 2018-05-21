package org.uma.BDB2018.Driver;
import java.sql.SQLException;

public class PostgreSqlConnection extends GenericSqlConnection{
	
	public PostgreSqlConnection() throws SQLException {
		url = "jdbc:postgresql://0.0.0.0:5432";
		props.setProperty("user","postgres");
		databases.add("chebi_basic");
		databases.add("chebi_indexed");
	}
	
	public int dbAdapt(String dbName) {
		switch(dbName){
		case "chebi_basic": return 0;
		//case "chebi_indexed": return 1;
		default: return -1;
		}
	}
}
