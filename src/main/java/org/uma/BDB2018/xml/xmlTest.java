package org.uma.BDB2018.xml;

import org.uma.BDB2018.Driver.GenericSqlConnection;
import org.uma.BDB2018.Driver.PostgreSqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class xmlTest {
    xml xmlTest;
    Statement stmt;
    GenericSqlConnection con;
    private Connection connection;
    public xmlTest(){
        try {
            con = new PostgreSqlConnection();
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //con.
        //xmlTest = new chemicalData();
    }
}
