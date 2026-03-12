package com.saucedemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner — runs all feature files with Extent HTML reporting.
 *
 * Run from CLI:
 *   mvn test
 *   mvn test -Dbrowser=firefox -Dheadless=true
 *   mvn test -Dcucumber.filter.tags="@smoke"
 */
@CucumberOptions(
        features   = "src/test/resources/features",
        glue       = {"com.saucedemo.steps", "com.saucedemo.hooks"},
        tags       = "not @wip",
        plugin     = {
            "pretty",
            "html:reports/cucumber-html/report.html",
            "json:reports/cucumber-json/report.json",
            "junit:reports/cucumber-junit/report.xml",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish    = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /** Enable parallel scenario execution by returning true from parallel(). */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
