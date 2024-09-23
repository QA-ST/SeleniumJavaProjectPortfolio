package Tests.SmokeTests;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC02_DeleteWaterfallProject extends BaseClass {
    String waterfallProjectName = FetchPropertiesData.getProjectsModuleTestData("TestWaterfallProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String currentDate = HelperMethods.getCurrentDate();

    @Test(priority = 2, groups = {"Smoke test case"})
    public void deleteTasksAdded() {
        deleteTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("NewFeature_TaskType"), FetchPropertiesData.getProjectsModuleTestData("InProgress_Status"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDurationVerify"));
        deleteTask(FetchPropertiesData.getProjectsModuleTestData("TaskToTestSprint"), FetchPropertiesData.getProjectsModuleTestData("Generic_TaskType"), FetchPropertiesData.getProjectsModuleTestData("ToDoStatus"), FetchPropertiesData.getProjectsModuleTestData("NormalPriority"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDurationVerify"));
    }

    @Test(priority = 3, groups = {"Smoke test case"})
    public void deleteSprintAdded() {
        pageFactory.getProjectsSprintsTab().navigateToSprintsTab();
        pageFactory.getProjectsSprintsTab().verifySprintIsPresentFromTheList(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsSprintsTab().selectSprintDeleteOption(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"));
        CommonMethods.acceptAlert();
        CommonMethods.waitForSuccessMessageSidePopup();
        CommonMethods.verifyNoRecordPresent();
    }

    @Test(priority = 1, groups = {"Smoke test case"})
    public void deletePurchaseAndBudgetAccountAdded() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        verifyProjectAddedFromList();
        pageFactory.getProjectsPage().navigateToProject(waterfallProjectName);
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().verifyPurchaseFromPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().clickDeleteIconForThePurchase(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"));
        CommonMethods.acceptAlert();
        CommonMethods.wait(2000);
        pageFactory.getProjectsPurchasesTab().verifyPurchaseFromPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().clickDeleteIconForThePurchase(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"));
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
        pageFactory.getProjectsPurchasesTab().verifyBudgetAccountFromBudgetsList(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAmount_20000"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAmount_20000"));
        pageFactory.getProjectsPurchasesTab().clickDeleteIconForBudgetAccount(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"));
        CommonMethods.acceptAlert();
        pageFactory.getProjectsPurchasesTab().verifyBudgetTableHasNoRecord();
    }

    @Test(priority = 4, groups = {"Smoke test case"})
    public void deleteProjectRevenueAdded() {
        pageFactory.getProjectsRevenueTab().navigateToRevenueTab();
        pageFactory.getProjectsRevenueTab().verifyRevenueFromTable(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"), FetchPropertiesData.getProjectsModuleTestData("Cashed_RevenueCurrentStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().clickRevenueDeleteIcon(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"));
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
        pageFactory.getProjectsRevenueTab().verifyRevenueBottomUpAndActualAmount(FetchPropertiesData.getProjectsModuleTestData("NullAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
    }

    @Test(priority = 5, groups = {"Smoke test case"})
    public void deleteWaterfallProjectAdded() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        verifyProjectAddedFromList();
        pageFactory.getProjectsPage().clickCrossIconForProject(waterfallProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
        pageFactory.getProjectsPage().checkInactiveProjectFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyLatestProjectFromProjectList(waterfallProjectName, FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"), FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
    }

    public void verifyProjectAddedFromList() {
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(waterfallProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyProjectFromProjectList(waterfallProjectName, FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"), FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
    }

    public void verifyTaskIsPresent(String taskName, String taskType, String taskStatus, String taskPriority, String startDate, String endDate, String taskDuration) {
        pageFactory.getProjectsTasksTab().filterTasksListByName(taskName);
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().verifyTaskFromTasksList(taskName, taskType, taskStatus, taskPriority, startDate, endDate, taskDuration);
    }

    public void deleteTask(String taskToDelete, String taskType, String taskStatus, String taskPriority, String startDate, String endDate, String taskDuration) {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        verifyTaskIsPresent(taskToDelete, taskType, taskStatus, taskPriority, startDate, endDate, taskDuration);
        pageFactory.getProjectsTasksTab().clickTaskDeleteIcon(taskToDelete);
        CommonMethods.acceptAlert();
        CommonMethods.waitForPageLoader();
        CommonMethods.verifyNoRecordPresent();
    }
}