package org.automation.framework.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String formatDate(ZonedDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    public static String formatDate(ZonedDateTime dateTime) {
        String defaultPattern = "yyyy-MM-dd HH:mm:ss.SSS z";
        return formatDate(dateTime, defaultPattern);
    }
}
