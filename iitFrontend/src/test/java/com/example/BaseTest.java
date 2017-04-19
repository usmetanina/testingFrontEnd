package com.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.TextReport;
import com.example.constants.Constants;
import com.galenframework.testng.GalenTestNgTestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Listeners({ TextReport.class, ScreenShotListener.class })
public abstract class BaseTest extends GalenTestNgTestBase {
    private String browserType;
    private String browserScreen;

    private Map<String, int[]> browserSizes;

    public BaseTest() {
        Configuration.screenshots = false;
        Configuration.timeout = 10000;

        browserSizes = new HashMap<>();
        browserSizes.put(Constants.SCREEN_DESKTOP, new int[] {1024, 768});
        browserSizes.put("largeDesktop", new int[] {1600, 1200});
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({ Constants.CONFIG_BROWSER, Constants.CONFIG_SCREEN })
    protected void setBrowser(@Optional(Constants.BROWSER_CHROME) String browser, @Optional(Constants.SCREEN_DESKTOP) String screen) {
        browserType = browser;
        browserScreen = screen;
    }

    @Override
    public WebDriver createDriver(Object[] args) {
        int browserWidth = browserSizes.get(browserScreen)[0];
        int browserHeight = browserSizes.get(browserScreen)[1];

        WebDriver driver;
        if(Constants.BROWSER_FIREFOX.equalsIgnoreCase(browserType)) {
//            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver(capabilities);
        }
        else if(Constants.BROWSER_CHROME.equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else {
            throw new IllegalArgumentException("Unexpected browser: " + browserType);
        }
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(browserWidth, browserHeight));
        WebDriverRunner.setWebDriver(driver);
        return driver;
    }

    protected void checkPageView(String specFile) throws IOException{
        checkLayout(specFile, Collections.singletonList(browserScreen));
    }
}