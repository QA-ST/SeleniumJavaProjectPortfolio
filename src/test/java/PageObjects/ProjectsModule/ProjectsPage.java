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

public class ProjectsPage extends BaseClass {
    String disableClickClass = "disableClick";
    String projectActiveFilterVerify = "Active";
    String projectInactiveFilterVerify = "InActive";
    String projectAllStatusFilterVerify = "All";

    static By addNewProjectButton = By.id("drpMenuCreatePrj");
    By createNewWaterfallProjectOption = By.xpath("//ul//a[contains(@tkey,'new Waterfall project')]");
    By createNewAgileProjectOption = By.xpath("//ul//a[contains(@tkey,'new Agile project')]");
    By projectTabs = By.xpath("//ul[contains(@class,'mainNav')]//li/a");
    By projectGanttTabOption = By.id("liProjectGantt");
    By projectBoardTabOption = By.id("liKanbanBoard");
    By projectSprintsTab = By.id("liProjectSprints");
    By projectNameFilterInputField = By.id("txtProjectName");
    By projectSearchedName = By.xpath("(//div[@id='projects']//table)[2]//tr[1]/td[2]/a");
    By latestProjectSearched = By.xpath("//ul[@id='ulPagination']//preceding::tr[1]/td[2]/a");
    By latestProjectSearchedType = By.xpath("//ul[@id='ulPagination']//preceding::tr[1]/td[3]");
    By latestProjectSearchedApprovalStatus = By.xpath("//ul[@id='ulPagination']//preceding::tr[1]/td[5]");
    By latestProjectSearchedPriority = By.xpath("//ul[@id='ulPagination']//preceding::tr[1]/td[6]");
    By projectSearchedType = By.xpath("(//div[@id='projects']//table)[2]//tr/td[3]");
    By projectSearchedApprovalStatus = By.xpath("(//div[@id='projects']//table)[2]//tr/td[5]");
    By projectSearchedPriority = By.xpath("(//div[@id='projects']//table)[2]//tr/td[6]");
    By projectSearchedAssessment = By.xpath("(//div[@id='projects']//table)[2]//tr/td[12]");
    By projectSearchedCompletedPercentage = By.xpath("(//div[@id='projects']//table)[2]//tr/td[13]");
    By activeProjectsFilterDropdown = By.xpath("//label[@tkey='Active']//following::input[1]");
    By activeProjectsFilterCheckbox = By.xpath("//div[@id='divActive']//ul//ul/li//span[text()='Active']//preceding::span[contains(@class,'checkbox-wrapper')][1]");
    By inactiveProjectsFilterCheckbox = By.xpath("//div[@id='divActive']//ul//li//span[text()='InActive']//preceding::span[contains(@class,'checkbox-wrapper')][1]");
    By projectLabel = By.id("lblHeader");
    By currentStatusDropdownIcon = By.xpath("//label/span[@tkey='Current Status:']//following::span[@class='k-select'][1]");
    By currentStatusList = By.xpath("//div[@id='drpStatus-list']//ul");
    By projectCrossIcon = By.xpath("(//div[@id='projects']//table)[2]//tr[1]/td/a[@id='imgDeleteProject']");

    String projectTabsClickStatusVerify = "//ul[contains(@class,'mainNav')]//li[%s]/a";
    String currentStatusListItems = "(//div[@id='drpStatus-list']//ul)[%s]/li/span[2]";

    public void selectNewWaterfallProjectOption() {
        CommonMethods.waitForElementClickable(addNewProjectButton).click();
        CommonMethods.waitForElementVisibility(createNewWaterfallProjectOption).click();
        CommonMethods.waitForElementVisibility(projectGanttTabOption);
        ExtentLogger.pass("Selected create new waterfall project option");
    }

    public void selectNewAgileProjectOption() {
        CommonMethods.waitForElementClickable(addNewProjectButton).click();
        CommonMethods.waitForElementClickable(createNewAgileProjectOption).click();
        CommonMethods.waitForElementVisibility(projectBoardTabOption);
        ExtentLogger.pass("Selected create new agile project option");
    }

