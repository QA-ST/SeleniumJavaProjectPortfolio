package PageObjects;

import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;

public class LoginPage extends BaseClass {
    By userEmailInputField = By.id("txtUserName");
    By passwordInputField = By.id("txtPassword");
    By loginButton = By.id("btnLogin");
    By homePageVerify = By.xpath("//h5[@tkey='Personal Board']");

    public void enterUserEmail(String email) {
        CommonMethods.waitForElementVisibility(userEmailInputField).sendKeys(email);
    }

    public void enterUserPassword(String password) {
        CommonMethods.waitForElementVisibility(passwordInputField).sendKeys(password);
    }

    public void clickLoginButton() {
        CommonMethods.waitForElementClickable(loginButton).click();
        CommonMethods.waitForElementVisibility(homePageVerify);
    }
}