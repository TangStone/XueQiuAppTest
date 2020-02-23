package server.department;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestWork {
    static String tokent;
    int parentmentDepartId = 2;
    @BeforeAll
    public static void getToken(){
        tokent = given()
                .queryParam("corpid","ww9c314cedb3516797")
                .queryParam("corpsecret","icWpopzL1O47TBKov45lrfzv10ZSVXrLtc-DPbi8ZL0")
        .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
        .then()
                .log().all()
                .body("errcode",equalTo(0))
        .extract()
                .body().path("access_token");
       // System.out.println(tokent);

    }

    @Test
    public void createDepartment(){
        HashMap<String,Object> data = new HashMap<>();

        data.put("name","部门1");
        data.put("parentid",parentmentDepartId);
        given()
                .queryParam("access_token",tokent)
                .contentType(ContentType.JSON)
                .body(data)
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
        .then()
                .log().all()
                .body("errcode",equalTo(0));

    }
}
