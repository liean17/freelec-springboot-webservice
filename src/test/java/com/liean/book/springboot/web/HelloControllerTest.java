package com.liean.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    /**
     * 웹 API 테스트에 사용, 스프링 MVC 테스트의 시작점이며
     * 이것을 통해 HTTP GET,POST 등에 대한 API 테스트를 할 수 있다.
     */
    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // 요청
                .andExpect(status().isOk())  // Status 검증
                .andExpect(content().string(hello)); // 응답 결과 검증
    }

    @Test
    public void return_helloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) // json 응답값을 필드별로 검증
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}