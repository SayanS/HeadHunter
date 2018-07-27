package ru.hh.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hh.api.ApiUtils;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

import java.util.Arrays;
import java.util.regex.Pattern;

public class GlobalSearchFunctionality extends BaseTest {

    @Test(enabled = true)
    public void isVacancyTitleOrDescriptionContainsAnyWordOfSearchText() {
        String searchText = "QA Automation";
        HomePage homePage = getPage(HomePage.class).open();
        homePage.enterIntoGlobalSearchField(searchText);
        SearchResultsPage searchResultsPage = homePage.clickOnGlobalSearchButton();

        searchResultsPage.getVacancyIdListOfActivePage().forEach(id -> {
            String vacancyDescription;
            Boolean flag=false;

            Pattern htmlTagPattern = Pattern.compile("(<)(.*?)(>)");
            vacancyDescription = String.join("", htmlTagPattern.split(ApiUtils.getVacancyValueOf(id,"description")));

            String expectedTexts[] = searchText.split(" ");

            for (int i = 0; i <= expectedTexts.length - 1; i++) {
               if((searchResultsPage.getVacancyTitle(id)+vacancyDescription).contains(expectedTexts[i])){
                   flag=true;
                   break;
               }
            }
            Assert.assertTrue(flag, vacancyDescription +"\n of vacancy id="+id +"\n isn't contains any word from\n"+ Arrays.toString(expectedTexts));
        });
    }


}
