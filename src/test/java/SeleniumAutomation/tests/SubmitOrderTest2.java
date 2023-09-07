package SeleniumAutomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTest;
import SeleniumAutomation.pageobjects.CartCheckout;
import SeleniumAutomation.pageobjects.ConfirmationMsg;
import SeleniumAutomation.pageobjects.OrderPage;
import SeleniumAutomation.pageobjects.PlaceOrderCheckout;
import SeleniumAutomation.pageobjects.ProductCatalogue;
 
public class SubmitOrderTest2 extends BaseTest {
//	String productName = ("ADIDAS ORIGINAL");	//if we add more then one test we need this value for that we declare here globally
	
	
	@Test(dataProvider="getData",groups="purchaseorder") 
		public void SubmitOrder( HashMap<String, String> input) throws IOException, InterruptedException
		{
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"),input.get("password"));  //instead of creating object in every page object class lets creates here
		List<WebElement> products = productCatalogue.getProductList();	
		productCatalogue.addProductToCart(input.get("product"));
		CartCheckout cartCheckout = productCatalogue.goToCartPage();   //lets create object for cart checkout in gotocarpage because of its present inside of its
		Boolean cartNameMatch = cartCheckout.verifyCartDisplay(input.get("product"));
		Assert.assertTrue(cartNameMatch);
		PlaceOrderCheckout placeOrderCheckOut = cartCheckout.goTOCheckout();   //lets create object for placeorder in goTOCheckout because of its present inside of its
		placeOrderCheckOut.goToCountry();
		ConfirmationMsg confirmationMsg =placeOrderCheckOut.goToPlaceOrder();
		String confirmMsg= confirmationMsg.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); 
		System.out.println(confirmMsg); 
		 
		} 
		
		
		
		
	//verify order display is shown added product name  in orderpage for that we create this methods
		
		@Test (dependsOnMethods= {"SubmitOrder"})  //we need verify order page only after click product to cart for that we use dependency
		public void OrderHistoryTest() 
		{
			ProductCatalogue productCatalogue =landingPage.loginApplication("rampr@gmail.com", "Fluke@5011"); 
			OrderPage orderPage =productCatalogue.goToOrderPage();
			Assert.assertTrue(orderPage.verifyProductOrderDisplay("zara coat 3"));  //application issue order page is not product added
		}
		
		
	//this is 1st type of data send methods
		
//		@DataProvider          //dataprovider is used to send multiple data
//		public Object[][] getData()
//		{
//			
//			//we created two curly bracket for giver two value we need to pass value in inside that			
//		Object[][] object = new Object[][ ] { {"anshika@gmail.com","Iamking@000"},{"rampr@gmail.com","Fluke@5011"}};  
//		return object;
//		}
		
	//this is 2nd type of data send methods
//		@DataProvider  //data provider it also return hashmap
//		public  Object[][] getData()
//		{
//			//HashMap is another type of sending multiple value it expect two string value
//			HashMap<String,String> map = new HashMap<String,String>();  
//			map.put("email","anshika@gmail.com");			
//			map.put("password","Iamking@000");
//			map.put("product","ADIDAS ORIGINAL");
//			HashMap<String,String> map1 = new HashMap<String,String>();  
//			map1.put("email","rampr@gmail.com");
//			map1.put("password","Fluke@5011");
//			map1.put("product", "ZARA COAT 3");
//			return new  Object[][] {{map},{map1}};
//		}
		
		
		
		
		//now 3rd type json file to get the data 
		//now jason formet to hashmap using file utility
		@DataProvider
		public Object[][] getData() throws IOException
		{
			
			//we can directly call this methods because we write it in base test instead of creating new object of that class(dataReader)
			
			List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumAutomation\\data\\PurchaseOrder.json");
			
			return new  Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
	
//
}
