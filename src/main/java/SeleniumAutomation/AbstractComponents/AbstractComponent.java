package SeleniumAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumAutomation.pageobjects.CartCheckout;
import SeleniumAutomation.pageobjects.OrderPage;

public class AbstractComponent {

	//this method is to reuseable in every page objcet class
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {  //super constructor
		
		this.driver=driver; 
		PageFactory.initElements(driver, this); 
	}

	public void waitForElementAppear(By findBy)   ///its start for locator so By variable
	{ 
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); 
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy)); //this will wait for product to load for example safety we write here
	}
	
	//same wait using WEbElmetn locateter typer

	public void waitForWebElementAppear(WebElement findBy)   
	{ 
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); 
	wait.until(ExpectedConditions.visibilityOf(findBy)); //this will wait for product to load for example safety we write here
	}
	
	
	
	public void waitForElementInvisibilityDisappear(WebElement ele) throws InterruptedException  //this methods is start from driver. so use webElment variable
	{
		
		Thread.sleep(4000);//it will take long time to click cart icon for that we use this
		
	//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3)); 

	//	wait.until(ExpectedConditions.invisibilityOf(ele)); 

	}
	
	//click on cart icon we used here because its common for all page so we declared here
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement clickcart;
	
	public CartCheckout goToCartPage() 
	{
		clickcart.click();
		//lets create object for cartcheckout because of its present inside of its
		CartCheckout cartCheckout = new CartCheckout(driver); 
		return cartCheckout;
	}
	
	//click on order icon 
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	
	
}
