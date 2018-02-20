package com.company.eshop.controller;

import com.company.eshop.dao.ProductDao;
import com.company.eshop.dao.exception.DaoException;
import com.company.eshop.dao.exception.DaoSystemException;
import com.company.eshop.dao.exception.NoSuchEntityException;
import com.company.eshop.dao.impl.jdbc.tx.TransactionManager;
import com.company.eshop.entity.Product;
import com.company.eshop.inject.DependencyInjectionServlet;
import com.company.eshop.inject.Inject;
import com.company.eshop.util.ClassName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductAllControllerExternalTx extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productAll.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("productDao")
    private ProductDao productDao;
//    private final ProductDao productDao = new ProductDaoJdbcImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> model = txManager.doInTransaction(() -> productDao.selectAll());
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
            response.sendRedirect(PAGE_ERROR);
        }
    }
}
