package org.uma.BDB2018.Driver;

import java.sql.SQLException;

public class MySQLConnection extends GenericSqlConnection{
	public MySQLConnection() {
		url = "jdbc:mysql://localhost:3306";
		props.setProperty("user","root");
		props.setProperty("password","BDB2018");
		databases.add("chebi_basic");
		databases.add("chebi_indexed");
	}

	@Override
	public int dbAdapt(String dbName) {
		switch(dbName){
		case "Chebi": return 0;
		case "ChebiIndex": return 1;
		case "ChebiStorage": return 2;
		case "ChebiOptimize": return 3;
		default: return -1;
		}
	}
}
