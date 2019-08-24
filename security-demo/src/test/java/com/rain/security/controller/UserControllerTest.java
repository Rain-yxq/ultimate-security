package com.rain.security.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 使用MockMVC对http接口编写测试用例
 * @author 左边
 * @date 2019/8/24 12:35 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testWhenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("userName","rain"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void testWhenQueryWithPageSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/page")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userName", "rain")
                .param("age", "18")
                .param("ageTo", "60")
                .param("size", "10")
                .param("page", "3")
                .param("sort", "age,desc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void testWhenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("rain"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"rain\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .content(content))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {

        // 创建一个一年以后的时间来测试birthday上的@Past校验
        Date date = new Date(LocalDateTime.now().plusYears(1)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()); // 使用系统默认的时区并转成毫秒
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\", \"username\":\"rain\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
