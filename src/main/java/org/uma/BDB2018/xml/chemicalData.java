package org.uma.BDB2018.xml;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class chemicalData extends xml {
    HashMap<String,String> table_id;
    public chemicalData() throws SQLException, IOException {
        super();
        table_id = new HashMap<>();
        table_id.put("name","chemical data");
        pw.write(startLabel("table", table_id));
        System.out.print(startLabel("table", table_id));
    }

    @Override
    public void addValues(ResultSet result) throws SQLException, IOException {
        String type;
        String value;
        //int num = result.getMetaData().getColumnCount();
        HashMap<String,String> idHash = new HashMap<>();

        while (result.next()) {
            if(id.equals(result.getString("compound_id"))) {
                type = result.getString("type").replaceAll(" ","_");
                value = result.getString("chemical_data");
                pw.write(startLabel(type));
                System.out.print(startLabel(type));
                pw.write(value);
                System.out.print(value);
                pw.write(endLabel(type));
                System.out.print(endLabel(type));
            }else{
                if(idInit){
                    pw.write(endLabel("id"));
                }else{
                    idInit = true;
                }
                id = result.getString("compound_id");
                idHash.put("Value",id);
                pw.write(startLabel("id", idHash));
                System.out.print(startLabel("id", idHash));
            }
        }
    }

    public void xmlEnd() throws IOException {
        pw.write(endLabel("id"));
        System.out.print(endLabel("id"));
        pw.write(endLabel("table"));
        System.out.print(endLabel("table"));
        pw.close();
    }
}
