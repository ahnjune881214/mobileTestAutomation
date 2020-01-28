package com.ahnlab.android.objects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.setup.AndroidSetup;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.qameta.allure.Step;

public class BehaviorObj extends AndroidSetup{
	static DefaultObj defaultObj = new DefaultObj();
	static Screenshot screenshot = new Screenshot();
	
	static WebDriverWait wait = new WebDriverWait(getDriver(), 20);
	static AppiumObj obj = new AppiumObj();
	
	
	@Step("findby : {0}, elementId : {1}")
	public static void mobileClickBehavior(String findby, String elementId, String actualResult, String fullScrennshotImgFilePath, String elementScreenshotImgFIlePath) throws Exception {
		
		List<String> actualList = Arrays.asList(actualResult.replaceAll(", ", ",").split(","));
		
		if (findby.matches("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementId)));
			screenshot.screenshot("xpath", elementId, fullScrennshotImgFilePath, elementScreenshotImgFIlePath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementId))).click();
			System.out.println("\t[ClickXpath] Selector : " + elementId);
			
		} else if (findby.matches("id")) {
			System.out.println("123123123");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
			screenshot.screenshot("id", elementId, fullScrennshotImgFilePath, elementScreenshotImgFIlePath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId))).click();
			System.out.println("\t[ClickId] Selector : " + elementId);
			
			
		} else if (findby.matches("acc")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(elementId)));
			screenshot.screenshot("accessibilityId", elementId, fullScrennshotImgFilePath, elementScreenshotImgFIlePath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(elementId))).click();
			System.out.println("\t[ClickAcc] Selector : " + elementId);
		}
		 
		AssertionCheck.assertionCheck(actualList);
	}
}
