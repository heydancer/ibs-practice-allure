package ru.ibs.practice.tests.ui;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.practice.tests.ui.general.BaseTestUI;
import ru.ibs.practice.tests.ui.util.XPaths;

@Slf4j
public class SeleniumPracticeTest extends BaseTestUI {
    private static final String NON_EXOTIC_PRODUCT = "Морковь";
    private static final String EXOTIC_PRODUCT = "Дуриан";

    @BeforeEach
    public void preCondition() {
        log.info("Переходим по адресу: {}", config.getBaseUrl());

        driver.get(config.getBaseUrl());

        log.info("Переход на вкладку 'Песочница' -> 'Товары'");

        driver.findElement(XPaths.NAVBAR_DROPDOWN).click();
        driver.findElement(XPaths.BTN_FOOD_IN_NAVBAR_DROPDOWN).click();
    }

    @Test
    public void testAddingNonExoticVegetable() {
        log.info("Шаг 1: Нажатие кнопки 'Добавить'");

        driver.findElement(XPaths.ADD_PRODUCT_BTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(XPaths.ADD_PRODUCT_WIN));

        log.info("Проверка открытия окна 'Добавление товара'");

        Assertions.assertTrue(driver.findElement(XPaths.ADD_PRODUCT_WIN).isDisplayed(),
                "Не отображается окно 'Добавление товара'");
        Assertions.assertTrue(driver.findElement(XPaths.PRODUCT_NAME_FIELD).isDisplayed(),
                "Не отображается поле 'Наименование'");
        Assertions.assertTrue(driver.findElement(XPaths.PRODUCT_TYPE_FIELD).isDisplayed(),
                "Не отображается поле 'Тип'");
        Assertions.assertTrue(driver.findElement(XPaths.EXOTIC_CHECKBOX).isDisplayed(),
                "Не отображается checkbox 'Экзотический'");
        Assertions.assertTrue(driver.findElement(XPaths.SAVE_PRODUCT_BTN).isDisplayed(),
                "Не отображается кнопка 'Сохранить'");
        Assertions.assertTrue(driver.findElement(XPaths.CLOSE_ADD_PROD_WIN).isDisplayed(),
                "Не отображается кнопка 'x' -> 'закрыть окно'");

        log.info("Шаг 2: Ввод в поле 'Наименование' название овоща на кириллице '{}'", NON_EXOTIC_PRODUCT);

        WebElement nameField = driver.findElement(XPaths.FIELD_NAME);
        nameField.sendKeys(NON_EXOTIC_PRODUCT);

        log.info("Проверка введенного значения");

        Assertions.assertEquals(NON_EXOTIC_PRODUCT, nameField.getAttribute("value"),
                String.format("Введенное наименование не соответствует наименованию '%s'", NON_EXOTIC_PRODUCT));

        log.info("Шаг 3: Выбор в выпадающем списке 'Тип' значение 'Овощ'");

        WebElement typeDropdown = driver.findElement(XPaths.BTN_VEGETABLE_IN_TYPE_DROPDOWN);
        typeDropdown.click();

        log.info("Проверка выбранного значения на соответствие типу 'Овощ'");

        Assertions.assertEquals("VEGETABLE", typeDropdown.getAttribute("value"),
                "Атрибут не соответствует типу 'VEGETABLE'");
        Assertions.assertEquals("Овощ", typeDropdown.getText(),
                "Текст не соответствует типу 'Овощ'");

        log.info("Шаг 4: Установка состояние чекбокса 'Экзотический' в false");

        WebElement exoticCheckbox = driver.findElement(XPaths.EXOTIC_CHECKBOX);
        if (exoticCheckbox.isSelected()) {
            exoticCheckbox.click();
        }

        log.info("Проверка состояния чекбокса");

        Assertions.assertFalse(exoticCheckbox.isSelected(),
                "Чекбокс должен быть в состоянии 'false'");

        log.info("Шаг 5: Нажатие кнопки 'Сохранить'");

        driver.findElement(XPaths.SAVE_PRODUCT_BTN).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(XPaths.ADD_PRODUCT_WIN));

        log.info("Проверка закрытия окна 'Добавление товара'");

        Assertions.assertFalse(driver.findElement(XPaths.ADD_PRODUCT_WIN).isDisplayed(),
                "Окно 'Добавление товара' не должно отображаться");

        log.info("Проверка добавления наименования овоща в конец таблицы");

        WebElement lastProduct = driver.findElement(XPaths.LAST_PRODUCT);

