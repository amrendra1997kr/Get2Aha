/**
 * 
 */
package com.pratian.automation.testclass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.pratian.automation.utility.PropertyManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Amrendra
 *
 */
public class BaseTest {
	public WebDriver driver;

	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) throws IOException
	{
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver();
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();//Maximize the window
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(PropertyManager.getProperty("url.app"));// accessing the url for testing	
	}



	@AfterClass
	public void tearDown()
	{
		driver.close();//closing the latest opened driver
		driver.quit();//closing all opened driver
	}

}
