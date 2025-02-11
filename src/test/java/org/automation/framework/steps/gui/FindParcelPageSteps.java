package org.automation.framework.steps.gui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.automation.framework.pages.FindParcelPage;
import org.automation.framework.pages.HomePage;
import org.automation.framework.utils.ConfigReader;
import org.automation.framework.utils.CustomLogger;
import org.automation.framework.utils.DriverManager;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FindParcelPageSteps {
    private final CustomLogger logger;
    private final WebDriver driver = DriverManager.driver;
    private final HomePage homePage = new HomePage(DriverManager.driver);
    private final FindParcelPage findParcelPage = new FindParcelPage(DriverManager.driver);

    public FindParcelPageSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @Given("user navigates to the InPost parcel tracking page")
    public void userNavigatesToTheInPostParcelTrackingPage() {
        String url = ConfigReader.getStringProperty("find.parcel.url");
        logger.logStep("Opening [%s] page.", url);
        driver.get(url);
        homePage.clickAcceptCookies();
    }

    @When("user enters the parcel number {string}")
    public void userEntersTheParcelNumber(String parcelNumber) {
        logger.logStep("Entering the parcel no. [%s]", parcelNumber);
        findParcelPage.enterParcelNumber(parcelNumber);
    }

    @And("user clicks on find button")
    public void userClicksOnFindButton() {
        logger.logStep("Clicking on Find button.");
        findParcelPage.clickFindButton();
    }

    @Then("the status of the parcel should be {string}")
    public void theStatusOfTheParcelShouldBe(String expectedStatus) {
        logger.logStep("Verifying parcel status.");
        String actualStatus = findParcelPage.getParcelStatus();
        assertEquals(expectedStatus, actualStatus, "Incorrect parcel status.");
        logger.logInfo("Parcel has correct state.");
    }
}
