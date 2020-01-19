package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddressBook extends BasePage{

    public AddressBook addMember(String username, String acctid, String mobile){
        findElement(By.id("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(acctid);
        findElement(By.name("mobile")).sendKeys(mobile);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public void listMemeber(){

    }

    public AddressBook deleteMember(String keyWord){
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(keyWord);
        try{
            waitClickable(By.linkText("编辑"));
        } catch (Exception e) {
            System.out.println("not found");
            findElement(By.id("clearMemberSearchInput")).click();
            return this;
        }
        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.id("clearMemberSearchInput")).click();
        return this;
    }

    public void deleteCurrentPage(){
        waitClickable(By.cssSelector(".js_unactive_warn"));
        waitClickable(By.cssSelector(".ww_checkbox"));
        List<WebElement> elements =  driver.findElements(By.cssSelector(".ww_checkbox"));
        System.out.println(elements.size());
        for(int i=2;i<elements.size();i++){
            System.out.println(i);
            elements.get(i).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
    }

    public void fileImport(String path){
        findElement(By.partialLinkText("批量导入/导出")).click();
        findElement(By.linkText("文件导入")).click();
        findElement(By.id("js_upload_file_input"),0).sendKeys(path);
        findElement(By.linkText("确认导入")).click();
        findElement(By.linkText("前往查看")).click();


    }
}
