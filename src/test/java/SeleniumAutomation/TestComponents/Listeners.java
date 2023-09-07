package SeleniumAutomation.TestComponents;

import org.testng.ITestListener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumAutomation.resources.ExtentReporterTestNG;


public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent=ExtentReporterTestNG.getReportObject(); //becauset its returinig extent we need to use that classs in this methods for that we use this step (classnaem.method name)
	//thread local used for when we run test parallel it cause error so when we use thread local it wont return error
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	
		//it will start executing test so we write our mandatoru step of extent report
		test =extent.createTest(result.getMethod().getMethodName());//(path) inside this we need to give the pathe it means results have it andalso getmethodsname 
		
		extentTest.set(test);   //this is test object set to thread local 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// this is used to run after test passed
		extentTest.get().log(Status.PASS,"test passed");  
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		// this is run after test is failed
	//	once set next time we used in another method just get to use this methods
		extentTest.get().fail(result.getThrowable());  //throwable is used to get error message in report thats why we used here 
		
		
		
		// this step is used to get driver value to here
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1)  { //this is genaric exception
			e1.printStackTrace();
		}
			
			
			
		String filepath =null;
		try {
			 filepath =getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// this method will execute last so we can write extent report close method so that we will get report
		extent.flush();
		
	}
	
	
	
	
	

}
