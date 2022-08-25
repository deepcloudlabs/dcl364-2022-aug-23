package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.AppConfig;
import com.example.service.impl.SimpleCalculator;

public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container =
		new AnnotationConfigApplicationContext(
						AppConfig.class);
		SimpleCalculator calculator = 
				container.getBean(SimpleCalculator.class);
		System.err.println(calculator.getClass());
		System.err.println(calculator.add(3, 5));
		System.err.println(calculator.sub(42, 18));
		System.err.println(calculator.mul(7, 4));
		System.err.println(calculator.div(12, 4));
		container.close();
	}
}
