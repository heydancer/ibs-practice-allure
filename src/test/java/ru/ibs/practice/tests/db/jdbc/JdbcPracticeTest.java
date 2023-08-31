package ru.ibs.practice.tests.db.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ibs.practice.tests.db.jdbc.general.BaseTestDB;
import ru.ibs.practice.tests.db.model.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcPracticeTest extends BaseTestDB {
    private static final String SQL_SHOW_TABLES = "SHOW TABLES;";
    private static final String SQL_INSERT_FOOD = "INSERT INTO food(food_name, food_type, food_exotic) VALUES (?, ?, ?);";
    private static final String SQL_SELECT_ALL_FOOD = "SELECT * FROM food;";
    private static final String SQL_SELECT_FOOD_ID = "SELECT food_id FROM food WHERE food_id = ?;";
    private static final String SQL_DELETE_FOOD = "DELETE FROM food WHERE food_id = ?;";
    private Food lastFood;

    @Test
    public void testAddingProductInDb() {
        try {
            preCondition();
            firstStep();
            secondStep();
            postCondition();
        } catch (SQLException e) {
            Assertions.fail("Ошибка при добавлении продукта в базу данных", e);
        }
    }

    private void preCondition() throws SQLException {
        log.info("Проверка наличия таблицы 'FOOD' в схеме БД");

        preparedStatement = connection.prepareStatement(SQL_SHOW_TABLES);
        ResultSet rs = preparedStatement.executeQuery();

        boolean tableExists = false;

        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase("food")) {
                tableExists = true;
                break;
            }
        }

        Assertions.assertTrue(tableExists, "Таблица 'FOOD' не найдена в БД");
    }

    private void firstStep() throws SQLException {
        log.info("Вставка новой записи в таблицу 'FOOD'");

        preparedStatement = connection.prepareStatement(SQL_INSERT_FOOD);
        preparedStatement.setString(1, "Морковь");
        preparedStatement.setString(2, "VEGETABLE");
        preparedStatement.setInt(3, 0);

        int rowsInserted = preparedStatement.executeUpdate();

        Assertions.assertEquals(1, rowsInserted, "Запись не была добавлена в таблицу");
    }

    private void secondStep() throws SQLException {
        log.info("Выполнение SQL-запроса для получения списка всех товаров таблицы 'FOOD'");

        List<Food> foodList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_FOOD);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            foodList.add(Food.builder()
                    .id(rs.getInt("food_id"))
                    .name(rs.getString("food_name"))
                    .type(rs.getString("food_type"))
                    .exotic(rs.getInt("food_exotic"))
                    .build());
        }

        lastFood = foodList.get(foodList.size() - 1);

        Assertions.assertFalse(foodList.isEmpty(),
                "Записи не найдены");
        Assertions.assertNotNull(lastFood.getId(),
                "ID не должен быть null");
        Assertions.assertEquals("Морковь", lastFood.getName(),
                "Значение столбца 'food_name' не соответствует значению 'Морковь'");
        Assertions.assertEquals("VEGETABLE", lastFood.getType(),
                "Значение столбца 'food_type' не соответствует значению 'VEGETABLE'");
        Assertions.assertEquals(0, lastFood.getExotic(),
                "Значение столбца 'food_exotic не соответствует значению '0' -> не экзотический");
    }

    private void postCondition() throws SQLException {
        log.info("Выполнение SQL-запроса для удаления записи из таблицы 'FOOD'");

        preparedStatement = connection.prepareStatement(SQL_DELETE_FOOD);
        preparedStatement.setInt(1, lastFood.getId());

        int rowsDeleted = preparedStatement.executeUpdate();

        Assertions.assertEquals(1, rowsDeleted,
                "Запись не была удалена из таблицы");

        preparedStatement = connection.prepareStatement(SQL_SELECT_FOOD_ID);
        preparedStatement.setInt(1, lastFood.getId());
        ResultSet rs = preparedStatement.executeQuery();

        Assertions.assertFalse(rs.next(),
                String.format("Запись с ID = %d не должна быть в таблице 'FOOD'", lastFood.getId()));
    }
}