package com.company.eshop.dao.impl.jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JdbcUtils {
    private static Driver instance; // JDBC Driver instance - singleton

    public static synchronized Driver getDriver() throws SQLException {
        if (instance == null) {
            instance = new com.mysql.jdbc.Driver();
        }
        return instance;
    }

    public static void rollbackQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (Exception e) {/*NOP*/}
        }
    }

    public static void closeQuietly(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            closeQuietly(resource);
        }
    }

    public static void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {/*NOP*/}
        }
    }
}
