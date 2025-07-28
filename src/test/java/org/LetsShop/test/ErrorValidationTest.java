package org.LetsShop.test;

import java.io.IOException;

import org.LetsShop.TestComponents.BaseTest;
import org.LetsShop.TestComponents.Retry;
import org.LetsShop.pageobjects.CartPage;
import org.LetsShop.pageobjects.CheckoutPage;
import org.LetsShop.pageobjects.ConfirmationPage;
import org.LetsShop.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingpage.loginApplication("SC@gmail.com", "Selenium@1234");
		Assert.assertEquals("Incorrect email or* password", landingpage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String product = "IPHONE 13 PRO";
		/*Login*/
		ProductCatalogue productcatalogue = landingpage.loginApplication("SC@gmail.com", "Selenium@123");
		
		/*Adding Product To cart*/
		productcatalogue.addProductToCart(product);
		CartPage cartpage = productcatalogue.goToCartPage();
		boolean match = cartpage.VerifyProdctDisplay("IPHONE 13 PRO");
		Assert.assertTrue(match);
	}
}
