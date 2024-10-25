package com.student.enroll.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUser() throws Exception {
        String userJson = "{\"fullName\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"username\":\"jambo\",\"password\":\"Habimana97\"}";
       
        // Step 1: Create the user
        MvcResult result= mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk()) .andReturn();
            //   System.out.println("fffffffffff"+result.getResponse().getContentAsString());
    }
    
    @Test
    public void testLoginAndAccessSecuredEndpoint() throws Exception {
        // Step 2: Log in with the same credentials
        String loginJson = "{\"username\":\"jambo@gmail.com\", \"password\":\"Habimana97\"}";
        
        // Step 3: Perform the login request
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(loginJson))
                    .andExpect(status().isOk())  // Expect OK status for successful login
                    .andReturn();
        
        // Parse the JSON response to extract the token
        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        String jwtToken = jsonNode.get("data").asText();
        
        // Step 5: Use JWT token to access a secured endpoint
        mockMvc.perform(get("/api/auth/me")
                .header("Authorization", "Bearer " + jwtToken))  // Pass JWT token
                .andExpect(status().isOk());
    }
}
