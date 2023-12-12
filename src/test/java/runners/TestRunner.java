package runners;

import common.Common;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        // Call your method to check uniqueness in the YAML file
        Common.checkYamlFileForUniqueness("xpaths/xpaths.yaml");
        // Clean up the screenshot directory before the tests run
        Common.cleanDirectory("test-output/screenshots");
    }
}

