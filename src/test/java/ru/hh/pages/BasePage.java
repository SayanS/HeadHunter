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

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
    private WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected String open(String url) {
        webDriver.navigate().to(url);
        return webDriver.getCurrentUrl();
    }

    protected List<WebElement> findAll(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    protected WebElement findBy(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    protected WebElement $(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    protected List<String> getTexts(String xpath) {
        List<String> textValues = new ArrayList<>();
        findAll(xpath).forEach(webElement -> textValues.add(webElement.getText()));
        return textValues;
    }

    protected String getText(String xpath) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        return findBy(xpath).getText();
    }

    protected String getText(String xpath, String keyWordXpath) {
        return getText(xpath.replace("$KeyWord", keyWordXpath));
    }

    protected void clickOnJS(String xpath) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", findBy(xpath));
    }

    protected void scrollIntoView(String xpath, int offset_y) {
        int y = webDriver.findElement(By.xpath(xpath)).getLocation().getY() + offset_y;
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, " + y + ")");
    }

    protected void scrollIntoView(String xpath) {
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, 0)");
    }

    protected void clickOn(String xpath) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        scrollIntoView(xpath, -100);
        moveTo($(xpath));
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(xpath)), 3);
        $(xpath).click();
    }

    protected void clickOn(String xpath, String keyWordXpath) {
        clickOn(xpath.replace("$KeyWord", keyWordXpath));
    }

    protected <V> V waitFor(Function<? super WebDriver, V> condition, int secTimeout) {
        return (new WebDriverWait(webDriver, secTimeout)).until(condition);
    }

    protected void sendKeys(String xpath, String value) {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)), 5);
        scrollIntoView(xpath);
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)), 3);
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(xpath)), 3);
        $(xpath).sendKeys(value);
    }

    protected String switchToLustWindow() {
        webDriver.getWindowHandles().forEach(wh -> webDriver
                .switchTo().window(wh));
        return webDriver.getCurrentUrl();
    }

    protected void moveTo(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    protected String getAttribute(String xpath, String attribute) {
        return $(xpath).getAttribute(attribute);
    }


    protected void selectFromDropDownByValue(String xpath, String value) {
        Select select = new Select($(xpath));
        select.selectByValue(value);
    }


}
