package com.company.eshop.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextHolder {
    private static final Map<String, ApplicationContext> pathToContextRepository = new HashMap<>();

    private ApplicationContextHolder() {
    }

    static synchronized ApplicationContext getClasspathXmlApplicaionContext(String path) {
        if (!pathToContextRepository.containsKey(path)) {
            pathToContextRepository.put(path, new ClassPathXmlApplicationContext(path));
        }
        return pathToContextRepository.get(path);
    }
}
