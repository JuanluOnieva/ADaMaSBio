package org.uma.BDB2018.Driver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.xmldb.api.base.ResourceSet;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class MongoDBConnection implements DBDriverInterface{

    protected static final String URI = "mongodb://localhost:27017";
    private long time;
    private boolean moreDB = false;
    private  MongoDatabase database;
	
	@Override
	public void connect(String db) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void executeQuery(String query) throws SQLException, IOException {
		String dbName = "chebi_basic";
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		String[] querySet = query.split(",");
		database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(querySet[0]);
		BasicDBObject queryObject = null;
		if(querySet.length==3)
			queryObject = new BasicDBObject(querySet[1], querySet[2]);			
		else if(querySet.length==4)
			queryObject = new BasicDBObject(querySet[1], new BasicDBObject(querySet[2], querySet[3]));			
		long timeBefore = System.currentTimeMillis();
		FindIterable<Document> cursor = collection.find(queryObject);
		time = System.currentTimeMillis() - timeBefore;
		System.out.println(cursor.first().toJson());
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
