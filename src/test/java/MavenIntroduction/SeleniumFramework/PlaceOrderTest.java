package MavenIntroduction.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import maven.testcomponents.BaseTest;
import test.abstractcomponent.ConfirmationMessage;
import test.abstractcomponent.OrderPage;
import test.pageObject.CartPage;
import test.pageObject.CheckoutPage;
import test.pageObject.ProductCatalogue;

public class PlaceOrderTest extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";


	@Test
	public void submitOrder() throws IOException, InterruptedException  {

		
		ProductCatalogue productCatalogue = launchUrl.LaunchApplication("anshika@gmail.com", "Iamking@000");

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
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory() {
		
		ProductCatalogue productCatalogue = launchUrl.LaunchApplication("anshika@gmail.com", "Iamking@000");
		OrderPage goToOrderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(goToOrderPage.verifyProductDisplay(productName));
		
	}

}
