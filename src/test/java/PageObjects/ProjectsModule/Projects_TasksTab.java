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

public class Projects_TasksTab extends BaseClass {
    By projectTasksTab = By.id("liProjectTask");
    By taskTeamTab = By.id("liTaskTeam");
    By taskEffortTab = By.id("liTaskEffort");
    By addNewTaskButton = By.id("drpMenuCreatePrj");
    By addTaskOption = By.id("liAddTaskLink");
    By taskNameInputField = By.id("txtTaskName");
    By taskStatusDropdown = By.id("drpStatus");
    By taskTypeDropdown = By.id("drpType");
    By taskPriorityDropdown = By.id("drpPriority");
    By taskStartDate = By.id("txtTaskStartDate");
    By taskEndDate = By.id("txtTaskEndDate");
    By taskSprintDropdown = By.id("drpSprint");
    By taskDurationVerify = By.id("lblDuration");
    By taskNameFilterInputField = By.id("txtTaskName");
    By tasksTable = By.xpath("(//div[@id='tasks']//table)[2]");
    By tasksNameList = By.xpath("(//div[@id='tasks']//table)[2]//tr/td[3]/a");
    By projectStartDateVerify = By.id("lblProjectStartDate");
    By projectEndDateVerify = By.id("lblProjectEndDate");
    By taskTeamPageHeading = By.xpath("//h5/label[@tkey='Assigned members']");
    By taskAssignedMembersTable = By.xpath("//div[@id='divTaskAssignment']//table/tbody");
    By taskAssignedMembersEmailList = By.xpath("//div[@id='divTaskAssignment']//table/tbody/tr/td[4]");
    By professionalCategoryTable = By.xpath("//h5[@id='hdrProfessionalCategory']//following::div[@id='grdWorkTimeByCategory']/table/tbody");
    By taskEstimateEffortByTeamMemberTable = By.xpath("//div[@id='grdWorkTime']//table/tbody");
    By teamMemberEstimatedEffortEntries = By.xpath("//div[@id='grdWorkTime']/table/tbody/tr/td[1]/span");
    By totalEstimatedEffortHoursVerify = By.id("lblEstimatedEffortHour");
    By totalEstimatedEffortMinutesVerify = By.id("lblEstimatedEffortMin");
    By taskEstimateEffortByCategoryTable = By.xpath("//div[@id='grdWorkTimeByCategory' and contains(@style,'display: block;')]/table/tbody/tr[1]");
    By assignedEffortHoursByCategoryVerify = By.id("lblFooterTotalAssignedEffortHours");
    By assignedEffortMinutesByCategoryVerify = By.id("lblFooterTotalAssignedEffortMins");
    By categoriesListProfessionalCategoryTable = By.xpath("//div[@id='grdWorkTimeByCategory']/table/tbody/tr/td[2]/span");
    By addRequiredCategoryButton = By.id("btnAddRequiredCategory");
    By addRequiredCategoryPopup = By.xpath("//div[@id='ModalReqiredCategory' and @style='display: block;']");
    By categoryListCategoryPopup = By.xpath("//div[@id='grdCategories']/table/tbody/tr/td[3]");
    By categoryPopupSaveButton = By.xpath("(//button[@tkey='Save'])[2]");
    By taskPurchaseTabButton = By.id("liTaskPurchases");
    By taskProgressTabButton = By.id("liTaskFollowUp");
    By taskAddPurchaseButton = By.xpath("//button[@id='drpMenuCreateInvoice']");
    By taskAddNewPurchaseOption = By.xpath("//button[@id='drpMenuCreateInvoice' and @aria-expanded='true']/following::ul[1]/li/a[@title='Add New Purchase']");
    By taskPurchaseTotalBudgetVerify = By.xpath("//label[@tkey='Total Budget:']//following::label[1]");
    By taskPurchaseAvailableBudgetVerify = By.xpath("//label[@tkey='Available Budget:']//following::label[1]");
    By taskPurchaseTablePagination = By.id("drpPageSize");
    By taskPurchaseTable = By.xpath("(//div[@id='taskpurchases']//table)[2]/tbody");
    By taskPurchaseList = By.xpath("(//div[@id='taskpurchases']//table)[2]/tbody/tr/td[1]/a");
    By addTaskProgressReportButton = By.xpath("//button[@id='btnCreateTaskFollowUp']");
    By taskProgressAssessmentDropdownIcon = By.xpath("//label/span[@tkey='Assessment:']//following::span[@class='k-select'][1]");
    By assessmentDropdownListOptions = By.xpath("//ul[@id='drpAssessment_listbox' and @aria-hidden='false']/li/span[2]");
    By taskProgressShortDescriptionInputField = By.id("txtShortDescription");
    By taskProgressCompletedPercentageInputField = By.id("txtFollowUpPercentCompleted");
    By taskProgressReportDateInputField = By.id("txtFollowUpDate");
    By taskProgressTable = By.xpath("//div[@id='taskfollowup' and contains(@style,'visibility: visible;')]//table/tbody/tr[1]/td[1]");
    By taskProgressList = By.xpath("//div[@id='taskfollowup' and contains(@style,'visibility: visible;')]//table/tbody/tr/td[2]/a");

