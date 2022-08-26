package com.example.market.application;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.market.application.event.StockPriceChangedEvent;

@ServerEndpoint("/changes")
@Singleton
public class StockMarketWebSocketService {

	private Map<String, Session> sessions = new ConcurrentHashMap<>();

	@OnOpen
	public void openConnection(Session session) {
		sessions.put(session.getId(), session);
	}

	@OnClose
	public void closeConnection(Session session) {
		sessions.remove(session.getId());
	}
	
	public void listenStockEvents(@Observes StockPriceChangedEvent event) {
		var eventAsJson = Json.createObjectBuilder()
				              .add("symbol", event.getSymbol())
				              .add("oldPrice", event.getOldPrice())
				              .add("newPrice", event.getNewPrice())
				              .build()
				              .toString();
		sessions.forEach((sessionId,session) -> {
			try {
				session.getBasicRemote().sendText(eventAsJson);
			} catch (IOException e) {
				throw new IllegalArgumentException("Cannot convert event to json.");
			}
		});
	}
}
