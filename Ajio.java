package testCases;

//import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//1) Go to https://www.ajio.com/shop/sale
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByName("searchVal").sendKeys("Bags");
		driver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();
		Thread.sleep(2000);
		
		//3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid']").click();
		
		Select select = new Select(driver.findElementByTagName("select"));
		select.selectByValue("newn");
		Thread.sleep(2000);
		
		//4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementById("minPrice").sendKeys("2000");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		Thread.sleep(2000);
		
		//5) Click on the product "Puma Ferrari LS Shoulder Bag"
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		
		//6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		String couponCode = driver.findElementByXPath("//div[@class='promo-title']").getText();
		//System.out.println(couponCode);
		String discountAmount = driver.findElementByXPath("//div[@class='promo-discounted-price']//span").getText();
		String amount = discountAmount.replaceAll("[^0-9]", "");
		
		if (driver.findElementByXPath("//div[contains(text(),'Upto')]").isDisplayed()) {
			//couponCode = driver.findElementByXPath("//div[@class='promo-title']//br").getText();
			System.out.println(couponCode);
			System.out.println(amount);
		}else {
			System.out.println("No Discount");
		}
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("//span[contains(text(),'pin-code')]").click();
		
		driver.findElementByName("pincode").sendKeys("682001");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
		Thread.sleep(2000);
		
		//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		driver.findElementByXPath("//div[text()='Other information']").click();
		String customerCareDetails = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		System.out.println(customerCareDetails);
		
		//9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(8000);
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		Thread.sleep(2000);
		
		//10) Check the Order Total before apply coupon
		String orderTotal = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		System.out.println(orderTotal);
		
		//11) Enter Coupon Code and Click Apply
		driver.findElementById("couponCodeInput").sendKeys("EPIC");
		driver.findElementByXPath("//button[text()='Apply']").click();
		Thread.sleep(2000);
		
		//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
		String savings = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
		System.out.println(savings);
		//String finalPrice = driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
		//System.out.println(finalPrice);
		//String final1 = finalPrice.replaceAll("[^0-9]", "");
		driver.findElementByXPath("//div[text()='Savings :']").click();
		String finalSavings = driver.findElementByXPath("//div[@class='coupon-discount-section']//span").getText();
		System.out.println(finalSavings);
		
		if (savings.equals(finalSavings)) {
			System.out.println("Amount Matches");
		}else {
			System.out.println("Amount not matched");
		}
		
		driver.findElementByXPath("//div[text()=' Done ']").click();
		
		//13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[text()='Delete']").click();
		driver.findElementByXPath("//div[text()='DELETE']").click();
		Thread.sleep(2000);
		
		//14) Close all the browsers
		driver.quit();
	}

}
