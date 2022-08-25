package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.lottery.interceptors.Audit;
import com.example.lottery.interceptors.Profile;

@SuppressWarnings("serial")
@Named("model")
@SessionScoped
@Profile
@Audit
public class LotteryViewModel implements Serializable {
	@RandomNumber(min=1,max=60,size=6,distinct=true,sorted=true,column=5)
	private final List<List<Integer>> lotteryNumbers = new ArrayList<>();

	public List<List<Integer>> getLotteryNumbers() {
		return lotteryNumbers;
	}
	
	public void addLotteryNumbers(List<Integer> numbers) {
		this.lotteryNumbers.add(numbers);
	}
}
