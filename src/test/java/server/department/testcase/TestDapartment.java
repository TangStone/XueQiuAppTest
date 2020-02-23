package server.department.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.department.api.Department;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestDapartment {
    static Department dapartment = new Department();
    static int parenid = 2;
    @BeforeAll
    public static void beforeAll(){
        ArrayList<Integer> ids  = dapartment.list(parenid).then().body("errmsg", equalTo("ok"))
                .extract().body().path("department.findAll{it->it.parentid==" + parenid + "}.id");
        ids.forEach(id->{
            dapartment.delete(id).then().body("errmsg",equalTo("deleted"));
        });
        System.out.println(ids);
        System.out.println("asddddddddddddddddddddddddddddddddddddddddd");

    }


    @Test
    public void create(){
        String name = "部门1";
        dapartment.create(name,parenid).then().body("errmsg",equalTo("created"));
        dapartment.list(parenid).then().body("department.findAll{it->it.name=='"+name+"'}.id",hasSize(1));
    }

    @Test
    public void list(){
        dapartment.list(parenid).then().body("errmsg",equalTo("ok"));
    }

    @Test
    public void delete(){
        int id = dapartment.create("部门4",parenid).then().body("errmsg",equalTo("created")).extract().body().path("id");
        dapartment.delete(id).then().body("errmsg",equalTo("deleted"));
    }
}
