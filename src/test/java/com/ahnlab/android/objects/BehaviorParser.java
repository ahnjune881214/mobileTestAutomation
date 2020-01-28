package com.ahnlab.android.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.setup.AndroidSetup;

public class BehaviorParser extends AndroidSetup {
	
	static WebDriverWait wait = new WebDriverWait(getDriver(), 20);
	static Screenshot screenshot = new Screenshot();
	
	public static void mobileClickParser(String findBy, String elementId, String actualResults, String fullScrennshotImgFilePath, String elementScreenshotImgFilepath) throws Exception {
//		findBy = findBy.toLowerCase();
		
		if (findBy.matches("clickxpath") || findBy.matches("ClickXpath") || findBy.matches("Clickxpath") || findBy.matches("CLICKXPATH")) {
			findBy = "xpath";
			
		} else if (findBy.matches("mobileclickid")) {
			findBy = "id";
			System.out.println("aaaa : " );
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
			screenshot.screenshot("id", elementId, fullScrennshotImgFilePath, elementScreenshotImgFilepath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId))).click();
//			getDriver().findElement(By.id(elementId)).click();
		
		} else if (findBy.matches("mobileclickacc") || findBy.matches("MobileClickAcc")){
			findBy = "acc";
			// Optional 
		}
	}
}
