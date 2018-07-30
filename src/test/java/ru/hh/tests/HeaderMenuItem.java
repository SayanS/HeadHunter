package ru.hh.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.hh.pages.HomePage;

import java.util.ArrayList;
import java.util.Arrays;

public class HeaderMenuItem extends BaseTest{

    @DataProvider(name="HeaderMenuItems")
    public static Object[][] headerMenuItems() {
        HeaderMenuItem headerMenuItem;

        return new Object[][] {
                { "Ищу работу", new ArrayList<String>(Arrays.asList("Создать резюме","Пройти профориентацию","Получить карьерную консультацию","Заказать готовое резюме"))},
                { "testuser_1", "Test@123" }
        };
    }

    @Test(enabled = true, dataProvider = "HeaderMenuItems")
    public void checkInternalLinksOfHeaderMenu() {
        HomePage homePage;
        homePage=getPage(HomePage.class);
        homePage.open();


    }


}
