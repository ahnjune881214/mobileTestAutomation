package com.ahnlab.android.objects.behavior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdObj {
    public static void main(String[] args) throws Exception {
    	String cmd = "/Users/juneahn/Library/Android/sdk/platform-tools/adb reboot"; 
        Process p = Runtime.getRuntime().exec(cmd);
        Thread.sleep(30000);
    	
//        try {
////        	String ADB=System.getenv("ANDROID_HOME");
////        	String[] cmd = { "/bin/bash, -c , ssh " + "adb devices" };
//        	String cmd = null;
//        	Process p;
//        	
////        	String cmd = "/Users/juneahn/Library/Android/sdk/platform-tools/adb reboot"; 
////            Process p = Runtime.getRuntime().exec(cmd);
//            
//            String line = null;
//            String str = null;
//            Thread.sleep(10000);
//            while (true) {
//            	cmd = "/Users/juneahn/Library/Android/sdk/platform-tools/adb devices"; 
//                p = Runtime.getRuntime().exec(cmd);
//                BufferedReader in = new BufferedReader(
//                                    new InputStreamReader(p.getInputStream()));
//            	Thread.sleep(2000);
//            	while ((line = in.readLine()) != null) {
//            		
//            		str = line;
//            		System.out.println("a : " + str);
//            		if (str.contains("710KPLC0272848")) {
//            			System.out.print("br");
//                		break;
//            		}         	
//            		
//            	}
//            	break;
//            	
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
