package org.LetsShop.pageobjects;

import java.util.List;

import org.LetsShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cart h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".subtotal .btn-primary")
	WebElement checkOut;
	
	public boolean VerifyProdctDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(a->a.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOut.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
}
