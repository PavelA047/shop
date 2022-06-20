package com.example.shoptest.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tryToStart() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Hello")));
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLogin() throws Exception {
        mockMvc.perform(formLogin("/auth").user("admin").password("pass"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        mockMvc.perform(formLogin("/auth").user("moo").password("moo"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    public void badCredentialsVar2() throws Exception {
        mockMvc.perform(formLogin("/auth").user("moo").password("moo"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-error"));
//                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("")));
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

//    https://support.smartbear.com/alertsite/docs/monitors/api/endpoint/jsonpath.html
//
//    @Test
//    public void getProductByIdAPI() throws Exception {
//        mockMvc.perform(get("/shop/product_info/{id}", 1))
//                .andExpect(MockMvcResultMatchers.jsonPath("id").exists());
//
//        String content = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/shop/product_info/{id}", 1)
//                        .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
//                .andReturn().getResponse().getContentAsString();
//         .andExpect(MockMvcResultMatchers.jsonPath("productCost").exists());
//         .andExpect(MockMvcResultMatchers.jsonPath("id").value(1));
//        String content = result.getResponse().getContentAsString();
//        System.out.println(content);
//        mockMvc.perform(delete("/api/todo/{id}", 1L)
//                        .with(userDetailsService(IntegrationTestUtil.CORRECT_USERNAME))
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().mimeType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$.title", is("Foo")));
//    }

//    @Test
//    public void getAllProductsAPI() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/products")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists());
//         .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].id").isNotEmpty());
//    }

//    @Test
//    public void testSimpleREST() throws Exception {
//        this.mockMvc.perform(get("/rest/message"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello there!"));
//    }
}