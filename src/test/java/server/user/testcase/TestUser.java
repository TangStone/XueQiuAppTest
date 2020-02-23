package server.user.testcase;

import org.junit.jupiter.api.Test;
import server.user.api.User;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class TestUser {
    User user = new User();


    @Test
    public void geUserInfo(){
        String userid = "tanglei1993";
        user.getUserInfo(userid).then().body("userid",equalTo(userid));
    }

    @Test
    public void createUser(){

        HashMap<String,Object> data = new HashMap<>();
        String userid = "tanglei" + System.currentTimeMillis();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",userid);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.getUserInfo(userid).then().body("userid",equalTo(userid));

    }

    @Test
    public void deleteUser(){
        HashMap<String,Object> data = new HashMap<>();
        String userid = "tanglei" + System.currentTimeMillis();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",userid);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.deleteUser(userid).then().body("errmsg",equalTo("deleted"));

    }

    @Test
    public void updataUser(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("name","zhangsan");
        String userid = "tanglei1582377193393";
        user.updataUser(userid,data).then().body("errcode",equalTo(0));
        user.getUserInfo(userid).then().body("name",equalTo("zhangsan"));

    }
}
