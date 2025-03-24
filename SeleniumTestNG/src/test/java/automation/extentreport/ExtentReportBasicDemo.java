package automation.extentreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import automation.pom.pages.SearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportBasicDemo extends BaseExtentReport {
	
	/**
	 * READ : Some issue with multiple class files, 
	 * My suggestion create test class and method for each functionality in separate file
	 * call method from single file.
	 * And use order number in test
	 */
	
	WebDriver driver;

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
		System.out.println(" beforeTest called : ExtentReportBasicDemo");
		//READ : Driver can be made global variable if there are multiple methods in the same class 
		//driver = WebDriverManager.firefoxdriver().clearDriverCache().create();
		driver = new FirefoxDriver();
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test
	public void testMavenSearchPass() throws InterruptedException {
		System.out.println(" test called : testMavenSearchPass");
		testLogger.get().info("Searching text...");
		SearchPageObjects pageObjects = new SearchPageObjects(driver);
		pageObjects.insertSerchTextToMavenSearchInHomePage("bonigarcia");
		testLogger.get().info("Clicking Submit");
		pageObjects.clickMavenSearchBtnInHomePage();
		testLogger.get().info("Checking if title begins with 'Maven Repository'");
		if(driver.getTitle().startsWith("Maven Repository"))
			testLogger.get().log(Status.PASS, "Title is correct and is : "+ driver.getTitle());
		else
			testLogger.get().log(Status.FAIL, "Title is incorrect and is : "+ driver.getTitle());
		System.out.println("Test Selenium completed.");
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println(" @Aftertest called");
		driver.close();
	}
	
}