package test.abstractcomponent;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class OrderPage extends Abstractcomponent {
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//tr/td[2]")
	private List<WebElement> orderprodutcs;

	

	public boolean verifyProductDisplay(String productName) {

		boolean match = orderprodutcs.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return match;

	}


}
