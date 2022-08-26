package com.example.application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class StockMarketClientApplication {

	public static void main(String[] args) throws InterruptedException {
		var listener = new StockMarketListener();
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create("ws://localhost:8080/stockmarket/changes"), listener);
        TimeUnit.SECONDS.sleep(30);
	}

}

class StockMarketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connection is open!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Connection is closed!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error: "+error.getMessage());
		Listener.super.onError(webSocket, error);
	}
	
}