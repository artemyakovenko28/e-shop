package com.company.eshop.dao.impl.jdbc.tx;

import com.company.eshop.dao.impl.jdbc.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceTransactionManager extends BaseDataSource implements TransactionManager {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/exampledb?user=root&password=password&useSSL=false";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    private DataSource connectionPool;

    public void setConnectionPool(DataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E {
        Connection conn = null;
        T result = null;
//        Connection conn = JdbcUtils.getDriver().connect(JDBC_URL, new Properties());
        try {
            conn = connectionPool.getConnection();
            conn.setAutoCommit(false);
            connectionHolder.set(conn);

            result = unitOfWork.doInTx();
            conn.commit();
//            return result;
        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
//            throw new E;
        } finally {
            JdbcUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
        return result;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionHolder.get();
    }
}
