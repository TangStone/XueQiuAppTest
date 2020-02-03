package util;

import appium.page.App;
import appium.page.BasePage;
import appium.page.TestCaseSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSteps {

    @Test
    public void testSteps() throws JsonProcessingException {
        HashMap<String , String> map = new HashMap<String , String>();
        map.put("aa","xxx");
        map.put("bb","xxxx");
        List<HashMap<String,String>> listMap = new ArrayList<>();
        listMap.add(map);
        listMap.add(map);

        TestCaseSteps method = new TestCaseSteps();
        method.setStep(listMap);
        HashMap<String,TestCaseSteps> mapTestCase = new HashMap<String,TestCaseSteps>();
        mapTestCase.put("Search",method);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapTestCase));

    }

    @Test
    public void parseSteps() throws IOException {
        App.Start();
        App.toSearch();
        BasePage basePage = new BasePage();
        basePage.parseSteps("SearchAlibaba");
    }
}
