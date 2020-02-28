package server.user.testcase;

import org.junit.jupiter.api.Test;
import server.user.api.UserApi;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class TestUserApi {
    UserApi User= new UserApi();
    @Test
    public void geUserInfo(){

        String userid = "TangLei";
        User.getUserInfo(userid).then().log().all()
                .body(matchesJsonSchemaInClasspath("server/user/testcase/schema_getUser.json"));
    }
}