    String taskTypeVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[5]";
    String taskStatusVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[6]";
    String taskPriorityVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[8]";
    String taskStartDateVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[9]";
    String taskEndDateVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[10]";
    String taskDurationVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[16]/span";
    String taskAssessmentVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[21]";
    String taskCompletedPercentageVerifyFromList = "(//div[@id='tasks']//table)[2]//tr[%s]/td[22]/span";
    String taskAssignedMemberRole = "//div[@id='divTaskAssignment']//table/tbody/tr[%s]/td[14]";
    String teamMemberEstimatedEffortHoursInputField = "//div[@id='grdWorkTime']/table/tbody/tr[%s]/td[9]//input[contains(@class,'grdTextBox')]";
    String teamMemberEstimatedEffortMinutesVerify = "//div[@id='grdWorkTime']/table/tbody/tr[%s]/td[9]//input[contains(@id,'hdnWorkTimeEstimatedEffortMins')]";
    String teamMemberEstimatedEffortMinutesDropdown = "//div[@id='grdWorkTime']/table/tbody/tr[%s]//select[contains(@id,'drpWorkTimeEstimatedEffortMins')]";
    String taskDeleteIcon = "(//div[@id='tasks']//table)[2]//tr[%s]/td/*[local-name()='svg']";
    String professionalCategoryAssignedHoursVerify = "//div[@id='grdWorkTimeByCategory']/table/tbody/tr[%s]/td[4]//span[contains(@id,'lblAssignedEffortHours')]";
    String professionalCategoryAssignedMinutesVerify = "//div[@id='grdWorkTimeByCategory']/table/tbody/tr[%s]/td[4]//span[contains(@id,'lblAssignedEffortMins')]";
    String categorySelectCheckboxCategoryPopup = "//div[@id='grdCategories']/table/tbody/tr[%s]/td[3]//preceding::td[2]//input";
    String categoryUnassignedEffortInputField = "//div[@id='grdWorkTimeByCategory']/table/tbody/tr[%s]/td/div[@class='divUnAssignedEffort']/input[contains(@id,'NonAssignedEffort')][1]";
    String categoryUnassignedEffortMinutesVerify = "//div[@id='grdWorkTimeByCategory']/table/tbody/tr[%s]/td/div[@class='divUnAssignedEffort']/select[contains(@id,'drpNonAssignedEffortMins')][1]/option[@selected]";
    String taskPurchaseStatusVerify = "(//div[@id='taskpurchases']//table)[2]/tbody/tr[%s]/td[2]";
    String taskPurchaseDateVerify = "(//div[@id='taskpurchases']//table)[2]/tbody/tr[%s]/td[3]";
    String taskPurchaseProjectedAmountVerify = "(//div[@id='taskpurchases']//table)[2]/tbody/tr[%s]/td[5]/span";
    String taskPurchaseActualAmountVerify = "(//div[@id='taskpurchases']//table)[2]/tbody/tr[%s]/td[7]/span";
    String taskPurchaseDeleteIcon = "(//div[@id='taskpurchases']//table)[2]/tbody/tr[%s]//a[@id='imgDeleteTaskPurchase']";
    String taskProgressCompletePercentageVerify = "//div[@id='taskfollowup' and contains(@style,'visibility: visible;')]//table/tbody/tr[%s]/td[3]/span";
    String taskProgressAssessmentVerify = "//div[@id='taskfollowup' and contains(@style,'visibility: visible;')]//table/tbody/tr[%s]/td[4]";
    String taskProgressReportDateVerify = "//div[@id='taskfollowup' and contains(@style,'visibility: visible;')]//table/tbody/tr[%s]/td[5]";

