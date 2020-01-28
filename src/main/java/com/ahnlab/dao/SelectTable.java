package com.ahnlab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTable extends DbUtil {
	
	public static void main(String[] args) {
		try {
			Connection conn = getConnection();
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			
			String selectUDID = "SELECT DISTINCT A.UDID, B.TEST_NAME FROM dev_mit.TEST_LOG A, dev_mit.TEST_MERGE_IMG B;";
//			String selectTable = "SELECT A.UDID, A.STEP_NO , A.TC_BEHAVIOR_METHOD , A.TC_BEHAVIOR_ELEMENT, A.TC_EXPECTED_METHOD, A.TC_EXPECTED_ELEMENT, A.TC_EXPECTED_OUTPUT , A.TC_INPUT, A.TC_SCROLL_VALUE, A.TC_SLEEP_TIME, A.RESULTS, A.STACK_TRACE, A.ELE_IMG, A.FULL_IMG, C.MERGE_IMG_NAME, A.DATE \n" + 
//					"FROM dev_mit.DETAIL_TEST_INFO A, dev_mit.TEST_LOG B, dev_mit.TEST_MERGE_IMG C where A.UDID = B.UDID AND A.UDID = C.UDID AND A.UDID = '3b7f24067a2143a3b886aadc733eed65' order by A.DATE, A.STEP_NO ASC;";
			
			ResultSet rs1 = stmt1.executeQuery(selectUDID);
			while (rs1.next()) {
				String udid = rs1.getString("UDID");
				String testName = rs1.getString("TEST_NAME");
				System.out.println(udid);
				System.out.println(testName);
				
				String selectTable = "SELECT A.STEP_NO , A.TC_BEHAVIOR_METHOD , A.TC_BEHAVIOR_ELEMENT, A.TC_EXPECTED_METHOD, A.TC_EXPECTED_ELEMENT, A.TC_EXPECTED_OUTPUT , A.TC_INPUT, A.TC_SCROLL_VALUE, A.TC_SLEEP_TIME, A.RESULTS, A.STACK_TRACE, A.ELE_IMG, A.FULL_IMG, C.MERGE_IMG_NAME, A.DATE \n" + 
						"FROM dev_mit.DETAIL_TEST_INFO A, dev_mit.TEST_LOG B, dev_mit.TEST_MERGE_IMG C where A.UDID = B.UDID AND A.UDID = C.UDID AND A.UDID = '" + udid + "' order by A.DATE, A.STEP_NO ASC;";
				System.out.println(selectTable);
				
				ResultSet rs2 = stmt2.executeQuery(selectTable);
				while (rs2.next()) {
					
					String stepNo = "stepNo : " +  String.valueOf(rs2.getInt("STEP_NO"));
					String tdBehaviorMethod = "tdBehaviorMethod : " +  rs2.getString("TC_BEHAVIOR_METHOD");
					String tcBehaviorElement = "tcBehaviorElement : " +  rs2.getString("TC_BEHAVIOR_ELEMENT");
					String tcExpectedMethod = "tcExpectedMethod : " +  rs2.getString("TC_EXPECTED_METHOD");
					String tcExpectedElement = "tcExpectedElement : " +  rs2.getString("TC_EXPECTED_ELEMENT");
					String tcExpectedOutput = "tcExpectedOutput : " +  rs2.getString("TC_EXPECTED_OUTPUT");
					String tcInput = "tcInput : " +  rs2.getString("TC_INPUT");
					String tcScrollValue = "tcScrollValue : " +  rs2.getString("TC_SCROLL_VALUE");
					String tcSleepTime = "tcSleepTime : " +  rs2.getString("TC_SLEEP_TIME");
					String results = "results : " +  rs2.getString("RESULTS");
					String stackTrace = "stackTrace : " +  rs2.getString("STACK_TRACE");
					String eleImg = "eleImg : " +  rs2.getString("ELE_IMG");
					String fullImg = "fullImg : " +  rs2.getString("FULL_IMG");
					String mergeImg = "mergeImg : " +  rs2.getString("MERGE_IMG_NAME");
					
					System.out.printf("%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n\n"
							, stepNo, tdBehaviorMethod, tcBehaviorElement, tcExpectedMethod, tcExpectedElement, tcExpectedOutput, tcInput, tcScrollValue, tcSleepTime, results, stackTrace, eleImg, fullImg, mergeImg);
					}
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String test() throws Exception {
		
		
		
		return null;
	}
	
	
	
	
	
//	public static void main(String[] args) {
//		try {
//			Connection conn = getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM dev_mit.PROJECT_INFO WHERE PROJECT_NAME='SODA';");
//			
//			while (rs.next()) {
//				String projectName		=	rs.getString("PROJECT_NAME");
//				String os				=	rs.getString("OS");
//				String appPackage		=	rs.getString("APP_PACKAGE");
//				String appActivity		=	rs.getString("APP_ACTIVITY");
//				String appPath			=	rs.getString("APP_PATH");
//				String krTechWriting	=	rs.getString("KR_TECH_WRITING");
//				String enTechWriting	=	rs.getString("EN_TECH_WRITING");
//				String jpTechWriting	=	rs.getString("JP_TECH_WRITING");
//				
//				System.out.printf("%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n", projectName, os, appPackage, appActivity, appPath, krTechWriting, enTechWriting, jpTechWriting);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
