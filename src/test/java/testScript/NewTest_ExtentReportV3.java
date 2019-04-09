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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

/*import retryFailedTestcase.RetryFailedTestcase;
import utility.CaptureScreenshot;
import utility.GetLatestFile;
import utility.SendAttachmentInEmail;
import utility.ZipFiles;*/
import utility.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
/*import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;*/
//import Flash.FlashObjectWebDriver;
import listeners.ListenerV3;


@Listeners(listeners.Listener.class)

public class NewTest_ExtentReportV3{
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	//ATUTestRecorder recorder;
	
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	
	public void beforeTest(String browser) throws MalformedURLException {
	//public void beforeTest(String browser) throws MalformedURLException, ATUTestRecorderException {	
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports_"+timeStamp+".html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports.html");
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
		report.setSystemInfo("Environment", "Verification01");
		report.setSystemInfo("Build","10.0.120.46");
		report.setSystemInfo("User Name","Uttam Kumar");
		
		/*// Record ececution video
		String execRecording = "TestExecution Recording_"+timeStamp ;
		recorder = new ATUTestRecorder(System.getProperty("user.dir")+"/Misc/scriptRecorder", execRecording , false);
		recorder.start();*/
		
        
		if(browser.equalsIgnoreCase("firefox")) {

			  /*//Run without exe
			  WebDriverManager.firefoxdriver().setup();*/
			  System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  // if above property is not working or not opening the application in browser then try below property //
			  //System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  driver = new FirefoxDriver();
			  driver.manage().window().maximize();
			
			 /* // Headless mode
			 System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
		     FirefoxBinary firefoxBinary = new FirefoxBinary();
		     firefoxBinary.addCommandLineOptions("--headless");		     
		     FirefoxOptions firefoxOptions = new FirefoxOptions();
		     firefoxOptions.setBinary(firefoxBinary);
		     driver = new FirefoxDriver(firefoxOptions);*/
			 
		}else if(browser.equalsIgnoreCase("ie")) {
			
			 /*//Run without exe
			 WebDriverManager.iedriver().setup();*/
			 System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe"); 
			 driver = new InternetExplorerDriver();
			 driver.manage().window().maximize();
			 
		}else if(browser.equalsIgnoreCase("chrome")) {
			
			/*//Run without exe
			WebDriverManager.chromedriver().setup();*/			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			/*// for headless browser
			options.addArguments("window-size=1400,800","headless");*/
			driver = new ChromeDriver(options);
			System.out.println("Chrome starting...");
			
			/*//Selenium Grid
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			DesiredCapabilities cap=DesiredCapabilities.chrome();			 
			// Set the platform where we want to run our test- we can use MAC and Linux and other platforms as well
			cap.setPlatform(Platform.WINDOWS);			 
			// Here you can use hub address, hub will take the responsibility to execute the test on respective node
			URL url=new URL("http://172.21.192.1:4444/wd/hub");			 
		    driver=new RemoteWebDriver(url, cap);*/
			
		}
}

	// Start - Test to pass as to verify listeners
	@Test(groups = { "Listeners" }, priority = 1, enabled = true)
	public void TestToPass() {

		logger = report.createTest("Verify TestToPass to test listeners").assignCategory("Regression").assignAuthor("UAT");		
		logger.log(Status.INFO, "TestToPass started...");
		System.out.println("Method to Pass the test to test Listeners");
		driver.get("https://www.gmail.com");
		driver.getTitle();
	}
	
	@Test(groups = { "Listeners" }, priority = 2, enabled = true)
	public void TestToFail() {
		
		logger = report.createTest("Verify TestToFail to test listeners").assignCategory("Regression").assignAuthor("UAT");
		logger.log(Status.INFO, "TestToFail started...");
		System.out.println("Method to Fail the test to test Listeners");
		Assert.assertTrue(true);
	}

	// Used skip exception to skip the test
	@Test(groups = { "Listeners" }, priority = 3, enabled = true)
	public void TestToSkip() {
		
		logger = report.createTest("Verify TestToSkip to test listeners").assignCategory("Regression").assignAuthor("UAT");
		logger.log(Status.INFO, "TestToSkip started...");
		System.out.println("Method to Skip the test to test Listeners");
		throw new SkipException("Skipping - This is not ready for testing");
	}
	// End - Test to pass as to verify listeners

