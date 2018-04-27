package org.uma.BDB2018.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends GenericSqlConnection{
	public MySQLConnection() throws SQLException{
		url = "jdbc:mysql://localhost:3306/chebi?user=root&password=BDB2018";
		connection = DriverManager.getConnection(url);
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
