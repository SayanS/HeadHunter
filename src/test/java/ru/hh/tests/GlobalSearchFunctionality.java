package ru.hh.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

public class GlobalSearchFunctionality extends BaseTest {
    @Autowired
    HomePage homePage;
    @Autowired
    SearchResultsPage searchResultsPage;

    @Test(enabled = true)
    public void checkGlobalSearchFunctionality() {
        homePage.open("");
        homePage.clickOn(homePage.GLOBAL_SEARCH_BUTTON);
        Assert.assertEquals("21",searchResultsPage.getVacancyBlockAmount());


        int i = 0;
    }

}
