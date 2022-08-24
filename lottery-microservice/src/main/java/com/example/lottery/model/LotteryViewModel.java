package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named("model")
@SessionScoped
public class LotteryViewModel implements Serializable {
	private final List<List<Integer>> lotteryNumbers = new ArrayList<>();

	public List<List<Integer>> getLotteryNumbers() {
		return lotteryNumbers;
	}
	
	public void addLotteryNumbers(List<Integer> numbers) {
		this.lotteryNumbers.add(numbers);
	}
}
