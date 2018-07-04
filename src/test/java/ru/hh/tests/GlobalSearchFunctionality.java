package ru.hh.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

public class GlobalSearchFunctionality extends BaseTest {

    @Test(enabled = true)
    public void checkGlobalSearchFunctionality() {
        HomePage homePage=getPage(HomePage.class).open();
        homePage.enterIntoGlobalSearchField("QA Automation");
        SearchResultsPage searchResultsPage=homePage.clickOnGlobalSearchButton();
        Assert.assertEquals(searchResultsPage.getVacancyBlockAmount(),"21");
    }

}
