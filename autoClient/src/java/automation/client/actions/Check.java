package automation.client.actions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class Check extends Action {
    public WebElement findTestObject() {
        return findUIObject();
    }

    protected void handle(WebElement testObject) {
        assert testObject!=null;
        boolean toBe = false;
        if(StringUtils.isEmpty(data)||data.equalsIgnoreCase("true")||data.equalsIgnoreCase("yes")||data.equalsIgnoreCase("y")||data.equalsIgnoreCase("on"))
            toBe = true;
        if(testObject.isSelected() != toBe)
            testObject.click();
    }
}
