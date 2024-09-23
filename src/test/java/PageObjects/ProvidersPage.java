package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProvidersPage extends BaseClass {
    By addNewProviderButton = By.xpath("//a[contains(@id,'btnAddNewPROVIDER')]");
    By providerNameInputBox = By.xpath("//input[contains(@id,'txtProviderName')]");
    By paymentPeriodInputField = By.xpath("//input[contains(@id,'txtStandardPaymentClauses')]");
    By taxInputField = By.xpath("(//input[contains(@id,'txtTax')])[1]");
    By ratesTabButton = By.xpath("//a[contains(@id,'lnkRates')]");
    By providerEmailInputBox = By.xpath("//input[contains(@id,'txtEmail')]");
    By providerPhoneInputBox = By.xpath("//input[contains(@id,'txtPhone')]");
    By addNewCategoryButton = By.xpath("//a[contains(@id,'btnAddNewCategory')]");
    By providerCategoryStartDateInputField = By.xpath("(//input[contains(@id,'ucFromDate_dateInput')])[1]");
    By providerNameFilterInputBox = By.xpath("//input[contains(@id,'ProviderNameSearch')]");
    By providerSearchedVerify = By.xpath("//div[contains(@id,'grdProviderList')]/table/tbody/tr[1]/td[2]/a");
    By providerSearchedEmailVerify = By.xpath("//div[contains(@id,'grdProviderList')]/table/tbody/tr[1]/td[3]");
    By providerSearchedPhoneVerify = By.xpath("//div[contains(@id,'grdProviderList')]/table/tbody/tr[1]/td[4]");
    By providerSearchedCityVerify = By.xpath("//div[contains(@id,'grdProviderList')]/table/tbody/tr[1]/td[5]");
    By providerCityInputField = By.xpath("//input[contains(@id,'txtCity')]");

    public void clickAddNewProviderButton() {
        CommonMethods.waitForElementClickable(addNewProviderButton).click();
        CommonMethods.waitForElementClickable(providerNameInputBox);
        ExtentLogger.pass("Clicked the add new provider button");
    }

    public void enterProviderName(String providerName) {
        WebElement providerNameField = CommonMethods.waitForElementClickable(providerNameInputBox);
        CommonMethods.getActions().click(providerNameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(providerName).perform();
        ExtentLogger.pass("Entered provider name as - " + providerName);
    }

    public void enterProviderEmail(String providerEmail) {
        WebElement emailField = CommonMethods.waitForElementClickable(providerEmailInputBox);
        CommonMethods.getActions().click(emailField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(providerEmail).perform();
        ExtentLogger.pass("Entered provider email as - " + providerEmail);
    }

    public void enterProviderPhoneNumber(String providerPhoneNumber) {
        WebElement phoneNumberField = CommonMethods.waitForElementClickable(providerPhoneInputBox);
        CommonMethods.getActions().click(phoneNumberField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(providerPhoneNumber).perform();
        ExtentLogger.pass("Entered provider phone number as - " + providerPhoneNumber);
    }

    public void enterProviderPaymentPeriod(String paymentPeriod) {
        WebElement paymentPeriodField = CommonMethods.waitForElementClickable(paymentPeriodInputField);
        CommonMethods.getActions().click(paymentPeriodField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(paymentPeriod).perform();
        ExtentLogger.pass("Entered provider payment period as - " + paymentPeriod);
    }

    public void enterProviderTax(String tax) {
        WebElement taxField = CommonMethods.waitForElementClickable(taxInputField);
        CommonMethods.getActions().click(taxField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(tax).perform();
        ExtentLogger.pass("Entered provider tax as - " + tax);
    }

    public void saveProviderDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(providerNameInputBox);
        ExtentLogger.pass("Saved the provider details");
    }

    public void switchToRatesTab() {
        CommonMethods.waitForElementClickable(ratesTabButton).click();
        ExtentLogger.pass("Switched to the rates button");
    }

    public void clickProvidersAddNewCategoryButton() {
        CommonMethods.waitForElementClickable(addNewCategoryButton).click();
        ExtentLogger.pass("Clicked the provider add new category button");
    }

    public void enterProviderCategoryStartDate(String startDate) {
        CommonMethods.wait(1000);
        WebElement startDateField = CommonMethods.waitForElementClickable(providerCategoryStartDateInputField);
        CommonMethods.getActions().click(startDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(startDate).perform();
        ExtentLogger.pass("Entered provider category start date as - " + startDate);
    }

    public void filterProviderByName(String providerName) {
        WebElement nameFilter = CommonMethods.waitForElementClickable(providerNameFilterInputBox);
        CommonMethods.getActions().click(nameFilter).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(providerName).perform();
        CommonMethods.applyFilter();
        CommonMethods.waitForElementClickable(providerSearchedVerify);
        ExtentLogger.pass("Filtered the providers table to search for " + providerName + " provider");
    }

    public void enterProviderCity(String city) {
        WebElement cityField = CommonMethods.waitForElementClickable(providerCityInputField);
        CommonMethods.getActions().click(cityField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(city).perform();
        ExtentLogger.pass("Entered provider city as - " + city);
    }

    public void verifyProviderDetails(String providerName, String providerEmail, String phone, String city) {
        WebElement providerSearched = CommonMethods.waitForElementClickable(providerSearchedVerify);
        if (providerSearched.getText().equals(providerName)) {
            Assert.assertEquals(driver.findElement(providerSearchedEmailVerify).getText(), providerEmail);
            Assert.assertEquals(driver.findElement(providerSearchedPhoneVerify).getText(), phone);
            Assert.assertEquals(driver.findElement(providerSearchedCityVerify).getText(), city);
            ExtentLogger.pass(providerName + " provider is present in the providers list");
        } else {
            ExtentLogger.fail(providerName + " provider not found");
        }
    }

    public void verifyProviderCategoryStandardCostHistoryFromList(String startDate, String standardInputCost, String standardBillRate) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(CategoriesAndRatesPage.categoryTable);
        List<WebElement> standardCostHistory = driver.findElements(CategoriesAndRatesPage.categoryStandardCostHistoryList);
        for (WebElement ele : standardCostHistory) {
            count++;
            if (ele.getText().contains(startDate)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(CategoriesAndRatesPage.categoryStandardInputCostHistoryVerify, count))).getText(), standardInputCost);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(CategoriesAndRatesPage.categoryStandardBillRateHistoryVerify, count))).getText(), standardBillRate);
                ExtentLogger.pass("Provider category history with start date as " + startDate + " is present with " + standardInputCost + " input cost and " + standardBillRate + " bill rate");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Provider category history with start date " + startDate + " not found");
            Assert.fail("Provider category history with start date " + startDate + " not found");
        }
    }
}