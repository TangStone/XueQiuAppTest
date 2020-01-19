package appium.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    public static void Start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity","view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    }


    public static SearchPage toSearch(){
        findElement(By.id("com.xueqiu.android:id/tv_search")).click();
        return new SearchPage();
    }
}
