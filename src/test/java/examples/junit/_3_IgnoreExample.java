package examples.junit;

import org.junit.Ignore;
import org.junit.Test;

public class _3_IgnoreExample {
    @Test
    public void testOk() {
        System.out.println("This test is ok and done");
    }

    @Ignore
    @Test
    public void test_fail_but_ignored() {
        System.out.println("This test is fail but ignored");
        throw new RuntimeException();
    }
}
