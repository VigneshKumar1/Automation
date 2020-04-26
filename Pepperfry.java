package testCases;

//import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("(//a[text()='Furniture'])[1]")).pause(2000).perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		
		//3) click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		
		//4) Change the minimum Height as 50 in under Dimensions
		WebElement height = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[2]");
		height.clear();
		height.sendKeys("50", Keys.ENTER);
		Thread.sleep(2000);
		
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		
		//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		act.moveToElement(driver.findElementByXPath("(//a[text()='Homeware'])[1]")).pause(2000).perform();
		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
		Thread.sleep(2000);
		
		//7) Select Prestige as Brand
		driver.findElementByXPath("//li[@data-search='Prestige']//label[1]").click();
		Thread.sleep(2000);
		
		//8) Select Capacity as 1-3 Ltr
		driver.findElementByXPath("//label[text()='1 Ltr - 3 Ltr']").click();
		Thread.sleep(2000);
		
		//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		driver.findElementByXPath("(//a[@id='clip_wishlist_'])[3]").click();
		
		//10) Verify the number of items in Wishlist
		String count = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		System.out.println(count);
		if (count.equals("2")) {
			System.out.println("Count Matches");
		}else {
			System.err.println("Count Mismatch");
		}
		
		//11) Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		
		//12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("(//a[@class='addtocart_icon'])[2]").click();
		
		//13) Check for the availability for Pincode 600128
		driver.findElementByXPath("//div[@class='pin_block']//input[1]").sendKeys("600128");
		driver.findElementByXPath("//a[text()='Check']").click();
		Thread.sleep(2000);
		
		//14) Click Proceed to Pay Securely
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		
		//15 Click Proceed to Pay
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		
		//16) Capture the screenshot of the item under Order Item
		driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-right']//span)[1]").click();
		Thread.sleep(2000);
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snap1.png");
		FileUtils.copyFile(src, dst);
		
		//17) Close the browser
		driver.close();
	}

}
