package com.example.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class CategoryItemDataPage extends ElementsContainer {
    public SelenideElement getIcon() {
        return getSelf().find(".category-icon");
    }

    public SelenideElement getTextElement() {
        return getSelf().$(".category-caption");
    }

    public SelenideElement getCountElement() {
        return getSelf().find(".count");
    }
}
