package PageObjects.ProjectsModule;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Projects_GanttTab extends BaseClass {
    By projectsGanttTab = By.id("liProjectGantt");
    By ganttPageLoader = By.xpath("//div/i[contains(@class,'icon-spinner')]");
    By ganttSearchBar = By.xpath("//input[contains(@name,'b-textfield')]");
    By ganttTaskTable = By.xpath("//div[@id='b-gantt-1-lockedSubgrid']");
    public By ganttTasksNameList = By.xpath("//div[contains(@id,'lockedSubgrid')]/div[contains(@class,'b-grid-row')]/div[contains(@class,'b-name')]//div[@class='b-tree-cell-value']");
    public By ganttTaskEditPopup = By.xpath("(//div[contains(@class,'b-gantttaskeditor')])[2]");
    By ganttTaskEditPopupResourcesTabButton = By.xpath("//div[contains(@class,'b-tabpanel-tabs')]/button[4]");
    By ganttTaskEditPopupResourcesTable = By.xpath("//header//div[contains(@aria-label,'Resource column')]");
    By resourcesAssignedList = By.xpath("//header//div[contains(@aria-label,'Resource column')]//following::div[contains(@class,'b-grid-row')]/div[@data-column='resource']");
    By ganttEditTaskPopupSaveButton = By.xpath("//button[contains(@class,'btn-edit-task') and @data-ref='saveButton']");
    public By projectStartDateDisplayed = By.id("lblStartDateVal");
    public By projectEndDateDisplayed = By.id("lblEndDateVal");
    public By projectCompletedPercentageDisplayed = By.id("lblLastFollowUpVal");
    By shareGanttIcon = By.xpath("//div[@id='gantt-icon-wrapper']/*[local-name()='svg' and @id='shareGantt']");
    By lastGanttSnapshotTimestamp = By.xpath("//div[contains(@class,'share-timestamp')]");
    By lastGanttSnapshotDeleteIcon = By.xpath("//span[@id='lastSnapshot']//following::*[local-name()='svg']");
    By lastSnapshotDeleteConfirmButton = By.xpath("//itm-button[contains(@id,'btn-del-confirm')]");
    By createGanttSnapshotButton = By.xpath("//itm-button[contains(@id,'btn-create')]");
    By updateGanttSnapshotButton = By.xpath("//div[contains(@class,'update')]/itm-button");
    By ganttSharePopupDoneButton = By.xpath("//itm-button[contains(@id,'btn-done')]");
    By ganttSnapshotCopyLinkButton = By.xpath("//itm-button[contains(@id,'copylink')]");
    By closeGanttEditTaskPopupIcon = By.xpath("//button[contains(@class,'b-popup-close')]");
    By createAutomaticProgressReportCheckbox = By.id("chkCreateAnAutomaticFollowUp");

    String taskStartDateVerify = "//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')][%s]/div[@data-column='startDate']";
    String taskEndDateVerify = "//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')][%s]/div[@data-column='endDate']";
    String taskDurationVerify = "//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')][%s]/div[@data-column='fullDuration']";
    String taskResourceEffortColumn = "//header//div[contains(@aria-label,'Resource column')]//following::div[contains(@class,'b-grid-row')][%s]/div[@data-column='effort']";

    public void navigateToGanttTab() {
        CommonMethods.waitForElementClickable(projectsGanttTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.wait(4000);
        ExtentLogger.pass("Navigated to gantt tab");
    }

    public void searchTaskFromGanttSearchBar(String taskNameToSearch) {
        WebElement searchBar = CommonMethods.waitForElementClickable(ganttSearchBar);
        searchBar.clear();
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).sendKeys(taskNameToSearch).pause(Duration.ofSeconds(2)).perform();
        ExtentLogger.pass("Searched task from gantt search bar - " + taskNameToSearch);
    }

    public void verifyTaskFromGanttTab(String taskNameToVerify, String taskStartDate, String taskEndDate, String taskDuration) {
        int count = 0;
        boolean status = false;
        List<WebElement> tasksList = driver.findElements(ganttTasksNameList);
        for (WebElement ele : tasksList) {
            count++;
            if (ele.getText().equals(taskNameToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskStartDateVerify, count))).getText(), taskStartDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskEndDateVerify, count))).getText(), taskEndDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskDurationVerify, count))).getText(), taskDuration);
                ExtentLogger.pass(taskNameToVerify + " task is present in the gantt tasks list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskNameToVerify + " not found in the gantt tasks list");
            Assert.fail(taskNameToVerify + " not found in the gantt tasks list");
        }
    }

    public void openEditTaskPopupForTask(String taskNameToEdit) {
        boolean status = false;
        CommonMethods.wait(1000);
        for (int attempt = 0; attempt < 3; attempt++) {
            List<WebElement> tasksList = driver.findElements(ganttTasksNameList);
            for (WebElement ele : tasksList) {
                if (ele.getText().equals(taskNameToEdit)) {
                    CommonMethods.getActions().moveToElement(ele).contextClick().sendKeys(Keys.DOWN, Keys.RETURN).perform();
                    CommonMethods.waitForElementVisibility(ganttTaskEditPopup);
                    status = true;
                    ExtentLogger.pass("Opened gantt edit task popup for " + taskNameToEdit + " task");
                    break;
                }
            }
        }
        if (!status) {
            ExtentLogger.fail("Gantt edit task popup not visible for " + taskNameToEdit + " task");
            Assert.fail("Gantt edit task popup not visible for " + taskNameToEdit + " task");
        }
    }

    public void switchToResourcesTabInGanttEditTaskPopup() {
        CommonMethods.waitForElementClickable(ganttTaskEditPopupResourcesTabButton).click();
        CommonMethods.waitForElementVisibility(ganttTaskEditPopupResourcesTable);
        ExtentLogger.pass("Switch to the resources tab in the gantt edit task popup");
    }

    public void addEffortToTaskFromGanttEditTaskPopup(String resourceName, String effortToAdd) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(ganttTaskEditPopupResourcesTable);
        List<WebElement> resourcesList = driver.findElements(resourcesAssignedList);
        for (WebElement ele : resourcesList) {
            count++;
            if (ele.getText().equals(resourceName)) {
                CommonMethods.getActions().doubleClick(CommonMethods.waitForElementClickable(driver.findElement(By.xpath(String.format(taskResourceEffortColumn, count))))).pause(Duration.ofSeconds(1)).sendKeys(effortToAdd).perform();
                CommonMethods.waitForElementClickable(ganttEditTaskPopupSaveButton).click();
                ExtentLogger.pass("Added " + effortToAdd + " hrs task effort to " + resourceName + " resource");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass("Failed to add effort to the task for " + resourceName + " resource");
            Assert.fail("Failed to add effort to the task for " + resourceName + " resource");
        }
    }

    public void saveProjectGanttDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.wait(2000);
        ExtentLogger.pass("Saved the project gantt details");
    }

    public void clickShareGanttSnapshotShareIcon() {
        CommonMethods.waitForElementClickable(shareGanttIcon).click();
        CommonMethods.waitForElementClickable(ganttSharePopupDoneButton);
        ExtentLogger.pass("Clicked the gantt snapshot icon");
    }

    public void deleteLastGanttSnapshot() {
        if (driver.findElement(lastGanttSnapshotTimestamp).getCssValue("display").equals(CommonMethods.displayFlexVerify)) {
            CommonMethods.waitForElementClickable(lastGanttSnapshotDeleteIcon).click();
            CommonMethods.waitForElementClickable(lastSnapshotDeleteConfirmButton).click();
            CommonMethods.waitForElementVisibility(createGanttSnapshotButton);
            ExtentLogger.pass("Deleted the last gantt snapshot");
        } else {
            ExtentLogger.pass("No last gantt snapshot exists");
        }
    }

    public void createGanttSnapshot() {
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(createGanttSnapshotButton)).perform();
        CommonMethods.waitForElementVisibility(lastGanttSnapshotTimestamp);
        CommonMethods.waitForElementVisibility(updateGanttSnapshotButton);
        ExtentLogger.pass("Created gantt snapshot");
    }

    public void clickGanttSharePopupDoneButton() {
        CommonMethods.waitForElementClickable(ganttSharePopupDoneButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ganttSharePopupDoneButton));
        ExtentLogger.pass("Clicked the gantt share popup done button");
    }

    public String copyGanttSnapshotLink() {
        CommonMethods.clickUsingJavascript(CommonMethods.waitForElementClickable(ganttSnapshotCopyLinkButton));
        CommonMethods.wait(2000);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String clipboardText = null;
        try {
            clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            throw new RuntimeException(e);
        }
        ExtentLogger.pass("Copied the gantt snapshot link");
        return clipboardText;
    }

    public void verifyUserEstimateEffortFromGanttEditTaskPopup(String userName, String estimateEffort) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(ganttTaskEditPopupResourcesTable);
        List<WebElement> resourcesList = driver.findElements(resourcesAssignedList);
        for (WebElement ele : resourcesList) {
            count++;
            if (ele.getText().equals(userName)) {
                String hoursDisplayed = CommonMethods.waitForElementClickable(By.xpath(String.format(taskResourceEffortColumn, count))).getText();
                Assert.assertEquals(hoursDisplayed, estimateEffort);
                ExtentLogger.pass(userName + " user assigned " + estimateEffort + " hrs, verified from gantt task popup");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass(userName + " user not assigned " + estimateEffort + " hrs, verified from gantt task popup");
            Assert.fail(userName + " user not assigned " + estimateEffort + " hrs, verified from gantt task popup");
        }
    }

    public void closeGanttEditTaskPopup() {
        CommonMethods.waitForElementClickable(closeGanttEditTaskPopupIcon).click();
        CommonMethods.wait(1000);
        ExtentLogger.pass("Closed the gantt task popup");
    }

    public void verifyCreateAutomaticProgressReportCheckboxIsChecked() {
        WebElement automaticProgressReportCheckbox = CommonMethods.waitForElementClickable(createAutomaticProgressReportCheckbox);
        if (automaticProgressReportCheckbox.isSelected()) {
            ExtentLogger.pass("Create automatic progress report checkbox is checked");
        } else {
            ExtentLogger.fail("Create automatic progress report checkbox is not checked");
            Assert.fail("Create automatic progress report checkbox is not checked");
        }
    }
}