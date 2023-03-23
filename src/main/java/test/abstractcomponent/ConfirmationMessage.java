package test.abstractcomponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class ConfirmationMessage extends Abstractcomponent {
	

	WebDriver driver;

	public ConfirmationMessage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmationMessage;
	

	public String verifyConfirmationMessage() {
		
		return confirmationMessage.getText();
	}

}
