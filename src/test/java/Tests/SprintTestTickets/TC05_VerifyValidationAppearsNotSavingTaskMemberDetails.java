package Tests.SprintTestTickets;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC05_VerifyValidationAppearsNotSavingTaskMemberDetails extends BaseClass {
    String testProjectName = FetchPropertiesData.getProjectsModuleTestData("TestAgileProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String taskName = FetchPropertiesData.getProjectsModuleTestData("TestTaskName");

    // Task ID : T-70495-24070042
    @Test(groups = {"Sprint test case"})
    public void verifyValidationMessageIsDisplayedNotSavingTaskMemberDetails() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        pageFactory.getProjectsPage().selectNewAgileProjectOption();
        createProject();
        pageFactory.getProjectsBoardTab().navigateToBoardTab();
        pageFactory.getProjectsBoardTab().clickCreateTaskButtonInBoard();
        pageFactory.getProjectsBoardTab().enterTaskNameInTaskPopup(taskName);
        pageFactory.getProjectsBoardTab().clickTaskPopupSaveButton();
        pageFactory.getProjectsBoardTab().closeTaskPopup();
        pageFactory.getProjectsBoardTab().navigateToBoardTab();
        pageFactory.getProjectsBoardTab().openTaskPopup(taskName);
        pageFactory.getProjectsBoardTab().selectUserToAddToTaskFromBoardTaskPopup(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getProjectsBoardTab().clickTaskPopupSaveButton();
        CommonMethods.verifyErrorValidationMessageIsDisplayed();
        pageFactory.getProjectsBoardTab().closeTaskPopup();
        deleteProject();
    }

    public void createProject() {
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectPriority(FetchPropertiesData.getProjectsModuleTestData("LowPriority"));
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().uncheckEnableSprintCheckbox();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
    }

    public void deleteProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        CommonMethods.clickViewMoreFilterOption();
        pageFactory.getProjectsPage().filterProjectListByName(testProjectName);
        pageFactory.getProjectsPage().checkActiveProjectsFilter();
        CommonMethods.clickFilterApplyButton();
        pageFactory.getProjectsPage().clickCrossIconForProject(testProjectName);
        CommonMethods.acceptAlert();
    }
}