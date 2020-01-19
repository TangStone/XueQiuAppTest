package selenium.page;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BroadcastPage extends BasePage{
    public BroadcastPage send(String range,String title,String body,String summary,String authour){
        findElement(By.linkText("选择需要发消息的应用")).click();
        findElement(By.cssSelector(".ww_icon_AppNotice")).click();
        findElement(By.linkText("确定")).click();
        findElement(By.linkText("选择发送范围")).click();
        findElement(By.id("memberSearchInput")).sendKeys(range);
        findElement(By.cssSelector(".ww_searchResult_title_peopleName")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
        driver.switchTo().frame("ueditor_0");
        findElement(By.cssSelector(".msg_noticeEditor_frameBody")).sendKeys(body);
        driver.switchTo().defaultContent();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        findElement(By.cssSelector(".msg_edit_infoItem_textWord")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);
        findElement(By.cssSelector(".js_amrd_sendName")).sendKeys(authour);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        findElement(By.linkText("发送")).click();
        findElement(By.linkText("确定")).click();
        return this;
    }

    public List<String> getSendMsg(){
        findElement(By.linkText("已发送")).click();
        List<String> msg = new ArrayList<String>();
        driver.findElements(By.cssSelector(".msg_history_msgList_td")).forEach(element->{
           msg.add(element.getText());
            System.out.println(element.getText());
        });
        return msg;
    }
}
