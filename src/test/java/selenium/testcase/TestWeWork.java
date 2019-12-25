package selenium.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.App;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class TestWeWork {

    static App app;
    @BeforeClass
    public static void beforAll(){
        app = new App();
        app.loginWithCookies();
    }

    @Test
    public void testStart(){
        String ceshi = "18845599643";
        app.toAddMember().addMember(ceshi,ceshi,ceshi);
    }

}
