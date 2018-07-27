package ru.hh.pages;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class SearchResultsPage extends HeaderPage {
    String VACANCY_BLOCKS = "//div[@data-qa='vacancy-serp__vacancy vacancy-serp__vacancy_premium']";
    String VACANCY_BLOCKS_TITLES = "//a[@data-qa='vacancy-serp__vacancy-title']";

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getVacancyBlockAmount() {
        return String.valueOf(findAll(VACANCY_BLOCKS).size());
    }


    public List<String> getVacancyIdListOfActivePage() {
        List<String> vacanciesID = new ArrayList<>();
        findAll(VACANCY_BLOCKS_TITLES).forEach(title -> {
            String[] tmp = title.getAttribute("href").split(Pattern.quote("?"))[0].split("/");
            vacanciesID.add(tmp[tmp.length - 1]);
        });
        return vacanciesID;
    }

    public String getVacancyTitle(String id) {
        AtomicReference<String> vacancyTitle = new AtomicReference<String>();
        findAll(VACANCY_BLOCKS_TITLES).forEach(we -> {
            if (we.getAttribute("href").contains("/vacancy/" + id)) {
                vacancyTitle.set(we.getText());
            }
        });
        return vacancyTitle.toString();
    }

}
