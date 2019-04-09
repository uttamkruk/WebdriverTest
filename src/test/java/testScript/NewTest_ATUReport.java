/*package testScript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import retryFailedTestcase.RetryFailedTestcase;
import utility.CaptureScreenshot;
import utility.SendAttachmentInEmail;


@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class NewTest_ATUReport {

	WebDriver driver;
	
	{
        System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\Misc\\atu.properties");
    }
	
	
	@Test(groups = { "Title_verification" }, priority = 1, enabled = true)
	public void testEasy() throws InterruptedException {

		 driver.get("https://www.google.com/");
		 Thread.sleep(10000);		 

		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Google.."));
		
		//ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		WebElement element = driver
                .findElement(By.xpath("/html/body/div/h1/a"));
		ATUReports.add("Warning Step", LogAs.WARNING,
                new CaptureScreen(element));
		//ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
		System.out.println("Test Case One in " + getClass().getSimpleName()+ " with Thread Id -: " + Thread.currentThread().getId());
		
		}

	@Test(groups = { "Screenshot" }, priority = 2, enabled = true)
	public void captureScreenshot() {

		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			System.out.println(ts.getTime());
			driver.get("http://www.ebay.in/");
			TakesScreenshot oScn = (TakesScreenshot) driver;

			// Screenshot info will be saved in OscnShot variable (of type FILE)
			File oScnShot = oScn.getScreenshotAs(OutputType.FILE);

			// Creating an empty Image file
			File oDest = new File(System.getProperty("user.dir")+"\\outfiles\\Screenshot_"+ts.getTime()+".jpeg");

			// Save in a Physical location
			FileUtils.copyFile(oScnShot, oDest);

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		
		System.out.println("Test Case Two in " + getClass().getSimpleName()	+ " with Thread Id:- " + Thread.currentThread().getId());
	}
	

	// Re-run the failed testcase to 2 more times	
	 @Test (priority = 3, retryAnalyzer = RetryFailedTestcase.class, enabled = true) 
	 public void rerunFailedTestcase() {
	  
		  driver.get("https://www.google.com/");
		  driver.manage().window().maximize(); String title = driver.getTitle();
		  Assert.assertTrue(title.contains("Google failed...")); 
	  }
	 

	@BeforeClass(alwaysRun = true)
	public void beforeTest() {
		
		 // Firefox Browser
		  System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")"\\driver\\geckodriver.exe");
		 
		  // if above property is not working or not opening the application in browser then try below property 
		  // System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		 
		
		  // IE Browser 
		  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe"); 
		  driver = new InternetExplorerDriver();
		  driver.manage().window().maximize(); 
		 

		// Chrome Browser
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		ATUReports.setWebDriver(driver);
		//ATUReports.indexPageDescription = "WebDriver Project With ATUReporter";
		setIndexPageDescription();
		setAuthorInfoForReports();

	}
	
	
	private void setAuthorInfoForReports() {
        ATUReports.setAuthorInfo("Uttam Kumar", Utils.getCurrentTime(),"1.0");
	}
	
	private void setIndexPageDescription() {
        ATUReports.indexPageDescription = "WebDriver Project With ATUReporter <br/> <b>include Full set of HTML Tags</b>";
	}
	
	

	//@AfterClass(alwaysRun = true)	
	@AfterMethod(alwaysRun = true)	
	//public void afterTest(){
	public void afterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = CaptureScreenshot.captureScreenshot(
					driver, result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL,"Title Verification",image);
		}
			report.endTest(logger);
			report.flush();
			driver.get(System.getProperty("user.dir")+"\\extent-reports\\reports.html");
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}else if(result.getStatus() == ITestResult.SKIP){
			ATUReports.add("Pass Step", LogAs.WARNING, new CaptureScreen(ScreenshotOf.DESKTOP));
		 }else {
			 ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		 }	
		
		    //driver.quit();
		}
	
	
	@AfterTest (alwaysRun = true)    // Email report after complete execution	
	public void tearDown (){
		
		try{
			//String reportFileName = System.getProperty("user.dir")+"\\${workspace}\\Misc\\ATUReporter\\index.html";
			String reportFileName = System.getProperty("user.dir")+"\\${workspace}\\Misc\\ATUReporter";
			SendAttachmentInEmail email = new SendAttachmentInEmail();
			email.emailReport(reportFileName);
			System.out.println("Email sent successfully..");
		}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Could not send email..");
			}
		finally {
			driver.quit();
		}
	}
		

}
	
	
	
	
	
	
	
*/