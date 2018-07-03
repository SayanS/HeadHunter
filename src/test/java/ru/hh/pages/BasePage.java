package ru.hh.pages;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public abstract class BasePage {
    @Autowired
    private WebBrowser webBrowser;


    public WebBrowser getWebBrowser() {
        return webBrowser;
    }

    public <T extends BasePage> T open(Class<T> page, String uri){
        getWebBrowser().webDriver.navigate().to(getWebBrowser().baseUrl+uri);
        try {
            return page.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WebElement> findAll(String xpath) {
        return webBrowser.webDriver.findElements(By.xpath(xpath));
    }

    public WebElement findBy(String xpath) {
        return webBrowser.webDriver.findElement(By.xpath(xpath));
    }

    public WebElement $(String xpath) {
        return webBrowser.webDriver.findElement(By.xpath(xpath));
    }

    public List<String> getTexts(String xpath) {
        List<String> textValues = new ArrayList<>();
        findAll(xpath).forEach(webElement -> textValues.add(webElement.getText()));
        return textValues;
    }

    public String getText(String xpath) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        return findBy(xpath).getText();
    }

    public String getText(String xpath, String keyWordXpath) {
        return getText(xpath.replace("$KeyWord", keyWordXpath));
    }

    public void clickOnJS(String xpath) {
        ((JavascriptExecutor) webBrowser.webDriver).executeScript("arguments[0].click();", findBy(xpath));
    }

    public void scrollIntoView(String xpath, int offset_y) {
        int y = webBrowser.webDriver.findElement(By.xpath(xpath)).getLocation().getY() + offset_y;
        ((JavascriptExecutor) webBrowser.webDriver).executeScript("window.scrollTo(0, " + y + ")");
    }

    public void scrollIntoView(String xpath) {
        ((JavascriptExecutor) webBrowser.webDriver).executeScript("window.scrollTo(0, 0)");
    }

    public void clickOn(String xpath) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        scrollIntoView(xpath, -100);
        moveTo($(xpath));
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(xpath)), 3);
        $(xpath).click();
    }

    public void clickOn(String xpath, String keyWordXpath) {
        clickOn(xpath.replace("$KeyWord", keyWordXpath));
    }

    protected <V> V waitFor(Function<? super WebDriver, V> condition, int secTimeout) {
        return (new WebDriverWait(webBrowser.webDriver, secTimeout)).until(condition);
    }

    public void sendKeys(String xpath, String value) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        scrollIntoView(xpath);
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)), 3);
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(xpath)), 3);
        $(xpath).sendKeys(value);
    }

    public String switchToLustWindow() {
        webBrowser.webDriver.getWindowHandles().forEach(wh -> webBrowser.webDriver
                .switchTo().window(wh));
        return webBrowser.webDriver.getCurrentUrl();
    }

    public void moveTo(WebElement webElement) {
        Actions actions = new Actions(webBrowser.webDriver);
        actions.moveToElement(webElement).perform();
    }

    public String getAttribute(String xpath, String attribute) {
        return $(xpath).getAttribute(attribute);
    }


    public void selectFromDropDownByValue(String xpath, String value) {
        Select select = new Select($(xpath));
        select.selectByValue(value);
    }
}
