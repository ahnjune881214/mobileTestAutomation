package com.ahnlab.android.objects;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellObj {
	public static String execCommand(String cmd) throws InterruptedException {
		StringBuffer buffer;
		Process process;
		BufferedReader buff;
		StringBuffer readBuffer;
		
		try {
			buffer = new StringBuffer();
			buffer.append("cmd.exe ");
			buffer.append("/c ");
			buffer.append(cmd);
			
			process = Runtime.getRuntime().exec(buffer.toString());
			buff = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line = null;
			readBuffer = new StringBuffer();
			
			while((line = buff.readLine()) != null ) {
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			return readBuffer.toString();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		Thread.sleep(3000);
		return null;
	}
}
