package org.uma.BDB2018.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends GenericSqlConnection{
	public MySQLConnection() throws SQLException{
		url = "jdbc:mysql://localhost:3306/chebi?user=root&password=BDB2018";
		connection = DriverManager.getConnection(url);
	}
}
