package com.example.lottery.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LotteryDTO {
	private Integer[][] numbers;

	public LotteryDTO() {
	}

	public LotteryDTO(ArrayList<ArrayList<Integer>> numbers) {
		this.numbers = new Integer[numbers.size()][];
		for (var i=0;i<numbers.size();++i)
		    this.numbers[i] = numbers.get(i).toArray(new Integer[0]);
	}


	public Integer[][] getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer[][] numbers) {
		this.numbers = numbers;
	}

}
