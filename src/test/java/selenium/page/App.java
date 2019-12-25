package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{

    public App loginWithCookies(){
        String URL = "https://work.weixin.qq.com/";
        System.setProperty("webdriver.chrome.driver", "D:/drivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1855, 839));
        findElement(By.linkText("企业登录")).click();

        driver.manage().addCookie(new Cookie("wwrtx.refid","3450998872389333"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","aWFbRy20wLsngP69FHsey0TNKaxww77XDhOU31yKDVtwaFVUi8ymjCbL-XI8BbLy"));

        driver.navigate().refresh();
        return this;
    }

    public AddressBook toAddressBook(){
        findElement(By.id("menu_contacts")).click();
        return new AddressBook();
    }

    public AddressBook toAddMember(){
        findElement(By.linkText("添加成员")).click();
        return new AddressBook();
    }
}
