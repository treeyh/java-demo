package com.ejyi.demo.springboot.server.web;

import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2019-01-15 2:51 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unittest")
public abstract class BaseTest {



    private static String DB_URL = "jdbc:h2:mem:springboot_demo;MODE=MYSQL;DB_CLOSE_DELAY=-1";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";

    private static Flyway flyway;



    @BeforeClass
    public static void initMigration() throws SQLException {
        flyway = new Flyway();
        flyway.setDataSource(DB_URL, DB_USER, DB_PASSWORD);
        flyway.setLocations("filesystem:src/test/resources/db/migration");
        flyway.migrate();
    }

    @AfterClass
    public static void cleanMigration() throws SQLException {
//        flyway.clean();
//        if (!connection.isClosed()) {
//            connection.close();
//        }
    }
}
