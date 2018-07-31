package ru.hh.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.hh.models.TestDataHeaderMenuItem;
import ru.hh.pages.HomePage;
import ru.hh.utils.FilesUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDataHeaderMenuItemTests extends BaseTest {

    @DataProvider(name = "TestDataHeaderMenuItems")
    public static Object[][] headerMenuItems() {
        Object[][] result=null;
        try {
            Map<String, List<TestDataHeaderMenuItem>> testDataHeaderMenu = (new ObjectMapper()).readValue(new File("./src/test/resources/data/headerMenuItems.json"), new TypeReference<Map<String, List<TestDataHeaderMenuItem>>>() {
            });
            List<TestDataHeaderMenuItem> testDataHeaderMenuItem = testDataHeaderMenu.get(FilesUtils.getConfigProperty("localization"));
            result = new TestDataHeaderMenuItem [1][testDataHeaderMenuItem.size()];
            for (int i = 0; i < testDataHeaderMenuItem.size(); i++) {
                result[0][i] = testDataHeaderMenuItem.get(i);
            }
            int i=0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test(enabled = true, dataProvider = "TestDataHeaderMenuItems")
    public void checkInternalLinksOfHeaderMenu() {
        HomePage homePage;
        homePage = getPage(HomePage.class);
        homePage.open();


    }


}
