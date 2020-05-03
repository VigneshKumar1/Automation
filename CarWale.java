package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class CarWale {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.carwale.com/
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//2) Click on Used
		driver.findElementByXPath("//li[contains(text(),'Used')]").click();
		
		//3) Select the City as Chennai
		driver.findElementById("usedCarsList").sendKeys("Chennai");
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']")));
		
		//4) Select budget min (8L) and max(12L) and Click Search
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		driver.findElementByXPath("(//button[contains(text(),'Search')])[2]").click();
		//driver.findElementById("placesQuery").sendKeys("Chennai,T",Keys.ENTER);
		
		//5) Select Cars with Photos under Only Show Cars With
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		Thread.sleep(2000);
		
		//6) Select Manufacturer as "Hyundai" --> Creta
		WebElement brand = driver.findElementByXPath("//span[text()=' Hyundai ']");
		js.executeScript("arguments[0].click()", brand);
		Thread.sleep(2000);
		WebElement model = driver.findElementByXPath("//span[text()='Creta']");
		js.executeScript("arguments[0].click()", model);
		Thread.sleep(2000);
		
		//7) Select Fuel Type as Petrol
		WebElement fuelType = driver.findElementByXPath("//h3[contains(text(),'Fuel Type')]");
		js.executeScript("arguments[0].click()", fuelType);
		WebElement fuel = driver.findElementByXPath("(//span[text()='Petrol'])[1]");
		js.executeScript("arguments[0].click()", fuel);
		Thread.sleep(4000);
		
		//8) Select Best Match as "KM: Low to High"
		Select dropdown = new Select(driver.findElementById("sort"));
		dropdown.selectByVisibleText("KM: Low to High");
		
		//9) Validate the Cars are listed with KMs Low to High
		List<WebElement> kmsList = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
		List<Integer> intKms = new ArrayList<Integer>();
		
		for (int i = 0; i < kmsList.size(); i++) {
			String kms = kmsList.get(i).getText();
			String kmss = kms.replaceAll("[^0-9]", "");
			int kmsNum = Integer.parseInt(kmss);
			intKms.add(kmsNum);
		}
		
		List<Integer> sortKms = new ArrayList<Integer>(intKms);
		Collections.sort(sortKms);
		
		if (intKms.equals(sortKms)) {
			System.out.println("Kilometers are sorted correctly");
		}else {
			System.err.println("Kilometers are not sorted correctly");
		}
		
		//10) Add the least KM ran car to Wishlist
		for (int i = 0; i < kmsList.size(); i++) {
			if (intKms.get(i).equals(sortKms.get(0))) {
				driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]").click();
				Thread.sleep(2000);
				break;
			}
		}
		
		//11) Go to Wishlist and Click on More Details
		driver.findElementByXPath("//li[@class='action-box shortlistBtn']").click();
		driver.findElementByXPath("//a[contains(text(),'More details')]").click();
		
		//12) Print all the details under Overview 
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		
		Map<String, String> details = new LinkedHashMap<String, String>();
		List<WebElement> overview = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		List<WebElement> values = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		
		for (int i = 0; i < overview.size(); i++) {
			String mapKey = overview.get(i).getText().trim();
			String mapValue = values.get(i).getText().trim();
			details.put(mapKey, mapValue);
		}
		
		for (Entry<String, String> eachEntry : details.entrySet()) {
			System.out.println(eachEntry.getKey() +"----"+ eachEntry.getValue());
		}
		
		//13) Close the browser.
		driver.close();
	}

}
