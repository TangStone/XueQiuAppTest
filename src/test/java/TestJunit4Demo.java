import org.junit.*;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.closeTo;


public class TestJunit4Demo {
    @BeforeClass
    public static void beforeclassDemo(){
        System.out.println("beforeclassDemo");
    }

    @AfterClass
    public static void afterclassDemo(){
        System.out.println("afterclassDemo");
    }

    @After
    public void afterDemo(){
        System.out.println("afterDemo");
    }

    @Before
    public void beforDemo(){
        System.out.println("beforDemo");
    }

    @Test
    public void testDemo(){
        System.out.println("testDemo");
        assertThat("actual value close to 10",10.0,anyOf(closeTo(10,0.1),equalTo(9.88)));
    }

    @Category({SlowTests.class})
    @Test
    public void testDemo1(){
        System.out.println("testDemo1 slow category");
    }

    @Category(FastTests.class)
    @Test
    public void testDemo2(){
        System.out.println("testDemo2 fast category");
    }

}
