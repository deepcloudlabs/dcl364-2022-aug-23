package com.example.event;

public record TradeVolumeEvent(String symbol,double price, double quantity,double volume) {

	public TradeVolumeEvent(TradeEvent te) {
		this(te.symbol(),te.price(),te.quantity(),te.price() * te.quantity());
	}
}
