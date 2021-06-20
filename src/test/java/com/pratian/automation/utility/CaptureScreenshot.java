/**
 * 
 */
package com.pratian.automation.utility;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Amrendra
 *
 */
public class CaptureScreenshot {
	
	public String takeScreenshot(WebDriver driver,String methodName) throws Exception {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir")+"/Screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(path));
		return path;
	}

	private static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName +"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return fileName;
	}

}
