package Tests.SmokeTests;

import Helpers.CommonMethods;
import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class TC06_OverallTesting extends BaseClass {
    String testOrganizationUnitName = FetchPropertiesData.getOrganizationTestData("TestOrganizationUnitName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String organizationUnitCode = FetchPropertiesData.getOrganizationTestData("TestOrganizationUnitCode").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testWorkGroupName = FetchPropertiesData.getOrganizationTestData("TestWorkGroupName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String workGroupCode = FetchPropertiesData.getOrganizationTestData("TestWorkGroupCode");
    String currentDate = HelperMethods.getCurrentDate();
    String testPriorityName = FetchPropertiesData.getParametersTestData("TestProjectPriorityName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testPriorityDescription = FetchPropertiesData.getParametersTestData("TestProjectPriorityDescription");
    String testProjectApprovalName = FetchPropertiesData.getParametersTestData("TestProjectApprovalName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testProjectApprovalDescription = FetchPropertiesData.getParametersTestData("TestProjectApprovalDescription");
    String testActivityPriorityName = FetchPropertiesData.getParametersTestData("TestActivityPriorityName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testActivityPriorityDescription = FetchPropertiesData.getParametersTestData("TestActivityPriorityDescription");
    String testActivityStatusName = FetchPropertiesData.getParametersTestData("TestActivityStatusName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testActivityStatusDescription = FetchPropertiesData.getParametersTestData("TestActivityStatusDescription");
    String testActivityTypeName = FetchPropertiesData.getParametersTestData("TestActivityTypeName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testActivityTypeDescription = FetchPropertiesData.getParametersTestData("TestActivityTypeDescription");
    String testCustomFieldName = FetchPropertiesData.getCustomizationTestData("TestCustomFieldName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testCustomFieldType = FetchPropertiesData.getCustomizationTestData("TestCustomFieldType_Number");
    String testCurrency = FetchPropertiesData.getCostManagementTestData("TestCurrency");
    String testCurrencyName = FetchPropertiesData.getCostManagementTestData("TestCurrencyName");
    String testCurrencySymbol = FetchPropertiesData.getCostManagementTestData("TestCurrencySymbol");
    String exchangeRate = FetchPropertiesData.getCostManagementTestData("TestExchangeRate");
    String testCategoryName = FetchPropertiesData.getCostManagementTestData("OverallTestCategoryName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String globalCategoryType = FetchPropertiesData.getCostManagementTestData("InternalCategoryType");
    String testCategoryCost = FetchPropertiesData.getCostManagementTestData("TestCategoryInputCost_20");
    String testCategoryEmployeeCost = FetchPropertiesData.getCostManagementTestData("TestCategoryEmployeeCost_40");
    String testCategoryCost_400 = FetchPropertiesData.getCostManagementTestData("TestCategoryStandardBillRate_400");
    String currency = FetchPropertiesData.getCostManagementTestData("TestCurrency_Eur");
    String testProviderName = FetchPropertiesData.getCostManagementTestData("TestProviderName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testProviderEmail = FetchPropertiesData.getCostManagementTestData("TestProviderEmail").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String testProviderNumber = FetchPropertiesData.getUserManagementTestData("TestPersonalNumber");
    String testProviderCity = FetchPropertiesData.getCostManagementTestData("TestProviderCity");
    String testWorkingHourName = FetchPropertiesData.getUserManagementTestData("OverallTestWorkingHourName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String workingHour = FetchPropertiesData.getUserManagementTestData("WorkingHours_8");
    String calenderName = FetchPropertiesData.getUserManagementTestData("OverallTestCalendarName").replace("{timestamp}", HelperMethods.getTimestamp_hh_mm_ss());
    String calendarDescription = FetchPropertiesData.getUserManagementTestData("TestCalendarDescription");
    String[] workingDays = {FetchPropertiesData.getUserManagementTestData("WeekDay_Monday"), FetchPropertiesData.getUserManagementTestData("WeekDay_Tuesday"), FetchPropertiesData.getUserManagementTestData("WeekDay_Wednesday"), FetchPropertiesData.getUserManagementTestData("WeekDay_Thursday"), FetchPropertiesData.getUserManagementTestData("WeekDay_Friday")};

    @Test(groups = {"Smoke test case"})
    public void overallTesting() {
//        addOrganizationUnit();
//        addWorkGroup();
//        pageFactory.getSideBarHamburgerMenu().navigateToProjectParametersPage();
//        addProjectPriority();
//        addProjectApproval();
//        pageFactory.getSideBarHamburgerMenu().navigateToActivityParameterPage();
//        addActivityPriority();
//        addActivityStatus();
//        addActivityType();
//        pageFactory.getSideBarHamburgerMenu().navigateToCustomFieldsPage();
//        addCustomField();
//        pageFactory.getSideBarHamburgerMenu().navigateToMulticurrencyPage();
//        addMultiCurrencyAndExchangeRate();
//        pageFactory.getSideBarHamburgerMenu().navigateToCategoriesAndRatesPage();
//        addCategory();
//        updateCategoryTypeStandardCostRate();
//        pageFactory.getCategoriesAndRatesPage().switchToCategoriesStandardCostTab();
//        updateCategoryGeneralAndEmployeeStandardCost();
//        pageFactory.getSideBarHamburgerMenu().navigateToProvidersPage();
//        addProviders();
//        pageFactory.getSideBarHamburgerMenu().navigateToWorkingHoursPage();
//        addWorkingHour();
        pageFactory.getSideBarHamburgerMenu().navigateToCalendarsPage();
        addCalendar();
    }

    public void addOrganizationUnit() {
        pageFactory.getSideBarHamburgerMenu().navigateToOrganizationUnitsPage();
        pageFactory.getOrganizationUnitsPage().clickAddNewOrganizationUnitButton();
        pageFactory.getOrganizationUnitsPage().enterOrganizationUnitName(testOrganizationUnitName);
        pageFactory.getOrganizationUnitsPage().enterOrganizationUnitCode(organizationUnitCode);
        pageFactory.getOrganizationUnitsPage().saveOrganizationUnitDetails();
        pageFactory.getOrganizationUnitsPage().filterOrganizationUnitList(testOrganizationUnitName);
        pageFactory.getOrganizationUnitsPage().verifyOrganizationUnitFromList(testOrganizationUnitName, organizationUnitCode, currentDate);
    }

    public void addWorkGroup() {
        pageFactory.getSideBarHamburgerMenu().navigateToWorkGroupsPage();
        pageFactory.getWorkGroupsPage().clickAddNewWorkGroupButton();
        pageFactory.getWorkGroupsPage().enterWorkGroupName(testWorkGroupName);
        pageFactory.getWorkGroupsPage().enterWorkGroupCode(workGroupCode);
        pageFactory.getWorkGroupsPage().saveWorkGroupDetails();
        pageFactory.getWorkGroupsPage().filterWorkGroupTableByName(testWorkGroupName);
        pageFactory.getWorkGroupsPage().verifyWorkGroupFromList(testWorkGroupName, workGroupCode, currentDate);
    }

    public void addProjectPriority() {
        pageFactory.getProjectParametersPage().clickAddProjectPriorityButton();
        pageFactory.getProjectParametersPage().enterProjectPriorityName(testPriorityName);
        pageFactory.getProjectParametersPage().enterProjectPriorityDescription(testPriorityDescription);
        pageFactory.getProjectParametersPage().saveProjectPriorityDetails();
        pageFactory.getProjectParametersPage().verifyProjectPriorityFromPriorityTable(testPriorityName, testPriorityDescription);
        pageFactory.getProjectParametersPage().selectDefaultProjectPriority(testPriorityName);
    }

    public void addProjectApproval() {
        pageFactory.getProjectParametersPage().switchToProjectApprovalTab();
        pageFactory.getProjectParametersPage().clickAddNewProjectApprovalButton();
        pageFactory.getProjectParametersPage().enterProjectApprovalName(testProjectApprovalName);
        pageFactory.getProjectParametersPage().enterProjectApprovalDescription(testProjectApprovalDescription);
        pageFactory.getProjectParametersPage().saveProjectApprovalDetails();
        pageFactory.getProjectParametersPage().verifyProjectApprovalFromApprovalTable(testProjectApprovalName, testProjectApprovalDescription);
    }

    public void addActivityPriority() {
        pageFactory.getActivityParametersPage().clickAddNewActivityPriorityButton();
        pageFactory.getActivityParametersPage().enterActivityPriorityName(testActivityPriorityName);
        pageFactory.getActivityParametersPage().enterActivityPriorityDescription(testActivityPriorityDescription);
        pageFactory.getActivityParametersPage().saveActivityDescriptionDetails();
        pageFactory.getActivityParametersPage().verifyActivityPriorityFromList(testActivityPriorityName, testActivityPriorityDescription);
    }

    public void addActivityStatus() {
        pageFactory.getActivityParametersPage().switchToActivityStatusTab();
        pageFactory.getActivityParametersPage().clickAddNewActivityStatusButton();
        pageFactory.getActivityParametersPage().enterActivityStatusName(testActivityStatusName);
        pageFactory.getActivityParametersPage().enterActivityStatusDescription(testActivityStatusDescription);
        pageFactory.getActivityParametersPage().selectStatusTypeForActivityStatus(FetchPropertiesData.getProjectsModuleTestData("InProgress_Status"));
        pageFactory.getActivityParametersPage().saveActivityStatusDetails();
        pageFactory.getActivityParametersPage().verifyActivityStatusDetails(testActivityStatusName, testActivityStatusDescription);
    }

    public void addActivityType() {
        pageFactory.getActivityParametersPage().switchToActivityTypeTab();
        pageFactory.getActivityParametersPage().clickAddNewActivityTypeButton();
        pageFactory.getActivityParametersPage().enterActivityTypeName(testActivityTypeName);
        pageFactory.getActivityParametersPage().enterActivityTypeDescription(testActivityTypeDescription);
        pageFactory.getActivityParametersPage().saveActivityTypeDetails();
        pageFactory.getActivityParametersPage().selectMaximumPaginationForActivityTypeTable();
        pageFactory.getActivityParametersPage().verifyActivityTypeFromList(testActivityTypeName, testActivityTypeDescription);
    }

    public void addCustomField() {
        pageFactory.getCustomFieldsPage().selectEntityForCustomField(FetchPropertiesData.getCustomizationTestData("EntityType_Projects"));
        pageFactory.getCustomFieldsPage().clickAddNewFieldButton(FetchPropertiesData.getCustomizationTestData("EntityType_Projects"));
        pageFactory.getCustomFieldsPage().enterCustomFieldName(testCustomFieldName);
        pageFactory.getCustomFieldsPage().checkCustomFieldDisplayCheckbox();
        pageFactory.getCustomFieldsPage().checkCustomFieldDisplayToTeamMembersCheckbox();
        pageFactory.getCustomFieldsPage().selectCustomFieldType(testCustomFieldType);
        pageFactory.getCustomFieldsPage().enableTypeOnEntity(FetchPropertiesData.getProjectsModuleTestData("Consulting_ProjectType"));
        pageFactory.getCustomFieldsPage().saveCustomFieldsDetails();
        pageFactory.getCustomFieldsPage().verifyCustomFieldDetails(testCustomFieldName, testCustomFieldType);
    }

    public void addMultiCurrencyAndExchangeRate() {
        pageFactory.getMulticurrencyPage().switchToMulticurrencyIframe();
        pageFactory.getMulticurrencyPage().clickAddNewCurrencyButton();
        pageFactory.getMulticurrencyPage().selectCurrency(testCurrency);
        pageFactory.getMulticurrencyPage().saveCurrencyDetails();
        pageFactory.getMulticurrencyPage().switchToMulticurrencyIframe();
        pageFactory.getMulticurrencyPage().verifyCurrencyFromList(testCurrencyName, testCurrencySymbol);
        CommonMethods.switchToDefaultContent();
        pageFactory.getMulticurrencyPage().switchToExchangeRateTab();
        pageFactory.getMulticurrencyPage().switchToMulticurrencyIframe();
        pageFactory.getMulticurrencyPage().clickAddNewExchangeRateButton();
        pageFactory.getMulticurrencyPage().selectLocalCurrency(testCurrency);
        pageFactory.getMulticurrencyPage().enterExchangeRate(exchangeRate);
        pageFactory.getMulticurrencyPage().enterExchangeRateStartDate(currentDate);
        pageFactory.getMulticurrencyPage().saveExchangeRateDetails();
        pageFactory.getMulticurrencyPage().switchToMulticurrencyIframe();
        pageFactory.getMulticurrencyPage().verifyExchangeRateDetails(testCurrencyName, exchangeRate);
        pageFactory.getMulticurrencyPage().navigateToCurrencyExchangeRate(testCurrencyName);
        pageFactory.getMulticurrencyPage().verifyExchangeRateHistory(currentDate, exchangeRate);
        CommonMethods.switchToDefaultContent();
    }

    public void addCategory() {
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().clickAddNewCategoryButton();
        pageFactory.getCategoriesAndRatesPage().enterCategoryName(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryFromCategoryTable(testCategoryName);
        CommonMethods.switchToDefaultContent();
    }

    public void updateCategoryTypeStandardCostRate() {
        pageFactory.getCategoriesAndRatesPage().switchToGlobalStandardCostTab();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().navigateToGlobalCategoryType(globalCategoryType);
        pageFactory.getCategoriesAndRatesPage().enterStartDateForCategoryCost(currentDate);
        pageFactory.getCategoriesAndRatesPage().enterCategoryInputCost(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().enterCategoryStandardBillRate(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().selectStandardBillRateCurrency(testCurrencySymbol);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().navigateToGlobalCategoryType(globalCategoryType);
        pageFactory.getCategoriesAndRatesPage().verifyCategoryStandardCostHistoryFromList(currentDate, testCategoryCost, currency, testCategoryCost_400, testCurrencySymbol);
        CommonMethods.switchToDefaultContent();
    }

    public void updateCategoryGeneralAndEmployeeStandardCost() {
        pageFactory.getCategoriesAndRatesPage().switchToCategoriesStandardCostTab();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().enterStartDateForCategoryCost(currentDate);
        pageFactory.getCategoriesAndRatesPage().enterCategoryInputCost(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().enterCategoryStandardBillRate(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        CommonMethods.switchToDefaultContent();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getCategoriesAndRatesPage().switchToCategoryFrame();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryCostDetailsForCategoryCostTable(testCategoryName, FetchPropertiesData.getCostManagementTestData("CategoryNullAmount"), testCategoryCost, testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().selectEmployeeCategoryType();
        pageFactory.getCategoriesAndRatesPage().enterStartDateForCategoryCost(currentDate);
        pageFactory.getCategoriesAndRatesPage().enterCategoryInputCost(testCategoryEmployeeCost);
        pageFactory.getCategoriesAndRatesPage().enterCategoryStandardBillRate(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryCostDetailsForCategoryCostTable(testCategoryName, testCategoryEmployeeCost, testCategoryCost, testCategoryCost);
        CommonMethods.switchToDefaultContent();
    }

    public void addProviders() {
        pageFactory.getProvidersPage().clickAddNewProviderButton();
        pageFactory.getProvidersPage().enterProviderName(testProviderName);
        pageFactory.getProvidersPage().enterProviderEmail(testProviderEmail);
        pageFactory.getProvidersPage().enterProviderPhoneNumber(testProviderNumber);
        pageFactory.getProvidersPage().enterProviderCity(testProviderCity);
        pageFactory.getProvidersPage().enterProviderPaymentPeriod(FetchPropertiesData.getCostManagementTestData("TestPaymentPeriod"));
        pageFactory.getProvidersPage().enterProviderTax(FetchPropertiesData.getCostManagementTestData("TestProviderTax"));
        pageFactory.getProvidersPage().saveProviderDetails();
        pageFactory.getProvidersPage().switchToRatesTab();
        pageFactory.getProvidersPage().clickProvidersAddNewCategoryButton();
        pageFactory.getCategoriesAndRatesPage().selectCategoryFromCategoryStandardCostDropdown(testCategoryName);
        pageFactory.getProvidersPage().enterProviderCategoryStartDate(currentDate);
        pageFactory.getCategoriesAndRatesPage().enterCategoryInputCost(testCategoryCost);
        pageFactory.getCategoriesAndRatesPage().enterCategoryStandardBillRate(testCategoryCost_400);
        pageFactory.getCategoriesAndRatesPage().saveCategoryDetails();
        CommonMethods.waitForSuccessMessageSidePopup();
        pageFactory.getProvidersPage().switchToRatesTab();
        pageFactory.getCategoriesAndRatesPage().verifyCategoryFromCategoryTable(testCategoryName);
        pageFactory.getCategoriesAndRatesPage().navigateToCategoryFromCategoryTable(testCategoryName);
        pageFactory.getProvidersPage().verifyProviderCategoryStandardCostHistoryFromList(currentDate, testCategoryCost, testCategoryCost_400);
        pageFactory.getSideBarHamburgerMenu().navigateToProvidersPage();
        pageFactory.getProvidersPage().filterProviderByName(testProviderName);
        pageFactory.getProvidersPage().verifyProviderDetails(testProviderName, testProviderEmail, testProviderNumber, testProviderCity);
    }

    public void addWorkingHour() {
        pageFactory.getWorkingHoursPage().clickAddNewWorkingHoursButton();
        pageFactory.getWorkingHoursPage().enterWorkingHoursName(testWorkingHourName);
        for (String weekDay : workingDays) {
            pageFactory.getWorkingHoursPage().enterWorkingHoursPerWeekDay(weekDay, workingHour);
        }
        pageFactory.getWorkingHoursPage().saveWorkingHourDetails();
        pageFactory.getWorkingHoursPage().verifyWorkingHourFromList(testWorkingHourName, workingHour, workingHour, workingHour, workingHour, workingHour, FetchPropertiesData.getUserManagementTestData("WorkingHour_0"), FetchPropertiesData.getUserManagementTestData("WorkingHour_0"));
    }

    public void addCalendar() {
        pageFactory.getCalendarsPage().clickAddNewCalenderButton();
        pageFactory.getCalendarsPage().enterCalenderName(calenderName);
        pageFactory.getCalendarsPage().enterCalenderDescription(calendarDescription);
        pageFactory.getCalendarsPage().saveCalenderDetails();
        pageFactory.getCalendarsPage().verifyCalendarFromList(calenderName, calendarDescription);
    }
}