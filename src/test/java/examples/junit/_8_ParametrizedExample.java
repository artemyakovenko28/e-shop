package examples.junit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class _8_ParametrizedExample {
    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{{-3, 3}, {-2, 2}, {-1, 1}, {0, 0}, {1, 1}, {2, 2}, {3, 3}});
    }

    @Parameter(0)
    public int input;

    @Parameter(1)
    public int expected;

    @Test
    public void test() {
        Assert.assertThat(Math.abs(input), Matchers.is(expected));
    }
}
