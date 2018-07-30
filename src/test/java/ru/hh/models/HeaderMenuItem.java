package ru.hh.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderMenuItem {
    private String language;
    private String menuItemName;
    private String subItemName;
    private Boolean internalLink;
    private String expectedURL;
    private String expectedPageTitle;
}