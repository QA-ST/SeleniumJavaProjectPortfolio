package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class UsersPage extends BaseClass {
    By userLoginNameFilterInputField = By.id("MainContent_txtCustomerSearch");
    By userPageFilterApplyButton = By.xpath("//a[@id='MainContent_btnApplyFilter']");
    By usersTable = By.xpath("(//div[contains(@id,'grdUserList')]//table[contains(@class,'rgMasterTable')])[1]/thead/tr/th[1]");
    By userSearchedEmail = By.xpath("(//div[contains(@id,'grdUserList')]//table[contains(@class,'rgMasterTable')])[2]//tbody/tr[1]/td/a[contains(@id,'lnkUserName')]");
    By userSearchedDisplayName = By.xpath("(//div[contains(@id,'grdUserList')]//table[contains(@class,'rgMasterTable')])[2]//tbody/tr[1]/td/span[contains(@id,'lblServiceAlias')]");
    By userLoginEmailInputField = By.xpath("//input[contains(@name,'LoginName')]");
    By userPersonalDetailsSaveButton = By.xpath("//a[contains(@id,'btnSubmit')]");
    By positionTabOption = By.id("MainContent_liPosition");
    By userCategorySelectDropdown = By.xpath("//select[contains(@id,'Category')]");
    By categorySelected = By.xpath("//select[contains(@id,'Category')]/option[@selected]");
    By categoryInputCostVerify = By.id("MainContent_lblInternalCostValue");
    By categoryOutputCostVerify = By.id("MainContent_lblChargedCostValue");
    By userPasswordInputField = By.xpath("//input[@id='MainContent_txtLoginpass']");
    By userFirstnameInputField = By.xpath("//input[@id='MainContent_txtFirstName']");
    public By userLastnameInputField = By.xpath("//input[@id='MainContent_txtLastName']");
    By userPositionInputField = By.id("MainContent_txtPosition");
    By positionStartDateInputField = By.xpath("//span[contains(@id,'ucStartDate_dateInput')]/input[1]");
    By userPositionSaveButton = By.id("MainContent_btnPositionSubmit");
    By userPersonnelInputField = By.xpath("//input[@id='MainContent_txtPersonalNumber']");
    By userAssignedRolesSection = By.xpath("//div[contains(@id,'rlbInRole')]");
    By userAssignedRolesList = By.xpath("//div[contains(@id,'rlbInRole')]//ul/li/span");
    By userRolesListSection = By.xpath("//div[contains(@id,'rlbRole')]");
    By userRolesList = By.xpath("//div[contains(@id,'rlbRole')]//ul/li/span");
    By roleSelectRightArrowIcon = By.xpath("//a[@class='rlbButton rlbNoButtonText rlbTransferFrom']");
    By userPositionTable = By.xpath("//table[@class='rgMasterTable']/thead/tr/th[1]");
    By positionStartDateList = By.xpath("//table[@class='rgMasterTable']/tbody/tr/td[3]");
    By userDeleteIcon = By.xpath("(//div[contains(@id,'grdUserList')]//table[contains(@class,'rgMasterTable')])[2]//tbody/tr[1]/td/a[contains(@id,'imgActiveInactive')]");
    By allowNonBillingHoursCheckbox = By.xpath("//input[contains(@id,'AllowNonBilling')]");
    By createNewUserButton = By.xpath("//a[contains(@id,'btnAddNewUser')]");

    String positionHistoryCategoryVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td/span[contains(@id,'lblCategoryNAme')]";
    String positionHistoryCategory = "//table[@class='rgMasterTable']/tbody/tr[%s]/td/a[contains(@id,'lnkPosition')]";
    String positionDeleteIcon = "//table[@class='rgMasterTable']/tbody/tr[%s]/td/a[contains(@id,'imgDelete')]";

    public void filterUserByLoginName(String userEmail) {
        CommonMethods.waitForElementClickable(userLoginNameFilterInputField).clear();
        driver.findElement(userLoginNameFilterInputField).sendKeys(userEmail);
        ExtentLogger.pass("Filtered users table data for " + userEmail + " user");
    }

    public void clickUserPageFilterApplyButton() {
        CommonMethods.waitForElementClickable(userPageFilterApplyButton).click();
        CommonMethods.waitForElementClickable(usersTable);
        ExtentLogger.pass("Clicked the user page filter apply button");
    }

    public void navigateToUserDetailsFromUsersTable(String userToView) {
        CommonMethods.waitForElementVisibility(usersTable);
        WebElement userSearched = CommonMethods.waitForElementClickable(userSearchedEmail);
        if (userSearched.getText().equals(userToView)) {
            userSearched.click();
            CommonMethods.waitForElementClickable(positionTabOption);
            ExtentLogger.pass("Navigated to user details page for " + userToView + " user");
        } else {
            ExtentLogger.fail(userToView + " user not found");
            Assert.fail(userToView + " user not found");
        }
    }

    public void switchToPositionTab() {
        CommonMethods.waitForElementClickable(positionTabOption).click();
        CommonMethods.waitForElementClickable(userCategorySelectDropdown);
        ExtentLogger.pass("Navigated to the position tab");
    }

    public void selectCategoryForUser(String categoryToSelect) {
        WebElement categoryDropdown = CommonMethods.waitForElementClickable(userCategorySelectDropdown);
        CommonMethods.getSelect(categoryDropdown).selectByVisibleText(categoryToSelect);
        CommonMethods.wait(1000);
        if (CommonMethods.waitForElementVisibility(categorySelected).getText().equals(categoryToSelect)) {
            ExtentLogger.pass("Selected " + categoryToSelect + " category for the user");
        } else {
            ExtentLogger.fail("Failed to select " + categoryToSelect + " category for the user");
            Assert.fail("Failed to select " + categoryToSelect + " category for the user");
        }
    }

    public void verifyCategoryInputOutputCost(String inputCostToVerify, String outputCostToVerify) {
        WebElement inputCost = CommonMethods.waitForElementVisibility(categoryInputCostVerify);
        Assert.assertEquals(inputCost.getText(), inputCostToVerify);
        Assert.assertEquals(CommonMethods.waitForElementVisibility(categoryOutputCostVerify).getText(), outputCostToVerify);
        ExtentLogger.pass("Category cost displayed in the position tab is as expected. Input cost - " + inputCostToVerify + " and output cost - " + outputCostToVerify);
    }

    public void verifyUserIsPresentInUsersPageTable(String userEmailToVerify, String userDisplayName) {
        CommonMethods.waitForElementVisibility(usersTable);
        WebElement userSearched = CommonMethods.waitForElementClickable(userSearchedEmail);
        if (userSearched.getText().equals(userEmailToVerify)) {
            Assert.assertEquals(driver.findElement(userSearchedDisplayName).getText(), userDisplayName);
            ExtentLogger.pass("User with " + userEmailToVerify + " email and " + userDisplayName + " display name found");
        } else {
            ExtentLogger.fail("User with " + userEmailToVerify + " email not found");
            Assert.fail("User with " + userEmailToVerify + " email not found");
        }
    }

    public void enterUserLoginEmail(String userLoginEmail) {
        WebElement emailInputField = CommonMethods.waitForElementClickable(userLoginEmailInputField);
        emailInputField.clear();
        emailInputField.sendKeys(userLoginEmail);
        ExtentLogger.pass("Entered user email as " + userLoginEmail);
    }

    public boolean verifyUserRoleSelected(String userRole) {
        boolean status = false;
        CommonMethods.waitForElementClickable(userAssignedRolesSection);
        List<WebElement> assignedRolesList = driver.findElements(userAssignedRolesList);
        for (WebElement ele : assignedRolesList) {
            if (ele.getText().equals(userRole)) {
                status = true;
                ExtentLogger.pass(userRole + " user role is assigned to user");
            }
        }
        return status;
    }

    public void selectUserRole(String userRoleToSelect) {
        boolean status = false;
        CommonMethods.waitForElementClickable(userRolesListSection);
        List<WebElement> rolesList = driver.findElements(userRolesList);
        for (WebElement ele : rolesList) {
            if (ele.getText().equals(userRoleToSelect)) {
                ele.click();
                CommonMethods.wait(1000);
                CommonMethods.waitForElementClickable(roleSelectRightArrowIcon).click();
                status = verifyUserRoleSelected(userRoleToSelect);
                if (status) {
                    ExtentLogger.pass(userRoleToSelect + "selected");
                }
            }
        }
        if (!status) {
            ExtentLogger.pass(userRoleToSelect + " user not selected");
            Assert.fail(userRoleToSelect + " user not selected");
        }
    }

    public void saveUserPersonalDetails() {
        CommonMethods.waitForElementClickable(userPersonalDetailsSaveButton).click();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementVisibility(userLoginEmailInputField);
        ExtentLogger.pass("Saved the user personal details");
    }

    public void enterUserFirstName(String firstName) {
        CommonMethods.wait(1000);
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(userFirstnameInputField)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(firstName).perform();
        CommonMethods.waitForElementClickable(userLoginEmailInputField).click();
        ExtentLogger.pass("Entered user first name as - " + firstName);
    }

    public void enterUserLastName(String lastName) {
        CommonMethods.wait(1000);
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(userLastnameInputField)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(lastName).perform();
        ExtentLogger.pass("Entered user last name as - " + lastName);
    }

    public void enterUserPassword(String password) {
        CommonMethods.wait(1000);
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(userPasswordInputField)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(password).perform();
        ExtentLogger.pass("Entered user password as - " + password);
    }

    public void enterUserPersonnelNumber(String number) {
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(userPersonnelInputField)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(number).perform();
        ExtentLogger.pass("Enter user personnel number as - " + number);
    }

    public void enterUserPosition(String position) {
        WebElement positionInputField = CommonMethods.waitForElementVisibility(userPositionInputField);
        positionInputField.clear();
        positionInputField.sendKeys(position);
        ExtentLogger.pass("Entered user position as - " + position);
    }

    public void enterUserPositionStartDate(String startDate) {
        WebElement positionStartDateField = CommonMethods.waitForElementVisibility(positionStartDateInputField);
        positionStartDateField.clear();
        positionStartDateField.sendKeys(startDate);
        ExtentLogger.pass("Entered position start date as - " + startDate);
    }

    public void saveUserPositionDetails() {
        CommonMethods.waitForElementClickable(userPositionSaveButton).click();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementVisibility(userCategorySelectDropdown);
        ExtentLogger.pass("Saved the user position details");
    }

    public void deleteCategoryHistoryOfUser(String positionStartDate, String category, String position) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(userPositionTable);
        CommonMethods.wait(1000);
        List<WebElement> positionStartList = driver.findElements(positionStartDateList);
        for (WebElement ele : positionStartList) {
            count++;
            if (ele.getText().contains(positionStartDate)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(positionHistoryCategoryVerify, count))).getText(), category);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(positionHistoryCategory, count))).getText(), position);
                CommonMethods.waitForElementClickable(By.xpath(String.format(positionDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                CommonMethods.acceptAlert();
                CommonMethods.waitForSuccessMessageSidePopup();
                ExtentLogger.pass("Deleted the " + position + " position history of user for " + category + " category starting from " + positionStartDate);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Failed to delete " + position + " position history of user for " + category + " category");
            Assert.fail("Failed to delete " + position + " position history of user for " + category + " category");
            Assert.fail("Failed to delete " + position + " position history of user for " + category + " category");
        }
    }

    public void deleteUser(String userEmailToDelete) {
        CommonMethods.waitForElementVisibility(usersTable);
        WebElement userSearched = CommonMethods.waitForElementClickable(userSearchedEmail);
        if (userSearched.getText().equals(userEmailToDelete)) {
            CommonMethods.waitForElementClickable(userDeleteIcon).click();
            wait.until(ExpectedConditions.alertIsPresent());
            CommonMethods.acceptAlert();
            CommonMethods.waitForSuccessMessageSidePopup();
            ExtentLogger.pass("Deleted user with " + userEmailToDelete + " email");
        } else {
            ExtentLogger.fail("Failed to delete user with " + userEmailToDelete + " email");
            Assert.fail("Failed to delete user with " + userEmailToDelete + " email");
        }
    }

    public void checkAllowNonBillingHoursForUser() {
        WebElement checkbox = CommonMethods.waitForElementClickable(allowNonBillingHoursCheckbox);
        if (checkbox.isSelected()) {
            ExtentLogger.pass("Checked the allow adding non-billing hours checkbox");
        } else {
            checkbox.click();
            ExtentLogger.pass("Checked the allow adding non-billing hours checkbox");
        }
    }

    public void clickCreateNewUserButton() {
        CommonMethods.waitForElementClickable(createNewUserButton).click();
        CommonMethods.waitForElementClickable(userLoginEmailInputField);
        ExtentLogger.pass("Clicked the create new user button");
    }
}