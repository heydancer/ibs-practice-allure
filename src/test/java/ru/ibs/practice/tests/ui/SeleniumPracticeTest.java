package ru.ibs.practice.tests.ui;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibs.practice.tests.ui.general.BaseTestUI;

@Slf4j
public class SeleniumPracticeTest extends BaseTestUI {
    private static final String NON_EXOTIC_PRODUCT = "Морковь";
    private static final String EXOTIC_PRODUCT = "Дуриан";

    @BeforeEach
    public void precondition() {
        homePage.open(config.getBaseUrl());
        homePage.navigateToFoodPage();

    }

    @Test
    public void testAddingNonExoticVegetable() {
        foodPage.clickAddProductButton()
                .inputProductName(NON_EXOTIC_PRODUCT)
                .selectTypeOfVegetable()
                .setExoticCheckBoxToFalse()
                .clickSaveButton()
                .checkVegetableToEndTable(NON_EXOTIC_PRODUCT);
    }

    @Test
    public void testAddingExoticVegetable() {
        foodPage.clickAddProductButton()
                .inputProductName(EXOTIC_PRODUCT)
                .selectTypeOfFruit()
                .setExoticCheckBoxToTrue()
                .clickSaveButton()
                .checkFruitToEndTable(EXOTIC_PRODUCT);
    }
}
