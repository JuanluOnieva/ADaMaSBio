package org.uma.BDB2018.xml;

import org.uma.BDB2018.Driver.GenericSqlConnection;
import org.uma.BDB2018.Driver.PostgreSqlConnection;

import java.sql.SQLException;

public class xmlTest {
    xml xmlTest;
    GenericSqlConnection con;
    public xmlTest(){
        try {
            con = new PostgreSqlConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //con.
        //xmlTest = new chemicalData();
    }
}
