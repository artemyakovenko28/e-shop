package com.company.eshop.dao.impl;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDaoMock implements ProductDao {
    private final Map<Integer, Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock() {
        memory.put(1, new Product(1, "Bread"));
        memory.put(2, new Product(2, "Paper"));
        memory.put(3, new Product(3, "Sugar"));
        memory.put(4, new Product(4, "Tomatoes"));
    }
    @Override
    public Product selectById(int id) throws NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Product for id '" + id + "'");
        }
        return memory.get(id);
    }

    @Override
    public List<Product> selectAll() throws NoSuchEntityException {
        ArrayList<Product> result = new ArrayList<>(memory.values());
        if (result == null) {
            throw new NoSuchEntityException("No Products at all");
        }
        return result;
    }
}