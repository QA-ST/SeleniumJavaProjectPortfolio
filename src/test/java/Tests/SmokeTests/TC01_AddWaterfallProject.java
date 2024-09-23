package Tests.SmokeTests;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC01_AddWaterfallProject extends BaseClass {
    String waterfallProjectName = FetchPropertiesData.getProjectsModuleTestData("TestWaterfallProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String currentDate = HelperMethods.getCurrentDate();

    @Test(priority = 1, groups = {"Smoke test case"})
    public void createWaterfallProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        pageFactory.getProjectsPage().selectNewWaterfallProjectOption();
        pageFactory.getProjectsPage().verifyProjectTabsAreDisabled();
        pageFactory.getProjectsGeneralTab().enterProjectName(waterfallProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectApprovalStatus(FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"));
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().checkEnableSprintsCheckbox();
        pageFactory.getProjectsGeneralTab().checkEnableRevenueCheckbox();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        pageFactory.getProjectsPage().verifySprintsTabIsPresent();
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(waterfallProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyProjectFromProjectList(waterfallProjectName, FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"), FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsPage().navigateToProject(waterfallProjectName);
    }

    @Test(priority = 2, groups = {"Smoke test case"})
    public void createTask() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().selectAddTaskOption();
        pageFactory.getProjectsTasksTab().enterTaskName(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsTasksTab().selectTaskStatus(FetchPropertiesData.getProjectsModuleTestData("InProgress_Status"));
        pageFactory.getProjectsTasksTab().selectTaskType(FetchPropertiesData.getProjectsModuleTestData("NewFeature_TaskType"));
        pageFactory.getProjectsTasksTab().selectTaskPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsTasksTab().enterTaskStartDate(FetchPropertiesData.getProjectsModuleTestData("StartDate"));
        pageFactory.getProjectsTasksTab().enterTaskEndDate(FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsTasksTab().saveTaskGeneralDetails();
        pageFactory.getProjectsTasksTab().verifyTaskDuration(FetchPropertiesData.getProjectsModuleTestData("TaskDuration"));
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        verifyTaskIsPresent(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("NewFeature_TaskType"), FetchPropertiesData.getProjectsModuleTestData("InProgress_Status"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDurationVerify"));
        pageFactory.getProjectsTasksTab().navigateToTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsTasksTab().verifyProjectDatesFromTask(FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        pageFactory.getProjectsGanttTab().searchTaskFromGanttSearchBar(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsGanttTab().verifyTaskFromGanttTab(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDuration"));
    }

    @Test(priority = 3, groups = {"Smoke test case"})
    public void addSprint() {
        pageFactory.getProjectsSprintsTab().navigateToSprintsTab();
        pageFactory.getProjectsSprintsTab().clickAddSprintButton();
        pageFactory.getProjectsSprintsTab().switchToSprintFormIframe();
        pageFactory.getProjectsSprintsTab().enterSprintName(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"));
        pageFactory.getProjectsSprintsTab().selectSprintDurationType(FetchPropertiesData.getProjectsModuleTestData("Custom_SprintDurationType"));
        pageFactory.getProjectsSprintsTab().enterSprintStartDate(FetchPropertiesData.getProjectsModuleTestData("StartDate"));
        pageFactory.getProjectsSprintsTab().enterSprintEndDate(FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsSprintsTab().saveSprintDetails();
        CommonMethods.switchToDefaultContent();
        pageFactory.getProjectsSprintsTab().verifySprintIsPresentFromTheList(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().selectAddTaskOption();
        pageFactory.getProjectsTasksTab().enterTaskName(FetchPropertiesData.getProjectsModuleTestData("TaskToTestSprint"));
        pageFactory.getProjectsTasksTab().enterTaskStartDate(FetchPropertiesData.getProjectsModuleTestData("StartDate"));
        pageFactory.getProjectsTasksTab().enterTaskEndDate(FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsTasksTab().selectSprintForTask(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"));
        pageFactory.getProjectsTasksTab().saveTaskGeneralDetails();
        pageFactory.getProjectsSprintsTab().navigateToSprintsTab();
        pageFactory.getProjectsSprintsTab().verifyTaskIsPresentInSprint(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"), FetchPropertiesData.getProjectsModuleTestData("TaskToTestSprint"), FetchPropertiesData.getProjectsModuleTestData("ToDoStatus"));
        pageFactory.getProjectsSprintsTab().verifySprintDeleteIconIsDisabled(FetchPropertiesData.getProjectsModuleTestData("TestSprintName"));
        pageFactory.getProjectsGeneralTab().navigateToProjectGeneralTab();
        pageFactory.getProjectsGeneralTab().verifyEnableSprintCheckboxIsDisabled();
    }

    @Test(priority = 4, groups = {"Smoke test case"})
    public void addBudgetAccountAndPurchase() {
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().clickAddBudgetAccountButton();
        pageFactory.getProjectsPurchasesTab().enterBudgetAccountName(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"));
        pageFactory.getProjectsPurchasesTab().enterBudgetAccountAmount(FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsPurchasesTab().saveBudgetAccountDetails();
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().verifyBudgetAccountFromBudgetsList(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsPurchasesTab().selectAddNewPurchaseOption();
        pageFactory.getProjectsPurchasesTab().enterPurchaseName(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"));
        pageFactory.getProjectsPurchasesTab().selectBudgetAccountForPurchase(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"));
        pageFactory.getProjectsPurchasesTab().verifyPurchaseTotalAndAvailableBudget(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("OrderPlaced_PurchaseStatus"));
        pageFactory.getProjectsPurchasesTab().enterPurchaseStatusDate(currentDate);
        pageFactory.getProjectsPurchasesTab().enterPurchaseProjectedAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().savePurchaseDetails();
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().verifyPurchaseFromPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("OrderPlaced_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsPurchasesTab().navigateToPurchase(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"));
        CommonMethods.acceptAlert();
        pageFactory.getProjectsPurchasesTab().savePurchaseDetails();
        pageFactory.getProjectsPurchasesTab().verifyPurchaseActualAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().verifyPurchaseTotalAndAvailableBudget(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAfterPurchase"));
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().verifyPurchaseFromPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().verifyBudgetAccountFromBudgetsList(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
    }

    @Test(priority = 5, groups = {"Smoke test case"})
    public void addTaskPurchase() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().navigateToTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsTasksTab().switchToTaskPurchaseTab();
        pageFactory.getProjectsTasksTab().selectTaskAddNewPurchaseOption();
        pageFactory.getProjectsPurchasesTab().enterPurchaseName(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"));
        pageFactory.getProjectsPurchasesTab().selectBudgetAccountForPurchase(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("OrderPlaced_PurchaseStatus"));
        pageFactory.getProjectsPurchasesTab().enterPurchaseStatusDate(currentDate);
        pageFactory.getProjectsPurchasesTab().enterPurchaseProjectedAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().savePurchaseDetails();
        pageFactory.getProjectsTasksTab().verifyTaskPurchaseTotalAndAvailableBudget(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAfterPurchase"));
        pageFactory.getProjectsPurchasesTab().verifyPurchaseActualAmount(FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsTasksTab().switchToTaskPurchaseTab();
        pageFactory.getProjectsTasksTab().verifyPurchaseFromTaskPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("OrderPlaced_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsTasksTab().navigateToTaskPurchase(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"));
        CommonMethods.acceptAlert();
        pageFactory.getProjectsPurchasesTab().savePurchaseDetails();
        pageFactory.getProjectsPurchasesTab().verifyPurchaseActualAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsTasksTab().verifyTaskPurchaseTotalAndAvailableBudget(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAmount_30000"));
        pageFactory.getProjectsTasksTab().switchToTaskPurchaseTab();
        pageFactory.getProjectsTasksTab().verifyPurchaseFromTaskPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsPurchasesTab().navigateToPurchasesTab();
        pageFactory.getProjectsPurchasesTab().verifyBudgetAccountFromBudgetsList(FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAmount_20000"), FetchPropertiesData.getProjectsModuleTestData("AvailableBudgetAmount_20000"));
        pageFactory.getProjectsPurchasesTab().verifyPurchaseFromPurchaseList(FetchPropertiesData.getProjectsModuleTestData("TestTaskPurchaseName"), FetchPropertiesData.getProjectsModuleTestData("Paid_PurchaseStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestBudgetAccountName"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
    }

    @Test(priority = 6, groups = {"Smoke test case"})
    public void addTaskProgress() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().navigateToTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsTasksTab().switchToTaskProgressTab();
        pageFactory.getProjectsTasksTab().clickAddTaskProgressReportButton();
        pageFactory.getProjectsProgressTab().selectProgressAssessmentType(FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"));
        pageFactory.getProjectsTasksTab().enterTaskProgressShortDescription(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"));
        pageFactory.getProjectsTasksTab().enterTaskProgressCompletePercentage(FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"));
        pageFactory.getProjectsTasksTab().enterTaskProgressReportDate(currentDate);
        pageFactory.getProjectsTasksTab().saveTaskProgressDetails();
        pageFactory.getProjectsTasksTab().verifyTaskProgressReportFromTaskProgressTable(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"), FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"), FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"), currentDate);
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().filterTasksListByName(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().verifyTaskProgressFromTaskList(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"), FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"));
    }

    @Test(priority = 7, groups = {"Smoke test case"})
    public void addRevenue() {
        pageFactory.getProjectsRevenueTab().navigateToRevenueTab();
        pageFactory.getProjectsRevenueTab().selectAddNewRevenueOption();
        pageFactory.getProjectsRevenueTab().enterRevenueName(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("Planned_RevenueCurrentStatus"));
        pageFactory.getProjectsRevenueTab().enterRevenueProjectedAmount(FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().saveRevenueDetails();
        pageFactory.getProjectsRevenueTab().verifyRevenueFromTable(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"), FetchPropertiesData.getProjectsModuleTestData("Planned_RevenueCurrentStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsRevenueTab().verifyRevenueBottomUpAndActualAmount(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("NullAmount"));
        pageFactory.getProjectsRevenueTab().navigateToRevenue(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"));
        pageFactory.getProjectsPage().selectCurrentStatus(FetchPropertiesData.getProjectsModuleTestData("Cashed_RevenueCurrentStatus"));
        CommonMethods.acceptAlert();
        pageFactory.getProjectsRevenueTab().verifyActualAmountFromRevenueDetailsPage(FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().saveRevenueDetails();
        pageFactory.getProjectsRevenueTab().verifyRevenueFromTable(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"), FetchPropertiesData.getProjectsModuleTestData("Cashed_RevenueCurrentStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().verifyRevenueBottomUpAndActualAmount(FetchPropertiesData.getProjectsModuleTestData("TestAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().navigateToRevenue(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"));
        pageFactory.getProjectsRevenueTab().enterRevenueProjectedAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"));
        pageFactory.getProjectsRevenueTab().saveRevenueDetails();
        pageFactory.getProjectsRevenueTab().verifyRevenueFromTable(FetchPropertiesData.getProjectsModuleTestData("TestRevenueName"), FetchPropertiesData.getProjectsModuleTestData("Cashed_RevenueCurrentStatus"), currentDate, FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
        pageFactory.getProjectsRevenueTab().verifyRevenueBottomUpAndActualAmount(FetchPropertiesData.getProjectsModuleTestData("TestProjectedAmount"), FetchPropertiesData.getProjectsModuleTestData("TestAmount"));
    }

    public void verifyTaskIsPresent(String taskName, String taskType, String taskStatus, String taskPriority, String startDate, String endDate, String taskDuration) {
        pageFactory.getProjectsTasksTab().filterTasksListByName(taskName);
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().verifyTaskFromTasksList(taskName, taskType, taskStatus, taskPriority, startDate, endDate, taskDuration);
    }
}