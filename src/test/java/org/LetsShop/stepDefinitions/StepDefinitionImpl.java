package org.LetsShop.stepDefinitions;

import java.io.IOException;

import org.LetsShop.TestComponents.BaseTest;
import org.LetsShop.pageobjects.CartPage;
import org.LetsShop.pageobjects.CheckoutPage;
import org.LetsShop.pageobjects.ConfirmationPage;
import org.LetsShop.pageobjects.ProductCatalogue;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	ProductCatalogue productcatalogue;
	CartPage cartpage;
	ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		System.out.println(driver);
		launchApplication();
		System.out.println(driver);
	}

	@Given("I logged in with {string} and {string}")
	public void i_logged_in_with_and(String email, String password) {
		productcatalogue = landingpage.loginApplication(email, password);
	}

	@When("add the {string} to cart")
	public void add_the_to_cart(String product) throws InterruptedException {
		productcatalogue.addProductToCart(product);
		cartpage = productcatalogue.goToCartPage();
	}

	@When("Checkout {string} and submit the order")
	public void checkout_and_submit_the_order(String product) {
		boolean match = cartpage.VerifyProdctDisplay(product);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();

		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.submtiOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String Text) {
		String confirmMessage = confirmationpage.getConfirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(Text));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String Text) {
		Assert.assertEquals(Text, landingpage.getErrorMessage());
		driver.close();
	}
}
