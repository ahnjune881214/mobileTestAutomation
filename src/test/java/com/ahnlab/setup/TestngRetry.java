package com.ahnlab.setup;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestngRetry implements IRetryAnalyzer {
	private int retryCount = 1;
	private static int maxRetryCount;
	static {
		maxRetryCount = 2;
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			System.out.println("[Retry] Count : " + retryCount);
			
			AndroidSetup.dr.get().closeApp();
			AndroidSetup.dr.get().launchApp();
			
			retryCount++;
			return true;
		}
		return false;
	}
}
