package Pages.Impls;

import Pages.DashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import WebDriver.DriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

public class DashboardPageImpl implements DashboardPage {

    private Properties properties = new Properties();
    public static String orderId = "";
    public static String orderIdFromNewWindow = "";

//    ExpectedData expectedData = new ExpectedData();

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

    @Override
    public String navigatesToOrderDetails() {
        //Maybe right Utility to retry
        if (waitForElement(TRACKING_MENU).isDisplayed()) {
            driver.findElement(TRACKING_MENU).click();
            waitForElement(SEARCH_ORDER).click();
            waitForElement(ORDER_FIRST_ROW_CONTAINER);
            orderId = driver.findElement(ORDER_FIRST_ROW_CONTAINER).findElement(REQUEST_TRACKING_COLUMN).getText();
            driver.findElement(ORDER_FIRST_ROW_CONTAINER).findElement(REQUEST_TRACKING_COLUMN).click();
            String currentWindow = driver.getWindowHandle();
            //driver.findElement(ORDER_FIRST_ROW_CONTAINER).findElement(REQUEST_TRACKING_COLUMN);
            Set<String> allWindows = driver.getWindowHandles();
            for (String win : allWindows) {
                if (!currentWindow.equalsIgnoreCase(win)) {
                    driver.switchTo().window(win);
                }
            }
            waitForElement(TRACKING_ID);
            orderIdFromNewWindow = driver.findElement(TRACKING_ID).getText();


            return orderIdFromNewWindow;
        } else {
            throw new NoSuchElementException("Tracking menu not found");
        }

    }

    public WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

}
