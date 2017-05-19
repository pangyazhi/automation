package automation.client.actions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class Select extends Action {
    public WebElement findTestObject() {
        return findUIObject();
    }

    protected void handle(WebElement testObject) {
        assert testObject != null;
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(testObject);
        try {
            select.selectByVisibleText(this.data);
        }catch (NoSuchElementException ex){
            select.selectByValue(this.data);
        }
    }
}
