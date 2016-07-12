package com.ic.automation;
import java.util.concurrent.TimeUnit;
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
        driver.findElement(By.id("field_38kar")).isDisplayed();
        driver.findElement(By.id("field_38kar")).sendKeys("7768996680");
        driver.findElement(By.className("frm_submit")).click();
        assertTrue("Success message is not present",driver.findElement(By.className("frm_message")).getText().contains("We've sent you a link to download the app"));

    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
