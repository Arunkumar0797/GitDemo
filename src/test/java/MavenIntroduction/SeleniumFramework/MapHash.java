package MavenIntroduction.SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import maven.testcomponents.BaseTest;
import test.abstractcomponent.ConfirmationMessage;
import test.pageObject.CartPage;
import test.pageObject.CheckoutPage;
import test.pageObject.ProductCatalogue;

public class MapHash extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "dataPro" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = launchUrl.LaunchApplication(input.get("email"), (input.get("password")));

		// save products in list
		List<WebElement> products = productCatalogue.getProducts();
		// AddToCart products
		productCatalogue.addToCart(input.get("productName"));
		// goToCartPage
		CartPage cartpage = productCatalogue.goToCartPage();
		// any match cartpage vs productName
		boolean match = cartpage.productDisplay(input.get("productName"));
		Assert.assertTrue(match);
		// goToCheckOut page
		CheckoutPage goToCheckOut = cartpage.goToCheckOut();
		// selectCountry
		goToCheckOut.selectCountry("india");
		// placeOrder button
		ConfirmationMessage placeOrder = goToCheckOut.placeOrder();
		// verifyConfirmationMessage
		String verifyConfirmationMessage = placeOrder.verifyConfirmationMessage();

		Assert.assertTrue(verifyConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "shetty@gmail.com");
		map2.put("password", "Iamking@000");
		map2.put("productName", "ZARA COAT 3");

		return new Object[][] { { map1 }, { map2 } };
	}

}
