package ru.hh.tests;

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
        homePage=getPage(HomePage.class);
        searchResultsPage=homePage.open().clickOnGlobalSearchButton();
        Assert.assertEquals(searchResultsPage.getVacancyBlockAmount(),"25");
    }


}
