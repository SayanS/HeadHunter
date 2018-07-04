package ru.hh.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

public class HeaderMenu extends BaseTest{

    @Test(enabled = true)
    public void checkHeaderMenuLinks(){
        Assert.assertTrue(true);
    }

    @Test(enabled = true)
    public void check111111111GlobalSearchFunctionality() {
        HomePage homePage;
        SearchResultsPage searchResultsPage;
        homePage.open("");
        homePage.clickOn(homePage.GLOBAL_SEARCH_BUTTON);
        Assert.assertEquals("22",searchResultsPage.getVacancyBlockAmount());
    }


}
