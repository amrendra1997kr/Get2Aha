/**
 * 
 */
package com.pratian.automation.pageobjects;

import org.openqa.selenium.WebDriver;

/**
 * @author Amrendra
 *
 */
public class BasePage {
	private WebDriver driver;
	public BasePage(WebDriver driver)
	{
		this.driver = driver;// calling web driver
	}

}
