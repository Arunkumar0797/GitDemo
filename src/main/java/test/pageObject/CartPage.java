package test.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class CartPage extends Abstractcomponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	private List<WebElement> cartproducts;

	
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public boolean productDisplay(String productName) {

		boolean match = cartproducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return match;

	}
	
	public CheckoutPage goToCheckOut() {
		
		checkout.click();
		return new CheckoutPage(driver);
	}

}
