package com.example.lottery.interceptors;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Profile
public class ProfileInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var method= ic.getMethod();
		var methodName = method.getName();
		var start = System.nanoTime();
		var result = ic.proceed();
		var stop = System.nanoTime();
		System.err.println(String.format("%s runs %d ns.",methodName, (stop - start)));
		return result;	
	}
}
