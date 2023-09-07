package SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumAutomation.pageobjects.LandingPage;

public class BaseTest {

	///Global properties we used herr like drivermethods, waitmechanism , maxmize likeeeee
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDrier() throws IOException
	{
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream("G:\\ram\\java\\SeleniumFramework\\src\\main\\java\\SeleniumAutomation\\resources\\GlobalData.properties");  ///properties required arg as input stream thats why we creating this class
		pro.load(file);   
		
	 //	String browserName =pro.getProperty("browser");
	// this is new code line for writting in cmd -------if its null it will run cmd  mentioned browser----- if its not null it will run from galobal data file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser");
		
		if(browserName.contains("chrome")) 
		{
			  //this step is used for headless mode atctivate it means it wont open chrome it will run backend
			ChromeOptions option =new ChromeOptions();
			if(browserName.contains("headless"))
				
			{	
			option.addArguments("headless");
			}
				 driver = new ChromeDriver(option);
				 driver.manage().window().setSize(new Dimension(1440,900)); //it will help to run full screen in headlesss mode
			
				}else if(browserName.equalsIgnoreCase("firefox"))
		{
		 driver = new FirefoxDriver(); //firefox browser 
			 
		}else if(browserName.equalsIgnoreCase("edge"))
		{
		//	 driver = new Edge();
		}
		
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	//this is important intervoew question how to convert external  file 
	//this method for conveting json formet to file as a proudctname ,username, password
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException 
	{
		//read file json into string
	String json =FileUtils.readFileToString(new File(filepath) ,StandardCharsets.UTF_8); 
		
	//now convert string into HashMap using jackson databind
	ObjectMapper mapper =new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>(){});
	return data;
	
	}
	
	//this method used to after failure it will take screenshot 
	
	public String getScreenShot(String testCaseName ,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png"); //we creating file class for fileutils it expect as file only 
		FileUtils.copyFile(src, file);
	//	return file; //it will retrun report as file
		return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";  //this will return report as path of file
		
	}
	
		
		@BeforeMethod (alwaysRun=true) //always run why we give means when we want to run only particulart methods it need to wrok for the we use this attributes annotation
		public LandingPage launchApplication() throws IOException
		{
			driver =initializeDrier();

			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
		
		@AfterMethod (alwaysRun=true)//always run why we give means when we want to run only particulart methods it need to wrok for the we use this attributes annotation
		public void closeBrowser()
		{
			driver.close();
		}
}
