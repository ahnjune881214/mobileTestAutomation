package com.ahnlab.android.objects;

import java.util.List;

import com.ahnlab.setup.AndroidSetup;

public class AssertionCheck extends AndroidSetup {
	
	public static String assertionCheck(List<String> actualList) throws Exception {
		
		for (String results : actualList) {
			long saveTime = System.currentTimeMillis();
			long currTime = 0;
			int timer = 60000;
			
			while( currTime - saveTime < timer){
				currTime = System.currentTimeMillis();
				if (getDriver().getPageSource().contains(results)) {
					System.out.println("\t[ActualResult] Results Set : " + results + " | Result : Pass");
					break;
				} else if (currTime - saveTime < timer) {
				} else {
					System.out.println("\t[ActualResult] Results Set : " + results + " | Result : Fail");
				}
			}
		}
		
		return null;
	}
}
