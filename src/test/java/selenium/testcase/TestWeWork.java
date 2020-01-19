package selenium.testcase;

import org.junit.AfterClass;
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

    public static App app;
    @BeforeClass
    public static void beforAll(){
        app = new App();
        app.loginWithCookies();
        String ceshi = "18845599644";
        app.toAddressBook().deleteMember(ceshi);
    }

    @Test
    public void testAdd(){
        String ceshi = "18845599644";
        app.toAddMember().addMember(ceshi,ceshi,ceshi);
    }

    @Test
    public void testDelete(){
        String ceshi = "18845599645";
        app.toAddMember().addMember(ceshi,ceshi,ceshi).deleteMember(ceshi);
    }

    @Test
    public void testDeleteCurrentPage(){
        app.toAddressBook().deleteCurrentPage();
    }

    @Test
    public void testFileImport(){
        app.toAddressBook().fileImport("C:\\Users\\86188\\IdeaProjects\\JUnit4DemoTestSihan\\src\\test\\java\\selenium\\通讯录批量导入模板.xlsx");
    }

    @AfterClass
    public static void  afterall() throws InterruptedException {
        app.quit();
    }

}
