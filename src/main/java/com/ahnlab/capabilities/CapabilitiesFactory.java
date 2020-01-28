package com.ahnlab.capabilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.ahnlab.dao.DbUtil;
import com.ahnlab.extentreport.ExtentManager;
import com.aventstack.extentreports.ExtentTest;

public class CapabilitiesFactory extends ExtentReport{
    protected static DesiredCapabilities capabilities;

//    public static DesiredCapabilities getCapabilities(String projectName, String platformVersion, String deviceName, String udid, Boolean noReset, String automationName, String testcase) throws Exception {
//    	Connection conn = DbUtil.getConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM dev_mit.PROJECT_INFO WHERE PROJECT_NAME='" + projectName + "';");
//
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//    	
//		while (rs.next()) {
//			String os				=	rs.getString("OS");
//			String appPackage		=	rs.getString("APP_PACKAGE");
//			String appActivity		=	rs.getString("APP_ACTIVITY");
//			String appPath			=	rs.getString("APP_PATH");
//			
//			capabilities.setCapability("platformName", os); 
//			capabilities.setCapability("platformVersion", platformVersion); 
//			
//			capabilities.setCapability("deviceName", deviceName);
//			capabilities.setCapability("udid", udid);
//			
//			capabilities.setCapability("appPackage", appPackage);
//			capabilities.setCapability("appActivity", appActivity);
//			capabilities.setCapability("app", appPath);
//			
//			capabilities.setCapability("automationName", automationName);
//			
//			capabilities.setCapability("newCommandTimeout", 120);
//			if (noReset == true) {
//				capabilities.setCapability("noReset", false);
//			} else {
//				capabilities.setCapability("noReset", true);
//			}
//			
//			extent = ExtentReport.GetExtent(os, platformVersion, deviceName, udid, appPackage, appActivity, appPath, automationName);
//		}
//    	
//        return capabilities;
//    }
}
