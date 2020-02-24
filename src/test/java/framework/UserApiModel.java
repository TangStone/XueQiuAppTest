package framework;

import io.restassured.response.Response;

import java.util.HashMap;

public class UserApiModel {

    private HashMap<String, UserApiMethodModel> getMethods() {
        return methods;
    }

    public HashMap<String,UserApiMethodModel> methods = new HashMap<>();

    public Response run(String method, HashMap<String, Object> params){
        return this.getMethods().get(method).run(params);
    }

}
