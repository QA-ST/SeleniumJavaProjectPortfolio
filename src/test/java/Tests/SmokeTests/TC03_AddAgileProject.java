package Tests.SmokeTests;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC03_AddAgileProject extends BaseClass {
    String agileProjectName = FetchPropertiesData.getProjectsModuleTestData("TestAgileProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());

    @Test(groups = {"Smoke test case"})
    public void createAgileProjectAndDeleteCreatedProject() {
        pageFactory.getSideBarHamburgerMenu().selectCreateAgileProjectFromCreateNewProjectShortcut();
        pageFactory.getProjectsPage().verifyProjectTabsAreDisabled();
        pageFactory.getProjectsGeneralTab().enterProjectName(agileProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectApprovalStatus(FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"));
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(agileProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyProjectFromProjectList(agileProjectName, FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"), FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"));

        // Delete agile project created
        pageFactory.getProjectsPage().clickCrossIconForProject(agileProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
        pageFactory.getProjectsPage().checkInactiveProjectFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyLatestProjectFromProjectList(agileProjectName, FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"), FetchPropertiesData.getProjectsModuleTestData("Approved_ApprovalStatus"), FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
    }
}