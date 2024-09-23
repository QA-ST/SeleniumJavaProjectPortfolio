package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class OrganizationUnitsPage extends BaseClass {
    By addNewOrganizationUnitButton = By.xpath("//a[contains(@id,'btnAddNewDepartment')] ");
    By organizationUnitNameInputField = By.xpath("//input[contains(@id,'txtDepartmentName')]");
    By organizationUnitCodeInputField = By.xpath("//input[contains(@id,'txtDepartmentCode')]");
    By organizationUnitTable = By.xpath("//div[contains(@id,'grdDepartmentList')]/table/tbody/tr[1]/td[1]");
    By organizationUnitNameSearched = By.xpath("//div[contains(@id,'grdDepartmentList')]/table/tbody/tr[1]/td[2]/a");
    By organizationUnitSearchedCodeVerify = By.xpath("//div[contains(@id,'grdDepartmentList')]/table/tbody/tr[1]/td[3]/span");
    By organizationUnitSearchedCreatedDateVerify = By.xpath("//div[contains(@id,'grdDepartmentList')]/table/tbody/tr[1]/td[5]");
    By organizationUnitNameFilterInputField = By.xpath("//input[contains(@id,'txtDepartmentSearch')]");

    public void clickAddNewOrganizationUnitButton() {
        CommonMethods.waitForElementClickable(addNewOrganizationUnitButton).click();
        CommonMethods.waitForElementClickable(organizationUnitNameInputField);
        ExtentLogger.pass("Clicked the add new organization unit button");
    }

    public void enterOrganizationUnitName(String organizationUnitName) {
        WebElement nameInputField = CommonMethods.waitForElementClickable(organizationUnitNameInputField);
        CommonMethods.getActions().click(nameInputField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(organizationUnitName).perform();
        ExtentLogger.pass("Entered the organization unit name as - " + organizationUnitName);
    }

    public void enterOrganizationUnitCode(String organizationUnitCode) {
        WebElement codeInputField = CommonMethods.waitForElementClickable(organizationUnitCodeInputField);
        CommonMethods.getActions().click(codeInputField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(organizationUnitCode).perform();
        ExtentLogger.pass("Entered the organization unit code as - " + organizationUnitCode);
    }

    public void saveOrganizationUnitDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(organizationUnitTable);
        ExtentLogger.pass("Saved the organization unit details");
    }

    public void filterOrganizationUnitList(String unitToSearch) {
        WebElement organizationUnitNameFilter = CommonMethods.waitForElementClickable(organizationUnitNameFilterInputField);
        CommonMethods.getActions().click(organizationUnitNameFilter).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(unitToSearch).perform();
        CommonMethods.applyFilter();
        CommonMethods.waitForElementClickable(organizationUnitTable);
        ExtentLogger.pass("Filtered the organization unit list by name filter");
    }

    public void verifyOrganizationUnitFromList(String unitNameToVerify, String unitCode, String dateToVerify) {
        CommonMethods.waitForElementClickable(organizationUnitTable);
        CommonMethods.wait(1000);
        if (CommonMethods.waitForElementClickable(organizationUnitNameSearched).getText().equals(unitNameToVerify)) {
            Assert.assertEquals(driver.findElement(organizationUnitSearchedCodeVerify).getText(), unitCode);
            Assert.assertTrue(driver.findElement(organizationUnitSearchedCreatedDateVerify).getText().contains(dateToVerify));
            ExtentLogger.pass(unitNameToVerify + " organization unit is present in the list");
        } else {
            ExtentLogger.fail(unitNameToVerify + " organization unit not found");
            Assert.fail(unitNameToVerify + " organization unit not found");
        }
    }
}