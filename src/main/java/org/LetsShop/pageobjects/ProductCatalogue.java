package org.LetsShop.pageobjects;

import java.util.List;

import org.LetsShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".mb-3")
	List<WebElement> productnames;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		WaitForElementToAppear(productsBy);
		return productnames;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement DesiredProduct = getProductList().stream().filter(a->a.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return DesiredProduct;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement DesiredProduct = getProductByName(productName);
		DesiredProduct.findElement(addToCart).click();
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisappear(spinner);
	}
	
}
