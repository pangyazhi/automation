package automation.centre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AutomationApplication.class})
@WebAppConfiguration
public class AutomationApplicationTests {


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
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/hello")).andExpect(jsonPath("message", is("OK")));

        logger.info(RepositoryFactory.getInstance().findByName("Project"));
        logger.info(RepositoryFactory.getInstance().findByName("Test Environment"));
    }

}
