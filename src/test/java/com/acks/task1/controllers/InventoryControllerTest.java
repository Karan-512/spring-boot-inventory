package com.acks.task1.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;



import com.acks.task1.models.Inventory;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @InjectMocks


    Inventory newInventoryItem = new Inventory();
    


// The hard coded list from the controller file
//  public  List<Inventory> inventoryList = new ArrayList<>(Arrays.asList(
//         new Inventory(1, "iPhone 14 pro deep purple 128gb", 999.00, 100),
//         new Inventory(2, "AirPods Pro 2nd Generation", 249.00, 250),
//         new Inventory(3, "Apple Watch Series 7", 450.00, 150)));    



    @BeforeEach
    void setUp(){
        
        newInventoryItem = new Inventory(1, "MacBook Air", 1599.00, 50);
        
    }

    @Test
    void testAddInventoryItem() throws Exception {
        mockMvc.perform(delete("/inventory/deleteInventoryItem/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testDeleteInventoryItem() {

    }

    @Test
    void testGetAllProducts_shouldReturnAllItems() throws Exception{
       mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("iPhone 14 pro deep purple 128gb")))
                .andExpect(jsonPath("$[1].name", is("AirPods Pro 2nd Generation")))
                .andExpect(jsonPath("$[2].name", is("Apple Watch Series 7")));
    }

    @Test
    void testGetInventoryDetailbyID() throws Exception {

       mockMvc.perform(get("/inventory/1"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.name", is("iPhone 14 pro deep purple 128gb")))
       .andExpect(jsonPath("$.price", is(999.00)))
       .andExpect(jsonPath("$.quantity", is(100)))
       .andDo(MockMvcResultHandlers.print());

    }


    @Test
    void testUpdateInventoryItem() throws Exception {
        Inventory updatedItem = new Inventory(1, "iPhone 14 pro max", 1099.00, 80);

        mockMvc.perform(put("/inventory/updateInventoryItem/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedItem)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("iPhone 14 pro max")))
        .andExpect(jsonPath("$.price", is(1099.00)))
        .andExpect(jsonPath("$.quantity", is(80)));

        mockMvc.perform(get("/inventory/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("iPhone 14 pro max")))
        .andExpect(jsonPath("$.price", is(1099.00)))
        .andExpect(jsonPath("$.quantity", is(80)));
    }
}
