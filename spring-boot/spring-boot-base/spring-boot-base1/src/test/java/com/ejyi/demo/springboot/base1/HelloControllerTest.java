package com.ejyi.demo.springboot.base1;

import com.ejyi.demo.springboot.base1.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
    private static final Logger logger  = LoggerFactory.getLogger(HelloControllerTest.class);
    private static final Long sleepTime = 100L;
    private MockMvc mvc;

    @Autowired
    private HelloController helloController;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }


    @Test
    public void testHello() throws Exception {
        assertNotNull(this.mvc);
        for (int i = 0; i < 3; i++) {

            try{

                String content = mvc.perform(get("/service/hello").param("age","33").contentType(MediaType.TEXT_HTML)).andDo(print())
                        .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
                        .andExpect(jsonPath("$.data").exists())
                        .andReturn().getResponse().getContentAsString();
                System.out.println(content);
                break;
            }catch (NestedServletException ex){
                logger.error(ex.getMessage(), ex);
                if(i == 2){
                    throw ex;
                }
                Thread.sleep(sleepTime);
            }
        }
    }



    @Test
    public void testAddUser2() throws Exception {
        assertNotNull(this.mvc);
        for (int i = 0; i < 3; i++) {

            try{

//                String content = mvc.perform(post("/service/addActiveInfo").param("age","33")
//                        .content("{\"name\":\"tree\",\"age\":7}").contentType(MediaType.TEXT_HTML)).andDo(print())
//                        .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
//                        .andExpect(jsonPath("$.data").exists())
//                        .andExpect(jsonPath("$.data.id").exists())
//                        .andReturn().getResponse().getContentAsString();
//                System.out.println(content);

                String content = mvc.perform(get("/service/addActiveInfo").param("code","12")
                        .param("size","11").param("type","1")
                        .contentType(MediaType.TEXT_HTML)).andDo(print())
                        .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
                        .andExpect(jsonPath("$.data").exists())
                        .andExpect(jsonPath("$.data.id").exists())
                        .andReturn().getResponse().getContentAsString();
                System.out.println(content);
                break;
            }catch (NestedServletException ex){
                logger.error(ex.getMessage(), ex);
                if(i == 2){
                    throw ex;
                }
                Thread.sleep(sleepTime);
            }
        }
    }


}
