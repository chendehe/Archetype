package com.chendehe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * mock 方式测试.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestsConfiguration.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TestsConfiguration config;

    @Test
    public void exampleTest() throws Exception {
        System.out.println(config);
        this.mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello World"));
    }

}
