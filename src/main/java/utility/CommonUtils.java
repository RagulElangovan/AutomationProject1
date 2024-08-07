package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {

	WebDriver driver;
	WebDriverWait wait;

	public CommonUtils(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));

	}
	
	
	
	public void waitTillVisibility(By byLocator) {
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));

	}
	
	public void waitTillInvisibility(WebElement element) {
	
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	

}
