package Utils.Config;

import Helpers.FetchPropertiesData;
import Helpers.HelperMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

import static ExtentReporter.ExtentReport.test;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebDriverWait waitForElement;
    protected PageFactory pageFactory;
    public static Actions action;
    public static JavascriptExecutor jsExecutor;

    int timeout = 20;
    int elementWaitTimeout = 3;
    String failTestsScreenshotsFolderPath = "/src/test/java/TestReport/FailTestsScreenCapture/";

    @BeforeClass(groups = {"Smoke test case", "Sprint test case"})
    public void setupBrowserAndNavigateToWebApp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920,1080");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.navigate().to(FetchPropertiesData.getEnvironmentVariables("Env"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waitForElement = new WebDriverWait(driver, Duration.ofSeconds(elementWaitTimeout));
        pageFactory = new PageFactory();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

        pageFactory.getLoginPage().enterUserEmail(FetchPropertiesData.getEnvironmentVariables("UserEmail"));
        pageFactory.getLoginPage().enterUserPassword(FetchPropertiesData.getEnvironmentVariables("UserPassword"));
        pageFactory.getLoginPage().clickLoginButton();
    }

    @AfterClass(groups = {"Smoke test case", "Sprint test case"})
    public void logoutFromAppAndCloseBrowser() {
        pageFactory.getHeader().userLogoutFromApp();
        driver.quit();
    }

    @AfterMethod(groups = {"Smoke test case", "Sprint test case"})
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = HelperMethods.captureScreenshot(driver, result.getName(), failTestsScreenshotsFolderPath);
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @BeforeSuite(groups = {"Smoke test case", "Sprint test case"})
    public void suiteSetup() {
        HelperMethods.deleteAllFilesFromDirectory(failTestsScreenshotsFolderPath);
    }
}