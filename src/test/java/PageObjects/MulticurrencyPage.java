package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MulticurrencyPage extends BaseClass {
    By addNewCurrencyButton = By.xpath("//a[contains(@id,'btnAdd')]");
    By selectCurrencyDropdown = By.xpath("//select[contains(@id,'drpCurrency')]");
    By currencyTable = By.xpath("//div[contains(@id,'grdCurrency')]//table/tbody/tr[1]/td[1]");
    By currencyList = By.xpath("//div[contains(@id,'grdCurrency')]//table/tbody/tr/td[2]/a");
    By exchangeRateTabButton = By.id("btnExchange");
    By addNewExchangeRateButton = By.xpath("//a[contains(@id,'btnAddNewExchange')]");
    By selectLocalCurrencyDropdown = By.xpath("//select[contains(@id,'drpLocalCurrency')]");
    By exchangeRateInputBox = By.xpath("(//input[contains(@id,'txtExchangeValue')])[1]");
    By exchangeStartDateInputField = By.xpath("(//input[contains(@id,'StartDate_dateInput')])[1]");
    By exchangeRateTable = By.xpath("(//div[contains(@id,'grdExchange')]/table)[1]/tbody/tr[1]/td[1]");
    By exchangeRateList = By.xpath("(//div[contains(@id,'grdExchange')]/table)[1]/tbody/tr/td[2]/a");
    By exchangeRateHistoryTable = By.xpath("(//div[contains(@id,'grdER')]/table)[1]/tbody/tr[1]/td[1]");
    By exchangeRateHistoryListByStartDate = By.xpath("(//div[contains(@id,'grdER')]/table)[1]/tbody/tr/td[5]");
    By currencyFrame = By.xpath("//iframe[@id='ifrmProjectPortfolio']");

    String currencySymbolVerify = "//div[contains(@id,'grdCurrency')]//table/tbody/tr[%s]/td[3]/span";
    String currencyExchangeRateVerify = "(//div[contains(@id,'grdExchange')]/table)[1]/tbody/tr[%s]/td[4]/span";
    String currencyExchangeRateHistoryVerify = "(//div[contains(@id,'grdER')]/table)[1]/tbody/tr[%s]/td[4]/span";

    public void switchToMulticurrencyIframe() {
        CommonMethods.switchToIframe(currencyFrame);
        CommonMethods.wait(1000);
    }

    public void clickAddNewCurrencyButton() {
        CommonMethods.waitForElementClickable(addNewCurrencyButton).click();
        CommonMethods.waitForElementClickable(selectCurrencyDropdown);
        ExtentLogger.pass("Clicked the add new currency button");
    }

    public void selectCurrency(String currencyToSelect) {
        WebElement currencyDropdown = CommonMethods.waitForElementClickable(selectCurrencyDropdown);
        CommonMethods.getSelect(currencyDropdown).selectByVisibleText(currencyToSelect);
        ExtentLogger.pass("Selected currency as -" + currencyToSelect);
    }

    public void saveCurrencyDetails() {
        CommonMethods.saveDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        ExtentLogger.pass("Saved the currency details");
    }

    public void verifyCurrencyFromList(String currencyToVerify, String currencySymbol) {
        boolean status = false;
        int count = 0;
        CommonMethods.selectMaximumTablePagination(addNewCurrencyButton);
        CommonMethods.waitForElementClickable(currencyTable);
        List<WebElement> currencies = driver.findElements(currencyList);
        for (WebElement ele : currencies) {
            count++;
            if (ele.getText().contains(currencyToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(currencySymbolVerify, count))).getText().contains(currencySymbol));
                ExtentLogger.pass(currencyToVerify + " currency is present in the list");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail(currencyToVerify + " currency not found");
            Assert.fail(currencyToVerify + " currency not found");
        }
    }

    public void switchToExchangeRateTab() {
        CommonMethods.waitForElementClickable(exchangeRateTabButton).click();
        ExtentLogger.pass("Switched to exchange rate tab");
    }

    public void clickAddNewExchangeRateButton() {
        CommonMethods.waitForElementClickable(addNewExchangeRateButton).click();
        CommonMethods.waitForElementClickable(selectLocalCurrencyDropdown);
        ExtentLogger.pass("Clicked the add new exchange rate button");
    }

    public void selectLocalCurrency(String currency) {
        WebElement localCurrencyDropdown =  CommonMethods.waitForElementClickable(selectLocalCurrencyDropdown);
        CommonMethods.getSelect(localCurrencyDropdown).selectByVisibleText(currency);
        ExtentLogger.pass("Selected local currency as - " + currency);
    }

    public void enterExchangeRate(String exchangeRate) {
        WebElement exchangeRateField = CommonMethods.waitForElementClickable(exchangeRateInputBox);
        CommonMethods.getActions().click(exchangeRateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(exchangeRate).perform();
        ExtentLogger.pass("Entered exchange rate as - " + exchangeRate);
    }

    public void enterExchangeRateStartDate(String date) {
        WebElement startDateField = CommonMethods.waitForElementClickable(exchangeStartDateInputField);
        CommonMethods.getActions().click(startDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(date).perform();
        ExtentLogger.pass("Entered exchange rate start date as - " + date);
    }

    public void saveExchangeRateDetails() {
        CommonMethods.saveDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        ExtentLogger.pass("Saved the exchange rate details");
    }

    public void verifyExchangeRateDetails(String currencyToVerify, String exchangeRateVerify) {
        boolean status = false;
        int count = 0;
        CommonMethods.selectMaximumTablePagination(addNewExchangeRateButton);
        CommonMethods.waitForElementClickable(exchangeRateTable);
        List<WebElement> exchangeRates = driver.findElements(exchangeRateList);
        for (WebElement ele : exchangeRates) {
            count++;
            if (ele.getText().equals(currencyToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(currencyExchangeRateVerify, count))).getText(), exchangeRateVerify);
                ExtentLogger.pass(currencyToVerify + " currency is present in the list with " + exchangeRateVerify + " exchange rate");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(currencyToVerify + " currency with " + exchangeRateVerify + " exchange rate not found");
            Assert.fail(currencyToVerify + " currency with " + exchangeRateVerify + " exchange rate not found");
        }
    }

    public void navigateToCurrencyExchangeRate(String currencyToView) {
        boolean status = false;
        int count = 0;
        CommonMethods.selectMaximumTablePagination(addNewExchangeRateButton);
        CommonMethods.waitForElementClickable(exchangeRateTable);
        List<WebElement> exchangeRates = driver.findElements(exchangeRateList);
        for (WebElement ele : exchangeRates) {
            count++;
            if (ele.getText().equals(currencyToView)) {
                ele.click();
                CommonMethods.waitForElementClickable(selectLocalCurrencyDropdown);
                ExtentLogger.pass("Navigated to " + currencyToView + " currency exchange rate details page");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(currencyToView + " currency with not found");
            Assert.fail(currencyToView + " currency with not found");
        }
    }

    public void verifyExchangeRateHistory(String startDateVerify, String exchangeRate) {
        boolean status = false;
        int count = 0;
        CommonMethods.selectMaximumTablePagination(selectLocalCurrencyDropdown);
        CommonMethods.waitForElementClickable(exchangeRateHistoryTable);
        List<WebElement> exchangeRateHistoryDate = driver.findElements(exchangeRateHistoryListByStartDate);
        for (WebElement ele : exchangeRateHistoryDate) {
            count++;
            if (ele.getText().contains(startDateVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(currencyExchangeRateHistoryVerify, count))).getText(), exchangeRate);
                ExtentLogger.pass(exchangeRate + " exchange rate is applied to the currency starting from : " + startDateVerify);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(exchangeRate + " exchange rate for currency not found starting from : " + startDateVerify);
            Assert.fail(exchangeRate + " exchange rate for currency not found starting from : " + startDateVerify);
        }
    }
}