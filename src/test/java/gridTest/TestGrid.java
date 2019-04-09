//hub : java -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role hub
//node 1 : java -Dwebdriver.chrome.driver=C:\Testing\WebdriverTest\driver\chromedriver.exe -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role node -hub http://192.168.56.1:4444/grid/register 
//node 2 : java -Dwebdriver.chrome.driver=C:\Testing\WebdriverTest\driver\chromedriver.exe -jar C:\Testing\WebdriverTest\selenium-server-standalone-3.13.0.jar -role node -hub http://192.168.56.1:4444/grid/register   -port 5555

package gridTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class TestGrid {
 static WebDriver driver;
 static String nodeUrl;
 
 @BeforeTest
 public void setup() throws MalformedURLException {
	 
 //hubUrl = "http://localhost:4444/wd/hub";	 
 nodeUrl = "http://192.168.246.113:5557/wd/hub";
 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
 capabilities.setBrowserName("chrome");
 capabilities.setPlatform(Platform.WINDOWS);
 driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
 
 
 /*hubUrl = "http://localhost:4444/wd/hub"; 
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--start-maximized");
 //options.addArguments("window-size=1400,800","headless");  // Running in Headless mode
 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
 capabilities.setCapability(ChromeOptions.CAPABILITY, options);
 capabilities.setPlatform(Platform.WIN10);
 capabilities.setBrowserName("chrome");
 driver = new RemoteWebDriver(new URL(hubUrl), capabilities);*/
 
 }
 
 @Test
 public void simpleTest() {
 driver.get("https://www.edureka.co/");
 Assert.assertEquals("Instructor-Led Online Training with 24X7 Lifetime Support | Edureka", driver.getTitle());
 }
 
 @AfterTest
 public void afterTest() {
 driver.quit();
 }
}
