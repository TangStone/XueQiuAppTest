package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    static protected AndroidDriver<WebElement> driver;

    public static WebElement findElement(By by){
        try {
            return driver.findElement(by);
        }catch (Exception e){
            System.out.println("没有找到元素，来到这里啦");
            System.out.println(by);
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void findElementAndClick(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            System.out.println("没有找到元素，来到这里啦");
            System.out.println(by);
            handleAlert();
            driver.findElement(by).click();
        }

    }

    public static List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    private static void handleAlert() {
        List<By> alertBoxs = new ArrayList<By>();
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        alertBoxs.add(By.id("com.xueqiu.android:id/tv_agree"));
        alertBoxs.add(By.id("com.xueqiu.android:id/tv_left"));
        alertBoxs.forEach(ele -> {
            List<WebElement> ads = driver.findElements(ele);
            if (driver.findElements(ele).size() >= 1) {
                ads.get(0).click();
            }
        });
    }

}
