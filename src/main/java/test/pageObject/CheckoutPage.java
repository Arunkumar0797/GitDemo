package test.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class CheckoutPage extends Abstractcomponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String CountryName) {
		Actions a = new Actions(driver);

		a.sendKeys(Country, CountryName).build().perform();
		waitForElementAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

}
