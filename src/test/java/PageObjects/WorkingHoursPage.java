package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class WorkingHoursPage extends BaseClass {
    By addNewWorkingHoursButton = By.xpath("//button[@id='btnAddNewSWH']");
    By workingHourNameInputBox = By.xpath("//input[@id='txtSWHName']");
    By workingHourTable = By.xpath("//div[@id='standardworkhours']/table/tbody/tr[1]/td[2]");
    By workingHourList = By.xpath("//div[@id='standardworkhours']/table/tbody/tr/td[3]/a");

    String workingHourInputBox = "//input[@id='%s']";
    String workingHoursWeekdayWiseVerify = "//div[@id='standardworkhours']/table/tbody/tr[%s]/td[%s]";

    public void clickAddNewWorkingHoursButton() {
        CommonMethods.waitForElementClickable(addNewWorkingHoursButton).click();
        CommonMethods.waitForElementClickable(workingHourNameInputBox);
        ExtentLogger.pass("Clicked the add new working hours button");
    }

    public void enterWorkingHoursName(String workingGroupName) {
        WebElement nameField = CommonMethods.waitForElementClickable(workingHourNameInputBox);
        CommonMethods.getActions().click(nameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(workingGroupName).perform();
        ExtentLogger.pass("Entered working group name as - " + workingGroupName);
    }

    public void enterWorkingHoursPerWeekDay(String weekDay, String workingHours) {
        WebElement workingHourField = CommonMethods.waitForElementClickable(By.xpath(String.format(workingHourInputBox, weekDay)));
        CommonMethods.getActions().click(workingHourField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(workingHours).perform();
        ExtentLogger.pass("Entered " + workingHours + " hours for " + weekDay);
    }

    public void saveWorkingHourDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(workingHourTable);
        CommonMethods.waitForElementClickable(addNewWorkingHoursButton);
        ExtentLogger.pass("Saved the working hour details");
    }

    public void verifyWorkingHourFromList(String workingHourToVerify, String mondayHours, String tuesdayHours, String wednesdayHours, String thursdayHours, String fridayHours, String saturdayHours, String sundayHours) {
        boolean status = false;
        int count = 0;
        int hoursVerifyIndexCount = 0;
        CommonMethods.waitForElementClickable(workingHourTable);
        CommonMethods.selectAllPaginationOption();
        String[] hoursToVerify = {mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours, sundayHours};
        List<WebElement> workingHours = driver.findElements(workingHourList);
        for (WebElement ele : workingHours) {
            count++;
            if (ele.getText().equals(workingHourToVerify)) {
                for (int i = 4; i <= 10; i++) {
                    Assert.assertEquals(driver.findElement(By.xpath(String.format(workingHoursWeekdayWiseVerify, count, i))).getText(), hoursToVerify[hoursVerifyIndexCount]);
                    hoursVerifyIndexCount++;
                }
                ExtentLogger.pass(workingHourToVerify + " working hour is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(workingHourToVerify + " working hour not found");
            Assert.fail(workingHourToVerify + " working hour not found");
        }
    }
}