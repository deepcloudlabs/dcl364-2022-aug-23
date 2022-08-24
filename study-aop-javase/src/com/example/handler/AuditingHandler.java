package com.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

public class AuditingHandler implements InvocationHandler {
	private Object target;

	public AuditingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var methodName = method.getName();
		System.out
				.println("%s runs at %s with parameters %s.".formatted(methodName, new Date(), Arrays.toString(args)));
		var result = method.invoke(target, args);
		System.out.println("%s returns %s.".formatted(methodName, result));
		return result;
	}

}
