package org.uma.BDB2018.Driver;

import java.util.Random;

public class DummySqlConnection implements DBDriverInterface {

	public DummySqlConnection(String url, String user, String password) {
		mysqlConnect();
	}
	
	public Boolean mysqlConnect() {
		return true;
	}

	public String executeQuery(String query) {
		return "Test result";
	}

	public float getExTime() {
		Random n = new Random();
		return n.nextFloat();
	}

	public String executionTime(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public String executionNumber() {
		// TODO Auto-generated method stub
		return null;
	}
}
