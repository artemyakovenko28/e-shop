package com.company.demo.jdbc.intro;

import java.sql.*;

public class MySQLExample_71 {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/exampledb?user=root&password=password";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL);
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO tmp (id, name) VALUES (1, 'Mike')");
            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        }
    }
}
