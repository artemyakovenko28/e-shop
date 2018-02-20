package com.company.demo.jdbc.intro;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class SuperDbDriver0 implements Driver {
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return (Connection) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith("jdbc:SUPER_DB://");
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMajorVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMinorVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean jdbcCompliant() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }
}
