package com.company.demo.jdbc.intro;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLExample_30 {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys?user=root&password=password";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.company.demo.jdbc.intro.SuperDbDriver1");

        Enumeration<Driver> iter = DriverManager.getDrivers();
        while (iter.hasMoreElements()) {
            Driver driver = iter.nextElement();
            System.out.println("driver = " + driver);
        }
    }
}
