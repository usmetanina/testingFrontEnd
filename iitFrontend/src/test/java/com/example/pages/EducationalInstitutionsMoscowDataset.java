package com.example.pages;

import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Created by home-pc on 30.04.2017.
 */
public class EducationalInstitutionsMoscowDataset {
    public void openDataset()
    {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.clickOnDataset("Образовательные учреждения города Москвы");
    }

    public String getCountElementInTable()
    {
        return $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();
    }

    public void inputFilterInTable()
    {
        $(By.cssSelector("[data-col-name='LegalAddress']")).val("Владимирская").pressEnter();
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
}
