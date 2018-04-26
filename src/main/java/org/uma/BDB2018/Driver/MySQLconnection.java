package org.uma.BDB2018.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLconnection extends DummySqlConnection {

	private Connection conn;
	private int num;
	
	public MySQLconnection() throws SQLException{
        super(null, null, null);
		//create connection for a server installed in localhost, with a user "root" with no password
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chebi?user=root&password=BDB2018");
        num = 0;
	}	

	public String executionTime(String s){

		long timeAfter = 0;
		// create a Statement
		try (Statement stmt = conn.createStatement()){	
			//execute query
			num = 0;
			long timeBefore = System.currentTimeMillis();
			try (ResultSet rs = stmt.executeQuery(s)){ 
				timeAfter = System.currentTimeMillis() - timeBefore;
				rs.last();
				num = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return ""+timeAfter;
	}
	
	public String executionNumber(){
		return ""+num;
	}

}
