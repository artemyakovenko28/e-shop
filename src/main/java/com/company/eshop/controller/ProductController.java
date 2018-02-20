package com.company.eshop.controller;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.dao.impl.ProductDaoMock;
import com.company.eshop.dao.impl.jdbc.ProductDaoJdbcImpl;
import com.company.eshop.entity.Product;
import com.company.eshop.inject.DependencyInjectionServlet;
import com.company.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Inject("productDao")
    private ProductDao productDao;
//    private ProductDao productDao = new ProductDaoJdbcImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product model = productDao.selectById(id);
                request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                request.getRequestDispatcher(PAGE_OK).forward(request, response);
                return;
            } catch (NumberFormatException | DaoSystemException | NoSuchEntityException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(PAGE_ERROR);
    }
}

