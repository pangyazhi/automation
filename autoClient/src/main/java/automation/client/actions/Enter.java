package automation.client.actions;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class Enter extends Action {
    public WebElement findTestObject() {
        return findUIObject();
    }

    protected void handle(WebElement testObject) {
        assert testObject!=null;

        testObject.clear();
        if (StringUtils.isNotEmpty(this.data))
        testObject.sendKeys(this.data);
    }
}
