package org.uma.BDB2018.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlConnection extends GenericSqlConnection{
	
	public PostgreSqlConnection() throws SQLException {
		url = "jdbc:postgresql://0.0.0.0:5432/chebi_basic?user=postgres";
		connection = DriverManager.getConnection(url);
	}


	
}
