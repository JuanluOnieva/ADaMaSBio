package org.uma.BDB2018.Driver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlConnection extends GenericSqlConnection {
	
	public PostgreSqlConnection() throws SQLException{
		connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:5432/chebi_basic?user=postgres");
		time = 0;
        rowsNumber = 0;
	}
	
	public void executeQuery(String s){

		// create a Statement
		try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){	
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
