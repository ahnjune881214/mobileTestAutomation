package com.ahnlab.android.transtrator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestCaseGeneration {
	static String scr1 = "package com.ahnlab.testcase;\n" + 
			"\n" + 
			"import java.util.ArrayList;\n" + 
			"import java.util.Arrays;\n" + 
			"import java.util.Collections;\n" + 
			"import java.util.List;\n" + 
			"\n" + 
			"import org.testng.annotations.Parameters;\n" + 
			"import org.testng.annotations.Test;\n" + 
			"\n" + 
			"import com.ahnlab.android.objects.ScreenshotObj;\n" + 
			"import com.ahnlab.android.objects.behavior.InitializeObj;\n" + 
			"import com.ahnlab.android.objects.behavior.ReadTestCaseObj;\n" + 
			"import com.ahnlab.android.transtrator.ReadRobot;\n" + 
			"import com.ahnlab.dao.InsertTable;\n" + 
			"import com.ahnlab.setup.AndroidSetup;\n" + 
			"import com.ahnlab.utils.Utils;\n" + 
			"\n" + 
			"import io.qameta.allure.Description;\n" + 
			"\n";
	
	static String scr2 = "	@Test\n" + 
			"	@Description(\"projectName\")\n" + 
			"	@Parameters({ \"twFilePath\", \"testCaseFilePath\", \"androidHome\" })\n" + 
			"	public void testMethod(String twFilePath, String testcaseFilePath, String androidHome) throws Exception {\n" + 
			"		System.out.println(\"[TW_File_Path] : \" + twFilePath);\n" + 
			"		udid = Utils.nameingConvention();\n" + 
			"		InsertTable.testLog(udid);\n" + 
			"		String temp = \"./Resource/TestScripts/SODA/test.robot\";\n" + 
			"		System.out.println(\"[TestCase] \" + temp);\n" + 
			"\n" + 
			"		ArrayList<String> tcList = ReadRobot.readRobotScripts(temp);\n" + 
			"		int readLine = 1;\n" + 
			"		for (String tc : tcList) {\n" + 
			"			List<String> step = Arrays.asList(tc.substring(4).split(\"    \"));\n" + 
			"			if (step.get(0).contains(\"Documentation\")) {\n" + 
			"				testName = tc.replaceAll(\"    \\\\[Documentation\\\\]    \", \"\");\n" + 
			"				System.out.println(\"[TestCase Name] \" + testName);\n" + 
			"\n" + 
			"//					ExtentTest report = extent.createTest(testName);\n" + 
			"\n" + 
			"			} else if (!step.get(0).contains(\"Documentation\")) {\n" + 
			"				stepNo = readLine;\n" + 
			"				ReadTestCaseObj.readTestCase(readLine, step, twFilePath, androidHome);\n" + 
			"\n" + 
			"				mergeImages.add(fullImg);\n" + 
			"				System.out.println(\"udid : \" + udid + \" | stepNo : \" + stepNo + \" | behaviorMethod : \"\n" + 
			"						+ tcBehaviorMethod + \" | behaviorElement : \" + tcBehaviorElement + \" | expectedMethod : \"\n" + 
			"						+ tcExpectedMethod + \" | expectedElement : \" + tcExpectedElement + \" | expectedOutput : \"\n" + 
			"						+ tcExpectedOutput + \" | input : \" + tcInput + \" | scrollValue : \" + tcScrollValue\n" + 
			"						+ \" | sleepTime : \" + tcSleepTime + \" | results : \" + results + \" | stackTrace : \" + stackTrace\n" + 
			"						+ \" | eleImg : \" + eleImg + \" | fullImg : \" + fullImg);\n" + 
			"				InsertTable.detailTestInfo(udid, stepNo, tcBehaviorMethod, tcBehaviorElement, tcExpectedMethod,\n" + 
			"						tcExpectedElement, tcExpectedOutput, tcInput, tcScrollValue, tcSleepTime, results, stackTrace,\n" + 
			"						eleImg, fullImg);\n" + 
			"				InitializeObj.initialization();\n" + 
			"\n" + 
			"				readLine++;\n" + 
			"			}\n" + 
			"		}\n" + 
			"\n" + 
			"		// null 제거\n" + 
			"		mergeImages.removeAll(Collections.singleton(null));\n" + 
			"		String mergeImg = ScreenshotObj.mergeImgArray(mergeImages);\n" + 
			"		// 합친 이미지 모음 table\n" + 
			"		InsertTable.testMergeImg(udid, testName, mergeImg);\n" + 
			"		// 현재 수행한 테스트 로그 모음\n" + 
			"		System.out.println(\"[MergeImg] \" + mergeImg);\n" + 
			"\n" + 
			"		Thread.sleep(5000);\n" + 
			"		dr.get().closeApp();\n" + 
			"		Thread.sleep(5000);\n" + 
			"		dr.get().launchApp();\n" + 
			"\n" + 
			"		System.out.println(\"\\n\");\n" + 
			"		dr.get().closeApp();\n" + 
			"	}\n" + 
			"}\n" + 
			"";
	
	public static void main(String[] args) throws IOException {
		
		String filePath = "/Users/juneahn/git/m_blia/src/test/java/com/ahnlab/testcase/";
		String rfTcName = "abc";
		BufferedWriter tcWrite = new BufferedWriter(new FileWriter(filePath + rfTcName + ".java"));
		
		tcWrite.write(scr1);
		tcWrite.write("public class " + rfTcName + " extends AndroidSetup {\n");
		tcWrite.write(scr2);
		tcWrite.close();
	}	
}
