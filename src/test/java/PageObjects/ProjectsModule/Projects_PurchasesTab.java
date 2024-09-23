package PageObjects.ProjectsModule;

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

public class Projects_PurchasesTab extends BaseClass {
    By projectPurchasesTab = By.id("liProjectPurchases");
    By addBudgetAccountButton = By.id("btnAddBudget");
    By budgetAccountNameInputField = By.id("txtBudgetAccountName");
    By budgetAccountAmountInputField = By.xpath("//div[@id='txtCostPrice']/input[1]");
    By budgetAccountTable = By.xpath("(//div[@id='divGrdProjectCost']//table)[2]/tbody");
    By budgetAccountsNameList = By.xpath("(//div[@id='divGrdProjectCost']//table)[2]/tbody/tr/td[1]/a");
    By budgetAccountPaginationDropdown = By.id("drpPageSizeBudget");
    By addNewPurchaseButton = By.id("drpMenuAddPurchase");
    By addNewPurchaseOption = By.id("liAddNewPurchase");
    By purchaseNameInputField = By.id("txtTaskName");
    By selectPurchaseBudgetAccountDropdown = By.id("drpBudget");
    By purchaseStatusDateInputField = By.id("txtStatusDate");
    By totalBudgetAmountVerify = By.xpath("//label[@tkey='Total Budget:']//following::label[1]");
    By availableBudgetVerify = By.xpath("//label/span[@tkey='Available Budget:']//following::label[1]");
    By purchaseProjectedAmountInputField = By.xpath("//div[@id='txtProjectedAmount']/input[1]");
    By purchaseSaveButton = By.xpath("//button[@id='btnSave']");
    By disabledPurchaseSaveButton = By.xpath("//button[@id='btnSave' and contains(@class,'disabled')]");
    By purchaseTable = By.xpath("(//div[@id='projectpurchases']//table)[2]/tbody");
    By purchasesNameList = By.xpath("(//div[@id='projectpurchases']//table)[2]/tbody/tr/td[2]/a");
    By purchaseTablePaginationDropdown = By.id("drpPageSizePurchases");
    By purchaseActualAmountInputField = By.xpath("//div[@id='txtActualAmount']/input[1]");
    By emptyBudgetTableVerify = By.xpath("//div[@id='divgrdNoRecordsBudget']");

    String budgetAccountAmountVerify = "(//div[@id='divGrdProjectCost']//table)[2]/tbody/tr[%s]/td[2]/span";
    String budgetProjectedAmountVerify = "(//div[@id='divGrdProjectCost']//table)[2]/tbody/tr[%s]/td[3]/span";
    String budgetActualAmountVerify = "(//div[@id='divGrdProjectCost']//table)[2]/tbody/tr[%s]/td[4]/span[1]";
    String purchaseStatusVerify = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td[3]";
    String purchaseStatusDateVerify = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td[4]";
    String purchaseBudgetAccountVerify = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td[5]";
    String purchaseProjectedAmountVerify = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td[7]/span";
    String purchaseActualAmountVerify = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td[13]/span";
    String purchaseDeleteIcon = "(//div[@id='projectpurchases']//table)[2]/tbody/tr[%s]/td/a[@id='imgDeleteTaskPurchase']";
    String budgetAccountDeleteIcon = "(//div[@id='divGrdProjectCost']//table)[2]/tbody/tr[%s]/td/img[@class='imgDelete']";

    public void navigateToPurchasesTab() {
        CommonMethods.getActions().moveToElement(CommonMethods.waitForElementClickable(projectPurchasesTab)).click().perform();
        CommonMethods.waitForPageLoader();
        CommonMethods.wait(1000);
        CommonMethods.waitForElementClickable(addBudgetAccountButton);
        ExtentLogger.pass("Navigated to project purchases tab");
    }

    public void clickAddBudgetAccountButton() {
        CommonMethods.waitForElementClickable(addBudgetAccountButton).click();
        CommonMethods.waitForElementVisibility(budgetAccountNameInputField);
        ExtentLogger.pass("Clicked the add budget account button");
    }

    public void enterBudgetAccountName(String budgetAccountName) {
        CommonMethods.waitForElementVisibility(budgetAccountNameInputField).clear();
        driver.findElement(budgetAccountNameInputField).sendKeys(budgetAccountName);
        ExtentLogger.pass("Entered budget account name as - " + budgetAccountName);
    }

