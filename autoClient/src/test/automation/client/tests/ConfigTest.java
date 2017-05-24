package automation.client.tests;

import automation.client.actions.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by jien.huang on 20/10/2016.
 */
public class ConfigTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConfig(){
        Assert.assertEquals("false", Config.getInstance().get("debug")); //my.properties will override common.properties
        Assert.assertEquals("true", Config.getInstance().get("noExistKey","true")); //default value work

    }

    @After
    public void tearDown() throws Exception {

    }

}