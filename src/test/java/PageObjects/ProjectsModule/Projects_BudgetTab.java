package PageObjects.ProjectsModule;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Projects_BudgetTab {
    By projectBudgetTab = By.id("liProjectBudget");
    By budgetTable = By.xpath("//div[@class='page-content']//table[@class='main-table']");
    By bottomUpTotalWorkforceHour = By.xpath("//span[@id='btmUpTotalWorkForceHour']/span[1]");
    By bottomUpInternalTeamHour = By.xpath("//span[@id='btmUpInternalTeamHour']/span[1]");

    public void navigateToBudgetTab() {
        CommonMethods.waitForElementClickable(projectBudgetTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementVisibility(budgetTable);
        ExtentLogger.pass("Navigated to project budget tab");
    }

    public void verifyBottomUpTotalWorkforceHours(String workforceHours) {
        CommonMethods.waitForElementClickable(budgetTable);
        WebElement bottomUpWorkforceHours = CommonMethods.waitForElementVisibility(bottomUpTotalWorkforceHour);
        Assert.assertEquals(bottomUpWorkforceHours.getText(), workforceHours);
        ExtentLogger.pass("The bottom up total workforce hours is as expected - " + workforceHours + " hrs");
    }

    public void verifyBottomUpInternalTeamHours(String internalTeamHours) {
        CommonMethods.waitForElementClickable(budgetTable);
        WebElement bottomUpWorkforceHours = CommonMethods.waitForElementVisibility(bottomUpInternalTeamHour);
        Assert.assertEquals(bottomUpWorkforceHours.getText(), internalTeamHours);
        ExtentLogger.pass("The bottom up total workforce hours is as expected - " + internalTeamHours + " hrs");
    }
}