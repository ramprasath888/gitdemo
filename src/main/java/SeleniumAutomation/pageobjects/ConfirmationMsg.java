package SeleniumAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

//this page object for after placeorder get confirmation msg
public class ConfirmationMsg extends AbstractComponent {

	
	
	WebDriver driver;
	public ConfirmationMsg (WebDriver driver)  ///constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement captureMsg;
	public String getConfirmationMsg()
	{
				
				return captureMsg.getText();
	}
	
}
