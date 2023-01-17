package com.auction.seller.dto;

import java.util.List;

import com.auction.seller.model.Buyer;

 

public class ShowBidResponse {

    private int productId;
    
    private String productName;
	 
	 
 
	private String shortDesc;
 
	private String detailedDesc;
 
	private String category;
 
	private int startingBid;
 
	 
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private List<Buyer> buyes;

	 

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDetailedDesc() {
		return detailedDesc;
	}

	public void setDetailedDesc(String detailedDesc) {
		this.detailedDesc = detailedDesc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStartingBid() {
		return startingBid;
	}

	public void setStartingBid(int startingBid) {
		this.startingBid = startingBid;
	}

	public List<Buyer> getBuyes() {
		return buyes;
	}

	public void setBuyes(List<Buyer> buyes) {
		this.buyes = buyes;
	}
	

}
