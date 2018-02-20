package examples.junit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class _4_HamcrestExample_0 {
    private List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void test_core_java() {
        list.add("A");
        assert (list.get(0).equals("A"));
    }

    @Test
    public void test_junit_long() {
        list.add("A");
        assertTrue(list.get(0).equals("A"));
    }

    @Test
    public void test_junit_short() {
        list.add("A");
        assertTrue(list.get(0).equals("A"));
    }

    @Test
    public void test_hamcrest_long() {
        list.add("A");
        Assert.assertThat(list.get(0), Matchers.is("A"));
    }

    @Test
    public void test_hamcrest_short() {
        list.add("A");
        assertThat(list.get(0), is("A"));
    }
    // internal DSL (domain specific language) -
    // внутренний предметно-ориентированный язык программирования -
    // язык программирования, цель которого решать одну заранее поставленную задачу
}
