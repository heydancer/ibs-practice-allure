package ru.ibs.practice.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.practice.tests.ui.general.BaseTestUI;

@Owner("Ustiantcev Aleksandr")
@DisplayName("Проверка добавления продуктов UI")
public class SeleniumPracticeTest extends BaseTestUI {
    private static final String NON_EXOTIC_PRODUCT = "Морковь";
    private static final String EXOTIC_PRODUCT = "Дуриан";
    private static final String VEGETABLE = "Овощ";
    private static final String FRUIT = "Фрукт";
    private static final String FOOD = "Товары";

    @Test
    @DisplayName("Добавление овоща, экзотический = false")
    @Description("Проверка возможности добавить продукт с типом 'Овощ' не экзотический")
    public void testAddingNonExoticVegetable() {
        pageManager.getHomePage()
                .clickOnDropdownMenu()
                .selectBtnInDropdownMenuByText(FOOD)
                .clickAddProductButton()
                .inputProductName(NON_EXOTIC_PRODUCT)
                .selectTypeByText(VEGETABLE)
                .setExoticCheckBox(false)
                .clickSaveButton()
                .checkFoodToEndTable(NON_EXOTIC_PRODUCT, VEGETABLE, false);
    }

    @Test
    @DisplayName("Добавление овоща, экзотический = true")
    @Description("Проверка возможности добавить продукт с типом 'Фрукт' экзотический")
    public void testAddingExoticVegetable() {
        pageManager.getHomePage()
                .clickOnDropdownMenu()
                .selectBtnInDropdownMenuByText(FOOD)
                .clickAddProductButton()
                .inputProductName(EXOTIC_PRODUCT)
                .selectTypeByText(FRUIT)
                .setExoticCheckBox(true)
                .clickSaveButton()
                .checkFoodToEndTable(EXOTIC_PRODUCT, FRUIT, true);
    }
}