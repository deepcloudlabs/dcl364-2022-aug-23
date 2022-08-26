package com.example.lottery.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.example.lottery.dto.LotteryDTO;

@WebService
public interface LotteryWebService {
	
	@WebMethod(operationName = "cekilis")
	public LotteryDTO draw(
			@WebParam(name = "maximum") int max, 
			@WebParam(name = "length") int size,
			@WebParam(name = "column") int column);
}
