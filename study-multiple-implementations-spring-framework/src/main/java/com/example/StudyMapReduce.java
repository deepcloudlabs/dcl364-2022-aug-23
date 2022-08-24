package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class StudyMapReduce {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(4, 8, 15, 16, 23, 42);
		Stream<Integer> stream = numbers.stream();
		if (numbers.size() > 1_000_000 && Runtime.getRuntime().availableProcessors() >= 8)
			stream = stream.parallel();
		int sum = stream.filter(i -> i % 2 == 1).mapToInt(x -> x * x).sum();
		System.out.println(sum);
	}
}
