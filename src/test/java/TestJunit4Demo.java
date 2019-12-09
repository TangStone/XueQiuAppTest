import org.junit.Assert;
import org.junit.Test;

public class TestJunit4Demo {
    @Test
    public void testDemo(){
        Assert.assertTrue(true);
    }

    @Test
    public void testDemo1(){
        Assert.assertTrue(false);
    }

}
