package com.ahnlab.setup;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ahnlab.android.objects.ScreenshotObj;
import com.ahnlab.android.objects.behavior.InitializeObj;
import com.ahnlab.android.objects.behavior.TouchObj;
import com.ahnlab.capabilities.ExtentReport;
import com.ahnlab.dao.DbUtil;
import com.ahnlab.dao.InsertTable;
import com.ahnlab.utils.Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidSetup extends ExtentReport {
	public static AndroidDriver<AndroidElement> driver;
	public static ThreadLocal<AndroidDriver> dr = new ThreadLocal<AndroidDriver>();
	
    public static String projectName = null;
    public static String testcase = null;
    
    public static String udid = null;
    public static int stepNo;
    
    public static String tcBehaviorMethod = null;
    public static String tcBehaviorElement = null;
    public static String tcExpectedMethod = null;
    public static String tcExpectedElement = null;
    public static String tcExpectedOutput = null;
    public static String tcInput = null;
    public static String tcScrollValue = null;
    public static int tcSleepTime;
    
    public static boolean results;
    public static String stackTrace = null;
    
    public static String eleImg = null; 
    public static String fullImg = null;
    
    // merge img table
    public static String testName = null;
    public static String mergeImg = null;
    public static ArrayList<String> mergeImages = new ArrayList<>();
    public static String imgPath = null;
    
    // extent report
//	public static ExtentReports extent;
//	public static ExtentHtmlReporter htmlReporter;
//	public static String filePath = "./Resource/Report/extentreport.html";
    private ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();
    public static WebDriverWait wait;
    
    @BeforeTest(alwaysRun = true)
//	@BeforeClass
	@Parameters({"projectName", "platformName", "platformVersion", "deviceName", "udid", "appiumServer", "noReset", "testcase", "automationName"})
	public void setUp(String projectName, String platformName, String platformVersion, String deviceName, String udid, String appiumServer, boolean noReset, String testcase, String automationName) throws Exception {
		AndroidSetup.projectName = projectName;
		AndroidSetup.testcase = testcase;

    	Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM dev_mit.PROJECT_INFO WHERE PROJECT_NAME='" + projectName + "';");

		DesiredCapabilities capabilities = new DesiredCapabilities();
    	
		while (rs.next()) {
			String os				=	rs.getString("OS");
			String appPackage		=	rs.getString("APP_PACKAGE");
			String appActivity		=	rs.getString("APP_ACTIVITY");
			String appPath			=	rs.getString("APP_PATH");
			
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, os); 
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion); 
			
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appActivity", appActivity);
			capabilities.setCapability(MobileCapabilityType.APP, appPath);
			
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
			
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
			
			capabilities.setCapability("newCommandTimeout", 120);
			if (noReset == true) {
				capabilities.setCapability("noReset", false);
			} else {
				capabilities.setCapability("noReset", true);
			}
			
			extent = ExtentReport.GetExtent(os, platformVersion, deviceName, udid, appPackage, appActivity, appPath, automationName);
			System.out.println("11");	
		}
		System.out.println("1");
//    	driver = new AndroidDriver<AndroidElement>(new URL(appiumServer),CapabilitiesFactory.getCapabilities(projectName, platformVersion, deviceName, udid, noReset, automationName, testcase));
    	
//		driver = new AndroidDriver<AndroidElement>(new URL(appiumServer),capabilities);
//		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		setDriver(driver);
		
		threadLocalDriver.setTLDriver(new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4444/wd/hub"),capabilities));
		driver = threadLocalDriver.getTLDriver();
		wait = new WebDriverWait(driver, 10);
		 
		
		
		
		System.out.println("2");
		
		
		
		mergeImages.clear();
//		extent = ExtentReport.GetExtent();
		report = extent.createTest(testcase);
		
		System.out.println("3");
//		TruncateTable.testLog();
		// report 
//		extent = ExtentManager.GetExtent();
		
		Thread.sleep(5000);
		
	}

//	➜  ~ adb shell getprop | grep locale // 단말 언어 확인
	
	public static AndroidDriver getDriver() {
		return dr.get();
	}

	public void setDriver(AndroidDriver driver) {
		dr.set(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
//				TakesScreenshot ts = (TakesScreenshot) driver;
//				File source = ts.getScreenshotAs(OutputType.FILE);
//				SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//				Calendar calendar = Calendar.getInstance();
//				FileUtils.copyFile(source, new File(PropertiesGetter.getter("failingScreenshotImgFilePath") + formater.format(calendar.getTime()) + ".png"));
				
				
				
				
//				mergeImages.add((ScreenshotObj.errScreenshot()));
//				imgPath = TouchObj.mergeImg();
//				ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
//				extent.flush();
				
				
				
			} catch (Exception e) {
//				System.out.println("Exception while taking screenshot " + e.getMessage());
				mergeImages.add(fullImg);
				stackTrace = Utils.StackTraceToString(e);
				System.out.println("udid : " + udid + " | stepNo : " + stepNo + " | behaviorMethod : " + tcBehaviorMethod + 
						" | behaviorElement : " + tcBehaviorElement + " | expectedMethod : " + tcExpectedMethod + " | expectedElement : " + tcExpectedElement + " | expectedOutput : " + tcExpectedOutput + " | input : " + tcInput + " | scrollValue : " + tcScrollValue + " | sleepTime : " + tcSleepTime + " | results : " + results + " | stackTrace : " + stackTrace + 
						" | eleImg : " + eleImg + " | fullImg : " + fullImg);
				InsertTable.detailTestInfo(udid, stepNo,  tcBehaviorMethod, tcBehaviorElement, tcExpectedMethod, tcExpectedElement, tcExpectedOutput, tcInput, tcScrollValue, tcSleepTime, results, stackTrace, eleImg, fullImg);
				
				// null 제거
				mergeImages.removeAll(Collections.singleton(null));
				String mergeImg = ScreenshotObj.mergeImgArray(mergeImages);
				
				
				
				////
				String exceptionLog = Utils.StackTraceToString(e);
				System.out.println("[ERROR StackTrace] \n" + exceptionLog);
				ExtentReport.failStackTrace(exceptionLog);
				
				if (mergeImg != null) {
					ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + mergeImg);	
					//합친 이미지 모음 table
					InsertTable.testMergeImg(udid, testName, mergeImg);
					InitializeObj.initialization();
				}
				
				extent.flush();
			}
		} else {
//			mergeImages.add((ScreenshotObj.errScreenshot()));
			imgPath = TouchObj.mergeImg(udid);
			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
//			System.out.println("[TestMethodName] " + result.getName());
		}
		
	}
}


