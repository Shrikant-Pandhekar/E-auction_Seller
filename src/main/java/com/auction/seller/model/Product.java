package com.auction.seller.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "short_desc")
	private String shortDesc;
	@Column(name = "detailed_desc")
	private String detaileDesc;
	@Column(name = "category")
	private String category;
	@Column(name = "starting_bid")
	private int startingBid;
	@Column(name = "bid_end_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate bidEndDate;
	
		
	
	public Product() {
		super();
	}



	public Product(int productId, String productName, String shortDesc, String detaileDesc, String category,
			int startingBid, LocalDate bidEndDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.shortDesc = shortDesc;
		this.detaileDesc = detaileDesc;
		this.category = category;
		this.startingBid = startingBid;
		this.bidEndDate = bidEndDate;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getShortDesc() {
		return shortDesc;
	}



	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}



	public String getDetaileDesc() {
		return detaileDesc;
	}



	public void setDetaileDesc(String detaileDesc) {
		this.detaileDesc = detaileDesc;
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



	public LocalDate getBidEndDate() {
		return bidEndDate;
	}



	public void setBidEndDate(LocalDate bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	 

	
	
}
