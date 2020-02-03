package util;

import appium.testcase.TestSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class testResources {
    public String name;
    public int age;

    public testResources(){
        this.name = "tanglei";
        this.age = 10;
    }

    @Test
    public void readSearchYaml() throws IOException {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(this.getClass().getResource("/appium/testcase/TestSearch.yaml"));
        System.out.println(FileUtils.readFileToString(new File(getClass().getResource("/appium/testcase/TestSearch.yaml").getPath()), "UTF-8"));
    }

    @Test
    public void writeJson() throws IOException {
        ObjectMapper  mapper = new ObjectMapper();
        mapper.writeValue(new File("demo.json"),this);

    }

    @Test
    public void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        testResources demo = mapper.readValue(new File("demo.json"), this.getClass());
        System.out.println(demo);
        System.out.println(demo.name);
    }

    @Test
    public void readYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] demo =  mapper.readValue(this.getClass().getResource("/appium/testcase/TestSearch.yaml"),Object[][].class);
        System.out.println(demo);
        System.out.println(demo[0][1]);
    }





}
