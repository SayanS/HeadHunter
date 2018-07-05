package ru.hh.tests;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.hh.pages.BasePage;
import ru.hh.utils.FilesUtils;

import java.lang.reflect.InvocationTargetException;

//@ContextConfiguration("file:src/test/resources/spring.xml")
//@ContextConfiguration(locations = { "classpath:spring.xml" })
//public class BaseTest extends AbstractTestNGSpringContextTests {
    public class BaseTest{
    private WebDriver webDriver;

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

        switch (FilesUtils.getConfigProperty("webDriver").toUpperCase()) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", FilesUtils.getConfigProperty("pathWebDriver"));
                webDriver = new ChromeDriver(chromeOptions);
                break;
            }
            case "FIREFOX": {
                System.setProperty("webdriver.firefox.driver", FilesUtils.getConfigProperty("pathWebDriver"));
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default: {
                System.setProperty("webdriver.chrome.driver", FilesUtils.getConfigProperty("pathWebDriver"));
                webDriver = new ChromeDriver(chromeOptions);
                break;
            }
        }
    }

    public <T extends BasePage> T getPage(Class<T> page){
        try {
            return page.getConstructor(WebDriver.class).newInstance(webDriver);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeSuite(alwaysRun = true)
    public void configure() {
        RestAssured.baseURI = FilesUtils.getConfigProperty("baseUri");
        //RestAssured.port = 8080;
//        RestAssured.basePath = "/books";
    }

}
