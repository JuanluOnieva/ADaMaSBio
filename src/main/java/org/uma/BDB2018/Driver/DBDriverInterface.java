package org.uma.BDB2018.Driver;

import java.io.IOException;
import java.sql.SQLException;

public interface DBDriverInterface {
	public void executeQuery(String query) throws SQLException, IOException;
	public String getTime();
	public String getRows();
}
