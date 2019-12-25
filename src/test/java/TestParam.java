import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class TestParam {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {1,3},{2,4},{3,3},{4,4},{3,5},{6,7},
        });
    }
    @Parameterized.Parameter
    public int actaul;

    @Parameterized.Parameter(1)
    public int excpet;

    @Test
    public void testDemo(){
        assertThat("actual value close to 10",actaul,equalTo(excpet));
    }
}
