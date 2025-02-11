package org.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(
        String href,
        int count,
        int page,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total_pages") int totalPages
) {
}
