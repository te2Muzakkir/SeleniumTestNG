package automation.extentreport;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class BaseExtentReport {
	
	public ExtentReports extentReports = new ExtentReports();
	public ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
	public static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
	
	//READ : @BeforeSuite and @AfterSuite annotated methods are added in BaseClass and then extended in child classes 
	@BeforeSuite
	public void setup() throws InterruptedException {
		System.out.println(" BeforeSuite called");
		//READ : Driver can be made global variable if there are multiple methods in the same class 
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Useful Title Name");
		spark.viewConfigurer().viewOrder().as(new ViewName[] {
				ViewName.DASHBOARD, ViewName.LOG, ViewName.TEST, ViewName.EXCEPTION, ViewName.CATEGORY
		}).apply();
		extentReports.attachReporter(spark);
	}
	
	@BeforeMethod
    public void createTestLogger(Method method) {
        ExtentTest test = extentReports.createTest(method.getName()).assignAuthor("Muzakkir")
    			.assignCategory("Functional Test").assignDevice("Windows");
        testLogger.set(test);
    }

    @AfterMethod
    public void clearTestLogger() {
        testLogger.remove();
    }
	
	@AfterSuite
	public void tearDown() {
		System.out.println(" @AfterSuite called");
		extentReports.flush();
	}

}