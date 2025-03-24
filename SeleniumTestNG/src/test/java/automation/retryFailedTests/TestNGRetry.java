package automation.retryFailedTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGRetry {
	
	/**
	 * https://testng.org/#_rerunning_failed_tests
	 * To re-run failed test cases maunally run the file /test-output/testng-failed.xml
	 * 
	 * 
	 * or for automation run the below commands
	 * To run all the tc first run cmd
	 * java -classpath testng.jar;%CLASSPATH% org.testng.TestNG -d test-outputs testng.xml
	 * 
	 * then after sometime for the failed tc run cmd
	 * java -classpath testng.jar;%CLASSPATH% org.testng.TestNG -d test-outputs test-outputs\testng-failed.xml


	 */
	
	@Test
	public void test1() {
		System.out.println("This is test 1");
	}
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void test2() {
		System.out.println("This is test 2");
		int i = 1/0;
	}

	
	@Test(retryAnalyzer = DelayedRetryAnalyzer.class)
	public void test3() {
		System.out.println("This is test 3");
		Assert.assertTrue(0>1);
	}
	
	@Test
	public void test4() {
		System.out.println("This is test 4");
	}

}
