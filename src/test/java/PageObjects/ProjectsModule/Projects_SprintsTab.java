package PageObjects.ProjectsModule;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Projects_SprintsTab extends BaseClass {
    By projectSprintTab = By.id("liProjectSprints");
    By addSprintButton = By.id("btnAddSprint");
    By sprintNameInputField = By.xpath("//label[@tkey='Sprint Name:']//following::input[1]");
    By sprintDurationDropdown = By.id("sprintDuration");
    By sprintStartDate = By.id("sprintStartDate");
    By sprintEndDate = By.id("sprintEndDate");
    By sprintSaveButton = By.xpath("//button[@id='btnSave']");
    By sprintsList = By.xpath("//div[@id='sprints']//div[@id='divMain']");
    By sprintsNameList = By.xpath("//div[@id='divMain']//h4[@id='headerSprintName']");
    By sprintDetailFormIframe = By.id("ifrmAUS");

    String sprintDatesVerify = "(//div[@id='divMain']//h4[@id='headerSprintName'])[%s]//following::span[@id='spanSprintDates'][1]";
    String sprintTasksList = "(//div[@id='divMain']//h4[@id='headerSprintName'])[%s]//following::table[1]/tbody/tr/td[1]";
    String sprintTaskStatus = "(//div[@id='divMain']//h4[@id='headerSprintName'])[%s]//following::table[1]/tbody/tr[%s]/td[2]";
    String sprintEditDropdown = "(//div[@id='divMain']//h4[@id='headerSprintName'])[%s]/following::button[@id='drpMenuEditSprint'][1]";
    String sprintDeleteOption = "(//ul/li/a[@title='Delete'])[%s]";

    public void navigateToSprintsTab() {
        CommonMethods.waitForElementClickable(projectSprintTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementClickable(addSprintButton);
        CommonMethods.wait(2000);
        ExtentLogger.pass("Navigated to sprints tab");
    }

    public void clickAddSprintButton() {
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(addSprintButton)).perform();
        ExtentLogger.pass("Clicked the add sprint button");
    }

    public void switchToSprintFormIframe() {
        CommonMethods.switchToIframe(sprintDetailFormIframe);
        CommonMethods.wait(1000);
    }

    public void enterSprintName(String sprintName) {

        CommonMethods.waitForElementVisibility(sprintNameInputField).clear();
        driver.findElement(sprintNameInputField).sendKeys(sprintName);
        ExtentLogger.pass("Enter sprint name as - " + sprintName);
    }

    public void selectSprintDurationType(String durationType) {
        WebElement selectSprintDuration = CommonMethods.waitForElementClickable(sprintDurationDropdown);
        CommonMethods.getSelect(selectSprintDuration).selectByValue(durationType);
        ExtentLogger.pass("Selected sprint duration type as - " + durationType);
    }

    public void enterSprintStartDate(String startDate) {
        CommonMethods.waitForElementVisibility(sprintStartDate).clear();
        driver.findElement(sprintStartDate).sendKeys(startDate);
        ExtentLogger.pass("Set sprint start date as - " + startDate);
    }

    public void enterSprintEndDate(String endDate) {
        CommonMethods.waitForElementVisibility(sprintEndDate).clear();
        driver.findElement(sprintEndDate).sendKeys(endDate);
        ExtentLogger.pass("Set sprint end date as - " + endDate);
    }

    public void saveSprintDetails() {
        CommonMethods.waitForElementClickable(sprintSaveButton).click();
        CommonMethods.waitForPageLoader();
        ExtentLogger.pass("Saved the sprint details");
    }

    public void verifySprintIsPresentFromTheList(String sprintNameToVerify, String sprintStartDate, String sprintEndDate) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(sprintsList);
        List<WebElement> sprintsName = driver.findElements(sprintsNameList);
        for (WebElement sprint : sprintsName) {
            count++;
            if (sprint.getText().equals(sprintNameToVerify)) {
                String sprintDuration = CommonMethods.waitForElementVisibility(By.xpath(String.format(sprintDatesVerify, count))).getText();
                Assert.assertTrue(sprintDuration.contains(sprintStartDate));
                Assert.assertTrue(sprintDuration.contains(sprintEndDate));
                status = true;
                ExtentLogger.pass(sprintNameToVerify + " sprint is present in the list");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(sprintNameToVerify + " sprint not found");
            Assert.fail(sprintNameToVerify + " sprint not found");
        }
    }

    public void verifyTaskIsPresentInSprint(String sprintNameToVerify, String taskNameToVerify, String taskStatus) {
        boolean status = false;
        int sprintCount = 0;
        int taskCount = 0;
        CommonMethods.waitForElementVisibility(sprintsList);
        List<WebElement> sprintsName = driver.findElements(sprintsNameList);
        for (WebElement sprint : sprintsName) {
            sprintCount++;
            if (sprint.getText().equals(sprintNameToVerify)) {
                List<WebElement> sprintTasks = driver.findElements(By.xpath(String.format(sprintTasksList, sprintCount)));
                for (WebElement task : sprintTasks) {
                    taskCount++;
                    if (task.getText().equals(taskNameToVerify)) {
                        Assert.assertEquals(driver.findElement(By.xpath(String.format(sprintTaskStatus, sprintCount, taskCount))).getText(), taskStatus);
                        ExtentLogger.pass(taskNameToVerify + " task is present in the " + sprintNameToVerify + " sprint with " + taskStatus + " status");
                        status = true;
                        break;
                    }
                }
            }
        }
        if (!status) {
            ExtentLogger.fail(taskNameToVerify + " task not found in the " + sprintNameToVerify + " sprint");
            Assert.fail(taskNameToVerify + " task not found in the " + sprintNameToVerify + " sprint");
        }
    }

    public void verifySprintDeleteIconIsDisabled(String sprintNameToVerify) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(sprintsList);
        List<WebElement> sprintsName = driver.findElements(sprintsNameList);
        for (WebElement sprint : sprintsName) {
            count++;
            if (sprint.getText().equals(sprintNameToVerify)) {
                WebElement editDropdown = CommonMethods.waitForElementClickable(By.xpath(String.format(sprintEditDropdown, count)));
                CommonMethods.getActions().click(editDropdown).pause(Duration.ofSeconds(1)).perform();
                WebElement deleteOption = CommonMethods.waitForElementVisibility(By.xpath(String.format(sprintDeleteOption, count)));
                Assert.assertTrue(deleteOption.getAttribute("disabled") != null);
                status = true;
                ExtentLogger.pass(sprintNameToVerify + " sprint delete option is disabled");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(sprintNameToVerify + " sprint delete button is enabled");
            Assert.fail(sprintNameToVerify + " sprint delete button is enabled");
        }
    }

    public void selectSprintDeleteOption(String sprintToDelete) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(sprintsList);
        List<WebElement> sprintsName = driver.findElements(sprintsNameList);
        for (WebElement sprint : sprintsName) {
            count++;
            if (sprint.getText().equals(sprintToDelete)) {
                WebElement editDropdown = CommonMethods.waitForElementClickable(By.xpath(String.format(sprintEditDropdown, count)));
                CommonMethods.getActions().click(editDropdown).pause(Duration.ofSeconds(1)).perform();
                WebElement deleteOption = CommonMethods.waitForElementVisibility(By.xpath(String.format(sprintDeleteOption, count)));
                deleteOption.click();
                wait.until(ExpectedConditions.alertIsPresent());
                status = true;
                ExtentLogger.pass("Selected the delete option for the " + sprintToDelete + " sprint");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Delete option not clicked for the " + sprintToDelete + " sprint");
            Assert.fail("Delete option not clicked for the " + sprintToDelete + " sprint");
        }
    }
}