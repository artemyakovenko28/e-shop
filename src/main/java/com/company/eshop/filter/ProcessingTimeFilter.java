package com.company.eshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessingTimeFilter extends BaseFilter {

//    ProcessingTimeFilter() {
//        System.out.println(">> ProcessingTimeFilter - NEW");
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">> ProcessingTimeFilter - init");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        chain.doFilter(request, response);
        long outTime = System.nanoTime();
        System.out.println(">> ProcessingTimeFilter: dT = " + (outTime - inTime));
        System.out.println();
    }

    @Override
    public void destroy() {
        System.out.println(">> ProcessingTimeFilter - destroy");
    }
}
