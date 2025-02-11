package org.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PointsResponse(
        String href,
        int count,
        int page,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total_pages") int totalPages,
        List<Points> items,
        Meta meta
) {
}
