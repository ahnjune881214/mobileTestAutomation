package com.ahnlab.android.transtrator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.ahnlab.setup.AndroidSetup;

public class ReadRobot extends AndroidSetup{
	static int start = 0;
	static int end = 0;
	
	public static ArrayList<String> readRobotScripts(String tcPath) throws Exception {
		ArrayList<String> tcList = new ArrayList<>();
		FileInputStream fstream = new FileInputStream(tcPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		int strCount = 0;
		
		start = startPoint(tcPath);
		end = endPoint(tcPath);
		
		while ((strLine = br.readLine()) != null) {
			if(strCount > start + 1 && strCount < end - 1) {
				tcList.add(strLine);
			}
			strCount++;
		}
		br.close();
		
		return tcList;
	}
	
	public static int startPoint(String tcPath) throws Exception {
		FileInputStream fstream = new FileInputStream(tcPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		int strCount = 0;
		
		while ((strLine = br.readLine()) != null) {
			if (strLine.contains("*** Test Cases ***")) {
				start = strCount;
			} 
			strCount++;
		}
		
		return start;
	}
	
	public static int endPoint(String tcPath) throws Exception {
		FileInputStream fstream = new FileInputStream(tcPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		int strCount = 0;
		
		while ((strLine = br.readLine()) != null) {
			if (strLine.contains("*** Keywords ***")) {
				end = strCount;
			} 
			strCount++;
		}

		return end;
	}
	
}
