package com.ahnlab.android.objects.behavior;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.ahnlab.android.objects.assertion.AssertTw;
import com.ahnlab.setup.AndroidSetup;

public class AssertObj extends AndroidSetup {
	
	public static boolean assertCheckSize2(String expectedMethod, String expectedOutput) throws Exception {
			if (expectedMethod.toLowerCase().matches("m_assert")) {
				
				Thread.sleep(1000);
				long start = System.currentTimeMillis();
				String actualResults = null;
				while (true) {
					actualResults = getDriver().getPageSource().toString();
					if (actualResults.contains(expectedOutput)) {
						break;
						
					} else if (System.currentTimeMillis()-start > 50000) {
						// Assertion
						System.out.println("[FAIL] ActualResults와 Expectedoutput이 맞지 않습니다.");
						System.out.println("[ActualResults] " + actualResults);
						System.out.println("[ExpectedOutput] " + expectedOutput);
						Assert.assertTrue(false);
						break;
						
					} 
				}
				
			} 
		
		return true;
	}
	
	public static boolean assertCheckSize3(String expectedMethod, String expectedElement, String expectedOutput, String twFilePath) throws Exception {
		String actualResult = null;
		
		if (expectedMethod.toLowerCase().contains("m_assert_id_txt")) {
			actualResult = getDriver().findElement(By.id(expectedElement)).getText();
			Thread.sleep(1000);
			long start = System.currentTimeMillis();
			while (true) {
				if (actualResult.contains(expectedOutput)) {
					break;
				} else if (System.currentTimeMillis()-start > 50000) {
					// Assertion
					System.out.println("[FAIL] ActualResults와 Expectedoutput이 맞지 않습니다.");
					System.out.println("[ActualResults] " + actualResult);
					System.out.println("[ExpectedOutput] " + expectedOutput);
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
				} else if (System.currentTimeMillis()-start > 50000) {
					// Assertion
					System.out.println("[FAIL] ActualResults와 Expectedoutput이 맞지 않습니다.");
					System.out.println("[ActualResults] " + actualResult);
					System.out.println("[ExpectedOutput] " + expectedOutput);
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
		return true;
	}
}
