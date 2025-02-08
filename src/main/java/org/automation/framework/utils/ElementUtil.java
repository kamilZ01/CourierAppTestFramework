package org.automation.framework.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ElementUtil {
    private final FluentWait<WebDriver> fluentWait;

    public ElementUtil(WebDriver driver) {
        int toBeLocatedTimeout = ConfigReader.getNumberProperty("to.be.located.timeout");
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(toBeLocatedTimeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeNotVisible(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        WebElement elementToClick = waitForElementClickable(element);
        elementToClick.click();
    }

    public void sendKeys(WebElement element, String text) {
        WebElement visibleElement = waitForElementVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    public String getElementText(WebElement element) {
        return waitForElementVisible(element).getText();
    }

}
