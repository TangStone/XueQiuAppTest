import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class ParamPass2 {
    public static void main (String[] args)  {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Person person = new Person();
        person.setAge(20);
        person.setName("tanglei");
        person.data.put("tanglei",20);
        person.data.put("zhangqiang",20);
        HashMap<String,String> map = new HashMap<>();
        map.put("wo","ai");
        person.datadata.put("zhang",map);
        person.suzu = new int[]{1,2,3};

        String yamlString = null;
        try {
            yamlString = objectMapper.writeValueAsString(person);
            System.out.println(yamlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // normally, rethrow exception here - or don't catch it at all.
        }

        try {
            Person employee2 = objectMapper.readValue(yamlString, Person.class);
            //System.out.println(employee2.data);

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public void middle(){
        System.out.println(this.getClass().getResource("server/user/api/user.json").getPath());
    }
}

class Person{
    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public void setAge(int age) {
        this.age = age;
    }

    public int age;
    public HashMap<String,Integer> data = new HashMap<>();
    public HashMap<String,HashMap<String,String>> datadata = new HashMap<>();
    public int[] suzu ;

}



