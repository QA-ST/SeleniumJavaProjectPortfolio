package ExtentReporter;

import org.testng.*;

import java.io.IOException;

import static ExtentReporter.ExtentReport.test;

public class Listeners implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        try {
            ExtentReport.initReports();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReports();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        try {
            ExtentReport.createTest(result.getMethod().getMethodName());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentLogger.pass(result.getMethod().getMethodName() + " is passed");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
            ExtentLogger.fail(String.valueOf(result.getThrowable()));
        } catch (Exception e) {
            test.fail(new RuntimeException());
            ExtentLogger.fail("Exception: " + e.getMessage());
        }
    }
}