package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.NetworkInterceptor;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public DevTools devTools;
    protected NetworkInterceptor networkInterceptor;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        String propPath = System.getProperty("user.dir") + "/src/main/java/resources/dataproperties";
        FileInputStream fis = new FileInputStream(propPath);
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
        	WebDriverManager.chromedriver().driverVersion("134.0.6998.35").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

//            devTools = ((ChromeDriver) driver).getDevTools();
//            devTools.createSession();
//            networkInterceptor = new NetworkInterceptor(devTools);
        } 
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver(options);
        } 
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);
        } 
        else {
            throw new IllegalArgumentException("Browser name not recognized: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        return driver;
    }
}
