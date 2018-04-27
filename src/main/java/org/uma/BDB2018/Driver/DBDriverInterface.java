package org.uma.BDB2018.Driver;

import java.sql.SQLException;

public interface DBDriverInterface {
	public void executeQuery(String query) throws SQLException;
	public String getTime();
	public String getRows();
}
