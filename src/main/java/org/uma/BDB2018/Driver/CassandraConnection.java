package org.uma.BDB2018.Driver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.xmldb.api.base.ResourceSet;

import java.util.Arrays;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class CassandraConnection implements DBDriverInterface{

    private Cluster cluster;
    private Session session;
    private boolean moreDB = false;
    private long time;

	
	@Override
	public void connect(String db) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void executeQuery(String query) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String dbName = "chebi_basic";
		String serverIp = "127.0.0.1";
		int port = 9042;
        Builder b = Cluster.builder().addContactPoint(serverIp).withPort(port);
        cluster = b.build();
        session = cluster.connect(dbName);
        session.execute("use chebi_basic");
		long timeBefore = System.currentTimeMillis();	
		session.execute(query);
		time = System.currentTimeMillis() - timeBefore;
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return ""+time;
	}

	@Override
	public String getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMoreDB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> availableDbs() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int dbAdapt(String dbName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
