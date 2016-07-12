package com.ic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class JoinNowPage {

    @FindBy(how = How.ID, using = "field_38kar")
    private WebElement phoneNumberTextbox;

    @FindBy(how = How.CLASS_NAME, using = "frm_submit")
    private WebElement getTheAppButton;

    @FindBy(how = How.CLASS_NAME, using = "frm_message")
    private WebElement message;

    @FindBy(how = How.CLASS_NAME, using = "frm_error")
    private WebElement errorMessage;

    @FindBy(how = How.CLASS_NAME, using = "frm_error_style")
    private WebElement multipleSubmissionErrorMessage;


    public JoinNowPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumberTextbox.isDisplayed();
        phoneNumberTextbox.sendKeys(phoneNumber);
    }

    public void submitPhoneNumber() {
        getTheAppButton.click();
    }

    public String getMessage() {
        return message.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getMultipleSubmissionErrorMessage() {
        return multipleSubmissionErrorMessage.getText();
    }

}
