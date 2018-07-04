package ru.hh.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsPage extends HeaderPage{
    @Autowired
    WebBrowser webBrowser;
    String VACANCY_BLOCKS="//div[@data-qa='vacancy-serp__vacancy vacancy-serp__vacancy_premium']";

    public String getVacancyBlockAmount(){
        return String.valueOf(findAll(VACANCY_BLOCKS).size());
    }


}
