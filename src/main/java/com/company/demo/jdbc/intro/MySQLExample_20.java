package com.company.demo.jdbc.intro;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLExample_20 {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys?user=root&password=password";

    public static void main(String[] args) throws SQLException {
        Enumeration<Driver> iter = DriverManager.getDrivers();
        while (iter.hasMoreElements()) {
            Driver driver = iter.nextElement();
            System.out.println("driver = " + driver);
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("conn = " + conn);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:SUPER_DB://")) {
            System.out.println("conn = " + conn);
        }
    }
}
