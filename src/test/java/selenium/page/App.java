package selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{

    public App loginWithCookies(){
        String URL = "https://work.weixin.qq.com/";
        System.setProperty("webdriver.chrome.driver", "D:/drivers/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1855, 839));
        findElement(By.linkText("企业登录")).click();

        driver.manage().addCookie(new Cookie("wwrtx.refid","3450998872389333"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","aWFbRy20wLsngP69FHseywPn3wuuv4CgPzztUWhuOrpvSClr2ZQy-MKNYSjDroXT"));

        driver.navigate().refresh();
        return this;
    }

    public AddressBook toAddressBook(){
        findElement(By.id("menu_contacts")).click();
        return new AddressBook();
    }

    public AddressBook toAddMember(){
       // waitClickable(By.linkText("添加成员"));
        findElement(By.linkText("添加成员")).click();
        return new AddressBook();
    }

    public BroadcastPage toGroupMessage(){
        findElement(By.linkText("管理工具")).click();
        findElement(By.cssSelector(".ww_icon_AppGroupMessageBig")).click();
        return new BroadcastPage();
    }
}
