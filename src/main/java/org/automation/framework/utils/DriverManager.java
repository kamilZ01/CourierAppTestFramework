package org.automation.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.automation.framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
public class DriverManager {
    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        String browser = ConfigReader.getStringProperty("browser");
        String runMode = ConfigReader.getStringProperty("run.mode");

        System.out.printf("Browser value is: [%s], run mode is: [%s]\n", browser, runMode);

        if ("remote".equalsIgnoreCase(runMode)) {
            driver = getRemoteDriver(BrowserType.valueOf(browser.toUpperCase()));
        } else {
            driver = getLocalDriver(BrowserType.valueOf(browser.toUpperCase()));
        }

        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getLocalDriver(BrowserType browser) {
        return switch (browser) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
        };
    }

    private static WebDriver getRemoteDriver(BrowserType browser) {
        try {
            URL gridURL = new URL(ConfigReader.getStringProperty("selenium.grid.url"));
            switch (browser) {
                case FIREFOX -> {
                    return new RemoteWebDriver(gridURL, new FirefoxOptions());
                }
                case CHROME -> {
                    return new RemoteWebDriver(gridURL, new ChromeOptions());
                }
                case EDGE -> {
                    return new RemoteWebDriver(gridURL, new EdgeOptions());
                }
                default ->
                        throw new IllegalArgumentException(String.format("Unsupported browser: [%s]", browser.name()));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: [%s].", e);
        }
    }

}
