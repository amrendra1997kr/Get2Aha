/**
 * 
 */
package com.pratian.automation.utility;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.pratian.automation.testclass.BaseTest;

/**
 * @author Amrendra
 *
 */
public class Listeners implements ITestListener{
	
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//To make thread safe if multiple class are running in parallel
	CaptureScreenshot cs = new CaptureScreenshot();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getTestClass().getName()+" :: "+result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Method"+result.getMethod().getMethodName()+"Successful</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>"+"Exception Occured, Click to see details:"+"</font></b></summary>"+
								exceptionMessage.replaceAll(",", "<br>")+"</details> \n");
		WebDriver driver = ((BaseTest)result.getInstance()).driver;
		try {
			String path = cs.takeScreenshot(driver , result.getMethod().getMethodName());
			extentTest.get().fail("<b><font color=red>"+"Screenshot of failure"+"</font></b>",
										MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			extentTest.get().fail("Test failed, cannot attach screenshot");
		}
		String logText = "<b>Test Method"+result.getMethod().getMethodName()+"Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method"+result.getMethod().getMethodName()+"Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		if(extent != null) {
			extent.flush();
		}
	}

}
