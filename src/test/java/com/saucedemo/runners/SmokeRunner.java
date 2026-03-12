package com.saucedemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * SmokeRunner — executes only @smoke tagged scenarios.
 *
 * Run with:
 *   mvn test -Dsurefire.suiteXmlFiles=testng-smoke.xml
 */
@CucumberOptions(
        features   = "src/test/resources/features",
        glue       = {"com.saucedemo.steps", "com.saucedemo.hooks"},
        tags       = "@smoke",
        plugin     = {
            "pretty",
            "html:reports/smoke-html/report.html",
            "json:reports/smoke-json/report.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class SmokeRunner extends AbstractTestNGCucumberTests {}