    public void verifySprintsTabIsPresent() {
        CommonMethods.waitForElementVisibility(projectSprintsTab);
        ExtentLogger.pass("Sprint tab is present");
    }

    public void filterProjectListByName(String projectName) {
        CommonMethods.waitForElementClickable(projectNameFilterInputField).clear();
        driver.findElement(projectNameFilterInputField).sendKeys(projectName);
        ExtentLogger.pass("Entered " + projectName + " project name in the project name filter");
    }

    public void checkActiveProjectsFilter() {
        CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
        CommonMethods.waitForElementClickable(activeProjectsFilterCheckbox).click();
        WebElement filterStatusVerify = CommonMethods.waitForElementVisibility(activeProjectsFilterDropdown);
        if (filterStatusVerify.getAttribute("value").equals(projectActiveFilterVerify)) {
            ExtentLogger.pass("Checked the active projects filter checkbox");
        } else if (filterStatusVerify.getAttribute("value").equals(projectAllStatusFilterVerify)) {
            CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
            CommonMethods.waitForElementClickable(inactiveProjectsFilterCheckbox).click();
            Assert.assertEquals(filterStatusVerify.getAttribute("value"), projectActiveFilterVerify);
            ExtentLogger.pass("Checked the active projects filter checkbox");
        } else {
            CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
            CommonMethods.waitForElementClickable(activeProjectsFilterCheckbox).click();
            Assert.assertEquals(filterStatusVerify.getAttribute("value"), projectActiveFilterVerify);
            ExtentLogger.pass("Checked the active projects filter checkbox");
        }
    }

    public void checkInactiveProjectFilter() {
        CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
        CommonMethods.waitForElementClickable(inactiveProjectsFilterCheckbox).click();
        WebElement filterStatusVerify = CommonMethods.waitForElementVisibility(activeProjectsFilterDropdown);
        if (filterStatusVerify.getAttribute("value").equals(projectInactiveFilterVerify)) {
            ExtentLogger.pass("Checked the inactive projects filter checkbox");
        } else if (filterStatusVerify.getAttribute("value").equals(projectAllStatusFilterVerify)) {
            CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
            CommonMethods.waitForElementClickable(activeProjectsFilterCheckbox).click();
            Assert.assertEquals(filterStatusVerify.getAttribute("value"), projectInactiveFilterVerify);
            ExtentLogger.pass("Checked the inactive projects filter checkbox");
        } else {
            CommonMethods.waitForElementClickable(activeProjectsFilterDropdown).click();
            CommonMethods.waitForElementClickable(inactiveProjectsFilterCheckbox).click();
            Assert.assertEquals(filterStatusVerify.getAttribute("value"), projectInactiveFilterVerify);
            ExtentLogger.pass("Checked the inactive projects filter checkbox");
        }
    }

    public void verifyProjectFromProjectList(String projectNameToVerify, String projectTypeToVerify, String approvalStatusToVerify, String projectPriorityToVerify) {
        WebElement projectSearched = CommonMethods.waitForElementClickable(projectSearchedName);
        String projectName = projectSearched.getText();
        if (projectName.equals(projectNameToVerify)) {
            Assert.assertEquals(driver.findElement(projectSearchedType).getText(), projectTypeToVerify);
            Assert.assertEquals(driver.findElement(projectSearchedApprovalStatus).getText(), approvalStatusToVerify);
            Assert.assertEquals(driver.findElement(projectSearchedPriority).getText(), projectPriorityToVerify);
            ExtentLogger.pass(projectNameToVerify + " is present in the project list");
        } else {
            ExtentLogger.fail(projectNameToVerify + " project not found");
            Assert.fail(projectNameToVerify + " project not found");
        }
    }

    public void navigateToProject(String projectToNavigate) {
        WebElement projectSearched = CommonMethods.waitForElementClickable(projectSearchedName);
        String projectName = projectSearched.getText();
        if (projectName.equals(projectToNavigate)) {
            CommonMethods.wait(2000);
            CommonMethods.clickUsingJavascript(projectSearched);
            Assert.assertTrue(CommonMethods.waitForElementVisibility(projectLabel).getText().contains(projectToNavigate));
            ExtentLogger.pass("Navigated to " + projectToNavigate + " project");
        } else {
            ExtentLogger.fail(projectToNavigate + " project not found");
            Assert.fail(projectToNavigate + " project not found");
        }
    }