    public void navigateToTasksTab() {
        CommonMethods.waitForElementClickable(projectTasksTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementClickable(addNewTaskButton);
        ExtentLogger.pass("Navigated to tasks tab");
    }

    public void selectAddTaskOption() {
        CommonMethods.getActions().click(CommonMethods.waitForElementClickable(addNewTaskButton)).pause(Duration.ofSeconds(1)).perform();
        CommonMethods.waitForElementClickable(addTaskOption).click();
        CommonMethods.waitForElementVisibility(taskNameInputField);
        ExtentLogger.pass("Selected add new task option");
    }

    public void enterTaskName(String taskName) {
        CommonMethods.waitForElementVisibility(taskNameInputField).clear();
        driver.findElement(taskNameInputField).sendKeys(taskName);
        ExtentLogger.pass("Entered task name as - " + taskName);
    }

    public void selectTaskStatus(String status) {
        WebElement ele = CommonMethods.waitForElementClickable(taskStatusDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(status);
        ExtentLogger.pass("Selected task status as - " + status);
    }

    public void selectTaskType(String type) {
        WebElement ele = CommonMethods.waitForElementClickable(taskTypeDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(type);
        ExtentLogger.pass("Selected task type as - " + type);
    }

    public void selectTaskPriority(String priority) {
        WebElement ele = CommonMethods.waitForElementClickable(taskPriorityDropdown);
        CommonMethods.getSelect(ele).selectByVisibleText(priority);
        ExtentLogger.pass("Selected task priority as - " + priority);
    }

    public void enterTaskStartDate(String date) {
        WebElement taskStartDateField = CommonMethods.waitForElementVisibility(taskStartDate);
        CommonMethods.getActions().click(taskStartDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(date + Keys.ENTER).perform();
        ExtentLogger.pass("Entered " + date + " as task start date");
    }

    public void enterTaskEndDate(String date) {
        WebElement taskEndDateField = CommonMethods.waitForElementVisibility(taskEndDate);
        CommonMethods.getActions().click(taskEndDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(date + Keys.ENTER).perform();
        ExtentLogger.pass("Entered " + date + " as task start date");
    }

    public void selectSprintForTask(String sprintNameToSelect) {
        CommonMethods.waitForElementClickable(taskSprintDropdown);
        CommonMethods.getSelect(driver.findElement(taskSprintDropdown)).selectByVisibleText(sprintNameToSelect);
        ExtentLogger.pass("Selected " + sprintNameToSelect + " sprint for task");
    }

    public void saveTaskGeneralDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.wait(1000);
        CommonMethods.waitForElementVisibility(taskNameInputField);
        ExtentLogger.pass("Saved the task general details");
    }

    public void saveTaskEffortDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForPageLoader();
        CommonMethods.wait(1000);
        CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
        ExtentLogger.pass("Saved the task effort details");
    }

    public void saveTaskDetailsUpdated() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForSuccessMessageSidePopup();
        ExtentLogger.pass("Saved the task details");
    }

    public void verifyTaskDuration(String duration) {
        CommonMethods.wait(1000);
        WebElement ele = CommonMethods.waitForElementVisibility(taskDurationVerify);
        String durationDisplayed = ele.getText();
        Assert.assertEquals(durationDisplayed, duration);
        ExtentLogger.pass("Task duration is as expected - " + duration);
    }

    public void filterTasksListByName(String taskName) {
        CommonMethods.waitForElementVisibility(taskNameFilterInputField).clear();
        driver.findElement(taskNameFilterInputField).sendKeys(taskName);
        ExtentLogger.pass("Entered the " + taskName + " task name in the task name filter");
    }

    public void verifyTaskFromTasksList(String taskNameToVerify, String taskType, String taskStatus, String taskPriority, String startDate, String endDate, String taskDuration) {
        int count = 0;
        boolean status = false;
        CommonMethods.selectAllPaginationOption();
        CommonMethods.waitForElementVisibility(tasksTable);
        List<WebElement> tasksDisplayed = driver.findElements(tasksNameList);
        for (WebElement task : tasksDisplayed) {
            count++;
            String taskNameText = task.getText();
            if (taskNameText.equals(taskNameToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskTypeVerifyFromList, count))).getText(), taskType);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskStatusVerifyFromList, count))).getText(), taskStatus);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskPriorityVerifyFromList, count))).getText(), taskPriority);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskStartDateVerifyFromList, count))).getText(), startDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskEndDateVerifyFromList, count))).getText(), endDate);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskDurationVerifyFromList, count))).getText(), taskDuration);
                status = true;
                ExtentLogger.pass(taskNameToVerify + " is present in the tasks list");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskNameToVerify + " not found in the tasks list");
            Assert.fail(taskNameToVerify + " not found in the tasks list");
        }
    }

    public void navigateToTask(String taskNameToNavigate) {
        boolean status = false;
        CommonMethods.selectAllPaginationOption();
        CommonMethods.waitForElementVisibility(tasksTable);
        List<WebElement> tasksDisplayed = driver.findElements(tasksNameList);
        for (WebElement task : tasksDisplayed) {
            String taskNameText = task.getText();
            if (taskNameText.equals(taskNameToNavigate)) {
                task.click();
                CommonMethods.waitForElementVisibility(taskNameInputField);
                status = true;
                ExtentLogger.pass("Navigated to task - " + taskNameToNavigate);
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskNameToNavigate + " not found in the tasks list");
            Assert.fail(taskNameToNavigate + " not found in the tasks list");
        }
    }

    public void verifyProjectDatesFromTask(String startDateToVerify, String endDateToVerify) {
        WebElement startDate = CommonMethods.waitForElementVisibility(projectStartDateVerify);
        Assert.assertEquals(startDate.getText(), startDateToVerify);
        Assert.assertEquals(driver.findElement(projectEndDateVerify).getText(), endDateToVerify);
        ExtentLogger.pass("Project start and end dates are as expected");
    }

    public void navigateToTaskTeamTab() {
        CommonMethods.waitForElementClickable(taskTeamTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementVisibility(taskTeamPageHeading);
        ExtentLogger.pass("Navigated to tasks team tab");
    }

    public void verifyTaskAssignedMember(String userEmailToVerify, String userRoleToVerify) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(taskAssignedMembersTable);
        List<WebElement> userEmailsDisplayed = driver.findElements(taskAssignedMembersEmailList);
        for (WebElement email : userEmailsDisplayed) {
            count++;
            if (email.getText().equals(userEmailToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(taskAssignedMemberRole, count))).getText(), userRoleToVerify);
                status = true;
                ExtentLogger.pass(userEmailToVerify + " user is assigned to the task as - " + userRoleToVerify);
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(userEmailToVerify + " user not found as an assigned member in the task");
            Assert.fail(userEmailToVerify + " user not found as an assigned member in the task");
        }
    }

    public void navigateToTaskEffortTab() {
        CommonMethods.waitForElementClickable(taskEffortTab).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.wait(1000);
        CommonMethods.waitForElementClickable(professionalCategoryTable);
        ExtentLogger.pass("Navigated to tasks effort tab");
    }

    public void verifyTaskEstimatedEffortByTeamMember(String userNameToVerify, String estimatedHours, String estimatedMinutes) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(taskEstimateEffortByTeamMemberTable);
        List<WebElement> assignedMembers = driver.findElements(teamMemberEstimatedEffortEntries);
        for (WebElement members : assignedMembers) {
            count++;
            if (members.getText().equals(userNameToVerify)) {
                String hours = driver.findElement(By.xpath(String.format(teamMemberEstimatedEffortHoursInputField, count))).getAttribute("value");
                String minutes = driver.findElement(By.xpath(String.format(teamMemberEstimatedEffortMinutesVerify, count))).getAttribute("value");
                Assert.assertEquals(hours, estimatedHours);
                Assert.assertEquals(minutes, estimatedMinutes);
                status = true;
                ExtentLogger.pass("Assign by team member estimated effort table has entry for " + userNameToVerify + " user with " + estimatedHours + ":" + estimatedMinutes + " estimated hours");
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Assign by team member estimated effort table entry for " + userNameToVerify + " not found");
            Assert.fail("Assign by team member estimated effort table entry for " + userNameToVerify + " not found");
        }
    }

    public void clickTaskDeleteIcon(String taskName) {
        int count = 0;
        boolean status = false;
        CommonMethods.selectAllPaginationOption();
        CommonMethods.waitForElementVisibility(tasksTable);
        List<WebElement> tasksDisplayed = driver.findElements(tasksNameList);
        for (WebElement task : tasksDisplayed) {
            count++;
            String taskNameText = task.getText();
            if (taskNameText.equals(taskName)) {
                CommonMethods.waitForElementClickable(By.xpath(String.format(taskDeleteIcon, count))).click();
                wait.until(ExpectedConditions.alertIsPresent());
                ExtentLogger.pass("Clicked the delete icon for " + taskName + " task");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.pass("Not clicked the delete icon for " + taskName + " task");
            Assert.fail("Not clicked the delete icon for " + taskName + " task");
        }
    }

    public void verifyTaskTotalEstimatedEffort(String hours, String minutes) {
        String totalEstimatedEffortHours = CommonMethods.waitForElementVisibility(totalEstimatedEffortHoursVerify).getText();
        Assert.assertEquals(totalEstimatedEffortHours, hours);
        String totalEstimatedEffortMinutes = CommonMethods.waitForElementVisibility(totalEstimatedEffortMinutesVerify).getText();
        Assert.assertEquals(totalEstimatedEffortMinutes, minutes);
        ExtentLogger.pass("Task total estimated effort is as expected - " + hours + ":" + minutes + " h");
    }

    public void verifyTaskAssignedEffortByCategoryTable(String hours, String minutes) {
        CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
        String totalEstimatedEffortHours = CommonMethods.waitForElementVisibility(assignedEffortHoursByCategoryVerify).getText();
        Assert.assertEquals(totalEstimatedEffortHours, hours);
        String totalEstimatedEffortMinutes = CommonMethods.waitForElementVisibility(assignedEffortMinutesByCategoryVerify).getText();
        Assert.assertEquals(totalEstimatedEffortMinutes, minutes);
        ExtentLogger.pass("Task assigned effort is as expected - " + hours + ":" + minutes + " hrs");
    }

    public void assignEstimateEffortByTeamMember(String username, String estimateHours, String estimateMinutes) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(taskEstimateEffortByTeamMemberTable);
        CommonMethods.wait(1000);
        List<WebElement> assignedMembers = driver.findElements(teamMemberEstimatedEffortEntries);
        for (WebElement members : assignedMembers) {
            count++;
            if (members.getText().equals(username)) {
                WebElement estimateHoursField = CommonMethods.waitForElementClickable(By.xpath(String.format(teamMemberEstimatedEffortHoursInputField, count)));
                jsExecutor.executeScript("arguments[0].value = '';", estimateHoursField);
                estimateHoursField.sendKeys(estimateHours);
                WebElement estimateMinutesDropdown = CommonMethods.waitForElementClickable(By.xpath(String.format(teamMemberEstimatedEffortMinutesDropdown, count)));
                CommonMethods.getSelect(estimateMinutesDropdown).selectByVisibleText(estimateMinutes);
                ExtentLogger.pass("Assigned " + estimateHours + ":" + estimateMinutes + " hrs estimate effort for task to " + username + " user");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail("Estimated effort for task not assigned to " + username + " user");
            Assert.fail("Estimated effort for task not assigned to " + username + " user");
        }
    }

    public void verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(String categoryToVerify, String hours, String minutes) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
        List<WebElement> categories = driver.findElements(categoriesListProfessionalCategoryTable);
        for (WebElement ele : categories) {
            count++;
            if (ele.getText().contains(categoryToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(professionalCategoryAssignedHoursVerify, count))).getText(), hours);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(professionalCategoryAssignedMinutesVerify, count))).getText(), minutes);
                ExtentLogger.pass(categoryToVerify + " category has " + hours + ":" + minutes + " hrs assigned");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail(categoryToVerify + " category does not has " + hours + ":" + minutes + " hrs assigned");
            Assert.fail(categoryToVerify + " category does not has " + hours + ":" + minutes + " hrs assigned");
        }
    }

    public void addCategoryFromTaskEffortTab(String categoryToAdd) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementClickable(addRequiredCategoryButton).click();
        CommonMethods.wait(1000);
        List<WebElement> categoryList = driver.findElements(categoryListCategoryPopup);
        for (WebElement ele : categoryList) {
            count++;
            if (ele.getText().equals(categoryToAdd)) {
                driver.findElement(By.xpath(String.format(categorySelectCheckboxCategoryPopup, count))).click();
                CommonMethods.waitForElementClickable(categoryPopupSaveButton).click();
                CommonMethods.wait(1000);
                CommonMethods.waitForPageLoader();
                CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
                ExtentLogger.pass("Added " + categoryToAdd + " category in the task");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail("Failed to add " + categoryToAdd + " category");
            Assert.fail("Failed to add " + categoryToAdd + " category");
        }
    }

    public void addUnassignedEffortToCategory(String categoryToAssign, String estimatedHours) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
        CommonMethods.wait(1000);
        List<WebElement> categories = driver.findElements(categoriesListProfessionalCategoryTable);
        for (WebElement ele : categories) {
            count++;
            if (ele.getText().contains(categoryToAssign)) {
                WebElement unassignedEffortField = CommonMethods.waitForElementClickable(By.xpath(String.format(categoryUnassignedEffortInputField, count)));
                CommonMethods.getActions().click(unassignedEffortField).keyDown(Keys.DELETE).keyUp(Keys.DELETE).sendKeys(estimatedHours).perform();
                ExtentLogger.pass("Added " + estimatedHours + " hrs unassigned to " + categoryToAssign + " category");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.fail("Failed to add unassigned hours to " + categoryToAssign + " category");
            Assert.fail("Failed to add unassigned hours to " + categoryToAssign + " category");
        }
    }

    public void verifyCategoryUnassignedEffort(String categoryToVerify, String hours, String minutes) {
        int count = 0;
        boolean status = false;
        CommonMethods.waitForElementVisibility(taskEstimateEffortByCategoryTable);
        List<WebElement> categories = driver.findElements(categoriesListProfessionalCategoryTable);
        for (WebElement ele : categories) {
            count++;
            if (ele.getText().contains(categoryToVerify)) {
                Assert.assertEquals(driver.findElement(By.xpath(String.format(categoryUnassignedEffortInputField, count))).getAttribute("value"), hours);
                Assert.assertEquals(driver.findElement(By.xpath(String.format(categoryUnassignedEffortMinutesVerify, count))).getText(), minutes);
                ExtentLogger.pass(categoryToVerify + " category has " + hours + ":" + minutes + " hrs unassigned effort");
                status = true;
            }
        }
        if (!status) {
            ExtentLogger.pass(categoryToVerify + " category not found");
            Assert.fail(categoryToVerify + " category not found");
        }
    }

    public void switchToTaskPurchaseTab() {
        CommonMethods.waitForElementClickable(taskPurchaseTabButton).click();
        CommonMethods.waitForPageLoader();
        CommonMethods.waitForElementClickable(taskAddPurchaseButton);
        ExtentLogger.pass("Switched to task purchase tab");
    }

    public void selectTaskAddNewPurchaseOption() {
        CommonMethods.waitForElementClickable(taskAddPurchaseButton).click();
        CommonMethods.waitForElementClickable(taskAddNewPurchaseOption).click();
        ExtentLogger.pass("Selected the task add purchase option");
    }

    public void verifyTaskPurchaseTotalAndAvailableBudget(String totalBudgetToVerify, String availableBudgetToVerify) {
        Assert.assertEquals(CommonMethods.waitForElementClickable(taskPurchaseTotalBudgetVerify).getText(), totalBudgetToVerify);
        Assert.assertEquals(CommonMethods.waitForElementClickable(taskPurchaseAvailableBudgetVerify).getText(), availableBudgetToVerify);
        ExtentLogger.pass("Task purchase total budget - " + totalBudgetToVerify + " and purchase available budget - " + availableBudgetToVerify);
    }

    public void verifyPurchaseFromTaskPurchaseList(String taskPurchaseToVerify, String purchaseStatus, String purchaseStatusDate, String purchaseProjectedAmount, String purchaseActualAmount) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(taskPurchaseTable);
        WebElement ele = CommonMethods.waitForElementClickable(taskPurchaseTablePagination);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(2000);
        List<WebElement> taskPurchases = driver.findElements(taskPurchaseList);
        for (WebElement purchase : taskPurchases) {
            count++;
            if (purchase.getText().equals(taskPurchaseToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskPurchaseStatusVerify, count))).getText().contains(purchaseStatus));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskPurchaseDateVerify, count))).getText().contains(purchaseStatusDate));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskPurchaseProjectedAmountVerify, count))).getText().contains(purchaseProjectedAmount));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskPurchaseActualAmountVerify, count))).getText().contains(purchaseActualAmount));
                ExtentLogger.pass(taskPurchaseToVerify + " purchase is present in the task purchase list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskPurchaseToVerify + " purchase not found");
            Assert.fail(taskPurchaseToVerify + " purchase not found");
        }
    }

    public void navigateToTaskPurchase(String taskPurchase) {
        boolean status = false;
        CommonMethods.waitForElementVisibility(taskPurchaseTable);
        WebElement ele = CommonMethods.waitForElementClickable(taskPurchaseTablePagination);
        CommonMethods.getSelect(ele).selectByVisibleText(CommonMethods.allPaginationOption);
        CommonMethods.wait(2000);
        List<WebElement> taskPurchases = driver.findElements(taskPurchaseList);
        for (WebElement purchase : taskPurchases) {
            if (purchase.getText().equals(taskPurchase)) {
                purchase.click();
                CommonMethods.waitForPageLoader();
                ExtentLogger.pass("Navigated to " + taskPurchase + " task purchase");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskPurchase + " task purchase not found");
            Assert.fail(taskPurchase + " task purchase not found");
        }
    }

    public void switchToTaskProgressTab() {
        CommonMethods.waitForElementClickable(taskProgressTabButton).click();
        CommonMethods.waitForElementClickable(addTaskProgressReportButton);
        ExtentLogger.pass("Switched to task progress tab");
    }

    public void clickAddTaskProgressReportButton() {
        CommonMethods.wait(1000);
        CommonMethods.clickUsingJavascript(driver.findElement(addTaskProgressReportButton));
//        CommonMethods.waitForElementClickable(addTaskProgressReportButton).click();
        CommonMethods.waitForElementClickable(taskProgressAssessmentDropdownIcon);
        ExtentLogger.pass("Clicked the add task progress report button");
    }

    public void selectTaskProgressAssessment(String assessmentToSelect) {
        boolean status = false;
        CommonMethods.waitForElementClickable(taskProgressAssessmentDropdownIcon).click();
        List<WebElement> assessmentOptions = driver.findElements(assessmentDropdownListOptions);
        for (WebElement ele : assessmentOptions) {
            String assessmentOption = ele.getAttribute("data-original-title");
            if (!assessmentOption.isEmpty() && assessmentOption.equals(assessmentToSelect)) {
                CommonMethods.wait(1000);
                CommonMethods.clickUsingJavascript(ele);
                ExtentLogger.pass("Selected " + assessmentToSelect + " assessment for task progress");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail("Failed to " + assessmentToSelect + " assessment for task progress");
            Assert.fail("Failed to " + assessmentToSelect + " assessment for task progress");
        }
    }

    public void enterTaskProgressShortDescription(String shortDescription) {
        WebElement shortDescriptionField = CommonMethods.waitForElementClickable(taskProgressShortDescriptionInputField);
        shortDescriptionField.clear();
        shortDescriptionField.sendKeys(shortDescription);
        ExtentLogger.pass("Entered the task progress short description as - " + shortDescription);
    }

    public void enterTaskProgressCompletePercentage(String completePercentage) {
        WebElement completePercentageField = CommonMethods.waitForElementClickable(taskProgressCompletedPercentageInputField);
        completePercentageField.clear();
        completePercentageField.sendKeys(completePercentage);
        ExtentLogger.pass("Entered the task progress complete percentage as - " + completePercentage + "%");
    }

    public void enterTaskProgressReportDate(String reportDate) {
        WebElement reportDateField = CommonMethods.waitForElementClickable(taskProgressReportDateInputField);
        CommonMethods.getActions().click(reportDateField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(reportDate).perform();
        ExtentLogger.pass("Entered task progress report as - " + reportDate);
    }

    public void saveTaskProgressDetails() {
        CommonMethods.clickSaveButton();
        CommonMethods.waitForElementVisibility(taskProgressTable);
        ExtentLogger.pass("Saved the task progress details");
    }

    public void verifyTaskProgressReportFromTaskProgressTable(String taskProgressToVerify, String taskProgressCompletedPercentage, String taskProgressAssessment, String taskProgressReportDate) {
        boolean status = false;
        int count = 0;
        CommonMethods.waitForElementVisibility(taskProgressTable);
        List<WebElement> progressList = driver.findElements(taskProgressList);
        for (WebElement ele : progressList) {
            count++;
            if (ele.getText().equals(taskProgressToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskProgressCompletePercentageVerify, count))).getText().contains(taskProgressCompletedPercentage));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskProgressAssessmentVerify, count))).getText().contains(taskProgressAssessment));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskProgressReportDateVerify, count))).getText().contains(taskProgressReportDate));
                ExtentLogger.pass(taskProgressToVerify + " task progress is present in the list");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskProgressToVerify + " task progress not found");
            Assert.fail(taskProgressToVerify + " task progress not found");
        }
    }

    public void verifyTaskProgressFromTaskList(String taskNameToVerify, String assessment, String completedPercentage) {
        int count = 0;
        boolean status = false;
        CommonMethods.selectAllPaginationOption();
        CommonMethods.waitForElementVisibility(tasksTable);
        List<WebElement> tasksDisplayed = driver.findElements(tasksNameList);
        for (WebElement task : tasksDisplayed) {
            count++;
            String taskNameText = task.getText();
            if (taskNameText.equals(taskNameToVerify)) {
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskAssessmentVerifyFromList, count))).getText().contains(assessment));
                Assert.assertTrue(driver.findElement(By.xpath(String.format(taskCompletedPercentageVerifyFromList, count))).getText().contains(completedPercentage));
                ExtentLogger.pass(taskNameToVerify + " task has assessment type " + assessment + " and completed percentage as " + completedPercentage + "%");
                status = true;
                break;
            }
        }
        if (!status) {
            ExtentLogger.fail(taskNameToVerify + " task not found in the tasks table list");
            Assert.fail(taskNameToVerify + " task not found in the tasks table list");
        }
    }
}