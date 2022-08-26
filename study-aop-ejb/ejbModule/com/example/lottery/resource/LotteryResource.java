package com.example.lottery.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.service.LotteryService;

@Path("/numbers")
public class LotteryResource {

	@EJB
	private LotteryService lotteryService;
	
	// GET http://localhost:8080/lottery/api/v1/numbers?max=60&size=6&column=10
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Integer>> getLotteryNumbers(
			@QueryParam("max") int max,
			@QueryParam("size") int size,
			@QueryParam("column") int column){
		return lotteryService.draw(max, size, column);
	}
}
