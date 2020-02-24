package server.user.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.response.Response;
import server.WeChatToken;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApi extends BaseApi{

    public Response getUserInfo(String userid){
//        return given()
//                .queryParam("userid",userid)
//                .queryParam("access_token", WeChatToken.getInstance().getToken())
//                .when().log().all()
//                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
//                .then().body("errcode",equalTo(0)).log().all()
//                .extract().response();
        HashMap<String,Object> params= new HashMap<>();
        params.put("userid",userid);
        setParam(params);
        return parseSteps();

    }

    public Response createUser(HashMap<String,Object> data){
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        System.out.println(this.getClass().getResource("/server/user/api/user.json").getPath());
        Mustache mustache = mf.compile(this.getClass().getResource("/server/user/api/user.json").getPath());
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return given()
                .queryParam("access_token", WeChatToken.getInstance().getToken())
                .body(writer.toString())
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all()
                .extract().response();
    }

    public Response deleteUser(String userid){
        return given()
                .queryParam("access_token", WeChatToken.getInstance().getToken())
                .queryParam("userid",userid)
                .when().log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all()
                .extract().response();
    }

    public Response updataUser(String userid,HashMap<String,Object> data){
        data.put("userid",userid);
        return given()
                .queryParam("access_token", WeChatToken.getInstance().getToken())
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all()
                .extract().response();
    }

    public Response simplelist(int department_id,boolean fetch_child){
        return given()
                .queryParam("access_token", WeChatToken.getInstance().getToken())
                .queryParam("department_id",department_id)
                .queryParam("fetch_child",fetch_child)
                .when().log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all()
                .extract().response();

    }

    public Response batchdelete(ArrayList<String> userids) {
        HashMap<String,Object> data = new HashMap<>();
        data.put("useridlist",userids);
        return given()
                .queryParam("access_token", WeChatToken.getInstance().getToken())
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
                .then().log().all()
                .extract().response();


    }
}
