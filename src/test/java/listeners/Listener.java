package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
	
	@Override
	public void onStart(ITestContext result) {
		
		System.out.println("******************* Test Suite started *********************");
		
	}

	@Override
	public void onFinish(ITestContext result) {

		System.out.println("******************* Test Suite finished *********************");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("*************** Test case start *************** ");
		System.out.println(result.getName()+" test case started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("The name of testcase Passed is : "+result.getName());
				
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println("The name of testcase Failed is : "+result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		 System.out.println("<<<<<<<<<<<<<<<<<<<< On Test Skipped >>>>>>>>>>>>>>>>>>>>");
		 System.out.println("The name of testcase Skipped is : "+result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	
		
	}



}
