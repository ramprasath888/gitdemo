package SeleniumAutomation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {
	
	public static ExtentReports getReportObject()  //give static means we can use this method everywhere without creating object
	{
		
		// there is two class Extent Reports and Extent Spark Reporter
		String path = System.getProperty("user.dir")+"//reports//index.html";
		
		// Extent Spark Reporter
		ExtentSparkReporter reports = new ExtentSparkReporter(path);
		reports.config().setReportName("Web Automation");
		reports.config().setDocumentTitle("Test Resluts ");
		
		//  Extent Reports
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reports);  //its expect tha object of ExtentSparkReporter 
		extent.setSystemInfo("Tester","Ramprasath");
		return extent;  ///we need to use this mehtod for that we returned it
		
		
	}
	  
	
	

}
