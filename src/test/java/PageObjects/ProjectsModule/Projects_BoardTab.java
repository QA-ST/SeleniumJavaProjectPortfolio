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

public class Projects_BoardTab extends BaseClass {
    By projectBoardTab = By.id("liKanbanBoard");
    By createTaskButton = By.id("btnAddTask");
    By taskPopup = By.xpath("//div[@id='modelForTask']");
    By taskNameInputField = By.id("txtTaskName");
    By taskPopupSaveButton = By.xpath("//itm-button[@tkey='Save']");
    By taskPopupCloseButton = By.xpath("//div[@id='modelForTask']//button[contains(@class,'close')]");
    By boardTasksList = By.xpath(" //tr[@class='trProjectSwimlaneStatus']/td//ul/li//div[@class='taskName']/span");
    By addPeopleButtonTaskPopup = By.xpath("//div[@class='divAddPeople']/div[@id='addIcon']");
    By teamMemberEmailFilter = By.xpath("//label[@tkey='Login:']//following::input[1]");
    By userSearchedTaskPopup = By.xpath("//table[@id='tblProjectUsers']/tbody/tr/td[4]");

    String userSearchedSelectCheckbox = "//table[@id='tblProjectUsers']/tbody/tr/td[1]/input[@value='%s']";

    public void navigateToBoardTab() {
        CommonMethods.waitForElementClickable(projectBoardTab).click();
        CommonMethods.waitForPageLoader();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonMethods.hiddenPageLoader));
        CommonMethods.waitForElementClickable(createTaskButton);
        ExtentLogger.pass("Navigated to project board tab");
    }

    public void clickCreateTaskButtonInBoard() {
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).click(CommonMethods.waitForElementClickable(createTaskButton)).perform();
        Assert.assertEquals(CommonMethods.waitForElementVisibility(taskPopup).getCssValue("display"), CommonMethods.displayBlockVerify);
        ExtentLogger.pass("Clicked the create task button in the board");
    }

    public void enterTaskNameInTaskPopup(String taskName) {
        WebElement taskNameField = CommonMethods.waitForElementVisibility(taskNameInputField);
        taskNameField.clear();
        taskNameField.sendKeys(taskName);
        ExtentLogger.pass("Entered task name as - " + taskName);
    }

    public void clickTaskPopupSaveButton() {
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(taskPopupSaveButton)).pause(Duration.ofSeconds(2)).perform();
        ExtentLogger.pass("Clicked the task popup save button");
    }

    public void closeTaskPopup() {
        CommonMethods.waitForElementClickable(taskPopupCloseButton).click();
        Assert.assertEquals(CommonMethods.waitForElementVisibility(taskPopup).getCssValue("display"), CommonMethods.displayBlockVerify);
        ExtentLogger.pass("Closed the board task popup");
    }

    public void openTaskPopup(String taskToOpen) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(createTaskButton);
        List<WebElement> tasksList = driver.findElements(boardTasksList);
        for (WebElement ele : tasksList) {
            if (ele.getText().equals(taskToOpen)) {
                CommonMethods.getActions().click(ele).perform();
                Assert.assertEquals(CommonMethods.waitForElementVisibility(taskPopup).getCssValue("display"), CommonMethods.displayBlockVerify);
                ExtentLogger.pass("Opened the task popup from board for " + taskToOpen + " task");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskToOpen + " task not found in board");
            Assert.fail(taskToOpen + " task not found in board");
        }
    }

    public void selectUserToAddToTaskFromBoardTaskPopup(String userLoginEmail) {
        CommonMethods.waitForElementClickable(addPeopleButtonTaskPopup).click();
        CommonMethods.waitForElementVisibility(teamMemberEmailFilter).clear();
        CommonMethods.waitForElementClickable(teamMemberEmailFilter).sendKeys(userLoginEmail);
        WebElement userSearched = CommonMethods.waitForElementVisibility(userSearchedTaskPopup);
        if (userSearched.getText().equals(userLoginEmail)) {
            WebElement userSelectCheckbox = CommonMethods.waitForElementClickable(By.xpath(String.format(userSearchedSelectCheckbox, userLoginEmail)));
            userSelectCheckbox.click();
            Assert.assertEquals(userSelectCheckbox.getAttribute("class"), CommonMethods.dirtyClass);
            ExtentLogger.pass("Selected user with login email as " + userLoginEmail + " to add to task");
        } else {
            ExtentLogger.fail("Not found user with " + userLoginEmail + " email");
        }
    }
}