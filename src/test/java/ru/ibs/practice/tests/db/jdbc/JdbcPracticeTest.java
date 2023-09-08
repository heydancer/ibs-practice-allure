package ru.ibs.practice.tests.db.jdbc;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.practice.framework.model.Food;
import ru.ibs.practice.tests.db.jdbc.general.BaseTestDB;

import static ru.ibs.practice.framework.model.FoodType.FRUIT;

@Slf4j
@Owner("Ustiantcev Aleksandr")
@DisplayName("Проверка добавления товара в БД с использованием JDBC")
public class JdbcPracticeTest extends BaseTestDB {
    @BeforeEach
    public void preConditions() {
        dataBaseManager
                .checkTable("FOOD");
    }

    @Test
    @DisplayName("Добавление товара в базу данных")
    @Description("Тестирование функциональности добавления нового товара в базу данных")
    public void testAddingProductInDb() {
        Food foodBeforeAdding = dataBaseManager
                .createTestFood("Дуриан", FRUIT, true);
        Food foodAfterAdding = dataBaseManager
                .addProduct(foodBeforeAdding);

        dataBaseManager
                .checkAddedFoodInTableById(foodAfterAdding.getId());
        dataBaseManager
                .checkFoodForCompliance(foodBeforeAdding, foodAfterAdding);
        dataBaseManager
                .removeProductById(foodAfterAdding.getId());
        dataBaseManager
                .checkThatFoodHasBeenRemoved(foodAfterAdding.getId());
    }
}