package Tests.SprintTestTickets;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC07_VerifyCategoryAmountFromMyProfessionalPage extends BaseClass {
    String generalStandardInputCost;
    String standardBillRate;

    // Task ID : T-70495-24070050
    @Test(groups = {"Sprint test case"})
    public void verifyCategoryAmountFromMyProfessionalPage() {
        pageFactory.getSideBarHamburgerMenu().navigateToCategoriesAndRatesPage();
        pageFactory.getCategoriesAndRatesPage().switchToCategoriesStandardCostTab();
        fetchTheCategoryCostValues();
        pageFactory.getSideBarHamburgerMenu().navigateToUsersPage();
        pageFactory.getUsersPage().filterUserByLoginName(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getUsersPage().clickUserPageFilterApplyButton();
        pageFactory.getUsersPage().navigateToUserDetailsFromUsersTable(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getUsersPage().switchToPositionTab();
        pageFactory.getUsersPage().selectCategoryForUser(FetchPropertiesData.getCostManagementTestData("DefaultCategory"));
        pageFactory.getUsersPage().verifyCategoryInputOutputCost(generalStandardInputCost, standardBillRate);
    }

    public void fetchTheCategoryCostValues() {
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        String[] categoryCost = pageFactory.getCategoriesAndRatesPage().fetchCategoryCostFromCategoryCostTable(FetchPropertiesData.getCostManagementTestData("DefaultCategory"));
        generalStandardInputCost = categoryCost[1];
        standardBillRate = categoryCost[2];
        CommonMethods.switchToDefaultContent();
    }
}