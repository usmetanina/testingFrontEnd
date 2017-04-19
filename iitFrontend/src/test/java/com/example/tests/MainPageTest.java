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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
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
    public void filterInstitutionByAddress() {
        openMainPage();

        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();

        String before = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();
        $(By.cssSelector("[data-col-name='LegalAddress']")).val("Владимирская").pressEnter();
        String after = $(By.cssSelector("[data-bind='text: window.vm.countString()']")).getText();

        Assert.assertNotEquals(before,after);
    }

    @Test
    public void DisplayReferenceInformation() {
        openMainPage();
        $(withText("Детские развивающие центры")).parent().find(By.id("descLink")).click();
        $(withText("позволяет уточнить название спортивной зоны")).shouldBe(visible);
    }

    @Test
    public void DisplayInformationInMapMode() {
        openMainPage();
        $(withText("предоставляющие право на бесплатное")).click();
        registerNewTab();
        $(By.cssSelector("a[href='/opendata/7710878000-vysshie-uchebnye-zavedeniya-goroda-moskvy-predostavlyayushchie-pravo-na-besplatnoe-oformlenie-sotsialnoy-karty/data/map?versionNumber=1&releaseNumber=8']"))
                .click();
        registerNewTab();
        $$(By.cssSelector("img[src='https://apidata.mos.ru/v1/datasets/2105/marker']")).get(0).click();
    }

    @Test
    public void CopyElementInTable() throws AWTException {
        openMainPage();
        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();
        org.openqa.selenium.Point coordinates = $("#id_170807727").find(By.className("copy-card-url-link")).getLocation();
        Robot robot = new Robot();
        robot.mouseMove(coordinates.getX()-5, coordinates.getY());
        //$("#id_170807727").find(By.className("copy-card-url-link")).click();
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        String s=Keys.CONTROL + "v";
        Assert.assertEquals(s, "https://data.mos.ru/opendata/7719028495-obrazovatelnye-uchrejdeniya-goroda-moskvy/row/170807727");
        //open(Keys.CONTROL + "v");
    }

    @Test
    public void DisplayElementInMap() throws AWTException {
        openMainPage();
        $(withText("Образовательные учреждения города Москвы")).click();
        registerNewTab();
        WebElement element=$("#id_170807727").find(By.className("map-card-link"));

        //((JavascriptExecutor)driver).executeScript("visibility.attr = visible;", element, 10);
        //js.executeScript("$('#id_170807727 .map-card-link').attr('displayed', 'true')");

        Point coordinates = $("#id_170807727").find(By.className("map-card-link")).getLocation();
        Robot robot = new Robot();
        robot.mouseMove(coordinates.getX(), coordinates.getY());
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        Assert.assertEquals($(By.id("mapCard")).attr("class"),"noCard"); //карта открылась

        //getWebDriver();
        //driver.action().mouseMove(element).click().pe‌​rform();
    }
}
