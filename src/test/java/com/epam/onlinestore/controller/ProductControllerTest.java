package com.epam.onlinestore.controller;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.service.ProductService;
import com.epam.onlinestore.util.UtilsForTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * RestController was testing for working with products
 */
@Slf4j
@WebMvcTest(value = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProductsTest() throws Exception {
        ProductDto productDto = UtilsForTests.createTestProductDto();

        when(productService.getAllProductsDto()).thenReturn(Collections.singletonList(productDto));

        mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(productDto.getName()));
    }

    @Test
    void getAllProducts_expectException_onError() throws Exception {
        String message = "error message";
        when(productService.getAllProductsDto()).thenThrow(new NullPointerException(message));

        mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(message));
    }

    @Test
    void getProductByIdTest() throws Exception {
        ProductDto productDto = UtilsForTests.createTestProductDto();

        when(productService.getProductById(UtilsForTests.MOCK_ID)).thenReturn(productDto);

        mockMvc.perform(get("/product/{id}", UtilsForTests.MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value(productDto.getName()));
    }

    @Test
    void updateProductTest() throws Exception {
        ProductDto editingProductDto = UtilsForTests.createTestProductDto();

        when(productService.updateProduct(eq(UtilsForTests.MOCK_ID), any())).thenReturn(editingProductDto);

        mockMvc.perform(
                        put("/product/{id}", UtilsForTests.MOCK_ID)
                                .content(objectMapper.writeValueAsString(editingProductDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("price").value(editingProductDto.getPrice()));
    }

    @Test
    public void deleteProductTest() throws Exception {
        willDoNothing().given(productService).deleteProduct(UtilsForTests.MOCK_ID);

        mockMvc.perform(get("/product/{id}", UtilsForTests.MOCK_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createProduct() throws Exception {
        ProductDto productDto = UtilsForTests.createTestProductDto();

        when(productService.addNewProduct(any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(post("/product")
                        .content(objectMapper.writeValueAsString(productDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(UtilsForTests.MOCK_ID));
    }

}
