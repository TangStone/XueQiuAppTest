package appium.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    public static void Start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity","view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",false);
        desiredCapabilities.setCapability("autoGrantPermissions",true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        new WebDriverWait(driver,30).until(x->{
            System.out.println(System.currentTimeMillis());
            String xml = driver.getPageSource();
            Boolean exist =  xml.contains("home_search")||xml.contains("image_cancel");
            System.out.println(exist);
            return exist;
        });

    }


    public static SearchPage toSearch(){
        findElement(By.id("com.xueqiu.android:id/tv_search")).click();
        return new SearchPage();
    }

    public static StockPage toStock(){
        new WebDriverWait(driver,400).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'tab_name') and @text='行情']")));
        findElement(By.xpath("//*[contains(@resource-id,'tab_name') and @text='行情']")).click();
        return new StockPage();
    }
}
