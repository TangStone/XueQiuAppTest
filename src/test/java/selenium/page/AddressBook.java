package selenium.page;

import org.openqa.selenium.By;

public class AddressBook extends BasePage{

    public AddressBook addMember(String username, String acctid, String mobile){
        findElement(By.id("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(acctid);
        findElement(By.name("mobile")).sendKeys(mobile);
        return this;
    }

    public void listMemeber(){

    }
}
