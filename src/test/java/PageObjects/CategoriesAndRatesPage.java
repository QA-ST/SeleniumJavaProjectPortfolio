package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CategoriesAndRatesPage extends BaseClass {
    By addNewCategoryButton = By.xpath("//a[contains(@id,'btnAddNewCategory')]");
    By categoryNameInputField = By.xpath("//input[contains(@id,'Category')]");
    By saveButton = By.xpath("//a[contains(@id,'Submit')]");
    static By categoryTable = By.xpath("//table[@class='rgMasterTable']/thead/tr/th[2]");
    By categoriesStandardTab = By.id("liGeneralC");
    By categoryTableList = By.xpath("//table[@class='rgMasterTable']/tbody/tr/td[2]/a");
    By categoryNameDropdown = By.xpath("//select[contains(@id,'drpCategory')]");
    By categoryCostStartDateInputField = By.xpath("(//input[contains(@id,'ucFromDate_dateInput')])[1]");
    static By categoryStandardCostHistoryList = By.xpath("//table[@class='rgMasterTable']/tbody/tr/td[2]");
    By employeeTypeCategoryTypeRadioButton = By.xpath("//table[contains(@id,'rdType')]//td[2]/input");
    By categoryTabButton = By.id("MainContent_liCategory");
    By categoryFrame = By.id("ifrmCategory");
    By allowNonBillingHoursCheckbox = By.xpath("//input[contains(@id,'AllowNonBilling')]");
    By globalStandardCostTabButton = By.xpath("//a[@id='btnGlobal']");
    By categoryInputCostInputField = By.xpath("(//input[contains(@id,'txtInternalCost_txtAmount')])[1]");
    By categoryStandardBillRateInputField = By.xpath("(//input[contains(@id,'txtChargedCost_txtAmount')])[1]");
    By standardBillRateCurrencyDropdownIcon = By.xpath("//div[contains(@id,'txtChargedCost_drpCurrency')]//td[contains(@class,'rcbArrowCell')]");
    By standardBillRateCurrencyList = By.xpath("//div[contains(@id,'txtChargedCost_drpCurrency')]//ul/li[1]");
    By standardBillRateCurrencyListItems = By.xpath("//div[contains(@id,'txtChargedCost_drpCurrency')]//ul/li//span");
    By categoryStandardBillRateCurrencySelected = By.xpath("//input[contains(@id,'txtChargedCost_drpCurrency_Input')]");

    String categoryDeleteIconCategoryTable = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[3]//a";
    static String categoryStandardInputCostHistoryVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[4]/span[1]";
    static String categoryStandardBillRateHistoryVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[5]/span[1]";
    String categoryEmployeeStandardInputCostVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[3]/span";
    String categoryGeneralStandardInputCostVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[4]/span";
    String categoryStandardBillRateVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[5]/span";
    String categoryInputCostHistoryCurrencyVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[4]/span[2]";
    String categoryStandardBillRateHistoryCurrencyVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[5]/span[2]";

    public void switchToCategoryFrame() {
        CommonMethods.switchToIframe(categoryFrame);
        CommonMethods.wait(1000);
    }

    public void clickAddNewCategoryButton() {
        CommonMethods.waitForElementClickable(addNewCategoryButton).click();
        CommonMethods.waitForElementVisibility(categoryNameInputField);
        ExtentLogger.pass("Clicked the add new category button");
    }

    public void enterCategoryName(String categoryName) {
        WebElement categoryNameField = CommonMethods.waitForElementVisibility(categoryNameInputField);
        categoryNameField.clear();
        categoryNameField.sendKeys(categoryName);
        ExtentLogger.pass("Entered the category name as - " + categoryName);
    }

    public void saveCategoryDetails() {
        CommonMethods.waitForElementClickable(saveButton).click();
        ExtentLogger.pass("Saved the category details");
    }

    public void switchToCategoriesStandardCostTab() {
        CommonMethods.waitForElementClickable(categoriesStandardTab).click();
        ExtentLogger.pass("Switched to categories standard tab");
    }

    public void verifyCategoryFromCategoryTable(String categoryToVerify) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            if (ele.getText().equals(categoryToVerify)) {
                ExtentLogger.pass(categoryToVerify + " category found in the category table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryToVerify + " category not found in the category table");
            Assert.fail(categoryToVerify + " category not found in the category table");
        }
    }

    public void verifyCategoryIsNotPresentInTheCategoryTable(String categoryToVerify) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            if (ele.getText().equals(categoryToVerify)) {
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass(categoryToVerify + " category not found in the category table");
        } else {
            ExtentLogger.fail(categoryToVerify + " category found in the category table");
            Assert.fail(categoryToVerify + " category found in the category table");
        }
    }

    public void clickCategoryDeleteIconFromCategoryTable(String categoryToDelete) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            count++;
            if (ele.getText().equals(categoryToDelete)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(categoryDeleteIconCategoryTable, count))).click();
                wait.until((ExpectedConditions.alertIsPresent()));
                ExtentLogger.pass("Clicked the " + categoryToDelete + " category delete icon from the category table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryToDelete + " category not found in the category table");
            Assert.fail(categoryToDelete + " category not found in the category table");
        }
    }

    public void navigateToCategoryFromCategoryTable(String categoryName) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            if (ele.getText().equals(categoryName)) {
                CommonMethods.waitForElementClickable(ele).click();
                ExtentLogger.pass("Navigated to " + categoryName + " category from the category table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryName + " category not found in the category table");
            Assert.fail(categoryName + " category not found in the category table");
        }
    }

    public void selectCategoryFromCategoryStandardCostDropdown(String categoryToSelect) {
        WebElement categoryDropdown = CommonMethods.waitForElementClickable(categoryNameDropdown);
        CommonMethods.getSelect(categoryDropdown).selectByVisibleText(categoryToSelect);
        ExtentLogger.pass("Selected category as - " + categoryToSelect);
    }

    public void enterStartDateForCategoryCost(String startDate) {
        WebElement startDateInputField = CommonMethods.waitForElementClickable(categoryCostStartDateInputField);
        startDateInputField.clear();
        startDateInputField.sendKeys(startDate);
        ExtentLogger.pass("Entered category standard cost date as - " + startDate);
    }

    public void verifyCategoryStandardCostHistoryFromList(String startDate, String standardInputCost, String inputCostCurrency, String standardBillRate, String standardBillRateCurrency) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> standardCostHistory = driver.findElements(categoryStandardCostHistoryList);
        for (WebElement ele : standardCostHistory) {
            count++;
            if (ele.getText().contains(startDate)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(categoryStandardInputCostHistoryVerify, count))).getText(), standardInputCost);
                Assert.assertTrue(driver.findElement(By.xpath(String.format(categoryInputCostHistoryCurrencyVerify, count))).getText().contains(inputCostCurrency));
                Assert.assertEquals(driver.findElement(By.xpath(String.format(categoryStandardBillRateHistoryVerify, count))).getText(), standardBillRate);
                Assert.assertTrue(driver.findElement(By.xpath(String.format(categoryStandardBillRateHistoryCurrencyVerify, count))).getText().contains(standardBillRateCurrency));
                ExtentLogger.pass("Category history with start date as " + startDate + " is present with " + standardInputCost + " input cost and " + standardBillRate + " bill rate");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Category history with start date " + startDate + " not found");
            Assert.fail("Category history with start date " + startDate + " not found");
        }
    }

    public void verifyCategoryCostDetailsForCategoryCostTable(String categoryToVerify, String employeeInputCost, String generalInputCost, String billRate) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            count++;
            if (ele.getText().equals(categoryToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(categoryEmployeeStandardInputCostVerify, count))).getText().contains(employeeInputCost));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(categoryGeneralStandardInputCostVerify, count))).getText().contains(generalInputCost));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(categoryStandardBillRateVerify, count))).getText().contains(billRate));
                ExtentLogger.pass(categoryToVerify + " category found in the category standard cost table table with employee input cost : " + employeeInputCost + " , general input cost : " + generalInputCost + " and standard bill rate : " + billRate);
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryToVerify + " category not found in the category table");
            Assert.fail(categoryToVerify + " category not found in the category table");
        }
    }

    public void selectEmployeeCategoryType() {
        WebElement employeeRadioButton = CommonMethods.waitForElementClickable(employeeTypeCategoryTypeRadioButton);
        CommonMethods.getActions().click(employeeRadioButton).pause(Duration.ofSeconds(1)).perform();
        ExtentLogger.pass("Selected employee category type radio button");
    }

    public void switchToCategoryTab() {
        CommonMethods.waitForElementClickable(categoryTabButton).click();
        ExtentLogger.pass("Switched to category tab");
    }

    public String[] fetchCategoryCostFromCategoryCostTable(String categoryToFetch) {
        boolean status = false;
        int count = 0;
        String employeeInputCost = null;
        String standardInputCost = null;
        String standardBillRate = null;
        CommonMethods.waitForElementVisibility(categoryTable);
        List<WebElement> categoriesList = driver.findElements(categoryTableList);
        for (WebElement ele : categoriesList) {
            count++;
            if (ele.getText().equals(categoryToFetch)) {
                employeeInputCost = driver.findElement(By.xpath(String.format(categoryEmployeeStandardInputCostVerify, count))).getText();
                standardInputCost = driver.findElement(By.xpath(String.format(categoryGeneralStandardInputCostVerify, count))).getText();
                standardBillRate = driver.findElement(By.xpath(String.format(categoryStandardBillRateVerify, count))).getText();
                ExtentLogger.pass("Fetched the category input and output cost for " + categoryToFetch + " category from the category cost table");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryToFetch + " category not found in the category table");
            Assert.fail(categoryToFetch + " category not found in the category table");
        }
        return new String[]{employeeInputCost, standardInputCost, standardBillRate};
    }

    public void checkAllowNonBillingHoursForCategory() {
        WebElement nonBillingHoursCheckbox = CommonMethods.waitForElementClickable(allowNonBillingHoursCheckbox);
        if (nonBillingHoursCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the allow non billing hours checkbox for category");
        } else {
            nonBillingHoursCheckbox.click();
            ExtentLogger.pass("Checked the allow non billing hours checkbox for category");
        }
    }

    public void switchToGlobalStandardCostTab() {
        CommonMethods.waitForElementClickable(globalStandardCostTabButton).click();
        ExtentLogger.pass("Switched to global standard cost");
    }

    public void navigateToGlobalCategoryType(String globalType) {
        boolean status = false;
        CommonMethods.waitForElementClickable(categoryTable);
        List<WebElement> globalTypes = driver.findElements(categoryTableList);
        for (WebElement ele : globalTypes) {
            if (ele.getText().equals(globalType)) {
                ele.click();
                ExtentLogger.pass("Navigated to " + globalType + " global type");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(globalType + " global type not found");
            Assert.fail(globalType + " global type not found");
        }
    }

    public void enterCategoryInputCost(String inputCost) {
        WebElement costField = CommonMethods.waitForElementClickable(categoryInputCostInputField);
        CommonMethods.getActions().click(costField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(inputCost).perform();
        ExtentLogger.pass("Entered the category input cost as - " + inputCost);
    }

    public void enterCategoryStandardBillRate(String standardBillRate) {
        WebElement costField = CommonMethods.waitForElementClickable(categoryStandardBillRateInputField);
        costField.clear();
        CommonMethods.typeUsingJavascript(costField, standardBillRate);
        ExtentLogger.pass("Entered the category standard bill rate as - " + standardBillRate);
    }

    public void selectStandardBillRateCurrency(String currencyToSelect) {
        boolean status = false;
        CommonMethods.waitForElementClickable(standardBillRateCurrencyDropdownIcon).click();
        CommonMethods.wait((1000));
        try {
            waitForElement.until(ExpectedConditions.elementToBeClickable(standardBillRateCurrencyList));
        } catch (Exception ex) {
            CommonMethods.waitForElementClickable(standardBillRateCurrencyDropdownIcon).click();
            CommonMethods.waitForElementClickable(standardBillRateCurrencyList);
        }
        List<WebElement> currencies = driver.findElements(standardBillRateCurrencyListItems);
        for (WebElement ele : currencies) {
            if (ele.getText().contains(currencyToSelect)) {
                ele.click();
                CommonMethods.wait(1000);
                Assert.assertTrue(CommonMethods.waitForElementClickable(categoryStandardBillRateCurrencySelected).getAttribute("value").contains(currencyToSelect));
                ExtentLogger.pass("Selected " + currencyToSelect + " currency for category standard bill rate");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(currencyToSelect + " currency not found");
            Assert.fail(currencyToSelect + " currency not found");
        }
    }
}