package com.example.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class CategoryItemMainPage extends ElementsContainer {
    public SelenideElement getUnit() {
        return getSelf().find(".unit");
    }

    public SelenideElement getTextElement() {
        return getSelf().find(".text");
    }
}
