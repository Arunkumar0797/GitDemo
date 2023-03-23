package MavenIntroduction.SeleniumFramework;

import java.io.IOException;
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

public class DataProv extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "dataPro" })
	public void submitOrder(String email, String password, String productName)
			throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = launchUrl.LaunchApplication(email, password);

		// save products in list
		List<WebElement> products = productCatalogue.getProducts();
		// AddToCart products
		productCatalogue.addToCart(productName);
		// goToCartPage
		CartPage cartpage = productCatalogue.goToCartPage();
		// any match cartpage vs productName
		boolean match = cartpage.productDisplay(productName);
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

		return new Object[][] { { "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" },
				{ "shetty@gmail.com", "Iamking@000", "ZARA COAT 3" } };
	}
	
	


}
