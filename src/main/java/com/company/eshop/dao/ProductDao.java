package com.company.eshop.dao;

import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.entity.Product;

import java.util.List;

//CRUD operations (actions with storage)
//create = SQL/insert = List/add
//read = SQL/select = List/get
//update = SQL/update = List/set
//delete = SQL/delete = List/remove
public interface ProductDao {
    /**
     * Never return null
     */
    Product selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Product> selectAll() throws DaoSystemException, NoSuchEntityException;
}
