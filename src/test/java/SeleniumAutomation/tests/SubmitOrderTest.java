package SeleniumAutomation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumAutomation.pageobjects.CartCheckout;
import SeleniumAutomation.pageobjects.ConfirmationMsg;
import SeleniumAutomation.pageobjects.LandingPage;
import SeleniumAutomation.pageobjects.PlaceOrderCheckout;
import SeleniumAutomation.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = ("ADIDAS ORIGINAL");	
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3)); 

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
	//lets create object for productcatalogue in landingpage because of its present inside of its
		ProductCatalogue productCatalogue =landingPage.loginApplication("rampr@gmail.com", "Fluke@5011");  //instead of creating object in every page object class lets creates here
				
	//	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();	
		productCatalogue.addProductToCart(productName);
		CartCheckout cartCheckout = productCatalogue.goToCartPage();   //lets create object for cart checkout in gotocarpage because of its present inside of its
	
	//	CartCheckout cartCheckout = new CartCheckout(driver);
		Boolean cartNameMatch = cartCheckout.verifyCartDisplay(productName);
		Assert.assertTrue(cartNameMatch);
		PlaceOrderCheckout placeOrderCheckOut = cartCheckout.goTOCheckout();   //lets create object for placeorder in goTOCheckout because of its present inside of its
		placeOrderCheckOut.goToCountry();
		
		ConfirmationMsg confirmationMsg =placeOrderCheckOut.goToPlaceOrder();
		String confirmMsg= confirmationMsg.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMsg);
		driver.close();
	 

	}

}
