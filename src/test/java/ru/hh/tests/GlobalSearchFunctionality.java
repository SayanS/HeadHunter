package ru.hh.tests;

import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hh.api.EndPoints;
import ru.hh.api.RestAssuredConfiguration;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class GlobalSearchFunctionality extends BaseTest {

    @Test(enabled = true)
    public void isVacancyDescriptionContainsAnyWordOfSearchText() {
        String searchText = "QA Automation";
        HomePage homePage = getPage(HomePage.class).open();
        homePage.enterIntoGlobalSearchField(searchText);
        SearchResultsPage searchResultsPage = homePage.clickOnGlobalSearchButton();


        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();

        searchResultsPage.getVacancyIdListOfActivePage().forEach(id -> {
            String description;
            Boolean flag=false;
            Pattern p = Pattern.compile("(<)(.*?)(>)");
            description = String.join("", p.split(given().spec(requestSpecification)
                    .get(EndPoints.GET_VACANCIES + "/" + id)
                    .getBody()
                    .jsonPath().get("description").toString()));
            String expectedTexts[] = searchText.split(" ");
            for (int i = 0; i <= expectedTexts.length - 1; i++) {
               if(description.contains(expectedTexts[i])){
                   flag=true;
               }
            }
            Assert.assertTrue(flag, description +"\n of vacancy id="+id +"\n isn't contains any word from\n"+searchText);
        });
    }

}
