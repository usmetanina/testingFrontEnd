package com.example.pages;
import com.example.components.Categories;
import org.openqa.selenium.By;
import java.util.Set;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class EducationCategoryPage {
    private static final String CATEGORY_NAME = "Образование";
    private static final String CATEGORY_ID = "6";

    public void openPage()
    {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));
    }

    public void clickOnDataset(String name){
        $(withText(name)).click();
        registerNewTab();
    }

    private void registerNewTab()
    {
        Set<String> test=getWebDriver().getWindowHandles();
        switchTo().window((String) test.toArray()[test.size()-1]);
    }

    public void filterRowInTable(){
        $(By.cssSelector("#datasets > div.sticky-list.scroll > ul > li:nth-child(13) > ul > li:nth-child(15) > a")).click();
        registerNewTab();
        $(By.xpath("//*[@id='dictionaryFilter-AdmArea']")).click();
        $(By.cssSelector("#dropFilter-AdmArea > ul > li:nth-child(8)")).click();
        $(By.xpath("//*[@id=\"dictionaryFilter-District\"]")).click();
        $(By.cssSelector("#dropFilter-District > ul > li:nth-child(63)")).click();
    }

    public void getInformationAboutDataset(String name)
    {
        $(withText(name)).parent().find(By.id("descLink")).click();
        $(withText("Набор данных позволяет")).shouldBe(visible);
    }
}
