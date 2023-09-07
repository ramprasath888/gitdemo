package SeleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

//this page object for checkout page
public class CartCheckout extends AbstractComponent {

	WebDriver driver;
	public CartCheckout (WebDriver driver)  ///constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cart h3")
	List<WebElement> cartproduct;
	public Boolean verifyCartDisplay(String productName)
	{
		
		Boolean match = cartproduct.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(productName)); //its rutrun boolean value that why create class like
		return match;
	}
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOut;
	public PlaceOrderCheckout goTOCheckout()
	{
		checkOut.click();
		PlaceOrderCheckout placeOrderCheckOut = new PlaceOrderCheckout(driver);
		return placeOrderCheckOut;
		
	}

	
	
}
