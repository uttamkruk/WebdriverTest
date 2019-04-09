package utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class Example {
	
public static void main(String[] args) {
System.out.println(System.getProperty("user.dir"));

File firebug = new File(System.getProperty("user.dir") + "\\driver\\firebug-2.0.3.xpi");
File netExport = new File(System.getProperty("user.dir") + "\\driver\\netExport-0.9b3.xpi");

System.setProperty("webdriver.gecko.driver", "C:\\Users\\212552591\\workspace\\Testing\\WebdriverTest\\driver\\geckodriver.exe");
FirefoxProfile profile = new FirefoxProfile();

/*//Firefox Browser 
System.setProperty("webdriver.gecko.driver", "C:\\Users\\212552591\\workspace\\Testing\\WebdriverTest\\driver\\geckodriver.exe");
   // if above property is not working or not opening the application in browser then try below property //
  //System.setProperty("webdriver.firefox.marionette", "C:\\Users\\212552591\\workspace\\Testing\\WebdriverTest\\driver\\geckodriver.exe"); 
 
driver = new FirefoxDriver();*/

/*ProfilesIni allProfs = new ProfilesIni(); // load all profiles
FirefoxProfile myProfile = allProfs.getProfile("default");		
WebDriver driver = new FirefoxDriver(myProfile);*/


try {
profile.addExtension(firebug);
profile.addExtension(netExport);

} catch (Exception e) {
e.printStackTrace();
}

profile.setPreference("app.update.enabled", false);

//Setting Firebug preferences
profile.setPreference("extensions.firebug.currentVersion", "2.0");
profile.setPreference("extensions.firebug.addonBarOpened", true);
profile.setPreference("extensions.firebug.console.enableSites", true);
profile.setPreference("extensions.firebug.script.enableSites", true);
profile.setPreference("extensions.firebug.net.enableSites", true);
profile.setPreference("extensions.firebug.previousPlacement", 1);
profile.setPreference("extensions.firebug.allPagesActivation", "on");
profile.setPreference("extensions.firebug.onByDefault", true);
profile.setPreference("extensions.firebug.defaultPanelName", "net");

// Setting netExport preferences
profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
profile.setPreference("extensions.firebug.netexport.Automation", true);
profile.setPreference("extensions.firebug.netexport.showPreview", false);
profile.setPreference("extensions.firebug.netexport.defaultLogDir", "C:\\Users\\212552591\\Desktop\\CaptureNetworkTraffic");

DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setBrowserName("firefox");
capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
capabilities.setCapability(FirefoxDriver.PROFILE, profile);

WebDriver driver = new FirefoxDriver(capabilities);
try {
Thread.sleep(10000);
driver.get("http://www.gaana.com");
JavascriptExecutor js= (JavascriptExecutor)driver;
//js.executeScript("window.NetExport.triggerExport('abcd')");
js.executeScript("HAR.triggerExport('harFile')");
Thread.sleep(10000);
} catch(InterruptedException ie) {
ie.printStackTrace();
}
driver.quit();
}
}