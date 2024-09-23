package ExtentReporter;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentLogger {
    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentReport.test.pass(message);
    }

    public static void fail(String message) {
        ExtentReport.test.fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }
}