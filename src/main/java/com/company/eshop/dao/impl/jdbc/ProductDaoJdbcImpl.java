package com.company.eshop.dao.impl.jdbc;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDaoJdbcImpl implements ProductDao {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/exampledb?user=root&password=password&verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    public static final String SELECT_ALL_SQL = "SELECT id, caption FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT id, caption FROM Products WHERE id=?";

    /**
     * Never return null!
     */
    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
//            conn = DriverManager.getConnection(JDBC_URL);
            conn = JdbcUtils.getDriver().connect(JDBC_URL, new Properties());
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No Product for id = " + id);
            }
            Product result = new Product(rs.getInt("id"), rs.getString("caption"));
            conn.commit();
            return result;
        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
            e.printStackTrace();
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt, conn);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            conn = JdbcUtils.getDriver().connect(JDBC_URL, new Properties());
//            conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_SQL);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Product(rs.getInt("id"), rs.getString("caption")));
            }
            if (result.isEmpty()) {
                throw new NoSuchEntityException("No Products in database");
            }
            return result;
        } catch (NoSuchEntityException | SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt, conn);
        }
    }
}
