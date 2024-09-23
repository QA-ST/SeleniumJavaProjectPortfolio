package Tests.SprintTestTickets;

import Helpers.FetchPropertiesData;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC08_VerifyPasswordUpdateIsNotRequiredWhenChangingUserEmail extends BaseClass {
    String testUserEmail = "test123@gmail.com";

    // Task ID : T-70495-24070043
    @Test(groups = {"Sprint test case"})
    public void VerifyPasswordUpdateIsNotRequiredWhenChangingUserEmail() {
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        verifyUserIsPresent(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getUsersPage().navigateToUserDetailsFromUsersTable(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getUsersPage().enterUserLoginEmail(testUserEmail);
        pageFactory.getUsersPage().saveUserPersonalDetails();
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        verifyUserIsPresent(testUserEmail);
        pageFactory.getUsersPage().navigateToUserDetailsFromUsersTable(testUserEmail);
        pageFactory.getUsersPage().enterUserLoginEmail(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getUsersPage().saveUserPersonalDetails();
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        verifyUserIsPresent(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
    }

    public void verifyUserIsPresent(String userEmail) {
        pageFactory.getUsersPage().filterUserByLoginName(userEmail);
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().verifyUserIsPresentInUsersPageTable(userEmail, FetchPropertiesData.getEnvironmentVariables("UserName"));
    }
}