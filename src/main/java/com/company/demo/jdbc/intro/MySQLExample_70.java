package com.company.demo.jdbc.intro;

import java.sql.*;

public class MySQLExample_70 {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=password&verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS tmp;");
            stmt.execute("CREATE TABLE tmp (id INT, name VARCHAR(64))");

            stmt.execute("INSERT INTO tmp (id, name) VALUES (1, 'Mike')");
            stmt.execute("INSERT INTO tmp (id, name) VALUES (2, 'Sarah')");
            stmt.execute("INSERT INTO tmp (id, name) VALUES (3, 'John')");

            conn.rollback();

            ResultSet rs = stmt.executeQuery("SELECT id, name FROM tmp");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.printf("%d, %s\n", id, name);
            }

            stmt.execute("DROP TABLE tmp");
        }
    }
}
