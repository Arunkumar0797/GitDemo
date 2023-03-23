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

public class ReadData extends BaseTest {

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
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonToData(
				System.getProperty("user.dir") + "\\src\\test\\java\\test\\dataDriven\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
