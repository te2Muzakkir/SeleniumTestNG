package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class BrowserTestWait {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		//Implicit wait - global applies wait for all elements
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

		driver.get("https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0");
		driver.manage().window().maximize();
		Thread.sleep(5000);

		//Explicit wait - applies only to specific elements for mentioned time with fixed polling interval 250 milli second
		//if not found throws NoSuchElementException
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10L));
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("abcd")));


		//fluent wait - applies only to specific elements for mentioned time with custom polling interval
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(5L))
				.ignoring(NoSuchElementException.class);
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.id("foo"));
				if(element.isEnabled())
					System.out.println("Element Found.");
				return element;
			}
		});
		foo.click();

		driver.findElement(By.id("query")).sendKeys("bonigarcia");
		driver.findElement(By.xpath("/html/body/header/div[2]/form/input[2]"))
		.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		driver.quit();
	}

}
