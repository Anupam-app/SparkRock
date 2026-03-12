package com.saucedemo.hooks;

import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks — Cucumber lifecycle hooks.
 *
 * @Before  : Initialises WebDriver before each scenario.
 * @After   : Captures screenshot on failure, then quits driver.
 * @AfterStep: Embeds screenshot after each step (for rich reports).
 */
public class Hooks {

    /**
     * Runs before every Cucumber scenario.
     * Initialises a fresh WebDriver instance.
     */
    @Before(order = 1)
    public void setUp(Scenario scenario) {
        System.out.println("▶ Starting: " + scenario.getName());
        DriverManager.initDriver();
    }

    /**
     * Runs after every Cucumber scenario.
     * Captures a screenshot on failure and quits the driver.
     */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("✖ FAILED: " + scenario.getName());
            try {
                byte[] screenshot = ScreenshotUtils.captureScreenshot(
                        DriverManager.getDriver(),
                        scenario.getName()
                );
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            } catch (Exception e) {
                System.err.println("[Hooks] Could not capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("✔ PASSED: " + scenario.getName());
        }
        DriverManager.quitDriver();
    }

    /**
     * Embeds a screenshot after every step for detailed reporting.
     * Only active when the scenario is still running (not yet failed).
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (!scenario.isFailed()) {
            try {
                byte[] screenshot = ScreenshotUtils.captureScreenshot(
                        DriverManager.getDriver(),
                        "step_" + scenario.getName()
                );
                scenario.attach(screenshot, "image/png", "Step Screenshot");
            } catch (Exception e) {
                // Non-fatal – step screenshots are supplementary
            }
        }
    }
}
