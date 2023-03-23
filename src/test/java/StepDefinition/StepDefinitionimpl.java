package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maven.testcomponents.BaseTest;
import test.abstractcomponent.ConfirmationMessage;
import test.pageObject.CartPage;
import test.pageObject.CheckoutPage;
import test.pageObject.LaunchApplication;
import test.pageObject.ProductCatalogue;

public class StepDefinitionimpl extends BaseTest {

	public LaunchApplication launchUrl;

	public ProductCatalogue productCatalogue;
	public ConfirmationMessage placeOrder;

	@Given("I landed on Ecommerce Page") // static
	public void I_landed_on_Ecommerce_Page() throws IOException {

		launchUrl = landingpage();
	}

	@Given("^Logged in with username (.+) and password (.+)$") // dynamic
	public void Logged_in_with_username_and_password(String username, String password) {

		productCatalogue = launchUrl.LaunchApplication(username, password);
	}

	@When("^I add product from cart (.+)$")
	public void Add_product_from_product(String productName) throws InterruptedException {

		List<WebElement> products = productCatalogue.getProducts();
		// AddToCart products
		productCatalogue.addToCart(productName);

	}

	// And checkout <productName> and submit order
	@Given("^checkout (.+) and submit order$")
	public void checkout_and_submitOrder(String productName) {

		CartPage cartpage = productCatalogue.goToCartPage();
		// any match cartpage vs productName
		boolean match = cartpage.productDisplay(productName);
		Assert.assertTrue(match);
		// goToCheckOut page
		CheckoutPage goToCheckOut = cartpage.goToCheckOut();
		// selectCountry
		goToCheckOut.selectCountry("india");
		// placeOrder button
		placeOrder = goToCheckOut.placeOrder();
	}

	@Then("I verify the {string} is displayed on confirmation page") // static
	public void display_confirmationMessage(String string) {

		// verifyConfirmationMessage
		String verifyConfirmationMessage = placeOrder.verifyConfirmationMessage();

		Assert.assertTrue(verifyConfirmationMessage.equalsIgnoreCase(string));
		driver.close();

	}

	@Then("^I verify the \"([^\"]*)\" error message display$")
	public void i_verify_the_something_error_message_display(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, launchUrl.getErrorMessage());
		driver.close();
	}

}
