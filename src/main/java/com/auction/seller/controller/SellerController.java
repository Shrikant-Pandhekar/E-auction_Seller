package com.auction.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.seller.dto.ShowBidResponse;
import com.auction.seller.model.Product;
import com.auction.seller.model.Seller;
import com.auction.seller.service.SellerService;


@RestController
@RequestMapping("/e-auction/api/v1/seller")
@CrossOrigin
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@PostMapping("/addproduct")
	public Product addproduct(@RequestBody Product newProduct) {
		return sellerService.addProduct(newProduct);
	}
	
	@PostMapping("/addseller")
	public ResponseEntity<String> addSeller(@RequestBody Seller newSeller){
		return sellerService.addSeller(newSeller);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int id) {
		return sellerService.deleteProduct(id);
	}
	
	@GetMapping("/show-bids/{productId}")
	public ResponseEntity<ShowBidResponse> getBidsByProduct(@PathVariable("productId")int id) {
		return sellerService.getBidsByProduct(id);
	}

}
