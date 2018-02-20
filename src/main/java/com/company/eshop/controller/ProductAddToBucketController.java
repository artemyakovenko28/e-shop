package com.company.eshop.controller;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.dao.impl.ProductDaoMock;
import com.company.eshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.company.eshop.controller.SessionAttributes.*;
import static java.util.Collections.*;

public class ProductAddToBucketController extends HttpServlet {
    public static final String PARAM_ID = "id";
    public static final String ERROR_PAGE = "productAll.do";

    private final ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
            Integer id = Integer.valueOf(idStr);
                Product product = productDao.selectById(id);

                HttpSession session = request.getSession(true);
                Map<Product, Integer> oldBucket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BUCKET);
                if (oldBucket == null) {
                    session.setAttribute(PRODUCTS_IN_BUCKET, singletonMap(product, 1));
                } else {
                    Map<Product, Integer> newBucket = new LinkedHashMap<>(oldBucket);
                    if (!oldBucket.containsKey(product)) {
                        newBucket.put(product, 1);
                    } else {
                        newBucket.put(product, newBucket.get(product) + 1);
                    }
                    session.setAttribute(PRODUCTS_IN_BUCKET, unmodifiableMap(newBucket));
                }
                // OK
                String newLocation = "product.do?id=" + id;
                response.sendRedirect(newLocation);
                return;
            } catch (DaoSystemException | NoSuchEntityException | NumberFormatException e) {
                e.printStackTrace();
            }

            response.sendRedirect(ERROR_PAGE);
        }
    }
}
