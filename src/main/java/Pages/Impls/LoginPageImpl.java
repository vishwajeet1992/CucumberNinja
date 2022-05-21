package Pages.Impls;

import Pages.LoginPage;
import WebDriver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginPageImpl implements LoginPage {
    private static final String baseUrl = "https://dashboard-qa.ninjavan.co/login-v2";
    private Properties properties = new Properties();


    public void loadProperties() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        DriverFactory driverFactory = DriverFactory.getInstanceOfDriverFactory();
        loadProperties();
        return driverFactory.get(properties.getProperty("chromeBrowser"));
    }

    WebDriver driver = getDriver();


    public boolean login() {
        driver.get(baseUrl);
        waitForElement(LOGIN);
        driver.findElement(EMAIL).clear();
        driver.findElement(EMAIL).sendKeys(properties.getProperty("userNameDashboard"));
        driver.findElement(PASSWORD).clear();
        driver.findElement(PASSWORD).sendKeys(properties.getProperty("passwordDashboard"));
        driver.findElement(LOGIN).click();
        waitForElement(CREATE_NEW_ORDER);
        return driver.findElement(CREATE_NEW_ORDER).isDisplayed();
    }


    public WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }
}
