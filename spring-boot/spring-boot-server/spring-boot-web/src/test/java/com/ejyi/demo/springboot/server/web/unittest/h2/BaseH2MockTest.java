package com.ejyi.demo.springboot.server.web.unittest.h2;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @author 余海
 * @version 1.0
 * @description 基于h2内存数据库进行的单元测试， 参考文档：https://github.com/database-rider/database-rider
 * @create 2019-01-17 4:53 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unittest")
@AutoConfigureMockMvc
@DBUnit()
public class BaseH2MockTest {


    private static String DB_URL = "jdbc:h2:mem:springboot_demo;MODE=MYSQL;DB_CLOSE_DELAY=-1";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";


    private static Flyway flyway;

//    private static Connection connection;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.
            instance(() -> flyway.getDataSource().getConnection());


    @BeforeClass
    public static void initMigration() throws SQLException {

        flyway = new Flyway();
        flyway.setDataSource(DB_URL, DB_USER, DB_PASSWORD);
        flyway.setLocations("filesystem:src/test/resources/db/migration");
        flyway.migrate();

//        connection = flyway.getDataSource().getConnection();
//        //add some data to test db cleanup
//        try (Statement stmt = connection.createStatement()) {
//            stmt.addBatch("INSERT INTO active_info VALUES (1, '111111', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');");
//            stmt.addBatch("INSERT INTO active_info VALUES (2, '222222', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');");
//            int[] result = stmt.executeBatch();
//            assertEquals(result.length, 2);
//        }
    }

    @AfterClass
    public static void cleanMigration() throws SQLException {
//        flyway.clean();
//        if (!connection.isClosed()) {
//            connection.close();
//        }
    }
}
