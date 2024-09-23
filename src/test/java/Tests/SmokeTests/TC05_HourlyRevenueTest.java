package Tests.SmokeTests;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC05_HourlyRevenueTest extends BaseClass {
    String testProjectStatusName = FetchPropertiesData.getParametersTestData("TestHourlyRevenueProjectStatusName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testProjectTypeName = FetchPropertiesData.getParametersTestData("TestHourlyRevenueProjectTypeName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String projectTypeDescription = FetchPropertiesData.getParametersTestData("TestProjectTypeDescription");
    String currentDate = HelperMethods.getCurrentDate();
    String testProjectName = FetchPropertiesData.getProjectsModuleTestData("TestHourlyRevenueProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testTaskName = FetchPropertiesData.getProjectsModuleTestData("TestHourlyRevenueTask").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());

    @Test(groups = {"Smoke test case"})
    public void hourlyRevenueTest() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectParametersPage();
        addProjectStatus();
        addProjectType();
        addProject();
        addTaskAndAssignToUser();
        deleteProject();
        pageFactory.getSideBarHamburgerMenu().navigateToProjectParametersPage();
        deleteProjectStatus();
        deleteProjectType();
    }

    public void addProjectStatus() {
        pageFactory.getProjectParametersPage().switchToProjectStatus();
        pageFactory.getProjectParametersPage().clickAddNewStatusButton();
        pageFactory.getProjectParametersPage().enterProjectStatusName(testProjectStatusName);
        pageFactory.getProjectParametersPage().checkEnableTimeReportingForProjectStatus();
        pageFactory.getProjectParametersPage().saveProjectStatusDetails();
        pageFactory.getProjectParametersPage().selectMaximumPaginationForProjectStatusTable();
        pageFactory.getProjectParametersPage().verifyProjectStatusFromList(testProjectStatusName);
        pageFactory.getProjectParametersPage().navigateToProjectStatusDetailPage(testProjectStatusName);
        pageFactory.getProjectParametersPage().selectDestinationStatusForProjectStatus(FetchPropertiesData.getParametersTestData("ClosedProjectStatus"));
        pageFactory.getProjectParametersPage().saveProjectStatusDetails();
        pageFactory.getProjectParametersPage().verifyDestinationStatusForProjectStatusFromList(testProjectStatusName, FetchPropertiesData.getParametersTestData("ClosedProjectStatus"));
        pageFactory.getProjectParametersPage().navigateToProjectStatusDetailPage(FetchPropertiesData.getParametersTestData("InitialProjectStatus"));
        pageFactory.getProjectParametersPage().selectDestinationStatusForProjectStatus(testProjectStatusName);
        pageFactory.getProjectParametersPage().saveProjectStatusDetails();
        pageFactory.getProjectParametersPage().verifyDestinationStatusForProjectStatusFromList(FetchPropertiesData.getParametersTestData("InitialProjectStatus"), testProjectStatusName);
    }

    public void addProjectType() {
        pageFactory.getProjectParametersPage().switchToProjectTypeTab();
        pageFactory.getProjectParametersPage().clickAddNewProjectTypeButton();
        pageFactory.getProjectParametersPage().enterProjectTypeName(testProjectTypeName);
        pageFactory.getProjectParametersPage().enterProjectTypeDescription(projectTypeDescription);
        pageFactory.getProjectParametersPage().checkEnableRevenueForProjectType();
        pageFactory.getProjectParametersPage().checkAllowNonBillingHoursForProjectType();
        pageFactory.getProjectParametersPage().saveProjectTypeDetails();
        pageFactory.getProjectParametersPage().selectMaximumPaginationForProjectTypeTable();
        pageFactory.getProjectParametersPage().verifyProjectTypeFromList(testProjectTypeName, projectTypeDescription);
    }

    public void addProject() {
        pageFactory.getSideBarHamburgerMenu().selectCreateWaterfallProjectFromCreateNewProjectShortcut();
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(testProjectTypeName);
        pageFactory.getProjectsGeneralTab().checkEnableSprintsCheckbox();
        pageFactory.getProjectsGeneralTab().checkEnableRevenueCheckbox();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        pageFactory.getProjectsGeneralTab().selectProjectStatus(testProjectStatusName);
        pageFactory.getProjectsGeneralTab().saveProjectStatusDetails();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void addTaskAndAssignToUser() {
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTasksTab().selectAddTaskOption();
        pageFactory.getProjectsTasksTab().enterTaskName(testTaskName);
        pageFactory.getProjectsTasksTab().enterTaskStartDate(currentDate);
        pageFactory.getProjectsTasksTab().enterTaskEndDate(currentDate);
        pageFactory.getProjectsTasksTab().saveTaskGeneralDetails();
        pageFactory.getProjectsTasksTab().navigateToTasksTab();
        pageFactory.getProjectsTeamTab().navigateToTeamTab();
        pageFactory.getProjectsTeamTab().assignTaskToUser(testTaskName, FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getProjectsModuleTestData("TaskMember_Role"));
        CommonMethods.clickSaveButton();
        CommonMethods.wait(2000);
    }

    public void deleteProjectStatus() {
        pageFactory.getProjectParametersPage().switchToProjectStatus();
        pageFactory.getProjectParametersPage().selectMaximumPaginationForProjectStatusTable();
        pageFactory.getProjectParametersPage().clickProjectStatusDeleteIcon(testProjectStatusName);
        CommonMethods.acceptAlert();
        CommonMethods.waitForSuccessMessageSidePopup();
    }

    public void deleteProjectType() {
        pageFactory.getProjectParametersPage().switchToProjectTypeTab();
        pageFactory.getProjectParametersPage().selectMaximumPaginationForProjectTypeTable();
        pageFactory.getProjectParametersPage().clickProjectTypeDeleteIcon(testProjectTypeName);
        CommonMethods.acceptAlert();
        CommonMethods.waitForSuccessMessageSidePopup();
    }

    public void deleteProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(testProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().navigateToProject(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectStatus(FetchPropertiesData.getParametersTestData("ClosedProjectStatus"));
        pageFactory.getProjectsGeneralTab().saveProjectStatusDetails();
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        pageFactory.getProjectsPage().clickCrossIconForProject(testProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
    }
}