package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindParcelPage {
    private final ElementUtil elementUtil;

    @FindBy(xpath = "//form[@class='tracking-form']//input[@name='number']")
    private WebElement parcelNumberInput;
    @FindBy(xpath = "//form[@class='tracking-form']//button[@type='submit' and contains(@class,'btn--primary')]")
    private WebElement findButton;
    @FindBy(xpath = "//div[@class='single--status--block -active']/div[contains(@class,'message-box')]/p[contains(@class,'big')]")
    private WebElement activeStatus;
    @FindBy(xpath = "//div[contains(@class,'parcel--statuses--errors')]//p")
    private WebElement parcelStatusesErrorsMessage;

    public FindParcelPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterParcelNumber(String number) {
        elementUtil.sendKeys(parcelNumberInput, number);
    }

    public void clickFindButton() {
        elementUtil.click(findButton);
    }

    public String getParcelStatus() {
        return elementUtil.isElementDisplayed(activeStatus)
                ? elementUtil.getElementText(activeStatus)
                : "";
    }

    public String getErrorMessage() {
        return elementUtil.isElementDisplayed(parcelStatusesErrorsMessage)
                ? elementUtil.getElementText(parcelStatusesErrorsMessage)
                : "";
    }
}
