package com.example.lottery.service.business;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class LongRunningService {

	@Asynchronous
	public Future<Integer> getResult() {
		try { TimeUnit.SECONDS.sleep(3);} catch(Exception e) {}
		return new AsyncResult<Integer>(42);
	}
}
