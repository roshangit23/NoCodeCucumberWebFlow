
package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import utils.DriverFactory;

public class CucumberHooks {

    private static boolean isBrowserActive = false;

    @Before
    public void beforeEveryScenario(Scenario scenario) {
        if (!isBrowserActive) {
            System.out.println("Browser launch");
            WebDriver driver = DriverFactory.getDriver();
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            isBrowserActive = true;
        }
        if (scenario.getSourceTagNames().contains("@First")) {
            System.out.println("Starting new feature");
            // Any additional setup specifically for the first scenario of a feature
        }
    }

    @After
    public void afterEveryScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed. Closing browser.");
            DriverFactory.closeDriver();
            isBrowserActive = false;
        } else if (scenario.getSourceTagNames().contains("@Last")) {
            System.out.println("Closing browser after last scenario");
            DriverFactory.closeDriver();
            isBrowserActive = false;
        }
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            try {
                if (!isAlertPresent(driver)) {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "image");
                }
            } catch (Exception e) {
            }
        }
    }

    private boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
