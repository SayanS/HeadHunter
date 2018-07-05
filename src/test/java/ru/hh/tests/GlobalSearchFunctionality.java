package ru.hh.tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import ru.hh.api.EndPoints;
import ru.hh.api.RestAssuredConfiguration;
import ru.hh.pages.HomePage;
import ru.hh.pages.SearchResultsPage;

import java.util.Arrays;
import java.util.regex.Matcher;
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

        searchResultsPage.getVacancyIdListOfActivePage()
                .forEach(id -> {
//            requestSpecification.queryParam("employeeId", 200).log().all();
                    String str;
                    Pattern p = Pattern.compile(">(.*?)<");


                    Response response = given().spec(requestSpecification).get(EndPoints.GET_VACANCIES + "/" + id);
                    str=response.getBody().jsonPath().get("description").toString();
                    Matcher m = p.matcher(str);

                    Arrays.stream(searchText.split(" ")).forEach(text -> response.then().body("description", org.hamcrest.Matchers.containsString(text)));
                });
    }

}
