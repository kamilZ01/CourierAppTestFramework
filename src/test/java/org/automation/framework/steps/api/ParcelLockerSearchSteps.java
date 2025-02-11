package org.automation.framework.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.automation.framework.endpoints.ApiEndpoints;
import org.automation.framework.models.Location;
import org.automation.framework.models.Points;
import org.automation.framework.models.PointsDTO;
import org.automation.framework.models.PointsResponse;
import org.automation.framework.utils.ApiHelper;
import org.automation.framework.utils.ConfigReader;
import org.automation.framework.utils.CustomLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.automation.framework.utils.FileHelper.saveDataToFile;
import static org.automation.framework.utils.Mapper.mapToDTOList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParcelLockerSearchSteps {
    private final CustomLogger logger;
    private List<Points> points = new ArrayList<>();
    private String currentCity;

    public ParcelLockerSearchSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @Given("user sends a request to fetch Parcel Lockers for {string}")
    public void userSendsARequestToFetchParcelLockersFor(String city) {
        logger.logStep("Sending request to fetch Parcel Locker for [%s] city.", city);
        currentCity = city;
        points = fetchAllPointsForCity(city);
    }

    private List<Points> fetchAllPointsForCity(String city) {
        List<Points> allPoints = new ArrayList<>();
        int page = 1, perPage = 500, totalPages;

        do {
            Response response = ApiHelper.get(ApiEndpoints.POINTS, Map.of("city", city, "page", page, "per_page", perPage,
                    "fields", "name,address_details,location"));
            PointsResponse pointsResponse = response.as(PointsResponse.class);
            allPoints.addAll(pointsResponse.items());

            totalPages = pointsResponse.totalPages();
            page++;
        } while (page <= totalPages);

        return allPoints;
    }

    @Then("the response should contain valid Parcel Lockers data")
    public void theResponseShouldContainValidParcelLockersData() {
        logger.logStep("Validating Parcel Lockers data.");
        assertAll("Parcel Lockers validation",
                () -> assertThat("Expected Parcel Lockers list to be non-empty", points, is(not(empty()))),
                () -> points.forEach(point -> assertAll(String.format("Validating parcel locker details [%s]", point.name()),
                        () -> assertThat("Parcel Locker city should match the searched city", point.addressDetails().city(), equalTo(currentCity)),
                        () -> assertThat("Parcel Locker name should not be null", point.name(), is(notNullValue())),
                        () -> assertThat("Parcel Locker postal code should not be null", point.addressDetails().postCode(), is(notNullValue())),
                        () -> assertThat("Parcel Locker coordinates should not be null", point.location(), is(notNullValue())),
                        () -> {
                            Location location = point.location();
                            assertAll(String.format("Validating coordinates for Parcel Locker [%s]", point.name()),
                                    () -> assertThat("Latitude should be between -90 and 90 degrees",
                                            location.latitude(), allOf(greaterThanOrEqualTo(-90.0), lessThanOrEqualTo(90.0))),
                                    () -> assertThat("Longitude should be between -180 and 180 degrees",
                                            location.longitude(), allOf(greaterThanOrEqualTo(-180.0), lessThanOrEqualTo(180.0))));
                        }
                )));
        logger.logInfo("All Parcel Locker details are correct.");
    }

    @And("the data is saved to {string} file")
    public void theDataIsSavedToFile(String fileName) {
        String finalFileName = String.format("%s/%s", ConfigReader.getStringProperty("test_output_dir"),
                fileName.replace("<city>", currentCity));
        logger.logStep("Saving Parcel Locker details to the file [%s].", finalFileName);
        List<PointsDTO> pointsToSave = mapToDTOList(points);
        saveDataToFile(pointsToSave, finalFileName);
        logger.logInfo("Save data successfully.");
    }
}
