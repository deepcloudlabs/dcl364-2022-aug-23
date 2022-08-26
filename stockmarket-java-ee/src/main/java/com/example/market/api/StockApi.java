package com.example.market.api;


import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.example.market.application.StockMarketApplication;
import com.example.market.application.event.StockPriceChangedEvent;
import com.example.market.entity.Stock;

@Path("/stocks")
@ApplicationScoped
public class StockApi {

	@Inject
	private StockMarketApplication application;
	@Resource(lookup = "")
	private ManagedExecutorService executorService;
	
	@SuppressWarnings("unused")
	private Sse sse;
	private SseBroadcaster sseBroadcaster;
	private OutboundSseEvent.Builder eventBuilder;
	
	@Context
	public void setSse(Sse sse) {
		this.sse = sse;
		this.sseBroadcaster = sse.newBroadcaster();
		this.eventBuilder = sse.newEventBuilder();
	}

	@GET
	@Path("subscribe")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void listen(@Context SseEventSink sseEventSink) {
		this.sseBroadcaster.register(sseEventSink);
	}
	
	public void listenStockEvents(@Observes StockPriceChangedEvent event) {
		var eventAsJson = Json.createObjectBuilder()
				              .add("symbol", event.getSymbol())
				              .add("oldPrice", event.getOldPrice())
				              .add("newPrice", event.getNewPrice())
				              .build()
				              .toString();
		var sseEvent = this.eventBuilder.name("stockPriceChangedEvent")
				           .id(UUID.randomUUID().toString())
				           .mediaType(MediaType.APPLICATION_JSON_TYPE)
				           .data(eventAsJson)
				           .reconnectDelay(3_000)
				           .comment("price changed")
				           .build();
		sseBroadcaster.broadcast(sseEvent);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{symbol}")
	// curl -X GET "http://localhost:8080/stockmarket/api/v1/stocks/ORCL" -H "Accept: application/json"
	public Stock findStockBySymbol(@PathParam("symbol") String symbol) {
		return application.findStock(symbol);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	// curl -X GET "http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=25" -H "Accept: application/json"
	public void findStocks(
			@QueryParam("page") int pageNo,
			@QueryParam("size") int pageSize,
			@Suspended AsyncResponse asyncResponse   
	) {
		executorService.execute( () -> asyncResponse.resume(application.findStocks(pageNo,pageSize)));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)	
	// curl -X POST "http://localhost:8080/stockmarket/api/v1/stocks" -d "{\"symbol\": \"ORCL\", \"company\": \"Oracle Inc.\", \"description\": \"no description.\", \"price\": 121.34}" -H "Content-Type: application/json"  -H "Accept: application/json"
	public Stock addStock(@Valid Stock stock) {
		return application.persistStock(stock);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("{symbol}")	
	// curl -X PUT "http://localhost:8080/stockmarket/api/v1/stocks/ORCL" -d "{\"symbol\": \"ORCL\", \"company\": \"Oracle Inc.\", \"description\": \"no description.\", \"price\": 120.74}" -H "Content-Type: application/json"  -H "Accept: application/json"
	public Stock updateStock(@PathParam("symbol") String symbol, @Valid Stock stock) {
		return application.updateStock(symbol,stock);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("{symbol}")	
	// curl -X DELETE "http://localhost:8080/stockmarket/api/v1/stocks/ORCL"
	public Stock removeStock(@PathParam("symbol") String symbol) {
		return application.deleteStock(symbol);
	}
}
