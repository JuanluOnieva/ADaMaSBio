package org.uma.BDB2018.Driver;

import org.exist.xmldb.EXistXQueryService;
import org.exist.xmldb.XmldbURI;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.OutputKeys;

/**
 *  Reads an XQuery file and executes it. To run this example enter: 
 * 
 *  bin/run.sh org.exist.examples.xmldb.XQueryDriver <xqueryfile>
 *  
 *  in the root directory of the distribution.
 *
 *@author     Wolfgang Meier <meier@ifs.tu-darmstadt.de>
 *created    20. September 2002
 */
public class XQueryDriver implements DBDriverInterface{

    protected static final String URI = "xmldb:exist://localhost:9080/exist/xmlrpc";
    protected static final String driver = "org.exist.xmldb.DatabaseImpl";
    private long qtime;
    private boolean moreDB = false;
    private  ResourceSet result;
    /**
     * Read the xquery file and return as string.
     */
    
    private void doQuery(String query) {
        /**
         *
         * Query example:
         * String query = "for $x in doc(\"/db/chebi/chemical_data.xml\")/table/id return $x";
         */
        try {
            Class<?> cl = Class.forName( driver );
            Database database = (Database) cl.newInstance();
            database.setProperty( "create-database", "true" );
            DatabaseManager.registerDatabase( database );
            

            System.out.println(query);
            // get root-collection
            Collection col =
                DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION, "admin", "");
            // get query-service
            EXistXQueryService service =
                (EXistXQueryService) col.getService( "XQueryService", "1.0" );
            
            // set pretty-printing on
            service.setProperty( OutputKeys.INDENT, "yes" );
            service.setProperty( OutputKeys.ENCODING, "UTF-8" );
            
            CompiledExpression compiled = service.compile( query );
            
            long start = System.currentTimeMillis();
            
            // execute query and get results in ResourceSet
            result = service.execute( compiled );

            qtime = System.currentTimeMillis() - start;

            /*start = System.currentTimeMillis();
            
            Properties outputProperties = new Properties();
            outputProperties.setProperty(OutputKeys.INDENT, "yes");
            SAXSerializer serializer = (SAXSerializer) SerializerPool.getInstance().borrowObject(SAXSerializer.class);
            serializer.setOutput(new OutputStreamWriter(System.out), outputProperties);
            
            for ( int i = 0; i < (int) result.getSize(); i++ ) {
                XMLResource resource = (XMLResource) result.getResource( (long) i ); 
                resource.getContentAsSAX(serializer);
            }
            
            SerializerPool.getInstance().returnObject(serializer);
            long rtime = System.currentTimeMillis() - start;
			System.out.println("hits:          " + result.getSize());
            System.out.println("query time:    " + qtime);
            System.out.println("retrieve time: " + rtime);*/


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeQuery(String query) {
        doQuery(query);
    }

    @Override
    public String getTime() {
    	return String.valueOf(qtime);
    }

    @Override
    public String getRows() {
        String rows = "0";
        try {
  			rows = String.valueOf(result.getSize());
  		} catch (XMLDBException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

        return rows;
    }

	@Override
	public boolean hasMoreDB() {
		// TODO Auto-generated method stub
		return this.moreDB;
	}

	@Override
	public List<String> availableDbs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connect(String db) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dbAdapt(String dbName) {
		// TODO Auto-generated method stub
		return 0;
	}
}
