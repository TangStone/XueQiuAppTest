import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class YamlJacksonExample {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        List<HashMap<String,String>> lmap = new ArrayList<>();

        HashMap<String,String> map1 = new HashMap<>();
        HashMap<String,String> map2 = new HashMap<>();
        map1.put("tang","lei");
        map1.put("song","lei");
        map2.put("tang","lei");
        map2.put("song","lei");
        lmap.add(map1);
        lmap.add(map2);
        TypeReference<List<HashMap<String,String>>> typeReference = new TypeReference<List<HashMap<String, String>>>() {} ;
        String yamlString = null;
        try {
            yamlString = objectMapper.writeValueAsString(lmap);
            System.out.println(yamlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // normally, rethrow exception here - or don't catch it at all.
        }

        try {
             lmap = objectMapper.readValue(yamlString, typeReference);

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}