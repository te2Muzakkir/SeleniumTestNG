package automation.extentreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportBasicDemo2 extends BaseExtentReport {
	
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
		System.out.println(" beforeTest called : ExtentReportBasicDemo2");
		//READ : Driver can be made global variable if there are multiple methods in the same class 
		driver = new FirefoxDriver();
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@Test
	public void testMavenSearchException() throws InterruptedException {
		System.out.println(" test called : testMavenSearchException");
		Throwable throwable = new RuntimeException("Causes run time exception");
		testLogger.get().info("Expecting Failure");
		testLogger.get().log(Status.FAIL, throwable.getMessage());
		testLogger.get().fail(throwable);
		System.out.println("Test Selenium completed.");
	}
	
	@Test
	public void testMavenSearchFail() throws InterruptedException {
		System.out.println(" test called : testMavenSearchFail");
		testLogger.get().info("Searching text...");
		testLogger.get().info("Checking if title begins with 'Maven Repository'");
		testLogger.get().log(Status.FAIL, "Title is incorrect ");
		testLogger.get().fail("Title is incorrect ");
		System.out.println("Test Selenium completed.");
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println(" @Aftertest called");
		driver.close();
	}
	
}
