package com.example;

@SuppressWarnings("unused")
public class StudyFunctionalInterface {

	public static void main(String[] args) {
		// Function Programming i) HoF ii) Pure Function
		int x = 42; // value
		A y = new A(42); // reference
		// i) Lambda Expression ii) Method Reference
		S s = u -> u * u * u + x;
		S t = p -> y.run(p);
		S r = y::run; // method reference
	}

}

record A(int x) {
	public int run(int v) {
		return v * v + 42;
	}
	public S sun() { // Higher-Order Function
		return o -> o*o*o*o*o;
	}
	public int gun(S s) { // Higher-Order Function
		return s.fun(42);
	}
}

@FunctionalInterface
interface S {
	// SAM
	int fun(int x);
}