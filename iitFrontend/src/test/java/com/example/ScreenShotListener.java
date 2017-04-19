package com.example;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;


public class ScreenShotListener implements ITestListener {
    private static final Logger logger = Logger.getLogger(ITestListener.class);

    public void onTestStart(ITestResult iTestResult) {
        // Add some code here if you want to execute it on test start
        // This comment is here because of Sonar code style requirement
    }

    public void onTestSuccess(ITestResult tr) {
    }

    public void onTestFailure(ITestResult tr) {
        try {
            screenshot();
        } catch (IOException e) {
            logger.warn("Can't take screenshot: " + e);
        }

    }

    public void onTestSkipped(ITestResult tr) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Add some code here if you want to execute it on test failed but within success percentage
        // This comment is here because of Sonar code style requirement
    }

    public void onStart(ITestContext iTestContext) {
        // Add some code here if you want to execute it on start
        // This comment is here because of Sonar code style requirement

    }

    public void onFinish(ITestContext iTestContext) {
        // Add some code here if you want to execute it on finish
        // This comment is here because of Sonar code style requirement

    }

    @Attachment(value = "screenshot", type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}