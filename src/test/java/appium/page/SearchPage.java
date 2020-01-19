package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage{
    private By inputBox = By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage SearchAlibaba(String str) {
        MobileElement el2 = (MobileElement) findElement(inputBox);
        el2.sendKeys(str);
        findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")).click();
        return this;
    }

    public float getCurrentPrice() {
        WebElement el4= findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        return Float.valueOf(el4.getText());
    }

    public App cancle(){
        findElement(By.id("com.xueqiu.android:id/action_close")).click();
        return new App();
    }
}
