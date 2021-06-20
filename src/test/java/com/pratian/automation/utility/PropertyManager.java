/**
 * 
 */
package com.pratian.automation.utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Amrendra
 *
 */
public final class PropertyManager {
	
	public static String getProperty(String propName) throws IOException
	{
		FileReader fileReader = new FileReader("App.properties");
		Properties properties = new Properties();
		properties.load(fileReader);
		return properties.getProperty(propName);
	}

}
