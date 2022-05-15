package com.young.sarangbang.mvc;

import com.young.sarangbang.controller.home.HomeController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Date : 2022-03-12
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class homeTest {


//    @Autowired
//    MockMvc mockMvc;

    @Test
    public void homeWithMvc() throws Exception {
        String hello = "";

//        mockMvc.perform(get("/home"))
//                .andExpect(status().isOk());
    }


}
