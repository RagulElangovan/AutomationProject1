package testComponents;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent;
	ExtentTest test;
	ThreadLocal<ExtentTest > extentTest = new ThreadLocal(); // thread Safe while parallel run
	


	@Override
	public void onTestStart(ITestResult result) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmmss");
		LocalDateTime now = LocalDateTime.now();
		String date = ofPattern.format(now);
		test = extent.createTest(result.getMethod().getMethodName()+" "+date);
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + "Test Passed");
		 WebDriver driver = null;

		try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
		String ssPath = null;
		try {
			ssPath = screenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;

		try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } 
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
		String ssPath = null;
		try {
			ssPath = screenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		extent = extentreport();
	 // sets unique thread id
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
