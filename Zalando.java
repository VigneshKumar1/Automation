package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//ChromeDriver driver = new ChromeDriver();
		
		//1) Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Get the Alert text and print it
		Alert alert = driver.switchTo().alert();
		alert.getText();
		System.out.println(alert);
		
		//3) Close the Alert box and click on Zalando.uk
		alert.accept();
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='Zalando.uk']")).click();
		Thread.sleep(4000);
		
		//4) Click Women--> Clothing and click Coat
		//driver.findElement(By.xpath("//span[2]"));
		
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		
		driver.findElement(By.xpath("(//span[text()='Women'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Clothing']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[text()='Coats'])[3]")).click();
		Thread.sleep(2000);
		
		//5) Choose Material as cotton (100%) and Length as thigh-length
		driver.findElement(By.xpath("//span[text()='Material']")).click();
		driver.findElement(By.xpath("//span[text()='Cotton']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[text()='Length']")).click();
		driver.findElement(By.xpath("//span[text()='thigh-length']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		
		//6) Click on Q/S designed by MANTEL - Parka coat
		driver.findElement(By.xpath("//div[text()='Q/S designed by']")).click();
		
		//7) Check the availability for Color as Olive and Size as 'M'
		driver.findElement(By.xpath("(//img[@alt='olive'])[2]")).click();
		Thread.sleep(2000);
		
		try {
			String out = driver.findElement(By.xpath("//h2[contains(text(),'Out')]")).getText();
			System.out.println(out);
			if (out.equals("Out of stock")) {
				driver.findElement(By.xpath("(//img[@alt='navy'])[2]")).click();
			}else {
				System.out.println("Selected color is in stock");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		Thread.sleep(2000);
		//8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		driver.findElement(By.xpath("//span[text()='Choose your size']")).click();
		driver.findElement(By.xpath("//span[text()='M']")).click();
		
		//9) Add to bag only if Standard Delivery is free
		String sd = driver.findElement(By.xpath("(//span[@class='AtOZbZ'])[2]")).getText();
		System.out.println("Standard Delivery is " +sd);
		if (sd.equals("Free")) {
			driver.findElement(By.xpath("//span[text()='Add to bag']")).click();
		}else {
			System.out.println("Standard Delivery is not free");
		}
		Thread.sleep(2000);
		
		//10) Mouse over on Your Bag and Click on "Go to Bag"
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span[@class='z-text z-navicat-header_navToolCounter z-text-cta z-text-black'])[2]"))).pause(2000).perform();
		driver.findElement(By.xpath("//div[text()='Go to bag']")).click();
		
		//11) Capture the Estimated Deliver Date and print
		String date = driver.findElement(By.xpath("//div[@data-id='delivery-estimation']")).getText();
		System.out.println(date);
		
		//12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Free delivery & returns*']"))).pause(2000).perform();
		String tipText = driver.findElement(By.xpath("//a[text()='Free delivery & returns*']/parent::span")).getAttribute("title");
		System.out.println(tipText);
		
		//13) Click on Start chat in the Start chat and go to the new window
		driver.findElement(By.xpath("//a[text()='Free delivery & returns*']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[text()='Start chat']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		
		//14) Enter you first name and a dummy email and click Start Chat
		driver.findElement(By.id("prechat_customer_name_id")).sendKeys("Vicky");
		driver.findElement(By.id("prechat_customer_email_id")).sendKeys("hello@abc.com");
		driver.findElement(By.xpath("//span[text()='Start Chat']")).click();
		Thread.sleep(8000);
		
		//15) Type Hi, click Send and print thr reply message and close the chat window.
		driver.findElement(By.id("liveAgentChatTextArea")).sendKeys("Hi");
		driver.findElement(By.xpath("//button[text()='Send']")).click();
		String chatText = driver.findElement(By.xpath("//span[contains(text(),'Hi there! How')]")).getText();
		System.out.println(chatText);
		driver.close();
	}

}
