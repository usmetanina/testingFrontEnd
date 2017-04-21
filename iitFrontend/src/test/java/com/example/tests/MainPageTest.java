package com.example.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.Categories;
import com.example.components.CategoryItemMainPage;
import com.example.pages.DataPage;
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
public class MainPageTest extends BaseTest {
    private static final String CATEGORY_NAME = "Образование";
    private static final String CATEGORY_ID = "6";

    private void openMainPage()
    {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));
    }

    private void registerNewTab()
    {
        Set<String> test=getWebDriver().getWindowHandles();
        switchTo().window((String) test.toArray()[test.size()-1]);
    }

    @Test
    public void filterInstitutionByAddress_DMru_12() throws InterruptedException {
        openMainPage();

        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();

        String before = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();
        $(By.cssSelector("[data-col-name='LegalAddress']")).val("Владимирская").pressEnter();
        String after = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();

        Assert.assertNotEquals(before,after);
    }

    @Test
    public void DisplayReferenceInformation_DMru_13() {
        openMainPage();
        $(withText("Детские развивающие центры")).parent().find(By.id("descLink")).click();
        $(withText("позволяет уточнить название спортивной зоны")).shouldBe(visible);
    }

    /*@Test
    public void DisplayInformationInMapMode_DMru_14() throws InterruptedException {
        openMainPage();
        $(withText("предоставляющие право на бесплатное")).click();
        registerNewTab();
        $(By.cssSelector("a[href='/opendata/7710878000-vysshie-uchebnye-zavedeniya-goroda-moskvy-predostavlyayushchie-pravo-na-besplatnoe-oformlenie-sotsialnoy-karty/data/map?versionNumber=1&releaseNumber=8']"))
                .click();
        //$("svg").shouldBe(visible);
        //$$("img[src='https://apidata.mos.ru/v1/datasets/2105/marker'").get(2).click();

        $$(By.xpath("//*[@id='datasetMap']/div[1]/div[2]/div[3]/img[3]")).get(1).click();
        //$(By.cssSelector("#datasetMap > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > img:nth-child(5)")).click();

        //Assert.assertEquals($(By.id("card")).attr("class"),"map openOnMap"); //открылась панель слева
    }*/

    @Test
    public void FilterTable_DMru_16()  {
        openMainPage();
        $(By.cssSelector("#datasets > div.sticky-list.scroll > ul > li:nth-child(13) > ul > li:nth-child(15) > a")).click();
        registerNewTab();
        $(By.xpath("//*[@id='dictionaryFilter-AdmArea']")).click();
        $(By.cssSelector("#dropFilter-AdmArea > ul > li:nth-child(8)")).click();
        $(By.xpath("//*[@id=\"dictionaryFilter-District\"]")).click();
        $(By.cssSelector("#dropFilter-District > ul > li:nth-child(63)")).click();
    }

    @Test
    public void CopyElementInTable_DMru_15() throws IOException, UnsupportedFlavorException {
        openMainPage();
        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();

        $$("#id_170807727").get(1).find(By.className("copy-card-url-link")).hover();

        executeJavaScript("return document.querySelector('#linkTooltip > span.before').remove();"); //подсказки перекрывали
        executeJavaScript("return document.querySelector('#linkTooltip > span.after').remove();"); //кнопку

        $$("#id_170807727").get(1).find(By.className("copy-card-url-link")).click(); //скопирована в буфер

        String data = (String)Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);

        Assert.assertEquals(data, "https://data.mos.ru/opendata/7719028495-obrazovatelnye-uchrejdeniya-goroda-moskvy/row/170807727");
    }

    @Test
    public void DisplayElementInMap_DMru_18() {
        openMainPage();
        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();

        $(".loader-block").waitWhile(visible, 30000);

        $$("#id_170807727").get(1).find(By.className("map-card-link")).hover().click();
        Assert.assertNotEquals($(By.id("mapCard")).attr("class"),"noCard closed"); //карта открылась
    }
}
