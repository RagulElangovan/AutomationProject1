package pageObjectModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.CommonUtils;

public class ProductCatalogue extends CommonUtils {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By productList = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastcontainer = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	WebElement animating;

	@FindBy(css = ".ng-trigger")
	WebElement spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;



	public List<WebElement> listOfProducts() {

		waitTillVisibility(productList);
		return driver.findElements(productList);
	}

	public void addToCart(String productName) {
		WebElement prod = listOfProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		System.out.println(prod);
		prod.findElement(addToCart).click();
		waitTillVisibility(toastcontainer);
		waitTillInvisibility(spinner);

	}

	public CartPage clickCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

}
