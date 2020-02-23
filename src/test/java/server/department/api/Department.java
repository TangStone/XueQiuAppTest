package server.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import server.WeChatToken;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Department {

    public Response create(String name,int parentmentDepartId){
        HashMap<String,Object> data = new HashMap<>();
        data.put("name",name);
        data.put("parentid",parentmentDepartId);

        return given()
                .queryParam("access_token",WeChatToken.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
        .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response list(int parentmentDepartId){
        return given()
                .queryParam("access_token",WeChatToken.getInstance().getToken())
                .queryParam("id",parentmentDepartId)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract()
                .response();
    }

    public Response delete(int DepartmentId){
        return given()
                .queryParam("access_token",WeChatToken.getInstance().getToken())
                .queryParam("id",DepartmentId)
                .when().log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }
}
