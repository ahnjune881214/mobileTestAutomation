package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesRead {
	public static void main(String[] args) throws IOException {
		String resource = "mblia.properties";
		Properties properties = new Properties();
		
		InputStream input = null;
		input = new FileInputStream(resource);
		
		properties.load(input);
		
		System.out.println("1 : " + properties.getProperty("fullScrennshotImgFilePath"));
		System.out.println("2 : " + properties.getProperty("elementScreenshotImgFilePath"));
		System.out.println("3 : " + properties.getProperty("failingScreenshotImgFilePath"));
		System.out.println("4 : " + properties.getProperty("mergeScreenshotImgFilePath"));
		System.out.println("5 : " + properties.getProperty("twFilePath"));
	}
}
