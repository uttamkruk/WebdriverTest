/*wget-exit-codes
 ================
0       No problems occurred
1       Generic error code
2       Parse error — for instance, when parsing command-line options, the .wgetrc or .netrc…
3       File I/O error
4       Network failure
5       SSL verification failure
6       Username/password authentication failure
7       Protocol errors
8       Server issued an error response */

package utility;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFile {
	
		WebDriver driver;
	    @Test
	    public void downloadViaWget() {
	    	
			// Chrome Browser
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			System.out.println("Chrome starting...");
			
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

	}


