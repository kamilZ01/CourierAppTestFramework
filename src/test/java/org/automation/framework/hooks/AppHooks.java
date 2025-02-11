package org.automation.framework.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;
import org.automation.framework.utils.DriverManager;
import org.automation.framework.utils.TagCheckerUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Getter
public class AppHooks {

    private final TagCheckerUtil tagCheckerUtil = new TagCheckerUtil();
    private Scenario scenario;
    private WebDriver driver;
    private boolean isRestScenario;

    @Before
    public void launchBrowser(Scenario scenario) {
        isRestScenario = tagCheckerUtil.hasTestAPITag(scenario);
        if (!isRestScenario) {
            driver = DriverManager.getWebDriver();
        }
        this.scenario = scenario;
    }

    @After(order = 0)
    public void quitBrowser() {
        if (!isRestScenario) {
            driver.quit();
        }
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && !isRestScenario) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
