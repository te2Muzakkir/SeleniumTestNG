package automation.retryFailedTests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class DelayedRetryAnalyzer implements IRetryAnalyzer {
	
	private int retryCount = 0;
    private final int maxRetryCount = 2; // Number of retries
    private final long delayMillis = 1 * 60 * 1000; // 15 minutes delay

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            try {
                System.out.println("Retrying test after " + (delayMillis / 60000) + " minutes...");
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                return false;
            }
            return true;
        }
        return false;
    }

}
