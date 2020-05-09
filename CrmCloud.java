package testCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class CrmCloud {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://demo.1crmcloud.com/
		driver.get("https://demo.1crmcloud.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
		
		//3) Choose theme as Claro Theme
		WebElement theme = driver.findElementById("login_theme");
		Select dropdown = new Select(theme);
		dropdown.selectByVisibleText("Claro Theme");
		driver.findElementByXPath("//span[text()='Login']").click();
		Thread.sleep(2000);
		
		//4) Click on Sales and Marketing
		driver.findElementByXPath("//div[text()='Sales & Marketing']").click();
		
		//5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		
		//6) Select Title and type First name, Last Name, Email and Phone Numbers
		driver.findElementByXPath("//div[@id='DetailFormsalutation-input']").click();
		driver.findElementByXPath("//div[text()='Mr.']").click();
		driver.findElementByXPath("//input[@name='first_name']").sendKeys("Vignesh");
		driver.findElementByXPath("//input[@name='last_name']").sendKeys("Kumar");
		driver.findElementByXPath("//input[@name='email1']").sendKeys("hello@gmail.com");
		driver.findElementByXPath("//input[@name='phone_work']").sendKeys("9840123456");
		
		//7) Select Lead Source as "Public Relations"
		driver.findElementById("DetailFormlead_source-input").click();
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		
		//8) Select Business Roles as "Sales"
		driver.findElementById("DetailFormbusiness_role-input").click();
		driver.findElementByXPath("//div[text()='Sales']").click();
		
		//9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("No. 41/25, New Street");
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("TamilNadu");
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600019");
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		//10) Mouse over on Today's Activities and click Meetings
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("(//div[@class='menu-label'])[1]")).pause(2000).perform();
		driver.findElementByXPath("//div[text()='Meetings']").click();
		Thread.sleep(2000);
		
		//11) Click Create
		driver.findElementByXPath("(//span[text()='Create'])[1]").click();
		
		//12) Type Subject as "Project Status" , Status as "Planned"
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		
		//13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
		driver.findElementById("DetailFormdate_start-input").click();
		Date date = new Date();
		System.out.println(date);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		String today = dateFormat.format(date);
		//System.out.println(today);
		int tomorrow = Integer.parseInt(today)+1;
		WebElement input = driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input");
		input.clear();
		input.sendKeys("2020-04-"+tomorrow, Keys.ENTER);
		input.clear();
		input.sendKeys("15:00",Keys.ENTER);
		
		driver.findElementById("DetailFormduration-time").clear();
		driver.findElementById("DetailFormduration-time").sendKeys("1",Keys.TAB);
		
		//14) Click Add participants, add your created Contact name and click Save
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		driver.findElementByXPath("(//input[@class='input-text'])[4]").sendKeys("Vignesh Kumar");
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='app-search']//div[@class='option-cell input-label ']").click();
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(2000);
		
		//15) Go to Sales and Marketing-->Contacts
		action.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).pause(2000).perform();
		driver.findElementByXPath("(//div[text()='Contacts'])[2]").click();
		
		//16) search the lead Name and click the name from the result
		driver.findElementById("filter_text").sendKeys("Vignesh Kumar", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElementByXPath("(//table[@class='listView']//tr)[2]/td[3]").click();
		Thread.sleep(2000);
		
		
		//17) Check weather the Meeting is assigned to the contact under Activities Section
		if (driver.findElementByXPath("//a[@class='listViewNameLink']").isDisplayed()) {
			System.out.println("Meeting Created");
		}else {
			System.out.println("Meeting Not Created");
		}
		
	}

}
