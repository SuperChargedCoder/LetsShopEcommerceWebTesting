package org.LetsShop.pageobjects;

import java.util.List;

import org.LetsShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= ".form-group .input")
	WebElement selectCountry;
	
	@FindBy(css= ".list-group-item .ng-star-inserted")
	List<WebElement> countryList;
	
	@FindBy(css= ".action__submit")
	WebElement sumitOrder;
	
	By countryTable = By.cssSelector(".list-group");
	
	public void selectCountry(String country) {
		selectCountry.sendKeys(country);
		WaitForElementToAppear(countryTable);
		List<WebElement> countryes = getCountryList();
		for (WebElement c : countryes) {
			if(c.getText().equalsIgnoreCase(country)){
				c.click();
				break;
			}
		}
	}
	
	public List<WebElement> getCountryList() {
		return countryList;
	}
	
	public ConfirmationPage submtiOrder() {
		sumitOrder.click();
		ConfirmationPage cf = new ConfirmationPage(driver);
		return cf;
	}
}
