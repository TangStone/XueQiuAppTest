package appium.testcase;

import appium.page.App;
import appium.page.StockPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStock {
    public static StockPage stockPage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        App.Start();
        stockPage = App.toStock();
    }

    @BeforeEach
    public void beforEach(){

    }

    @Test
    @Order(1)
    public void addDefaultSelectedStocks(){
        if(stockPage.checkDefalutStock().size()>=1){
            stockPage.cancelAllAttention();
        }
        assertThat(stockPage.oneClickAddOption().checkDefalutStock().size(),greaterThanOrEqualTo(6));
    }

    @ParameterizedTest
    @MethodSource("data")
    @Order(2)
    public void addSearchStock(String code,String name){
        stockPage.toSearchPage().SearchAlibaba(code).addOpitionalStock().cancle();
        assertThat(stockPage.checkDefalutStock(),hasItem(name));
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments("daqin","大秦铁路"),
                Arguments.arguments("haier","海尔智家"));
    }

}
