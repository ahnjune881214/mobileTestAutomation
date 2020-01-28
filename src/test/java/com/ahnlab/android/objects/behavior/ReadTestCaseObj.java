package com.ahnlab.android.objects.behavior;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.android.objects.ScreenshotObj;
import com.ahnlab.capabilities.ExtentReport;
import com.ahnlab.setup.AndroidSetup;

public class ReadTestCaseObj extends AndroidSetup{
//	WebDriverWait wait = new WebDriverWait(getDriver(), 20);

	public static void readTestCase(int readLine, List<String> step, String twFilePath, String androidHome) throws Exception {
		int step_size = step.size();
		tcBehaviorMethod = step.get(0).toLowerCase(); // behavior method normalization
//		String behaviorElement = null;
//		String expectedMethod = null;
//		String expectedElement = null;
//		String expectedOutput = null;
////		String actualResult = null;
//		String input = null;
//		String scrollValue = null;
//		int sleepTime;
//		
//		boolean results;
		
		
//		Array는 0에서 부터 
		if (tcBehaviorMethod.contains("m_click_id") | tcBehaviorMethod.contains("m_click_xpath") | tcBehaviorMethod.contains("m_click_accid")) {
			if (step_size == 2) {
				tcBehaviorElement = step.get(1);
				results = TouchObj.mClickSize2(tcBehaviorMethod, tcBehaviorElement);
//				if (results == true) {
//					System.out.println("[M_CLICK_ID] EleId : " + behaviorMethod + "(" + behaviorElement + ") | Result : PASS");
//					
//				} else {
//					System.out.println("[M_CLICK_ID] Results : FAIL");	
//				}
//				BehaviorParser.mobileClickParser(behavior_method, step.get(1), step.get(2), fullScrennshotImgFilePath, elementScreenshotImgFilepath);

			} else if (step_size == 3) {
				tcBehaviorElement = step.get(1);
				tcExpectedOutput = step.get(2);
				
				results = TouchObj.mClickSize3(tcBehaviorMethod, tcBehaviorElement, tcExpectedOutput);
//				if (results == true) {
//					System.out.println("[M_CLICK_ID] EleId : " + behaviorMethod + "(" + behaviorElement + ") | Ex_output : " + expectedOutput + " | Results : PASS");
//				} else {
//					System.out.println("[M_CLICK_ID] Results : FAIL");	
//				}

			} else if (step_size == 5) {
				tcBehaviorElement = step.get(1);
				tcExpectedMethod = step.get(2);
				tcExpectedElement = step.get(3);
				tcExpectedOutput = step.get(4);
				
				results = TouchObj.mClickSize5(tcBehaviorMethod, tcBehaviorElement, tcExpectedMethod, tcExpectedElement, tcExpectedOutput, twFilePath);
//				if (results == true) {
//					System.out.println("[M_CLICK_ID] EleId : " + behaviorMethod + "(" + behaviorElement + ") | Ex_Method : " + expectedMethod + "(" + expectedElement + ") | Ex_output : " + expectedOutput + " | Results : PASS");
//				} else {
//					System.out.println("[M_CLICK_ID] Results : FAIL");	
//				}
				
			} 
		} else if (tcBehaviorMethod.contains("m_assert") | tcBehaviorMethod.contains("m_assert_id_txt") | tcBehaviorMethod.contains("m_assert_id_tw") |
				tcBehaviorMethod.contains("m_assert_id_txt") | tcBehaviorMethod.contains("m_assert_id_tw")) {
			
			if (step_size == 2) {
				tcExpectedMethod = step.get(0);
				tcExpectedOutput = step.get(1);
				
//				System.out.println("expectedMethod : " + expectedMethod );
//				System.out.println("expectedOutput : " + expectedOutput );
				
				results = AssertObj.assertCheckSize2(tcExpectedMethod, tcExpectedOutput);
//				if (results == true) {
//					System.out.println("[ASSERT] Ex_Method : " + expectedMethod + " | Ex_output : " + expectedOutput + " | Results : PASS");
//				} else {
//					System.out.println("[ASSERT] Results : FAIL");	
//				}
				
			} else if (step_size == 3) {
				tcExpectedMethod = step.get(0);
				tcExpectedElement = step.get(1);				
				tcExpectedOutput = step.get(2);
				results = AssertObj.assertCheckSize3(tcExpectedMethod, tcExpectedElement, tcExpectedOutput, twFilePath);
//				if (results == true) {
//					System.out.println("[ASSERT] Ex_Method : " + expectedMethod + "(" + expectedElement + ") | Ex_output : " + expectedOutput +  " | Results : PASS");
//				} else {
//					System.out.println("[ASSERT] Results : FAIL");	
//				}
			}
			
		} else if (tcBehaviorMethod.contains("m_sendkey_id") | tcBehaviorMethod.contains("m_sendkey_xpath") | tcBehaviorMethod.contains("m_sendkey_accid")) {
			tcBehaviorElement = step.get(1);
			tcInput = step.get(2);

			results = SendKeyObj.sendkey(tcBehaviorMethod, tcBehaviorElement, tcInput);
//			if (results == true) {
//				System.out.println("[M_SENDKEY_ID] EleId : " + behaviorMethod + "(" + behaviorElement + ") | Input : " + input + " | Results : PASS");
//				 
//			} else {
//				System.out.println("[M_SENDKEY_ID] Results : FAIL");		
//			}

		} else if (tcBehaviorMethod.contains("hwback")) {
			results = HwButtonObj.hwBack();
//			if (results == true) {
//				System.out.println("[M_HW_BACK] Results : PASS");
//				
//			} else {
//				System.out.println("[M_HW_BACK] Results : FAIL");
//			}
		} else if (tcBehaviorMethod.contains("hwhome")) {
			results = HwButtonObj.hwHome();
//			if (results == true) {
//				System.out.println("[M_HW_HOME] Results : PASS");
//				
//			} else {
//				System.out.println("[M_HW_HOME] Results : FAIL");
//			}
		} else if (tcBehaviorMethod.contains("m_sleep")) {
			tcSleepTime = Integer.parseInt(step.get(1));
			results = EtcObj.sleep(tcSleepTime);
//			if (results == true) {
//				System.out.println("[M_SLEEP] SLEEP : " + sleepTime + " | Results : PASS");
//				
//			} else {
//				System.out.println("[M_SLEEP] Results : FAIL");				
//			}
		} else if (tcBehaviorMethod.contains("m_scroll")) {
			tcScrollValue = step.get(1);
			
			results = EtcObj.scroll(tcBehaviorMethod, tcScrollValue);
//			if (results == true) {
//				System.out.println("[M_SCROLL] Value : " + scrollValue + " | Results : PASS");
//				
//			} else {
//				System.out.println("[M_SCROLL] Results : FAIL");				
//			}
		} else if (tcBehaviorMethod.contains("m_capture")) {
			tcBehaviorElement = step.get(1);
			String test = ScreenshotObj.objectScreenshot(tcBehaviorMethod, tcBehaviorElement);
//			System.out.println("screenshot : " + test);
		} 
		
		
		
//		if (behaviorMethod.contains("m_click_id")) {
//			
//		} else if (behaviorMethod.contains("m_click_xpath")) {
//			
//		}
		
	}
}
