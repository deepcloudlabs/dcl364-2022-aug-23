package com.example.lottery.cdi.extension;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
				var numbers = IntStream.range(0, column)
						               .mapToObj(i -> getNumbers(min,max,size,distinct,sorted))
						               .collect(Collectors.toList());
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

	private List<Integer> getNumbers(int min, int max, int size, boolean distinct, boolean sorted) {
		var numbers = ThreadLocalRandom.current()
				                       .ints(min, max);
		if (distinct)
			numbers = numbers.distinct();
		numbers = numbers.limit(size);
		if (sorted)
			numbers = numbers.sorted();
		return numbers.boxed().collect(Collectors.toList());
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
