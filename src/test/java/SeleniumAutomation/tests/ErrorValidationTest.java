package SeleniumAutomation.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTest;
import SeleniumAutomation.pageobjects.CartCheckout;
import SeleniumAutomation.pageobjects.ProductCatalogue;
 
public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"errorcase"},retryAnalyzer=SeleniumAutomation.TestComponents.Retry.class	)  
	public void ErrorValidation()
	{
	String productName = ("ADIDAS ORIGINAL");			  
	
		landingPage.loginApplication("ram888@gmail.com", "Fluke@511");  
		String error = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email  password.",error); //we give wrong expect passs for report (or) removed between email and password
		
	} 
	@Test
	public void productErrorValidation() throws InterruptedException 
	 {
			String productName = ("ZARA COAT 3");				
			ProductCatalogue productCatalogue =landingPage.loginApplication("rampr@gmail.com", "Fluke@5011");	//we are giving diff name pass for overlap issue		
			List<WebElement> products = productCatalogue.getProductList();	
			productCatalogue.addProductToCart(productName);
			CartCheckout cartCheckout = productCatalogue.goToCartPage();   
			Boolean cartNameMatch = cartCheckout.verifyCartDisplay("zara coat 33"); //if we give wrong name it will give error thats why we used in error validation	
			Assert.assertFalse(cartNameMatch);
	 }
	
}
