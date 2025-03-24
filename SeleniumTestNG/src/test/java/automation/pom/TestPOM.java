package automation.pom;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automation.pom.pages.SearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Testing Page Object Model of Selenium
 */
public class TestPOM {
	
	private WebDriver driver = null;
	
	@BeforeTest
	public void setup() throws InterruptedException {
		//READ : Driver can be made global variable if there are multiple methods in the same class 
		driver = WebDriverManager.firefoxdriver().clearDriverCache().create();
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test
	public void testMavenSearch() throws InterruptedException {
		SearchPageObjects pageObjects = new SearchPageObjects(driver);
		pageObjects.insertSerchTextToMavenSearchInHomePage("bonigarcia");
		pageObjects.clickMavenSearchBtnInHomePage();
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		System.out.println("Test Selenium completed.");
	}

}
