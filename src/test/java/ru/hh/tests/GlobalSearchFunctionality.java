package ru.hh.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ru.hh.pages.HomePage;

//@ContextConfiguration("file:src/test/resources/spring.xml")


public class GlobalSearchFunctionality extends BaseTest {
    @Autowired
    private HomePage homePage;

    @Test(enabled = true)
    public void checkGlobalSearchFunctionality() {
        HomePage z;
        z=homePage.open();
                z.clickOn(homePage.GLOBAL_SEARCH_BUTTON);

        int i=0;
    }

}
