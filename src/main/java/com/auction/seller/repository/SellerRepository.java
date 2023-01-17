package com.auction.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.seller.model.Seller;

 

public interface SellerRepository extends JpaRepository<Seller, Integer> {

}