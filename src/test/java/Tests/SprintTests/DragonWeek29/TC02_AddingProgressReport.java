package Tests.SprintTests.DragonWeek29;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC02_AddingProgressReport extends BaseClass {
    String testProjectName = FetchPropertiesData.getProjectsModuleTestData("TestingProgressProject").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String currentDate = HelperMethods.getCurrentDate();

    // Task ID : T-70495-24070039
    @Test(groups = {"Sprint test case"})
    public void addProgressReportToProject() {
        createTestProject();
        pageFactory.getProjectsProgressTab().navigateToProjectProgressTab();
        pageFactory.getProjectsProgressTab().clickAddProgressReportButton();
        pageFactory.getProjectsProgressTab().selectProgressAssessmentType(FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"));
        pageFactory.getProjectsProgressTab().enterProgressShortDescription(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"));
        pageFactory.getProjectsProgressTab().enterProgressPercentageCompleted(FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"));
        pageFactory.getProjectsProgressTab().saveProjectProgressReport();
        pageFactory.getProjectsProgressTab().navigateToProjectProgressTab();
        pageFactory.getProjectsProgressTab().verifyLastProgressReport(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"), currentDate, FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"));
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        verifyProjectProgressDetails(FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"), FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"));
        pageFactory.getProjectsPage().navigateToProject(testProjectName);
        pageFactory.getProjectsProgressTab().navigateToProjectProgressTab();
        pageFactory.getProjectsProgressTab().openProjectProgressReport();
        pageFactory.getProjectsProgressTab().verifyProgressFromProgressList(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"), FetchPropertiesData.getProjectsModuleTestData("ProgressPercentageCompleted"), FetchPropertiesData.getProjectsModuleTestData("AssessmentType_Good"), currentDate, FetchPropertiesData.getEnvironmentVariables("UserName"));
        pageFactory.getProjectsProgressTab().clickDeleteIconForProgress(FetchPropertiesData.getProjectsModuleTestData("ProgressShortDescription"));
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
        pageFactory.getProjectsProgressTab().closeProjectProgressMenu();
        pageFactory.getProjectsProgressTab().navigateToProjectProgressTab();
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        verifyProjectProgressDetails("", "");
        deleteTestProject();
    }

    public void createTestProject() {
        pageFactory.getSideBarHamburgerMenu().selectCreateWaterfallProjectFromCreateNewProjectShortcut();
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void verifyProjectProgressDetails(String assessmentType, String progressPercentage) {
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(testProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().verifyProjectProgressDetailsFromProjectsList(testProjectName, assessmentType, progressPercentage);
    }

    public void deleteTestProject() {
        pageFactory.getProjectsPage().clickCrossIconForProject(testProjectName);
        CommonMethods.acceptAlert();
        CommonMethods.verifyNoRecordPresent();
    }
}