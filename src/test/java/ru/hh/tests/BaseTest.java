package ru.hh.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import ru.hh.pages.WebBrowser;
import ru.hh.utils.FilesUtils;

@ContextConfiguration("file:src/test/resources/spring.xml")
//@ContextConfiguration(locations = { "classpath:spring.xml" })
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebBrowser browser;
    private String pathConfigProp = "./src/test/resources/config.properties";

    @BeforeClass
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("-incognito");
        chromeOptions.addArguments("--start-maximized", "true");
        chromeOptions.addArguments("disable-popup-blocking", "true");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--private");
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.setCapability("marionette", true);

        switch (FilesUtils.getProperty(pathConfigProp, "webDriver").toUpperCase()) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
                browser.webDriver = new ChromeDriver(chromeOptions);
                break;
            }
            case "FIREFOX": {
                System.setProperty("webdriver.firefox.driver", FilesUtils.getProperty(pathConfigProp, "pathWebDriver") + "geckodriver");
                browser.webDriver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default: {
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
                browser.webDriver = new ChromeDriver(chromeOptions);
                break;
            }
        }

        browser.baseUrl = FilesUtils.getProperty(pathConfigProp, "baseUrl");
    }

}
