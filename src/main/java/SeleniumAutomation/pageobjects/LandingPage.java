package SeleniumAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	//this page object method for login page
	WebDriver driver;
	
public LandingPage (WebDriver driver)  ///constructor
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	
		//WebElement userEmail =driver.findElement(By.cssSelector("input#userEmail"));  //this is one methods of webElement
//this is another methods of defining webelement like driver.find
	@FindBy(css="input#userEmail")
	WebElement userEmail;

	@FindBy(css="#userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	public void goTo()  //whe we call this home page will be loaded thats also we use page object
	{
		driver.get("https://www.rahulshettyacademy.com/client");

	}
	public ProductCatalogue loginApplication(String email , String password)
	{
		
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		//instead of creating object in every page object class lets creates here
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	//click on cart icon we used here bec
	
	
	//if give incorrext pas or name 
	@FindBy(css="div[aria-label='Incorrect email or password.")  //class=ng-tns-c4-27 toast-message ng-star-inserted---error pop up
	WebElement errorMsg;
	public String getErrorMessage()
	{
		waitForWebElementAppear(errorMsg);
	     return	errorMsg.getText();
	
	}
	

}
