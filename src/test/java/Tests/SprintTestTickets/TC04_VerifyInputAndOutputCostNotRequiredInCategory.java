package Tests.SprintTestTickets;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC04_VerifyInputAndOutputCostNotRequiredInCategory extends BaseClass {
    String testCategoryName = FetchPropertiesData.getCostManagementTestData("TestCategoryName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String startDate = HelperMethods.getCurrentDate();
    String nullCostValue = FetchPropertiesData.getCostManagementTestData("CategoryNullAmount");
    String currency = FetchPropertiesData.getCostManagementTestData("TestCurrency_Eur");

    // Task ID : T-70495-24070051
    @Test(groups = {"Sprint test case"})
    public void verifyInputAndOutputCostNotMandatoryInCategoryStandard() {
        pageFactory.getSideBarHamburgerMenu().navigateToCategoriesAndRatesPage();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().clickAddNewCategoryButton();
        pageFactory.getCategoriesAndRatesPage().enterCategoryName(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        waitForValidationMessage();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryFromCategoryTable(testCategoryName);
        CommonMethods.switchToDefaultContent();
        pageFactory.getCategoriesAndRatesPage().switchToCategoriesStandardCostTab();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().selectCategoryFromCategoryStandardCostDropdown(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().enterStartDateForCategoryCost(startDate);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        waitForValidationMessage();
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().selectEmployeeCategoryType();
        pageFactory.getCategoriesAndRatesPage().selectCategoryFromCategoryStandardCostDropdown(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().enterStartDateForCategoryCost(startDate);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        waitForValidationMessage();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryCostDetailsForCategoryCostTable(testCategoryName, nullCostValue, nullCostValue, nullCostValue);
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().verifyCategoryStandardCostHistoryFromList(startDate, nullCostValue, currency, nullCostValue, currency);
        pageFactory.getCategoriesAndRatesPage().selectEmployeeCategoryType();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryStandardCostHistoryFromList(startDate, nullCostValue, currency, nullCostValue, currency);
        CommonMethods.switchToDefaultContent();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryTab();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().clickCategoryDeleteIconFromCategoryTable(testCategoryName);
        CommonMethods.acceptAlert();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryIsNotPresentInTheCategoryTable(testCategoryName);
        CommonMethods.switchToDefaultContent();
    }

    public void waitForValidationMessage() {
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
    }
}