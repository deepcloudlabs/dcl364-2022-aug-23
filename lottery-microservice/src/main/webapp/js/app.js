class LotteryViewModel {
	constructor(){
		this.lotteryNumbers = ko.observableArray([]);
		this.drawLotteryNumbers = this.drawLotteryNumbers.bind(this);
		this.clearLotteryNumbers = this.clearLotteryNumbers.bind(this);
	}
	
	drawLotteryNumbers(){ // lambda expression
       fetch("http://localhost:8080/lottery/api/v1/numbers?max=60&size=6&column=1")
           .then(res => res.json())
           .then( numbers => this.lotteryNumbers.push(numbers[0]));  		
	}
	
	clearLotteryNumbers(){
		this.lotteryNumbers([]);
	}
}

let viewModel = new LotteryViewModel() ;

window.onload = () => {
	ko.applyBindings(viewModel);	
}