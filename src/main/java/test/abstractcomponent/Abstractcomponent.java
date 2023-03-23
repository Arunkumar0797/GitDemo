package test.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.pageObject.CartPage;

public class Abstractcomponent {

	WebDriver driver;

	public Abstractcomponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement ele;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	public void waitForElementAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForElementAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementDisappear(WebElement spinner) throws InterruptedException {

		Thread.sleep(1000);

		/*
		 * 4 second----------application takes time WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.invisibilityOf(spinner));
		 */

	}

	public CartPage goToCartPage() {

		cartHeader.click();

		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

	public OrderPage goToOrderPage() {

		orderHeader.click();

		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public ConfirmationMessage placeOrder() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", ele);

		return new ConfirmationMessage(driver);

	}

}