    public void enterBudgetAccountAmount(String budgetAmount) {
        CommonMethods.waitForElementVisibility(budgetAccountAmountInputField).clear();
        driver.findElement(budgetAccountAmountInputField).sendKeys(budgetAmount);
        ExtentLogger.pass("Entered budget account amount as - " + budgetAmount);
    }

    public void saveBudgetAccountDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementVisibility(budgetAccountTable);
        ExtentLogger.pass("Save the budget account details");
    }

    public void verifyBudgetAccountFromBudgetsList(String budgetAccountToVerify, String budgetAccountAmount, String projectedAmount, String actualAmount) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(budgetAccountTable);
        WebElement ele = CommonMethods.waitForElementClickable(budgetAccountPaginationDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(2000);
        List<WebElement> budgetAccounts = driver.findElements(budgetAccountsNameList);
        for (WebElement accountName : budgetAccounts) {
            count++;
            String accountNameText = accountName.getText();
            if (accountNameText.equals(budgetAccountToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(budgetAccountAmountVerify, count))).getText().contains(budgetAccountAmount));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(budgetProjectedAmountVerify, count))).getText().contains(projectedAmount));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(budgetActualAmountVerify, count))).getText().contains(actualAmount));
                status = true;
                ExtentLogger.pass(budgetAccountToVerify + " budget account is present in the list with " + budgetAccountAmount + " amount");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(budgetAccountToVerify + " budget account not found");
        }
    }

    public void selectAddNewPurchaseOption() {
        CommonMethods.waitForElementClickable(addNewPurchaseButton).click();
        CommonMethods.waitForElementClickable(addNewPurchaseOption);
        CommonMethods.getActions().pause(Duration.ofSeconds(1)).click(driver.findElement(addNewPurchaseOption)).perform();
        CommonMethods.waitForElementVisibility(purchaseNameInputField);
        ExtentLogger.pass("Clicked the add new purchase option");
    }

    public void enterPurchaseName(String purchaseName) {
        CommonMethods.waitForElementVisibility(purchaseNameInputField).clear();
        driver.findElement(purchaseNameInputField).sendKeys(purchaseName);
        ExtentLogger.pass("Entered purchase name as - " + purchaseName);
    }

    public void selectBudgetAccountForPurchase(String budgetAccountToSelect) {
        WebElement budgetAccountDropdown = CommonMethods.waitForElementClickable(selectPurchaseBudgetAccountDropdown);
        CommonMethods.wait(1000);
        CommonMethods.getSelect(budgetAccountDropdown).selectByVisibleText(budgetAccountToSelect);
        CommonMethods.wait(1000);
        ExtentLogger.pass("Selected " + budgetAccountToSelect + " budget account for the purchase");
    }

    public void verifyPurchaseTotalAndAvailableBudget(String totalBudget, String availableBudget) {
        Assert.assertEquals(CommonMethods.waitForElementClickable(totalBudgetAmountVerify).getText(), totalBudget);
        Assert.assertEquals(CommonMethods.waitForElementClickable(availableBudgetVerify).getText(), availableBudget);
        ExtentLogger.pass("Purchase total budget - " + totalBudget + " and purchase available budget - " + availableBudget);
    }

    public void enterPurchaseStatusDate(String date) {
        WebElement statusDateInput = CommonMethods.waitForElementClickable(purchaseStatusDateInputField);
        CommonMethods.wait(1000);
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(statusDateInput)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).pause(Duration.ofSeconds(1)).sendKeys(date + Keys.ENTER).perform();
        ExtentLogger.pass("Entered purchase status date as - " + date);
    }

    public void enterPurchaseProjectedAmount(String projectedAmount) {
        CommonMethods.waitForElementClickable(purchaseProjectedAmountInputField).clear();
        driver.findElement(purchaseProjectedAmountInputField).sendKeys(projectedAmount);
        ExtentLogger.pass("Entered purchase projected amount as - " + projectedAmount);
    }

    public void savePurchaseDetails() {
        CommonMethods.getActions().click(driver.findElement(purchaseSaveButton)).pause(Duration.ofSeconds(2)).perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(disabledPurchaseSaveButton));
        CommonMethods.waitForElementClickable(purchaseSaveButton);
        CommonMethods.wait(1000);
        ExtentLogger.pass("Saved the purchase details");
    }

    public void verifyPurchaseFromPurchaseList(String purchaseNameToVerify, String purchaseStatus, String purchaseDate, String budgetAccount, String projectedAmount, String actualAmount) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(purchaseTable);
        WebElement ele = CommonMethods.waitForElementClickable(purchaseTablePaginationDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(1000);
        List<WebElement> purchasesList = driver.findElements(purchasesNameList);
        for (WebElement purchaseName : purchasesList) {
            count++;
            if (purchaseName.getText().equals(purchaseNameToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(purchaseStatusVerify, count))).getText().contains(purchaseStatus));
                Assert.assertEquals(driver.findElement(By.xpath(String.format(purchaseStatusDateVerify, count))).getText(), purchaseDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(purchaseBudgetAccountVerify, count))).getText(), budgetAccount);
                Assert.assertTrue(driver.findElement(By.xpath(String.format(purchaseProjectedAmountVerify, count))).getText().contains(projectedAmount));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(purchaseActualAmountVerify, count))).getText().contains(actualAmount));
                status = true;
                ExtentLogger.pass(purchaseNameToVerify + " purchase is present in the purchases list");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(purchaseNameToVerify + " purchase not found in the purchases list");
            Assert.fail(purchaseNameToVerify + " purchase not found in the purchases list");
        }
    }

    public void navigateToPurchase(String purchaseToNavigate) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(purchaseTable);
        WebElement ele = CommonMethods.waitForElementClickable(purchaseTablePaginationDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(1000);
        List<WebElement> purchasesList = driver.findElements(purchasesNameList);
        for (WebElement purchaseName : purchasesList) {
            if (purchaseName.getText().equals(purchaseToNavigate)) {
                CommonMethods.getActions().click(purchaseName).pause(Duration.ofSeconds(3)).perform();
                CommonMethods.waitForPageLoader();
                status = true;
                ExtentLogger.pass("Navigated to " + purchaseToNavigate + " purchase");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(purchaseToNavigate + " purchase not found in the purchases list");
            Assert.fail(purchaseToNavigate + " purchase not found in the purchases list");
        }
    }

    public void verifyPurchaseActualAmount(String amountToVerify) {
        WebElement purchaseActualAmount = CommonMethods.waitForElementVisibility(purchaseActualAmountInputField);
        String actualAmount = purchaseActualAmount.getAttribute("value");
        Assert.assertEquals(actualAmount, amountToVerify);
        ExtentLogger.pass("Actual amount is reflected as - " + amountToVerify);
    }

    public void clickDeleteIconForThePurchase(String purchaseToDelete) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(purchaseTable);
        WebElement ele = CommonMethods.waitForElementClickable(purchaseTablePaginationDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(1000);
        List<WebElement> purchasesList = driver.findElements(purchasesNameList);
        for (WebElement purchaseName : purchasesList) {
            count++;
            if (purchaseName.getText().equals(purchaseToDelete)) {
                WebElement deleteIcon = driver.findElement(By.xpath(String.format(purchaseDeleteIcon, count)));
                deleteIcon.click();
                ExtentLogger.pass("Clicked the delete icon for " + purchaseToDelete + " purchase");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Delete icon for " + purchaseToDelete + " purchase not clicked");
            Assert.fail("Delete icon for " + purchaseToDelete + " purchase not clicked");
        }
    }

    public void clickDeleteIconForBudgetAccount(String budgetAccountToDelete) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(budgetAccountTable);
        WebElement ele = CommonMethods.waitForElementClickable(budgetAccountPaginationDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(2000);
        List<WebElement> budgetAccounts = driver.findElements(budgetAccountsNameList);
        for (WebElement accountName : budgetAccounts) {
            count++;
            String accountNameText = accountName.getText();
            if (accountNameText.equals(budgetAccountToDelete)) {
                WebElement deleteIcon = driver.findElement(By.xpath(String.format(budgetAccountDeleteIcon, count)));
                deleteIcon.click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the delete icon for " + budgetAccountToDelete + " budget account");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Delete icon for " + budgetAccountToDelete + " budget not clicked ");
            Assert.fail("Delete icon for " + budgetAccountToDelete + " budget not clicked ");
        }
    }

    public void verifyBudgetTableHasNoRecord() {
        WebElement emptyBudgetTable = wait.until(ExpectedConditions.presenceOfElementLocated(emptyBudgetTableVerify));
        CommonMethods.wait(1000);
        String displayValue = emptyBudgetTable.getCssValue("display");
        if (displayValue.equals("block")) {
            ExtentLogger.pass("No record found in the list");
        } else {
            ExtentLogger.pass("Record found in the list");
            Assert.fail("Record found in the list");
        }
    }
}