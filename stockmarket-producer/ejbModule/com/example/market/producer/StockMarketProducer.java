package com.example.market.producer;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

@Stateless
public class StockMarketProducer {
	@Inject
	private JMSContext jmsContext;
	
	@Resource(mappedName = "java:/jms/queue/stockQueue")
	private Queue queue;
	
	@Schedule(second="*/5", minute = "*", hour = "*")
	public void sendStockPriceChanges() {
		var eventAsJson = Json.createObjectBuilder()
	              .add("symbol", "ORCL")
	              .add("oldPrice", ThreadLocalRandom.current().nextDouble(100, 110))
	              .add("newPrice", ThreadLocalRandom.current().nextDouble(100, 110))
	              .build()
	              .toString();
		
		jmsContext.createProducer().send(queue, eventAsJson);
	}
}
