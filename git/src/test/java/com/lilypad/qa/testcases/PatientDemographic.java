package com.lilypad.qa.testcases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lilypad.qa.util.TestUtil;

import Database.Base;

public class PatientDemographic extends Base{

	TestUtil tu= new TestUtil();
	public PatientDemographic() {
		super();
	}


	@BeforeMethod
	public void setUp() throws ClassNotFoundException, SQLException {
		sourcedb();
		SourceStatement();
		destinationdb();
		DestinationStatement();
	}
	
	//@Test

		public void rowcount() throws ClassNotFoundException, SQLException {

			String query = "select * from [dbo].[MSreplication_options]";
			rs = tu.getData(query);
			rs.next();
		    int rowCount = rs.getInt(1);
		    System.out.println(rowCount);
		}

	@Test

	public void getpatientinfo() throws ClassNotFoundException, SQLException {

		String query = "select * from [dbo].[MSreplication_options]";
		rs = tu.getData(query);
		System.out.println(rs);
		while(rs.next()){
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
		}
	}

	//@Test

	public void patientinfo1() throws ClassNotFoundException, SQLException {

		String query = "select * from [dbo].[MSreplication_options]";
		String query1 = "select * from [dbo].[spt_fallback_usg]";
		rs = tu.getData(query);
		rs1 = tu.getData1(query1);
		System.out.println(rs);
		System.out.println(rs1);
		while(rs.next()&& rs1.next()){
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			System.out.println(rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(3));
		}

		Assert.assertEquals(rs.next(), rs1.next());
		System.out.println("Data matched for source and destination");
	}

	//@Test

	public void compare_two_tables() throws SQLException, ClassNotFoundException {
		String query = "select * from [dbo].[MSreplication_options]";
		String query1 = "select * from [dbo].[spt_fallback_usg]";
		rs = tu.getData(query);
		rs1 = tu.getData1(query1);
		int col = 1;
		while (rs.next() && rs1.next()) {
			final Object res1 = rs.getObject(col);
			final Object res2 = rs1.getObject(col);
			// Check values
			if (!res1.equals(res2)) {
				throw new RuntimeException(String.format("%s and %s aren't equal at common position %d",
						res1, res2, col));
			}

			// rs1 and rs2 must reach last row in the same iteration
			if ((rs.isLast() != rs1.isLast())) {
				throw new RuntimeException("The two ResultSets contains different number of columns!");
			}

			col++;
		}
	}

	//@Test

	public void patientinfo2(ResultSet rs, ResultSet rs1) throws ClassNotFoundException, SQLException {

		String query = "select * from [dbo].[MSreplication_options]";
		String query1 = "select * from [dbo].[MSreplication_options]";
		rs = tu.getData(query);
		rs1 = tu.getData1(query1);
		boolean next1 = true;
		boolean next2 = true;
		while (true) {
			next1 = next1 && rs.next();
			next2 = next2 && rs1.next();

			if (!(next1 || next2)) break; // reached end of both resultsets

			if (next1 != next2) {
				if (next1) {
					// the current row in rs1 does not exist in rs2 (rs2 is at the end already)
					System.out.println("The current row in rs does not exist in rs1 (rs1 is at the end already)");
				} else {
					// the current row in rs1 does not exist in rs (rs is at the end already)
					System.out.println("The current row in rs1 does not exist in rs (rs is at the end already)");
				}
			} else {
				int result = compareRS(rs, rs1);
				if (result < 0) {
					// the current row in rs1 does not exist in rs2
					System.out.println("the current row in Source does not exist in Destination");
				} else if (result > 0) {
					// the current row in rs2 does not exist in rs1
					System.out.println("the current row in Destination does not exist in Source");
				} else {
					// the current rows in rs1 and rs2 are identical
					System.out.println("the current row in Source and Destination is matching");
				}
			}
		}

	}

	private int compareRS(ResultSet rs, ResultSet rs1) {
		return 0;
	}


	@AfterMethod
	public void teardown() throws SQLException {
		CloseSourceConnection();
		CloseDestinationConnection();
	}



}
