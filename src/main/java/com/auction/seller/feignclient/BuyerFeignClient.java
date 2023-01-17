package com.auction.seller.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.auction.seller.model.Buyer;

@FeignClient(value = "Buyer", url = "http://localhost:8082/e-auction/api/v1/buyer")
public interface BuyerFeignClient {
	@GetMapping("/getBuyerList/{productId}")
	public List<Buyer> getBuyerListByProductId(@PathVariable("productId") int productId);

	@GetMapping("/getSortedBuyerList/{productId}")
	public List<Buyer> getSortedBuyerListByProductId(@PathVariable("productId") int productId);
}
