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

public class BasePage {
    static protected AndroidDriver<WebElement> driver;

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


    public void parseSteps(String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String,TestCaseSteps>> typeRefer = new TypeReference<HashMap<String,TestCaseSteps>>(){};

        HashMap<String,TestCaseSteps> testCase = null;
        try {
            testCase = mapper.readValue(this.getClass().getResource("/"+this.getClass().getName().replace('.','/')+".yaml"),typeRefer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(testCase.get(method));
        TestCaseSteps testCaseSteps = testCase.get(method);
        parseSteps(testCaseSteps);
    }

    public static void parseSteps(String method,String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String,TestCaseSteps>> typeRefer = new TypeReference<HashMap<String,TestCaseSteps>>(){};
        System.out.println(path);
        HashMap<String,TestCaseSteps> testCase = null;
        try {
            testCase = mapper.readValue(BasePage.class.getResource(path),typeRefer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(testCase.get(method));
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

            if(steps.get("send") != null){
                element.sendKeys(steps.get("send"));
            }else if(steps.get("attribute") != null){
                element.getAttribute(steps.get("send"));
            } else if(steps.get("text") != null){
                element.getText();
            }else{
                element.click();
            }
        });
    }

}
