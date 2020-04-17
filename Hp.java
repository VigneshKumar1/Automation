package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Hp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);

		//1) Go to https://store.hp.com/in-en/
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Mouse over on Laptops menu and click on Pavilion
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//span[text()='Laptops'])[1]")).pause(2000).perform();
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		Thread.sleep(2000);
		
		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("(//input[@class='product-filter-checkbox'])[2]").click();
		Thread.sleep(4000);
		
		//4) Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		Thread.sleep(4000);
		
		//5) Select Sort By: Price: Low to High
		WebElement sort = driver.findElementById("sorter");
		Select dropdown = new Select(sort);
		dropdown.selectByValue("price_asc");
		Thread.sleep(4000);
		
		//6) Print the First resulting Product Name and Price
		String productName = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();
		System.out.println("Product Name is : "+productName);
		String price = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		price = price.replaceAll("\\D", "");
		Integer.parseInt(price);
		System.out.println("Product Price is : "+price);
		
		//7) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(2000);
		
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByXPath("//a[@title='Shopping Cart']").click();
		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		Thread.sleep(5000);
		
		//9) Check the Shipping Option --> Check availability at Pincode
		driver.findElementByName("pincode").sendKeys("600019");
		driver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(2000);
		
		//10) Verify the order Total against the product price
		String orderTotal = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		orderTotal = orderTotal.replaceAll("\\D", "");
		Integer.parseInt(orderTotal);
		System.out.println(orderTotal);
		
		//11) Proceed to Checkout if Order Total and Product Price matches
		if (orderTotal.equals(price)) {
			System.out.println("Prices are same");
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
		}else {
			System.err.println("Price Not Matched");
		}
		
		//12) Click on Place Order
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
		
		//13) Capture the Error message and Print
		String errorMessage = driver.findElementByXPath("//div[@class='message notice']").getText();
		System.err.println(errorMessage);
		
		//14) Close Browser
		driver.close();
		
	}

}
