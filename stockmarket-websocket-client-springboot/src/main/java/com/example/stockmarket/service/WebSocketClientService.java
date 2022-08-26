package com.example.stockmarket.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.stockmarket.event.StockPriceChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WebSocketClientService implements WebSocketHandler {

	@Autowired
	private WebSocketClient webSocketClient;
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, "ws://localhost:8080/stockmarket/changes");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the market server!");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String payload = message.getPayload().toString();
		var event = objectMapper.readValue(payload, StockPriceChangedEvent.class);
		System.err.println("New message has arrived: %s.".formatted(event));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("Error: %s.".formatted(exception.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("WebSocket connection is closed.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
