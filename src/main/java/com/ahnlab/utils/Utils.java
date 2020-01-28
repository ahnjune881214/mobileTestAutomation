package com.ahnlab.utils;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
	public static String nameingConvention() throws Exception {
	    return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getElapsedTimeSting(long startTime, String device) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		String elpsedTimeString = (elapsedTime / 1000) + "." + (elapsedTime % 1000);
		System.out.println("[DONE] Test Automation " + device + " (Total " + elpsedTimeString + " sec)");
		return elpsedTimeString;
	}
	
    public static String StackTraceToString(Exception e) {
        String result = e.toString() + "\n";
        StackTraceElement[] trace = e.getStackTrace();
        
        for (int i = 0;i < trace.length; i++) {
            result += trace[i].toString() + "\n";
        }
        return result;
    }
}
