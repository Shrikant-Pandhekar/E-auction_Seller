package com.auction.seller.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auction.seller.model.Product;


@FeignClient(value = "Product", url = "http://localhost:8080/e-auction/api/v1/product")
public interface ProductFeignClient {
	
	@PostMapping("/addproduct")
	public Product addproduct(@RequestBody Product newProduct);
	
	@DeleteMapping("/delete/{productId}" )
	public void deleteProduct(@PathVariable("productId")int id);
	
	@GetMapping("/getProduct/{productId}")
	public Product getProductById(@PathVariable("productId")int productId);

}
