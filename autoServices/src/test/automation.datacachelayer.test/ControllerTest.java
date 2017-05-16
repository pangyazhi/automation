package automation.datacachelayer.test;

import automation.datacachelayer.DataCache;
import automation.rest.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by jien.huang on 12/05/2017.
 */



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class ControllerTest {
    private static final Logger logger = LoggerFactory.getLogger("ApplicationTest");

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        DataCache.get_instance().deleteAll();
        DataCache.get_instance().insert("{ '_id' : '001001000000A', 'type' : 'test', data : 'data1'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000B', 'type' : 'case', data : 'data2'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000C', 'type' : 'project', data : 'data3'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000D', 'type' : 'suite', data : 'data4'  }");
        DataCache.get_instance().insert("{ '_id' : '001001000000E', 'type' : 'instance', data : 'data5'  }");

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }




    @Test
    public void ping() throws Exception {

        verify("/ping", 200, null, "{\"message\":\"OK\"}");

    }


    @Test
    public void id() throws Exception {
        verify("/id/001001000000A", 200, null,
                "001001000000A", "data1");
    }


    @Test
    public void update() throws Exception {
        verify("/update", 200, "{\"_id\":\"001001000000A\",\"type\":\"updated\",\"data\":\"update1\"}",
                "001001000000A", "update1", "updatedAt");
    }

    @Test
    public void add() throws Exception {
        verify("/add", 200, "{\"_id\":\"001001000000F\",\"type\":\"add\",\"data\":\"added\"}",
                "001001000000F", "added");
    }

    @Test
    public void search() throws Exception {
        verify("/search?type=(case|project)&data=data.*", 200, null,
                "001001000000B", "001001000000B");
    }

    @Test
    public void delete() throws Exception {
        verify("/delete/001001000000D", 200, null,
                "message", "OK");
    }

    @After
    public void tearDown() {
        //DataCache.get_instance().deleteAll();
    }

    private void verify(String url, int status, String submitData, String... jsonContent) throws Exception {
        RequestBuilder request = null;
        if (url.contains("ping") || url.contains("id") || url.contains("search"))
            request = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        if (url.contains("delete"))
            request = MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON);

        if (url.contains("add") || url.contains("update"))
            request = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON).content(submitData)
                    .contentType(MediaType.APPLICATION_JSON);
        assert request != null;
        String response = this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is(status))
                .andReturn().getResponse().getContentAsString();
        logger.info(response);
        for (String json : jsonContent) {
            Assert.assertTrue(response.contains(json));
        }
    }

}
