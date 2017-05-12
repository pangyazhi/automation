package automation.client.actions;

import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by jien.huang on 20/10/2016.
 */
public class Browser {

    public static WebElement findTestObject(JsonObject ui, long timeOut) {
        return findTestObject(ui, 0, timeOut);
    }

    private static WebElement findTestObject(JsonObject ui, int i, long timeOut) {
        List<WebElement> found = find(ui, timeOut);
        if (found == null)
            return null;
        if (found.size() == 0)
            return null;
        if (found.size() <= i)
            return null;
        return found.get(i);
    }

    private static List<WebElement> find(JsonObject ui, long timeOut) {
        List<WebElement> found = null;

        final By way = findBy(ui);
        if(way!=null) {

            WebDriver driver = BrowserInstance.getInstance().get();

            FluentWait wait = new FluentWait<WebDriver>(driver);
            wait.pollingEvery(1, TimeUnit.SECONDS);
            wait.withTimeout(timeOut, TimeUnit.SECONDS);
            Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElements(way) != null;
                }
            };
            wait.until(function);


//            WebDriverWait wait = new WebDriverWait(driver, timeOut * 1000);

//            wait.until(ExpectedConditions.presenceOfElementLocated(way));
//
            found = driver.findElements(way);
        }

        return found;
    }

    private static By findBy(JsonObject ui){
        String way = ui.get("way").getAsString();
        if(StringUtils.isEmpty(way))
            return null;
        String search = ui.get("data").getAsString();
        if(StringUtils.isEmpty(search))
            return null;
        return findBy(way, search);

    }

    private static By findBy(@NotNull String way, @NotNull String search) {
        if (way.equalsIgnoreCase("id"))
            return By.id(search);
        if (way.equalsIgnoreCase("tagname"))
            return By.tagName(search);
        if (way.equalsIgnoreCase("xpath"))
            return By.xpath(search);
        if (way.equalsIgnoreCase("classname"))
            return By.className(search);
        if (way.equalsIgnoreCase("cssselector"))
            return By.cssSelector(search);
        if (way.equalsIgnoreCase("linktext"))
            return By.linkText(search);
        if (way.equalsIgnoreCase("name"))
            return By.name(search);
        if (way.equalsIgnoreCase("partialLinkText"))
            return By.partialLinkText(search);
        return By.xpath("//*[@" + way + "='" + search + "']");

    }


}
