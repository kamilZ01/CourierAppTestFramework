package org.automation.framework.enums;

public enum BrowserType {
    EDGE, CHROME, FIREFOX;

    public static BrowserType getBrowser(String browser) {
        return switch(browser.toLowerCase()) {
          case "edge" -> EDGE;
          case "chrome" -> CHROME;
          case "firefox" -> FIREFOX;
            default -> throw new IllegalArgumentException(String.format("Unsupported browser: [%s]", browser));
        };
    }
}

