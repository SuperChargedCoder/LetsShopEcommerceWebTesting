package org.LetsShop.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.LetsShop.TestComponents.BaseTest;
import org.LetsShop.pageobjects.CartPage;
import org.LetsShop.pageobjects.CheckoutPage;
import org.LetsShop.pageobjects.ConfirmationPage;
import org.LetsShop.pageobjects.OrderPage;
import org.LetsShop.pageobjects.ProductCatalogue;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	String filepath = "D:\\All Desktop folders\\Selenium Revision\\Shubham Selenium Codes\\LetsShopFramework\\src\\test\\java\\org\\LetsShop\\data\\PurchaseOrder.json";

	@Test (dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		/* Login */
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));

		/* Adding Product To cart */
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalogue.goToCartPage();
		boolean match = cartpage.VerifyProdctDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();

		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submtiOrder();

		String confirmMessage = confirmationpage.getConfirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods= {"submitOrder"},dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		OrderPage ordersPage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(filepath);
		return new Object[][]  {{data.get(0)}, {data.get(1) }};
	}
	
	/*
	 * @DataProvider
		public Object[][] getData() {
			return new Object[][] {{"SC@gmail.com", "Selenium@123","IPHONE 13 PRO"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		}
		
		@DataProvider
		public Object[][] getData() {
			HashMap<Object, Object> map = new HashMap<Object, Object>(); // Here we are using object as data type because it can store all type of DataType
			map.put("email", "anshika@gmail.com");
			map.put("password", "Iamking@000");
			map.put("product", "ADIDAS ORIGINAL");
			HashMap<Object, Object> map1 = new HashMap<Object, Object>(); // Here we are using object as data type because it can store all type of DataType
			map1.put("email", "SC@gmail.com");
			map1.put("password", "Selenium@123");
			map1.put("product", "IPHONE 13 PRO");
		
		return new Object[][] {{map},{map1}};
	}
	*/
}

