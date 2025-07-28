package org.LetsShop.test;

import java.time.Duration;
import java.util.List;

import org.LetsShop.pageobjects.LandingPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		String product = "IPHONE 13 PRO";
		ChromeDriver driver  = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingpage = new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client");
		
		/*Login*/
		driver.findElement(By.id("userEmail")).sendKeys("SC@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@123");
		driver.findElement(By.id("login")).click();
		
		/*Using ExpliciteWait*/
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> productNames = driver.findElements(By.cssSelector(".mb-3"));
		WebElement DesiredProduct = productNames.stream().filter(a->a.findElement(By.cssSelector("b")).getText().equals(product)).findFirst().orElse(null);
		DesiredProduct.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		/*****************************************************************************************/
		
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cartProducts.stream().anyMatch(a->a.getText().equalsIgnoreCase(product));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".subtotal .btn-primary")).click();
		driver.findElement(By.cssSelector(".form-group .input")).sendKeys("india");
		//.list-group-item .ng-star-inserted
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		
		List<WebElement> countryes = driver.findElements(By.cssSelector(".list-group-item .ng-star-inserted"));
		
		for (WebElement country : countryes) {
			if(country.getText().equalsIgnoreCase("India")){
				country.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
}
