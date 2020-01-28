package com.ahnlab.android.objects.behavior;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;

public class EtcObj extends AndroidSetup {
	
	// sleep 
	public static boolean sleep(int sleepTime) throws Exception{
		getDriver().manage().timeouts().implicitlyWait(sleepTime, TimeUnit.SECONDS);
		
		return true;
	}
	
	// scroll
	public static boolean scroll(String behaviorMethod, String scrollValue) {
		try {
			if (behaviorMethod.contains("scroll_text")) {
				getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
			            ".scrollIntoView(new UiSelector().textContains(\"" + scrollValue + "\").instance(0));");
				
			} else if (behaviorMethod.contains("scroll_resourceid")) {
				getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
			            ".scrollIntoView(new UiSelector().resourceIdMatches(\".*" + scrollValue + "\").instance(0));");
			}
			
		} catch (TimeoutException | NoSuchElementException e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] \n" + exceptionLog);
			// Assertion
			Assert.assertTrue(false);
		} catch (Exception e) {
			String exceptionLog = Utils.StackTraceToString(e);
			System.out.println("[StackTrace] \n" + exceptionLog);
			// Assertion
			Assert.assertTrue(false);
		}
		return true;
	}
	
}
