package ru.hh.pages;

import org.springframework.stereotype.Component;

@Component
public class HomePage extends HeaderPage {
    String uriHomePage="";
    public HomePage open() {
       return open(HomePage.class, uriHomePage);
    }

}
