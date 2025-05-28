package Hooks;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageobjects.Signinpage;
import resources.Base;
import utils.NetworkInterceptor;

public class Testhooks extends Base {
    public WebDriver driver;
    public WebDriverWait wait;
    public Signinpage signinpage;
    
    private static Logger LOGGER = LogManager.getLogger(Testhooks.class);

    public void initializeWait() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Before 
    public void setup(Scenario scenario) throws Exception {
        LOGGER.info("Execution Started");
        driver = initializeDriver();
        LOGGER.info("Browser launched successfully");

//        if (driver instanceof ChromeDriver) {
//            devTools = ((ChromeDriver) driver).getDevTools();
//            devTools.createSession();
//            
//            networkInterceptor = new NetworkInterceptor(devTools);
//            networkInterceptor.startIntercepting();
//        } else {
//            throw new IllegalStateException("WebDriver is not an instance of ChromeDriver");
//        }

        signinpage = new Signinpage(driver);

        driver.get(prop.getProperty("TEST_URL"));
        initializeWait();
        LOGGER.info("Navigated to URL");
		System.out.println("Login page opened");

//		Robot robot = new Robot();
//		System.out.println("About to zoom out");
//		for (int i = 0; i < 3; i++) {
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//		}
		LOGGER.info("Page is zoomed out");
		System.out.println(
				"** Execution started for scenario -" + scenario.getName());
		System.out.println("++ Browser got launched and maximized ++");
		System.out.println("++ Application URL got opened in the browser ++");
		Thread.sleep(1000);
		
		
//		String bearerToken = networkInterceptor.getBearerToken();
//        if (bearerToken != null) {
//            LOGGER.info("Captured Bearer Token: " + bearerToken);
//        } else {
//            LOGGER.warn("Bearer Token not found!");
//        }
    }
//	@After
//	public void tearDown(Scenario scenario) throws Exception {
//
//		if (driver != null) {
//			driver.quit();
//			LOGGER.info("Browser got closed");
//			System.out.println("-- Browser got closed --");
//			System.out.println(
//					"** Execution ended for scenario -" + scenario.getName());
//		}
//	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.BYTES);
		if (scenario.isFailed()) {
			scenario.attach(screenshot, "image/png", scenario.getName());
			LOGGER.info("Failed scenario screenshot got captured" + scenario.getName());
		}else {
            scenario.attach(screenshot, "image/png", "Passed scenario: " + scenario.getName());
            LOGGER.info("Passed scenario screenshot captured: " + scenario.getName());
        }
	}

	 public WebDriver getDriver() {
	        return driver;
	    }

//	    public DevTools getDevToolsInstance() {
//	        return devTools;
//	    }

}
