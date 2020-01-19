package appium.testcase;

import appium.page.App;
import appium.page.SearchPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"alibaba",100f},{"baidu",10f},{"JD",9f}
        });
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Float price;

    @Test
    public void searchAlibaba(){
        assertThat(searchPage.SearchAlibaba(stock).getCurrentPrice(), greaterThanOrEqualTo(price)) ;
        //searchPage.SearchAlibaba("alibaba").getCurrentPrice();
    }

    @After
    public void after(){
        searchPage.cancle();
    }

}
