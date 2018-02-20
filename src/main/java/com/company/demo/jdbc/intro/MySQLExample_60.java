package com.company.demo.jdbc.intro;

import java.sql.*;

public class MySQLExample_60 {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys?user=root&password=password";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS tmp;");
            stmt.execute("CREATE TABLE tmp(id INT, name VARCHAR(64));");
            stmt.execute("DROP TABLE tmp;");
        }
    }
}
