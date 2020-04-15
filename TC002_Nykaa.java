package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC002_Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--diable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Mouseover on Brands and Mouseover on Popular
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()='brands']")).pause(2000).perform();
		builder.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).pause(2000).perform();
		
		//3) Click L'Oreal Paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(2000);
		String title = driver.getTitle();
		String text = "L'Oreal Paris";
		if(title.contains(text)) {
			System.out.println("Title Matches");
		}else {
			System.err.println("Title Not Matched");
		}
		
		//5) Click sort By and select customer top rated
		driver.findElementByXPath("(//span[text()='popularity'])[1]").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		Thread.sleep(2000);
		
		//6) Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[text()='Shampoo (16)']").click();  ////span[text()='Shampoo (16)']/following-sibling::div
		Thread.sleep(2000);
		
		//7) check whether the Filter is applied with Shampoo
		String filter = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		System.out.println(filter);
		
		if (filter.contains("Shampoo")) {
			System.out.println("Selected filter is Shampoo");
		}else {
			System.err.println("Selected filter is not Shampoo");
		}
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains(text(),'Colour Protect')]").click();
		
		//9) GO to the new window and select size as 175ml
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windowList1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(windowList1.get(2));
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//10) Print the MRP of the product
		String price = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println(price);
		
		//11) Click on ADD to BAG
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		Thread.sleep(2000);
		
		//12) Go to Shopping Bag 
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		Thread.sleep(2000);
		
		//13) Print the Grand Total amount
		driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		
		//14) Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();
		Thread.sleep(2000);
		
		//15) Click on Continue as Guest
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		
		//16) Print the warning message (delay in shipment)
		String errorMessage = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println(errorMessage);
		
		//17) Close all windows
		driver.quit();
		
		
	}

}
