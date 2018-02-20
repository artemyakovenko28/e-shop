package com.company.demo.jdbc.intro;

import java.sql.SQLException;

public class SuperDbDriver1 extends SuperDbDriver0 {
    static {
        try {
            java.sql.DriverManager.registerDriver(new SuperDbDriver1());
        } catch (SQLException e) {
            System.out.println("Can't register driver");
        }
    }
}
