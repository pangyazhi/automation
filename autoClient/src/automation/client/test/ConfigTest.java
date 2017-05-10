package automation.client.test;

import automation.client.Config;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by jien.huang on 20/10/2016.
 */
public class ConfigTest {
    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testConfig(){
        Assert.assertEquals("false", Config.getInstance().get("debug")); //my.properties will override common.properties
        Assert.assertEquals("true", Config.getInstance().get("noExistKey","true")); //default value work

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

}