package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings("deprecation")
public class LegacyObserver {

	public static void main(String[] args) {
		System.err.println("Application is just started!");
		var tradeEvents = List.of(
			new TradeEvent("msft", 100.0, 100.0),	
			new TradeEvent("oracle", 100.1, 100.0),	
			new TradeEvent("msft", 100.2, 100.0),	
			new TradeEvent("oracle", 100.3, 100.0),	
			new TradeEvent("msft", 100.4, 100.0),	
			new TradeEvent("oracle", 100.5, 100.0)	
		);
		var observable = new TradeEventObservable();
		observable.addObserver((o,event) -> {
		    try {TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
		    System.err.println("Slow Observer has processed the event: %s".formatted(event));
		});
		observable.addObserver((o,event) -> {
			System.err.println("Fast Observer has processed the event: %s".formatted(event));
		});
		tradeEvents.forEach(observable::notifyObservers);
		System.err.println("Application is done!");
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}