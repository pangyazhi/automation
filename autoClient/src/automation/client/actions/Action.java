package automation.client.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jien.huang on 18/10/2016.
 */

/**
 *  An example of action
 *  {"action":"click","data":"", "ui":"{"way":"id", "data":"q"}"}
 */
public abstract class Action {
    Logger logger = LoggerFactory.getLogger("Action");
    public String data;
    public String action;
    public String ui;

    public abstract WebElement findTestObject();

    public void deal(){
        WebElement testObject = findTestObject();
        handle(testObject);
    }

    protected abstract void handle (WebElement testObject);

    public void clear() {
    }
}
