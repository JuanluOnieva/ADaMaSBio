package org.uma.BDB2018.Driver;
import java.sql.*;

import org.mariadb.jdbc.Driver;

public class MariaDBConnection extends GenericSqlConnection{
	
	
	public MariaDBConnection() throws SQLException{
		//create connection for a server installed in localhost, with a user "root" with no password
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3316?user=root&password=BDB2018");
        time = 0;
        rowsNumber = 0;
	}	

	public void executeQuery(String s){

		// create a Statement
		try (Statement stmt = connection.createStatement()){	
			//execute query
			time = 0;
			rowsNumber = 0;
			long timeBefore = System.currentTimeMillis();
			try (ResultSet rs = stmt.executeQuery(s)){ 
				time = System.currentTimeMillis() - timeBefore;
				rs.last();
				rowsNumber = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getTime() {
		return ""+time;
	}

	@Override
	public String getRows() {
		return ""+rowsNumber;
	}
	
}
