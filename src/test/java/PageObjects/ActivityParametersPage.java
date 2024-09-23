package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ActivityParametersPage extends BaseClass {
    By addNewActivityPriorityButton = By.xpath("//a[contains(@id,'btnAddNewPriority')]");
    By activityPriorityNameInputBox = By.xpath("//input[contains(@id,'txPrioritytName')]");
    By activityPriorityDescriptionInputBox = By.xpath("//input[contains(@id,'txtPriorityDesc')]");
    By activityPriorityTable = By.xpath("//div[contains(@id,'grdPriority')]/table/tbody/tr[1]/td[1]");
    By activityPriorityNameList = By.xpath("//div[contains(@id,'grdPriority')]/table/tbody/tr/td[2]/a");
    By activityStatusTabButton = By.xpath("//a[contains(@id,'lnkStatus')]");
    By addNewActivityStatusButton = By.xpath("//a[contains(@id,'btnAddNewStatus')]");
    By activityStatusNameInputBox = By.xpath("//input[contains(@id,'txtStatusName')]");
    By activityStatusDescriptionInputBox = By.xpath("//input[contains(@id,'txtStatusDesc')]");
    By activityStatusTypeDropdown = By.xpath("//select[contains(@id,'drpStatusKind')]");
    By selectedActivityStatusStatusType = By.xpath("//select[contains(@id,'drpStatusKind')]/option[@selected='selected']");
    By activityStatusTable = By.xpath("//div[contains(@id,'grdStatus')]/table/tbody/tr[1]/td[1]");
    By activityStatusNameList = By.xpath("//div[contains(@id,'grdStatus')]/table/tbody/tr/td[2]/a");
    By activityTypeTabButton = By.xpath("//a[contains(@id,'lnkType')]");
    By addNewActivityTypeButton = By.xpath("//a[contains(@id,'btnAddNewType')]");
    By activityTypeNameInputBox = By.xpath("//input[contains(@id,'txtTypeName')]");
    By activityTypeDescriptionField = By.xpath("//input[contains(@id,'txtTypeDesc')]");
    By activityTypeTable = By.xpath("//div[contains(@id,'grdType')]/table/tbody/tr[1]/td[1]");
    By activityTypeNameList = By.xpath("//div[contains(@id,'grdType')]/table/tbody/tr/td[2]/a");

    String activityPriorityDescriptionVerify = "//div[contains(@id,'grdPriority')]/table/tbody/tr[%s]/td[3]";
    String activityStatusDescriptionVerify = "//div[contains(@id,'grdStatus')]/table/tbody/tr[%s]/td[3]";
    String activityTypeDescriptionVerify = "//div[contains(@id,'grdType')]/table/tbody/tr[%s]/td[3]";

    public void clickAddNewActivityPriorityButton() {
        CommonMethods.waitForElementClickable(addNewActivityPriorityButton).click();
        CommonMethods.waitForElementClickable(activityPriorityNameInputBox);
        ExtentLogger.pass("Clicked the add new activity priority button");
    }

    public void enterActivityPriorityName(String activityPriorityName) {
        WebElement priorityNameField = CommonMethods.waitForElementClickable(activityPriorityNameInputBox);
        CommonMethods.getActions().click(priorityNameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityPriorityName).perform();
        ExtentLogger.pass("Entered the activity priority name as - " + activityPriorityName);
    }

    public void enterActivityPriorityDescription(String activityPriorityDescription) {
        WebElement priorityDescriptionField = CommonMethods.waitForElementClickable(activityPriorityDescriptionInputBox);
        CommonMethods.getActions().click(priorityDescriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityPriorityDescription).perform();
        ExtentLogger.pass("Entered the activity priority description as - " + activityPriorityDescription);
    }

    public void saveActivityDescriptionDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewActivityPriorityButton);
        CommonMethods.waitForElementClickable(activityPriorityTable);
        ExtentLogger.pass("Saved the activity parameter details");
    }

    public void verifyActivityPriorityFromList(String activityPriorityName, String activityPriorityDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(activityPriorityTable);
        List<WebElement> activityPriorities = driver.findElements(activityPriorityNameList);
        for (WebElement ele : activityPriorities) {
            count++;
            if (ele.getText().equals(activityPriorityName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(activityPriorityDescriptionVerify, count))).getText(), activityPriorityDescription);
                ExtentLogger.pass(activityPriorityName + " activity priority is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(activityPriorityName + " activity priority not found");
            Assert.fail(activityPriorityName + " activity priority not found");
        }
    }

    public void switchToActivityStatusTab() {
        CommonMethods.waitForElementClickable(activityStatusTabButton).click();
        CommonMethods.waitForElementClickable(addNewActivityStatusButton);
        ExtentLogger.pass("Switched to activity status tab");
    }

    public void clickAddNewActivityStatusButton() {
        CommonMethods.waitForElementClickable(addNewActivityStatusButton).click();
        CommonMethods.waitForElementClickable(activityStatusNameInputBox);
        ExtentLogger.pass("Clicked the add new activity status button");
    }

    public void enterActivityStatusName(String activityStatusName) {
        WebElement nameField = CommonMethods.waitForElementClickable(activityStatusNameInputBox);
        CommonMethods.getActions().click(nameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityStatusName).perform();
        ExtentLogger.pass("Entered activity status name as - " + activityStatusName);
    }

    public void enterActivityStatusDescription(String activityStatusDescription) {
        WebElement descriptionField = CommonMethods.waitForElementClickable(activityStatusDescriptionInputBox);
        CommonMethods.getActions().click(descriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityStatusDescription).perform();
        ExtentLogger.pass("Entered activity status description as - " + activityStatusDescription);
    }

    public void selectStatusTypeForActivityStatus(String statusToSelect) {
        WebElement statusTypeDropdown = CommonMethods.waitForElementClickable(activityStatusTypeDropdown);
        CommonMethods.getSelect(statusTypeDropdown).selectByVisibleText(statusToSelect);
        ExtentLogger.pass("Selected status type for activity status as - " + statusToSelect);
    }

    public void saveActivityStatusDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewActivityStatusButton);
        CommonMethods.waitForElementClickable(activityStatusTable);
        ExtentLogger.pass("Saved the activity status details");
    }

    public void verifyActivityStatusDetails(String activityStatusName, String activityStatusDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(activityStatusTable);
        List<WebElement> activityStatuses = driver.findElements(activityStatusNameList);
        for (WebElement ele : activityStatuses) {
            count++;
            if (ele.getText().equals(activityStatusName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(activityStatusDescriptionVerify, count))).getText(), activityStatusDescription);
                ExtentLogger.pass(activityStatusName + " activity status is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(activityStatusName + " activity status not found");
            Assert.fail(activityStatusName + " activity status not found");
        }
    }

    public void selectMaximumPaginationForActivityTypeTable() {
        ProjectParametersPage.selectMaximumPaginationForParameterTable(addNewActivityTypeButton);
    }

    public void switchToActivityTypeTab() {
        CommonMethods.waitForElementClickable(activityTypeTabButton).click();
        CommonMethods.waitForElementClickable(addNewActivityTypeButton);
        ExtentLogger.pass("Switched to activity type tab");
    }

    public void clickAddNewActivityTypeButton() {
        CommonMethods.waitForElementClickable(addNewActivityTypeButton).click();
        CommonMethods.waitForElementClickable(activityTypeNameInputBox);
        ExtentLogger.pass("Clicked the add new activity type button");
    }

    public void enterActivityTypeName(String activityTypeName) {
        WebElement nameField = CommonMethods.waitForElementClickable(activityTypeNameInputBox);
        CommonMethods.getActions().click(nameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityTypeName).perform();
        ExtentLogger.pass("Entered activity type name as - " + activityTypeName);
    }

    public void enterActivityTypeDescription(String activityDescription) {
        WebElement descriptionField = CommonMethods.waitForElementClickable(activityTypeDescriptionField);
        CommonMethods.getActions().click(descriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(activityDescription).perform();
        ExtentLogger.pass("Entered activity type description as - " + activityDescription);
    }

    public void saveActivityTypeDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewActivityTypeButton);
        CommonMethods.waitForElementClickable(activityTypeTable);
        ExtentLogger.pass("Saved the activity type description details");
    }

    public void verifyActivityTypeFromList(String activityTypeName, String activityTypeDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(activityTypeTable);
        List<WebElement> activityPriorities = driver.findElements(activityTypeNameList);
        for (WebElement ele : activityPriorities) {
            count++;
            if (ele.getText().equals(activityTypeName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(activityTypeDescriptionVerify, count))).getText(), activityTypeDescription);
                ExtentLogger.pass(activityTypeName + " activity type is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(activityTypeName + " activity type not found");
            Assert.fail(activityTypeName + " activity type not found");
        }
    }
}