package com.saucedemo.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils — captures screenshots to disk and returns the byte[]
 * for embedding in Extent Reports.
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "reports/screenshots/";

    private ScreenshotUtils() {}

    /**
     * Captures a screenshot, saves it to disk, and returns raw bytes
     * for embedding in reports.
     *
     * @param driver   active WebDriver
     * @param testName used as filename prefix
     * @return byte[] of the screenshot (PNG)
     */
    public static byte[] captureScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

        String timestamp  = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String safeName   = testName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String filePath   = SCREENSHOT_DIR + safeName + "_" + timestamp + ".png";

        try {
            File dest = new File(filePath);
            FileUtils.forceMkdirParent(dest);
            FileUtils.writeByteArrayToFile(dest, screenshotBytes);
            System.out.println("[Screenshot] Saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("[Screenshot] Could not save: " + e.getMessage());
        }

        return screenshotBytes;
    }
}
