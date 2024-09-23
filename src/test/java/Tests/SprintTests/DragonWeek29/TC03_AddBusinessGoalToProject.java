package Tests.SprintTests.DragonWeek29;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC03_AddBusinessGoalToProject extends BaseClass {
    String businessPlanName = FetchPropertiesData.getGoalsAndProcessesTestData("TestBusinessPlanName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String businessGoalName = FetchPropertiesData.getGoalsAndProcessesTestData("TestBusinessGoalName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testProjectName = FetchPropertiesData.getGoalsAndProcessesTestData("TestingBusinessGoalProjectName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());

    String currentDate = HelperMethods.getCurrentDate();

    @Test(groups = {"Sprint test case"})
    public void addBusinessGoalAndAddToProject() {
        pageFactory.getSideBarHamburgerMenu().navigateToBusinessGoalsPage();
        pageFactory.getBusinessGoalsPage().clickAddNewBusinessPlanButton();
        pageFactory.getBusinessGoalsPage().enterBusinessPlanName(businessPlanName);
        pageFactory.getBusinessGoalsPage().enterBusinessPlanStartDate(currentDate);
        pageFactory.getBusinessGoalsPage().enterBusinessPlanEndDate(currentDate);
        pageFactory.getBusinessGoalsPage().saveBusinessGoalDetails();
        pageFactory.getBusinessGoalsPage().verifyBusinessPlanFromList(businessPlanName, currentDate, currentDate);
        pageFactory.getBusinessGoalsPage().clickAddNewBusinessGoalButton();
        pageFactory.getBusinessGoalsPage().selectBusinessPlanForGoal(businessPlanName);
        pageFactory.getBusinessGoalsPage().enterBusinessGoalName(businessGoalName);
        pageFactory.getBusinessGoalsPage().enterBusinessGoalDescription(FetchPropertiesData.getGoalsAndProcessesTestData("TestBusinessGoalDescription"));
        pageFactory.getBusinessGoalsPage().saveBusinessGoalDetails();
        pageFactory.getBusinessGoalsPage().verifyBusinessGoalDetailsFromTheList(businessGoalName, businessPlanName, FetchPropertiesData.getGoalsAndProcessesTestData("TestBusinessGoalDescription"));
        pageFactory.getSideBarHamburgerMenu().navigateToProjectsPage();
        addProjectAndVerifyBusinessGoalCanBeLinkedToProject();
        pageFactory.getProjectsGeneralTab().removeBusinessGoalFromProject();
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        deleteProject();
        pageFactory.getSideBarHamburgerMenu().navigateToBusinessGoalsPage();
        deleteBusinessGoal();
        deleteBusinessPlan();
    }

    private void addProjectAndVerifyBusinessGoalCanBeLinkedToProject() {
        pageFactory.getProjectsPage().selectNewWaterfallProjectOption();
        pageFactory.getProjectsGeneralTab().enterProjectName(testProjectName);
        pageFactory.getProjectsGeneralTab().selectProjectType(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getProjectsGeneralTab().selectBusinessGoalForProject(businessPlanName, businessGoalName);
        pageFactory.getProjectsGeneralTab().saveProjectGeneralDetails();
        CommonMethods.verifyValidationMessageIsNotDisplayed();
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

    public void deleteBusinessGoal() {
        pageFactory.getBusinessGoalsPage().clickBusinessGoalDeleteIcon(businessGoalName, businessPlanName);
        CommonMethods.acceptAlert();
        CommonMethods.waitForValidationMessageSidePopup();
        pageFactory.getBusinessGoalsPage().verifyBusinessGoalIsNotPresentInList(businessGoalName);
    }

    public void deleteBusinessPlan() {
        pageFactory.getBusinessGoalsPage().clickBusinessPlanDeleteIcon(businessPlanName);
        CommonMethods.acceptAlert();
        CommonMethods.waitForValidationMessageSidePopup();
        pageFactory.getBusinessGoalsPage().verifyBusinessPlanIsNotPresentInTheList(businessPlanName);
    }
}