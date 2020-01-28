package com.ahnlab.android.objects.behavior;

import com.ahnlab.setup.AndroidSetup;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HwButtonObj extends AndroidSetup{

	public static boolean hwBack() {
		getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		
		return true;
	}
	
	public static boolean hwHome() {
		getDriver().pressKey(new KeyEvent(AndroidKey.HOME));
		
		return true;
	}
	
	
	
	
}
