package com.company.eshop.controller.demo;

import com.company.eshop.entity.MockEntityA;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MVCMockController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add to REQUEST attributes
        request.setAttribute("requestAttribute", new MockEntityA());
        // add to SESSION attributes
        request.getSession().setAttribute("sessionAttribute", new MockEntityA());
        // add to SERVLET_CONTEXT attributes
        request.getServletContext().setAttribute("servletContextAttribute", new MockEntityA());

        request.setAttribute("test", "request");
        request.getSession().setAttribute("test", "session");
        request.getServletContext().setAttribute("test", "servletContext");

        request.getRequestDispatcher("mvcMockView.jsp").forward(request, response);
    }
}
