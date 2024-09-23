package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CustomFieldsPage extends BaseClass {
    By selectEntityDropdown = By.xpath("//select[contains(@id,'drpPageList')]");
    By addNewFieldButton = By.xpath("//a[contains(@id,'btnAddNewField')]");
    By customFieldNameInputBox = By.xpath("//input[contains(@id,'txtName')]");
    By entityTypeVerify = By.xpath("//div[contains(text(),'Entity:')]//following::div[1]");
    By enableEntityDropdownIcon = By.xpath("//a[contains(@id,'drpTypes_Arrow')]");
    By enableEntityAllCheckbox = By.xpath("//input[@class='rcbCheckAllItemsCheckBox']");
    By enableEntityTypeDropdown = By.xpath("//div[contains(@id,'drpTypes_DropDown')]");
    By enableEntityTypeOptions = By.xpath("//ul[@class='rcbList']/li/label");
    By customFieldTypeDropdown = By.xpath("//select[contains(@id,'drpFieldType')]");
    By customFieldDisplayCheckbox = By.xpath("//input[@id='MainContent_chkDisplay']");
    By customFieldDisplayToTeamMembersCheckbox = By.xpath("//input[contains(@id,'chkDisplayForTM')]");
    By customFieldTable = By.xpath("//div[@id='tblFields']/table/tbody/tr[1]/td[1]");
    By customFieldsNameList = By.xpath("//div[@id='tblFields']/table/tbody/tr/td[2]/a");

    String enableEntityTypeCheckbox = "(//ul[@class='rcbList']/li/label)[%s]/input";
    String customFieldTypeVerify = "//div[@id='tblFields']/table/tbody/tr[%s]/td[3]";

    public void selectEntityForCustomField(String entityToSelect) {
        WebElement entityDropdown = CommonMethods.waitForElementClickable(selectEntityDropdown);
        CommonMethods.getSelect(entityDropdown).selectByVisibleText(entityToSelect);
        CommonMethods.waitForElementClickable(addNewFieldButton);
        ExtentLogger.pass("Selected custom field entity as - " + entityToSelect);
    }

    public void clickAddNewFieldButton(String entityType) {
        CommonMethods.waitForElementClickable(addNewFieldButton).click();
        CommonMethods.waitForElementClickable(customFieldNameInputBox);
        Assert.assertTrue(CommonMethods.waitForElementVisibility(entityTypeVerify).getText().contains(entityType));
        ExtentLogger.pass("Clicked the add new custom field button");
    }

    public void enableTypeOnEntity(String typeToSelect) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(customFieldNameInputBox);
        CommonMethods.waitForElementClickable(enableEntityDropdownIcon).click();
        CommonMethods.wait(1000);
        try {
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(enableEntityTypeDropdown));
        } catch (Exception ex) {
            CommonMethods.waitForElementClickable(enableEntityDropdownIcon).click();
            CommonMethods.waitForElementVisibility(enableEntityTypeDropdown);
        }
        CommonMethods.waitForElementClickable(enableEntityTypeDropdown);
        if (CommonMethods.waitForElementClickable(enableEntityAllCheckbox).isSelected()) {
            driver.findElement(enableEntityAllCheckbox).click();
        }
        List<WebElement> typeOptions = driver.findElements(enableEntityTypeOptions);
        for (WebElement ele : typeOptions) {
            count++;
            if (ele.getText().equals(typeToSelect)) {
                WebElement checkbox = CommonMethods.waitForElementClickable(By.xpath(String.format(enableEntityTypeCheckbox, count)));
                checkbox.click();
                if (checkbox.isSelected()) {
                    CommonMethods.waitForElementClickable(enableEntityDropdownIcon).click();
                    ExtentLogger.pass(typeToSelect + " type selected on entity");
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            ExtentLogger.pass(typeToSelect + " type not selected on entity");
            Assert.fail(typeToSelect + " type not selected on entity");
        }
    }

    public void enterCustomFieldName(String customFieldName) {
        WebElement nameInput = CommonMethods.waitForElementClickable(customFieldNameInputBox);
        CommonMethods.wait(2000);
        CommonMethods.getActions().click(nameInput).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(customFieldName).perform();
        ExtentLogger.pass("Entered the custom field name as - " + customFieldName);
    }

    public void selectCustomFieldType(String fieldType) {
        WebElement fieldTypeDropdown = CommonMethods.waitForElementClickable(customFieldTypeDropdown);
        CommonMethods.getSelect(fieldTypeDropdown).selectByVisibleText(fieldType);
        ExtentLogger.pass("Selected " + fieldType + " field type");
    }

    public void checkCustomFieldDisplayCheckbox() {
        WebElement displayCheckbox = CommonMethods.waitForElementClickable(customFieldDisplayCheckbox);
        if (displayCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the custom field display checkbox");
        } else {
            displayCheckbox.click();
            ExtentLogger.pass("Checked the custom field display checkbox");
        }
    }

    public void checkCustomFieldDisplayToTeamMembersCheckbox() {
        WebElement displayCheckbox = CommonMethods.waitForElementClickable(customFieldDisplayToTeamMembersCheckbox);
        if (displayCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the custom field display to team members checkbox");
        } else {
            displayCheckbox.click();
            ExtentLogger.pass("Checked the custom field display to team members checkbox");
        }
    }

    public void saveCustomFieldsDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewFieldButton);
        CommonMethods.waitForElementClickable(customFieldTable);
        ExtentLogger.pass("Saved the custom field details");
    }

    public void verifyCustomFieldDetails(String fieldName, String fieldType) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(customFieldTable);
        List<WebElement> customField = driver.findElements(customFieldsNameList);
        for (WebElement ele : customField) {
            count++;
            if (ele.getText().equals(fieldName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(customFieldTypeVerify, count))).getText(), fieldType);
                status = true;
                ExtentLogger.pass(fieldName + " custom field is present in the list");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(fieldName + " custom field not found");
            Assert.fail(fieldName + " custom field not found");
        }
    }
}