	@Test(groups = { "Title_verification" }, priority = 4, enabled = true)
	public void testEasy() throws InterruptedException, IOException {
		
		logger = report.createTest("Verify testEasy Title name").assignCategory("Functional").assignAuthor("UK");
		logger.log(Status.INFO, "Browser Started ...");

		driver.get("https://www.google.com/");
		logger.log(Status.INFO, "Application is up and running ...");
		
		// Capture the Particular WebElement Image
		WebElement element = driver.findElement(By.cssSelector("#hplogo"));
		CaptureScreenshot.getScreenshot(driver, element);
		System.out.println("WebElement is captured successfully..");
		logger.log(Status.PASS, "WebElement is captured successfully...");

		String title = driver.getTitle();		
		Assert.assertTrue(title.contains("Google"));	

		logger.log(Status.PASS, "Tilte Verified Successfully...");			
		
		//logger.createNode("Invalid title name verification");	
		
	}

	@Test(groups = { "Screenshot" }, priority = 5, enabled = true)
	public void captureScreenshot() {

		try {			
			logger = report.createTest("Verify captureScreenshot").assignCategory("Functional").assignAuthor("UK");
			logger.log(Status.INFO, "Capture Screenshot Started ...");
			driver.get("https://www.ebay.in/");

			File oScnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Save in a Physical location
			FileUtils.copyFile(oScnShot, new File(System.getProperty("user.dir") + "\\outfiles\\Screenshot_" + System.currentTimeMillis()+ ".png"));

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
	 
	
	 @Test (priority = 7, enabled = false)
	 public void javaScriptExeMethod() throws InterruptedException{
		 
	 logger = report.createTest("Verify javaScriptExecutor functionality").assignCategory("Selenium").assignAuthor("UK");
	 logger.log(Status.INFO, "javaScriptExecutor functionality Started ...");	 
	 driver.get("https://www.gmail.com");
	 //WebElement loginButton = driver.findElement(By.xpath("//*[@id='next']"));
	 
	 /*Syntax:
	 JavascriptExecutor js = (JavascriptExecutor) driver;  
	 js.executeScript(Script,Arguments);
	 script - The JavaScript to execute
	 Arguments - The arguments to the script.(Optional)*/
	 
               JavascriptExecutor js = (JavascriptExecutor)driver;
               //Uncomment each scenario by using Ctrl + Shift + \ (backslash) and find the solution

               //to type text in Selenium WebDriver without using sendKeys() method
               //js.executeScript("document.getElementById('value of Id in DOM').value='someValue';");
               js.executeScript("document.getElementById('identifierId').value='uttamkr.uk@gmail.com';");
               
               //WebElement nextButton = driver.findElement(By.xpath("//*[@id='identifierNext']/content/span"));
               //to click a button in Selenium WebDriver using JavaScript
               //js.executeAsyncScript("arguments[0].click();", nextButton);
               /*//or
               js.executeScript("document.getElementById('enter your element id').click();");
               js.executeScript("document.getElementById('next').click();");*/

               /*//to handle checkbox
               js.executeScript("document.getElementById('enter element id').checked=false;");*/
                              
	 
				 //to generate Alert Pop window in selenium
				 //js.executeScript("alert('hello world');");
				 
				 /*//to refresh browser window using Javascript 
				 js.executeScript("history.go(0)");*/
				 
				 /*// to get innertext of the entire webpage in Selenium
				 String sText =  js.executeScript("return document.documentElement.innerText;").toString();
				 System.out.println(sText);*/
				 
				 /*//to get the Title of our webpage
				 String sText =  js.executeScript("return document.title;").toString();
				 System.out.println(sText);*/
				 
				 /*//to get the domain
				 String sText =  js.executeScript("return document.domain;").toString();
				 System.out.println(sText);*/
				 
				 /*//to get the URL of our webpage
				 String sText =  js.executeScript("return document.URL;").toString();
				 System.out.println(sText);*/
				 
				 /*//to perform Scroll on application using  Selenium
				 //Vertical scroll - down by 50  pixels
				 js.executeScript("window.scrollBy(0,50)");
				 // for scrolling till the bottom of the page we can use the code like
				 //js.executeScript("window.scrollBy(0,document.body.scrollHeight)");*/
				 
				 /* // to click on a SubMenu which is only visible on mouse hover on Menu?
				 //Hover on Automation Menu on the MenuBar
				         js.executeScript("$('ul.menus.menu-secondary.sf-js-enabled.sub-menu li').hover()");*/
				 
				 /*//to navigate to different page using Javascript?
				         //Navigate to new Page
				         js.executeScript("window.location = 'https://www.softwaretestingmaterial.com");*/
               
              /*// Get Attribute | Text -->  OUTPUT | content
               driver.get("http://google.co.in/");
               Object exampleDiv = ((JavascriptExecutor) driver).executeScript("return document.getElementById('main');");
               String name = ((WebElement) exampleDiv).getAttribute("class");
               System.out.println(name);
               
              // Last Modified --> OUTPUT |  lastModified  : 01/29/2014 14:56:46
               driver.get("http://google.co.in/");
               js = (JavascriptExecutor) driver;
               String lastModified = (String) js.executeScript("return document.lastModified");
               System.out.println("lastModified  : " + lastModified);
               
              // Ready state --> OUTPUT | readyState  : complete
               driver.get("http://google.co.in/");
               js = (JavascriptExecutor) driver;
               String readyState = (String) js.executeScript("return document.readyState");
               System.out.println("readyState  : " + readyState);*/
               
	 }
	
	
	 @Test (groups = { "My testing1" } ,priority = 8, enabled = true)
	 public void brokenLinks() {
		 
		 	logger = report.createTest("Verify broken link").assignCategory("Functional").assignAuthor("UK");
			logger.log(Status.INFO, "Browser Started ...");
		 	String homePage = "http://www.freecrm.com";
	        String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
	        driver.get(homePage);
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        
	        Iterator<WebElement> it = links.iterator();	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");	            
	            System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	            	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(homePage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                // Set method as "HEAD" to get only headers instead of GET which will give whole request body
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                    logger.log(Status.FAIL,"Broken link is found...");
	                }
	                else{
	                	logger.log(Status.INFO, url+" is a valid link and Response code is :--> "+respCode);
	                    System.out.println(url+" is a valid link");
	                    
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            
	        }
	        
	        logger.log(Status.PASS, "Links verified Successfully...");
	    }
	 
	// File download operation through Wget
    @Test(groups = { "My testing1" } , priority = 9 , enabled = false)
    public void downloadFileByWget() throws InterruptedException {
    	
    	logger = report.createTest("Verify download file by Wget").assignCategory("Functional").assignAuthor("UK");
		logger.log(Status.INFO, "Verification of downloading file has Started ...");
				
		driver.get("http://demo.guru99.com/test/yahoo.html");			
		WebElement downloadButton = driver.findElement(By.id("messenger-download"));
		String sourceLocation = downloadButton.getAttribute("href");
		String wget_command = "cmd /c  C:\\Testing\\wget-1.19.4-win32\\wget.exe -P C:\\Users\\uttkumar\\Downloads --no-check-certificate " + sourceLocation;
		
		try {
			Process exec = Runtime.getRuntime().exec(wget_command);
			int exitVal = exec.waitFor();
			System.out.println("Exit value: " + exitVal);
		 } catch (InterruptedException | IOException ex) {
			System.out.println(ex.toString());
		}
		driver.close();
	}
	
    @Test(groups = {"My testing1"} , priority = 10 , enabled = false)
    public void autoItOperation() throws IOException {
    	logger = report.createTest("Verify AutoIT File operations").assignCategory("File Operation").assignAuthor("UK");
    	logger.log(Status.INFO, "Verification of AutoIT file operation started...");
    	
    	driver.get("http://only-testing-blog.blogspot.com/2014/05/login.html");
    	driver.findElement(By.xpath("//a[contains(.,'Download Text File')]")).click();
    	//Execute Script To Download File.exe file to run AutoIt script. File location = C:\Testing\autoit-v3\install
    	Runtime.getRuntime().exec("C:\\Testing\\autoit-v3\\install\\Download File.exe");
    }
    
    @Test(groups = {"My testing"} , priority = 11 , enabled = true)
    public void ListFilesUtilOperation() throws IOException {
    	logger = report.createTest("Verify FileList operations").assignCategory("File Operation").assignAuthor("UK");
    	logger.log(Status.INFO, "Verification of FileList operations started...");
    	
    	String directoryPath =System.getProperty("user.dir");
    	ListFilesUtil fu = new ListFilesUtil();
    	System.out.println("***************List all the files under a directory*******************");
    	fu.listFiles(directoryPath);
    	System.out.println("*****************List all the folder under a directory******************");
    	fu.listFolders(directoryPath);
    	System.out.println("*****************List all the files and folders from a directory******************");
    	fu.listFilesAndFolders(directoryPath);
    	System.out.println("*****************List all files from a directory and its subdirectories******************");
    	fu.listFilesAndFilesSubDirectories(directoryPath);
    	System.out.println("*****************Get the newest file with extension from a directory******************");
    	fu.getTheNewestFile(directoryPath, ".war");
    }    
    
    /*@Test(groups = {"My testing"} , priority = 12 , enabled = false)
    public void flashTesting() throws InterruptedException {

    	FlashObjectWebDriver  flashApp = new FlashObjectWebDriver (driver , "myFlashMovie");
    	driver.get("http://demo.guru99.com/test/flash-testing.html");
    	Thread.sleep(5000);
    	flashApp.callFlashObject("Play");
    	Thread.sleep(10000);
    	flashApp.callFlashObject("StopPlay");
    	Thread.sleep(5000);
    	flashApp.callFlashObject("SetVariable","/:message","Flash testing using selenium Webdriver");
        System.out.println(flashApp.callFlashObject("GetVariable","/:message"));
    	
    }*/
    
	/*@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		System.out.println("Method name is : "+testName);
		logger = report.createTest(testName);		
	}*/

	@AfterMethod(alwaysRun = true) // ITestResult will work only with @AfterMethod (It will run after each @Test)
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotName = CaptureScreenshot.captureScreenshot(driver, result.getMethod().getMethodName());				
			logger.fail("Fail due to some issue", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
			logger.fail(result.getThrowable());
			//logger.addScreenCaptureFromPath("Fail due to some issue", screenshotName);
		}else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(Status.SKIP, "Test Case Skipped is -: "+result.getMethod().getMethodName());
		 }else if(result.getStatus() == ITestResult.SUCCESS){
			 logger.log(Status.PASS, "Test Casename as -: "+result.getMethod().getMethodName()+" is Passed");
			 /*String screenshotName = CaptureScreenshot.captureScreenshot(driver, result.getMethod().getMethodName());
			 logger.pass("Successfully executed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());*/
		 }
		   report.flush();
		
		 
	}

	@AfterTest(alwaysRun = true) // Zip report and send Email after complete execution
	public void tearDown() {
	//public void tearDown() throws ATUTestRecorderException {
		try {

			
			 /*// Zip report directory 
			  File sourceDir = new File(System.getProperty("user.dir")+"\\target\\surefire-reports"); 
			  File destinationDir = new File(System.getProperty("user.dir")+"\\Misc\\zipReport\\TestReport.zip");
			  ZipFiles zip = new ZipFiles(); 
			  ZipFiles.zipReport(sourceDir,destinationDir); 
			  String reportFileName = destinationDir.toString();
			  System.out.println("Files zipped successfully..");*/
			
			 /*//String reportFileName = System.getProperty("user.dir")+"\\extent-reports\\Reports.html";
			  File reportFileName = GetLatestFile.getTheNewestFile(System.getProperty("user.dir")+"/extent-reports/","html");			  
			  SendAttachmentInEmail.emailReport(reportFileName.toString());
			  System.out.println("Email sent successfully..");*/
			 
			// report.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not send email..");
		} finally {
			driver.quit();
			//recorder.stop();
		}
	}

}