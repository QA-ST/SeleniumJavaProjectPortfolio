package PageObjects;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SideBarHamburgerMenu extends BaseClass {
    String sideMenuExpandVerifyClass = "menu-open";

    By sideMenuExpandVerify = By.xpath("//form//preceding::head[1]//following::body");
    By sideMenuButton = By.xpath("(//header[@id='header']//button)[1]");
    By managementTabOption = By.id("liTab2");
    By projectsMenuOption = By.id("liProjects");
    By createNewProjectShortcutOption = By.id("aCreateProjectShortcut");
    By createNewAgileProject = By.id("btnCreateKanbanProject");
    By createNewWaterfallProject = By.id("btnCreateNewProject");
    By projectBoardTabOption = By.id("liKanbanBoard");
    By projectGanttTabOption = By.id("liProjectGantt");
    By organizationTabOption = By.xpath("//li/a[@title='Organization']");
    By goalsAndProcessesMenuOption = By.xpath("//li/a[@title='GOALS & PROCESSES']");
    By businessGoalsMenuOption = By.id("liBusinessGoals");
    By costManagementMenuOption = By.xpath("//li/a[@title='COST MANAGEMENT']");
    By costManagementList = By.xpath("//li/a[@title='COST MANAGEMENT']//following::ul[1]");
    By categoriesAndRatesMenuOption = By.id("liCategoryAndRates");
    By userManagementMenuOption = By.xpath("//li/a[@title='USER MANAGEMENT']");
    By userManagementList = By.xpath("//li/a[@title='USER MANAGEMENT']//following::ul[1]");
    By usersMenuOption = By.id("liUsers");
    By createNewUserMenuOption = By.id("liRegisteranewuser");
    By resourcesMenuOption = By.id("liResources");
    By configurationMenuTabOption = By.xpath("//li/a[@title='Configuration']");
    By parametersMenuOption = By.xpath("//li/a[@title='PARAMETERS']");
    By parametersMenuOptionList = By.xpath("//li/a[@title='PARAMETERS']//following::ul[1]");
    By projectParametersMenuOption = By.id("liProjectParameters");
    By organizationMenuOption = By.xpath("//li/a[@title='ORGANIZATION']");
    By organizationMenuOptionList = By.xpath("//li/a[@title='ORGANIZATION']//following::ul[1]");
    By organizationUnitsMenuOption = By.id("liOrganizationUnits");
    By workGroupsMenuOption = By.id("liWorkGroups");
    By activityParameterMenuOption = By.id("liActivitiesParameters");
    By customizationMenuTabOption = By.xpath("//li/a[@title='CUSTOMIZATION']");
    By customizationMenuOptionList = By.xpath("//li/a[@title='CUSTOMIZATION']//following::ul[1]");
    By customFieldsMenuOption = By.id("liCustomFields");
    By multicurrencyMenuOption = By.id("liMulticurrency");
    By providersMenuOption = By.id("liProviders");
    By workingHoursMenuOption = By.id("liStandardWorkingHours");
    By calendarsMenuOption = By.id("liManageCalendar");

    public void expandSidebarMenu() {
        WebElement sideMenu = CommonMethods.waitForElementClickable(sideMenuExpandVerify);
        String sideMenuExpandClass = sideMenu.getAttribute("class");
        if (!sideMenuExpandClass.contains(sideMenuExpandVerifyClass)) {
            wait.until(ExpectedConditions.elementToBeClickable(sideMenuButton)).click();
        }
    }

    public void clickCreateNewProjectShortcutOption() {
        CommonMethods.waitForElementClickable(createNewProjectShortcutOption).click();
        ExtentLogger.pass("Clicked the create new project shortcut option from sidebar menu");
    }

    public void navigateToProjectsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(managementTabOption).click();
        CommonMethods.waitForElementClickable(projectsMenuOption).click();
        CommonMethods.waitForPageLoader();
        ExtentLogger.pass("Navigated to projects module from the side menu");
    }

    public void selectCreateAgileProjectFromCreateNewProjectShortcut() {
        expandSidebarMenu();
        clickCreateNewProjectShortcutOption();
        CommonMethods.waitForElementClickable(createNewAgileProject).click();
        CommonMethods.waitForElementVisibility(projectBoardTabOption);
        ExtentLogger.pass("Selected the create new agile project option from the create new project shortcut option");
    }

    public void selectCreateWaterfallProjectFromCreateNewProjectShortcut() {
        expandSidebarMenu();
        clickCreateNewProjectShortcutOption();
        CommonMethods.waitForElementClickable(createNewWaterfallProject).click();
        CommonMethods.waitForElementVisibility(projectGanttTabOption);
        ExtentLogger.pass("Selected the create new waterfall project option from the create new project shortcut option");
    }

    public void navigateToBusinessGoalsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        CommonMethods.waitForElementClickable(goalsAndProcessesMenuOption).click();
        CommonMethods.waitForElementClickable(businessGoalsMenuOption).click();
        ExtentLogger.pass("Navigated to business goals module from the side menu");
    }

    public void navigateToCategoriesAndRatesPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(costManagementList)) {
            CommonMethods.waitForElementClickable(costManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(categoriesAndRatesMenuOption).click();
        ExtentLogger.pass("Navigated to categories and rates module from the side menu");
    }

    public boolean validateMenuOptionIsExpanded(By menuOptionToCheck) {
        boolean status = false;
        WebElement menuOption = wait.until(ExpectedConditions.presenceOfElementLocated(menuOptionToCheck));
        if (menuOption.getCssValue("display").contains(CommonMethods.displayBlockVerify)) {
            status = true;
        }
        return status;
    }

    public void navigateToUsersPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(userManagementList)) {
            CommonMethods.waitForElementClickable(userManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(usersMenuOption).click();
        ExtentLogger.pass("Navigated to users module from the side menu");
    }

    public void selectCreateNewUserOption() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(createNewUserMenuOption).click();
        ExtentLogger.pass("Selected the create new user option from the side menu");
    }

    public void navigateToResourcesTab() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(managementTabOption).click();
        CommonMethods.waitForElementClickable(resourcesMenuOption).click();
        ExtentLogger.pass("Navigated to resources module from the side menu");
    }

    public void navigateToProjectParametersPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(configurationMenuTabOption).click();
        if (!validateMenuOptionIsExpanded(parametersMenuOptionList)) {
            CommonMethods.waitForElementClickable(parametersMenuOption).click();
        }
        CommonMethods.waitForElementClickable(projectParametersMenuOption).click();
        ExtentLogger.pass("Navigated to project parameters module from the side menu");
    }

    public void navigateToOrganizationUnitsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(configurationMenuTabOption).click();
        if (!validateMenuOptionIsExpanded(organizationMenuOptionList)) {
            CommonMethods.waitForElementClickable(organizationMenuOption).click();
        }
        CommonMethods.waitForElementClickable(organizationUnitsMenuOption).click();
        ExtentLogger.pass("Navigated to organization units module from the side menu");
    }

    public void navigateToWorkGroupsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(configurationMenuTabOption).click();
        if (!validateMenuOptionIsExpanded(organizationMenuOptionList)) {
            CommonMethods.waitForElementClickable(organizationMenuOption).click();
        }
        CommonMethods.waitForElementClickable(workGroupsMenuOption).click();
        ExtentLogger.pass("Navigated to work groups module from the side menu");
    }

    public void navigateToActivityParameterPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(configurationMenuTabOption).click();
        if (!validateMenuOptionIsExpanded(parametersMenuOptionList)) {
            CommonMethods.waitForElementClickable(parametersMenuOption).click();
        }
        CommonMethods.waitForElementClickable(activityParameterMenuOption).click();
        ExtentLogger.pass("Navigated to activity parameters module from the side menu");
    }

    public void navigateToCustomFieldsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(configurationMenuTabOption).click();
        if (!validateMenuOptionIsExpanded(customizationMenuOptionList)) {
            CommonMethods.waitForElementClickable(customizationMenuTabOption).click();
        }
        CommonMethods.waitForElementClickable(customFieldsMenuOption).click();
        ExtentLogger.pass("Navigated to custom fields module from the side menu");
    }

    public void navigateToMulticurrencyPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(costManagementList)) {
            CommonMethods.waitForElementClickable(costManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(multicurrencyMenuOption).click();
        ExtentLogger.pass("Navigated to multicurrency module from the side menu");
    }

    public void navigateToProvidersPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(costManagementList)) {
            CommonMethods.waitForElementClickable(costManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(providersMenuOption).click();
        ExtentLogger.pass("Navigated to providers module from the side menu");
    }

    public void navigateToWorkingHoursPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(userManagementList)) {
            CommonMethods.waitForElementClickable(userManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(workingHoursMenuOption).click();
        ExtentLogger.pass("Navigated to working hours module from the side menu");
    }

    public void navigateToCalendarsPage() {
        expandSidebarMenu();
        CommonMethods.waitForElementClickable(organizationTabOption).click();
        if (!validateMenuOptionIsExpanded(userManagementList)) {
            CommonMethods.waitForElementClickable(userManagementMenuOption).click();
        }
        CommonMethods.waitForElementClickable(calendarsMenuOption).click();
        ExtentLogger.pass("Navigated to calendars module from the side menu");
    }
}