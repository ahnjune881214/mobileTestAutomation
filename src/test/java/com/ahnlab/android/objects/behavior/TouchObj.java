package com.ahnlab.android.objects.behavior;


import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ahnlab.android.objects.ScreenshotObj;
import com.ahnlab.android.objects.assertion.AssertTw;
import com.ahnlab.capabilities.ExtentReport;
import com.ahnlab.dao.InsertTable;
import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileBy.ByAccessibilityId;

public class TouchObj extends AndroidSetup {
//	public static String imgPath = null;
	static String eleImgPath = null;
//	protected static String actualResults = null;
//	protected static String expectedOutput = null;			
	
	
	
//	static WebDriverWait wait = new WebDriverWait(getDriver(), 20);
//	static ScreenshotObj screenshot = new ScreenshotObj();

//    public static String StackTraceToString(Exception e) {
//        String result = e.toString() + "\n";
//        StackTraceElement[] trace = e.getStackTrace();
//        
//        for (int i = 0;i < trace.length; i++) {
//            result += trace[i].toString() + "\n";
//        }
//        return result;
//    }
	
public static boolean mClickSize2(String behaviorMethod, String behaviorElement) throws Exception {
		try {			
			if (behaviorMethod.contains("m_click_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				// Screenshot  
				// 1. behaviorMethod.contain("id" or "xpath" or "accid")
				// 2. behaviorElement
				// 3. 
				System.out.println("behaviorMethod : " + behaviorMethod);
				System.out.println("behaviorElem나ent : " + behaviorElement);
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
//				System.out.println("behaviorMethod : " + behaviorMethod);
//				System.out.println("behaviorElement : " + behaviorElement);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement))).click();
				
				ExtentReport.pass("[m_click_id] " + behaviorElement, "/Users/juneahn/git/m_blia/" + eleImgPath);
				
			} else if (behaviorMethod.contains("m_click_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement))).click();
				
				ExtentReport.pass("[m_click_xpath] " + behaviorElement, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else if (behaviorMethod.contains("m_click_accid")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement))).click();
				
				ExtentReport.pass("[m_click_accid] " + behaviorElement, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else {
				// Assertion
				mergeImages.add((ScreenshotObj.errScreenshot()));
				imgPath = mergeImg(udid);
				ExtentReport.failStackTrace("[FAIL] 알수 없는 이유로 실패하였습니다. \n" 
						+ "- behaviorMethod : " + behaviorMethod + "\n" 
						+ "- behaviorElement : " + behaviorElement
						);
				ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
				extent.flush();
				
				Assert.assertTrue(false);
			}
			
		} catch (TimeoutException | NoSuchElementException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			// Assertion
			System.out.println("[FAIL]");
			System.out.println("[StackTrace1] \n" + exceptionLog);
			imgPath = mergeImg(udid);
			ExtentReport.failStackTrace("[FAIL] </br>- behaviorMethod : " + behaviorMethod + "</br>" 
					+ "- behaviorElement : " + behaviorElement);
			ExtentReport.failStackTrace(exceptionLog);
			ExtentReport.screenshot(imgPath);
//			extent.flush();
			
			Assert.assertTrue(false);
			
		} catch (WebDriverException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] WebDriver \n" + exceptionLog);
			// Assertion
			ExtentReport.warning("[WARNING] WebDriver \n" 
					+ "- StackTrace : \n" + exceptionLog);
//			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
		
			Assert.assertTrue(false);
			
//			Thread.sleep(20000);
//			String cmd = "/Users/juneahn/Library/Android/sdk/platform-tools/adb reboot"; 
//            Process p = Runtime.getRuntime().exec(cmd);
//            Thread.sleep(30000);
            
		} catch (Exception e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[FAIL]");
			System.out.println("[StackTrace2] \n" + exceptionLog);
			imgPath = mergeImg(udid);
			ExtentReport.fail("[FAIL] </br>- behaviorMethod : " + behaviorMethod + "</br>" 
					+ "- behaviorElement : " + behaviorElement);
			ExtentReport.failStackTrace(exceptionLog);
			Assert.assertTrue(false);
		}
		
//		report.pass("[M_CLICK_ID] EleId : " + behaviorMethod + "(" + behaviorElement + ") | Result : PASS");
		return true;
	}

	public static boolean mClickSize3(String behaviorMethod, String behaviorElement, String expectedOutput) throws Exception {
		try {
			if (behaviorMethod.contains("m_click_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement))).click();
				
//				ExtentReport.pass("[m_click_id] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else if (behaviorMethod.contains("m_click_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement))).click();
				
//				ExtentReport.pass("[m_click_xpath] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else if (behaviorMethod.contains("m_click_accid")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement))).click();

//				ExtentReport.pass("[m_click_accid] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else {
				// Assertion
				imgPath = mergeImg(udid);
				
				System.out.println("[FAIL] 알수 없는 이유로 실패하였습니다.");
				ExtentReport.fail("[FAIL] 알수 없는 이유로 실패하였습니다. </br>" 
							+ "- behaviorMethod : " + behaviorMethod + "</br>" 
							+ "- behaviorElement : " + behaviorElement + "</br>" 
							+ "- expectedOutput : " + expectedOutput
							);
				
				ExtentReport.screenshot(imgPath);
//				extent.flush();
				
				Assert.assertTrue(false);
				
			}

			Thread.sleep(1000);
			long start = System.currentTimeMillis();
			String actualResults = null;
			while (true) {
				actualResults = getDriver().getPageSource().toString();
				if (actualResults.contains(expectedOutput)) {
					ExtentReport.pass("[m_click] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
					break;
					
				} else if (System.currentTimeMillis()-start > 50000) {
					// Assertion
					System.out.println("[FAIL] Timeout b");
					System.out.println("[ActualResults] " + actualResults);
					System.out.println("[ExpectedOutput] " + expectedOutput);
					
					mergeImages.add((ScreenshotObj.errScreenshot()));
					imgPath = mergeImg(udid);
					ExtentReport.failStackTrace("[FAIL] \n- actualResults : \n" + actualResults + "\n" 
							+ "- ExpectedOutput : \n" + expectedOutput);
					ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
					extent.flush();
					
					Assert.assertTrue(false);

					break;
				} 
			}
			
		} catch (TimeoutException | NoSuchElementException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[FAIL]");
			System.out.println("[StackTrace3] \n" + exceptionLog);
			// Assertion
			mergeImages.add((ScreenshotObj.errScreenshot()));
			imgPath = mergeImg(udid);
			ExtentReport.failStackTrace("[FAIL] \n- behaviorMethod : " + behaviorMethod + "\n" 
					+ "- behaviorElement : \n" + behaviorElement + "\n" 
					+ "- ExpectedOutput : \n" + expectedOutput);
			ExtentReport.failStackTrace("- exceptionLog : \n" + exceptionLog);
			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
			
			Assert.assertTrue(false);
			
		} catch (WebDriverException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] WebDriver \n" + exceptionLog);
			// Assertion
			ExtentReport.warning("[WARNING] WebDriver \n" 
					+ "- StackTrace : \n" + exceptionLog);
//			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
		
			Assert.assertTrue(false);
			
//			Thread.sleep(20000);
//			String cmd = "/Users/juneahn/Library/Android/sdk/platform-tools/adb reboot"; 
//            Process p = Runtime.getRuntime().exec(cmd);
//            Thread.sleep(30000);
			
		} catch (Exception e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace4] \n" + exceptionLog);
			// Assertion
			mergeImages.add((ScreenshotObj.errScreenshot()));
			imgPath = mergeImg(udid);
			ExtentReport.failStackTrace("[FAIL] \n- behaviorMethod : " + behaviorMethod + "\n" 
					+ "- behaviorElement : \n" + behaviorElement + "\n" 
					+ "- ExpectedOutput : \n" + expectedOutput);
			ExtentReport.failStackTrace("- exceptionLog : \n" + exceptionLog);
			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
			
			Assert.assertTrue(false);
			
		}
		// Assertion
		return true;
		
	}
	
	public static boolean mClickSize5(String behaviorMethod, String behaviorElement, String expectedMethod, String expectedElement, String expectedOutput, String twFilePath) throws Exception {
		try {
			if (behaviorMethod.contains("m_click_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement))).click();

//				ExtentReport.pass("[m_click_id] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else if (behaviorMethod.contains("m_click_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement))).click();

//				ExtentReport.pass("[m_click_id] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else if (behaviorMethod.contains("m_click_accid")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement)));
				// Screenshot
				eleImgPath = ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement))).click();

//				ExtentReport.pass("[m_click_id] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
			} else {
				// Assertion
				mergeImages.add((ScreenshotObj.errScreenshot()));
				imgPath = mergeImg(udid);
				ExtentReport.failStackTrace("[FAIL] 알수 없는 이유로 실패하였습니다.\n " 
						+ "- behaviorMethod : " + behaviorMethod + "\n"
						+ "- behaviorElement : " + behaviorElement + "\n" 
						+ "- expectedElement : " + expectedElement + "\n"
						+ "- expectedOutput : " + expectedElement + "\n"
						+ "- twFilePath : " + expectedElement 
						);
				ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
				extent.flush();
							
				Assert.assertTrue(false);
			}

 			String actualResult = null;
 			if (expectedMethod.toLowerCase().contains("m_assert_id_txt")) {
 				actualResult = getDriver().findElement(By.id(expectedElement)).getText();
 				Thread.sleep(1000);
 				long start = System.currentTimeMillis();
 				while (true) {
 					if (actualResult.contains(expectedOutput)) {
 						ExtentReport.pass("[m_click] " + behaviorElement + "</br>[expectedOutput] " + expectedOutput, "/Users/juneahn/git/m_blia/" + eleImgPath);
 						break;
 					} else if (System.currentTimeMillis()-start > 20000) {
 						// Assertion
 						System.out.println("[FAIL] Timeout a");
 						System.out.println("[ActualResults] " + actualResult);
 						System.out.println("[ExpectedOutput] " + expectedOutput);
 						
 						
 						mergeImages.add((ScreenshotObj.errScreenshot()));
 						imgPath = mergeImg(udid);
 						ExtentReport.failStackTrace("[FAIL] \n- behaviorMethod : " + behaviorMethod + "\n" 
 								+ "- behaviorElement : " + behaviorElement + "\n" 
 								+ "- expectedOutput : " + expectedOutput);
 						ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
 						extent.flush();
 						
 						
 						Assert.assertTrue(false);
 						break;
 					} 
 				}
 				
 			} else if (expectedMethod.toLowerCase().contains("m_assert_id_tw")) {
 				actualResult = getDriver().findElement(By.id(expectedElement)).getText();
 				AssertTw.assertTw(twFilePath, expectedOutput, actualResult);
 				
 			} else if (expectedMethod.toLowerCase().contains("m_assert_xpath_txt")) {
 				actualResult = getDriver().findElement(By.xpath(expectedElement)).getText();
 				Thread.sleep(1000);
 				long start = System.currentTimeMillis();
 				while (true) {
 					if (actualResult.contains(expectedOutput)) {
 						break;
 					} else if (System.currentTimeMillis()-start > 20000) {
 						// Assertion
 						System.out.println("[FAIL] ActualResults와 Expectedoutput이 맞지 않습니다.");
 						System.out.println("[ActualResults] " + actualResult);
 						System.out.println("[ExpectedOutput] " + expectedOutput);
 						
 						
 						mergeImages.add((ScreenshotObj.errScreenshot()));
 						imgPath = mergeImg(udid);
 						ExtentReport.failStackTrace("[FAIL] ActualResults와 Expectedoutput이 맞지 않습니다. \n" 
 								+ "- ActualResults : " + actualResult + "\n" 
 								+ "- ExpectedOutput : " + expectedOutput);
 						ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
 						extent.flush();
 						
 						
 						
 						Assert.assertTrue(false);
 						break;
 					} 
 				}
 				
 			} else if (expectedMethod.toLowerCase().contains("m_assert_xpath_tw")) {
 				actualResult = getDriver().findElement(By.xpath(expectedElement)).getText();
 				AssertTw.assertTw(twFilePath, expectedOutput, actualResult);
 				
 			} else {
 				Assert.assertTrue(false);
 			}
			
		} catch (TimeoutException | NoSuchElementException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[ERROR StackTrace] \n" + exceptionLog);
			// Assertion
			
			mergeImages.add((ScreenshotObj.errScreenshot()));
			imgPath = mergeImg(udid);
			ExtentReport.failStackTrace("[FAIL] \n- behaviorMethod : " + behaviorMethod + "\n"
					+ "- behaviorElement : " + behaviorElement + "\n" 
					+ "- expectedElement : " + expectedElement + "\n"
					+ "- expectedOutput : " + expectedElement + "\n"
					+ "- twFilePath : " + expectedElement + "\n" );
			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
			
			Assert.assertTrue(false);
			
		} catch (Exception e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace5] \n" + exceptionLog);
			// Assertion
			
			mergeImages.add((ScreenshotObj.errScreenshot()));
			imgPath = mergeImg(udid);
			ExtentReport.failStackTrace("[FAIL] \n- behaviorMethod : " + behaviorMethod + "\n"
					+ "- behaviorElement : " + behaviorElement + "\n" 
					+ "- expectedElement : " + expectedElement + "\n"
					+ "- expectedOutput : " + expectedElement + "\n"
					+ "- twFilePath : " + expectedElement + "\n" );
			ExtentReport.screenshot("/Users/juneahn/git/m_blia/" + imgPath);
			extent.flush();
			
			Assert.assertTrue(false);
		}
		
		return true;
	}
	
	public static String mergeImg(String udid) throws Exception {
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
		
		return mergeImg;
	}
	
	
//	public static boolean assertion(String type, String actual, String expected) throws Exception {
//		try {
//			
//		} catch (TimeoutException | NoSuchElementException e) {
//			
//		}
//		
//		if(type.contains("m_assert")) {
//			Assert.assertEquals(actual, expected);
//		} 
//		
//		Assert.assertEquals(actual, expected);
//		
//		return true;
//		
//	}
	

}
