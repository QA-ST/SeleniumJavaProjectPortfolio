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

public class Projects_RevenueTab extends BaseClass {
    By projectRevenueTab = By.id("liProjectRevenueAndRecognition");
    By addNewRevenueButton = By.id("drpMenuAddRevenue");
    By newRevenueOption = By.id("liAddNewRevenue");
    By revenueNameInputField = By.id("txtRevenueName");
    By revenueProjectedAmountInputField = By.xpath("//div[@id='txtProjectedAmount']/input[1]");
    By revenueTable = By.xpath("(//div[@id='projectrevenues']//following::table)[1]/tbody");
    By revenueList = By.xpath("(//div[@id='projectrevenues']//following::table)[1]/tbody/tr/td[2]/a");
    By revenueActualAmountInputField = By.xpath("//div[@id='txtActualAmount']/input[1]");
    By bottomUpRevenueAmountVerify = By.id("lblBottomUpTotalRevenue");
    By revenueActualAmountVerify = By.id("lblAVTotalRevenue");

    String revenueStatusVerify = "(//div[@id='projectrevenues']//following::table)[1]/tbody/tr[%s]/td[3]";
    String revenueStatusDateVerify = "(//div[@id='projectrevenues']//following::table)[1]/tbody/tr[%s]/td[5]";
    String revenueRevenueProjectedAmount = "(//div[@id='projectrevenues']//following::table)[1]/tbody/tr[%s]/td[7]/span";
    String revenueActualAmountVerifyFromList = "(//div[@id='projectrevenues']//following::table)[1]/tbody/tr[%s]/td[9]/span";
    String revenueDeleteIcon = "(//div[@id='projectrevenues']//following::table)[1]/tbody/tr[%s]/td/a[@id='imgDeleteProjectRevenue']";

    public void navigateToRevenueTab() {
        CommonMethods.waitForElementClickable(projectRevenueTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementClickable(addNewRevenueButton);
        CommonMethods.wait(1000);
        ExtentLogger.pass("Navigated to revenue tab");
    }

    public void selectAddNewRevenueOption() {
        WebElement revenueDropdown = CommonMethods.waitForElementClickable(addNewRevenueButton);
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).click(revenueDropdown).pause(Duration.ofSeconds(1)).perform();
        CommonMethods.waitForElementClickable(newRevenueOption).click();
        CommonMethods.waitForElementVisibility(revenueNameInputField);
        ExtentLogger.pass("Selected the add new revenue option");
    }

    public void enterRevenueName(String revenueName) {
        CommonMethods.waitForElementVisibility(revenueNameInputField).clear();
        driver.findElement(revenueNameInputField).sendKeys(revenueName);
        ExtentLogger.pass("Entered revenue name as - " + revenueName);
    }

    public void enterRevenueProjectedAmount(String amount) {
        CommonMethods.waitForElementVisibility(revenueProjectedAmountInputField).clear();
        driver.findElement(revenueProjectedAmountInputField).sendKeys(amount);
        ExtentLogger.pass("Entered revenue projected amount as - " + amount);
    }

    public void saveRevenueDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementVisibility(revenueTable);
        ExtentLogger.pass("Saved the revenue details");
    }

    public void verifyRevenueFromTable(String revenueNameToVerify, String revenueStatus, String statusDate, String revenueProjectedAmount, String revenueActualAmount) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(revenueTable);
        CommonMethods.selectAllPaginationOption();
        List<WebElement> revenuesPresent = driver.findElements(revenueList);
        for (WebElement ele : revenuesPresent) {
            count++;
            if (ele.getText().equals(revenueNameToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(revenueStatusVerify, count))).getText(), revenueStatus);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(revenueStatusDateVerify, count))).getText(), statusDate);
                Assert.assertTrue(driver.findElement(By.xpath(String.format(revenueRevenueProjectedAmount, count))).getText().contains(revenueProjectedAmount));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(revenueActualAmountVerifyFromList, count))).getText().contains(revenueActualAmount));
                ExtentLogger.pass(revenueNameToVerify + " revenue is present in the revenue list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass(revenueNameToVerify + " revenue not found");
            Assert.fail(revenueNameToVerify + " revenue not found");
        }
    }

    public void navigateToRevenue(String revenueStatusToNavigate) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(revenueTable);
        CommonMethods.selectAllPaginationOption();
        List<WebElement> revenuesPresent = driver.findElements(revenueList);
        for (WebElement ele : revenuesPresent) {
            if (ele.getText().equals(revenueStatusToNavigate)) {
                ele.click();
                CommonMethods.waitForPageLoader();
                CommonMethods.waitForElementVisibility(revenueNameInputField);
                CommonMethods.wait(2000);
                ExtentLogger.pass("Navigated to " + revenueStatusToNavigate + " revenue");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass(revenueStatusToNavigate + " revenue not found");
            Assert.fail(revenueStatusToNavigate + " revenue not found");
        }
    }

    public void verifyActualAmountFromRevenueDetailsPage(String actualAmount) {
        WebElement revenueActualAmountVerify = CommonMethods.waitForElementVisibility(revenueActualAmountInputField);
        CommonMethods.wait(1000);
        Assert.assertEquals(revenueActualAmountVerify.getAttribute("value"), actualAmount);
        ExtentLogger.pass("Revenue actual amount is as expected - " + actualAmount);
    }

    public void verifyRevenueBottomUpAndActualAmount(String revenueBottomUpAmount, String actualAmount) {
        WebElement bottomUpPrice = CommonMethods.waitForElementVisibility(bottomUpRevenueAmountVerify);
        Assert.assertEquals(bottomUpPrice.getText(), revenueBottomUpAmount);
        WebElement revenueActualAmount = CommonMethods.waitForElementVisibility(revenueActualAmountVerify);
        Assert.assertEquals(revenueActualAmount.getText(), actualAmount);
        ExtentLogger.pass("Revenue bottom up amount - " + revenueBottomUpAmount + " and revenue actual amount - " + actualAmount);
    }

    public void clickRevenueDeleteIcon(String revenueToDelete) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(revenueTable);
        CommonMethods.selectAllPaginationOption();
        List<WebElement> revenuesPresent = driver.findElements(revenueList);
        for (WebElement ele : revenuesPresent) {
            count++;
            if (ele.getText().equals(revenueToDelete)) {
                WebElement deleteIcon = CommonMethods.waitForElementClickable(By.xpath(String.format(revenueDeleteIcon, count)));
                deleteIcon.click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the delete icon for " + revenueToDelete + " revenue");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(revenueToDelete + " revenue delete icon not clicked");
            Assert.fail(revenueToDelete + " revenue delete icon not clicked");
        }
    }
}