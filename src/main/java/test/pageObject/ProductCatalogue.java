package test.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.abstractcomponent.Abstractcomponent;

public class ProductCatalogue extends Abstractcomponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");

	By AddToCart = By.cssSelector(".card-body button:last-of-type");

	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProducts() {

		waitForElementAppear(productsBy);
		return products;
	}

	public WebElement getproductByName(String productName) {

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addToCart(String productName) throws InterruptedException {

		WebElement prod = getproductByName(productName);
		prod.findElement(AddToCart).click();
		waitForElementAppear(toastMessage);
		waitForElementDisappear(spinner);
		
		
	}

}
