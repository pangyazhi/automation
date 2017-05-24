package automation.client.actions;

import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jien.huang on 19/05/2017.
 */
public class Command extends Action {

    @Override
    public WebElement findTestObject() {
        return null;
    }

    @Override
    protected void handle(WebElement testObject) {
        try {
            callCommandString(this.data);
        }catch (Exception ex){
            //TODO handle exception
        }
    }

    private void callCommandString(String cmd) throws IOException {
        Process p = Runtime.getRuntime().exec(cmd);


        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        String s = null;

        String stdInputString = "", stdErrorString = "";
        while ((s = stdInput.readLine()) != null) {
            stdInputString += s;
        }

        while ((s = stdError.readLine()) != null) {
            stdErrorString += s;
        }
        logger.info("Cmd: " + cmd);
        logger.info("Std Output: " + stdInputString);
        logger.info("Std Error: " + stdErrorString);
    }
}
