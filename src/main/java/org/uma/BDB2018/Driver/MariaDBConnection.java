package org.uma.BDB2018.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection extends GenericSqlConnection{
	public MariaDBConnection() throws SQLException{
		url = "jdbc:mariadb://localhost:3316?user=root&password=BDB2018";
		connection = DriverManager.getConnection(url);
	}
}
