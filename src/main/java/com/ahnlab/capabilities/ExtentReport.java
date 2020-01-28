package com.ahnlab.capabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static String filePath = "./Resource/Report/extentreport.html";
	public static ExtentTest report;
	
	public static ExtentReports GetExtent(String platformName, String platformVersion, String deviceName, String udid,
			String appPackage, String appActivity, String appPath, String automationName) {
		if (extent != null)
			return extent;
		extent = new ExtentReports();
		
		extent.setSystemInfo("platformName", platformName);
//		extent.setSystemInfo("platformVersion", platformVersion);
		extent.setSystemInfo("deviceName", deviceName);
		extent.setSystemInfo("udid", udid);
		extent.setSystemInfo("appPackage", appPackage);
		extent.setSystemInfo("appActivity", appActivity);
		extent.setSystemInfo("appPath", appPath);
		extent.setSystemInfo("automationName", automationName);

		extent.attachReporter(getHtmlReporter());
		
//		report = extent.createTest("123");
		
		return extent;

	}

	private static ExtentHtmlReporter getHtmlReporter() {
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig("./Resource/Report/extent-config.xml");
		return htmlReporter;
	}
	
	public static void warning(String stackTrace) throws Exception {
		report.log(Status.WARNING, MarkupHelper.createCodeBlock(stackTrace));
	}
	
	public static void failStackTrace(String stackTrace) throws Exception {
		report.log(Status.FAIL, MarkupHelper.createCodeBlock(stackTrace));
	}
	
	public static void fail(String resultLog) throws Exception {
		System.out.println("test : " + resultLog);
		report.log(Status.FAIL, MarkupHelper.createCodeBlock(resultLog));
	}
	
	public static void pass(String resultLog, String imgPath) throws Exception {
		report.pass(resultLog, MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
	}
	
	public static void screenshot(String imgPath) throws Exception {
		report.addScreenCaptureFromPath(imgPath);
	}
}
