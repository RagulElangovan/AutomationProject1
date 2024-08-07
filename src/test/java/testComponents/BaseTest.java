package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjectModule.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;


	@Test
	public WebDriver initializeDriver() throws IOException {
		
		String property = null;

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(file);
		
		if (System.getProperty("browser")!=null) {
			
			 property = System.getProperty("browser");
			
		}else {

		 property = prop.getProperty("browser");
		}

		if (property.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();

		} else if (property.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public LandingPage openApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToURL();
		return landingPage;

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();

	}
	
	public static List<HashMap<String, String>> getDataFromJson() throws IOException {

		String  jsonContant = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\TestData.json"), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> value = mapper.readValue(jsonContant, new TypeReference<List<HashMap<String, String>>>(){});
		return value;
		

	}
	
	@DataProvider(name="Data")
	public Object[][] getData() throws IOException {
		
		
		List<HashMap<String, String>> data = getDataFromJson();
		
		
		return new Object [] [] { {data.get(0)}, {data.get(1)}};
	
		
		// return new Object [] [] {{"email","password"}, {"123", "4567"}};
	
	
	}
	
	public static ExtentReports extentreport() {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\report\\Testreport.html"));
		reporter.config().setReportName("Automation Test Report");
		reporter.config().setDocumentTitle("Test Execution  Result");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ragul");
		return extent;

	}
	
	public String screenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmmss");
		LocalDateTime now = LocalDateTime.now();
		String date = ofPattern.format(now);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "\\report\\"+testCaseName+".png"));
		return System.getProperty("user.dir") + "\\report\\"+testCaseName+".png";
	}

}
 