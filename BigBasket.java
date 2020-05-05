package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BigBasket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.bigbasket.com/
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) mouse over on  Shop by Category
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()=' Shop by Category ']")).pause(2000).perform();
		
		//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		builder.moveToElement(driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]")).pause(2000).perform();
		driver.findElementByLinkText("Rice & Rice Products").click();
		Thread.sleep(2000);
		
		//4) Click on Boiled & Steam Rice
		driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").click();
		Thread.sleep(2000);
		
		//5) Choose the Brand as bb Royal
		driver.findElementByXPath("(//i[contains(@class,'cr-icon fa')])[3]").click();
		Thread.sleep(2000);
		
		//6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		driver.findElementByXPath("(//i[@class='fa fa-caret-down'])[3]").click();
		driver.findElementByXPath("(//span[text()='5 kg'])[6]").click();
		
		//7) print the price of Rice
		String price = driver.findElementByXPath("//span[text()='336']").getText();
		System.out.println("Price of Rice :"+price);
		
		//8) Click Add button
		driver.findElementByXPath("(//button[text()='Add '])[3]").click();
		driver.findElementByXPath("(//a[text()='Continue'])[1]").click();
		
		//9) Verify the success message displayed
		//String message = driver.findElementByXPath("//div[text()='Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket']").getText();
		//String message = driver.findElementByXPath("//div[@class='toast-title']").getText();
		//System.out.println(message);
		//System.out.println(driver.findElementByClassName("toast-title").getText());
		
		//10) Type Dal in Search field and enter
		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
		Thread.sleep(4000);
		
		//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		driver.findElementByXPath("(//i[@class='fa fa-caret-down'])[2]").click();
		driver.findElementByXPath("(//span[text()='2 kg'])[3]").click();
		
		driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]").clear();
		driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]").sendKeys("2");
		
		//13) Print the price of Dal
		String dalPrice = driver.findElementByXPath("//span[text()='219']").getText();
		System.out.println("Dal Price :"+dalPrice);
		
		//14) Click Add button
		driver.findElementByXPath("(//button[text()='Add '])[3]").click();
		
		//15) Mouse hover on My Basket 
		builder.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).pause(2000).perform();
		
		//16) Validate the Sub Total displayed for the selected items
		String product1 = driver.findElementByXPath("(//div[@class='row mrp']/span)[1]").getText();
		String product2 = driver.findElementByXPath("(//div[@class='row mrp']/span)[2]").getText();
		int product2new = Integer.parseInt(product2);
		int sum = Integer.parseInt(product1)+(product2new*2);
		//System.out.println(sum);
		String total = driver.findElementByXPath("//span[@style='float: right']/span").getText();
		int subTotal = Integer.parseInt(total);
		
		if (sum == subTotal) {
			System.out.println("Sub Total matches with the product amount");
		}else {
			System.err.println("Amount mismatch");
		}
		
		//17) Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//button[text()='−'])[2]").click();
		
		//18) Validate the Sub Total for the current items
		int sum1 = Integer.parseInt(product1)+product2new;
		String total1 = driver.findElementByXPath("//span[@style='float: right']/span").getText();
		int newTotal = Integer.parseInt(total1);
		
		if (sum1 == newTotal) {
			System.out.println("Amount Matches");
		}else {
			System.err.println("Amount not matches");
		}
		
		//19) Close the Browser
		driver.close();
	}
	
}

