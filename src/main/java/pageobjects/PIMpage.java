package pageobjects;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import resources.Base;

public class PIMpage extends Base {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties prop;
	private Actions act;
	private Logger LOGGER = LogManager.getLogger(PIMpage.class); 
	private Signinpage signinpage;

	public PIMpage(WebDriver driver) throws Exception {
		this.driver = driver;
		prop = new Properties();
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
		String propPath = System.getProperty("user.dir") + "/src/main/java/resources/dataproperties";
		FileInputStream fis = new FileInputStream(propPath);
		prop.load(fis);
		initializeWait();
		this.signinpage = new Signinpage(driver);
		LOGGER = LogManager.getLogger(PIMpage.class.getName());
	}

	private boolean condition = true;

	private void initializeWait() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private static String generateUniqueName(String prefix) {
		Random rand = new Random();
		StringBuilder uniqueName = new StringBuilder(prefix);

		for (int i = 0; i < 10; i++) {
			char randomChar = (char) ('A' + rand.nextInt(26));
			uniqueName.append(randomChar);
		}
		return uniqueName.toString();
	}

	public static void main(String[] args) {
		String generatedName = generateUniqueName("validname");
		System.out.println(generatedName);
	}

	@FindBy(xpath = "//span[normalize-space()='PIM']")
	private WebElement pimclick;
	
	@FindBy(xpath = "//img[@alt='client brand banner']")
	private WebElement Dashboard_display;

	public void PIMClick() throws Exception{
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOf(Dashboard_display)).isDisplayed();
		wait.until(ExpectedConditions.visibilityOf(pimclick)).click();
	}
	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement click_addbutton;
	
	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstname;
	
	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	private WebElement middlename;
	
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastname;

	public void Click_Add_Button() throws Exception{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(click_addbutton)).click();

	}
	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement employeeid;
	
	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement click_save;
	public void Add_employee() throws Exception{
		Thread.sleep(4000);
		String first_name = generateUniqueName(prop.getProperty("first_name"));
		wait.until(ExpectedConditions.visibilityOf(firstname)).sendKeys(first_name);
		
		wait.until(ExpectedConditions.visibilityOf(firstname)).isDisplayed();
		String middle_name = generateUniqueName(prop.getProperty("middle_name"));
		wait.until(ExpectedConditions.visibilityOf(middlename)).sendKeys(middle_name);
	
		wait.until(ExpectedConditions.visibilityOf(middlename)).isDisplayed();
		String last_name = generateUniqueName(prop.getProperty("last_name"));
		wait.until(ExpectedConditions.visibilityOf(lastname)).sendKeys(last_name);
		Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(lastname)).isDisplayed();
    	wait.until(ExpectedConditions.visibilityOf(click_save)).click();

	}
	@FindBy(xpath = "//div[contains(text(),'Demoe')]")
	private WebElement display_employees;

	public void Verify_employees() throws Exception{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(display_employees));
		Assert.assertTrue(display_employees.isDisplayed());
		System.out.println("Name Verified");

	}

}
