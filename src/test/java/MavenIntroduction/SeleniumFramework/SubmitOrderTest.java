package MavenIntroduction.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.abstractcomponent.ConfirmationMessage;
import test.pageObject.CartPage;
import test.pageObject.CheckoutPage;
import test.pageObject.LaunchApplication;
import test.pageObject.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";

		// System.setProperty(key, value)

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// launchUrl
		LaunchApplication launchUrl = new LaunchApplication(driver);
		// url detail
		launchUrl.goTo();
		// username, password
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

}