        Assertions.assertTrue(lastProduct.getText().contains(NON_EXOTIC_PRODUCT),
                String.format("Последний товар не соответствует наименованию '%s'", NON_EXOTIC_PRODUCT));
        Assertions.assertTrue(lastProduct.getText().contains("Овощ"),
                "Товар не соответствует типу 'Овощ'");
        Assertions.assertTrue(lastProduct.getText().contains("false"),
                "Товар должен быть в состоянии 'false'");
    }

    @Test
    public void testAddingExoticVegetable() {
        log.info("Шаг 1: Нажатие кнопки 'Добавить'");

        driver.findElement(XPaths.ADD_PRODUCT_BTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(XPaths.ADD_PRODUCT_WIN));

        log.info("Проверка открытия окна 'Добавление товара'");

        Assertions.assertTrue(driver.findElement(XPaths.ADD_PRODUCT_WIN).isDisplayed(),
                "Не отображается окно 'Добавление товара'");
        Assertions.assertTrue(driver.findElement(XPaths.PRODUCT_NAME_FIELD).isDisplayed(),
                "Не отображается поле 'Наименование'");
        Assertions.assertTrue(driver.findElement(XPaths.PRODUCT_TYPE_FIELD).isDisplayed(),
                "Не отображается поле 'Тип'");
        Assertions.assertTrue(driver.findElement(XPaths.EXOTIC_CHECKBOX).isDisplayed(),
                "Не отображается checkbox 'Экзотический'");
        Assertions.assertTrue(driver.findElement(XPaths.SAVE_PRODUCT_BTN).isDisplayed(),
                "Не отображается кнопка 'Сохранить'");
        Assertions.assertTrue(driver.findElement(XPaths.CLOSE_ADD_PROD_WIN).isDisplayed(),
                "Не отображается кнопка 'x' -> 'закрыть окно'");

        log.info("Шаг 2: Ввод в поле 'Наименование' название фрукта на кириллице '{}'", EXOTIC_PRODUCT);

        WebElement nameField = driver.findElement(XPaths.FIELD_NAME);
        nameField.sendKeys(EXOTIC_PRODUCT);

        log.info("Проверка введенного значения");

        Assertions.assertEquals(EXOTIC_PRODUCT, nameField.getAttribute("value"),
                String.format("Введенное наименование не соответствует наименованию '%s'", EXOTIC_PRODUCT));

        log.info("Шаг 3: Выбор в выпадающем списке 'Тип' значение 'Фрукт'");

        WebElement typeDropdown = driver.findElement(XPaths.BTN_FRUIT_IN_TYPE_DROPDOWN);
        typeDropdown.click();

        log.info("Проверка выбранного значения на соответствие типу 'Овощ'");

        Assertions.assertEquals("FRUIT", typeDropdown.getAttribute("value"),
                "Атрибут не соответствует типу 'FRUIT'");
        Assertions.assertEquals("Фрукт", typeDropdown.getText(),
                "Текст не соответствует типу 'Фрукт'");

        log.info("Шаг 4: Установка состояние чекбокса 'Экзотический' в true");

        WebElement exoticCheckbox = driver.findElement(XPaths.EXOTIC_CHECKBOX);
        if (!exoticCheckbox.isSelected()) {
            exoticCheckbox.click();
        }

        log.info("Проверка состояния чекбокса");

        Assertions.assertTrue(exoticCheckbox.isSelected(),
                "Чекбокс должен быть в состоянии 'true'");

        log.info("Шаг 5: Нажатие кнопки 'Сохранить'");

        driver.findElement(XPaths.SAVE_PRODUCT_BTN).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(XPaths.ADD_PRODUCT_WIN));

        log.info("Проверка закрытия окна 'Добавление товара'");

        Assertions.assertFalse(driver.findElement(XPaths.ADD_PRODUCT_WIN).isDisplayed(),
                "Окно 'Добавление товара' не должно отображаться");

        log.info("Проверка добавления наименования фрукта в конец таблицы");

        WebElement lastProduct = driver.findElement(XPaths.LAST_PRODUCT);

        Assertions.assertTrue(lastProduct.getText().contains(EXOTIC_PRODUCT),
                String.format("Последний товар не соответствует наименованию '%s'", EXOTIC_PRODUCT));
        Assertions.assertTrue(lastProduct.getText().contains("Фрукт"),
                "Товар не соответствует типу 'Фрукт'");
        Assertions.assertTrue(lastProduct.getText().contains("true"),
                "Товар должен быть в состоянии 'true'");
    }
}
