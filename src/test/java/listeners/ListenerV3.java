package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.types.resources.comparators.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.CaptureScreenshot;

public class ListenerV3 implements ITestListener {
	
	 /*protected static WebDriver driver;
	 protected static ExtentReports reports;
	 protected static ExtentTest test;*/
	 
	 protected static ExtentHtmlReporter htmlReporter;
	 protected static ExtentReports report;
	 protected static ExtentTest logger;
	 public static WebDriver driver;
	 
	 public void onTestStart(ITestResult result) {
	  System.out.println("on test start");
	  logger = report.createTest(result.getMethod().getMethodName()).assignCategory("Functional").assignAuthor("UK");	
	  logger.log(Status.INFO, result.getMethod().getMethodName() + " test is started");
	 }
	 
	 public void onTestSuccess(ITestResult result) {
	  System.out.println("on test success");
	  logger.log(Status.PASS, result.getMethod().getMethodName() + " test is passed");
	  //logger.pass(result.getMethod().getMethodName() + "test is passed");
	  
	 }
	 
	 public void onTestFailure(ITestResult result) {
	  System.out.println("on test failure");
	  logger.log(Status.FAIL, result.getMethod().getMethodName() + " test is failed");
	  
	  
	 }
	 
	 public void onTestSkipped(ITestResult result) {
	  System.out.println("<<<<<<<<<<<<<<<<<<<< On Test Skipped >>>>>>>>>>>>>>>>>>>>");
	  logger.log(Status.SKIP, result.getMethod().getMethodName() + " test is skipped");
	  logger.skip(result.getThrowable().getMessage());
	 }
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	  System.out.println("on test sucess within percentage");
	 }
	 public void onStart(ITestContext context) {
	  System.out.println("*************** Test Suite start *************** ");

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports_" + System.currentTimeMillis()+ ".html");
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
		  report.setSystemInfo("User Name","Uttam Kumar");
		
	 }
	 public void onFinish(ITestContext context) {
	  System.out.println("******************* Test case finished *********************");
	  report.flush();
	  //driver.close();
	  
	 }
	}