package com.ahnlab.android.objects.behavior;

import com.ahnlab.setup.AndroidSetup;

public class InitializeObj extends AndroidSetup{

	public static void initialization() throws Exception {
	    // test 수행 관련
		stepNo = 0;
		    
		tcBehaviorMethod = null;
		tcBehaviorElement = null;
		tcExpectedMethod = null;
		tcExpectedElement = null;
		tcExpectedOutput = null;
		tcInput = null;
		tcScrollValue = null;
		tcSleepTime = 0;
		    
		results = false;
		stackTrace = null;
		    
		eleImg = null;
		fullImg = null;
		
		// merge img table 관
		mergeImg = null;
	}
	
}
