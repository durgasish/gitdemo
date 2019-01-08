package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Base {

	public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String destination = "jdbc:sqlserver://localhost:1433;" +  
			"databaseName=master;user=sa;password=open1234#;";
	public static Properties prop; 
	public static Connection con;
	public static Connection con1;
	public static Statement stmt;
	public static Statement stmt1;
	public static ResultSet rs;
	public static ResultSet rs1;



	public Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("\\Users\\manis\\Desktop\\Framework\\PageObjectModel-master\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sourcedb() throws SQLException, ClassNotFoundException {


		Class.forName(driver);
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
				"databaseName=master;user=sa;password=open1234#;";
		con = DriverManager.getConnection(connectionUrl);
		System.out.println("Connected to Source DB");
		String connectionurl = prop.getProperty("SourceDatabase");
		System.out.println(connectionurl);
	}

	public void destinationdb() throws SQLException, ClassNotFoundException {


		Class.forName(driver);
		con1 = DriverManager.getConnection(destination);
		System.out.println("Connected to Destination DB");
	}

	public Statement SourceStatement(){
		try {
			stmt = con.createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public Statement DestinationStatement(){
		try {
			stmt1 = con1.createStatement();
			return stmt1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt1;
	}

	// Code to close each and all Object related to Database connection

	public void CloseSourceConnection() throws SQLException {

		con = null;
		stmt = null;
		rs = null;

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		else {
			System.out.println("Source Connection Closed");
		}
	}
	public void CloseDestinationConnection() throws SQLException {

		con1 = null;
		stmt1= null;
		rs1 = null;

		if (rs1 != null) {
			try {
				rs1.close();
			} catch (Exception e) {
			}
		}
		if (stmt1 != null) {
			try {
				stmt1.close();
			} catch (Exception e) {
			}
		}
		if (con1 != null) {
			try {
				con1.close();
			} catch (Exception e) {
			}
		}

		else {
			System.out.println("Destination Connection Closed");
		}

	}
}


