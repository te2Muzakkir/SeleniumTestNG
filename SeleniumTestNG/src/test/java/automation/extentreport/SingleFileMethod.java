package automation.extentreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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

public class SingleFileMethod {
	
	/**
	 * READ : Some issue with multiple class files, 
	 * My suggestion create test class and method for each functionality in separate file
	 * call method from single file.
	 * And use order number in test
	 */
	
	public ExtentReports extentReports = new ExtentReports();
	public WebDriver driver;
	
	@BeforeSuite
	public void setup() throws InterruptedException {
		System.out.println(" BeforeSuite called");
		ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Useful Title Name");
		spark.viewConfigurer().viewOrder().as(new ViewName[] {
				ViewName.DASHBOARD, ViewName.LOG, ViewName.TEST, ViewName.EXCEPTION, ViewName.CATEGORY
		}).apply();
		extentReports.attachReporter(spark);
	}
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	/**
	 * @Test(priority = 0) ex -1,0,1,2....
	 * goes from least to highest number
	 * if no priority given then runs alphabetical order
	 * @throws InterruptedException
	 */
	@Test(priority = 0)
	public void testMavenSearchPass() throws InterruptedException {
		System.out.println(" test called : testMavenSearchPass");
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		ExtentTest extentTest = extentReports.createTest("Verify Maven Search Pass").assignAuthor("Muzakkir")
			.assignCategory("Functional Test").assignDevice("Windows");
		extentTest.info("Searching text...");
		SearchPageObjects pageObjects = new SearchPageObjects(driver);
		pageObjects.insertSerchTextToMavenSearchInHomePage("bonigarcia");
		extentTest.info("Clicking Submit");
		pageObjects.clickMavenSearchBtnInHomePage();
		extentTest.info("Checking if title begins with 'Maven Repository'");
		if(driver.getTitle().startsWith("Maven Repository"))
			extentTest.log(Status.PASS, "Title is correct and is : "+ driver.getTitle());
		else
			extentTest.log(Status.FAIL, "Title is incorrect and is : "+ driver.getTitle());
		System.out.println("Test Selenium completed.");
	}
	
	@Test
	public void testMavenSearchException() throws InterruptedException {
		System.out.println(" test called : testMavenSearchException");
		Throwable throwable = new RuntimeException("Causes run time exception");
		ExtentTest extentTest = extentReports.createTest("Verify Maven Search Exception").assignAuthor("Muzakkir")
				.assignCategory("Functional Test").assignDevice("Windows");
		extentTest.info("Expecting Failure");
		extentTest.log(Status.FAIL, throwable.getMessage());
		extentTest.fail(throwable);
		System.out.println("Test Selenium completed.");
	}
	
	@Test
	public void testMavenSearchFail() throws InterruptedException {
		System.out.println(" test called : testMavenSearchFail");
		ExtentTest extentTest = extentReports.createTest("Verify Maven Search Failed").assignAuthor("Muzakkir")
				.assignCategory("Functional Test").assignDevice("Windows");
		extentTest.info("Searching text...");
		extentTest.info("Checking if title begins with 'Maven Repository'");
		extentTest.log(Status.FAIL, "Title is incorrect ");
		extentTest.fail("Title is incorrect ");
		System.out.println("Test Selenium completed.");
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
	}
	
	@AfterSuite
	public void tearDown() {
		System.out.println(" @AfterSuite called");
		extentReports.flush();
	}

}