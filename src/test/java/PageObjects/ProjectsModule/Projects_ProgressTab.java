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

public class Projects_ProgressTab extends BaseClass {
    By projectsProgressTab = By.id("liProjectProgress");
    By addProgressReportButton = By.xpath("//itm-button[@title='Add a project report']");
    By progressAssessmentDropdownIcon = By.xpath("//span[@tkey='Assessment:']//following::span[@class='k-select'][1]");
    By progressAssessmentList = By.xpath("//ul[@id='drpAssessment_listbox']");
    By progressShortDescriptionInputField = By.id("txtShortDescription");
    By progressPercentageCompletedInputField = By.id("txtFollowUpPercentCompleted");
    By lastProgressReportVerify = By.id("lblLastFollowUp");
    By progressAssessmentVerify = By.id("lblAssessment");
    By projectProgressMenuIcon = By.xpath("//itm-ellipsis-menu[contains(@id,'ellipsis')]");
    By projectProgressReportTable = By.xpath("//div[@id='divProjectFollowUpGrid']//div[@class='k-grid-header']//table//tr/th[1]");
    By progressDescriptionList = By.xpath("//div[@id='projectfollowups']/div/table//tr/td[2]/a");
    By projectProgressReportModalCloseButton = By.xpath("//div[@id='divProjectFollowUpGrid']//preceding::button[contains(@class,'close')]");

    String progressAssessmentListItems = "(//ul[@id='drpAssessment_listbox'])[%s]/li/span[2]";
    String viewProjectProgressHistoryButton = ".menu-item[title=\"View progress report history\"]";
    String progressPercentageVerify = "//div[@id='projectfollowups']/div/table//tr[%s]/td[3]/span";
    String progressAssessmentTypeVerify = "//div[@id='projectfollowups']/div/table//tr[%s]/td[4]";
    String progressReportDateVerify = "//div[@id='projectfollowups']/div/table//tr[%s]/td[5]";
    String created = "//div[@id='projectfollowups']/div/table//tr[%s]/td[6]";
    String progressDeleteIcon = "//div[@id='projectfollowups']/div/table//tr[%s]/td/a[@id='imgDeleteProject']";

    public void navigateToProjectProgressTab() {
        CommonMethods.waitForElementClickable(projectsProgressTab).click();
        CommonMethods.waitForPageLoader();
        ExtentLogger.pass("Navigated to projects progress tab");
    }

    public void clickAddProgressReportButton() {
        CommonMethods.waitForElementClickable(addProgressReportButton).click();
        CommonMethods.waitForElementClickable(progressAssessmentDropdownIcon);
        ExtentLogger.pass("Clicked the add progress report button");
    }

    public void selectProgressAssessmentType(String assessmentToSelect) {
        boolean status = false;
        WebElement currentStatusDropdown = CommonMethods.waitForElementClickable(progressAssessmentDropdownIcon);
        int listCount = driver.findElements(progressAssessmentList).size();
        CommonMethods.getActions().click(currentStatusDropdown).pause(Duration.ofSeconds(1)).perform();
        List<WebElement> currentStatuses = driver.findElements(By.xpath(String.format(progressAssessmentListItems, listCount)));
        for (WebElement ele : currentStatuses) {
            String eleValue = ele.getText();
            if (eleValue != null && eleValue.equals(assessmentToSelect)) {
                CommonMethods.wait(1000);
                CommonMethods.clickUsingJavascript(ele);
                ExtentLogger.pass("Selected progress assessment as - " + assessmentToSelect);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(assessmentToSelect + " assessment not found");
            Assert.fail(assessmentToSelect + " assessment not found");
        }
    }

    public void enterProgressShortDescription(String shortDescription) {
        WebElement shortDescriptionField = CommonMethods.waitForElementVisibility(progressShortDescriptionInputField);
        shortDescriptionField.clear();
        shortDescriptionField.sendKeys(shortDescription);
        ExtentLogger.pass("Entered progress short description as - " + shortDescription);
    }

    public void enterProgressPercentageCompleted(String percentageCompleted) {
        WebElement percentageCompletedField = CommonMethods.waitForElementVisibility(progressPercentageCompletedInputField);
        percentageCompletedField.clear();
        percentageCompletedField.sendKeys(percentageCompleted);
        ExtentLogger.pass("Entered progress completed percentage as - " + percentageCompleted + " percent");
    }

    public void saveProjectProgressReport() {
        CommonMethods.clickSaveButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(progressAssessmentDropdownIcon));
        CommonMethods.waitForElementClickable(addProgressReportButton);
        ExtentLogger.pass("Saved the progress report");
    }

