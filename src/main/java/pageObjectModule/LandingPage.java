package pageObjectModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.CommonUtils;

public class LandingPage extends CommonUtils {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	public WebElement userID;

	@FindBy(id = "userPassword")
	public WebElement password;

	@FindBy(id = "login")
	public WebElement loginButton;

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client");

	}

	public ProductCatalogue loginFucn(String mail, String pass) {

		userID.sendKeys(mail);
		password.sendKeys(pass);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}
}
