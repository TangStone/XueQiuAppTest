import org.junit.*;
import org.junit.experimental.categories.Category;

public class TestJunit4DemoChilren extends TestJunit4Demo{

    @AfterClass
    public static void beforeclassDemoChilren(){
        System.out.println("ChilrenafterDemoClass");
    }

    @BeforeClass
    public static void beforClassDemoChilren(){
        System.out.println("ChilrenbeforDemoClass");
    }

    @After
    public void afterDemoChilren(){
        System.out.println("ChilrenafterDemo");
    }

    @Before
    public void beforDemoChilren(){
        System.out.println("ChilrenbeforDemo");
    }

    @Test
    public void testDemoChilren(){
        System.out.println("Chilren testDemo");
    }

    @Category(SlowTests.class)
    @Test
    public void testDemo1Chilren(){
        System.out.println("Chilren testDemo1 Slow category");
    }

    @Category({SlowTests.class,FastTests.class})
    @Test
    public void testDemo2Chilren(){
        System.out.println("Chilren testDemo1 slow fast category");
    }

}
