package SeleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

//this page objcet is for productlist	
	WebDriver driver;
	
public ProductCatalogue (WebDriver driver)  ///constructor
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".mb-3");

	public List<WebElement> getProductList()
	{
		//before return products we need to wait for some min to load product list for that
		waitForElementAppear(productBy);
		return products;
	}
	
	public WebElement getProducName(String productName)
	{
		WebElement prod= getProductList().stream().filter(product->product.findElement(By.cssSelector(".card-body b")).getText().equals(productName)).findFirst().orElse(null);	
		return prod;
	}
	
		
	//driver.finElement(By.cssSelector(".ng-animating"));   ///this is invisiblilty so use pagefactrory
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	By cart =By.cssSelector(".card-body button:last-of-type");
	By infoMsg =By.cssSelector("#toast-container");
	
	
	public void addProductToCart(String productName) throws InterruptedException 
	{
		

		 WebElement prod= getProducName(productName);
		 prod.findElement(cart).click();
		 waitForElementAppear(infoMsg);
		 waitForElementInvisibilityDisappear(animation);
	}
	
	
	
}



