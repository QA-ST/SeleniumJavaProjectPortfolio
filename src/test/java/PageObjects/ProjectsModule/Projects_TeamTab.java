package PageObjects.ProjectsModule;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Projects_TeamTab extends BaseClass {
    By projectTeamTab = By.id("liProjectTeamAndCategories");
    By teamControlTab = By.xpath("//div[contains(@class,'tc-controls')]");
    By tasksList = By.xpath("//div[contains(@class,'tc-tasks')]//div[contains(@class,'tc-row')]");
    By switchTeamTabRightIcon = By.xpath("//div[@class='tc-ctrl-field-right']");
    By estimatedEffortLabel = By.xpath("//div[@class='tc-ctrl-field']/div[text()='estimated effort']");
    By addPeopleIcon = By.xpath("//div[@class='tc-member tc-add']/div[1]");
    By loginNameFilter = By.xpath("//input[@id='txtLogin']");
    By userPopupSaveButton = By.xpath("(//button[@tkey='Save'])[2]");
    By projectTeamMembersList = By.xpath("//div[@class='tc-members']/div[@class='tc-member']");

    String taskAssignmentDropdown = "//div[@class='tc-data']/div[%s]/div[@data-member='%s']";
    String rolesListOptions = "//ul[contains(@class,'tc-combo-options')]/li[@data-value='%s']";
    String estimatedTimeEffortInputField = "//div[@class='tc-data']/div[%s]/div[@data-member='%s']//input[@class='tc-hours-estimated']";
    String userSearchedRow = "//table[@id='tblProjectUsers']/tbody/tr[contains(@id,'%s') and @style='display: table-row;']";
    String userSelectCheckbox = "//table[@id='tblProjectUsers']/tbody/tr[contains(@id,'%s') and @style='display: table-row;']/td/input";

    public void navigateToTeamTab() {
        CommonMethods.waitForElementClickable(projectTeamTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementVisibility(teamControlTab);
        ExtentLogger.pass("Navigated to project team tab");
    }

    public void assignTaskToUser(String taskNameToAssign, String user, String userRole) {
        int count = 0;
        CommonMethods.waitForElementVisibility(teamControlTab);
        List<WebElement> tasksDisplayed = driver.findElements(tasksList);
        for (WebElement task : tasksDisplayed) {
            count++;
            if (task.getText().equals(taskNameToAssign)) {
                WebElement assignTaskDrp = CommonMethods.waitForElementClickable(By.xpath(String.format(taskAssignmentDropdown, count, user)));
                CommonMethods.getActions().moveToElement(assignTaskDrp).click().perform();
                WebElement role = CommonMethods.waitForElementClickable(By.xpath(String.format(rolesListOptions, userRole)));
                CommonMethods.getActions().moveToElement(role).pause(Duration.ofSeconds(1)).click().perform();
                ExtentLogger.pass("Assigned " + taskNameToAssign + " to " + user + " as " + userRole);
                break;
            }
        }
    }

    public void switchToEstimatedTimeEffort() {
        CommonMethods.waitForElementClickable(switchTeamTabRightIcon).click();
        CommonMethods.waitForElementVisibility(estimatedEffortLabel);
        ExtentLogger.pass("Switched to estimated effort tab");
    }

    public void addEstimatedEffortForTask(String taskNameToAssign, String user, String estimatedTime) {
        int count = 0;
        CommonMethods.waitForElementVisibility(teamControlTab);
        List<WebElement> tasksDisplayed = driver.findElements(tasksList);
        for (WebElement task : tasksDisplayed) {
            count++;
            if (task.getText().equals(taskNameToAssign)) {
                WebElement timeInputField = CommonMethods.waitForElementClickable(By.xpath(String.format(estimatedTimeEffortInputField, count, user)));
                timeInputField.click();
                CommonMethods.wait(2000);
                CommonMethods.getActions().sendKeys(estimatedTime).perform();
                CommonMethods.wait(2000);
                ExtentLogger.pass("Added estimated effort to " + taskNameToAssign + " of " + estimatedTime + " for " + user);
                break;
            }
        }
    }

    public void clickAddPeopleIcon() {
        CommonMethods.waitForElementClickable(addPeopleIcon).click();
        CommonMethods.waitForElementClickable(loginNameFilter);
        ExtentLogger.pass("Clicked the add people icon");
    }

    public void addUserToProjectFromTeamTab(String userLoginEmail) {
        CommonMethods.getActions().click(CommonMethods.waitForElementVisibility(loginNameFilter)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(userLoginEmail + Keys.ENTER).perform();
        CommonMethods.waitForElementVisibility(By.xpath(String.format(userSearchedRow, userLoginEmail)));
        CommonMethods.waitForElementClickable(By.xpath(String.format(userSelectCheckbox, userLoginEmail))).click();
        CommonMethods.waitForElementClickable(userPopupSaveButton).click();
        ExtentLogger.pass("Added user to project from team tab");
    }

    public void verifyUserIsAddedToProjectFromTeamTab(String userEmailToVerify) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(teamControlTab);
        CommonMethods.wait(1000);
        List<WebElement> usersList = driver.findElements(projectTeamMembersList);
        for (WebElement ele : usersList) {
            String userEmail = ele.getAttribute("data-member");
            if (userEmail.equals(userEmailToVerify)) {
                ExtentLogger.pass(userEmailToVerify + " user found in the project members list");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail(userEmailToVerify + " user not found in the project members list");
            Assert.fail(userEmailToVerify + " user not found in the project members list");
        }
    }
}