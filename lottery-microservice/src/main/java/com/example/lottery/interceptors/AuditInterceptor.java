package com.example.lottery.interceptors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Audit
public class AuditInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var methodName = ic.getMethod().getName();
		System.err.println(String.format("%s runs at %s with parameters %s.",methodName, new Date(), Arrays.toString(ic.getParameters())));
		var result = ic.proceed();
		System.err.println(String.format("%s returns %s.",methodName, result));
		return result;		
	}
}
