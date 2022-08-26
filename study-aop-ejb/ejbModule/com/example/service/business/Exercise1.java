package com.example.service.business;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import com.example.service.LotteryService;

@Stateless
public class Exercise1 {

	@EJB
	private LotteryService lotteryService;
	
	@Schedule(second ="*/5", minute="*", hour="*")
	public void callLotteryService() {
		System.err.println(lotteryService.draw(60, 6));
	}
}
