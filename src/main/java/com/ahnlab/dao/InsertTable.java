package com.ahnlab.dao;

import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;

import com.ahnlab.utils.Utils;

public class InsertTable extends DbUtil{
	static String date = "NULL";
	
	public static String detailTestInfo(String udid, int stepNo, String tcBehaviorMethod, String tcBehaviorElement, String tcExpectedMethod, String tcExpectedElement, String tcExpectedOutput, String tcInput, String tcScrollValue, int tcSleepTime, boolean results, String stackTrace, String eleImg, String fullImg) {
		try {
			Statement st = getConnection().createStatement();

			String resultsValue = String.valueOf(results);
			
			
			if (StringUtils.isNotBlank(stackTrace) == false){
				stackTrace = null;
			} else {
				stackTrace = stackTrace.replaceAll("'", "&apos;").replaceAll("\'", "&apos;");
			}
			
			st.executeQuery("SET NAMES UTF8");
			st.executeUpdate("INSERT INTO DETAIL_TEST_INFO " + 
					"VALUES('"+ udid + "'" + //
					", " + stepNo + 
					", '" + tcBehaviorMethod + "'" + //
					", '" + tcBehaviorElement + "'" + // 
					", '" + tcExpectedMethod + "'" + //
					", '" + tcExpectedElement + "'" + //wjs 
					", '" + tcExpectedOutput + "'" + //
					", '" + tcInput + "'" +//
					", '" + tcScrollValue + "'" +// 
					", " + tcSleepTime + 
					", '" + resultsValue + "'" +  //
					", '" + stackTrace + "'" +  //
					", '" + eleImg + "'" + //
					", '" + fullImg + "'" +  //
					", " + date + 
					");");
			
		} catch (Exception e ) {
			System.out.println(Utils.StackTraceToString(e));
			
		}
		
		return null;
	}

	public static String testMergeImg(String udid, String testName, String mergeImg) {
		try {
			Statement st = getConnection().createStatement();
			
			st.executeUpdate("INSERT INTO TEST_MERGE_IMG " + 
					"VALUES('"+ udid + "'" +
					", '" + testName + "'" +
					", '" + mergeImg + "'" +
					", " + date + 
					");");
			
		} catch (Exception e ) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public static String testLog(String udid) {
		try {
			Statement st = getConnection().createStatement();
			
			st.executeUpdate("INSERT INTO TEST_LOG " + 
					"VALUES('"+ udid + "'" +
					");");
			
		} catch (Exception e ) {
			System.out.println(e);
		}
		
		return null;
	}	
}
