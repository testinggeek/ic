package com.ic.automation;

import com.ic.pages.JoinNowPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class CurveTest {

    protected static DesiredCapabilities dCaps;
    JoinNowPage joinNowPage;
    String validPhoneNumber;
    String invalidPhoneNumber;
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {

        dCaps = new DesiredCapabilities();
        dCaps.setJavascriptEnabled(true);
        dCaps.setCapability("takesScreenshot", false);

        driver = new FirefoxDriver(dCaps);
        baseUrl = "https://www.imaginecurve.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        validPhoneNumber = "7872951371";
        invalidPhoneNumber = "777";

    }

    @Test
    public void ensureThatHomePageTitleIsCorrect() throws Exception {
        driver.get(baseUrl + "/");
        assertTrue("Page title does not match", "Curve - All your cards in one".equalsIgnoreCase(driver.getTitle()));
    }

    @Test
    public void ensureThatUserCanSubmitMobileNumberToGetAppDownloadLink() {
        navigateToJoinNowAndSubmitPhoneNumber(validPhoneNumber);
        assertTrue("Success message is not present", joinNowPage.getMessage().contains("We've sent you a link to download the app"));
    }

    @Test
    public void ensureThatUserGetsAnErrorMessageForInvalidPhoneNumber() {
        navigateToJoinNowAndSubmitPhoneNumber(invalidPhoneNumber);
        assertTrue("Error message is not present", joinNowPage.getErrorMessage().contains("Phone Number is invalid"));
    }

    @Test
    public void ensureThatUserCanNotSubmitSameNumberMultipleTimes() {

        navigateToJoinNowAndSubmitPhoneNumber(validPhoneNumber);
        assertTrue("Success message is not present", joinNowPage.getMessage().contains("We've sent you a link to download the app"));

        navigateToJoinNowAndSubmitPhoneNumber(validPhoneNumber);
        assertTrue("Error message is not present", joinNowPage.getMultipleSubmissionErrorMessage().contains("It looks like you've already submitted that."));

    }

    private void navigateToJoinNowAndSubmitPhoneNumber(String phoneNumber) {
        driver.get(baseUrl + "/joinnow/");
        joinNowPage = new JoinNowPage(driver);
        joinNowPage.setPhoneNumber(phoneNumber);
        joinNowPage.submitPhoneNumber();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
