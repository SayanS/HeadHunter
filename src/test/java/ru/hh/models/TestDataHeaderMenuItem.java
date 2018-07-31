package ru.hh.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestDataHeaderMenuItem {
    public String menuItemName;
    public List<TestDataHeaderMenuItemDetails> optionsNameOfInternalLinks;
    public List<TestDataHeaderMenuItemDetails> optionsNameOfExternalLinks;
}
