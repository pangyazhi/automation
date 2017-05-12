package automation.datacachelayer.test;

import automation.rest.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        //RepositoryFactory.getInstance().setRepository(repository);
        //initData();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }




    @Test
    public void ping() throws Exception {

        this.mockMvc.perform(get("/ping"));

    }


}
