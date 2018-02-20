package com.company.demo.jdbc.intro;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_50_opened_count {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys?user=root&password=password";

    public static void main(String[] args) throws SQLException {
        open(new com.mysql.jdbc.Driver(), 0);
    }

    private static void open(Driver driver, int count) throws SQLException {
        System.err.println(count);
        try (Connection conn = driver.connect(JDBC_URL, new Properties())) {
            open(driver, count + 1);
        }
    }
}
