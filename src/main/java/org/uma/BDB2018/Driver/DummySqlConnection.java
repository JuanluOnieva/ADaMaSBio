package org.uma.BDB2018.Driver;

import java.sql.Connection;
import java.util.Random;

public abstract class DummySqlConnection implements DBDriverInterface {
	
	protected Connection connection;
	protected long time;
	protected int rowsNumber;
	
	public abstract void executeQuery(String query);
	public abstract String getTime();
	public abstract String getRows();
}
