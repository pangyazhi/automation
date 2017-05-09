package automation.datacachelayer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jien.huang on 09/05/2017.
 */
public class DataCacheTest {

    Logger logger = LoggerFactory.getLogger("test");

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
        DataCache.get_instance().deleteAll();
        DataCache.get_instance().insert("{ '_id' : '001001000000A', 'type' : 'test', data : 'data1'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000B', 'type' : 'case', data : 'data2'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000C', 'type' : 'project', data : 'data3'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000D', 'type' : 'suite', data : 'data4'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000E', 'type' : 'instance', data : 'data5'  }");

    }

    @org.testng.annotations.AfterMethod
    public void tearDown() throws Exception {
        //clear all data
        DataCache.get_instance().deleteAll();
    }

    @Test
    public void testGet_instance() throws Exception {
        Assert.assertNotNull(DataCache.get_instance());
    }

    @Test
    public void testSearch() throws Exception {
        String found = DataCache.get_instance().search("type=(case|project)", "data=data.*");
        logger.info(found);
        Assert.assertTrue(found.contains("001001000000B") && found.contains("001001000000C"));
    }

    @Test
    public void testGet() throws Exception {
        String found = DataCache.get_instance().get("001001000000E");
        logger.info(found);
        Assert.assertTrue(found.contains("data5"));
    }

    @Test
    public void testUpdate() throws Exception {
        String found = DataCache.get_instance().update("{ '_id' : '001001000000D', 'type' : 'suite', data : 'dataX'  }");
        logger.info(found);
        Assert.assertTrue(found.contains("dataX"));

    }



    @Test
    public void testDelete() throws Exception {
        DataCache.get_instance().delete("0010010000001");
        String found = DataCache.get_instance().get("0010010000001");
        logger.info(found);
        Assert.assertTrue(StringUtils.isEmpty(found));
    }

}