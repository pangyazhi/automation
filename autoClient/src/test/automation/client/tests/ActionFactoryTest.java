package automation.client.tests;

import automation.client.actions.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;

/**
 * Created by jien.huang on 18/10/2016.
 */
public class ActionFactoryTest {
    JsonArray jsonArray;

    @BeforeClass
    public void setUp() throws Exception {
        Gson gson = new Gson();
        jsonArray = (JsonArray) new JsonParser().parse(new JsonReader(new FileReader("./testData.json")));

    }

    private String getTestData(String id) {
        for (int i = 0; i < jsonArray.size(); i++) {
            String _id = jsonArray.get(i).getAsJsonObject().get("id").getAsString();
            if (_id.equalsIgnoreCase(id))
                return jsonArray.get(i).getAsJsonObject().toString();
        }
        return null;
    }

//    @Test
//    public void testWait(){
//        //String message = "{'action':'com.ibm.ie.spm.Wait','data':'5' }";
//        String message = getTestData("Wait5Seconds");
//        Wait action = (Wait) ActionFactory.getAction(message);
//        action.deal();
//        Assert.assertEquals(action.data,"5");
//    }

    @Test
    public void testCheck() {
        String message = "{'action':'automation.client.actions.Check','data':'5' }";
        Check action = (Check) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testClick() {
        String message = "{'action':'automation.client.actions.Click','data':'5' }";
        Click action = (Click) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testEnter() {
        String message = getTestData("EnterText");
        Enter action = (Enter) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testSelect() {
        String message = "{'action':'automation.client.actions.Select','data':'5' }";
        Select action = (Select) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testBrowser() {
        String message = getTestData("StartBrowser");
        StartBrowser startBrowserAction = (StartBrowser) ActionFactory.getAction(message);
        startBrowserAction.deal();

        message = getTestData("Wait5Seconds");
        Wait waitAction = (Wait) ActionFactory.getAction(message);
        waitAction.deal();
        Assert.assertEquals(waitAction.data, "5");

        message = getTestData("CloseBrowser");
        CloseBrowser closeBrowserAction = (CloseBrowser) ActionFactory.getAction(message);
        closeBrowserAction.deal();
    }

    @Test
    public void testVerify() {
        String message = "{'action':'automation.client.actions.Verify','data':'5' }";
        Verify action = (Verify) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testVerifySummary() {
        String message = "{'action':'automation.client.actions.VerifySummary','data':'5' }";
        VerifySummary action = (VerifySummary) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }

    @Test
    public void testVerifyTable() {
        String message = "{'action':'automation.client.actions.VerifyTable','data':'5' }";
        VerifyTable action = (VerifyTable) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }


    @Test
    public void testGetAll() {
        String message = "{'action':'automation.client.actions.GetAll','data':'5' }";
        GetAll action = (GetAll) ActionFactory.getAction(message);
        action.deal();
        Assert.assertEquals(action.data, "5");
    }


    @AfterMethod
    public void tearDown() throws Exception {

    }

}