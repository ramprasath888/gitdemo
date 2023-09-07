package SeleniumAutomation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxtry = 1;
	@Override
	public boolean retry(ITestResult result) {
		// this method used for only failure to rerun our test based on our maxtry value
		if(count<maxtry)
		{
			count++;
			return true;	
		}
		return false;
	}

}
