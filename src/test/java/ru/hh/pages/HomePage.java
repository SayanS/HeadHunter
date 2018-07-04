package ru.hh.pages;

import org.openqa.selenium.WebDriver;
import ru.hh.utils.FilesUtils;

public class HomePage extends HeaderPage {

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    public HomePage open(){
        String pathConfigProp = "./src/test/resources/config.properties";
        open(FilesUtils.getProperty(pathConfigProp, "baseUrl"));
        return this;
    }
}
