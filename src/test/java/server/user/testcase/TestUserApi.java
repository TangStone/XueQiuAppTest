package server.user.testcase;

import org.junit.jupiter.api.Test;
import server.user.api.UserApi;

import static org.hamcrest.Matchers.equalTo;

public class TestUserApi {
    UserApi User= new UserApi();
    @Test
    public void geUserInfo(){

        String userid = "TangLei";
        User.getUserInfo(userid).then().body("userid",equalTo(userid));
    }
}
