package selenium.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class TestGroupMessage {
    public static App app;
    @BeforeClass
    public static void beforAll(){
        app = new App();
        app.loginWithCookies();
    }

    @Test
    public void toSend(){
        List<String> sendMsg = app.toGroupMessage().send("唐磊", "邮件领取",
                "你有一个邮件需要领取", "领取邮件", "Stone").getSendMsg();
        assertThat(sendMsg,hasItem("邮件领取"));
    }
}
