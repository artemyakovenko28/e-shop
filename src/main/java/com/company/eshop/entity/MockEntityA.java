package com.company.eshop.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockEntityA {
    private String str;
    private String[] array;
    private List<Integer> list;
    private Map<String, String> map;
    private MockEntityB mockEntityB;

    public MockEntityA() {
        this.str = "str";
        this.array = new String[]{"AAA", "BBB", "CCC"};
        this.list = Arrays.asList(123, 456, 789);
        this.map = new HashMap<String, String>() {{put("key-0", "value-0");}};
        this.mockEntityB = new MockEntityB();
    }
}
