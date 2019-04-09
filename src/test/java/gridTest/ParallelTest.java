//hub : java -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role hub
//node 1 : java -Dwebdriver.chrome.driver=C:\Testing\WebdriverTest\driver\chromedriver.exe -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role node -hub http://192.168.56.1:4444/grid/register 
//node 2 : java -Dwebdriver.gecko.driver=C:\Testing\WebdriverTest\driver\geckodriver.exe -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role node -hub http://192.168.56.1:4444/grid/register   -port 5555

package gridTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class ParallelTest {
	
 static RemoteWebDriver driver;
 //static String hubUrl;
 
	 @BeforeTest
	 @Parameters({"platform","browser","nodeUrl"})
	 public void setup(String platform , String browser, String nodeUrl) throws MalformedURLException {
		 
		 DesiredCapabilities capabilities = null; 
		 if(browser.equals("firefox")) {
			 //System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			 FirefoxOptions options = new FirefoxOptions();
			 options.addArguments("--start-maximized");
			 capabilities = DesiredCapabilities.firefox();
			 capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			 capabilities.setBrowserName("firefox");
			 capabilities.setPlatform(Platform.WIN10);
			 
		     /*FirefoxBinary firefoxBinary = new FirefoxBinary();
		     //firefoxBinary.addCommandLineOptions("--headless");		     
		     FirefoxOptions options = new FirefoxOptions();
		     options.setBinary(firefoxBinary);
		     options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); */
			 
			        
			 
		 } else if(browser.equals("chrome")) {
			 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("--start-maximized");
			 //options.addArguments("window-size=1400,800","headless");  // Running in Headless mode
			 capabilities = DesiredCapabilities.chrome();
			 capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			 capabilities.setBrowserName("chrome");
			 capabilities.setPlatform(Platform.WIN10);
			 //capabilities.setPlatform(Platform.WINDOWS);
		 }
	 
		 driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
		 driver.get("https://www.google.co.in/");
	 
	 
	 }
	 
	 @Test
	 public void googleSearch() throws InterruptedException {
			 //driver.get("https://www.google.co.in/");
			 driver.findElementByName("q").sendKeys("execute automation");
			 driver.findElementByName("btnK").submit();
			 Thread.sleep(3000);
	 }
 
	 @Test
	 public void clickResLink() throws InterruptedException {
			 driver.findElementByPartialLinkText("Execute Automation").click();
	
	 }
	 
	 @AfterSuite
	 public void afterTest() {
		 	driver.quit();
	 }
	 
 }
