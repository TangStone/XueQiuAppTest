package appium.page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePage {
    static protected AndroidDriver<WebElement> driver;

    public static HashMap<String, Object> getParams() {
        return params;
    }

    public static void setParams(HashMap<String, Object> params) {
        BasePage.params = params;
    }

    static private HashMap<String,Object> params = new HashMap<>();

    public static HashMap<String, Object> getResult() {
        return result;
    }

    public static void setResult(HashMap<String, Object> result) {
        BasePage.result = result;
    }

    private static HashMap<String,Object> result = new HashMap<>();

    public static WebElement findElement(By by){
        try {
            return driver.findElement(by);
        }catch (Exception e){
            System.out.println("没有找到元素，来到这里啦");
            System.out.println(by);
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void findElementAndClick(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            System.out.println("没有找到元素，来到这里啦");
            System.out.println(by);
            handleAlert();
            driver.findElement(by).click();
        }

    }

    public static List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    private static void handleAlert() {
        List<By> alertBoxs = new ArrayList<By>();
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        alertBoxs.add(By.id("com.xueqiu.android:id/tv_agree"));
        alertBoxs.add(By.id("com.xueqiu.android:id/tv_left"));
        alertBoxs.forEach(ele -> {
            List<WebElement> ads = driver.findElements(ele);
            if (driver.findElements(ele).size() >= 1) {
                ads.get(0).click();
            }
        });
    }


    public void parseSteps(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String,TestCaseSteps>> typeRefer = new TypeReference<HashMap<String,TestCaseSteps>>(){};

        HashMap<String,TestCaseSteps> testCase = null;
        try {
            testCase = mapper.readValue(this.getClass().getResource("/"+this.getClass().getName().replace('.','/')+".yaml"),typeRefer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(method);
        TestCaseSteps testCaseSteps = testCase.get(method);
        parseSteps(testCaseSteps);
    }

    public static void parseSteps(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String,TestCaseSteps>> typeRefer = new TypeReference<HashMap<String,TestCaseSteps>>(){};
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(method);
        HashMap<String,TestCaseSteps> testCase = null;
        try {
            testCase = mapper.readValue(BasePage.class.getResource(path),typeRefer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestCaseSteps testCaseSteps = testCase.get(method);
        parseSteps(testCaseSteps);
    }

    public static void parseSteps(TestCaseSteps testCaseSteps){
        testCaseSteps.getStep().forEach(steps->{
            WebElement element = null;
            if (steps.get("id") != null) {
                new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id(steps.get("id"))));
                element = findElement(By.id(steps.get("id")));
            }

            if(steps.get("xpath") != null){
                element = findElement(By.id(steps.get("xpath")));
            }

            if(steps.get("aid") != null){
                element = findElement(By.id(steps.get("aid")));
            }

            String send = steps.get("send");
            if(send != null){
               for(Map.Entry<String,Object> entry:params.entrySet()){
                   String matcher = "${" + entry.getKey() + "}";
                   if(send.contains(matcher)){
                       send = send.replace(matcher,entry.getValue().toString());
                   }

               }
                element.sendKeys(send);
            }else if(steps.get("attribute") != null){
                String str = element.getAttribute(steps.get("attribute"));
                getResult().put(steps.get("attribute"),str);
            } else{
                element.click();
            }
        });
    }

}
