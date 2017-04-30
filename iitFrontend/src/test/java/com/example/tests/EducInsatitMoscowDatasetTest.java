package com.example.tests;

import com.example.pages.EducationalInstitutionsMoscowDataset;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by home-pc on 30.04.2017.
 */
public class EducInsatitMoscowDatasetTest {

    @Test
    public void DisplayElementInMap_DMru_18() {
        EducationalInstitutionsMoscowDataset dataset = new EducationalInstitutionsMoscowDataset();
        dataset.openDataset();
        Assert.assertNotEquals(dataset.getCardClassAfterOpenCard(),"noCard closed"); //карта открылась
    }

    @Test
    public void CopyElementInTable_DMru_15() throws IOException, UnsupportedFlavorException {
        EducationalInstitutionsMoscowDataset dataset = new EducationalInstitutionsMoscowDataset();
        dataset.openDataset();

        String data = dataset.getLinkOnElementTable();
        Assert.assertEquals(data, "https://data.mos.ru/opendata/7719028495-obrazovatelnye-uchrejdeniya-goroda-moskvy/row/170807727");
    }

    @Test
    public void filterInstitutionByAddress_DMru_12() throws InterruptedException {
        EducationalInstitutionsMoscowDataset dataset = new EducationalInstitutionsMoscowDataset();
        dataset.openDataset();

        String before = dataset.getCountElementInTable();
        dataset.inputFilterInTable();
        String after = dataset.getCountElementInTable();

        Assert.assertNotEquals(before,after);
    }
}
