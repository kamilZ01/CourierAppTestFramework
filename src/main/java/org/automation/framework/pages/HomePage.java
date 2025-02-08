package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final ElementUtil elementUtil;

    @FindBy(xpath = "//div[@aria-label='Cookie banner']//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookies;
    @FindBy(xpath = "//div[@aria-label='Cookie banner']")
    private WebElement cookieBanner;

    public HomePage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptCookies() {
        elementUtil.click(acceptCookies);
        elementUtil.waitForElementToBeNotVisible(cookieBanner);
    }
}
