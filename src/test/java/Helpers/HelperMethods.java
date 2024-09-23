package Helpers;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HelperMethods {
    protected static String timestamp_hh_mm_ss;

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter);
    }

    public static String getDateTenDaysFromCurrent() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return futureDate.format(formatter);
    }

    public static String getDateFourDaysFromCurrent() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return futureDate.format(formatter);
    }

    public static String getDateFiveDaysFromCurrent() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return futureDate.format(formatter);
    }

    public static String getDateSevenDaysFromCurrent() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return futureDate.format(formatter);
    }

    public static int countWeekdays(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        int count = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                count++;
            }
        }
        return count;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName, String screenshotFolderPath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + screenshotFolderPath + screenshotName + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public static void deleteAllFilesFromDirectory(String directoryPath) {
        File folder = new File(System.getProperty("user.dir") + directoryPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        boolean deleted = file.delete();
                        if (!deleted) {
                            System.out.println("Failed to delete - " + file.getAbsolutePath());
                        }
                    }
                }
            } else {
                System.out.println("Failed to list files in the directory");
            }
        } else {
            System.out.println("The specified path is not a directory or does not exist");
        }
    }

    public static String getTimestamp_hh_mm_ss() {
        return timestamp_hh_mm_ss;
    }

    private static String generateTimestamp_hh_mm_ss() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        return currentTime.format(formatter);
    }

    static {
        timestamp_hh_mm_ss = generateTimestamp_hh_mm_ss();
    }
}