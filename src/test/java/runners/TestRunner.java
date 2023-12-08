package runners;

import common.Common;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        // Call your method to check uniqueness in the YAML file
        checkYamlFileForUniqueness("xpaths/xpaths.yaml");
        // Clean up the screenshot directory before the tests run
        Common.cleanDirectory("test-output/screenshots");
    }
    private static void checkYamlFileForUniqueness(String filePath) {
        try (InputStream inputStream = TestRunner.class.getClassLoader().getResourceAsStream(filePath)) {
            Yaml yaml = new Yaml();
            Map<String, Map<String, String>> data = yaml.load(inputStream);

            Set<String> uniqueElements = new HashSet<>();
            for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
                for (String key : entry.getValue().keySet()) {
                    if (!uniqueElements.add(key)) {
                        throw new RuntimeException("Duplicate element name found: " + key);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking uniqueness in YAML file: " + e.getMessage(), e);
        }
    }
}

