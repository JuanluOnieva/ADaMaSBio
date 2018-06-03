package org.uma.BDB2018.xml;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class databaseAccesionXML extends xml {

    public databaseAccesionXML() throws SQLException, IOException {
        super();
    }

    public void addValues(ResultSet result) throws SQLException, IOException {
        int cnt = 2;
        String label = null;
        String key = null;
        int num = result.getMetaData().getColumnCount();
        pw.write(startLabel("table") + "\n");
        while (result.next()) {
            id = result.getString("id");
            if (!result.wasNull() && !idprev.equals("nada") && !idprev.equals(id))
                pw.write(endLabel("id") + "\n");
            if (!result.wasNull() && !id.equals(idprev))
                pw.write(startLabel("id") + id + "\n");
            List<String> rowsXML = new LinkedList<>();
            while (cnt <= num) {
                label = result.getMetaData().getColumnLabel(cnt);
                key = result.getString(cnt);
                if (result.wasNull())
                    key = "NULL";
                else if (id.equals(idprev)) {
                    key = result.getString("secondary_id");
                    rowsXML.add(startLabel("secondary_id") + key + endLabel("secondary_id"));
                    break;
                }
                rowsXML.add(startLabel(label) + key + endLabel(label));
                cnt++;
            }
            System.out.println(rowsXML.toString());
            escribir(rowsXML);
            rowsXML.clear();
            cnt = 2;
            idprev = result.getString("id");
        }
        pw.write(endLabel("id") + "\n");
        pw.write(endLabel("table") + "\n");

        if (null != pw)
            pw.close();
    }
}