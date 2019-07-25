package Zpractice.Pra;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



public class readcaptcha {
 static WebDriver driver ;
	public static void main(String[] args) throws Exception ,TesseractException , InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notification");
		
		driver=new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("loginText")).click();
		Thread.sleep(10000);
		File src = driver.findElement(By.id("captchaImg")).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/captcha.png" ;
		FileHandler.copy(src, new File(path));
		
		ITesseract image = new Tesseract();
		String imageText = image.doOCR(new File(path));
		System.out.println(imageText);
		String Finalcapta = imageText.split("below")[1].replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(Finalcapta);
		driver.findElement(By.id("nlpAnswer")).sendKeys(Finalcapta);
		
     
	}

}
