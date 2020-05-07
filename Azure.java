package testCases;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Azure {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "D:\\Downloads");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
			
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://azure.microsoft.com/en-in/
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//2) Click on Pricing
		driver.findElementByXPath("//a[text()='Pricing']").click();
		
		//3) Click on Pricing Calculator
		driver.findElementByXPath("(//a[@data-event='global-navigation-secondarynav-clicked-topmenu'])[2]").click();
		
		//4) Click on Containers
		driver.findElementByXPath("//button[text()='Containers']").click();
		
		//5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		Thread.sleep(2000);
		
		//6) Click on Container Instance Added View
		driver.findElementByXPath("//a[text()='View']").click();
		
		//7) Select Region as "South India"
		Select select = new Select(driver.findElementByName("region"));
		select.selectByValue("south-india");
		
		//8) Set the Duration as 180000 seconds
		driver.findElementByXPath("//input[@name='seconds']").clear();
		driver.findElementByXPath("//input[@name='seconds']").sendKeys("80000");
		
		//9) Select the Memory as 4GB
		Select memory = new Select(driver.findElementByName("memory"));
		memory.selectByValue("4");
		
		//10) Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
		
		//11) Select Indian Rupee  as currency
		Select currency = new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currency.selectByValue("INR");
		
		//12) Print the Estimated monthly price
		String estPrice = driver.findElementByXPath("(//span[@class='numeric']//span)[6]").getText();
		System.out.println(estPrice);
		
		//13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[text()='Export']").click();
		Thread.sleep(4000);
		
		//14) Verify the downloaded file in the local folder
		File file = new File("D:\\Downloads");
		File[] listFiles = file.listFiles();
		if (listFiles[0].getName().equals("ExportedEstimate.xlsx")) {
			System.out.println("1st File Matched");
		}else {
			System.out.println("1st File Not Matched");
		}
		
		//15) Navigate to Example Scenarios and Select CI/CD for Containers
		WebElement example = driver.findElementByXPath("//a[text()='Example Scenarios']");
		js.executeScript("arguments[0].click()", example);
		WebElement containers = driver.findElementByXPath("//span[text()='CI/CD for Containers']");
		js.executeScript("arguments[0].click()", containers);
		
		//16) Click Add to Estimate
		WebElement estimate = driver.findElementByXPath("//button[text()='Add to estimate']");
		js.executeScript("arguments[0].click()", estimate);
		Thread.sleep(4000);
		
		//17) Change the Currency as Indian Rupee
		Select currency1= new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currency1.selectByValue("INR");
		
		//18) Enable SHOW DEV/TEST PRICING
		WebElement toggle = driver.findElementById("devtest-toggler");
		js.executeScript("arguments[0].click()", toggle);
		
		//19) Export the Estimate
		WebElement export = driver.findElementByXPath("//button[text()='Export']");
		js.executeScript("arguments[0].click()", export);
		Thread.sleep(4000);
		
		//20) Verify the downloaded file in the local folder
		File[] listFiles1 = file.listFiles();
		if (listFiles1[0].getName().equals("ExportedEstimate (1).xlsx")) {
			System.out.println("2nd File Matched");
		}else {
			System.out.println("2nd File Not Matched");
		}
		
		driver.close();
	}

}
