package com.example.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.example.aop.Profiler;

@Service
@Profiler(TimeUnit.NANOSECONDS)
public class SimpleCalculator implements ApplicationContextAware {
	// @Autowired private Calculator self;
	private SimpleCalculator self = this;
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext  = applicationContext;
	}
	
	@PostConstruct
	public void init() {
		self = applicationContext.getBean(SimpleCalculator.class);
		System.err.println("Proxy: "+self.getClass().getName());
	}
	
	public int add(int x, int y) {
		return x + y;
	}

	public int sub(int x, int y) {
		return x - y;
	}

	public int mul(int x, int y) {
		int sum = 0;
		for (int i = 0; i < x; ++i, sum = self.add(sum, y))
			;
		return sum;
	}

	public int div(int x, int y) {
		return x / y;
	}


}
