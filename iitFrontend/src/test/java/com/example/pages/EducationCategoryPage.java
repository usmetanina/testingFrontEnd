package com.example.pages;

import com.example.components.Categories;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by home-pc on 30.04.2017.
 */
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

    public void clickOnSubcategory(String name){
        $(withText(name)).click();
        registerNewTab();
    }
    private void registerNewTab()
    {
        Set<String> test=getWebDriver().getWindowHandles();
        switchTo().window((String) test.toArray()[test.size()-1]);
    }

    public String getLinkOnElementTable() throws IOException, UnsupportedFlavorException {
        $$("#id_170807727").get(1).find(By.className("copy-card-url-link")).hover();

        executeJavaScript("return document.querySelector('#linkTooltip > span.before').remove();"); //подсказки перекрывали
        executeJavaScript("return document.querySelector('#linkTooltip > span.after').remove();"); //кнопку

        $$("#id_170807727").get(1).find(By.className("copy-card-url-link")).click(); //скопирована в буфер

        return (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public String getCardClassAfterOpenCard(){
        $(".loader-block").waitWhile(visible, 30000);
        $$("#id_170807727").get(1).find(By.className("map-card-link")).hover().click();
        return $(By.id("mapCard")).attr("class");
    }

    public void filterRowInTable(){
        $(By.cssSelector("#datasets > div.sticky-list.scroll > ul > li:nth-child(13) > ul > li:nth-child(15) > a")).click();
        registerNewTab();
        $(By.xpath("//*[@id='dictionaryFilter-AdmArea']")).click();
        $(By.cssSelector("#dropFilter-AdmArea > ul > li:nth-child(8)")).click();
        $(By.xpath("//*[@id=\"dictionaryFilter-District\"]")).click();
        $(By.cssSelector("#dropFilter-District > ul > li:nth-child(63)")).click();
    }
}
