package Tests.SmokeTests;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC04_EstimatedEffortHoursTest extends BaseClass {
    String firstTestCategoryName = FetchPropertiesData.getCostManagementTestData("TestEstimatedEffortCategory").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String secondTestCategoryName = FetchPropertiesData.getCostManagementTestData("DemoCategoryName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testUserEmail = FetchPropertiesData.getUserManagementTestData("TestUserEmail").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testPersonnelNumber = FetchPropertiesData.getUserManagementTestData("TestPersonalNumber");
    String testFirstName = FetchPropertiesData.getUserManagementTestData("TestUserFirstName");
    String testLastName = FetchPropertiesData.getUserManagementTestData("TestUserLastName");
    String testUserDisplayName = FetchPropertiesData.getUserManagementTestData("TestUserDisplayName");
    String testPositionName = FetchPropertiesData.getUserManagementTestData("TestUserPosition");
    String userRole = FetchPropertiesData.getUserManagementTestData("MastermindUserRole");
    String currentDate = HelperMethods.getCurrentDate();
    String testProjectName = FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String projectEndDate = HelperMethods.getDateTenDaysFromCurrent();
    String firstTaskName = FetchPropertiesData.getProjectsModuleTestData("TestEffortTask").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String secondTaskName = FetchPropertiesData.getProjectsModuleTestData("TestEffortTask2").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String firstTaskEndDate = HelperMethods.getDateFourDaysFromCurrent();
    String secondTaskStartDate = HelperMethods.getDateFiveDaysFromCurrent();
    String secondTaskEndDate = HelperMethods.getDateSevenDaysFromCurrent();
    String firsTaskEstimatedEffort = "08:00";
    String secondTaskEstimatedEffort = "05:00";
    String hoursVerify_8 = "8";
    String hoursVerify_5 = "5";
    String hoursVerify_15 = "15";
    String hoursVerify_25 = "25";
    String totalEstimateEffort_25 = "25:00";
    String assignedEstimateEffort_12 = "12:00";
    String totalEstimateEffort_15 = "15:00";
    String unassignedEstimatedEffort_3 = "03:00";
    String totalEstimateEffort_10 = "10:00";
    String hoursVerify_3 = "3";
    String hoursVerify_0 = "0";
    String totalEstimateTime_13 = "13:00";
    String totalEstimateTime_23 = "23:00";
    String projectBudgetEstimateEffort_33 = "33:00";
    String unassignedEstimateEffort_10 = "10";
    String estimatedEffort_12 = "12";
    String numberOfDaysToViewPlanningData = "15";
    String taskDemandTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='task' and @style=''])[%s]/div/div[1]/input";
    String taskAllocatedTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='task' and @style=''])[%s]/div/div[2]/input";
    String taskUnassignedTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='task' and @style=''])[%s]/div/div[3]/input";
    String categoryDemandTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='category' and @style=''])[%s]/div/div[1]/input";
    String categoryAllocatedTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='category' and @style=''])[%s]/div/div[2]/input";
    String categoryUnassignedTimeIntervalVerify = "(//div[@id='divProjectBodyRight']//div[@datatype='category' and @style=''])[%s]/div/div[3]/input";

    @Test(groups = {"Smoke test case"})
    public void estimateEffortHoursTest() {
        pageFactory.getSideBarHamburgerMenu().navigateToCategoriesAndRatesPage();
        addCategory(firstTestCategoryName);
        addCategory(secondTestCategoryName);
        createNewUserWithTheAddedCategory();
        createProject();
        addUserToProject();
        createTasks(firstTaskName, currentDate, firstTaskEndDate);
        createTasks(secondTaskName, secondTaskStartDate, secondTaskEndDate);
        addEstimateEffortFromTeamTab();
        verifyEstimateEffortFromBudgetTab(totalEstimateTime_13);
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        verifyTaskEstimatedEffortFromGanttTab(firstTaskName, FetchPropertiesData.getEnvironmentVariables("UserName"), hoursVerify_8);
        verifyTaskEstimatedEffortFromGanttTab(firstTaskName, testUserDisplayName, hoursVerify_0);
        verifyTaskEstimatedEffortFromGanttTab(secondTaskName, FetchPropertiesData.getEnvironmentVariables("UserName"), hoursVerify_0);
        verifyTaskEstimatedEffortFromGanttTab(secondTaskName, testUserDisplayName, hoursVerify_5);
        verifyEstimatedEffortForFirstTask();
        verifyEstimatedEffortForSecondTask();
        viewResourcesPlanningDataForProject();
        verifyTaskDemandTimeIntervalEstimatedEffortFromResources(firstTaskName, "1", currentDate, firstTaskEndDate, firsTaskEstimatedEffort);
        verifyTaskAllocatedTimeIntervalEstimatedEffortFromResources(firstTaskName, "1", currentDate, firstTaskEndDate, firsTaskEstimatedEffort);
        verifyTaskDemandTimeIntervalEstimatedEffortFromResources(secondTaskName, "2", secondTaskStartDate, secondTaskEndDate, secondTaskEstimatedEffort);
        verifyTaskAllocatedTimeIntervalEstimatedEffortFromResources(secondTaskName, "2", secondTaskStartDate, secondTaskEndDate, secondTaskEstimatedEffort);
        minimizePlanningTableData();
        navigateToProject();
        navigateToTask(secondTaskName);
        addCategoryToTaskAndAddUnassignedEstimatedEffortToCategory();
        verifyEstimateEffortFromBudgetTab(totalEstimateTime_23);
        navigateToTask(secondTaskName);
        addUnassignedHoursToCategoryAndAddEstimateEffortToCategoryUserAndVerifyDecreasedUnassignedHours();
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        verifyTaskEstimatedEffortFromGanttTab(secondTaskName, testUserDisplayName, estimatedEffort_12);
        verifyEstimateEffortFromBudgetTab(projectBudgetEstimateEffort_33);
        viewResourcesPlanningDataForProject();
        verifyTaskDemandTimeIntervalEstimatedEffortFromResources(secondTaskName, "2", secondTaskStartDate, secondTaskEndDate, totalEstimateEffort_25);
        verifyTaskAllocatedTimeIntervalEstimatedEffortFromResources(secondTaskName, "2", secondTaskStartDate, secondTaskEndDate, assignedEstimateEffort_12);
        verifyTaskUnassignedTimeIntervalEstimatedEffortFromResources(secondTaskName, "2", secondTaskStartDate, secondTaskEndDate, totalEstimateTime_13);
        pageFactory.getResourcesPage().expandTaskFromPlanningTable(secondTaskName);
        verifyCategoryDemandTimeIntervalEstimatedEffortFromResources(firstTestCategoryName, "1", secondTaskStartDate, secondTaskEndDate, totalEstimateEffort_15);
        verifyCategoryAllocatedTimeIntervalEstimatedEffortFromResources(firstTestCategoryName, "1", secondTaskStartDate, secondTaskEndDate, assignedEstimateEffort_12);
        verifyCategoryUnassignedTimeIntervalEstimatedEffortFromResources(firstTestCategoryName, "1", secondTaskStartDate, secondTaskEndDate, unassignedEstimatedEffort_3);
        verifyCategoryDemandTimeIntervalEstimatedEffortFromResources(secondTestCategoryName, "2", secondTaskStartDate, secondTaskEndDate, totalEstimateEffort_10);
        verifyCategoryUnassignedTimeIntervalEstimatedEffortFromResources(secondTestCategoryName, "2", secondTaskStartDate, secondTaskEndDate, totalEstimateEffort_10);
        minimizePlanningTableData();
        removeCategoryFromUserAndDeleteUser();
        navigateToProject();
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        deleteTask(firstTaskName);
        deleteTask(secondTaskName);
        deleteProject();
        pageFactory.getSideBarHamburgerMenu().navigateToCategoriesAndRatesPage();
        deleteCategory(firstTestCategoryName);
        deleteCategory(secondTestCategoryName);
    }

    public void addCategory(String categoryName) {
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().clickAddNewCategoryButton();
        pageFactory.getCategoriesAndRatesPage().enterCategoryName(categoryName);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryFromCategoryTable(categoryName);
        CommonMethods.switchToDefaultContent();
    }

    public void createNewUserWithTheAddedCategory() {
        pageFactory.getSideBarHamburgerMenu().selectCreateNewUserOption();
        pageFactory.getUsersPage().enterUserLoginEmail(testUserEmail);
        pageFactory.getUsersPage().enterUserPersonnelNumber(testPersonnelNumber);
        pageFactory.getUsersPage().enterUserFirstName(testFirstName);
        pageFactory.getUsersPage().enterUserLastName(testLastName);
        pageFactory.getUsersPage().enterUserPassword(FetchPropertiesData.getEnvironmentVariables("UserPassword"));
        boolean roleStatus = pageFactory.getUsersPage().verifyUserRoleSelected(userRole);
        if (!roleStatus) {
            pageFactory.getUsersPage().selectUserRole(userRole);
        }
        pageFactory.getUsersPage().saveUserPersonalDetails();
        pageFactory.getUsersPage().switchToPositionTab();
        pageFactory.getUsersPage().selectCategoryForUser(firstTestCategoryName);
        pageFactory.getUsersPage().enterUserPosition(testPositionName);
        pageFactory.getUsersPage().enterUserPositionStartDate(currentDate);
        pageFactory.getUsersPage().saveUserPositionDetails();
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        pageFactory.getUsersPage().filterUserByLoginName(testUserEmail);
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().verifyUserIsPresentInUsersPageTable(testUserEmail, testUserDisplayName);
    }

    public void createProject() {
        pageFactory.getSideBarHamburgerMenu().selectCreateWaterfallProjectFromCreateNewProjectShortcut();
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().enterProjectStartDate(currentDate);
        pageFactory.getProjectsGeneralTab().enterProjectEndDate(projectEndDate);
        driver.findElement(pageFactory.getProjectsGeneralTab().projectNameInputField).click();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void addUserToProject() {
        pageFactory.getProjectsTeamTab().navigateToTeamTab();
        pageFactory.getProjectsTeamTab().clickAddPeopleIcon();
        CommonMethods.waitForPageLoader();
        pageFactory.getProjectsTeamTab().addUserToProjectFromTeamTab(testUserEmail);
        CommonMethods.clickSaveButton();
        pageFactory.getProjectsTeamTab().verifyUserIsAddedToProjectFromTeamTab(testUserEmail);
    }

    public void createTasks(String taskName, String startDate, String endDate) {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().selectAddTaskOption();
        pageFactory.getProjectsTasksTab().enterTaskName(taskName);
        pageFactory.getProjectsTasksTab().enterTaskStartDate(startDate);
        pageFactory.getProjectsTasksTab().enterTaskEndDate(endDate);
        pageFactory.getProjectsTasksTab().saveTaskGeneralDetails();
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
    }

    public void addEstimateEffortFromTeamTab() {
        pageFactory.getProjectsTeamTab().navigateToTeamTab();
        pageFactory.getProjectsTeamTab().assignTaskToUser(firstTaskName, FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TaskManager_Role"));
        pageFactory.getProjectsTeamTab().assignTaskToUser(firstTaskName, testUserEmail, FetchPropertiesData.getProjectsModuleTestData("TaskMember_Role"));
        pageFactory.getProjectsTeamTab().assignTaskToUser(secondTaskName, FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TaskMember_Role"));
        pageFactory.getProjectsTeamTab().assignTaskToUser(secondTaskName, testUserEmail, FetchPropertiesData.getProjectsModuleTestData("TaskManager_Role"));
        CommonMethods.clickSaveButton();
        CommonMethods.wait(2000);
        pageFactory.getProjectsTeamTab().switchToEstimatedTimeEffort();
        pageFactory.getProjectsTeamTab().addEstimatedEffortForTask(firstTaskName, FetchPropertiesData.getEnvironmentVariables("UserEmail"), firsTaskEstimatedEffort);
        CommonMethods.clickSaveButton();
        CommonMethods.wait(4000);
        pageFactory.getProjectsTeamTab().addEstimatedEffortForTask(secondTaskName, testUserEmail, secondTaskEstimatedEffort);
        CommonMethods.clickSaveButton();
        CommonMethods.wait(4000);
    }

    public void verifyEstimateEffortFromBudgetTab(String totalEstimateEffort) {
        pageFactory.getProjectsBudgetTab().navigateToBudgetTab();
        pageFactory.getProjectsBudgetTab().verifyBottomUpTotalWorkforceHours(totalEstimateEffort);
        pageFactory.getProjectsBudgetTab().verifyBottomUpInternalTeamHours(totalEstimateEffort);
    }

    public void navigateToTask(String taskName) {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().filterTasksListByName(taskName);
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().navigateToTask(taskName);
    }

    public void verifyEstimatedEffortForFirstTask() {
        navigateToTask(firstTaskName);
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(hoursVerify_8, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(FetchPropertiesData.getCostManagementTestData("DefaultCategory"), hoursVerify_8, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(firstTestCategoryName, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_00"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(FetchPropertiesData.getEnvironmentVariables("UserName"), hoursVerify_8, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(testUserDisplayName, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_00"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
    }

    public void verifyEstimatedEffortForSecondTask() {
        navigateToTask(secondTaskName);
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(hoursVerify_5, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(FetchPropertiesData.getCostManagementTestData("DefaultCategory"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_00"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(firstTestCategoryName, hoursVerify_5, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(FetchPropertiesData.getEnvironmentVariables("UserName"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_00"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(testUserDisplayName, hoursVerify_5, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
    }

    public void filterPlanningData() {
        pageFactory.getResourcesPage().selectIntervalForPlanningData("Day");
        pageFactory.getResourcesPage().enterStartDateToViewPlanningData(currentDate);
        pageFactory.getResourcesPage().enterNumberOfDaysToViewPlanningData(numberOfDaysToViewPlanningData);
        pageFactory.getResourcesPage().clickPlanningApplyButton();
    }

    public void verifyTaskDemandTimeIntervalEstimatedEffortFromResources(String taskName, String taskOrder, String taskStartDate, String taskEndDate, String taskEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(taskEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(taskDemandTimeIntervalVerify, taskOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Time for " + taskName + " task is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the total estimate effort of " + taskEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Time for " + taskName + " task is not divided as per expected");
            Assert.fail("Time for " + taskName + " task is not divided as per expected");
        }
    }

    public void verifyTaskAllocatedTimeIntervalEstimatedEffortFromResources(String taskName, String taskOrder, String taskStartDate, String taskEndDate, String assignedEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(assignedEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(taskAllocatedTimeIntervalVerify, taskOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Allocated time for " + taskName + " task is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the assigned estimate effort of " + assignedEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Allocated time for " + taskName + " task is not divided as per expected");
            Assert.fail("Allocated time for " + taskName + " task is not divided as per expected");
        }
    }

    public void verifyTaskUnassignedTimeIntervalEstimatedEffortFromResources(String taskName, String taskOrder, String taskStartDate, String taskEndDate, String unassignedEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(unassignedEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(taskUnassignedTimeIntervalVerify, taskOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Unassigned time for " + taskName + " task is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the unassigned estimate effort of " + unassignedEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Unassigned time for " + taskName + " task is not divided as per expected");
            Assert.fail("Unassigned time for " + taskName + " task is not divided as per expected");
        }
    }

    public void verifyCategoryDemandTimeIntervalEstimatedEffortFromResources(String category, String categoryOrder, String taskStartDate, String taskEndDate, String categoryTotalEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(categoryTotalEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(categoryDemandTimeIntervalVerify, categoryOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Time for " + category + " category is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the total estimate effort of " + categoryTotalEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Time for " + category + " category is not divided as per expected");
            Assert.fail("Time for " + category + " category is not divided as per expected");
        }
    }

    public void verifyCategoryAllocatedTimeIntervalEstimatedEffortFromResources(String category, String categoryOrder, String taskStartDate, String taskEndDate, String assignedEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(assignedEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(categoryAllocatedTimeIntervalVerify, categoryOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Allocated time for " + category + " category is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the assigned estimate effort of " + assignedEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Allocated time for " + category + " category is not divided as per expected");
            Assert.fail("Allocated time for " + category + " category is not divided as per expected");
        }
    }

    public void verifyCategoryUnassignedTimeIntervalEstimatedEffortFromResources(String category, String categoryOrder, String taskStartDate, String taskEndDate, String unassignedEstimatedEffort) {
        int taskDuration = HelperMethods.countWeekdays(taskStartDate, taskEndDate);
        String timeToVerify = divideTime(unassignedEstimatedEffort, taskDuration);
        CommonMethods.wait(1000);
        List<WebElement> taskTimeInterval = driver.findElements(By.xpath(String.format(categoryUnassignedTimeIntervalVerify, categoryOrder)));
        int count = 0;
        for (WebElement ele : taskTimeInterval) {
            if (ele.getAttribute("value").equals(timeToVerify)) {
                count++;
            }
        }
        if (taskDuration == count) {
            ExtentLogger.pass("Unassigned time for " + category + " category is divided as per the task duration counting up to " + timeToVerify + " hrs to complete the unassigned estimate effort of " + unassignedEstimatedEffort + " hrs");
        } else {
            ExtentLogger.fail("Unassigned time for " + category + " category is not divided as per expected");
            Assert.fail("Unassigned time for " + category + " category is not divided as per expected");
        }
    }

    public String divideTime(String time, int count) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int totalMinutes = hours * 60 + minutes;
        int dividedMinutes = totalMinutes / count;
        int resultHours = dividedMinutes / 60;
        int resultMinutes = dividedMinutes % 60;
        String formattedHours = resultHours < 10 ? String.valueOf(resultHours) : String.format("%02d", resultHours);
        return String.format("%s:%02d", formattedHours, resultMinutes);
    }

    public void viewResourcesPlanningDataForProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToResourcesTab();
        pageFactory.getResourcesPage().switchToPlanning();
        pageFactory.getResourcesPage().switchToPlanningIframe();
        pageFactory.getResourcesPage().maximizePlanningTable();
        filterPlanningData();
        pageFactory.getResourcesPage().expandProjectFromPlanningTable(testProjectName);
    }

    public void minimizePlanningTableData() {
        pageFactory.getResourcesPage().minimizePlanningTable();
        CommonMethods.switchToDefaultContent();
    }

    public void navigateToProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(testProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().navigateToProject(testProjectName);
    }

    public void addCategoryToTaskAndAddUnassignedEstimatedEffortToCategory() {
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().addCategoryFromTaskEffortTab(secondTestCategoryName);
        pageFactory.getProjectsTasksTab().addUnassignedEffortToCategory(secondTestCategoryName, unassignedEstimateEffort_10);
        pageFactory.getProjectsTasksTab().saveTaskEffortDetails();
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().verifyCategoryUnassignedEffort(secondTestCategoryName, unassignedEstimateEffort_10, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(hoursVerify_15, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
    }

    public void addUnassignedHoursToCategoryAndAddEstimateEffortToCategoryUserAndVerifyDecreasedUnassignedHours() {
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().addUnassignedEffortToCategory(firstTestCategoryName, unassignedEstimateEffort_10);
        pageFactory.getProjectsTasksTab().saveTaskEffortDetails();
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().verifyCategoryUnassignedEffort(firstTestCategoryName, unassignedEstimateEffort_10, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(hoursVerify_25, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().assignEstimateEffortByTeamMember(testUserDisplayName, estimatedEffort_12, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().saveTaskEffortDetails();
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        pageFactory.getProjectsTasksTab().verifyCategoryUnassignedEffort(firstTestCategoryName, hoursVerify_3, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyEstimatedEffortToCategoryFromProfessionalCategoryTable(firstTestCategoryName, estimatedEffort_12, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(testUserDisplayName, estimatedEffort_12, FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
    }

    public void verifyTaskEstimatedEffortFromGanttTab(String taskName, String userName, String estimateEffort) {
        pageFactory.getProjectsGanttTab().openEditTaskPopupForTask(taskName);
        pageFactory.getProjectsGanttTab().switchToResourcesTabInGanttEditTaskPopup();
        pageFactory.getProjectsGanttTab().verifyUserEstimateEffortFromGanttEditTaskPopup(userName, estimateEffort);
        pageFactory.getProjectsGanttTab().closeGanttEditTaskPopup();
    }

    public void removeCategoryFromUserAndDeleteUser() {
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        pageFactory.getUsersPage().filterUserByLoginName(testUserEmail);
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().navigateToUserDetailsFromUsersTable(testUserEmail);
        pageFactory.getUsersPage().switchToPositionTab();
        pageFactory.getUsersPage().deleteCategoryHistoryOfUser(currentDate, firstTestCategoryName, testPositionName);
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        pageFactory.getUsersPage().filterUserByLoginName(testUserEmail);
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().deleteUser(testUserEmail);
    }

    public void deleteTask(String taskToDelete) {
        pageFactory.getProjectsTasksTab().filterTasksListByName(taskToDelete);
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().clickTaskDeleteIcon(taskToDelete);
        CommonMethods.acceptAlert();
        CommonMethods.waitForPageLoader();
    }

    public void deleteProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(testProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().clickCrossIconForProject(testProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
    }

    public void deleteCategory(String categoryToDelete) {
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().clickCategoryDeleteIconFromCategoryTable(categoryToDelete);
        CommonMethods.acceptAlert();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryIsNotPresentInTheCategoryTable(categoryToDelete);
        CommonMethods.switchToDefaultContent();
    }
}