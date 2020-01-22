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
    static protected AndroidDriver driver;

    public static WebElement findElement(By by){
        try {
            System.out.println("这是尝试点击搜索框");
            return driver.findElement(by);
        }catch (Exception e){
            System.out.println("搜索框没有点击成功，来到这里啦");
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void findElementAndClick(By by){
        try {
            System.out.println("这是尝试点击搜索框");
            driver.findElement(by).click();
        }catch (Exception e){
            System.out.println("搜索框没有点击成功，来到这里啦");
            handleAlert();
            driver.findElement(by).click();
        }

    }

    private static void handleAlert() {
        List<By> alertBoxs = new ArrayList<By>();
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        alertBoxs.add(By.id("com.xueqiu.android:id/tv_agree"));
        alertBoxs.forEach(ele -> {
            // new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ele));
            // new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ele));
            System.out.println(ele);
            List<WebElement> ads = driver.findElements(ele);
            System.out.println(driver.findElements(ele).size());
            if (driver.findElements(ele).size() >= 1) {
                ads.get(0).click();
            }
        });
    }

}
