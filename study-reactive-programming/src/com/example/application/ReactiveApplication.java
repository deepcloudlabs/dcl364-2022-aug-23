package com.example.application;

import java.util.List;
// Flow API : Reactive Programming/Streams
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;
import com.example.event.TradeVolumeEvent;

public class ReactiveApplication {

	public static void main(String[] args) {
		System.err.println("Application is just started!");
		var tradeEvents = List.of(new TradeEvent("msft", 100.0, 100.0), new TradeEvent("oracle", 100.1, 100.0),
				new TradeEvent("msft", 100.2, 100.0), new TradeEvent("oracle", 100.3, 100.0),
				new TradeEvent("msft", 100.4, 100.0), new TradeEvent("oracle", 100.5, 100.0));
		try (SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();) {
			var enrichmentMapper = new TransformProcessor<TradeEvent,TradeVolumeEvent>(TradeVolumeEvent::new);
			publisher.subscribe(enrichmentMapper);
			enrichmentMapper.subscribe(new SlowSubscriber());
			enrichmentMapper.subscribe(new FastSubscriber());
			tradeEvents.forEach(publisher::submit);
			System.err.println("Application is done!");
		}
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (Exception e) {
		}
	}

}

class SlowSubscriber implements Flow.Subscriber<TradeVolumeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SlowSubscriber has just subscribed to the publisher");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeVolumeEvent event) {
		try {
			//TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
		}
		System.err.println("[%s] SlowSubscriber has processed the event: %s".formatted(Thread.currentThread().getName(), event));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable e) {
		System.err.println("An error has occurred in SlowSubscriber: %s".formatted(e.getMessage()));
	}

	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has just finished!");
	}

}

class FastSubscriber implements Flow.Subscriber<TradeVolumeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("FastSubscriber has just subscribed to the publisher");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeVolumeEvent event) {
		System.err.println("[%s] FastSubscriber has processed the event: %s".formatted(Thread.currentThread().getName(),event));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable e) {
		System.err.println("An error has occurred in FastSubscriber: %s".formatted(e.getMessage()));
	}

	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has just finished!");
	}

}
