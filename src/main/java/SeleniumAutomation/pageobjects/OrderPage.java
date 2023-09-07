package SeleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

//this page object for checkout page
public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage (WebDriver driver)  ///constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProductName;
	public Boolean verifyProductOrderDisplay(String productName)
	{
		
		Boolean match = orderProductName.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(productName)); //its rutrun boolean value that why create class like
		return match;
	
	}

	
	
}
