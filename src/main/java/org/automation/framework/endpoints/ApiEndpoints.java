package org.automation.framework.endpoints;

import org.automation.framework.utils.ConfigReader;

public class ApiEndpoints {
    private static String POINTS_BASE_URL;
    public static String POINTS;

    static {
        POINTS_BASE_URL = ConfigReader.getStringProperty("points_base_url");
        POINTS = POINTS_BASE_URL + "geowidget/v1/points";
    }
}
