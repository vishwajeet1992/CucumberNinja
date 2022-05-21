package Pages.Impls;

import Pages.OrderDetails;
import WebDriver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

public class OrderDetailsImpl implements OrderDetails {

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

    @Override
    public String printAirwayBill() {
        driver.findElement(PRINT_AIRWAY_BUTTON).click();
        waitForElement(ONE_BILL_PAGE);
        driver.findElement(ONE_BILL_PAGE).click();
        waitForElement(PRINT_BUTTON);
        String pdfUrl = driver.findElement(PRINT_BUTTON).getAttribute("href");
        driver.navigate().to(pdfUrl);
        //driver.findElement(PRINT_BUTTON).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        //getShadowElement(SHADOW_DOWNLOAD).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {


            String script = "var request = new XMLHttpRequest();var result ;request.open('GET','"+pdfUrl+"', true);request.responseType = 'blob';request.onload =function() {  var reader = new FileReader();    reader.readAsDataURL(request.response);    reader.onload =  function(e){        result= e.target.result; return result;    };};request.send();";
            String result = (String) ((JavascriptExecutor) driver).executeAsyncScript(script, pdfUrl);

            byte[] decodedBytes = Base64.getDecoder().decode(result);
            String decodedString = new String(decodedBytes);
            return result;

            //return "worked";


        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdfUrl;
    }

    public WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public WebElement getShadowElement(String element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return document.querySelector('pdf-viewer').shadowRoot.querySelector('viewer-toolbar')." +
                "shadowRoot.querySelector('viewer-download-controls').shadowRoot.querySelector('" + element + " ');");
    }


}
