package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WorkGroupsPage extends BaseClass {
    By addNewGroupButton = By.xpath("//a[contains(@id,'btnAddNewGroup')]");
    By workGroupNameInputBox = By.xpath("//input[contains(@id,'txtGroupName')]");
    By workGroupCodeInputBox = By.xpath("//input[contains(@id,'txtGroupCode')]");
    By workGroupTable = By.xpath("//div[contains(@id,'grdGroupList')]/table/tbody/tr[1]/td[1]");
    By workGroupNameSearched = By.xpath("//div[contains(@id,'grdGroupList')]/table/tbody/tr[1]/td[2]/a");
    By workGroupCodeSearchedVerify = By.xpath("//div[contains(@id,'grdGroupList')]/table/tbody/tr[1]/td[3]/span");
    By workGroupDateCreatedVerify = By.xpath("//div[contains(@id,'grdGroupList')]/table/tbody/tr[1]/td[4]");
    By workGroupNameFilterInputField = By.xpath("//input[contains(@id,'txtGroupSearch')]");


    public void clickAddNewWorkGroupButton() {
        CommonMethods.waitForElementClickable(addNewGroupButton).click();
        CommonMethods.waitForElementClickable(workGroupNameInputBox);
        ExtentLogger.pass("Clicked the add new work group button");
    }

    public void enterWorkGroupName(String workGroupName) {
        WebElement workGroupField = CommonMethods.waitForElementClickable(workGroupNameInputBox);
        CommonMethods.getActions().click(workGroupField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(workGroupName).perform();
        ExtentLogger.pass("Entered the work group name as - " + workGroupName);
    }

    public void enterWorkGroupCode(String workGroupCode) {
        WebElement workGroupCodeField = CommonMethods.waitForElementClickable(workGroupCodeInputBox);
        CommonMethods.getActions().click(workGroupCodeField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(workGroupCode).perform();
        ExtentLogger.pass("Entered the work group code as - " + workGroupCode);
    }

    public void saveWorkGroupDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(workGroupTable);
        CommonMethods.waitForElementClickable(addNewGroupButton);
        ExtentLogger.pass("Saved the work group details");
    }

    public void filterWorkGroupTableByName(String workGroupName) {
        WebElement workGroupNameFilter = CommonMethods.waitForElementClickable(workGroupNameFilterInputField);
        CommonMethods.getActions().click(workGroupNameFilter).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(workGroupName).perform();
        CommonMethods.applyFilter();
        CommonMethods.waitForElementClickable(workGroupTable);
        ExtentLogger.pass("Filtered the organization unit list by name filter");
    }

    public void verifyWorkGroupFromList(String workGroupNameToVerify, String workGroupCode, String dateCreated) {
        CommonMethods.waitForElementClickable(workGroupTable);
        CommonMethods.wait(1000);
        if (CommonMethods.waitForElementClickable(workGroupNameSearched).getText().equals(workGroupNameToVerify)) {
            Assert.assertEquals(driver.findElement(workGroupCodeSearchedVerify).getText(), workGroupCode);
            Assert.assertTrue(driver.findElement(workGroupDateCreatedVerify).getText().contains(dateCreated));
            ExtentLogger.pass(workGroupNameToVerify + " work group is present in the list");
        } else {
            ExtentLogger.fail(workGroupNameToVerify + " work group not found");
            Assert.fail(workGroupNameToVerify + " work group not found");
        }
    }
}