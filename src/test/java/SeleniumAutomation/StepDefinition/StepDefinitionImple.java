package SeleniumAutomation.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumAutomation.TestComponents.BaseTest;
import SeleniumAutomation.pageobjects.CartCheckout;
import SeleniumAutomation.pageobjects.ConfirmationMsg;
import SeleniumAutomation.pageobjects.PlaceOrderCheckout;
import SeleniumAutomation.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImple extends BaseTest {
	
	public ProductCatalogue productCatalogue;
	public CartCheckout cartCheckout;
	public PlaceOrderCheckout placeOrderCheckOut;
	public ConfirmationMsg confirmationMsg;
// Given landed on Ecommerce page
	@Given("landed on Ecommerce page")
	public void Landed_on_Ecommerce_page() throws IOException
	{
		
		
	launchApplication();
	}
	
//	Given logged in with username <userName> and password <password>
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_with_username_and_password(String userName,String password)
	{
		 productCatalogue =landingPage.loginApplication(userName,password); 
	}
	
// When addd to product <productName> to cart
	@When("^add to product (.+) to cart$")
	public void Add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();	
		productCatalogue.addProductToCart(productName);
		
	}
	
// And  check out <productName> and submit the order
	@And("^checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName)
	{
		
		 cartCheckout = productCatalogue.goToCartPage();   
		Boolean cartNameMatch = cartCheckout.verifyCartDisplay(productName);
		Assert.assertTrue(cartNameMatch);
		 placeOrderCheckOut = cartCheckout.goTOCheckout(); 
		placeOrderCheckOut.goToCountry();
		 confirmationMsg =placeOrderCheckOut.goToPlaceOrder();
		
	}

//Then "THANKYOU FOR THE ORDER." message is diplayed in confirmation page
	@Then("{string} message is displayed in confirmationpage")
	public void Message_displayed_in_confirmationpage(String string)
	{		
		
		String confirmMsg= confirmationMsg.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string)); 
		driver.close();
		
	}
	
//Then "Incorrect email  password." message is displayed
	@Then("^\"([^\"]*)\"message is displayed$")
	public void Error_message_displayed(String string1)
	{
		
		Assert.assertEquals(string1,landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
	
	
	
	
	

}
