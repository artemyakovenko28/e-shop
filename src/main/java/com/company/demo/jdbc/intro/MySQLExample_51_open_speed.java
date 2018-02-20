package com.company.demo.jdbc.intro;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_51_open_speed {
    public static final String JDBC_URL =
            "jdbc:mysql://localhost:3306/exampledb?user=root&password=password&verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    public static void main(String[] args) throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        for (int k = 0; k < 200; k++) {
            long startTime = System.nanoTime();
            try (Connection conn = driver.connect(JDBC_URL, new Properties())) {
                long endTime = System.nanoTime();
                System.out.printf("%,10d\n", endTime - startTime);
            }
        }
    }
}
