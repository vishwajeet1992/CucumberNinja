package Pages;

import org.openqa.selenium.By;

public interface OrderDetails {

    By PRINT_AIRWAY_BUTTON = By.xpath("//span[normalize-space(text())='Print Airway Bill']/ancestor::button");
    By ONE_BILL_PAGE = By.xpath("//div[contains(@class,'ant-modal-content')]//span[normalize-space(text())='1 bills per page']/parent::span");
    By PRINT_BUTTON = By.xpath("//span[normalize-space(text())='print']/ancestor::a");
    String SHADOW_DOWNLOAD= "cr-icon-button[id=\"download\"]'";

    String printAirwayBill();


}
