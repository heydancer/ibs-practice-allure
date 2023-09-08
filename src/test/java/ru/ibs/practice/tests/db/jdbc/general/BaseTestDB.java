package ru.ibs.practice.tests.db.jdbc.general;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.ibs.practice.framework.manager.db.DataBaseManager;
import ru.ibs.practice.framework.manager.db.DataSourceManager;
import ru.ibs.practice.framework.manager.db.impl.JDBCManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseTestDB {
    private static Connection connection;
    protected static DataBaseManager dataBaseManager;

    @BeforeAll
    public static void setUp() {
        DataSource dataSource = DataSourceManager.getDataSourceManager().getDataSource();

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить соединение с базой данных");
        }

        dataBaseManager = new JDBCManager(connection);
    }

    @AfterAll
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при закрытии соединения с базой данных");
            }
        }
    }
}