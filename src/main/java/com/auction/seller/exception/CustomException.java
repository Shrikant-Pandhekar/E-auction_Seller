package com.auction.seller.exception;



public class CustomException extends RuntimeException {

	public CustomException(String string) {
		super(String.format("%s", string));
	}
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -1512629506795331698L;

}