    public void clickCrossIconForProject(String projectNameToDelete) {
        WebElement projectSearched = CommonMethods.waitForElementClickable(projectSearchedName);
        String projectName = projectSearched.getText();
        if (projectName.equals(projectNameToDelete)) {
            CommonMethods.wait(1000);
            CommonMethods.waitForElementClickable(projectCrossIcon).click();
            wait.until(ExpectedConditions.alertIsPresent());
            ExtentLogger.pass("Clicked the cross icon for " + projectNameToDelete + " project");
        } else {
            ExtentLogger.fail(projectNameToDelete + " project not found");
            Assert.fail(projectNameToDelete + " project not found");
        }
    }

    public void verifyLatestProjectFromProjectList(String projectNameToVerify, String projectTypeToVerify, String approvalStatusToVerify, String projectPriorityToVerify) {
        CommonMethods.selectAllPaginationOption();
        WebElement projectSearched = CommonMethods.waitForElementClickable(latestProjectSearched);
        String projectName = projectSearched.getText();
        if (projectName.equals(projectNameToVerify)) {
            Assert.assertEquals(driver.findElement(latestProjectSearchedType).getText(), projectTypeToVerify);
            Assert.assertEquals(driver.findElement(latestProjectSearchedApprovalStatus).getText(), approvalStatusToVerify);
            Assert.assertEquals(driver.findElement(latestProjectSearchedPriority).getText(), projectPriorityToVerify);
            ExtentLogger.pass(projectNameToVerify + " is present in the inactive projects list");
        } else {
            ExtentLogger.fail(projectNameToVerify + " project not found in the inactive projects list");
            Assert.fail(projectNameToVerify + " project not found in the inactive projects list");
        }
    }

    public void verifyProjectProgressDetailsFromProjectsList(String projectNameToVerify, String assessment, String projectCompletedPercentage) {
        WebElement projectSearched = CommonMethods.waitForElementClickable(projectSearchedName);
        String projectName = projectSearched.getText();
        if (projectName.equals(projectNameToVerify)) {
            Assert.assertEquals(driver.findElement(projectSearchedAssessment).getText(), assessment);
            Assert.assertTrue(driver.findElement(projectSearchedCompletedPercentage).getText().contains(projectCompletedPercentage));
            ExtentLogger.pass(projectNameToVerify + " has assessment type " + assessment + " and is " + projectCompletedPercentage + " percent completed");
        } else {
            ExtentLogger.fail(projectNameToVerify + " project not found");
            Assert.fail(projectNameToVerify + " project not found");
        }
    }

    //    Common methods across projects module
    public void verifyProjectTabsAreDisabled() {
        List<WebElement> tabs = driver.findElements(projectTabs);
        for (int i = 2; i <= tabs.size(); i++) {
            String elementClass = driver.findElement(By.xpath(String.format(projectTabsClickStatusVerify, i))).getAttribute("class");
            Assert.assertTrue(elementClass.contains(disableClickClass));
        }
        ExtentLogger.pass("Project tabs are disabled before saving the project general details");
    }

    public void selectCurrentStatus(String currentStatusToChoose) {
        boolean status = false;
        WebElement currentStatusDropdown = CommonMethods.waitForElementClickable(currentStatusDropdownIcon);
        int listCount = driver.findElements(currentStatusList).size();
        CommonMethods.getActions().click(currentStatusDropdown).pause(Duration.ofSeconds(3)).perform();
        List<WebElement> currentStatuses = driver.findElements(By.xpath(String.format(currentStatusListItems, listCount)));
        for (WebElement ele : currentStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(currentStatusToChoose)) {
                CommonMethods.wait(1000);
                CommonMethods.clickUsingJavascript(ele);
                ExtentLogger.pass("Selected purchase current status as - " + currentStatusToChoose);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(currentStatusToChoose + " status not found");
            Assert.fail(currentStatusToChoose + " status not found");
        }
    }
}