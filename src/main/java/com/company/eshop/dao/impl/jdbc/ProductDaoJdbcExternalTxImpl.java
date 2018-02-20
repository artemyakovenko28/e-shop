package com.company.eshop.dao.impl.jdbc;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcExternalTxImpl implements ProductDao {
    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/exampledb?user=root&password=password&useSSL=false&useSSL=false";

    public static final String SELECT_ALL_SQL = "SELECT id, caption FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT id, caption FROM Products WHERE id=?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Never returns null!
     */
    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No Product for id = " + id);
            }
            return new Product(rs.getInt("id"), rs.getString("caption"));
        } catch (SQLException e) {
            throw new DaoSystemException("Some exception");
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception");
        }
    }
}
