package org.mdk.jdental.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class ManageProperties {
	private static ManageProperties INSTANCE;

	public static ManageProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManageProperties();
		}
		return INSTANCE;
	}

	public String read(String prop) {
		// Read properties file.
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(Constants.PROPERTIES_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(prop);
	}
	
	public String read(String propPath, String prop) {
		// Read properties file.
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(prop);
	}
	
	public Properties setPropFile(String filename, String date){
		Properties properties = new Properties();
		try { 
			properties.setProperty("date", date);
			properties.store(new FileOutputStream(filename), null);
			} catch (IOException e) { } 
		return properties;
	}
}
