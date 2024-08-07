package ragulProject1;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModule.CartPage;
import pageObjectModule.LandingPage;
import pageObjectModule.PaymentPage;
import pageObjectModule.ProductCatalogue;
import testComponents.BaseTest;

public class ReframedTest extends BaseTest {
	@Test(dataProvider = "Data", dataProviderClass = BaseTest.class)
	public void Test1(HashMap<String, String> input) throws IOException, InterruptedException {

		
		ProductCatalogue productCatalogue = landingPage.loginFucn(input.get("email"), input.get("password"));
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addToCart(input.get("product"));
		Thread.sleep(2000);
		CartPage cartPage = productCatalogue.clickCart();
		PaymentPage paymentPage = cartPage.verifyCartAndCheckout(input.get("product"));
		paymentPage.placeOrder();
		paymentPage.verifyOrderConfirmation();

	}

}
