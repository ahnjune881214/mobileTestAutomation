package com.ahnlab.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesGetter {
	public static String getter(String id) throws Exception {
		String resource = "mblia.properties";
		Properties properties = new Properties();
		
		InputStream input = null;
		input = new FileInputStream(resource);
		properties.load(input);

		return properties.getProperty(id);
	}
}
