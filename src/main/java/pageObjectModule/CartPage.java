package pageObjectModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.CommonUtils;

public class CartPage extends CommonUtils {

	WebDriver driver;
	String productName = "ZARA COAT 3";

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartList;
	
	@FindBy(css =".totalRow button")
	WebElement checkOut;
	

	public PaymentPage verifyCartAndCheckout(String productName) {
		boolean match = cartList.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		checkOut.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
		

	}


}
