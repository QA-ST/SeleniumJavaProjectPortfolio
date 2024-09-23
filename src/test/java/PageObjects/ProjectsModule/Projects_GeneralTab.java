package PageObjects.ProjectsModule;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Projects_GeneralTab extends BaseClass {
    By projectGeneralTab = By.id("liGeneral");
    public By projectNameInputField = By.id("txtProjectName");
    By approvalStatusDropdownIcon = By.xpath("//span[text()='Approval Status:']//following::span[@class='k-select'][1]");
    By priorityDropdownIcon = By.xpath("//span[text()='Priority:']//following::span[@class='k-select'][1]");
    By projectTypeDropdownIcon = By.xpath("//span[text()='Project Type:']//following::span[@class='k-select'][1]");
    By enableSprintCheckbox = By.id("chkEnableSprint");
    By businessGoalDropdown = By.id("txtBusinessGoal");
    By businessGoalsDropdownList = By.xpath("//div[@id='divBusinessGoal']/ul/li//span");
    By nullBusinessGoal = By.xpath("//div[@id='divBusinessGoal']/ul/li[@class='k-item k-first']//span");
    By enableRevenueCheckbox = By.id("chkEnableRevenue");
    By projectStartDateInputField = By.id("txtStartDate");
    By projectEndDateInputField = By.id("txtEndDate");
    By projectStatusSelectIcon = By.xpath("//button[@id='btnGetProjectStatus']");
    By projectStatusDropdownIcon = By.xpath("//label[@tkey='Select the new status:']//following::span[@class='k-select']");
    By projectStatusDropdown = By.xpath("//ul[contains(@id,'drpModalStatus')]");
    By projectStatusSaveButton = By.xpath("//button[@id='btnSaveAuthorization']");

    String approvalStatusList = "drpProjectApproval_listbox";
    String priorityList = "drpPriority_listbox";
    String projectTypeList = "drpProjectType_listbox";
    String dropdownList = "//ul[@id='%s']//li/span[2]";
    String projectStatusList = "(//ul[contains(@id,'drpModalStatus')])[%s]/li/span[2]";

    public void navigateToProjectGeneralTab() {
        CommonMethods.waitForElementClickable(projectGeneralTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.wait(1000);
        CommonMethods.waitForElementVisibility(projectNameInputField);
        ExtentLogger.pass("Navigated to project general tab");
    }

    public void enterProjectName(String projectName) {
        WebElement projectNameField = CommonMethods.waitForElementClickable(projectNameInputField);
        CommonMethods.getActions().click(projectNameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectName).perform();
        ExtentLogger.pass("Defined project name as - " + projectName);
    }

    public void selectProjectApprovalStatus(String approvalStatusToSelect) {
        CommonMethods.getActions().click(driver.findElement(approvalStatusDropdownIcon)).pause(Duration.ofSeconds(1)).perform();
        try {
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.id(approvalStatusList)));
        } catch (Exception ex) {
            CommonMethods.getActions().click(driver.findElement(approvalStatusDropdownIcon)).pause(Duration.ofSeconds(1)).perform();
            CommonMethods.waitForElementVisibility(By.id(approvalStatusList));
        }
        List<WebElement> approvalStatuses = driver.findElements(By.xpath(String.format(dropdownList, approvalStatusList)));
        for (WebElement ele : approvalStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(approvalStatusToSelect)) {
                ele.click();
                ExtentLogger.pass("Selected project approval status as - " + approvalStatusToSelect);
                break;
            }
        }
    }

    public void selectProjectPriority(String priorityToSelect) {
        CommonMethods.getActions().click(driver.findElement(priorityDropdownIcon)).pause(Duration.ofSeconds(1)).perform();
        try {
            waitForElement.until(ExpectedConditions.elementToBeClickable(By.id(priorityList)));
        } catch (Exception ex) {
            CommonMethods.getActions().click(driver.findElement(priorityDropdownIcon)).pause(Duration.ofSeconds(1)).perform();
            CommonMethods.waitForElementClickable(By.id(priorityList));
        }
        List<WebElement> approvalStatuses = driver.findElements(By.xpath(String.format(dropdownList, priorityList)));
        for (WebElement ele : approvalStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(priorityToSelect)) {
                ele.click();
                ExtentLogger.pass("Selected project priority as - " + priorityToSelect);
                break;
            }
        }
    }

    public void selectProjectType(String projectTypeToSelect) {
        CommonMethods.getActions().click(driver.findElement(projectTypeDropdownIcon)).pause(Duration.ofSeconds(1)).perform();
        CommonMethods.waitForElementClickable(By.id(projectTypeList));
        List<WebElement> approvalStatuses = driver.findElements(By.xpath(String.format(dropdownList, projectTypeList)));
        for (WebElement ele : approvalStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(projectTypeToSelect)) {
                ele.click();
                ExtentLogger.pass("Selected project type as - " + projectTypeToSelect);
                break;
            }
        }
    }

    public void checkEnableSprintsCheckbox() {
        WebElement checkbox = CommonMethods.waitForElementClickable(enableSprintCheckbox);
        CommonMethods.wait(1000);
        if (checkbox.isSelected()) {
            ExtentLogger.pass("Checked the enable sprint checkbox");
        } else {
            CommonMethods.clickUsingJavascript(checkbox);
            ExtentLogger.pass("Checked the enable sprint checkbox");
        }
    }

    public void uncheckEnableSprintCheckbox() {
        WebElement checkbox = CommonMethods.waitForElementClickable(enableSprintCheckbox);
        CommonMethods.wait(1000);
        if (checkbox.isSelected()) {
            CommonMethods.clickUsingJavascript(checkbox);
            Assert.assertNotEquals(checkbox.getAttribute("class"), CommonMethods.dirtyClass);
            ExtentLogger.pass("Unchecked the enable sprint checkbox");
        } else {
            ExtentLogger.pass("Unchecked the enable sprint checkbox");
        }
    }

    public void saveProjectGeneralDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementVisibility(projectNameInputField);
        ExtentLogger.pass("Saved the project general details");
    }

    public void verifyEnableSprintCheckboxIsDisabled() {
        WebElement ele = CommonMethods.waitForElementVisibility(enableSprintCheckbox);
        CommonMethods.wait(1000);
        if (!ele.isEnabled()) {
            ExtentLogger.pass("Enable sprint checkbox is disabled");
        } else {
            ExtentLogger.fail("Enable sprint checkbox is enabled");
            Assert.fail("Enable sprint checkbox is enabled");
        }
    }

    public void selectBusinessGoalForProject(String businessPlan, String businessGoal) {
        boolean status = false;
        String businessGoalToSelect = businessPlan + "\\" + businessGoal;
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(businessGoalDropdown)).pause(Duration.ofSeconds(1)).perform();
        List<WebElement> businessGoals = driver.findElements(businessGoalsDropdownList);
        for (WebElement ele : businessGoals) {
            String businessGoalName = ele.getText();
            if (!businessGoalName.isEmpty() && businessGoalName.equals(businessGoalToSelect)) {
                CommonMethods.getActions().moveToElement(ele).click().perform();
                if (driver.findElement(businessGoalDropdown).getAttribute("value").equals(businessGoalToSelect)) {
                    ExtentLogger.pass("Business goal selected as - " + businessGoal);
                    status = true;
                }
            }
        }
        if (!status) {
            ExtentLogger.fail(businessGoal + " business goal not selected for project");
            Assert.fail(businessGoal + " business goal not selected for project");
        }
    }

    public void removeBusinessGoalFromProject() {
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(businessGoalDropdown)).pause(Duration.ofSeconds(1)).perform();
        CommonMethods.waitForElementClickable(nullBusinessGoal).click();
        Assert.assertEquals(CommonMethods.waitForElementClickable(businessGoalDropdown).getAttribute("value"), "");
        ExtentLogger.pass("Removed business goal from project");
    }

    public void checkEnableRevenueCheckbox() {
        try {
            WebElement checkbox = waitForElement.until(ExpectedConditions.elementToBeClickable(enableRevenueCheckbox));
            CommonMethods.wait(1000);
            if (checkbox.isSelected()) {
                ExtentLogger.pass("Checked the enable revenue checkbox");
            } else {
                CommonMethods.clickUsingJavascript(checkbox);
                ExtentLogger.pass("Checked the enable revenue checkbox");
            }
        } catch (Exception ex) {
            System.out.println("Revenue checkbox not present");
        }
    }

    public void enterProjectStartDate(String startDate) {
        WebElement startDateField = CommonMethods.waitForElementClickable(projectStartDateInputField);
        CommonMethods.getActions().click(startDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(startDate + Keys.ENTER).perform();
        ExtentLogger.pass("Entered project start date as - " + startDate);
    }

    public void enterProjectEndDate(String endDate) {
        WebElement startDateField = CommonMethods.waitForElementClickable(projectEndDateInputField);
        CommonMethods.wait(1000);
        CommonMethods.getActions().click(startDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(endDate + Keys.ENTER).perform();
        ExtentLogger.pass("Entered project start date as - " + endDate);
    }

    public void selectProjectStatus(String statusToSelect) {
        boolean status = false;
        CommonMethods.waitForElementClickable(projectStatusSelectIcon).click();
        WebElement statusDropdownIcon = CommonMethods.waitForElementVisibility(projectStatusDropdownIcon);
        int listCount = driver.findElements(projectStatusDropdown).size();
        CommonMethods.getActions().click(statusDropdownIcon).pause(Duration.ofSeconds(1)).perform();
        List<WebElement> projectStatuses = driver.findElements(By.xpath(String.format(projectStatusList, listCount)));
        for (WebElement ele : projectStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(statusToSelect)) {
                CommonMethods.wait(1000);
                CommonMethods.clickUsingJavascript(ele);
                ExtentLogger.pass("Selected project status as - " + statusToSelect);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Not able to select " + statusToSelect + " project status");
            Assert.fail("Not able to select " + statusToSelect + " project status");
        }
    }

    public void saveProjectStatusDetails() {
        CommonMethods.waitForElementClickable(projectStatusSaveButton).click();
        CommonMethods.wait(1000);
        ExtentLogger.pass("Saved the project status details");
    }
}