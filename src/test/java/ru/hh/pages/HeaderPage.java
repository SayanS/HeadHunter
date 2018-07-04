package ru.hh.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeaderPage extends BasePage {
    public String GLOBAL_SEARCH_FIELD = "//input[@data-qa='vacancy-serp__query']";
    public String GLOBAL_SEARCH_BUTTON="(//button[@data-qa='navi-search__button'])[1]";

    @Autowired
    WebBrowser webBrowser;

}
