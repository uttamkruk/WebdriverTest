package testScript;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import retryFailedTestcase.RetryFailedTestcase;
import utility.CaptureScreenshot;
import utility.SendAttachmentInEmail;
import utility.ZipFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import listeners.ListenerV3;

@Listeners(listeners.ListenerV3.class)

public class NewTest_ExtentReportV3Copy extends ListenerV3{
	
	/*ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest logger;*/
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	
	public void beforeTest(String browser) {
		
		/*ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports_" + System.currentTimeMillis()+ ".html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		
		//htmlReporter.config().setFilePath("./extent-config.xml");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Extent Report V3.X");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Report");
        report.setSystemInfo("Host Name", "Windows 10");
		report.setSystemInfo("Environment", "Verification");
		report.setSystemInfo("User Name","Uttam Kumar");*/
        
		if(browser.equalsIgnoreCase("firefox")) {
			
			  // Firefox Browser
			  System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  // if above property is not working or not opening the application in browser then try below property //
			  //System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  driver = new FirefoxDriver();
			  driver.manage().window().maximize();
			 
		}else if(browser.equalsIgnoreCase("ie")) {
			
			  // IE Browser
			  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe"); 
			  driver = new InternetExplorerDriver();
			  driver.manage().window().maximize();
			 
		}else if(browser.equalsIgnoreCase("chrome")) {
			
			// Chrome Browser
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			/*// for headless browser
			options.addArguments("window-size=1400,800","headless");*/
			driver = new ChromeDriver(options);
			System.out.println("Chrome starting...");
			
		}
}

	// Start - Test to pass as to verify listeners
	@Test(groups = { "Listeners" }, priority = 5, enabled = true)
	public void TestToPass() {
		//logger = report.createTest("Verify TestToPass to test listeners");
		logger.log(Status.INFO, "TestToPass started...");
		System.out.println("Method to Pass the test to test Listeners");
		driver.get("https://www.gmail.com");
		driver.getTitle();
	}
	
	@Test(groups = { "Listeners" }, priority = 7, enabled = true)
	public void TestToFail() {
		//logger = report.createTest("Verify TestToFail to test listeners");
		logger.log(Status.INFO, "TestToFail started...");
		System.out.println("Method to Fail the test to test Listeners");
		Assert.assertTrue(true);
	}

	// Used skip exception to skip the test
	@Test(groups = { "Listeners" }, priority = 6, enabled = true)
	public void TestToSkip() {
		//logger = report.createTest("Verify TestToSkip to test listeners");
		logger.log(Status.INFO, "TestToSkip started...");
		System.out.println("Method to Skip the test to test Listeners");
		throw new SkipException("Skipping - This is not ready for testing");
	}
	// End - Test to pass as to verify listeners

	@Test(groups = { "Title_verification" }, priority = 1, enabled = true)
	public void testEasy() throws InterruptedException, IOException {

		//logger = report.createTest("Verify testEasy Title name");
		logger.log(Status.INFO, "Browser Started ...");

		driver.get("https://www.google.com/");
		Thread.sleep(2000);

		logger.log(Status.INFO, "Application is up and running ...");

		String title = driver.getTitle();
	    Assert.assertTrue(title.contains("Google.."));

		logger.log(Status.PASS, "Tilte Verified Successfully...");
		
		WebElement element = driver.findElement(By.cssSelector("#hplogo"));
		CaptureScreenshot.getScreenshot(driver, element);
		System.out.println("WebElement is captured successfully..");
		logger.log(Status.PASS, "WebElement is captured successfully...");
	}

