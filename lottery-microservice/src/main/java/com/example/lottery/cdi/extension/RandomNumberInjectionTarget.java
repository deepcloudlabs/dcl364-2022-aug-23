package com.example.lottery.cdi.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import com.example.lottery.model.RandomNumber;

public class RandomNumberInjectionTarget<T> implements InjectionTarget<T> {

	private final InjectionTarget<T> injectionTarget;

	public RandomNumberInjectionTarget(InjectionTarget<T> injectionTarget) {
		this.injectionTarget = injectionTarget;
	}

	@Override
	public void dispose(T instance) {
		injectionTarget.dispose(instance);
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return injectionTarget.getInjectionPoints();
	}

	@Override
	public T produce(CreationalContext<T> context) {
		return injectionTarget.produce(context);
	}

	@Override
	public void inject(T instance, CreationalContext<T> context) {
		var clazz = instance.getClass();
		System.err.println("injecting random numbers into ..."+clazz.getName());
		for (var field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				var randomNumber = field.getAnnotation(RandomNumber.class);
				var min = randomNumber.min();
				var max = randomNumber.max();
				var size = randomNumber.size();
				var sorted = randomNumber.sorted();
				var distinct = randomNumber.distinct();
				var column = randomNumber.column();
				var numbers = new ArrayList<List<Integer>>();
				numbers.add(List.of(1, 2, 3, 4, 5, 6));
				numbers.add(List.of(4, 8, 15, 16, 23, 42));
				try {
					field.setAccessible(true);
					field.set(instance, numbers);
					field.setAccessible(false);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void postConstruct(T instance) {
		injectionTarget.postConstruct(instance);

	}

	@Override
	public void preDestroy(T instance) {
		injectionTarget.preDestroy(instance);
	}

}
