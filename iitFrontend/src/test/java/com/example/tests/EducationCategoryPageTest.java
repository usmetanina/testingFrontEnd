package com.example.tests;

import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.pages.EducationCategoryPage;
import org.testng.annotations.Test;

@Test
@Report
public class EducationCategoryPageTest extends BaseTest {

    @Test
    public void DisplayReferenceInformation_DMru_13() {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.getInformationAboutDataset("Детские развивающие центры");
    }

    @Test
    public void FilterTable_DMru_16()  {
        EducationCategoryPage educationCategoryPage = new EducationCategoryPage();
        educationCategoryPage.openPage();
        educationCategoryPage.filterRowInTable();
    }
}
