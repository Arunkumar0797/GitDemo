package test.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class LaunchApplication extends Abstractcomponent {

	WebDriver driver;

	public LaunchApplication(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement userName;

	@FindBy(css = "#userPassword")
	WebElement passWord;

	@FindBy(css = "[name='login']")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;

	public String getErrorMessage() {

		waitForElementAppear(errormessage);

		return errormessage.getText();

	}

	public ProductCatalogue LaunchApplication(String email, String pass) {

		userName.sendKeys(email);
		passWord.sendKeys(pass);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

}
