package com.example.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.Categories;
import com.example.components.CategoryItemMainPage;
import com.example.pages.DataPage;
import com.example.pages.EducationCategoryPage;
import com.example.pages.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.io.Console;
import java.lang.reflect.Array;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
//import static selenium-webdriver.ActionSequence;
import static org.eclipse.jetty.util.LazyList.size;

@Test
@Report
public class EducationCategoryPageTest extends BaseTest {



    @Test
    public void filterInstitutionByAddress_DMru_12() throws InterruptedException {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.clickOnSubcategory("Образовательные учреждения города Москвы");

        String before = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();
        $(By.cssSelector("[data-col-name='LegalAddress']")).val("Владимирская").pressEnter();
        String after = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();

        Assert.assertNotEquals(before,after);
    }

    @Test
    public void DisplayReferenceInformation_DMru_13() {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        $(withText("Детские развивающие центры")).parent().find(By.id("descLink")).click();
        $(withText("позволяет уточнить название спортивной зоны")).shouldBe(visible);
    }

    @Test
    public void FilterTable_DMru_16()  {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.filterRowInTable();
    }

    @Test
    public void CopyElementInTable_DMru_15() throws IOException, UnsupportedFlavorException {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.clickOnSubcategory("Образовательные учреждения города Москвы");

        String data = educationCategoryPage.getLinkOnElementTable();
        Assert.assertEquals(data, "https://data.mos.ru/opendata/7719028495-obrazovatelnye-uchrejdeniya-goroda-moskvy/row/170807727");
    }

    @Test
    public void DisplayElementInMap_DMru_18() {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.clickOnSubcategory("Образовательные учреждения города Москвы");
        Assert.assertNotEquals(educationCategoryPage.getCardClassAfterOpenCard(),"noCard closed"); //карта открылась
    }
}
