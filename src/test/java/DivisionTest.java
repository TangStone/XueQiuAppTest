import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DivisionTest {

    Division div = new Division();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divid() {

        assertThat(div.divid(4,2),equalTo(2 ));
    }

    @Test
    public void divid1() {
        assertThat(div.divid(3,0),equalTo(null ));
    }

    @Test
    public void divid2() {
        assertThat(div.divid(1,2),equalTo(0 ));
    }

    @Test
    public void divid3() {
        assertThat(div.divid(100000,2),equalTo(null ));
    }

    @Test
    public void divid4() {
        assertThat(div.divid(0,-2),equalTo(3 ));
    }
}