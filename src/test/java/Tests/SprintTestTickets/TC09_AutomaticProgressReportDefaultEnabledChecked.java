package Tests.SprintTestTickets;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC09_AutomaticProgressReportDefaultEnabledChecked extends BaseClass {
    String testUserEmail = FetchPropertiesData.getUserManagementTestData("TestGanttProgressReporterUserEmail").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testPersonnelNumber = FetchPropertiesData.getUserManagementTestData("TestPersonalNumber");
    String testFirstName = FetchPropertiesData.getUserManagementTestData("TestUserFirstName");
    String testLastName = FetchPropertiesData.getUserManagementTestData("TestUserLastName");
    String testUserDisplayName = FetchPropertiesData.getUserManagementTestData("TestUserDisplayName");
    String userRole = FetchPropertiesData.getUserManagementTestData("FullAccessUserRole");
    String testProjectName = FetchPropertiesData.getProjectsModuleTestData("TestGanttAutomaticProgressReportProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());

    // Task ID : T-70495-24070052
    @Test(groups = {"Sprint test case"})
    public void automaticProgressReportDefaultEnabledChecked() {
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        createNewUser();
        pageFactory.getHeader().userLogoutFromApp();
        loginToWebsite(testUserEmail, FetchPropertiesData.getEnvironmentVariables("UserPassword"));
        CommonMethods.navigateThroughTheNewAccountUnderstandingSteps();
        createNewProject();
        pageFactory.getProjectsGanttTab().navigateToGanttTab();
        pageFactory.getProjectsGanttTab().verifyCreateAutomaticProgressReportCheckboxIsChecked();
        deleteProject();
        pageFactory.getHeader().userLogoutFromApp();
        loginToWebsite(FetchPropertiesData.getEnvironmentVariables("UserEmail"), FetchPropertiesData.getEnvironmentVariables("UserPassword"));
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        filterUserTableAndVerifyUser();
        pageFactory.getUsersPage().deleteUser(testUserEmail);
    }

    public void createNewUser() {
        pageFactory.getUsersPage().clickCreateNewUserButton();
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
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        filterUserTableAndVerifyUser();
    }

    public void filterUserTableAndVerifyUser() {
        CommonMethods.clickResetButton();
        pageFactory.getUsersPage().filterUserByLoginName(testUserEmail);
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().verifyUserIsPresentInUsersPageTable(testUserEmail, testUserDisplayName);
    }

    public void createNewProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsPage().selectNewWaterfallProjectOption();
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void loginToWebsite(String userEmail, String userPassword) {
        pageFactory.getLoginPage().enterUserEmail(userEmail);
        pageFactory.getLoginPage().enterUserPassword(userPassword);
        pageFactory.getLoginPage().clickLoginButton();
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
}