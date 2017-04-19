package com.example.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;

public class Categories extends ElementsContainer {
    public CategoryItemMainPage getCategoryById(int id) {
        CategoryItemMainPage categoryItem = new CategoryItemMainPage();
        categoryItem.setSelf(getSelf().find(String.format("[data-id=\"%d\"]", id)));
        return categoryItem;
    }

    public CategoryItemMainPage getCategoryByName(String name) {
        CategoryItemMainPage categoryItem = new CategoryItemMainPage();
        categoryItem.setSelf(getSelf().find(By.xpath(String.format("//*[text()='%s']/../..", name))));
        return categoryItem;
    }

    public ElementsCollection getCategoriesCollection() {
        return getSelf().$$(".item");
    }
}
