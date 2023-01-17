package com.auction.seller.exception;

import java.util.Date;

public class ErrorDeatils {
	
	private String msg;
	private Date timestamp;
	
	
	public ErrorDeatils(String msg, Date timestamp) {
		super();
		this.msg = msg;
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
}
