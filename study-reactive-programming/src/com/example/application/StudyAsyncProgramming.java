package com.example.application;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class StudyAsyncProgramming {

	public static void main(String[] args) {
		System.err.println("Application is just started!");
//		var result = longRunningMethod();
//		System.out.println("Result is %d.".formatted(result));
		asyncLongRunningMethod().thenAcceptAsync( result -> {
			System.err.println("[%s] returns %d.".formatted(Thread.currentThread().getName(),result));
		} );
		for (var i=1;i<100;++i) {
			System.err.println("[%s] Working hard! %d".formatted(Thread.currentThread().getName(),i));
			try {TimeUnit.MILLISECONDS.sleep(250);} catch (Exception e) {}
		}
		System.err.println("Application is done!");
	}

	public static int longRunningMethod() { // blocking: synchronous method
		try {TimeUnit.SECONDS.sleep(5);} catch (Exception e) {}
		return 42;
	}
	
	public static CompletableFuture<Integer> asyncLongRunningMethod() { // non-blocking: asynchronous method
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(5);} catch (Exception e) {}
			return 42;			
		});
	}
}