	@Test(groups = { "Screenshot" }, priority = 2, enabled = true)
	public void captureScreenshot() {

		try {
			//logger = report.createTest("Verify captureScreenshot");
			logger.log(Status.INFO, "Capture Screenshot Started ...");
			driver.get("https://www.ebay.in/");
			TakesScreenshot oScn = (TakesScreenshot) driver;

			// Screenshot info will be saved in OscnShot variable (of type FILE)
			File oScnShot = oScn.getScreenshotAs(OutputType.FILE);

			// Creating an empty Image file
			File oDest = new File(System.getProperty("user.dir") + "\\outfiles\\Screenshot_" + System.currentTimeMillis()+ ".png");

			// Save in a Physical location
			FileUtils.copyFile(oScnShot, oDest);

			logger.log(Status.PASS, "Screenshot is captured successfully...");

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	
	/*  // Re-run the failed testcase to 2 more times	  
	 @Test (priority = 4, retryAnalyzer = RetryFailedTestcase.class, enabled= true) 	  
	  public void rerunFailedTestcase() {	
		 
		  driver.get("https://www.google.com/"); 
		  String title = driver.getTitle();
		  Assert.assertTrue(title.contains("Google title failed"));
		  logger.log(Status.INFO, "Retry is happening");
		  	  
	  }*/
	 

	@Test(groups = { "Phptravels.com" }, priority = 4, enabled = false)

	public void PhpTravels() {

		try {
		
			//logger = report.createTest("Verify PhpTravels to Book Flight");
			logger.log(Status.INFO, "Browser Started ...");

			driver.get("http://www.phptravels.net/");
			Thread.sleep(10000);

			logger.log(Status.INFO, "Application is up and running ...");

			Actions action = new Actions(driver);

			WebElement flight = driver.findElement(By.cssSelector("ul.nav > li:nth-child(2) > a > span"));
			flight.click();
			Thread.sleep(10);

			WebElement ori = driver.findElement(By.cssSelector("#s2id_autogen12 > a > span.select2-chosen"));
			action.moveToElement(ori).doubleClick().build().perform();
			WebElement ori1 = driver.findElement(By.cssSelector("#select2-drop > div > input"));
			ori1.sendKeys("ban");
			/*
			 * WebElement origin =
			 * driver.findElement(By.cssSelector("ul.select2-results >li:nth-child(4)"));
			 * origin.click();
			 */
			List<WebElement> origin = driver.findElements(By.cssSelector("ul.select2-results >li"));
			origin.get(3).click();

			WebElement dest = driver
					.findElement(By.cssSelector("#s2id_autogen14 > a > span.select2-chosen:nth-child(1)"));
			action.moveToElement(dest).doubleClick().build().perform();
			WebElement dest1 = driver.findElement(By.cssSelector("#select2-drop > div > input"));
			dest1.sendKeys("tata");
			/*
			 * WebElement destination =
			 * driver.findElement(By.cssSelector("ul.select2-results >li:nth-child(2)"));
			 * destination.click();
			 */
			List<WebElement> destination = driver.findElements(By.cssSelector("ul.select2-results >li"));
			destination.get(2).click();

			WebElement classtype = driver.findElement(By.cssSelector("select[name='cabinclass']"));
			Select select = new Select(classtype);
			select.selectByValue("business");

			WebElement departure = driver.findElement(By.cssSelector("input[placeholder='Depart']"));
			departure.sendKeys("2017-10-26");

			WebElement guests = driver.findElement(By.cssSelector("input[name ='totalPassenger']"));
			guests.click();
			WebElement adult = driver.findElement(By.cssSelector("select[name ='adult']"));
			new Select(adult);
			select.selectByValue("3");

			WebElement close = driver.findElement(By.xpath("//*[@id='flightTravelers']/div/div/div[1]/button"));
			close.click();

			guests.sendKeys("2");

			WebElement submit = driver.findElement(By.xpath("//*[@id='flight']/form/div[3]/div[4]/button"));
			submit.click();

			Thread.sleep(10);
			System.out.println("List displayed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		logger = report.createTest(testName);
		
	}*/

	@AfterMethod (alwaysRun = true) // ITestResult will work only with @AfterMethod (It will run after each @Test)
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotName = CaptureScreenshot.captureScreenshot(driver, result.getName());
			logger.fail("Fail due to some issue", MediaEntityBuilder.createScreenCaptureFromPath("./Misc/screenshot/"+screenshotName+".jpg").build());
			logger.fail(result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(Status.SKIP, "Test Case Skipped is -: "+result.getName());
		 }else if(result.getStatus() == ITestResult.SUCCESS){
			 //logger.log(Status.PASS, "Test Casename as -: "+result.getName()+" is Passed");
			 logger.pass("Successfully executed", MediaEntityBuilder.createScreenCaptureFromPath("./Misc/screenshot/"+result.getName()+".jpg").build());
		 }
		   report.flush();
		// driver.get(System.getProperty("user.dir")+"\\extent-reports\\reports.html");
		// driver.quit();

		
		/*  // Email report after each test case execution which results mails after each testcase exec 
		  report.flush();
		  driver.get(System.getProperty("user.dir")+"\\extent-reports\\reports.html");
		  
		  try{ String reportFileName =
		  System.getProperty("user.dir")+"\\extent-reports\\reports.html";
		  SendAttachmentInEmail email = new SendAttachmentInEmail();
		  email.emailReport(reportFileName);
		  System.out.println("Email sent successfully.."); 
		  } catch (Exception e){
		  e.printStackTrace(); 
		  System.out.println("Could not send email.."); 
		  }*/
		 
	}

	@AfterTest(alwaysRun = true) // Zip report and send Email after complete execution
	public void tearDown() {

		try {

			/*
			 * // Zip report directory File sourceDir = new File
			 * (System.getProperty("user.dir")+"\\target\\surefire-reports"); File
			 * destinationDir = new
			 * File(System.getProperty("user.dir")+"\\Misc\\zipReport\\TestReport.zip");
			 * //ZipFiles zip = new ZipFiles(); ZipFiles.zipReport(sourceDir,
			 * destinationDir); String reportFileName = destinationDir.toString();
			 * System.out.println("Files zipped successfully..");
			 */

			// String reportFileName =
			// System.getProperty("user.dir")+"\\Misc\\zipReport\\TestReport.zip";
			/*
			 * String reportFileName =
			 * System.getProperty("user.dir")+"\\extent-reports\\reports.html"; //
			 * SendAttachmentInEmail email = new SendAttachmentInEmail();
			 * SendAttachmentInEmail.emailReport(reportFileName);
			 * System.out.println("Email sent successfully..");
			 */
			// report.flush();
			//report.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not send email..");
		} finally {
			driver.quit();
		}
	}

}