package org.uma.BDB2018.Driver;

public interface DBDriverInterface {
	Boolean mysqlConnect();
	String executeQuery(String query);
	float getExTime();
}
