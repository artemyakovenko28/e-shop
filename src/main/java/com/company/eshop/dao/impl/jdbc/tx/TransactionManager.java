package com.company.eshop.dao.impl.jdbc.tx;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface TransactionManager {
    <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E;
}
