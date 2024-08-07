package pageObjectModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utility.CommonUtils;

public class PaymentPage extends CommonUtils {

	WebDriver driver;
	String Country = "India";

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryTxtBox;
	
	By list = By.cssSelector(".ta-results");
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectIndia;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	@FindBy(css = ".hero-primary")
	WebElement  confirmMessage;
	
	@FindBy(xpath= "//label[@class = 'ng-star-inserted']")
	WebElement orderID;
	
	public void placeOrder() {
		
		countryTxtBox.sendKeys(Country);
		waitTillVisibility(list);
		selectIndia.click();
		placeOrder.click();
	}
	
	
	public void verifyOrderConfirmation() {
		
		Assert.assertTrue((confirmMessage.getText()).equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Your Order ID is:  "+orderID.getText());
				
	}
	



}
