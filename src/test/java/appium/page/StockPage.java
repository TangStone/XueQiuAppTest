package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{
    public StockPage cancelAllAttention(){
//            findElement(By.id("com.xueqiu.android:id/edit_group")).click();
//            findElement(By.id("com.xueqiu.android:id/check_all")).click();
//            findElement(By.id("com.xueqiu.android:id/cancel_follow")).click();
//            findElement(By.id("com.xueqiu.android:id/tv_right")).click();
//            findElement(By.id("com.xueqiu.android:id/action_close")).click();
            parseSteps("cancelAllAttention");
            return this;
    }

    public List<String> checkDefalutStock(){
        List<String> stocks=new ArrayList<>();
        findElements(By.id("com.xueqiu.android:id/portfolio_stockName")).forEach(
                element -> {stocks.add(element.getText());
        });
        System.out.println(stocks);
        return stocks;
    }

    public StockPage oneClickAddOption() {
        findElement(By.id("com.xueqiu.android:id/subscribe")).click();
        return this;
    }

    public SearchPage toSearchPage(){
        findElement(By.id("com.xueqiu.android:id/action_search")).click();
        return new SearchPage();
    }
}
