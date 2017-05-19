package automation.client.actions;

import org.openqa.selenium.WebElement;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class Click extends Action {
    public WebElement findTestObject() {
        return findUIObject();
    }

    protected void handle(WebElement testObject) {
        assert testObject!=null;
        testObject.click();
    }
}
