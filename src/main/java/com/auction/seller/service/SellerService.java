package com.auction.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auction.seller.dto.ShowBidResponse;
import com.auction.seller.exception.CustomException;
import com.auction.seller.feignclient.BuyerFeignClient;
import com.auction.seller.feignclient.ProductFeignClient;
import com.auction.seller.model.Buyer;
import com.auction.seller.model.Product;
import com.auction.seller.model.Seller;
import com.auction.seller.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ProductFeignClient productFeignClient;

	@Autowired
	BuyerFeignClient buyerFeignClient;

	ShowBidResponse showBidResponse = new ShowBidResponse();

	public Product addProduct(Product newProduct) {
		return productFeignClient.addproduct(newProduct);
	}

	public ResponseEntity<String> addSeller(Seller newSeller) {
		sellerRepository.save(newSeller);
		return new ResponseEntity<>("New Seller Added Sucessfully", HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteProduct(int id) {
		productFeignClient.deleteProduct(id);
		return new ResponseEntity<>("Product Deleted Successfully!!!", HttpStatus.OK);
	}

	public ResponseEntity<ShowBidResponse> getBidsByProduct(int id) {
		List<Buyer> sortedBuyerList = buyerFeignClient.getSortedBuyerListByProductId(id);
		if (sortedBuyerList != null) {
			showBidResponse.setBuyes(sortedBuyerList);
			Product selectedProduct = productFeignClient.getProductById(id);
			showBidResponse.setProductId(selectedProduct.getProductId());
			showBidResponse.setProductName(selectedProduct.getProductName());
			showBidResponse.setShortDesc(selectedProduct.getShortDesc());
			showBidResponse.setDetailedDesc(selectedProduct.getDetaileDesc());
			showBidResponse.setCategory(selectedProduct.getCategory());
			showBidResponse.setStartingBid(selectedProduct.getStartingBid());
			return new ResponseEntity<>(showBidResponse,HttpStatus.OK);
		}
		throw new CustomException("No Product found with this id");
	}

}
