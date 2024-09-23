package Utils.Config;

import PageObjects.*;
import PageObjects.ProjectsModule.*;

public class PageFactory extends BaseClass {
    private LoginPage loginPage;
    private SideBarHamburgerMenu sideBarHamburgerMenu;
    private ProjectsPage projectsPage;
    private Projects_GeneralTab projectsGeneralTab;
    private Projects_TasksTab projectsTasksTab;
    private Projects_TeamTab projectsTeamTab;
    private Projects_BudgetTab projectsBudgetTab;
    private Projects_PurchasesTab projectsPurchasesTab;
    private Projects_RevenueTab projectsRevenueTab;
    private Projects_GanttTab projectsGanttTab;
    private Projects_SprintsTab projectsSprintsTab;
    private Projects_ProgressTab projectsProgressTab;
    private BusinessGoalsPage businessGoalsPage;
    private CategoriesAndRatesPage categoriesAndRatesPage;
    private Projects_BoardTab projectsBoardTab;
    private UsersPage usersPage;
    private ResourcesPage resourcesPage;
    private Header header;
    private ProjectParametersPage projectParametersPage;
    private OrganizationUnitsPage organizationUnits;
    private WorkGroupsPage workGroupsPage;
    private ActivityParametersPage activityParametersPage;
    private CustomFieldsPage customFieldsPage;
    private MulticurrencyPage multicurrencyPage;
    private ProvidersPage providersPage;
    private WorkingHoursPage workingHoursPage;
    private CalendarsPage calendarsPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public SideBarHamburgerMenu getSideBarHamburgerMenu() {
        if (sideBarHamburgerMenu == null) {
            sideBarHamburgerMenu = new SideBarHamburgerMenu();
        }
        return sideBarHamburgerMenu;
    }

    public ProjectsPage getProjectsPage() {
        if (projectsPage == null) {
            projectsPage = new ProjectsPage();
        }
        return projectsPage;
    }

    public Projects_GeneralTab getProjectsGeneralTab() {
        if (projectsGeneralTab == null) {
            projectsGeneralTab = new Projects_GeneralTab();
        }
        return projectsGeneralTab;
    }

    public Projects_TasksTab getProjectsTasksTab() {
        if (projectsTasksTab == null) {
            projectsTasksTab = new Projects_TasksTab();
        }
        return projectsTasksTab;
    }

    public Projects_TeamTab getProjectsTeamTab() {
        if (projectsTeamTab == null) {
            projectsTeamTab = new Projects_TeamTab();
        }
        return projectsTeamTab;
    }

    public Projects_BudgetTab getProjectsBudgetTab() {
        if (projectsBudgetTab == null) {
            projectsBudgetTab = new Projects_BudgetTab();
        }
        return projectsBudgetTab;
    }

    public Projects_PurchasesTab getProjectsPurchasesTab() {
        if (projectsPurchasesTab == null) {
            projectsPurchasesTab = new Projects_PurchasesTab();
        }
        return projectsPurchasesTab;
    }

    public Projects_RevenueTab getProjectsRevenueTab() {
        if (projectsRevenueTab == null) {
            projectsRevenueTab = new Projects_RevenueTab();
        }
        return projectsRevenueTab;
    }

    public Projects_GanttTab getProjectsGanttTab() {
        if (projectsGanttTab == null) {
            projectsGanttTab = new Projects_GanttTab();
        }
        return projectsGanttTab;
    }

    public Projects_SprintsTab getProjectsSprintsTab() {
        if (projectsSprintsTab == null) {
            projectsSprintsTab = new Projects_SprintsTab();
        }
        return projectsSprintsTab;
    }

    public Projects_ProgressTab getProjectsProgressTab() {
        if (projectsProgressTab == null) {
            projectsProgressTab = new Projects_ProgressTab();
        }
        return projectsProgressTab;
    }

    public Projects_BoardTab getProjectsBoardTab() {
        if (projectsBoardTab == null) {
            projectsBoardTab = new Projects_BoardTab();
        }
        return projectsBoardTab;
    }

    public BusinessGoalsPage getBusinessGoalsPage() {
        if (businessGoalsPage == null) {
            businessGoalsPage = new BusinessGoalsPage();
        }
        return businessGoalsPage;
    }

    public CategoriesAndRatesPage getCategoriesAndRatesPage() {
        if (categoriesAndRatesPage == null) {
            categoriesAndRatesPage = new CategoriesAndRatesPage();
        }
        return categoriesAndRatesPage;
    }

    public UsersPage getUsersPage() {
        if (usersPage == null) {
            usersPage = new UsersPage();
        }
        return usersPage;
    }

    public ResourcesPage getResourcesPage() {
        if (resourcesPage == null) {
            resourcesPage = new ResourcesPage();
        }
        return resourcesPage;
    }

    public Header getHeader() {
        if (header == null) {
            header = new Header();
        }
        return header;
    }

    public ProjectParametersPage getProjectParametersPage() {
        if (projectParametersPage == null) {
            projectParametersPage = new ProjectParametersPage();
        }
        return projectParametersPage;
    }

    public OrganizationUnitsPage getOrganizationUnitsPage() {
        if (organizationUnits == null) {
            organizationUnits = new OrganizationUnitsPage();
        }
        return organizationUnits;
    }

    public WorkGroupsPage getWorkGroupsPage() {
        if (workGroupsPage == null) {
            workGroupsPage = new WorkGroupsPage();
        }
        return workGroupsPage;
    }

    public ActivityParametersPage getActivityParametersPage() {
        if (activityParametersPage == null) {
            activityParametersPage = new ActivityParametersPage();
        }
        return activityParametersPage;
    }

    public CustomFieldsPage getCustomFieldsPage() {
        if (customFieldsPage == null) {
            customFieldsPage = new CustomFieldsPage();
        }
        return customFieldsPage;
    }

    public MulticurrencyPage getMulticurrencyPage() {
        if (multicurrencyPage == null) {
            multicurrencyPage = new MulticurrencyPage();
        }
        return multicurrencyPage;
    }

    public ProvidersPage getProvidersPage() {
        if (providersPage == null) {
            providersPage = new ProvidersPage();
        }
        return providersPage;
    }

    public WorkingHoursPage getWorkingHoursPage() {
        if (workingHoursPage == null) {
            workingHoursPage = new WorkingHoursPage();
        }
        return workingHoursPage;
    }

    public CalendarsPage getCalendarsPage() {
        if (calendarsPage == null) {
            calendarsPage = new CalendarsPage();
        }
        return calendarsPage;
    }
}