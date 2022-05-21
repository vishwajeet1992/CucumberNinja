package Pages;

import org.openqa.selenium.By;

public interface LoginPage {
    //Login page locators
    By EMAIL = By.cssSelector("input[data-key='email_placeholder']");
    By PASSWORD = By.id("password");
    By LOGIN = By.cssSelector("button.ant-btn-primary");
    By FORGET_PASSWORD = By.cssSelector("span[data-key='forgot_your_password']");
    By CREATE_NEW_ORDER = By.cssSelector("button[data-onboarding='create_orders']");

    boolean login();

    // More methods like forget password and other login
}
