package ru.ibs.practice.tests.ui.general;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.practice.framework.manager.ui.DriverManager;
import ru.ibs.practice.framework.manager.ui.PageManager;
import ru.ibs.practice.framework.manager.common.PropertiesManager;

public class BaseTestUI {
    protected PageManager pageManager = PageManager.getPageManager();
    private final PropertiesManager propertiesManager = PropertiesManager.getPropertiesManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeEach
    public void beforeEach() {
        driverManager
                .getDriver()
                .get(propertiesManager.get("base.url"));
    }

    @AfterAll
    public static void afterAll() {
        driverManager.quitDriver();
    }
}