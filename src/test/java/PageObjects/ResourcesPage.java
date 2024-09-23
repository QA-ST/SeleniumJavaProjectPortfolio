package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class ResourcesPage extends BaseClass {
    By planningTab = By.id("btnCapacityManagementUnit");
    By planningFrame = By.xpath("//iframe[@id='ifrmCapacityManagement']");
    By dayNumberToViewInputField = By.id("txtNoOfIntervals");
    By planningStartDateInputField = By.xpath("//input[@id='dtStartDate']");
    By planningFilterApplyButton = By.id("btnApplyFilter");
    By planningTableMaximizeIcon = By.xpath("//a[@class='maximize']");
    By planningTableMaximizeVerify = By.xpath("//a[@class='maximize']/span[@title='minimize this area']");
    By planningTableMinimizeVerify = By.xpath("//a[@class='maximize']/span[@title='maximize this area']");
    By planningTableLoader = By.xpath("//div[@id='divShowMore' and @class='showmore' and contains(@style,'display: block;')]");
    By projectsListPlanningTable = By.xpath("//div[@id='divProjectBodyLeft']/div[@datatype='project']/span/a");
    By planningIntervalFilterDropdown = By.xpath("//select[@id='drpIntervals']");
    By tasksListPlanningTable = By.xpath("//div[@id='divProjectBodyLeft']/div[@datatype='task']/span");
    String projectExpandArrowIcon = "(//div[@id='divProjectBodyLeft']/div[@datatype='project'])[%s]/img[@class='arrowUpImage']";
    String taskExpandArrowIcon = "(//div[@id='divProjectBodyLeft']/div[@datatype='task'])[%S]/img[@class='arrowUpImage']";

    public void switchToPlanning() {
        CommonMethods.waitForElementClickable(planningTab).click();
        ExtentLogger.pass("Switched to planning tab");
    }

    public void maximizePlanningTable() {
        CommonMethods.waitForElementClickable(planningTableMaximizeIcon).click();
        CommonMethods.waitForElementVisibility(planningTableMaximizeVerify);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(planningTableLoader));
        CommonMethods.wait(1000);
        ExtentLogger.pass("Maximized the planning tab table");
    }

    public void minimizePlanningTable() {
        CommonMethods.waitForElementClickable(planningTableMaximizeIcon).click();
        CommonMethods.waitForElementVisibility(planningTableMinimizeVerify);
        ExtentLogger.pass("Minimized the planning tab table");
    }

    public void switchToPlanningIframe() {
        CommonMethods.switchToIframe(planningFrame);
        ExtentLogger.pass("Switched to planning frame");
    }

    public void enterNumberOfDaysToViewPlanningData(String numberOfDays) {
        WebElement numberInputField = CommonMethods.waitForElementVisibility(dayNumberToViewInputField);
        try {
            numberInputField.clear();
            CommonMethods.clickUsingJavascript(numberInputField);
            CommonMethods.getActions().moveToElement(numberInputField).click().perform();
            numberInputField.sendKeys(numberOfDays);
        } catch (Exception ex) {
            CommonMethods.clickUsingJavascript(numberInputField);
            CommonMethods.getActions().moveToElement(numberInputField).click().perform();
            numberInputField.sendKeys(numberOfDays);
        }
        ExtentLogger.pass("Entered " + numberOfDays + " days to view planning data");
    }

    public void enterStartDateToViewPlanningData(String startDate) {
        WebElement startDateField = CommonMethods.waitForElementClickable(planningStartDateInputField);
        try {
            startDateField.clear();
            CommonMethods.clickUsingJavascript(startDateField);
            CommonMethods.getActions().moveToElement(startDateField).click().perform();
            startDateField.sendKeys(startDate);
        } catch (Exception ex) {
            CommonMethods.clickUsingJavascript(startDateField);
            CommonMethods.getActions().moveToElement(startDateField).click().perform();
            startDateField.sendKeys(startDate);
        }
        ExtentLogger.pass("Entered " + startDate + " start date to view planning data");
    }

    public void clickPlanningApplyButton() {
        CommonMethods.clickUsingJavascript(CommonMethods.waitForElementClickable(planningFilterApplyButton));
        CommonMethods.waitForElementVisibility(planningTableLoader);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(planningTableLoader));
        ExtentLogger.pass("Clicked the planning table filter apply button");
    }

    public void expandProjectFromPlanningTable(String projectToView) {
        int count = 0;
        boolean status = false;
        wait.until(ExpectedConditions.invisibilityOfElementLocated(planningTableLoader));
        CommonMethods.wait(1000);
        List<WebElement> projectsList = driver.findElements(projectsListPlanningTable);
        for (WebElement ele : projectsList) {
            count++;
            if (ele.getText().equals(projectToView)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(projectExpandArrowIcon, count))).click();
                ExtentLogger.pass("Expanded the " + projectToView + " project from the planning table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectToView + " project not found");
            Assert.fail(projectToView + " project not found");
        }
    }

    public void selectIntervalForPlanningData(String interval) {
        WebElement intervalDropdown = CommonMethods.waitForElementClickable(planningIntervalFilterDropdown);
        CommonMethods.getSelect(intervalDropdown).selectByVisibleText(interval);
        ExtentLogger.pass("Selected " + interval + " interval for planning data");
    }

    public void expandTaskFromPlanningTable(String taskToExpand) {
        int count = 0;
        boolean status = false;
        wait.until(ExpectedConditions.invisibilityOfElementLocated(planningTableLoader));
        CommonMethods.wait(1000);
        List<WebElement> tasksList = driver.findElements(tasksListPlanningTable);
        for (WebElement ele : tasksList) {
            count++;
            if (ele.getText().equals(taskToExpand)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(taskExpandArrowIcon, count))).click();
                ExtentLogger.pass("Expanded the " + taskToExpand + " task from the planning table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskToExpand + " task not found");
            Assert.fail(taskToExpand + " task not found");
        }
    }
}