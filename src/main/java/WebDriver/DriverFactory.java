package WebDriver;

import io.netty.util.internal.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DriverFactory {

    private static final String chromeDriverLocation = "chrome.driver.location";
    private static final String geckoDriverLocation = "firefox.driver.location";
    private Properties properties = new Properties();

    private WebDriver driver;
    private static DriverFactory instanceOfDriverFactory = null;


    public static DriverFactory getInstanceOfDriverFactory() {
        if (instanceOfDriverFactory == null) {
            instanceOfDriverFactory = new DriverFactory();
        }
        return instanceOfDriverFactory;
    }


    public WebDriver get(String browserName) {

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!StringUtil.isNullOrEmpty(browserName)) {
            switch (browserName) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", properties.getProperty(chromeDriverLocation));
                    if (driver == null) {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        Map<String, Object> prefs = new HashMap<String, Object>();
                        prefs.put("download.default_directory", "/Users/vchauhan/Downloads/");
                        prefs.put("download.prompt_for_download", "False");
                        prefs.put("download.directory_upgrade", "True");
                        prefs.put("safebrowsing.enabled", "True");
                        chromeOptions.setExperimentalOption("prefs", prefs);
                        driver = new ChromeDriver(chromeOptions);
                        driver.manage().window().maximize();
                        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                    }
                    break;
                case "fireFox":
                    System.setProperty("webdriver.gecko.driver", properties.getProperty(geckoDriverLocation));
                    if (driver == null) {
                        driver = new FirefoxDriver();
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Browser type is not supported");
            }

        }
        return driver;
    }

    public void clear() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private DriverFactory() {
    }

}
