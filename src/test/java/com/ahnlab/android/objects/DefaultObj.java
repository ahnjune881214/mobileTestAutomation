package com.ahnlab.android.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.setup.AndroidSetup;

import io.appium.java_client.MobileBy.ByAccessibilityId;

public class DefaultObj extends AndroidSetup{
	// Wait
	public void wait(String findBy, String elementId, String textToAppear) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 150);
		
		if (findBy.matches("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
		} else if (findBy.matches("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementId)));
		} else if (findBy.matches("textToId")) { 
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(elementId), textToAppear));
		} else if (findBy.matches("textToXpath")) {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(elementId), textToAppear));
		} else if (findBy.matches("accessibilityId")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(elementId)));
		} 
	}
}

