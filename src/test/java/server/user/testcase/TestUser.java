package server.user.testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import server.user.api.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestUser {
    User user = new User();


    @Test
    public void geUserInfo(){
        String userid = "tanglei1993";
        user.getUserInfo(userid).then().body("userid",equalTo(userid));
    }

    @Test
    public void createUser(){

        HashMap<String,Object> data = new HashMap<>();
        String userid = "tanglei" + System.currentTimeMillis();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",userid);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.getUserInfo(userid).then().body("userid",equalTo(userid));

    }

    @Test
    public void deleteUser(){
        HashMap<String,Object> data = new HashMap<>();
        String userid = "tanglei" + System.currentTimeMillis();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",userid);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.deleteUser(userid).then().body("errmsg",equalTo("deleted"));

    }

    @Test
    public void updataUser(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("name","zhangsan");
        String userid = "tanglei1582377193393";
        user.updataUser(userid,data).then().body("errcode",equalTo(0));
        user.getUserInfo(userid).then().body("name",equalTo("zhangsan"));

    }

    @Test
    public void simplelist(){
        user.simplelist(2,false).then().body("errcode",equalTo(0));
    }

    @Test
    public void batchdelete(){
         ArrayList<String> userids = user.simplelist(2,false).then().body("errcode",equalTo(0))
                .extract().body().path("userlist.userid");
         user.batchdelete(userids).then().body("errcode",equalTo(0));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/server/user/api/deleteUserByCsv.CSV")
    public void deleteUserByCsv(String userid,String name){
        HashMap<String,Object> data = new HashMap<>();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",name);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.deleteUser(userid).then().body("errmsg",equalTo("deleted"));

    }


    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    public void deleteUserByYaml(String userid,String name){
        HashMap<String,Object> data = new HashMap<>();
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
        data.put("userid",userid);
        data.put("name",name);
        data.put("mobile",mobile);

        user.createUser(data).then().body("errcode",equalTo(0));
        user.deleteUser(userid).then().body("errmsg",equalTo("deleted"));

    }

    static Stream<Arguments> stringIntAndListProvider() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());;
        List<HashMap<String,Object>> lmap = new ArrayList<>();
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {} ;
        try {
            lmap = mapper.readValue(TestUser.class.getResourceAsStream("deleteUserByYaml.yaml"),typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Arguments> result = new ArrayList<>();
        lmap.forEach(map->{
            result.add(arguments(map.get("name").toString(),map.get("userid").toString()));
        });
        return result.stream();
    }









}
