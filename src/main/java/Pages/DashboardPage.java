package Pages;

import org.openqa.selenium.By;

public interface DashboardPage {


    By TRACKING_MENU = By.cssSelector("li[data-analyticsid='tracking']");
    By SEARCH_ORDER = By.cssSelector("button[data-analyticsid='order search']");
    By ORDER_FIRST_ROW_CONTAINER = By.cssSelector("div.ReactVirtualized__Table__row:first-child");
    By ORDER_GRID = By.cssSelector(".ReactVirtualized__Grid__innerScrollContainer");
    By REQUEST_TRACKING_COLUMN = By.cssSelector("div[aria-colindex='2']");
    By TRACKING_ID = By.cssSelector("div[testid='tracking-id']");

    String navigatesToOrderDetails();
}
