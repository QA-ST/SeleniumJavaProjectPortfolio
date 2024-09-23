package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BusinessGoalsPage extends BaseClass {
    By addNewBusinessPlanButton = By.xpath("//a[contains(@id,'btnAddNewPlan')]");
    By businessPlanNameInputField = By.xpath("//input[contains(@id,'txtBusinessPlanName')]");
    By businessPlanStartDateInputField = By.xpath("(//input[contains(@id,'txtPlanStartDate_dateInput')])[1]");
    By businessPlanEndDateInputField = By.xpath("(//input[contains(@id,'txtPlanEndDate_dateInput')])[1]");
    By businessGoalSaveButton = By.xpath("//a[contains(@id,'Submit')]");
    By businessPlanTable = By.xpath("//div[contains(@id,'grdBusinessPlan')]/table/thead/tr/th[1]");
    By businessPlansList = By.xpath("(//div[contains(@id,'grdBusinessPlan')]/table)[1]/tbody/tr/td[2]/a");
    By businessPlanTablePagination = By.xpath("//div[contains(@id,'grdBusinessPlan')]//div[contains(@class,'rgNumPart')]");
    By addNewGoalButton = By.xpath("//a[contains(@id,'btnAddNewGoal')]");
    By businessPlanDropdown = By.xpath("//select[contains(@id,'drpBusinessPlan')]");
    By businessGoalNameInputField = By.xpath("//input[contains(@id,'txtBusinessGoalName')]");
    By businessGoalDescriptionInputField = By.xpath("//textarea[contains(@id,'txtGoalDescription')]");
    By businessGoalTable = By.xpath("//div[contains(@id,'grdBusinessGoal')]/table/thead/tr/th[1]");
    By businessGoalsList = By.xpath("//div[contains(@id,'grdBusinessGoal')]/table/tbody/tr/td[3]");
    By businessGoalTablePagination = By.xpath("//div[contains(@id,'grdBusinessGoal')]//div[contains(@class,'rgNumPart')]");

    String businessPlanStartDateVerify = "//div[contains(@id,'grdBusinessPlan')]/table/tbody/tr[%s]/td[3]";
    String businessPlanEndDateVerify = "//div[contains(@id,'grdBusinessPlan')]/table/tbody/tr[%s]/td[4]";
    String businessGoalSelectedPlanVerify = "//div[contains(@id,'grdBusinessGoal')]/table/tbody/tr[%s]/td[4]";
    String businessGoalDescriptionVerify = "//div[contains(@id,'grdBusinessGoal')]/table/tbody/tr[%s]/td[5]/span";
    String businessPlanListLastSetPagination = "//div[contains(@id,'grdBusinessPlan')]//div[contains(@class,'rgNumPart')]/a";
    String businessGoalListLastSetPagination = "//div[contains(@id,'grdBusinessGoal')]//div[contains(@class,'rgNumPart')]/a";
    String businessGoalDeleteIcon = "//div[contains(@id,'grdBusinessGoal')]/table/tbody/tr[%s]/td[6]//a";
    String businessPlanDeleteIcon = "//div[contains(@id,'grdBusinessPlan')]/table/tbody/tr[%s]/td[5]//a";

    public void clickAddNewBusinessPlanButton() {
        CommonMethods.waitForElementClickable(addNewBusinessPlanButton).click();
        CommonMethods.waitForElementVisibility(businessPlanNameInputField);
        ExtentLogger.pass("Clicked the add new plan ");
    }

    public void enterBusinessPlanName(String businessPlanName) {
        WebElement businessPlanNameField = CommonMethods.waitForElementVisibility(businessPlanNameInputField);
        businessPlanNameField.clear();
        businessPlanNameField.sendKeys(businessPlanName);
        ExtentLogger.pass("Entered business plan name as - " + businessPlanName);
    }

    public void enterBusinessPlanStartDate(String startDate) {
        WebElement startDateField = CommonMethods.waitForElementVisibility(businessPlanStartDateInputField);
        startDateField.clear();
        startDateField.sendKeys(startDate);
        ExtentLogger.pass("Entered business plan start date as - " + startDate);
    }

    public void enterBusinessPlanEndDate(String endDate) {
        WebElement startDateField = CommonMethods.waitForElementVisibility(businessPlanEndDateInputField);
        startDateField.clear();
        startDateField.sendKeys(endDate);
        ExtentLogger.pass("Entered business plan end date as - " + endDate);
    }

    public void saveBusinessGoalDetails() {
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).click(CommonMethods.waitForElementClickable(businessGoalSaveButton)).perform();
        CommonMethods.waitForSuccessMessageSidePopup();
        ExtentLogger.pass("Save the business goal details");
    }

    public void verifyBusinessPlanFromList(String businessPlanToVerify, String startDate, String endDate) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessPlanTable);
        if (!driver.findElements(businessPlanTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessPlanListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessPlanListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessPlans = driver.findElements(businessPlansList);
        for (WebElement ele : businessPlans) {
            count++;
            if (ele.getText().equals(businessPlanToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(businessPlanStartDateVerify, count))).getText().contains(startDate));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(businessPlanEndDateVerify, count))).getText().contains(endDate));
                ExtentLogger.pass(businessPlanToVerify + " business plan is present with start date as : " + startDate + " and end date as : " + endDate);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(businessPlanToVerify + " business plan not found");
            Assert.fail(businessPlanToVerify + " business plan not found");
        }
    }

    public void clickAddNewBusinessGoalButton() {
        CommonMethods.waitForElementClickable(addNewGoalButton).click();
        CommonMethods.waitForElementClickable(businessPlanDropdown);
        ExtentLogger.pass("Clicked the add new business goal button");
    }

    public void selectBusinessPlanForGoal(String businessPlanToSelect) {
        WebElement dropdown = CommonMethods.waitForElementClickable(businessPlanDropdown);
        CommonMethods.getSelect(dropdown).selectByVisibleText(businessPlanToSelect);
        ExtentLogger.pass("Selected " + businessPlanToSelect + " business plan for the business goal");
    }

    public void enterBusinessGoalName(String businessGoalName) {
        WebElement goalNameField = CommonMethods.waitForElementVisibility(businessGoalNameInputField);
        goalNameField.clear();
        goalNameField.sendKeys(businessGoalName);
        ExtentLogger.pass("Enter business goal name as - " + businessGoalName);
    }

    public void enterBusinessGoalDescription(String businessGoalDescription) {
        WebElement goalDescriptionField = CommonMethods.waitForElementClickable(businessGoalDescriptionInputField);
        goalDescriptionField.clear();
        goalDescriptionField.sendKeys(businessGoalDescription);
        CommonMethods.waitForElementClickable(businessGoalNameInputField).click();
        ExtentLogger.pass("Enter business goal description as - " + businessGoalDescription);
    }

    public void verifyBusinessGoalDetailsFromTheList(String businessGoalToVerify, String businessPlanSelected, String businessGoalDescription) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessGoalTable);
        if (!driver.findElements(businessGoalTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessGoalListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessGoalListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessGoals = driver.findElements(businessGoalsList);
        for (WebElement ele : businessGoals) {
            count++;
            if (ele.getText().equals(businessGoalToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(businessGoalSelectedPlanVerify, count))).getText(), businessPlanSelected);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(businessGoalDescriptionVerify, count))).getText(), businessGoalDescription);
                ExtentLogger.pass(businessGoalToVerify + " business goal is present with plan selected as : " + businessPlanSelected);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(businessGoalToVerify + " business goal not found");
            Assert.fail(businessGoalToVerify + " business goal not found");
        }
    }

    public void clickBusinessGoalDeleteIcon(String businessGoalToDelete, String businessPlanSelected) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessGoalTable);
        if (!driver.findElements(businessGoalTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessGoalListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessGoalListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessGoals = driver.findElements(businessGoalsList);
        for (WebElement ele : businessGoals) {
            count++;
            if (ele.getText().equals(businessGoalToDelete)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(businessGoalSelectedPlanVerify, count))).getText(), businessPlanSelected);
                CommonMethods.waitForElementClickable(By.xpath(String.format(businessGoalDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the delete icon for the " + businessGoalToDelete + " business goal");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(businessGoalToDelete + " business goal not found");
            Assert.fail(businessGoalToDelete + " business goal not found");
        }
    }

    public void clickBusinessPlanDeleteIcon(String businessPlanToDelete) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessPlanTable);
        if (!driver.findElements(businessPlanTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessPlanListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessPlanListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessPlans = driver.findElements(businessPlansList);
        for (WebElement ele : businessPlans) {
            count++;
            if (ele.getText().equals(businessPlanToDelete)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(businessPlanDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the delete icon for the " + businessPlanToDelete + " business plan");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(businessPlanToDelete + " business plan not found");
            Assert.fail(businessPlanToDelete + " business plan not found");
        }
    }

    public void verifyBusinessGoalIsNotPresentInList(String businessGoalToVerify) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessGoalTable);
        if (!driver.findElements(businessGoalTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessGoalListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessGoalListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessGoals = driver.findElements(businessGoalsList);
        for (WebElement ele : businessGoals) {
            if (ele.getText().equals(businessGoalToVerify)) {
                status = true;
                break;
            }
        }
        if (status) {
            ExtentLogger.fail(businessGoalToVerify + " business goal is present in the list");
            Assert.fail(businessGoalToVerify + " business goal is present in the list");
        } else {
            ExtentLogger.pass(businessGoalToVerify + " business plan is not present in the list");
        }
    }

    public void verifyBusinessPlanIsNotPresentInTheList(String businessPlanToVerify) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(businessPlanTable);
        if (!driver.findElements(businessPlanTablePagination).isEmpty()) {
            int lastSetCount = driver.findElements(By.xpath(businessPlanListLastSetPagination)).size();
            CommonMethods.getActions().click(CommonMethods.waitForElementClickable(By.xpath(businessPlanListLastSetPagination + "[" + lastSetCount + "]"))).pause(Duration.ofSeconds(1)).perform();
        }
        List<WebElement> businessPlans = driver.findElements(businessPlansList);
        for (WebElement ele : businessPlans) {
            if (ele.getText().equals(businessPlanToVerify)) {
                status = true;
                break;
            }
        }
        if (status) {
            ExtentLogger.fail(businessPlanToVerify + " business plan is present in the list");
            Assert.fail(businessPlanToVerify + " business plan is present in the list");
        } else {
            ExtentLogger.pass(businessPlanToVerify + " business plan is not present in the list");
        }
    }
}