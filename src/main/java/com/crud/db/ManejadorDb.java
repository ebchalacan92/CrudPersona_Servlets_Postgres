package com.crud.db;

import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

@ApplicationScoped
public class ManejadorDb {
	@Produces @ApplicationScoped
	public DataSource db() {
		
		 
        BasicDataSource ds = new BasicDataSource();

 

        URI dbUri;
        
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            
            ds.setDriverClassName( "org.postgresql.Driver" );
            ds.setUrl( dbUrl );
            ds.setUsername( username );
            ds.setPassword( password );
        } 
        catch (URISyntaxException e) {
            e.printStackTrace();
            
            throw new RuntimeException( "no s epuede conectar a la base de datos" );
        }

 


//        ds.setDriverClassName( "org.postgresql.Driver" );
//        ds.setUrl( "jdbc:postgresql://127.0.0.1:5432/distribuida" );
//        ds.setUsername( "postgres" );
//        ds.setPassword( "postgres" );
        
        return ds;
    }

}
