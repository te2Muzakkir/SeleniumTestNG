package automation.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * READ : mention Class and Method names as in detail as possible to void confucion
 * 
 */
public class SearchPageObjects {
	
	private static WebDriver driver = null;
	
	private By mvn_repo_search_textBox = By.id("query");
	private By mvn_repo_search_submitBtn = By.xpath("/html/body/header/div[2]/form/input[2]");
	
	public SearchPageObjects(WebDriver driver) {
		SearchPageObjects.driver = driver;
	}
	
	public void insertSerchTextToMavenSearchInHomePage(String searchText) {
		driver.findElement(mvn_repo_search_textBox).sendKeys(searchText);
	}
	
	public void clickMavenSearchBtnInHomePage() {
		driver.findElement(mvn_repo_search_submitBtn).sendKeys(Keys.ENTER);
	}

}
