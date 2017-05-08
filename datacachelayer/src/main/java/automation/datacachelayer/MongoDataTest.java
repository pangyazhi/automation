package automation.datacachelayer;

import org.testng.Assert;

/**
 * Created by jien.huang on 08/05/2017.
 */
public class MongoDataTest {
    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() throws Exception {
    }

    @org.testng.annotations.Test
    public void testGet_instance() throws Exception {
        Assert.assertNotNull(MongoData.get_instance());
    }

    @org.testng.annotations.Test(expectedExceptions = AssertionError.class)
    public void testInsertWithNoId() throws Exception {
        String noIdJsonString = "{ }";
        //TODO
    }

}