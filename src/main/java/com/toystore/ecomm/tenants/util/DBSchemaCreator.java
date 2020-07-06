package com.toystore.ecomm.tenants.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSchemaCreator {
	private Connection con = null;
	
	private String driverName;
	
	private String dbUrl;
	
	private String dbUsername;
	
	private String dbPassword;
	
	private DBSchemaCreator(String driverName, String dbUrl, String dbUsername, String dbPassword) {
		this.driverName = driverName;
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}
	
	public static void createDbSchemaTable(String driverName, String dbUrl, String dbUsername, String dbPassword, String newDbName) throws Exception {
		DBSchemaCreator creator = new DBSchemaCreator(driverName, dbUrl, dbUsername, dbPassword);
		creator.createSchema(newDbName);
		creator.createTables(newDbName);
	}
	
	public static void dropDbSchemaTable(String driverName, String dbUrl, String dbUsername, String dbPassword, String existingDbName) throws Exception {
		DBSchemaCreator creator = new DBSchemaCreator(driverName, dbUrl, dbUsername, dbPassword);
		creator.dropSchema(existingDbName);
	}
	
	private Connection openConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	     if (con != null)
	    	 closeConnection();
	         
	     Class.forName(driverName).newInstance();
	     con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	     
	     return con;
	}

	private void closeConnection() {
          if (con != null) {
             try {
                con.close();
             } catch (SQLException e) {
                System.err.println(e.getMessage());
             }
          }
	}
	
	private void createSchema(String dbName) throws Exception {
	      openConnection().createStatement().executeUpdate("CREATE DATABASE " + dbName);
	      
	      closeConnection();
	}
	
	private void createTables(String dbName) throws Exception {
	      Connection c = openConnection();
	      
	      c.createStatement().executeUpdate("USE " + dbName);
	      c.createStatement().executeUpdate("CREATE TABLE USER (USER_ID int(10) NOT NULL, FIRST_NAME varchar(45) DEFAULT NULL, LAST_NAME varchar(45) DEFAULT NULL, MIDDLE_NAME varchar(45) DEFAULT NULL, EMAIL_ID varchar(45) DEFAULT NULL, PRIMARY_CONTACT varchar(10) DEFAULT NULL, PRIMARY KEY (USER_ID))");
	      c.createStatement().executeUpdate("CREATE TABLE ADDRESS (ADDRESS_ID int(11) NOT NULL AUTO_INCREMENT, ADDRESS_TYPE char(1) DEFAULT NULL, USER_ID int(11) DEFAULT NULL, LINE1 varchar(45) DEFAULT NULL, LINE2 varchar(45) DEFAULT NULL, LINE3 varchar(45) DEFAULT NULL, DISTRICT varchar(45) DEFAULT NULL, STATE varchar(45) DEFAULT NULL, COUNTRY varchar(45) DEFAULT NULL, ZIP_CODE varchar(10) DEFAULT NULL, PRIMARY KEY (ADDRESS_ID), UNIQUE KEY ADDRESS_ID_UNIQUE (ADDRESS_ID), KEY USER_ID_idx (USER_ID), CONSTRAINT USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION)");
	      
	      closeConnection();
	}
	
	private void dropSchema(String dbName) throws Exception {
		Connection c = openConnection();
	      
	    c.createStatement().executeUpdate("DROP SCHEMA " + dbName);
	    
	    closeConnection();
	}

}
