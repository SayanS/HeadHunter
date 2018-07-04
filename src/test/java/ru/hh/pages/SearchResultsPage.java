package ru.hh.pages;

import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends HeaderPage{
    String VACANCY_BLOCKS="//div[@data-qa='vacancy-serp__vacancy vacancy-serp__vacancy_premium']";

    public SearchResultsPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getVacancyBlockAmount(){
        return String.valueOf(findAll(VACANCY_BLOCKS).size());
    }


}
