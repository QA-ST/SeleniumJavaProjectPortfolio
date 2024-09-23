package Helpers;

import ExtentReporter.ExtentLogger;
import Utils.Config.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CommonMethods extends BaseClass {
    static Select select;

    static By pageLoader = By.xpath("(//div[@id='divLoader' and contains(@style,'display: block')])[1]");
    public static By hiddenPageLoader = By.xpath("(//div[@id='divLoader' and contains(@style,'display: none')])[1]");
    static By saveButton = By.xpath("(//button[@tkey='Save'])[1]");
    static By filterApplyButton = By.xpath("(//button[@tkey='Apply'])[1]");
    static By paginationDropdown = By.id("drpPageSize");
    static By viewMoreFilterOptions = By.xpath("//a[@class='more-search-option']");
    static By filterResetButton = By.xpath("//button[@id='btnReset']");
    static By resetButton = By.xpath("//a[contains(@id,'btnReset')]");
    static By successValidationMessageSidePopup = By.xpath("//div[contains(@class,'success-msg') and contains(@style,'display: block')]");
    static By errorValidationMessageSidePopup = By.xpath("//div[contains(@class,'error-msg') and contains(@style,'display: block')]");
    static By validationMessageCloseIcon = By.xpath("//div[@id='divShowMessage']//following::a[@class='closeMessage']");
    static By emptyTableVerify = By.xpath("//div[@id='divgrdNoRecords']");
    static By validationMessageDisplayVerify = By.xpath("//div[@id='divShowMessage']");
    static By newAccountUnderstandingStepsRightArrowKey = By.xpath("//div[@id='fancyhelp-steps']//div[contains(@style,'float: right;')]");
    static By totalUnderstandingStepsCount = By.xpath("//div[@class='fancyhelp-step']");
    static By understandingStepText = By.xpath("//div[@class='fancyhelp-text']");
    static By saveButton2 = By.xpath("//a[contains(@id,'Submit')]");
    static By applyFilterButton = By.xpath("//a[contains(@id,'btnApplyFilter')]");
    static By paginationDropdownArrow = By.xpath("//td[contains(@class,'rcbArrowCellRight')]");
    static By paginationSetFiftyOption = By.xpath("//ul[@class='rcbList']/li[text()='50']");

    public static String allPaginationOption = "All";
    public static String displayBlockVerify = "block";
    public static String displayNoneVerify = "none";
    public static String displayFlexVerify = "flex";
    public static String dirtyClass = "dirty";

    public static WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisibility(WebElement ele) {
        return wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebElement ele) {
        return wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public static Actions getActions() {
        return action;
    }

    public static Select getSelect(WebElement ele) {
        return select = new Select(ele);
    }

    public static void clickUsingJavascript(WebElement ele) {
        jsExecutor.executeScript("arguments[0].click();", ele);
    }

    public static void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        ExtentLogger.pass("Accepted the alert");
    }

    public static void waitForPageLoader() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(pageLoader));
    }

    public static void wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void switchToIframe(By locator) {
        try {
            WebElement iframeElement = driver.findElement(locator);
            driver.switchTo().frame(iframeElement);
        } catch (Exception e) {
            Assert.fail("Failed to switch to the iframe: " + e.getMessage());
        }
    }

    public static void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            wait(2000);
        } catch (Exception e) {
            Assert.fail("Failed to switch to the default content: " + e.getMessage());
        }
    }

    public static void clickSaveButton() {
        waitForElementClickable(saveButton).click();
    }

    public static void clickFilterApplyButton() {
        waitForElementClickable(filterApplyButton).click();
        waitForPageLoader();
        wait(1000);
        ExtentLogger.pass("Clicked the filter apply button");
    }

    public static void selectAllPaginationOption() {
        WebElement ele = waitForElementClickable(paginationDropdown);
        wait(1000);
        getSelect(ele).selectByVisibleText(allPaginationOption);
        waitForPageLoader();
        wait(1000);
    }

    public static void clickViewMoreFilterOption() {
        WebElement ele = CommonMethods.waitForElementClickable(viewMoreFilterOptions);
        CommonMethods.getActions().click(ele).pause(Duration.ofSeconds(1)).perform();
    }

    public static void waitForSuccessMessageSidePopup() {
        WebElement validationMessage = waitForElementVisibility(successValidationMessageSidePopup);
        if (validationMessage.getCssValue("display").equals(displayBlockVerify)) {
            WebElement ele = waitForElementClickable(validationMessageCloseIcon);
            getActions().pause(Duration.ofSeconds(1)).click(ele).perform();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successValidationMessageSidePopup));
            ExtentLogger.pass("Success validation message side popup appeared and closed the popup");
        } else {
            ExtentLogger.fail("Success validation message side popup did not appear");
            Assert.fail("Success validation message side popup did not appear");
        }
    }

    public static void clickFilterResetButton() {
        waitForElementClickable(filterResetButton).click();
        waitForPageLoader();
        ExtentLogger.pass("Clicked the filter reset button");
    }

    public static void verifyNoRecordPresent() {
        WebElement emptyTable = wait.until(ExpectedConditions.presenceOfElementLocated(emptyTableVerify));
        wait(2000);
        String displayValue = emptyTable.getCssValue("display");
        if (displayValue.equals(displayBlockVerify)) {
            ExtentLogger.pass("No record found in the list");
        } else {
            ExtentLogger.pass("Record found in the list");
            Assert.fail("Record found in the list");
        }
    }

    public static void verifyValidationMessageIsNotDisplayed() {
        WebElement validationMessagePopup = wait.until(ExpectedConditions.presenceOfElementLocated(validationMessageDisplayVerify));
        String displayValue = validationMessagePopup.getCssValue("display");
        if (displayValue.equals(displayNoneVerify)) {
            ExtentLogger.pass("Validation message side popup does not appear");
        } else {
            ExtentLogger.fail("Validation message side popup appears");
            Assert.fail("Validation message side popup appears");
        }
    }

    public static void verifyErrorValidationMessageIsDisplayed() {
        WebElement validationMessagePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(errorValidationMessageSidePopup));
        String displayValue = validationMessagePopup.getCssValue("display");
        if (displayValue.equals(displayBlockVerify)) {
            WebElement ele = waitForElementClickable(validationMessageCloseIcon);
            getActions().pause(Duration.ofSeconds(1)).click(ele).perform();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successValidationMessageSidePopup));
            Assert.assertEquals(validationMessagePopup.getCssValue("display"), displayNoneVerify);
            ExtentLogger.pass("Error validation message side popup appears");
        } else {
            ExtentLogger.fail("Error validation message side popup does not appear");
            Assert.fail("Error validation message side popup does not appear");
        }
    }

    public static void waitForErrorMessageSidePopup() {
        WebElement validationMessage = waitForElementVisibility(errorValidationMessageSidePopup);
        if (validationMessage.getCssValue("display").equals(displayBlockVerify)) {
            WebElement ele = waitForElementClickable(validationMessageCloseIcon);
            getActions().pause(Duration.ofSeconds(1)).click(ele).perform();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(errorValidationMessageSidePopup));
            ExtentLogger.pass("Error validation message side popup appeared and closed the popup");
        } else {
            ExtentLogger.fail("Error validation message side popup did not appear");
            Assert.fail("Error validation message side popup did not appear");
        }
    }

    public static void typeUsingJavascript(WebElement ele, String value) {
        jsExecutor.executeScript(String.format("arguments[0].value='%s';", value), ele);
    }

    public static void clickResetButton() {
        CommonMethods.waitForElementClickable(resetButton).click();
        ExtentLogger.pass("Clicked the filter reset button");
    }

    public static void navigateThroughTheNewAccountUnderstandingSteps() {
//        WebElement rightArrowKey = CommonMethods.waitForElementClickable(newAccountUnderstandingStepsRightArrowKey);
//        CommonMethods.wait(1000);
//        List<WebElement> steps = driver.findElements(totalUnderstandingStepsCount);
//        for (int i = 1; i < steps.size(); i++) {
//            rightArrowKey.click();
//        }
        CommonMethods.wait(40000);
        CommonMethods.clickUsingJavascript(CommonMethods.waitForElementClickable(understandingStepText));
        CommonMethods.waitForElementClickable(understandingStepText).click();
        ExtentLogger.pass("Navigated through the new account understanding steps");
    }

    public static void saveDetails() {
        CommonMethods.waitForElementClickable(saveButton2).click();
    }

    public static void applyFilter() {
        CommonMethods.waitForElementClickable(applyFilterButton).click();
    }

    public static void selectMaximumTablePagination(By locatorToWaitFor) {
        try {
            waitForElement.until(ExpectedConditions.elementToBeClickable(paginationDropdownArrow)).click();
            CommonMethods.waitForElementClickable(paginationSetFiftyOption).click();
            CommonMethods.waitForElementClickable(locatorToWaitFor);
        } catch (Exception ex) {
            CommonMethods.waitForElementClickable(locatorToWaitFor);
        }
    }
}