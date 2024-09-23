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

public class ProjectParametersPage extends BaseClass {
    By projectStatusTabButton = By.xpath("//li[contains(@id,'liStatus')]");
    By addNewStatusButton = By.xpath("//a[contains(@id,'btnAddNewStatus')]");
    By projectStatusNameInputField = By.xpath("//input[contains(@id,'txtStatusName')]");
    By projectStatusEnableTimeReportingCheckbox = By.xpath("//input[contains(@id,'chkAllowTimeEntry')]");
    By projectStatusSaveButton = By.xpath("//a[contains(@id,'btnStatusSubmit')]");
    static By projectParameterPaginationSelectDropdown = By.xpath("//td[contains(@class,'rcbArrowCellRight')]");
    static By projectParameterTablePaginationSetFiftyOption = By.xpath("//ul[@class='rcbList']/li[text()='50']");
    By projectParameterTable = By.xpath("//div[@id='divProjectStatusGrid']//table/tbody/tr[1]/td[1]");
    By projectStatusNamesList = By.xpath("//div[@id='divProjectStatusGrid']//table[@class='rgMasterTable']/tbody/tr/td/a[contains(@id,'lnkResourceName')]");
    By destinationStatusDropdownExpandIcon = By.xpath("//div[contains(@id,'DestinationStatusList')]/table//td[contains(@class,'rcbArrowCell')]/a");
    By destinationStatusList = By.xpath("//div[@class='rcbSlide' and contains(@style,'display: block;')]");
    By destinationDropdownListItems = By.xpath("//div[contains(@id,'DestinationStatusList_DropDown')]//ul/li//label");
    By projectTypeTabButton = By.id("MainContent_liType");
    By addNewProjectTypeButton = By.xpath("//a[contains(@id,'btnAddNewType')]");
    By enableRevenueForProjectTypeCheckbox = By.xpath("//input[contains(@id,'chkEnableRevenue')]");
    By allowNonBillingHoursForProjectTypeCheckbox = By.xpath("//input[contains(@id,'chkNonBilling')]");
    By projectTypeNameInputField = By.xpath("//input[contains(@id,'txtTypeName')]");
    By projectTypeDescriptionInputField = By.xpath("//input[contains(@id,'txtTypeDesc')]");
    By projectTypeNamesList = By.xpath("//table[@class='rgMasterTable']/tbody/tr/td/a[contains(@id,'lnkResourceName')]");
    By projectTypeTable = By.xpath("//table[@class='rgMasterTable']/tbody/tr[1]/td[1]");
    By projectTypeSaveButton = By.xpath("//a[contains(@id,'btnTypeSubmit')]");
    By addNewProjectPriorityButton = By.xpath("//a[contains(@id,'btnAddNewPriority')]");
    By projectPriorityNameInputBox = By.xpath("//input[contains(@id,'txPrioritytName')]");
    By projectPriorityDescriptionInputBox = By.xpath("//input[contains(@id,'txtPriorityDesc')]");
    By projectPriorityTable = By.xpath("//div[contains(@id,'grdPriority')]/table/tbody/tr[1]/td[1]");
    By projectPriorityNameList = By.xpath("//div[contains(@id,'grdPriority')]/table/tbody/tr/td[2]/a");
    By projectApprovalTab = By.xpath("//a[contains(@id,'lnkApproval')]");
    By addNewApprovalButton = By.xpath("//a[contains(@id,'btnAddNewApproval')]");
    By projectApprovalNameInputBox = By.xpath("//input[contains(@id,'txtApprovalName')]");
    By projectApprovalDescriptionInputBox = By.xpath("//input[contains(@id,'txtDescription')]");
    By projectApprovalTable = By.xpath("//div[contains(@id,'grdApproval')]/table/tbody/tr[1]/td[1]");
    By projectApprovalNameList = By.xpath("//div[contains(@id,'grdApproval')]/table/tbody/tr/td[2]/a");

    String projectStatusDestinationStatusVerify = "//div[@id='divProjectStatusGrid']//table[@class='rgMasterTable']/tbody/tr[%s]/td[3]";
    String destinationStatusCheckbox = "(//div[contains(@id,'DestinationStatusList_DropDown')]//ul/li/label)[%s]/input";
    String projectTypeDescriptionVerify = "//table[@class='rgMasterTable']/tbody/tr[%s]/td[3]";
    String projectStatusDeleteIcon = "//div[@id='divProjectStatusGrid']//table[@class='rgMasterTable']/tbody/tr[%s]/td//a[@title='Delete']";
    String projectTypeDeleteIcon = "//table[@class='rgMasterTable']/tbody/tr[%s]/td//a[@title='Delete']";
    String projectPriorityDescriptionVerify = "//div[contains(@id,'grdPriority')]/table/tbody/tr[%s]/td[3]";
    String projectPriorityDefaultRadioButton = "//div[contains(@id,'grdPriority')]/table/tbody/tr[%s]/td[5]//input[contains(@id,'chkPrioritySelected')]";
    String projectApprovalDescriptionVerify = "//div[contains(@id,'grdApproval')]/table/tbody/tr[%s]/td[3]";

