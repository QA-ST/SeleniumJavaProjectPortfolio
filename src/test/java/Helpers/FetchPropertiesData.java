package Helpers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class FetchPropertiesData {
    private static final String rootDir = System.getProperty("user.dir");

    private static final String environmentVariablesFilePath = rootDir + "/src/test/java/Utils/EnvironmentVariables.properties";
    private static final String projectsModuleTestDataFilePath = rootDir + "/src/test/java/TestData/ProjectsModuleTestData.properties";
    private static final String goalsAndProcessesTestDataFilePath = rootDir + "/src/test/java/TestData/GoalsAndProcessesModuleTestData.properties";
    private static final String costManagementTestDataFilePath = rootDir + "/src/test/java/TestData/CostManagementModuleTestData.properties";
    private static final String parametersTestDataFilePath = rootDir + "/src/test/java/TestData/ParametersModuleTestData.properties";
    private static final String userManagementTestDataFilePath = rootDir + "/src/test/java/TestData/UserManagementModuleTestData.properties";
    private static final String organizationTestDataFilePath = rootDir + "/src/test/java/TestData/OrganizationModuleTestData.properties";
    private static final String customizationTestDataFilePath = rootDir + "/src/test/java/TestData/CustomizationModuleTestData.properties";

    private static final Properties environmentVariables = new Properties();
    private static final Properties projectsModuleTestData = new Properties();
    private static final Properties goalsAndProcessModuleTestData = new Properties();
    private static final Properties costManagementModuleTestData = new Properties();
    private static final Properties parametersModuleTestData = new Properties();
    private static final Properties userManagementModuleTestData = new Properties();
    private static final Properties organizationModuleTestData = new Properties();
    private static final Properties customizationModuleTestData = new Properties();

    static {
        loadPropertiesData(environmentVariables, environmentVariablesFilePath);
        loadPropertiesData(projectsModuleTestData, projectsModuleTestDataFilePath);
        loadPropertiesData(goalsAndProcessModuleTestData, goalsAndProcessesTestDataFilePath);
        loadPropertiesData(costManagementModuleTestData, costManagementTestDataFilePath);
        loadPropertiesData(parametersModuleTestData, parametersTestDataFilePath);
        loadPropertiesData(userManagementModuleTestData, userManagementTestDataFilePath);
        loadPropertiesData(organizationModuleTestData, organizationTestDataFilePath);
        loadPropertiesData(customizationModuleTestData, customizationTestDataFilePath);
    }

    private static void loadPropertiesData(Properties file, String filePath) {
        try {
            InputStream ip = new FileInputStream(filePath);
            file.load(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEnvironmentVariables(String key) {
        return environmentVariables.getProperty(key);
    }

    public static String getProjectsModuleTestData(String key) {
        return projectsModuleTestData.getProperty(key);
    }

    public static String getGoalsAndProcessesTestData(String key) {
        return goalsAndProcessModuleTestData.getProperty(key);
    }

    public static String getCostManagementTestData(String key) {
        return costManagementModuleTestData.getProperty(key);
    }

    public static String getParametersTestData(String key) {
        return parametersModuleTestData.getProperty(key);
    }

    public static String getUserManagementTestData(String key) {
        return userManagementModuleTestData.getProperty(key);
    }

    public static String getOrganizationTestData(String key) {
        return organizationModuleTestData.getProperty(key);
    }

    public static String getCustomizationTestData(String key) {
        return customizationModuleTestData.getProperty(key);
    }
}