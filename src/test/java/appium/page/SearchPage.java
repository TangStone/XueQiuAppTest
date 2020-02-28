package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;

public class SearchPage extends BasePage{
    private By inputBox = By.id("com.xueqiu.android:id/search_input_text");



    public SearchPage SearchAlibaba(String str)  {
//        MobileElement el2 = (MobileElement) findElement(inputBox);
//        el2.sendKeys(str);
//        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.xueqiu.android:id/name")));
//        findElement(By.id("com.xueqiu.android:id/name")).click();
        HashMap<String,Object> data = new HashMap<>();
        data.put("keyword",str);
        setParams(data);
        parseSteps();
        return this;
    }

    public float getCurrentPrice() {
        //WebElement el4= findElement(By.id("com.xueqiu.android:id/current_price"));
        parseSteps();
        return Float.valueOf(getResult().get("TEXT").toString());
    }

    public SearchPage addOpitionalStock(){
//        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.xueqiu.android:id/follow_btn")));
//        findElement(By.id("com.xueqiu.android:id/follow_btn")).click();

        parseSteps();
        return this;
    }

    public App cancle(){
        //findElement(By.id("com.xueqiu.android:id/action_close")).click();
        parseSteps();
        return new App();
    }
}
