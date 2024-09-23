package Tests.SprintTestTickets;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC01_AddingEstimateToTask extends BaseClass {
    String waterfallProjectName = FetchPropertiesData.getProjectsModuleTestData("TestingTaskEffortProject").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());

    // Task ID: T-70495-24070027
    @Test(groups = {"Sprint test case"})
    public void addEstimateTimeEffortTask() {
        createWaterfallProject();
        createTask();

        // Add estimate time effort to task from Project -> Team tab
        pageFactory.getProjectsTeamTab().navigateToTeamTab();
        pageFactory.getProjectsTeamTab().assignTaskToUser(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TaskManager_Role"));
        pageFactory.getProjectsTeamTab().switchToEstimatedTimeEffort();
        pageFactory.getProjectsTeamTab().addEstimatedEffortForTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_10"));
        CommonMethods.clickSaveButton();
        verifyWorkforceHoursFromBudgetTab(FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_10"));
        navigateToTask();
        pageFactory.getProjectsTasksTab().navigateToTaskTeamTab();
        pageFactory.getProjectsTasksTab().verifyTaskAssignedMember(FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TaskManagerRoleVerify"));
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        verifyTaskEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_10"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_10"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));

        // Add estimate time effort to task from Project -> Gantt tab
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        pageFactory.getProjectsGanttTab().searchTaskFromGanttSearchBar(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsGanttTab().verifyTaskFromGanttTab(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDuration"));
        pageFactory.getProjectsGanttTab().openEditTaskPopupForTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsGanttTab().switchToResourcesTabInGanttEditTaskPopup();
        pageFactory.getProjectsGanttTab().addEffortToTaskFromGanttEditTaskPopup(FetchPropertiesData.getEnvironmentVariables("UserName"), FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"));
        pageFactory.getProjectsGanttTab().saveProjectGanttDetails();
        verifyWorkforceHoursFromBudgetTab(FetchPropertiesData.getProjectsModuleTestData("EstimateEffortTimeVerify_15"));
        navigateToTask();
        pageFactory.getProjectsTasksTab().navigateToTaskEffortTab();
        verifyTaskEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));

        // Add estimate time effort to task from Task -> Effort tab
        pageFactory.getProjectsTasksTab().assignEstimateEffortByTeamMember(FetchPropertiesData.getEnvironmentVariables("UserName"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_10"), FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"));
        pageFactory.getProjectsTasksTab().saveTaskEffortDetails();
        verifyTaskEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortHours_10"), FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"));
        pageFactory.getProjectsTasksTab().verifyTaskTotalEstimatedEffort(FetchPropertiesData.getProjectsModuleTestData("TestEstimatedEffortTime_15"), FetchPropertiesData.getProjectsModuleTestData("EstimatedEffortMinutes_00"));
        verifyWorkforceHoursFromBudgetTab(FetchPropertiesData.getProjectsModuleTestData("EstimateEffortTimeVerify_15"));
        deleteProject();
    }

    public void createWaterfallProject() {
        pageFactory.getSideBarHamburgerMenu().selectCreateWaterfallProjectFromCreateNewProjectShortcut();
        pageFactory.getProjectsGeneralTab().enterProjectName(waterfallProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void createTask() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().selectAddTaskOption();
        pageFactory.getProjectsTasksTab().enterTaskName(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        pageFactory.getProjectsTasksTab().enterTaskStartDate(FetchPropertiesData.getProjectsModuleTestData("StartDate"));
        pageFactory.getProjectsTasksTab().enterTaskEndDate(FetchPropertiesData.getProjectsModuleTestData("EndDate"));
        pageFactory.getProjectsTasksTab().saveTaskGeneralDetails();
    }

    public void navigateToTask() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().filterTasksListByName(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsTasksTab().verifyTaskFromTasksList(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"), FetchPropertiesData.getProjectsModuleTestData("Generic_TaskType"), FetchPropertiesData.getProjectsModuleTestData("ToDoStatus"), FetchPropertiesData.getProjectsModuleTestData("NormalPriority"), FetchPropertiesData.getProjectsModuleTestData("StartDate"), FetchPropertiesData.getProjectsModuleTestData("EndDate"), FetchPropertiesData.getProjectsModuleTestData("TaskDurationVerify"));
        pageFactory.getProjectsTasksTab().navigateToTask(FetchPropertiesData.getProjectsModuleTestData("TestTaskName"));
    }

    public void verifyTaskEstimatedEffort(String estimatedHours, String estimatedMinutes) {
        pageFactory.getProjectsTasksTab().verifyTaskAssignedEffortByCategoryTable(estimatedHours, estimatedMinutes);
        pageFactory.getProjectsTasksTab().verifyTaskEstimatedEffortByTeamMember(FetchPropertiesData.getEnvironmentVariables("UserName"), estimatedHours, estimatedMinutes);
    }

    public void verifyWorkforceHoursFromBudgetTab(String time) {
        pageFactory.getProjectsBudgetTab().navigateToBudgetTab();
        pageFactory.getProjectsBudgetTab().verifyBottomUpTotalWorkforceHours(time);
    }

    public void deleteProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(waterfallProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().clickCrossIconForProject(waterfallProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
    }
}