package com.auction.seller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auction.seller.dto.ShowBidResponse;
import com.auction.seller.exception.CustomException;
import com.auction.seller.feignclient.BuyerFeignClient;
import com.auction.seller.feignclient.ProductFeignClient;
import com.auction.seller.model.Product;
import com.auction.seller.model.Seller;
import com.auction.seller.repository.SellerRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SellerService.class})
@ExtendWith(SpringExtension.class)
class SellerServiceTest {
    @MockBean
    private BuyerFeignClient buyerFeignClient;

    @MockBean
    private ProductFeignClient productFeignClient;

    @MockBean
    private SellerRepository sellerRepository;

    @Autowired
    private SellerService sellerService;

    
    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setBidEndDate(LocalDate.ofEpochDay(1L));
        product.setCategory("Category");
        product.setDetaileDesc("Detaile Desc");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setShortDesc("Short Desc");
        product.setStartingBid(1);
        when(productFeignClient.addproduct((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setBidEndDate(LocalDate.ofEpochDay(1L));
        product1.setCategory("Category");
        product1.setDetaileDesc("Detaile Desc");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setShortDesc("Short Desc");
        product1.setStartingBid(1);
        assertSame(product, sellerService.addProduct(product1));
        verify(productFeignClient).addproduct((Product) any());
    }

     
    @Test
    void testAddProduct2() {
        when(productFeignClient.addproduct((Product) any())).thenThrow(new CustomException("String"));

        Product product = new Product();
        product.setBidEndDate(LocalDate.ofEpochDay(1L));
        product.setCategory("Category");
        product.setDetaileDesc("Detaile Desc");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setShortDesc("Short Desc");
        product.setStartingBid(1);
        assertThrows(CustomException.class, () -> sellerService.addProduct(product));
        verify(productFeignClient).addproduct((Product) any());
    }

     
    @Test
    void testAddSeller() {
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
        when(sellerRepository.save((Seller) any())).thenReturn(seller);

        Seller seller1 = new Seller();
        seller1.setAddress("42 Main St");
        seller1.setCity("Oxford");
        seller1.setEmail("jane.doe@example.org");
        seller1.setFirstName("Jane");
        seller1.setId(1);
        seller1.setLastName("Doe");
        seller1.setPhone("4105551212");
        seller1.setPin("Pin");
        seller1.setState("MD");
        ResponseEntity<String> actualAddSellerResult = sellerService.addSeller(seller1);
        assertEquals("New Seller Added Sucessfully", actualAddSellerResult.getBody());
        assertEquals(HttpStatus.CREATED, actualAddSellerResult.getStatusCode());
        assertTrue(actualAddSellerResult.getHeaders().isEmpty());
        verify(sellerRepository).save((Seller) any());
    }

     
    @Test
    void testAddSeller2() {
        when(sellerRepository.save((Seller) any())).thenThrow(new CustomException("New Seller Added Sucessfully"));

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
        assertThrows(CustomException.class, () -> sellerService.addSeller(seller));
        verify(sellerRepository).save((Seller) any());
    }

     
    @Test
    void testDeleteProduct() {
        doNothing().when(productFeignClient).deleteProduct(anyInt());
        ResponseEntity<String> actualDeleteProductResult = sellerService.deleteProduct(1);
        assertEquals("Product Deleted Successfully!!!", actualDeleteProductResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteProductResult.getStatusCode());
        assertTrue(actualDeleteProductResult.getHeaders().isEmpty());
        verify(productFeignClient).deleteProduct(anyInt());
    }

     
    @Test
    void testDeleteProduct2() {
        doThrow(new CustomException("Product Deleted Successfully!!!")).when(productFeignClient).deleteProduct(anyInt());
        assertThrows(CustomException.class, () -> sellerService.deleteProduct(1));
        verify(productFeignClient).deleteProduct(anyInt());
    }

     
    @Test
    void testGetBidsByProduct() {
        when(buyerFeignClient.getSortedBuyerListByProductId(anyInt())).thenReturn(new ArrayList<>());

        Product product = new Product();
        product.setBidEndDate(LocalDate.ofEpochDay(1L));
        product.setCategory("Category");
        product.setDetaileDesc("Detaile Desc");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setShortDesc("Short Desc");
        product.setStartingBid(1);
        when(productFeignClient.getProductById(anyInt())).thenReturn(product);
        ResponseEntity<ShowBidResponse> actualBidsByProduct = sellerService.getBidsByProduct(1);
        assertTrue(actualBidsByProduct.hasBody());
        assertTrue(actualBidsByProduct.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBidsByProduct.getStatusCode());
        ShowBidResponse body = actualBidsByProduct.getBody();
        assertEquals("Detaile Desc", body.getDetailedDesc());
        assertEquals("Category", body.getCategory());
        assertTrue(body.getBuyes().isEmpty());
        assertEquals(1, body.getStartingBid());
        assertEquals(123, body.getProductId());
        assertEquals("Short Desc", body.getShortDesc());
        verify(buyerFeignClient).getSortedBuyerListByProductId(anyInt());
        verify(productFeignClient).getProductById(anyInt());
    }

     
    @Test
    void testGetBidsByProduct2() {
        when(buyerFeignClient.getSortedBuyerListByProductId(anyInt())).thenReturn(new ArrayList<>());
        when(productFeignClient.getProductById(anyInt())).thenThrow(new CustomException("String"));
        assertThrows(CustomException.class, () -> sellerService.getBidsByProduct(1));
        verify(buyerFeignClient).getSortedBuyerListByProductId(anyInt());
        verify(productFeignClient).getProductById(anyInt());
    }
}

