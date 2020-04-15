package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC001_Myntra {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--diable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Mouse over on WOMEN (Actions -> moveToElement
		WebElement women = driver.findElementByLinkText("Women");
		Actions act = new Actions(driver);
		act.moveToElement(women).pause(2000).perform();
		
		//3) Click Jackets & Coats
		driver.findElementByLinkText("Jackets & Coats").click();
		
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		
		//4) Find the total count of item (top) -> getText -> String
		String str = driver.findElementByClassName("title-count").getText();
		String text = str.replaceAll("\\D", "");
		int first = Integer.parseInt(text);
		System.out.println(first);
		
		//5) Validate the sum of categories count matches
		String cat1 = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String str1 = cat1.replaceAll("\\D", "");
		int int1 = Integer.parseInt(str1);
		System.out.println("Jackets"+int1);
		
		String cat2 = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String str2 = cat2.replaceAll("\\D", "");
		int int2 = Integer.parseInt(str2);
		System.out.println("Coats"+int2);
		
		int total = int1+int2;
		
		if (first==total) {
			System.out.println("Count Matches");
		}else {
			System.out.println("Count not matched");
		}
		
		//6) Check Coats
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		Thread.sleep(5000);
		
		//7) Click + More option under BRAND
		driver.findElementByXPath("//div[@class='brand-more']").click();
		Thread.sleep(4000);
		
		//8) Type MANGO and click checkbox
		driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("MANGO");
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[11]").click();
		Thread.sleep(2000);
		
		//9) Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		
		//10) Confirm all the Coats are of brand MANGO
		List<WebElement> products = driver.findElementsByXPath("//h3[@class='product-brand']");
		int brandCount=0;
		for (WebElement eachProduct : products) {
			if(eachProduct.getText().equals("MANGO")) {
				brandCount=brandCount+1;
			}
		}
		
		if(brandCount==products.size()) {
			System.out.println("All Products are MANGO brand");
		}
		
		//11) Sort by Better Discount
		act.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).pause(2000).perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		Thread.sleep(4000);
		
		//12) Find the price of first displayed item
		     //findElements (price) -> List<WebElement> 
		     //get(0) -> WebElement -> getText -> String -> int
		
		List<WebElement> priceList = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String price = priceList.get(0).getText();
		System.out.println("The Price of first item: "+price);
		
		//13) Mouse over on size of the first item
		act.moveToElement(driver.findElementByTagName("(//div[@class='product-productMetaInfo'])[1]")).perform();
		
		//14) Click on WishList Now
		driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click();
		
		//15) Close Browser
		driver.close();
	}

}