package com.ahnlab.android.objects.behavior;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ahnlab.android.objects.ScreenshotObj;
import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;

public class SendKeyObj extends AndroidSetup{
	static WebDriverWait wait = new WebDriverWait(getDriver(), 20);
	
	public static boolean sendkey(String behaviorMethod, String behaviorElement, String input) throws Exception {
		try {
			MobileElement sendEle = null;
			if (behaviorMethod.contains("m_sendkey_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				// Screenshot
				ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				sendEle = (MobileElement) getDriver().findElementById(behaviorElement);
				sendEle.sendKeys(input);
				
			} else if (behaviorMethod.contains("m_sendkey_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				// Screenshot
				ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				sendEle = (MobileElement) getDriver().findElementByXPath(behaviorElement);
				sendEle.sendKeys(input);
				
			} else if (behaviorMethod.contains("m_sendkey_accid")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(behaviorElement)));
				// Screenshot
				ScreenshotObj.screenshot(behaviorMethod, behaviorElement);
				
				sendEle = (MobileElement) getDriver().findElementByAccessibilityId(behaviorElement);
				sendEle.sendKeys(input);
				
			} else {

				Assert.assertTrue(false);
				
			}
			
		} catch (TimeoutException | NoSuchElementException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] \n" + exceptionLog);
			
			Assert.assertTrue(false);
			
		} catch (Exception e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] \n" + exceptionLog);
			
			Assert.assertTrue(false);
		}
		
		return true;
	}

}
