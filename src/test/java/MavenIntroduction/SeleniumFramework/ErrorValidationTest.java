package MavenIntroduction.SeleniumFramework;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import maven.testcomponents.BaseTest;
import maven.testcomponents.Retry;
import test.pageObject.CartPage;
import test.pageObject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	//@Test(groups = { "ErrorValidation" }, retryAnalyzer=Retry.class)
	
	@Test(groups = { "ErrorValidation" }, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {

		ProductCatalogue productCatalogue = launchUrl.LaunchApplication("anshika@gmail.com", "Iang@000");
		Assert.assertEquals("Incorrect email password.", launchUrl.getErrorMessage());
	}

	@Test
	public void submitOrderErrorvalidation() throws InterruptedException {

		String productName = "ADIDAS ORIGINAL";

		// LaunchApplication launchUrl = landingpage();

		ProductCatalogue productCatalogue = launchUrl.LaunchApplication("anshika@gmail.com", "Iamking@000");

		List<WebElement> products = productCatalogue.getProducts();

		productCatalogue.addToCart(productName);

		CartPage cartpage = productCatalogue.goToCartPage();

		boolean match = cartpage.productDisplay("ADIDAS123");
		Assert.assertFalse(match);

	}
}
