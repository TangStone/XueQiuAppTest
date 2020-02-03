package appium.testcase;

import appium.page.App;
import appium.page.SearchPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@RunWith(Parameterized.class)
public class TestSearch {
    public static SearchPage searchPage;
    @BeforeClass
    public static void  beforeAll() throws MalformedURLException {
        App.Start();
    }

    @Before
    public void before(){
        searchPage = App.toSearch();
    }

    @Parameterized.Parameters
//    public static Collection<Object[]> data() throws IOException {
//        ObjectMapper maper = new ObjectMapper(new YAMLFactory());
//        Object[][] demo = maper.readValue(TestSearch.class.getResourceAsStream("/"+TestSearch.class.getName().replace(".","/")+".yaml"),Object[][].class);
//        return Arrays.asList(demo);
//    }

    public static Collection<Object[]> data() throws IOException {
//        return Arrays.asList(new Object[][] {
//                { "alibaba", 100f },
//                { "xiaomi", 8f },
//                { "jd", 33f }
//        });

        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        String path="/"+TestSearch.class.getCanonicalName().replace('.', '/')+".yaml";
        Object[][] demo=mapper.readValue(
                TestSearch.class.getResourceAsStream(path),
                Object[][].class);
        return Arrays.asList(demo);
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Double price;

    @Test
    public void searchAlibaba(){
        assertThat(searchPage.SearchAlibaba(stock).getCurrentPrice(), greaterThanOrEqualTo(price.floatValue())) ;
        //searchPage.SearchAlibaba("alibaba").getCurrentPrice();
    }

    @After
    public void after(){
        searchPage.cancle();
    }

}
