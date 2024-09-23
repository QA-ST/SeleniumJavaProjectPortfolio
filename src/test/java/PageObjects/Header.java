package PageObjects;

import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;

import java.time.Duration;

public class Header extends BaseClass {
    By userProfileDropdown = By.xpath("//button[@id='dropdownMenu1']");
    By logoutButton = By.id("aLogout");

    public void userLogoutFromApp() {
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).click(driver.findElement(userProfileDropdown)).pause(Duration.ofSeconds(1)).perform();
        CommonMethods.waitForElementClickable(logoutButton).click();
    }
}