package org.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDetails(
        String city,
        String province,
        @JsonProperty("post_code") String postCode,
        String street,
        @JsonProperty("building_number") String buildingNumber,
        @JsonProperty("flat_number") String flatNumber
) {
}
