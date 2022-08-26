package com.example.lottery.soap;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.example.lottery.dto.LotteryDTO;
import com.example.lottery.service.LotteryService;

@WebService(endpointInterface = "com.example.lottery.soap.LotteryWebService")
public class LotterySOAPWebService {

	@Inject
	private LotteryService lotteryService;
	
	@WebMethod(operationName = "cekilis")
	public LotteryDTO draw(	
		@WebParam(name = "maximum") int max, 
		@WebParam(name = "length") int size, 
		@WebParam(name = "column") int column
	){
		var numbers = new ArrayList<ArrayList<Integer>>();
		lotteryService.draw(max, size, column)
		              .forEach(nums -> {
		            	  System.err.println(nums);
		            	  var list = new ArrayList<Integer>();
		            	  nums.forEach(list::add);
		            	  numbers.add(list);
		               });
		return new LotteryDTO(numbers);
	}
}
