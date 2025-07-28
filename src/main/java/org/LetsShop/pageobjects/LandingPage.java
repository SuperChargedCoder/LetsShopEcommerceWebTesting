package org.LetsShop.pageobjects;

import org.LetsShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	//Creating Constructor to give power to ChromeDriver Driver
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		/*InitElementes of PageFactory class help in construction of WebElement
		 * and WebElement will be constructed only on calling of initElements method of PageFactory Class*/
		PageFactory.initElements(driver, this);
	}
	/*Creating WebElement locator using page factory model*/
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement loginErrorMessage;
	
	/*Now Writing the Action Methods for these WebElements*/
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	public void goTo(String URL){
		driver.get(URL);
	}
	public String getErrorMessage() {
		WaitForWebElementToAppear(loginErrorMessage);
		return loginErrorMessage.getText();
	}
}
