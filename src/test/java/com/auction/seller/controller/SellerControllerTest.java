package com.auction.seller.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.auction.seller.model.Product;
import com.auction.seller.model.Seller;
import com.auction.seller.service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SellerController.class})
@ExtendWith(SpringExtension.class)
class SellerControllerTest {
    @Autowired
    private SellerController sellerController;

    @MockBean
    private SellerService sellerService;

    
    @Test
    void testAddSeller() throws Exception {
        when(sellerService.addSeller((Seller) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Seller seller = new Seller();
        seller.setAddress("42 Main St");
        seller.setCity("Oxford");
        seller.setEmail("jane.doe@example.org");
        seller.setFirstName("Jane");
        seller.setId(1);
        seller.setLastName("Doe");
        seller.setPhone("4105551212");
        seller.setPin("Pin");
        seller.setState("MD");
        String content = (new ObjectMapper()).writeValueAsString(seller);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-auction/api/v1/seller/addseller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    
    @Test
    void testDeleteProduct() throws Exception {
        when(sellerService.deleteProduct(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/e-auction/api/v1/seller/delete/{productId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

     
    @Test
    void testGetBidsByProduct() throws Exception {
        when(sellerService.getBidsByProduct(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/e-auction/api/v1/seller/show-bids/{productId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

     
    @Test
    void testAddproduct() throws Exception {
        Product product = new Product();
        product.setBidEndDate(LocalDate.ofEpochDay(1L));
        product.setCategory("Category");
        product.setDetaileDesc("Detaile Desc");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setShortDesc("Short Desc");
        product.setStartingBid(1);
        when(sellerService.addProduct((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setBidEndDate(null);
        product1.setCategory("Category");
        product1.setDetaileDesc("Detaile Desc");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setShortDesc("Short Desc");
        product1.setStartingBid(1);
        String content = (new ObjectMapper()).writeValueAsString(product1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-auction/api/v1/seller/addproduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":123,\"productName\":\"Product Name\",\"shortDesc\":\"Short Desc\",\"detaileDesc\":\"Detaile"
                                        + " Desc\",\"category\":\"Category\",\"startingBid\":1,\"bidEndDate\":\"1970-01-02\"}"));
    }
}

