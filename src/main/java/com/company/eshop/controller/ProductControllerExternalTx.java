package com.company.eshop.controller;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoException;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.dao.impl.jdbc.tx.TransactionManager;
import com.company.eshop.dao.impl.jdbc.tx.UnitOfWork;
import com.company.eshop.entity.Product;
import com.company.eshop.inject.DependencyInjectionServlet;
import com.company.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ProductControllerExternalTx extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";


    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("productDao")
    private ProductDao productDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product model = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException {
                        return productDao.selectById(id);
                    }
                });
                request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                // OK
                request.getRequestDispatcher(PAGE_OK).forward(request, response);
            } catch (NumberFormatException | DaoException e) {
                // ERROR
                response.sendRedirect(PAGE_ERROR);
            }
        }
    }
}

