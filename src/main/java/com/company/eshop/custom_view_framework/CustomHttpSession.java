package com.company.eshop.custom_view_framework;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomHttpSession {
    private final Map<String, Object> impl = new ConcurrentHashMap<>();

    public void putAttribute(String name, Object value) {

    }

    public Object getAttribute(String name) {
        return null;
    }

    public Iterator<String> getAttributeNames() {
        return null;
    }
}
