package server.restassured;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import server.user.api.UserApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class schemaTest {
    UserApi User= new UserApi();
    @Test
    public void geUserInfo(){

        String userid = "TangLei";
        User.getUserInfo(userid).then().log().all()
                .body("userid",equalTo(userid));
    }

    @Test
    public void authToGithub(){
        given().relaxedHTTPSValidation().auth().oauth2("16147b5b15d537471540d67de283f6d8ec6b1a35")
                .when().log().all().get("https://api.github.com/user/emails")
                .then().log().all().statusCode(200);
    }


    @Test
    void filterTest(){
        given().filter((req,res,ctx)->{
            System.out.println(req.getURI());
            Response resReal = ctx.next(req,res);
            System.out.println(resReal.getStatusCode());
            return resReal;
        })
                .when().log().all().get("http://localhost:8000/jmeter.json")
                .then().statusCode(200);
    }
}
