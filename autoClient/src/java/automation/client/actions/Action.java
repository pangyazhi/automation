package automation.client.actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    Gson gson = new Gson();

    public abstract WebElement findTestObject();

    public void deal(){
        WebElement testObject = findTestObject();
        handle(testObject);
    }

    protected abstract void handle (WebElement testObject);

    public void clear() {
    }

    public Object NotExpectedFindUIObject(){
        long timeOut = Long.parseLong(String.valueOf(Config.getInstance().get("not.expected.timeout","3")));
        return findGuiTestObject(timeOut);
    }
    public WebElement findUIObject() {
        long timeOut = Long.parseLong(String.valueOf(Config.getInstance().get("implicitly.timeout","30")));
        return findGuiTestObject(timeOut);
    }

    private WebElement findGuiTestObject(long timeOut) {

        if(this.ui==null){
            logger.warn("No UIObject element in the command, don't know how to find the target!");
            return null;
        }
        JsonObject uiObject = gson.fromJson(this.ui, JsonObject.class);
        return Browser.findTestObject(uiObject,timeOut);
    }

    public static String captureScreenToBase64String() throws AWTException, IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        BufferedImage capture = getBufferedImage(0, 0, width, height);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ImageIO.write(capture, "JPG", byteStream);
        String base64String = Base64.encodeBase64String(byteStream.toByteArray());
        byteStream.close();
        capture = null;
        return base64String;

    }

    private static BufferedImage getBufferedImage(int x, int y, int width, int height) throws AWTException {
        Rectangle area = new Rectangle(x, y , width, height);
        Robot robot = new Robot();
        BufferedImage capture = robot.createScreenCapture(area);
        return capture;
    }
}