    public void verifyLastProgressReport(String progressShortDescription, String date, String progressAssessment) {
        String lastProgressVerify = CommonMethods.waitForElementVisibility(lastProgressReportVerify).getText();
        Assert.assertTrue(lastProgressVerify.contains(progressShortDescription));
        Assert.assertTrue(lastProgressVerify.contains(date));
        Assert.assertEquals(CommonMethods.waitForElementVisibility(progressAssessmentVerify).getText(), progressAssessment);
    }

    public void openProjectProgressReport() {
        CommonMethods.waitForElementClickable(projectProgressMenuIcon);
        WebElement viewProgressHistoryButtonContainer = driver.findElement(projectProgressMenuIcon);
        viewProgressHistoryButtonContainer.click();

        WebElement viewProgressHistoryButton = (WebElement) jsExecutor.executeScript(
                "return arguments[0].shadowRoot.querySelector(arguments[1]);",
                viewProgressHistoryButtonContainer, viewProjectProgressHistoryButton
        );
        CommonMethods.getActions().pause(Duration.ofSeconds(2)).click(viewProgressHistoryButton).perform();
        CommonMethods.waitForElementVisibility(projectProgressReportTable);
        ExtentLogger.pass("Clicked the progress report menu icon");
    }

    public void verifyProgressFromProgressList(String progressDescriptionToVerify, String percentage, String assessment, String reportDate, String createdBy) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(projectProgressReportTable);
        List<WebElement> progressList = driver.findElements(progressDescriptionList);
        for (WebElement progressDescription : progressList) {
            if (progressDescription.getText().equals(progressDescriptionToVerify)) {
                count++;
                Assert.assertTrue(driver.findElement(By.xpath(String.format(progressPercentageVerify, count))).getText().contains(percentage));
                Assert.assertEquals(driver.findElement(By.xpath(String.format(progressAssessmentTypeVerify, count))).getText(), assessment);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(progressReportDateVerify, count))).getText(), reportDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(created, count))).getText(), createdBy);
                status = true;
                ExtentLogger.pass(progressDescriptionToVerify + " progress is present in the progress report list");
            }
        }
        if (!status) {
            ExtentLogger.fail(progressDescriptionToVerify + " progress not found in the progress report list");
            Assert.fail(progressDescriptionToVerify + " progress not found in the progress report list");
        }
    }

    public void clickDeleteIconForProgress(String progressDescriptionToVerify) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(projectProgressReportTable);
        List<WebElement> progressList = driver.findElements(progressDescriptionList);
        for (WebElement progressDescription : progressList) {
            count++;
            if (progressDescription.getText().equals(progressDescriptionToVerify)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(progressDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                status = true;
                ExtentLogger.pass("Clicked the delete icon for " + progressDescriptionToVerify + " progress");
            }
        }
        if (!status) {
            ExtentLogger.fail(progressDescriptionToVerify + " progress not found in the progress report list");
            Assert.fail(progressDescriptionToVerify + " progress not found in the progress report list");
        }
    }

    public void closeProjectProgressMenu() {
        CommonMethods.waitForElementClickable(projectProgressReportModalCloseButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(projectProgressReportTable));
        CommonMethods.waitForElementClickable(addProgressReportButton);
        ExtentLogger.pass("Clicked the cross icon for the project progress report menu");
    }
}