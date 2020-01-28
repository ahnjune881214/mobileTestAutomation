package com.ahnlab.android.test;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./extentreport.html";

	
	public static ExtentReports GetExtent() {
		if (extent != null)
			return extent;
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;

	}

	private static ExtentHtmlReporter getHtmlReporter() {
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig("./extent-config.xml");
		return htmlReporter;
	}

//	public static ExtentTest createTest(String name, String description) {
//		test = extent.createTest(name, description);
//		return test;
//
//	}
	
	public static void main(String[] args) throws IOException {
		extent = ExtentManager.GetExtent();
		
//		KlovReporter k = new KlovReporter();
//		k.initMongoDbConnection("localhost", 27017);
//		k.setProjectName("KlovProject");
//		k.setReportName("AppBuild");
//		k.setKlovUrl("http://locoalhost");
//		
//		extent.attachReporter(k);
		
		
		ExtentTest report = extent.createTest("TestCase1");
		
		String img1 = "/Users/juneahn/git/m_blia/Resource/TestImage/ElementImg/5023a6e799c54a00b257ed033cc22ae0.png";
		String img2 = "/Users/juneahn/git/m_blia/Resource/TestImage/ElementImg/54129663b0e04a6ea1011e70969d68e6.png";
		String img3 = "/Users/juneahn/git/m_blia/Resource/TestImage/ElementImg/10280a0cff8a43e890108bf56c696981.png";
		String img4 = "/Users/juneahn/git/m_blia/Resource/TestImage/ElementImg/cf65e44a44444a56b0c06d62cd8f0091.png";
		String img5 = "/Users/juneahn/git/m_blia/Resource/TestImage/ElementImg/64302820928a4a5296acaaf7abc0d99f.png";
		
		report.pass("[Touch] 권한 허용하기", MediaEntityBuilder.createScreenCaptureFromPath(img1).build());
		report.pass("[Touch] 허용", MediaEntityBuilder.createScreenCaptureFromPath(img2).build());
		report.pass("[Touch] ", MediaEntityBuilder.createScreenCaptureFromPath(img3).build());
		report.pass("[Touch] 다음", MediaEntityBuilder.createScreenCaptureFromPath(img4).build());
		report.pass("[Touch] 불필요한 앱 (선택 권장)", MediaEntityBuilder.createScreenCaptureFromPath(img5).build());
		
		report.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
		report.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.BLACK));
		report.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.GREEN));
		
		String[][] arr = {{"1","2"}, {"3","4"},{"5","6"}};
		report.log(Status.FAIL, MarkupHelper.createTable(arr));
		
		report.log(Status.WARNING, MarkupHelper.createCodeBlock("[RemoteTestNG] detected TestNG version 6.14.3\n" + 
				"4월 16, 2019 11:30:45 오전 io.appium.java_client.remote.AppiumCommandExecutor$1 lambda$0\n" + 
				"INFO: Detected dialect: W3C\n" + 
				"[Total TC Count] [test,  test]\n" + 
				"[Current TC] ./Resource/TestScripts/SODA/test.robot"));

		report.addScreenCaptureFromPath("123 : ", "/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png");
		report.addScreenCaptureFromPath("/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png");
		
		extent.flush();
	}
}
