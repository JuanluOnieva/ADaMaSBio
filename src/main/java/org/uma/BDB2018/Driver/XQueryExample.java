package org.uma.BDB2018.Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import org.exist.util.serializer.SAXSerializer;
import org.exist.util.serializer.SerializerPool;
import org.exist.xmldb.EXistXQueryService;
import org.exist.xmldb.XmldbURI;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

/**
 *  Reads an XQuery file and executes it. To run this example enter: 
 * 
 *  bin/run.sh org.exist.examples.xmldb.XQueryExample <xqueryfile>
 *  
 *  in the root directory of the distribution.
 *
 *@author     Wolfgang Meier <meier@ifs.tu-darmstadt.de>
 *created    20. September 2002
 */
public class XQueryExample {

    protected static final String URI = "xmldb:exist://localhost:9080/exist/xmlrpc";

    protected static final String driver = "org.exist.xmldb.DatabaseImpl";

    /**
     * Read the xquery file and return as string.
     */
    
    public static void main( String args[] ) {
        try {
            System.out.println("aqui");
            Class<?> cl = Class.forName( driver );
            Database database = (Database) cl.newInstance();
            database.setProperty( "create-database", "true" );
            DatabaseManager.registerDatabase( database );
            
            String query = "for $x in doc(\"db/tablasXML/chemical_data.xml\")/table/id where data($x/@Value)=\"15377\" return $x";
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
            ResourceSet result = service.execute( compiled );

            long qtime = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            
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
            System.out.println("retrieve time: " + rtime);
            System.out.println("aqui");

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    protected static void usage() {
        System.out.println( "usage: examples.xmldb.XQueryExample xquery-file" );
        System.exit( 0 );
    }
}
