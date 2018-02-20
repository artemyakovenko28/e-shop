package com.company.eshop.dao.impl.jdbc.tx;

@FunctionalInterface
public interface UnitOfWork<T, E extends Exception> {
    public T doInTx() throws E;
}
