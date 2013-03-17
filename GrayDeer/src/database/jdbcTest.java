package database;


/* 
 * This file is for test purposes.
 * Please ignore.
 */

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//public class jdbcTest{
//	Connection connection = null;
//	String driverName ="oracle.jdbc.driver.OracleDriver"; // for Oracle
//	// String driverName = “com.mysql.jdbc.Driver”; //for MySql
//	String serverName = "ginger.umd.edu"; // Use this server.
//	String portNumber = "1521";
//	String sid = "dbclass1";
//	String url="jdbc:oracle:thin:@"+serverName+":"+ portNumber+":"+sid; // for Oracle
//	//uri =”jdbc:mysql://server ip or address:port/database name”; //for Mysql
//	String username = "erensezener"; // You should modify this.
//	String password = "sifre123"; // You should modify this.
//	public jdbcTest() {}
//	public boolean doConnection(){
//		try {
//			// Load the JDBC driver
//			Class.forName(driverName);
//			// Create a connection to the database
//			connection = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException e) {
//			// Could not find the database driver
//			System.out.println("ClassNotFoundException : "+e.getMessage());
//			return false;
//		} catch (SQLException e) {
//			// Could not connect to the database
//			System.out.println(e.getMessage());
//			return false;
//		}
//		return true;
//	}
//	public static void main(String arg[]){
//		jdbcTest con =new jdbcTest();
//		System.out.println("Connection : " +con.doConnection());
//	}
//}

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcTest {
  public static void main(String[] argv) throws Exception {
    String driverName = "org.gjt.mm.mysql.Driver";
    Class.forName(driverName);

    String serverName = "localhost";
    String mydatabase = "mydatabase";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 

    String username = "username";
    String password = "password";
    Connection connection = DriverManager.getConnection(url, username, password);
  }
}