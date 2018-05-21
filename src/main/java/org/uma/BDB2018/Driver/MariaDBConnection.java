package org.uma.BDB2018.Driver;

import java.sql.SQLException;

public class MariaDBConnection extends GenericSqlConnection{
	public MariaDBConnection() throws SQLException{
		url = "jdbc:mariadb://localhost:3306";
		props.setProperty("user","root");
		props.setProperty("password","BDB2018");
		databases.add("chebi_indexed");
		databases.add("chebi_indexedd");
	}

	@Override
	public int dbAdapt(String dbName) {
		switch(dbName){
		//case "chebi": return 0;
		case "chebi_indexed": return 1;
		case "chebi_storage": return 2;
		case "chebi_optimize": return 3;
		default: return -1;
		}
	}
}
