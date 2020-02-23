package server;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WeChatToken {
    static WeChatToken weChatToken;
    private String token;
    public static WeChatToken getInstance(){
        if(null == weChatToken){
            weChatToken = new WeChatToken();
        }
        return weChatToken;
    }

    public String getToken(){
         return token = given()
                    .queryParam("corpid","ww9c314cedb3516797")
                    .queryParam("corpsecret","icWpopzL1O47TBKov45lrfzv10ZSVXrLtc-DPbi8ZL0")
                .when()
                    //.log().all()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                    //.log().all()
                    .body("errcode",equalTo(0))
                .extract()
                    .body().path("access_token");
        // System.out.println(tokent);
    }
}
