package framework;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import server.WeChatToken;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiMethodModel {
    public HashMap<String,Object> query ;
    public HashMap<String,Object> header ;
    public String method = "get";
    public String url = "";
    public HashMap<String, Object> postBody;
    public String postBodyRaw;

    public Response run(HashMap<String, Object> params) {
        RequestSpecification request = given();
        request.queryParam("access_token", WeChatToken.getInstance().getToken());

        if(query!=null){
            for(Map.Entry<String, Object> i : query.entrySet()){
                request
                        .queryParam(i.getKey(),replace(params,i.getValue().toString()));
            }
        }

        if(header!=null){
            header.entrySet().forEach(entry->{
                request
                        .header(entry.getKey(),replace(params,entry.getValue().toString()));
            });
        }

        if(postBody != null){
            request.body(postBody);
        }

        if(postBodyRaw != null){
            request.body(postBodyRaw);
        }

        return request.when().request(method,url).then().extract().response();
    }

    public String replace(HashMap<String, Object> params,String raw){
        for (Map.Entry<String, Object> kv : params.entrySet()) {
            String matcher = "${" + kv.getKey() + "}";
            if (raw.contains(matcher)) {
                System.out.println(kv);
                raw = raw.replace(matcher, kv.getValue().toString());
            }
        }
        return raw;
    }
}
