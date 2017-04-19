package com.example.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.open;

public abstract class AbstractPage {

    protected String url;

    public AbstractPage() {
    }

    public AbstractPage(String url) {
        this.url = url;
    }

    private String cleanseUri(String uri) {
        String modifiedUri = uri.split("[?]")[0];
        if ("/".equals(modifiedUri.substring(modifiedUri.length() - 1)))
            return modifiedUri.substring(0, modifiedUri.length() - 1);
        return modifiedUri;
    }

    public AbstractPage shouldBeOpened() {
        waitPageLoaded();
        Assert.assertEquals(cleanseUri(WebDriverRunner.url()), cleanseUri(url));

        return this;
    }

    public AbstractPage navigate(Class pageClass) {
        return ((AbstractPage) open(url, pageClass)).waitPageLoaded();
    }

    public String getUrl() { return url; }

    public abstract AbstractPage waitPageLoaded();
}
