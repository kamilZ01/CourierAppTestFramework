package org.automation.framework.models;

import java.util.Map;

public record LockerAvailability(
        String status,
        Map<String, String> details
) {
}
