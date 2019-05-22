package com.roman_musijowski.pgs_lessons.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class IndexControllerIT {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username="admin@gmail.com",roles={"STUDENT","ADMIN"})
    public void indexAdmin() throws Exception {


        mvc.perform(get("/","")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(authenticated().withRoles("STUDENT", "ADMIN"))
                .andExpect(view().name("user/indexAdmin"))
                .andExpect(content().string(containsString("user")));
    }

    @Test
    @WithMockUser(username="rushla@gmail.com", roles={"STUDENT"})
    public void indexStudent() throws Exception {

        mvc.perform(get("/","")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(authenticated().withRoles("STUDENT"))
                .andExpect(view().name("lesson/indexStudent"))
                .andExpect(content().string(containsString("user")));

    }

    @Test
    public void indexStudent_unauthenticated() throws Exception {
        mvc
                .perform(formLogin().password("invalid").user("rushla@gmail.com"))
                .andExpect(unauthenticated());
    }

    @Test
    public void indexAdmin_unauthenticated() throws Exception {
        mvc
                .perform(formLogin().password("invalid").user("admin@gmail.com"))
                .andExpect(unauthenticated());
    }

    @Test
    public void logOut() throws Exception {
        mvc.perform(logout("/logout"));
    }
}
