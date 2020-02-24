package server.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import framework.UserApiMethodModel;
import framework.UserApiModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import server.WeChatToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApi {
    HashMap<String,Object> params;

    public void setParam(HashMap<String, Object> params) {
        this.params = params;
    }

    public Response parseSteps(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UserApiModel userApiModel = new UserApiModel();

        String path = "/" + this.getClass().getCanonicalName().replace('.', '/') + ".yaml";
        System.out.println(path);
        try {
            userApiModel = mapper.readValue(BaseApi.class.getResourceAsStream(path), UserApiModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserApiMethodModel userApiMethodModel = new UserApiMethodModel();
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("method is" + method);

        return userApiModel.run(method,params);

    }
}
