package SeleniumAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

//this page object class for place order page
public class PlaceOrderCheckout extends AbstractComponent {
	
	WebDriver driver;
	public PlaceOrderCheckout (WebDriver driver)  ///constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countrybox;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement country;
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

	By waitForSuggestion =By.cssSelector(".ta-results"); //explicit wait for auto suggestion
	
	public void goToCountry()
	{
		
		Actions a = new Actions(driver);
		
		a.sendKeys(countrybox,"india").build().perform(); 
		
		waitForElementAppear(waitForSuggestion);

		country.click();
	}
	
	
	@FindBy(xpath="//div[@class='actions']/a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;

	By waitForOrdericon =By.xpath("//div[@class='actions']/a[@class='btnn action__submit ng-star-inserted']");
	
	
//	driver.findElement(By.xpath("//div[@class='actions']/a[@class='btnn action__submit ng-star-inserted']")).click();

	public ConfirmationMsg goToPlaceOrder()
	{
		//	 Actions a = new Actions(driver);   //i try to move mouse to placeorder icon thats also not working
	//		 a.moveToElement(placeOrder).build().perform();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,200)");
		
		waitForElementAppear(waitForOrdericon); 
		System.out.println(placeOrder.isEnabled());
		placeOrder.click();
		return new ConfirmationMsg(driver);
	}
}
