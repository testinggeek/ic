package com.ic.automation;
import java.util.concurrent.TimeUnit;

import com.ic.pages.JoinNowPage;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CurveTest {

    private WebDriver driver;
    private String baseUrl;
    protected static DesiredCapabilities dCaps;

    @Before
    public void setUp() throws Exception {

        dCaps = new DesiredCapabilities();
        dCaps.setJavascriptEnabled(true);
        dCaps.setCapability("takesScreenshot", false);

        driver = new FirefoxDriver(dCaps);
        baseUrl = "https://www.imaginecurve.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void ensureThatHomePageTitleIsCorrect() throws Exception {
        driver.get(baseUrl + "/");
        assertTrue("Page title does not match", "Curve - All your cards in one".equalsIgnoreCase(driver.getTitle()));
    }

    @Test
    public void ensureThatUserCanSubmitMobileNumberToGetAppDownloadLink(){

        driver.get(baseUrl + "/joinnow/");
        JoinNowPage joinNowPage = new JoinNowPage(driver);
        joinNowPage.setPhoneNumber("7768996680");
        joinNowPage.submitPhoneNumber();
        assertTrue("Success message is not present",joinNowPage.getMessage().contains("We've sent you a link to download the app"));

    }

    @Test
    public void ensureThatUserGetsAnErrorMessageForInvalidPhoneNumber(){
        driver.get(baseUrl + "/joinnow/");
        JoinNowPage joinNowPage = new JoinNowPage(driver);
        joinNowPage.setPhoneNumber("776");
        joinNowPage.submitPhoneNumber();
        assertTrue("Success message is not present",joinNowPage.getErrorMessage().contains("Phone Number is invalid"));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