    public void switchToProjectStatus() {
        CommonMethods.waitForElementClickable(projectStatusTabButton).click();
        CommonMethods.waitForElementClickable(addNewStatusButton);
        ExtentLogger.pass("Switched to project status tab");
    }

    public void clickAddNewStatusButton() {
        CommonMethods.waitForElementClickable(addNewStatusButton).click();
        CommonMethods.waitForElementClickable(projectStatusNameInputField);
        ExtentLogger.pass("Clicked the add new status button");
    }

    public void enterProjectStatusName(String projectStatusName) {
        WebElement projectStatusField = CommonMethods.waitForElementClickable(projectStatusNameInputField);
        CommonMethods.getActions().click(projectStatusField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectStatusName).perform();
        ExtentLogger.pass("Entered project status name as - " + projectStatusName);
    }

    public void checkEnableTimeReportingForProjectStatus() {
        WebElement enableTimeReportingCheckbox = CommonMethods.waitForElementClickable(projectStatusEnableTimeReportingCheckbox);
        if (enableTimeReportingCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the enable time reporting checkbox for project status");
        } else {
            enableTimeReportingCheckbox.click();
            ExtentLogger.pass("Checked the enable time reporting checkbox for project status");
        }
    }

    public void saveProjectStatusDetails() {
        CommonMethods.waitForElementClickable(projectStatusSaveButton).click();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewStatusButton);
        ExtentLogger.pass("Saved the project status details");
    }

    public static void selectMaximumPaginationForParameterTable(By locatorToWaitFor) {
        CommonMethods.waitForElementClickable(projectParameterPaginationSelectDropdown).click();
        CommonMethods.waitForElementClickable(projectParameterTablePaginationSetFiftyOption).click();
        CommonMethods.waitForElementClickable(locatorToWaitFor);
    }

    public void selectMaximumPaginationForProjectTypeTable() {
        selectMaximumPaginationForParameterTable(addNewProjectTypeButton);
    }

    public void selectMaximumPaginationForProjectStatusTable() {
        selectMaximumPaginationForParameterTable(addNewStatusButton);
    }

    public void verifyProjectStatusFromList(String projectStatus) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(projectParameterTable);
        List<WebElement> projectStatusList = driver.findElements(projectStatusNamesList);
        for (WebElement ele : projectStatusList) {
            if (ele.getText().equals(projectStatus)) {
                ExtentLogger.pass(projectStatus + " project status is present in the project status list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectStatus + " project status not found");
            Assert.fail(projectStatus + " project status not found");
        }
    }

    public void verifyDestinationStatusForProjectStatusFromList(String projectStatus, String destinationStatusToVerify) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(projectParameterTable);
        List<WebElement> projectStatusList = driver.findElements(projectStatusNamesList);
        for (WebElement ele : projectStatusList) {
            count++;
            if (ele.getText().equals(projectStatus)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(projectStatusDestinationStatusVerify, count))).getText().contains(destinationStatusToVerify));
                ExtentLogger.pass(projectStatus + " project status has " + destinationStatusToVerify + "destination status added");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectStatus + " project status not found");
            Assert.fail(projectStatus + " project status not found");
        }
    }

    public void selectDestinationStatusForProjectStatus(String destinationStatusToSelect) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(destinationStatusDropdownExpandIcon).click();
        CommonMethods.waitForElementVisibility(destinationStatusList);
        CommonMethods.wait(1000);
        List<WebElement> destinationStatuses = driver.findElements(destinationDropdownListItems);
        for (WebElement ele : destinationStatuses) {
            count++;
            if (ele.getText().equals(destinationStatusToSelect)) {
                CommonMethods.waitForElementClickable(driver.findElement(By.xpath(String.format(destinationStatusCheckbox, count)))).click();
                CommonMethods.waitForElementClickable(destinationStatusDropdownExpandIcon).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(destinationStatusList));
                ExtentLogger.pass("Selected " + destinationStatusToSelect + " project status as destination status");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(destinationStatusToSelect + " project status not found to select as destination status");
            Assert.fail(destinationStatusToSelect + " project status not found to select as destination status");
        }
    }

    public void navigateToProjectStatusDetailPage(String projectStatusToView) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(projectParameterTable);
        List<WebElement> projectStatusList = driver.findElements(projectStatusNamesList);
        for (WebElement ele : projectStatusList) {
            if (ele.getText().equals(projectStatusToView)) {
                ele.click();
                CommonMethods.waitForElementClickable(destinationStatusDropdownExpandIcon);
                ExtentLogger.pass("Navigated to " + projectStatusToView + " project status detail page");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Failed to navigate to " + projectStatusToView + " project status detail page");
            Assert.fail("Failed to navigate to " + projectStatusToView + " project status detail page");
        }
    }

    public void switchToProjectTypeTab() {
        CommonMethods.waitForElementClickable(projectTypeTabButton).click();
        CommonMethods.waitForElementClickable(addNewProjectTypeButton);
        ExtentLogger.pass("Switched to project type tab");
    }

    public void clickAddNewProjectTypeButton() {
        CommonMethods.waitForElementClickable(addNewProjectTypeButton).click();
        CommonMethods.waitForElementClickable(projectTypeNameInputField);
        ExtentLogger.pass("Clicked the add new project type button");
    }

    public void enterProjectTypeName(String projectTypeName) {
        WebElement projectTypeNameField = CommonMethods.waitForElementClickable(projectTypeNameInputField);
        CommonMethods.getActions().click(projectTypeNameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectTypeName).perform();
        ExtentLogger.pass("Entered the project type name as - " + projectTypeName);
    }

    public void enterProjectTypeDescription(String projectTypeDescription) {
        WebElement projectTypeDescriptionField = CommonMethods.waitForElementClickable(projectTypeDescriptionInputField);
        CommonMethods.getActions().click(projectTypeDescriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectTypeDescription).perform();
        ExtentLogger.pass("Entered the project type description as - " + projectTypeDescription);
    }

    public void checkEnableRevenueForProjectType() {
        WebElement enableRevenueCheckbox = CommonMethods.waitForElementClickable(enableRevenueForProjectTypeCheckbox);
        if (enableRevenueCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the enable revenue checkbox for project type");
        } else {
            enableRevenueCheckbox.click();
            CommonMethods.wait(1000);
            ExtentLogger.pass("Checked the enable revenue checkbox for project type");
        }
    }

    public void checkAllowNonBillingHoursForProjectType() {
        WebElement allowNonBillingHoursCheckbox = CommonMethods.waitForElementClickable(allowNonBillingHoursForProjectTypeCheckbox);
        if (allowNonBillingHoursCheckbox.isSelected()) {
            ExtentLogger.pass("Checked the enable revenue checkbox for project type");
        } else {
            allowNonBillingHoursCheckbox.click();
            ExtentLogger.pass("Checked the enable revenue checkbox for project type");
        }
    }

    public void verifyProjectTypeFromList(String projectTypeToVerify, String projectTypeDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(projectTypeTable);
        CommonMethods.wait(1000);
        List<WebElement> projectTypes = driver.findElements(projectTypeNamesList);
        for (WebElement ele : projectTypes) {
            count++;
            if (ele.getText().equals(projectTypeToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(projectTypeDescriptionVerify, count))).getText(), projectTypeDescription);
                ExtentLogger.pass(projectTypeToVerify + " project type is present in the project type table list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectTypeToVerify + " project type not found");
            Assert.fail(projectTypeToVerify + " project type not found");
        }
    }

    public void saveProjectTypeDetails() {
        CommonMethods.waitForElementClickable(projectTypeSaveButton).click();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(projectTypeTable);
        ExtentLogger.pass("Saved the project type details");
    }

    public void clickProjectStatusDeleteIcon(String projectStatusToDelete) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(projectParameterTable);
        List<WebElement> projectStatusList = driver.findElements(projectStatusNamesList);
        for (WebElement ele : projectStatusList) {
            count++;
            if (ele.getText().equals(projectStatusToDelete)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(projectStatusDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the " + projectStatusToDelete + " project status delete icon");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectStatusToDelete + " project status not found");
            Assert.fail(projectStatusToDelete + " project status not found");
        }
    }

    public void clickProjectTypeDeleteIcon(String projectTypeToDelete) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(projectTypeTable);
        CommonMethods.wait(1000);
        List<WebElement> projectTypes = driver.findElements(projectTypeNamesList);
        for (WebElement ele : projectTypes) {
            count++;
            if (ele.getText().equals(projectTypeToDelete)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(projectTypeDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the " + projectTypeToDelete + " project type delete icon");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectTypeToDelete + " project type not found");
            Assert.fail(projectTypeToDelete + " project type not found");
        }
    }

    public void clickAddProjectPriorityButton() {
        CommonMethods.waitForElementClickable(addNewProjectPriorityButton).click();
        CommonMethods.waitForElementClickable(projectPriorityNameInputBox);
        ExtentLogger.pass("Clicked the add project priorIty button");
    }

    public void enterProjectPriorityName(String projectPriority) {
        WebElement priorityField = CommonMethods.waitForElementClickable(projectPriorityNameInputBox);
        CommonMethods.getActions().click(priorityField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectPriority).perform();
        ExtentLogger.pass("Entered the project priority name as - " + projectPriority);
    }

    public void enterProjectPriorityDescription(String projectPriorityDescription) {
        WebElement priorityDescriptionField = CommonMethods.waitForElementClickable(projectPriorityDescriptionInputBox);
        CommonMethods.getActions().click(priorityDescriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectPriorityDescription).perform();
        ExtentLogger.pass("Entered the project priority description as - " + projectPriorityDescription);
    }

    public void saveProjectPriorityDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(projectPriorityTable);
        CommonMethods.waitForElementClickable(addNewProjectPriorityButton);
        ExtentLogger.pass("Saved the project priority details");
    }

    public void verifyProjectPriorityFromPriorityTable(String projectPriorityName, String projectPriorityDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(projectPriorityTable);
        CommonMethods.wait(1000);
        List<WebElement> projectPriorities = driver.findElements(projectPriorityNameList);
        for (WebElement ele : projectPriorities) {
            count++;
            if (ele.getText().equals(projectPriorityName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(projectPriorityDescriptionVerify, count))).getText(), projectPriorityDescription);
                ExtentLogger.pass(projectPriorityName + " project priority is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectPriorityName + " project priority not found");
            Assert.fail(projectPriorityName + " project priority not found");
        }
    }

    public void selectDefaultProjectPriority(String projectPriorityName) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(projectPriorityTable);
        CommonMethods.wait(1000);
        List<WebElement> projectPriorities = driver.findElements(projectPriorityNameList);
        for (WebElement ele : projectPriorities) {
            count++;
            if (ele.getText().equals(projectPriorityName)) {
                WebElement defaultRadioButton = CommonMethods.waitForElementClickable(By.xpath(String.format(projectPriorityDefaultRadioButton, count)));
                defaultRadioButton.click();
                CommonMethods.wait(1000);
                ExtentLogger.pass(projectPriorityName + " project priority set as default");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(projectPriorityName + " project priority not found");
            Assert.fail(projectPriorityName + " project priority not found");
        }
    }

    public void switchToProjectApprovalTab() {
        CommonMethods.waitForElementClickable(projectApprovalTab).click();
        CommonMethods.waitForElementClickable(addNewApprovalButton);
        ExtentLogger.pass("Switched to project approval tab");
    }

    public void clickAddNewProjectApprovalButton() {
        CommonMethods.waitForElementClickable(addNewApprovalButton).click();
        CommonMethods.waitForElementClickable(projectApprovalNameInputBox);
        ExtentLogger.pass("Clicked the add new project approval button");
    }

    public void enterProjectApprovalName(String projectApprovalName) {
        WebElement approvalNameField = CommonMethods.waitForElementClickable(projectApprovalNameInputBox);
        CommonMethods.getActions().click(approvalNameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectApprovalName).perform();
        ExtentLogger.pass("Entered project approval name as - " + projectApprovalName);
    }

    public void enterProjectApprovalDescription(String projectApprovalDescription) {
        WebElement descriptionField = CommonMethods.waitForElementClickable(projectApprovalDescriptionInputBox);
        CommonMethods.getActions().click(descriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(projectApprovalDescription).perform();
        ExtentLogger.pass("Entered project approval description as - " + projectApprovalDescription);
    }

    public void saveProjectApprovalDetails() {
        CommonMethods.saveDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(addNewApprovalButton);
        CommonMethods.waitForElementClickable(projectApprovalTable);
        ExtentLogger.pass("Saved the project approval details");
    }

    public void verifyProjectApprovalFromApprovalTable(String approvalName, String approvalDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(projectApprovalTable);
        CommonMethods.wait(1000);
        List<WebElement> projectPriorities = driver.findElements(projectApprovalNameList);
        for (WebElement ele : projectPriorities) {
            count++;
            if (ele.getText().equals(approvalName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(projectApprovalDescriptionVerify, count))).getText(), approvalDescription);
                status = true;
                ExtentLogger.pass(approvalName + " approval is present in the approval list");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(approvalName + " approval not found");
            Assert.fail(approvalName + " approval not found");
        }
    }
}