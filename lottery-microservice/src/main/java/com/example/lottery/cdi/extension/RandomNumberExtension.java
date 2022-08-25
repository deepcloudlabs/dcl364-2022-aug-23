package com.example.lottery.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import com.example.lottery.model.RandomNumber;

public class RandomNumberExtension implements Extension {

	public <T> void intializeRandomNumberExtension(@Observes ProcessInjectionTarget<T> pit) {
		System.err.println("intializeRandomNumberExtension()");
		var annotationType = pit.getAnnotatedType();
		for (var field : annotationType.getJavaClass().getDeclaredFields()) {
			System.err.println("Found: "+field);
			if (field.isAnnotationPresent(RandomNumber.class)) {
				System.err.println(field+" annotated with RandomNumber");
				pit.setInjectionTarget(new RandomNumberInjectionTarget<>(pit.getInjectionTarget()));
			}
		}
	}
}
