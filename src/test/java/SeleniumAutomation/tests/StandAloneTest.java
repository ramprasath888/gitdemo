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

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		String producName = ("ADIDAS ORIGINAL");
		driver.get("https://www.rahulshettyacademy.com/client");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("input#userEmail")).sendKeys("rampr@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Fluke@5011"); 
		driver.findElement(By.id("login")).click();
		//explicit wait lets write here
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3)); 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3"))); //this will wait for product to load for example safety we write here

		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector(".card-body b")).getText().equals(producName)).findFirst().orElse(null);	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//after click add to cart its give some invisible window and then pop msg show after that invisible window gone then prodcut added to cart
		//next time click cart we have to explicitly wait for that moment so use explicit wait methods

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));  //this is using locater
	//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));  //this is using webelement
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); //lets write regulart css
		

		List<WebElement> cartproduct= driver.findElements(By.cssSelector(".cart h3"));
		
		Boolean cartName = cartproduct.stream().anyMatch(carts->carts.getText().equalsIgnoreCase(producName)); //its rutrun boolean value that why create class like
		Assert.assertTrue(cartName);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Checkout']")));
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		
		driver.findElement(By.xpath("//div[@class='actions']/a[@class='btnn action__submit ng-star-inserted']")).click(); //we need no move coursor then only it clicked
		
	System.out.println(driver.findElement(By.xpath("//div[@class='actions']/a[@class='btnn action__submit ng-star-inserted']")).isEnabled());
	
		String output =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(output, "THANKYOU FOR THE ORDER."); 
		System.out.println(output);

		
		

		
		
		
		
		 

	}

}
