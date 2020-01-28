package com.ahnlab.dao;

import java.sql.Statement;

public class TruncateTable extends DbUtil{
	public static String testLog() {
		try {
			Statement st = getConnection().createStatement();
			
			st.executeUpdate("TRUNCATE TABLE TEST_LOG;");
			
		} catch (Exception e ) {
			System.out.println(e);
		}
		
		return null;
	}	
}
