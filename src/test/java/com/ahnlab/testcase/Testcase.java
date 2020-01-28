package com.ahnlab.testcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ahnlab.android.objects.ScreenshotObj;
import com.ahnlab.android.objects.behavior.InitializeObj;
import com.ahnlab.android.objects.behavior.ReadTestCaseObj;
import com.ahnlab.android.transtrator.ReadRobot;
import com.ahnlab.dao.InsertTable;
import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;
import com.aventstack.extentreports.ExtentTest;

import io.qameta.allure.Description;

public class Testcase extends AndroidSetup{

	//
	// TC를 불러다 쓰면 리포트 작성시 하나에 모두 저장됨 
	// 해서 요기는 자동으로 TC 만들어 주는 기능 추가해야함 !!!!! 
	// 디바이스 기준으로 ? 
	// 
	
//	@Test(retryAnalyzer = RetryListener.class)
	@Test
	@Description("projectName")
	@Parameters({"twFilePath", "testCaseFilePath", "androidHome"})
	public void testMethod(String twFilePath, String testcaseFilePath, String androidHome) throws Exception {
		System.out.println("[TW_File_Path] : " + twFilePath);		
		udid = Utils.nameingConvention();
		InsertTable.testLog(udid);
//		List<String> setTestcase = Arrays.asList(testcase.split(","));
//		ArrayList<String> mergeImages = new ArrayList<>();
		
//		for (String temp : setTestcase) {
//			String temp = testcaseFilePath + projectName.replaceAll(" ", "") + "/" + temp.replaceAll(" ", "") + ".robot";
			String temp = "./Resource/TestScripts/ESAND/test.robot"; // 변경가능해야함
			System.out.println("[TestCase] " + temp);

			ArrayList<String> tcList = ReadRobot.readRobotScripts(temp);
			int readLine = 1;
			for(String tc : tcList) {
				List<String> step = Arrays.asList(tc.substring(4).split("    "));
				if (step.get(0).contains("Documentation")){
					testName = tc.replaceAll("    \\[Documentation\\]    ", "");
					System.out.println("[TestCase Name] " + testName);
					
//					ExtentTest report = extent.createTest(testName);
					
				} else if (!step.get(0).contains("Documentation")) {
					System.out.println("readLine : " + readLine);
					System.out.println("step : " + step);
					System.out.println("twFilePath : " + twFilePath);
					System.out.println("androidHome : " + androidHome);
					
					stepNo = readLine;
					ReadTestCaseObj.readTestCase(readLine, step, twFilePath, androidHome);
					
					mergeImages.add(fullImg);
					System.out.println("udid : " + udid + " | stepNo : " + stepNo + " | behaviorMethod : " + tcBehaviorMethod + 
							" | behaviorElement : " + tcBehaviorElement + " | expectedMethod : " + tcExpectedMethod + " | expectedElement : " + tcExpectedElement + " | expectedOutput : " + tcExpectedOutput + " | input : " + tcInput + " | scrollValue : " + tcScrollValue + " | sleepTime : " + tcSleepTime + " | results : " + results + " | stackTrace : " + stackTrace + 
							" | eleImg : " + eleImg + " | fullImg : " + fullImg);
					InsertTable.detailTestInfo(udid, stepNo,  tcBehaviorMethod, tcBehaviorElement, tcExpectedMethod, tcExpectedElement, tcExpectedOutput, tcInput, tcScrollValue, tcSleepTime, results, stackTrace, eleImg, fullImg);
					InitializeObj.initialization();
					
					readLine++;
				}
			}

			// null 제거
			mergeImages.removeAll(Collections.singleton(null));
			String mergeImg = ScreenshotObj.mergeImgArray(mergeImages);
			//합친 이미지 모음 table
			InsertTable.testMergeImg(udid, testName, mergeImg);
			// 현재 수행한 테스트 로그 모음
			System.out.println("[MergeImg] " + mergeImg);

			Thread.sleep(5000);
			dr.get().closeApp();
			Thread.sleep(5000);
			dr.get().launchApp();
			
			System.out.println("\n");
//		}
		dr.get().closeApp();
	}
}
