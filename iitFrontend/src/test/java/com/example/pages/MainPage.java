package com.example.pages;

import com.example.components.Categories;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends AbstractPage {
    public MainPage() {
        super();
        this.url = "https://data.mos.ru/";
    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(By.className("banner-items")).waitUntil(visible, 30000);
        return this;
    }

    public Categories getCategories() {
        Categories categories = new Categories();
        categories.setSelf($("#categories"));
        return categories;
    }
}
