package ru.hh.pages;

import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {
     String GLOBAL_SEARCH_FIELD = "//input[@data-qa='vacancy-serp__query']";
     String GLOBAL_SEARCH_BUTTON="(//button[@data-qa='navi-search__button'])[1]";

    public HeaderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchResultsPage clickOnGlobalSearchButton(){
        clickOn(GLOBAL_SEARCH_BUTTON);
        return new SearchResultsPage(getWebDriver());
    }

    public void enterIntoGlobalSearchField(String text){
        $(GLOBAL_SEARCH_FIELD).sendKeys(text);
    }

}
