package com.company.demo.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetadataExample {
    private static boolean firstConnection = true;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println("conn = " + conn);
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
//         register JDBC driver
        if (firstConnection) {
            Class.forName("com.mysql.jdbc.Driver");
            firstConnection = false;
        }
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exampledb?user=root&password=password&useSSL=false");
    }
}
