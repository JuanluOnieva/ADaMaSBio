package org.uma.BDB2018.xml;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class chemicalData extends xml {
    HashMap<String,String> table_id;
    public chemicalData(ResultSet result) throws SQLException, IOException {
        super(result);
        table_id = new HashMap<>();
        table_id.put("name","chemical data");
    }

    @Override
    public void createXML() throws SQLException, IOException {
        String type;
        String value;
        int num = result.getMetaData().getColumnCount();
        pw.write("TEST");
        pw.write(startLabel("table", table_id));
        System.out.print(startLabel("table", table_id));
        result.next();
        id = result.getString("compound_id");
        HashMap<String,String> idHash = new HashMap<>();
        idHash.put("Value",id);
        System.out.print(startLabel("table", idHash));
        while (result.next()) {
            type = result.getString("type");
            value = result.getString("chemical_data");
            pw.write(startLabel(type));
            System.out.print(startLabel(type));
            pw.write(value);
            System.out.print(value);
            pw.write(endLabel(type));
            System.out.print(endLabel(type));
        }
        pw.write(endLabel(id));
        System.out.print(endLabel(id));
        pw.close();
    }
}
