package com.lilypad.qa.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.Base;

public class TestUtil extends Base{


	// Method to fetch data from Source database
	public ResultSet getData(String query) throws ClassNotFoundException, SQLException{
		ResultSet data = SourceStatement().executeQuery(query);
		return data;
	}

	// Method to fetch data from Destination database
	public ResultSet getData1(String query1) throws ClassNotFoundException, SQLException{
		ResultSet data1 = DestinationStatement().executeQuery(query1);
		return data1;
	}

	// Method to Insert data to Source database
	public void insertData(String query) throws ClassNotFoundException, SQLException{
		Statement sta = SourceStatement();
		sta.executeUpdate(query);
	}

	// Method to Insert data to Destination database
	public void insertData1(String query1) throws ClassNotFoundException, SQLException{
		Statement sta = DestinationStatement();
		sta.executeUpdate(query1);	
	}

	// Method to Update data to Source database
	public void updateData(String query) throws ClassNotFoundException, SQLException{
		SourceStatement().executeUpdate(query);

	}

	// Method to Update data to Destination database
	public void updateData1(String query1) throws ClassNotFoundException, SQLException{
		SourceStatement().executeUpdate(query1);

	}

	

}
