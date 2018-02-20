package com.company.demo.jdbc.intro;

//import com.mysql.jdbc.JDBC4Connection;

import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_10 {
    public static final String JDBC_URL = "jdbc:mysql//127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {
//        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
//        com.mysql.jdbc.JDBC4Connection conn  = (JDBC4Connection) driver.connect(JDBC_URL, new Properties());
//        try {
//            // use connection here
//        } finally {
//            conn.close();
//        }
    }
}
