package com.example.market.dto;

public class RestErrorMessage {

	private final String reason;

	public RestErrorMessage(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

}
