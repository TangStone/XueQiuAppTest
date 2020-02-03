package appium.page;

import java.util.HashMap;
import java.util.List;

public class TestCaseSteps {
    public List<HashMap<String, String>> getStep() {
        return step;
    }

    public void setStep(List<HashMap<String, String>> step) {
        this.step = step;
    }

    private List<HashMap<String,String>> step;
}
