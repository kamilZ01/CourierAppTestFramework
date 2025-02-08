package org.automation.framework.utils;

import org.automation.framework.enums.LogType;
import org.automation.framework.hooks.AppHooks;

import java.time.ZonedDateTime;

import static org.automation.framework.utils.DateUtil.formatDate;

public class CustomLogger {

    private final AppHooks appHooks;

    public CustomLogger(AppHooks appHooks) {
        this.appHooks = appHooks;
    }

    public void logInfo(String message, Object... args) {
        String formatted = getTypeMessage(message, LogType.INFO, args);
        appHooks.getScenario().log(formatted);
        printLog(formatted);
    }

    public void logStep(String message, Object... args) {
        String formatted = getStepMessage(message, args);
        appHooks.getScenario().log(formatted);
        printLog(formatted);
    }

    private String getStepMessage(String message, Object... args) {
        return String.format("%s (%s): %s", LogType.TEST_STEP, formatDate(ZonedDateTime.now()), getLogMessage(message, args));
    }

    private String getTypeMessage(String message, LogType logType, Object... args) {
        return String.format("  %s: %s", logType, getLogMessage(message, args));
    }

    private String getLogMessage(String message, Object... args) {
        String formatted = String.format(message, args);
        return String.format("  %s", formatted);
    }

    private void printLog(String message) {
        System.out.printf("%s\n", message);
    }
}
