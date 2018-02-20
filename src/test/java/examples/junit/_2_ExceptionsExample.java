package examples.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _2_ExceptionsExample {
    private List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void test_old_style() {
        try {
            list.get(0);
            // FAIL
            Assert.fail();
        } catch (IndexOutOfBoundsException ignore) {
            // OK
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_new_style() {
        list.get(0);
    }
}
