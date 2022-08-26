package com.example.application;

import java.lang.reflect.Proxy;

import com.example.handler.AuditingHandler;
import com.example.handler.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.business.StandardCalculator;

public class CalculatorApplication {

	public static void main(String[] args) {
		StandardCalculator standardCalculator = new StandardCalculator();
		var clazz = standardCalculator.getClass();
		Calculator calculator = (Calculator) Proxy.newProxyInstance(
				clazz.getClassLoader(),
				clazz.getInterfaces(), 
				new AuditingHandler(standardCalculator));
		calculator = (Calculator) Proxy.newProxyInstance(
				clazz.getClassLoader(),
				clazz.getInterfaces(), 
				new ProfilingHandler(calculator));
		standardCalculator.setSelf(calculator);
		System.out.println("3 + 5 = %f.".formatted(calculator.add(3, 5)));
		System.out.println("3 - 5 = %f.".formatted(calculator.sub(3, 5)));
		System.out.println("3 * 5 = %f.".formatted(calculator.mul(3, 5)));
		System.out.println("3 / 5 = %f.".formatted(calculator.div(3, 5)));

	}

}
