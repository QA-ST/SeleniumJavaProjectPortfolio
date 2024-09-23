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

public class CalendarsPage extends BaseClass {
    By addNewCalender = By.xpath("//a[contains(@id,'btnAddNew')]");
    By calenderNameInputBox = By.xpath("//input[contains(@id,'txtHolidayCalendarName')]");
    By calenderDescriptionInputBox = By.xpath("//textarea[contains(@id,'txtHolidayCalendarDescription')]");
    By calendarsTable = By.xpath("//div[contains(@id,'grdHolidayCalendarList')]/table/tbody/tr[1]/td[1]");
    By calendarList = By.xpath("//div[contains(@id,'grdHolidayCalendarList')]/table/tbody/tr/td[2]/a");

    String calendarDescriptionVerify = "//div[contains(@id,'grdHolidayCalendarList')]/table/tbody/tr[%s]/td[3]/span";

    public void clickAddNewCalenderButton() {
        CommonMethods.waitForElementClickable(addNewCalender).click();
        CommonMethods.waitForElementClickable(calenderNameInputBox);
        ExtentLogger.pass("Clicked the add new calender button");
    }

    public void enterCalenderName(String calenderName) {
        WebElement nameField = CommonMethods.waitForElementClickable(calenderNameInputBox);
        CommonMethods.getActions().click(nameField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(calenderName).perform();
        ExtentLogger.pass("Entered the calender name as - " + calenderName);
    }

    public void enterCalenderDescription(String calenderDescription) {
        WebElement descriptionField = CommonMethods.waitForElementClickable(calenderDescriptionInputBox);
        CommonMethods.getActions().click(descriptionField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(calenderDescription).perform();
        ExtentLogger.pass("Entered the calender description as - " + calenderDescription);
    }

    public void saveCalenderDetails() {
        CommonMethods.saveDetails();
        wait.until(ExpectedConditions.alertIsPresent());
        CommonMethods.acceptAlert();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.waitForElementClickable(calendarsTable);
        ExtentLogger.pass("Saved the calendar details");
    }

    public void verifyCalendarFromList(String calendarName, String calendarDescription) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementClickable(calendarsTable);
        List<WebElement> calendars = driver.findElements(calendarList);
        for (WebElement ele : calendars) {
            count++;
            if (ele.getText().equals(calendarName)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(calendarDescriptionVerify, count))).getText(), calendarDescription);
                ExtentLogger.pass(calendarName + " calendar is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(calendarName + " calender not found");
            Assert.fail(calendarName + " calender not found");
        }
    }
}