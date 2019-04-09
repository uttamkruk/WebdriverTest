package utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class CaptureScreenshot {

	WebDriver driver;
	
	// Capture Image of a WebPage 
	public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
			
			TakesScreenshot oScn = (TakesScreenshot) driver;			
			File source = oScn.getScreenshotAs(OutputType.FILE);			
			String dest = System.getProperty("user.dir")+"\\Misc\\screenshot\\"+screenshotName+".png";						
						
			FileUtils.copyFile(source, new File (dest)); 				
			System.out.println("Screenshot taken ...");			
		
		//return screenshotName;
		return dest;
		
	}
	
	// Capture Image of a particular WebElement 
	public static Image getScreenshot(WebDriver driver, WebElement element) throws IOException {
		
		
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			BufferedImage  fullImg = ImageIO.read(screenshot);
			
		try {
			// Get the location of element on the page
			Point point = element.getLocation();
	
			// Get width and height of the element
			int eleWidth = element.getSize().getWidth();
			int eleHeight = element.getSize().getHeight();
	
			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			    eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);
	
			// Copy the element screenshot to disk
			File screenshotLocation = new File(System.getProperty("user.dir")+"/screenshot/WebElement_screenshot"+System.currentTimeMillis()+".png");
			FileUtils.copyFile(screenshot, screenshotLocation);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
			return fullImg;
	}

}
