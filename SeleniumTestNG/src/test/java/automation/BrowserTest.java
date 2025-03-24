package automation;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTest {

	public static void main(String[] args) throws InterruptedException, AWTException {
		java.awt.Robot robot = new java.awt.Robot(); // for dialogs which are from OS like upload, mouse movements or key strokes etc.
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = WebDriverManager.chromedriver().clearDriverCache().create();
		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.id("query")).sendKeys("bonigarcia");
		driver.findElement(By.xpath("/html/body/header/div[2]/form/input[2]"))
			.sendKeys(Keys.RETURN);
		//Other way by name
		//driver.findElement(By.name("btnK")).sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		driver.quit();
	}

}
