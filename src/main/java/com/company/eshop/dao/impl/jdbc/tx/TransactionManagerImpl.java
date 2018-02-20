package com.company.eshop.dao.impl.jdbc.tx;

import com.company.eshop.dao.exception.DaoException;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.impl.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/exampledb?user=root&password=password";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E {
        Connection conn = null;
        T result = null;
        try {
            conn = JdbcUtils.getDriver().connect(JDBC_URL, new Properties());
//        Connection conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            connectionHolder.set(conn);
            result = unitOfWork.doInTx();
            conn.commit();
//            return result;
        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
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
