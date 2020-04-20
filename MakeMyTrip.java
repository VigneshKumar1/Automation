package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Click Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();
		
		//3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//span[text()='City / Hotel / Area / Building']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class='flexOne'])[1]").click();       ////p[text()='Goa, India']
		
		//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementById("checkin").click();
		//String text = driver.findElementByXPath("(//div[@class='DayPicker-Caption'])[1]").getText();
		//System.out.println(text);
		String startDate = driver.findElementByXPath("(//div[text()='15'])[2]").getText();
	    driver.findElementByXPath("(//div[text()='15'])[2]").click();
	    int endDate = Integer.parseInt(startDate)+5;
		driver.findElementByXPath("(//div[text()='"+endDate+"'])[2]").click();
		
		
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		driver.findElementByClassName("ageSelectBox").click();
		driver.findElementByXPath("//option[text()='12']").click();
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
		//6) Click Search button
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		
		//7) Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(2000);
		
		//8) Select 5 star in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();
		Thread.sleep(2000);
		
		//9) Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("(//p[@id='hlistpg_hotel_name'])[1]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(2000);
		
		//10) Print the Hotel Name
		String hotelName = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
		System.out.println(hotelName);
		
		//11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("(//span[text()='MORE OPTIONS'])[1]").click();
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		driver.findElementByXPath("//span[@class='close']").click();
		
		//12) Click on BOOK THIS NOW
		driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
		
		//13) Print the Total Payable amount
		String amount = driver.findElementById("revpg_total_payable_amt").getText();
		System.out.println(amount);
		
		//14) Close the browser
		driver.close();
		
	}

}
