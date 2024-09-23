package Tests.SprintTestTickets;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC06_VerifyGanttDetailsFromGanttSnapshot extends BaseClass {
    WebDriver incognitoDriver;
    WebDriverWait incognitoWait;

    String testProjectName = "test carga mpp";
    String projectStartDate;
    String projectEndDate;
    String projectCompletedPercentage;
    String firstTaskName;
    String firstTaskStartDate;
    String firstTaskEndDate;
    String firstTaskDuration;
    String ganttSnapshotLink;

    By ganttFirstTaskName = By.xpath("(//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')])[1]/div[contains(@class,'name-cell')]//div[@class='b-tree-cell-value']");
    By ganttFirstTaskStartDate = By.xpath("(//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')])[1]/div[contains(@class,'startdate-cell')]");
    By ganttFirstTaskEndDate = By.xpath("(//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')])[1]/div[contains(@class,'enddate-cell')]");
    By ganttFirstTaskDuration = By.xpath("(//div[@id='b-gantt-1-lockedSubgrid']/div[contains(@class,'b-grid-row')])[1]/div[contains(@class,'duration-cell')]");
    By ganttSnapshotProjectName = By.xpath("//div[@id='gantt-header']/h1");
    By ganttSnapshotProjectStartDate = By.xpath("//div[@id='gantt-header-line-2']/span[1]");
    By ganttSnapshotProjectEndDate = By.xpath("//div[@id='gantt-header-line-2']/span[2]");
    By ganttSnapshotProjectProgress = By.xpath("//div[@id='gantt-header-line-2']/span[3]/strong");

    // Task ID : T-70495-24070040
    @Test(groups = {"Sprint test case"})
    public void compareGanttDetailsFromGanttSnapshot() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        navigateToTestProject(testProjectName);
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        fetchProjectStartAndEndDateAndProjectCompletedPercentageFromGantt();
        fetchFirstProjectDetails();
        createGanttSnapshotLink();
        openGanttSnapshotLinkOnIncognitoWindow();
        verifyGanttDetailsFromGanttSnapshot();
    }

    @AfterMethod(groups = {"Sprint test case"})
    public void closeIncognitoBrowser() {
        incognitoDriver.close();
    }

    public void navigateToTestProject(String projectName) {
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(projectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().navigateToProject(projectName);

    }

    public void fetchProjectStartAndEndDateAndProjectCompletedPercentageFromGantt() {
        projectStartDate = CommonMethods.waitForElementVisibility(pageFactory.getProjectsGanttTab().projectStartDateDisplayed).getText();
        projectEndDate = CommonMethods.waitForElementVisibility(pageFactory.getProjectsGanttTab().projectEndDateDisplayed).getText();
        projectCompletedPercentage = CommonMethods.waitForElementVisibility(pageFactory.getProjectsGanttTab().projectCompletedPercentageDisplayed).getText();
        ExtentLogger.pass("Fetched the project start, end date and project completed percentage from the gantt tab");
    }

    public void fetchFirstProjectDetails() {
        firstTaskName = CommonMethods.waitForElementVisibility(ganttFirstTaskName).getText();
        firstTaskStartDate = CommonMethods.waitForElementVisibility(ganttFirstTaskStartDate).getText();
        firstTaskEndDate = CommonMethods.waitForElementVisibility(ganttFirstTaskEndDate).getText();
        firstTaskDuration = CommonMethods.waitForElementVisibility(ganttFirstTaskDuration).getText();
        ExtentLogger.pass("Fetched the first task details from the gantt tab");
    }

    public void createGanttSnapshotLink() {
        pageFactory.getProjectsGanttTab().clickShareGanttSnapshotShareIcon();
        pageFactory.getProjectsGanttTab().deleteLastGanttSnapshot();
        pageFactory.getProjectsGanttTab().clickGanttSharePopupDoneButton();
        pageFactory.getProjectsGanttTab().clickShareGanttSnapshotShareIcon();
        pageFactory.getProjectsGanttTab().createGanttSnapshot();
        ganttSnapshotLink = pageFactory.getProjectsGanttTab().copyGanttSnapshotLink();
        pageFactory.getProjectsGanttTab().clickGanttSharePopupDoneButton();
    }

    public void openGanttSnapshotLinkOnIncognitoWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        incognitoDriver = new ChromeDriver(options);
        incognitoDriver.get(ganttSnapshotLink);
        incognitoWait = new WebDriverWait(incognitoDriver, Duration.ofSeconds(60));
        ExtentLogger.pass("Opened gantt snapshot on incognito window");
    }

    public void verifyGanttDetailsFromGanttSnapshot() {
        Assert.assertTrue(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttSnapshotProjectName)).getText().contains(testProjectName));
        Assert.assertTrue(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttSnapshotProjectStartDate)).getText().contains(projectStartDate));
        Assert.assertTrue(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttSnapshotProjectEndDate)).getText().contains(projectEndDate));
        Assert.assertTrue(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttSnapshotProjectProgress)).getText().contains(projectCompletedPercentage));
        Assert.assertEquals(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttFirstTaskName)).getText(), firstTaskName);
        Assert.assertEquals(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttFirstTaskStartDate)).getText(), firstTaskStartDate);
        Assert.assertEquals(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttFirstTaskEndDate)).getText(), firstTaskEndDate);
        Assert.assertEquals(incognitoWait.until(ExpectedConditions.visibilityOfElementLocated(ganttFirstTaskDuration)).getText(), firstTaskDuration);
        ExtentLogger.pass("Gantt tab details match from the data displayed in the gantt snapshot");
    }
}