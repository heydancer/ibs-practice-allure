package ru.ibs.practice.tests.db.spring.general;

import org.junit.jupiter.api.BeforeAll;
import ru.ibs.practice.framework.manager.db.DataBaseManager;
import ru.ibs.practice.framework.manager.db.DataSourceManager;
import ru.ibs.practice.framework.manager.db.impl.SpringJDBCManager;

public class BaseTestDB {
    private static final DataSourceManager dataSourceManager = DataSourceManager.getDataSourceManager();
    protected static DataBaseManager dataBaseManager;

    @BeforeAll
    public static void setUp() {
        dataBaseManager = new SpringJDBCManager(dataSourceManager.getDataSource());
    }
}