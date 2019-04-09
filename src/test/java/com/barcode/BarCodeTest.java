package com.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeTest {

	WebDriver driver;
	@Test
	public void barCodeTest() throws IOException, NotFoundException {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		/*// for headless browser
		options.addArguments("window-size=1400,800","headless");*/
		driver = new ChromeDriver(options);
		System.out.println("Chrome starting...");
		
		driver.get("https://barcode.tec-it.com/en/Code128?data=Hi this is Uttam...!");
		//String barCodeURL = driver.findElement(By.tagName("img")).getAttribute("src");
		String barCodeURL = driver.findElement(By.cssSelector("div#infoTarget > div.barcode > img")).getAttribute("src");
		System.out.println(barCodeURL);
		
		URL url = new URL(barCodeURL);
		BufferedImage bufferedImage = ImageIO.read(url);
		
		LuminanceSource luminanceSource =  new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result = new MultiFormatReader().decode(binaryBitmap);
		System.out.println(result.getText());
	}
}
